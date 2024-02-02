package com.hero.wireless.web.service;

import com.drondea.wireless.config.Constant;
import com.drondea.wireless.config.ResultStatus;
import com.drondea.wireless.util.*;
import com.hero.wireless.enums.AccountStatus;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.config.ExceptionKey;
import com.hero.wireless.web.dao.business.*;
import com.hero.wireless.web.dao.business.ext.IAgentExtDAO;
import com.hero.wireless.web.dao.business.ext.IAgentLimitExtDAO;
import com.hero.wireless.web.dao.business.ext.IAgentRoleExtDAO;
import com.hero.wireless.web.entity.business.*;
import com.hero.wireless.web.entity.business.ext.AgentChargeExt;
import com.hero.wireless.web.entity.business.ext.AgentExt;
import com.hero.wireless.web.entity.business.ext.AgentLimitExt;
import com.hero.wireless.web.entity.business.ext.AgentRoleExt;
import com.hero.wireless.web.exception.BaseException;
import com.hero.wireless.web.util.CodeUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author volcano
 * @version V1.0
 * @createTime 2019年9月17日上午2:32:03
 */
@Service("agentManage")
public class AgentManageImpl implements IAgentManage {
    @Resource
    private IAgentDAO<Agent> agentDAO;
    @Resource
    private IAgentFeeDAO<AgentFee> agentFeeDAO;
    @Resource
    private IAgentChargeDAO<AgentCharge> agentChargeDAO;
    @Resource(name = "IAgentLimitDAO")
    private IAgentLimitDAO<AgentLimit> agentLimitDAO;
    @Resource(name = "agentLimitExtDAO")
    private IAgentLimitExtDAO agentLimitExtDAO;
    @Resource(name = "IAgentRoleLimitDAO")
    private IAgentRoleLimitDAO<AgentRoleLimit> agentRoleLimitDAO;
    @Resource
    private IAgentRoleDAO<AgentRole> agentRoleDAO;
    @Resource
    private IAgentRolesDAO<AgentRoles> agentRolesDAO;
    @Resource(name = "agentRoleExtDAO")
    private IAgentRoleExtDAO agentRoleExtDAO;
    @Resource(name = "agentExtDAO")
    private IAgentExtDAO agentExtDAO;
    @Resource(name = "IPropertiesDAO")
    private IPropertiesDAO<Properties> propertiesDAO;

    @Override
    public Agent agentLogin(Agent agent) {
        if (agent == null || StringUtils.isEmpty(agent.getUser_Name())
                || StringUtils.isEmpty(agent.getPassword())) {
            throw new ServiceException(ResultStatus.USER_PASSWORD_ERROR);
        }

        //rsa解密
        agent.setPassword(getPlainPassword(agent.getPassword()));

        AgentExample example = new AgentExample();
        AgentExample.Criteria cri = example.createCriteria();
        cri.andUser_NameEqualTo(agent.getUser_Name());
        cri.andPasswordEqualTo(SecretUtil.MD5(agent.getPassword()));

        List<AgentExt> agents = agentExtDAO.selectRolesAndLimitsByAgentExample(example);
        if (agents == null || agents.isEmpty()) {
            throw new ServiceException(ResultStatus.USER_PASSWORD_ERROR);
        }
        Agent loginResult = agents.get(0);
        String userStatus = loginResult.getStatus();
        if (!StringUtils.isEmpty(userStatus) && !userStatus.equals(AccountStatus.NORMAL.toString())) {
            throw new ServiceException(ResultStatus.ACCOUT_LOCKED);
        }
        return loginResult;
    }

    @Override
    public List<Agent> queryAgentList(AgentExt agentExt) {
        AgentExample example = new AgentExample();
        AgentExample.Criteria criteria = example.createCriteria();
        if (agentExt.getId() != null) {
            criteria.andIdEqualTo(agentExt.getId());
        }
        if (StringUtils.isNotBlank(agentExt.getNo())) {
            criteria.andNoEqualTo(agentExt.getNo());
        }
        if (StringUtils.isNotBlank(agentExt.getUser_Name())) {
            criteria.andUser_NameEqualTo(agentExt.getUser_Name());
        }
        if (StringUtils.isNotBlank(agentExt.getStatus())) {
            criteria.andStatusEqualTo(agentExt.getStatus());
        }
        if (StringUtils.isNotBlank(agentExt.getDomain())) {
            criteria.andDomainEqualTo(agentExt.getDomain());
        }
        if (agentExt.getMin_Available_Money() != null) {
            criteria.andAvailable_AmountGreaterThanOrEqualTo(agentExt.getMin_Available_Money());
        }
        if (agentExt.getMax_Available_Money() != null) {
            criteria.andAvailable_AmountLessThanOrEqualTo(agentExt.getMax_Available_Money());
        }
        example.setOrderByClause(" id desc");
        example.setPagination(agentExt.getPagination());
        return agentDAO.selectByExamplePage(example);
    }

    @Override
    public List<AgentFee> queryAgentFeeList(AgentFee agentFee) {
        AgentFeeExample example = new AgentFeeExample();
        AgentFeeExample.Criteria criteria = example.createCriteria();
        if (null != agentFee.getId()) {
            criteria.andIdEqualTo(agentFee.getId());
        }
        if (StringUtils.isNotBlank(agentFee.getAgent_No())) {
            criteria.andAgent_NoEqualTo(agentFee.getAgent_No());
        }
        if (StringUtils.isNotBlank(agentFee.getOperator())) {
            criteria.andOperatorEqualTo(agentFee.getOperator());
        }
        if (StringUtils.isNotBlank(agentFee.getTrade_Type_Code())) {
            criteria.andTrade_Type_CodeEqualTo(agentFee.getTrade_Type_Code());
        }
        if (StringUtils.isNotBlank(agentFee.getMessage_Type_Code())) {
            criteria.andMessage_Type_CodeEqualTo(agentFee.getMessage_Type_Code());
        }
        if (StringUtils.isNotBlank(agentFee.getCountry_Number())) {
            criteria.andCountry_NumberEqualTo(agentFee.getCountry_Number());
        }
        example.setPagination(agentFee.getPagination());
        return agentFeeDAO.selectByExamplePage(example);
    }

    @Override
    @Transactional(transactionManager = "txBusinessManager", rollbackFor = Exception.class)
    public Agent addAgent(Agent data) {
        // 查询用户名是否存在
        AgentExample agentExample = new AgentExample();
        agentExample.createCriteria().andUser_NameEqualTo(data.getUser_Name());
        List<Agent> auiList = agentDAO.selectByExample(agentExample);
        if (auiList != null && auiList.size() > 0) {
            throw new ServiceException(ResultStatus.USER_NAME_EXSITS);
        }
        agentExample = new AgentExample();
        agentExample.createCriteria().andNameEqualTo(data.getName());
        auiList = agentDAO.selectByExample(agentExample);
        if (auiList != null && auiList.size() > 0) {
            throw new ServiceException("该代理名称已被占用");
        }
        data.setNo(CodeUtil.buildAgentNo());
        // MD5加密
        data.setPassword(SecretUtil.MD5(data.getPassword()));
        data.setCreate_Date(new Date());
        data.setUsed_Amount(new BigDecimal(0));
        data.setAvailable_Amount(new BigDecimal(0));
        data.setSent_Count(0);
        data.setStatus("Normal");
        data.setLogin_Faild_Count(0);

        agentDAO.insert(data);
        //新增代理商添加默认角色
        AgentRoleExample roleExample = new AgentRoleExample();
        AgentRoleExample.Criteria cri = roleExample.createCriteria();
        cri.andCodeEqualTo(Constant.DEFAULT_ROLE_CODE);
        List<AgentRole> agentRoleList = this.agentRoleDAO.selectByExample(roleExample);
        if (agentRoleList != null && !agentRoleList.isEmpty()) {
            AgentRoles agentRoles = new AgentRoles();
            agentRoles.setCreate_Date(new Date());
            agentRoles.setAgent_Id(data.getId());
            agentRoles.setRole_Id(agentRoleList.get(0).getId());
            this.agentRolesDAO.insert(agentRoles);
        }
        return data;
    }
    private void setDefaultAgentNo(Agent data){
        data.setNo(String.format("%05d", data.getId()));
        while (true){
            AgentExample agentExample = new AgentExample();
            agentExample.createCriteria().andNoEqualTo(data.getNo()).andStatusNotEqualTo(AccountStatus.DELETE.toString());
            List<Agent> agentList = agentDAO.selectByExample(agentExample);
            if (agentList != null && !agentList.isEmpty()) {
                SuperLogger.error("增加代理"+data.getName()+"编号: "+data.getNo()+"重复");
                Integer no = Integer.valueOf(data.getNo());
                data.setNo(String.format("%05d", no + 1));
            }else{
                break;
            }
        }
    }

    @Override
    public Integer editAgent(AgentExt agentExt) {
        if (StringUtils.isNotBlank(agentExt.getUser_Name())) {
            AgentExample agentExample = new AgentExample();
            AgentExample.Criteria criteria = agentExample.createCriteria();
            criteria.andUser_NameEqualTo(agentExt.getUser_Name());
            criteria.andIdNotEqualTo(agentExt.getId());
            if (ObjectUtils.isNotEmpty(agentDAO.selectByExample(agentExample))) {
                throw new BaseException(ExceptionKey.USER_NAME_EXSITS, agentExt.getUser_Name());
            }
        }
        agentExt.setPassword(StringUtils.isNotEmpty(agentExt.getPassword()) ?
                SecretUtil.MD5(agentExt.getPassword()) : null);
        return this.agentDAO.updateByPrimaryKeySelective(agentExt);
    }

    @Override
    public Agent editPassword(Agent agent, String oldPassword) {
        AgentExample example = new AgentExample();
        AgentExample.Criteria cri = example.createCriteria();
        cri.andUser_NameEqualTo(agent.getUser_Name());
        cri.andPasswordEqualTo(SecretUtil.MD5(oldPassword));
        List<Agent> agentList = this.agentDAO.selectByExample(example);
        if (agentList == null || agentList.isEmpty()) {
            throw new ServiceException("原始密码错误！");
        }
        // MD5加密
        agent.setPassword(SecretUtil.MD5(agent.getPassword()));
        agentDAO.updateByPrimaryKeySelective(agent);
        return agent;
    }

    @Override
    public void delAgentFee(List<Integer> ckIds) {
        for (Integer id : ckIds) {
            this.agentFeeDAO.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void charge(AgentCharge agentCharge) {
        Agent agent = DatabaseCache.getAgentByNo(agentCharge.getAgent_No());
        agentCharge.setOrder_Number(UUID.randomUUID().toString());
        agentCharge.setCreate_Date(new Date());
        agentCharge.setBefore_Money(agent.getAvailable_Amount());
        this.agentChargeDAO.insert(agentCharge);
        Agent updateBean = new Agent();
        updateBean.setId(agent.getId());
        updateBean.setAvailable_Amount(agent.getAvailable_Amount().add(agentCharge.getMoney()));
        this.agentDAO.updateByPrimaryKeySelective(updateBean);
    }

    @Override
    @SuppressWarnings("all")
    public List<AgentCharge> queryAgentChargeList(AgentChargeExt agentChargeExt) {
        AgentChargeExample example = new AgentChargeExample();
        AgentChargeExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(agentChargeExt.getAgent_No())) {
            criteria.andAgent_NoEqualTo(agentChargeExt.getAgent_No());
        }
        if (StringUtils.isNotBlank(agentChargeExt.getMin_Create_Date())) {
            criteria.andCreate_DateGreaterThanOrEqualTo(DateTime.getDate(agentChargeExt.getMin_Create_Date() + " 00:00:00"));
        }
        if (StringUtils.isNotBlank(agentChargeExt.getMax_Create_Date())) {
            criteria.andCreate_DateLessThanOrEqualTo(DateTime.getDate(agentChargeExt.getMax_Create_Date() + " 23:59:59"));
        }
        example.setOrderByClause("id desc ");
        example.setPagination(agentChargeExt.getPagination());
        return agentChargeDAO.selectByExamplePage(example);
    }

    @Override
    public Integer updateAgentStatus(List<Integer> uIdList, String status) {
        AgentExample example = new AgentExample();
        AgentExample.Criteria cri = example.createCriteria();
        cri.andIdIn(uIdList);
        Agent record = new Agent();
        record.setStatus(status);
        return this.agentDAO.updateByExampleSelective(record, example);
    }

    @Override
    public List<AgentLimit> queryAgentLimitList(AgentLimit condition) {
        AgentLimitExample example = new AgentLimitExample();
        AgentLimitExample.Criteria cri = example.createCriteria();
        if (!StringUtils.isEmpty(condition.getUp_Code())) {
            cri.andUp_CodeEqualTo(condition.getUp_Code());
        }
        if (!StringUtils.isEmpty(condition.getCode())) {
            cri.andCodeEqualTo(condition.getCode());
        }
        if (!StringUtils.isEmpty(condition.getName())) {
            cri.andNameLike("%" + condition.getName() + "%");
        }
        if (condition.getId() != null) {
            cri.andIdEqualTo(condition.getId());
        }
        example.setPagination(condition.getPagination());
        return this.agentLimitDAO.selectByExamplePage(example);
    }

    @Override
    public void addAgentLimit(AgentLimit data) {
        AgentLimit newData = new AgentLimit();
        newData.setCode(data.getCode());
        List<AgentLimit> list = this.queryAgentLimitList(newData);
        if (list != null && !list.isEmpty()) {
            throw new ServiceException(ResultStatus.LIMIT_CODE_EXSITS, data.getCode());
        }
        data.setCreate_Date(new Date());
        this.agentLimitDAO.insert(data);
    }

    @Override
    @SuppressWarnings("all")
    public List<AgentRole> queryAgentRoleList(AgentRole condition) {
        AgentRoleExample example = new AgentRoleExample();
        AgentRoleExample.Criteria cri = example.createCriteria();
        if (!StringUtils.isEmpty(condition.getUp_Code())) {
            cri.andUp_CodeEqualTo(condition.getUp_Code());
        }
        if (!StringUtils.isEmpty(condition.getName())) {
            cri.andNameLike("%" + condition.getName() + "%");
        }
        if (!StringUtils.isEmpty(condition.getCode())) {
            cri.andCodeEqualTo(condition.getCode());
        }
        if (condition.getId() != null) {
            cri.andIdEqualTo(condition.getId());
        }
        example.setPagination(condition.getPagination());
        return this.agentRoleDAO.selectByExamplePage(example);
    }

    @Override
    public AgentRole addAgentRole(AgentRole data) {
        AgentRole newBean = new AgentRole();
        newBean.setCode(data.getCode());
        List<AgentRole> list = this.queryAgentRoleList(newBean);
        if (list != null && !list.isEmpty()) {
            throw new ServiceException(ResultStatus.ROLE_CODE_EXSITS, data.getCode());
        }
        data.setCreate_Date(new Date());
        this.agentRoleDAO.insert(data);
        return data;
    }

    @Override
    public List<AgentLimitExt> queryBindRoleLimitList(List<Integer> roleIdList) {
        if (roleIdList == null || roleIdList.isEmpty()) {
            return null;
        }
        return this.agentLimitExtDAO.selectBindRoleLimitByRoleId(roleIdList.get(0));
    }

    @Override
    public List<AgentRoleExt> queryBindAgentRoleList(List<Integer> ckIds) {
        if (ckIds == null || ckIds.isEmpty()) {
            return null;
        }
        return this.agentRoleExtDAO.selectBindAgentRoleByExample(ckIds.get(0));
    }

    @Override
    public void bindRoleLimit(List<AgentRoleLimit> roleLimitList) {
        // 删除现有的权限
        List<Integer> roleIds = new ArrayList<>();
        for (AgentRoleLimit agentRoleLimit : roleLimitList) {
            roleIds.add(agentRoleLimit.getRole_Id());
        }
        if (!roleIds.isEmpty()) {
            AgentRoleLimitExample example = new AgentRoleLimitExample();
            AgentRoleLimitExample.Criteria cri = example.createCriteria();
            cri.andRole_IdIn(roleIds);
            this.agentRoleLimitDAO.deleteByExample(example);
        }
        for (AgentRoleLimit agentRoleLimit : roleLimitList) {
            agentRoleLimit.setCreate_Date(new Date());
        }
        if (!roleLimitList.isEmpty()) {
            this.agentRoleLimitDAO.insertList(roleLimitList);
        }
    }

    @Override
    public boolean bindAgentRoles(List<AgentRoles> agentRolesList) {
        for (AgentRoles agentRoles : agentRolesList) {
            AgentRolesExample example = new AgentRolesExample();
            example.createCriteria().andAgent_IdEqualTo(agentRoles.getAgent_Id());
            int returnResult = this.agentRolesDAO.deleteByExample(example);
            SuperLogger.debug("删除行数:" + returnResult);
        }
        for (AgentRoles agentRoles : agentRolesList) {
            agentRoles.setCreate_Date(new Date());
            this.agentRolesDAO.insert(agentRoles);
        }
        return true;
    }

    @Override
    public Integer editLimit(AgentLimit agentLimit) {
        return this.agentLimitDAO.updateByPrimaryKeySelective(agentLimit);
    }

    @Override
    public void editRole(AgentRole agentRole) {
        this.agentRoleDAO.updateByPrimaryKeySelective(agentRole);
    }

    @Override
    public int deleteAgentLimit(List<Integer> ids) {
        if (ObjectUtils.isEmpty(ids)) {
            throw new BaseException("请选择权限");
        }
        return ids.stream().reduce(0, (row, id) -> {
            AgentLimit limit = this.agentLimitDAO.selectByPrimaryKey(id);
            AgentLimitExample deleteExample = new AgentLimitExample();
            deleteExample.createCriteria().andCodeLike(limit.getCode() + "%");
            return this.agentLimitDAO.deleteByExample(deleteExample);
        }, Integer::sum);
    }

    @Override
    public List<Agent> queryAddAgentCount() {
        AgentExample example = new AgentExample();
        example.createCriteria().andCreate_DateGreaterThan(DateTime.getDate(DateTime.getCurrentDayMinDate()));
        return agentDAO.selectByExample(example);
    }


    @Override
    public void addAgentFee(AgentFee agentFee) {
        List<AgentFee> agentFees = this.queryAgentFeeList(agentFee);
        if (agentFees.size()>0){
            throw new ServiceException(ResultStatus.AGENT_FEE_EXIST);
        }
        agentFee.setCreate_Date(new Date());
        this.agentFeeDAO.insert(agentFee);
    }

    @Override
    public void editAgentFee(AgentFee agentFee) {
        this.agentFeeDAO.updateByPrimaryKeySelective(agentFee);
    }

    private String getPlainPassword(String encryptPassword) {
        //rsa解密
        String passwordPrivateKey = DatabaseCache.getStringValueBySystemEnvAndCode("password_private_key", "");
        String plainText = null;
        if (StringUtils.isNotBlank(passwordPrivateKey)) {
            plainText = CertificateUtil.decryptByPrivateKey(encryptPassword, passwordPrivateKey);
            if (StringUtils.isEmpty(plainText)) {
                SuperLogger.error("密码解密错误，请检查私钥");
                throw new ServiceException(ResultStatus.ERROR);
            }
        }
        return plainText;
    }
}

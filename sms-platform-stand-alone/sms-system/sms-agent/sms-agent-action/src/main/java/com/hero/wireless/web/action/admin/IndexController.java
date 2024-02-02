package com.hero.wireless.web.action.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.drondea.wireless.util.DateTime;
import com.hero.wireless.web.action.admin.config.MenuInfo;
import com.hero.wireless.web.config.SystemKey;
import com.hero.wireless.web.entity.business.*;
import com.hero.wireless.web.entity.business.ext.AgentExt;
import com.hero.wireless.web.entity.business.ext.EnterpriseExt;
import com.hero.wireless.web.entity.business.ext.EnterpriseUserExt;
import com.hero.wireless.web.entity.business.ext.SmsRealTimeStatisticsExt;
import com.hero.wireless.web.service.IChargeManage;
import com.hero.wireless.web.service.IEnterpriseManage;
import com.hero.wireless.web.service.IStatisticsManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hero.wireless.json.LayUiObjectMapper;
import com.hero.wireless.web.action.BaseAgentController;
import com.hero.wireless.web.config.DatabaseCache;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class IndexController extends BaseAgentController {

    @Resource(name = "statisticsManage")
    private IStatisticsManage statisticsManage;
    @Resource(name = "chargeManage")
    private IChargeManage chargeManage;
    @Resource(name = "enterpriseManage")
    private IEnterpriseManage enterpriseManage;
    /**
     * 登录成功跳转至index页面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/home/console");
        return mv;
    }

    @RequestMapping("/initMenu")
    @ResponseBody
    public Map<String,Object> initMenu() {
        Map<String,Object> menuInfoMap = new HashMap();

        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setTitle("首页");
        menuInfo.setHref("/admin/home/console.jsp");
        menuInfoMap.put( "homeInfo", menuInfo);

        menuInfo = new MenuInfo();
        String menuTitle = DatabaseCache.getStringValueBySortCodeAndCode("page_configuration", "agent_menu_title", "菜单目录");
        menuInfo.setTitle(menuTitle);
        Code code = DatabaseCache.getCodeBySortCodeAndCode("page_configuration", "agent_menu_image");
        if(code!=null){
            String menuImage  = code.getValue();
            menuInfo.setImage(menuImage);
        }
        menuInfoMap.put( "logoInfo", menuInfo);

        HttpSession session = request.getSession();
        AgentExt users = (AgentExt) session
                .getAttribute(SystemKey.AGENT_INFO.toString());
        List<AgentLimit> limitList = users.getLimits();

        //菜单
        List<MenuInfo> menuInfoList = new ArrayList<>();
        MenuInfo menuInfoBase = new MenuInfo();;
        List<MenuInfo> child = new ArrayList<>();
        for (int i = 0; i < limitList.size(); i++) {
            if(limitList.get(i).getType_Code()!=null && limitList.get(i).getType_Code().equals("menu")){
                menuInfo = new MenuInfo();
                menuInfo.setTitle(limitList.get(i).getName());
                menuInfo.setHref(limitList.get(i).getUrl());
                menuInfo.setIcon(limitList.get(i).getIcon()==null ? "fa fa-bars":limitList.get(i).getIcon());
                menuInfo.setCode(limitList.get(i).getCode());
                menuInfo.setTarget("_self");
                if(limitList.get(i).getUp_Code().equals("00")){
                    if( menuInfoBase.getTitle()!=null){
                        menuInfoBase.setChild(child);
                        menuInfoList.add(menuInfoBase);
                        menuInfoBase = menuInfo;
                        child = new ArrayList<>();
                    }else{
                        menuInfoBase = menuInfo;
                    }
                }else{
                    child.add(menuInfo);
                }
            }

        }
        menuInfoBase.setChild(child);
        menuInfoList.add(menuInfoBase);

        menuInfoMap.put("menuInfo",menuInfoList);

        return menuInfoMap;
    }

    @RequestMapping("/index_currentDataMap")
    @ResponseBody
    public Map currentDataMap() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //新增企业数量统计
        EnterpriseExt enterpriseExt = new EnterpriseExt();
        enterpriseExt.setAgent_No(getAgentNo());
        enterpriseExt.setCreate_Date(DateTime.getDate(DateTime.getCurrentDayMinDate()));
        map.put("newEnterpriseCount", enterpriseManage.queryEnterpriseList(enterpriseExt).size());
        //新增企业用户数量统计
        List<String> enterpriseNoList = DatabaseCache.getEnterpriseNoByAgentNo(getLoginAgent());
        EnterpriseUserExt enterpriseUserExt = new EnterpriseUserExt();
        enterpriseUserExt.setCreate_Date(DateTime.getDate(DateTime.getCurrentDayMinDate()));
        enterpriseUserExt.setEnterpriseNoList(enterpriseNoList);
        List<EnterpriseUser> enterpriseUserList = enterpriseManage.queryEnterpriseUserList(enterpriseUserExt);
        map.put("newEnterpriseUserCount", enterpriseUserList.size());
        //发送量及利润统计
        SmsRealTimeStatisticsExt statisticsExt = new SmsRealTimeStatisticsExt();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        statisticsExt.setAgent_No(getAgentNo());
        statisticsExt.setMin_Submit_Date(sdf.parse(DateTime.getString()));
        statisticsExt.setGroupStr("Enterprise_User_Id");
        statisticsExt.setGroupStr("Submit_Date");
        List<SmsRealTimeStatisticsExt> smsRealTimeStatisticsExts = statisticsManage.querySmsRealTimeStatisticsGroupDataList(statisticsExt);
        int submitTotal = 0;
        int sendSuccessTotal = 0;
        double profits = 0;
        double cost = 0;
        if (smsRealTimeStatisticsExts.size()>0){
            SmsRealTimeStatisticsExt source = smsRealTimeStatisticsExts.get(0);
            submitTotal = source.getSubmit_Total();
            sendSuccessTotal = source.getSend_Success_Total();
            profits = source.getAgent_Profits()==null?0:source.getAgent_Profits().doubleValue();
            cost = source.getAgent_Cost()==null?0:source.getAgent_Cost().doubleValue();
        }
        map.put("submitTotal", submitTotal);
        map.put("sendSuccessCount", sendSuccessTotal);
        map.put("profitsTotal", profits);
        map.put("costTotal", cost);
        return map;
    }

    @RequestMapping("/index_enterpriseSendMap")
    @ResponseBody
    public String enterpriseSendMap() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            SmsRealTimeStatisticsExt statisticsExt = new SmsRealTimeStatisticsExt();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            statisticsExt.setAgent_No(getAgentNo());
            statisticsExt.setMin_Submit_Date(sdf.parse(DateTime.getString()));
            statisticsExt.setGroupStr("Enterprise_No");
            List<SmsRealTimeStatisticsExt> smsRealTimeStatisticsExts = statisticsManage.querySmsRealTimeStatisticsGroupDataList(statisticsExt);
            for (SmsRealTimeStatisticsExt source: smsRealTimeStatisticsExts){
                String enterpriseNo = source.getEnterprise_No();
                Enterprise enterprise = DatabaseCache.getEnterpriseByNo(enterpriseNo);
                if (enterprise == null) {
                    continue;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("enterpriseName", enterprise.getName());
                map.put("countTotal", source.getSubmit_Success_Total());
                list.add(map);
                if(list.size()>4) {
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            return new LayUiObjectMapper().asSuccessString(list);
        }
    }
}

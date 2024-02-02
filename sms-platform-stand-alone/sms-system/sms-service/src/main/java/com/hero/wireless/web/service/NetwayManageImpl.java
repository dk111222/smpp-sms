package com.hero.wireless.web.service;

import com.drondea.wireless.config.Constant;
import com.drondea.wireless.util.ServiceException;
import com.drondea.wireless.util.SuperLogger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hero.wireless.enums.*;
import com.hero.wireless.http.IHttp;
import com.hero.wireless.json.JsonUtil;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.dao.business.IChannelDAO;
import com.hero.wireless.web.dao.business.IChannelDiversionDAO;
import com.hero.wireless.web.dao.business.IChannelFeeDAO;
import com.hero.wireless.web.dao.business.IProductChannelsDAO;
import com.hero.wireless.web.dao.business.IProductDAO;
import com.hero.wireless.web.dao.business.ext.IChannelExtDAO;
import com.hero.wireless.web.entity.base.Pagination;
import com.hero.wireless.web.entity.business.*;
import com.hero.wireless.web.entity.business.ext.ExportFileExt;
import com.hero.wireless.web.entity.send.Submit;
import com.hero.wireless.web.service.base.BaseService;
import com.hero.wireless.web.util.BeanUtil;
import com.hero.wireless.web.util.ChannelUtil;
import com.hero.wireless.web.util.ChannelUtil.OtherParameter;
import com.hero.wireless.web.util.CodeUtil;
import com.hero.wireless.web.util.QueueUtil;
import com.hero.wireless.web.util.SMSUtil;
import com.hero.wireless.web.util.SMSUtil.DeaultEnterpriseUser;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("netwayManage")
public class NetwayManageImpl extends BaseService implements INetwayManage {

	private static final String SENDER_LOCAL_IP = "127.0.0.1";
	@Resource(name = "IChannelDAO")
	private IChannelDAO<Channel> channelDAO;
	@Resource(name = "channelExtDAO")
	private IChannelExtDAO channelExtDAO;
	@Autowired
	private IChannelFeeDAO<ChannelFee> channelFeeDAO;
	@Autowired
	private IProductChannelsDAO<ProductChannels> productChannelsDAO;
	@Autowired
	private IProductDAO<Product> productDAO;
	@Autowired
	protected IBusinessManage businessManage;
	@Resource(name = "sendManage")
	private ISendManage sendManage;
	@Resource
	private IChannelDiversionDAO channelDiversionDAO;

	@Override
	public List<Channel> queryChannelList(Channel channel) {
		ChannelExample example = new ChannelExample();
		ChannelExample.Criteria criteria = example.createCriteria();
		if (null != channel.getId()) {
			criteria.andIdEqualTo(channel.getId());
		}
		if (StringUtils.isNotEmpty(channel.getName())) {
			criteria.andNameLike("%" + channel.getName() + "%");
		}
		if (StringUtils.isNotEmpty(channel.getNo())) {
			criteria.andNoEqualTo(channel.getNo());
		}
		if (StringUtils.isNotEmpty(channel.getStatus_Code())) {
			criteria.andStatus_CodeEqualTo(channel.getStatus_Code());
		}
		if (StringUtils.isNotEmpty(channel.getProtocol_Type_Code())) {
			criteria.andProtocol_Type_CodeEqualTo(channel.getProtocol_Type_Code());
		}
		if (StringUtils.isNotEmpty(channel.getSession_Status())) {
			criteria.andSession_StatusEqualTo(channel.getSession_Status());
		}
		if (StringUtils.isNotEmpty(channel.getIp())) {
			criteria.andIpEqualTo(channel.getIp());
		}
		if (null != channel.getPort()) {
			criteria.andPortEqualTo(channel.getPort());
		}
		if (StringUtils.isNotEmpty(channel.getAccount())) {
			criteria.andAccountEqualTo(channel.getAccount());
		}
		if (StringUtils.isNotEmpty(channel.getPassword())) {
			criteria.andPasswordEqualTo(channel.getPassword());
		}
		if (StringUtils.isNotEmpty(channel.getTrade_Type_Code())) {
			criteria.andTrade_Type_CodeEqualTo(channel.getTrade_Type_Code());
		}
		if (StringUtils.isNotEmpty(channel.getArea_Code())) {
			criteria.andArea_CodeEqualTo(channel.getArea_Code());
		}
		if (StringUtils.isNotEmpty(channel.getVersion())) {
			criteria.andVersionEqualTo(channel.getVersion());
		}
		if (null != channel.getMax_Concurrent_Total()) {
			criteria.andMax_Concurrent_TotalEqualTo(channel.getMax_Concurrent_Total());
		}
		if (null != channel.getSubmit_Speed()) {
			criteria.andSubmit_SpeedEqualTo(channel.getSubmit_Speed());
		}
		if (StringUtils.isNotEmpty(channel.getSp_Number())) {
			criteria.andSp_NumberEqualTo(channel.getSp_Number());
		}
		if (StringUtils.isNotEmpty(channel.getSignature_Direction_Code())) {
			criteria.andSignature_Direction_CodeEqualTo(channel.getSignature_Direction_Code());
		}
		if (StringUtils.isNotEmpty(channel.getSignature_Position())) {
			criteria.andSignature_PositionEqualTo(channel.getSignature_Position());
		}
		if (StringUtils.isNotEmpty(channel.getOther_Parameter())) {
			criteria.andOther_ParameterEqualTo(channel.getOther_Parameter());
		}
		example.setPagination(channel.getPagination());
		return this.channelDAO.selectByExamplePage(example);
	}

	@Override
	public Channel addChannel(Channel data) {
		data.setNo(CodeUtil.buildProtocolNo(data.getProtocol_Type_Code()));
        data.setGroup_Code(initChannelGroupCode(data));
		data.setStatus_Code(ChannelStatus.STOP.toString());
		data.setSender_Local_IP(SENDER_LOCAL_IP);
		data.setCreate_Date(new Date());
		List<? extends Code> codeListBySortCode = DatabaseCache
				.getCodeListBySortCode(data.getProtocol_Type_Code() + "_parameter");
		Map<String, OtherParameter> paramMap = codeListBySortCode.stream()
				.collect(Collectors.toMap(Code::getCode, (code) -> {
					return new OtherParameter(code);
				}));
		data.setOther_Parameter(JsonUtil.writeValueAsString(paramMap));
		this.channelDAO.insert(data);
		return data;
	}

    /**
     * 初始化通道编码
     * @param data
     * @return
     */
    private String initChannelGroupCode(Channel data) {
        //如果有值，值为页面提交前根据账号密码校验得来。
        if(StringUtils.isNotEmpty(data.getGroup_Code())){
            return data.getGroup_Code();
        }
        //查询 max(Group_Code)
        Integer max = channelExtDAO.selectMaxGroupCode();
        max = ObjectUtils.defaultIfNull(max,1000)+1;
        return max.toString();
    }

    @Override
	public Channel editChannel(Channel data) {
		this.channelDAO.updateByPrimaryKeySelective(data);
		return data;
	}
	
	/**
	 * 2019.12.07 修正原始参数从code中获取
	 * 
	 *
	 */
	@Override
	public Channel updateChannelByPrimaryKey(Map<String, String> leastOtherParameterMap) {
		Channel updateRecord = new Channel();
		updateRecord.setId(Integer.valueOf(leastOtherParameterMap.get("id")));
		Channel channel = DatabaseCache.getChannelByNo(leastOtherParameterMap.get("no"));
		Map<String, OtherParameter> parameterMap = DatabaseCache
				.getCodeListBySortCode(channel.getProtocol_Type_Code() + "_parameter").stream()
				.collect(Collectors.toMap(Code::getCode, (code) -> {
					return new OtherParameter(code);
				}));

		// 这样写会多存储id和no，但可以兼容code中新增的参数和直接在jsp中新增加的参数
		leastOtherParameterMap.forEach((k, v) -> {
			OtherParameter otherParameter = parameterMap.getOrDefault(k, new OtherParameter(k, v));
			otherParameter.setValue(leastOtherParameterMap.get(k));
			parameterMap.put(k, otherParameter);
		});
		updateRecord.setOther_Parameter(JsonUtil.writeValueAsString(parameterMap).replaceAll("\\\\", ""));
		channelDAO.updateByPrimaryKeySelective(updateRecord);
		return updateRecord;
	}

	@Override
	public String channelBalance(String no) {
		Channel channel = DatabaseCache.getChannelByNo(no);
		String result = "0";
		Map<String, OtherParameter> mapParameter = ChannelUtil.getParameter(channel);
		String fullClassImpl = ChannelUtil.getParameterValue(mapParameter, "full_class_impl", null);
		if (StringUtils.isEmpty(fullClassImpl)) {
			result = "未配置查询接口";
			return result;
		}
		try {
			if (StringUtils.isEmpty(ChannelUtil.getParameterValue(mapParameter, "balance_url", null))) {
				result = "未配置查询接口";
				return result;
			}
			IHttp balance = (IHttp) Class.forName(fullClassImpl).newInstance();
			result = balance.balance(channel);
		} catch (Exception e) {
			SuperLogger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public void editChannelStatus(List<Integer> ids, String status) {
		ChannelExample example = new ChannelExample();
		example.createCriteria().andIdIn(ids);
		Channel info = new Channel();
		info.setStatus_Code(status);
		this.channelDAO.updateByExampleSelective(info, example);
	}

	@Override
	public List<ChannelFee> queryChannelFeeList(ChannelFee channelFee) {
		ChannelFeeExample example = new ChannelFeeExample();
		ChannelFeeExample.Criteria criteria = example.createCriteria();
		if (null != channelFee.getId()) {
			criteria.andIdEqualTo(channelFee.getId());
		}
		if (StringUtils.isNotBlank(channelFee.getChannel_No())) {
			criteria.andChannel_NoEqualTo(channelFee.getChannel_No());
		}
		if (StringUtils.isNotBlank(channelFee.getOperator())) {
			criteria.andOperatorEqualTo(channelFee.getOperator());
		}
		if (StringUtils.isNotBlank(channelFee.getTrade_Type_Code())) {
			criteria.andTrade_Type_CodeEqualTo(channelFee.getTrade_Type_Code());
		}
		if (StringUtils.isNotBlank(channelFee.getMessage_Type_Code())) {
			criteria.andMessage_Type_CodeEqualTo(channelFee.getMessage_Type_Code());
		}
		if (StringUtils.isNotBlank(channelFee.getCountry_Number())) {
			criteria.andCountry_NumberEqualTo(channelFee.getCountry_Number());
		}
		example.setPagination(channelFee.getPagination());
		return channelFeeDAO.selectByExamplePage(example);
	}
	
	@Override
	public void addChannelFee(ChannelFee channelFee) {
        checkChannelFee(channelFee);
		channelFee.setCreate_Date(new Date());
		this.channelFeeDAO.insert(channelFee);
	}

	//校验资费是否存在
    private void checkChannelFee(ChannelFee channelFee) {
        ChannelFeeExample example = new ChannelFeeExample();
        ChannelFeeExample.Criteria criteria = example.createCriteria();
        criteria.andChannel_NoEqualTo(channelFee.getChannel_No());//通道编号
        criteria.andCountry_NumberEqualTo(channelFee.getCountry_Number());//国家
        criteria.andOperatorEqualTo(channelFee.getOperator());//运营商
        criteria.andTrade_Type_CodeEqualTo(channelFee.getTrade_Type_Code());//行业
        criteria.andMessage_Type_CodeEqualTo(channelFee.getMessage_Type_Code());//消息类型
        List<ChannelFee> channelFeeList = channelFeeDAO.selectByExamplePage(example);
        if(channelFeeList.size() > 0){
            if (channelFee.getId() == null){//新增
                throw new ServiceException("此资费已存在，请勿重复创建！");
            }
            if (!channelFeeList.get(0).getId().equals(channelFee.getId())){//修改
                throw new ServiceException("此资费已存在，请勿重复创建！");
            }
        }
    }

    @Override
	public void editChannelFee(ChannelFee channelFee) {
        checkChannelFee(channelFee);
		this.channelFeeDAO.updateByPrimaryKeySelective(channelFee);
	}

	@Override
	public List<ProductChannels> queryProductChannelsList(ProductChannels productChannels) {
		ProductChannelsExample example = new ProductChannelsExample();
		ProductChannelsExample.Criteria criteria = example.createCriteria();
		if (productChannels.getId() != null) {
			criteria.andIdEqualTo(productChannels.getId());
		}
		if (StringUtils.isNotBlank(productChannels.getChannel_No())) {
			criteria.andChannel_NoEqualTo(productChannels.getChannel_No());
		}
		if (StringUtils.isNotBlank(productChannels.getProduct_No())) {
			criteria.andProduct_NoEqualTo(productChannels.getProduct_No());
		}
		if (StringUtils.isNotBlank(productChannels.getOperator())) {
			criteria.andOperatorEqualTo(productChannels.getOperator());
		}
		if (StringUtils.isNotBlank(productChannels.getMessage_Type_Code())) {
			criteria.andMessage_Type_CodeEqualTo(productChannels.getMessage_Type_Code());
		}
		example.setPagination(productChannels.getPagination());
		return productChannelsDAO.selectByExamplePage(example);

	}

	@Override
	public List<Product> queryProductList(Product product) {
		ProductExample example = new ProductExample();
		ProductExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(product.getNo())) {
			criteria.andNoEqualTo(product.getNo());
		}
		if (StringUtils.isNotBlank(product.getStatus_Code())) {
			criteria.andStatus_CodeEqualTo(product.getStatus_Code());
		}
		example.setPagination(product.getPagination());
		return productDAO.selectByExamplePage(example);
	}

	@Override
	public void delChannelFee(List<Integer> ckIds) {
		if(ckIds.isEmpty()) {
			throw new ServiceException("ckids is null");
		}
		ckIds.forEach(id ->{
			this.channelFeeDAO.deleteByPrimaryKey(id);
		});
	}

	@Override
	public void try2Try(String channelNo, String phoneNos, String content
            , String subCode, String countryCode) {
		String[] phoneArray = phoneNos.split(Constant.MUTL_MOBILE_SPLIT);
		DeaultEnterpriseUser user = new DeaultEnterpriseUser();
		Channel channel = DatabaseCache.getChannelByNo(channelNo);
        String signature = SMSUtil.getSignature(content);
		Arrays.asList(phoneArray).stream().forEach(phone -> {
			Submit submit = new Submit();
			submit.setChannel_No(channelNo);
			submit.setGroup_Code(channel.getGroup_Code());
            submit.setCountry_Code(countryCode);
			submit.setEnterprise_No(user.getEnterprise_No());
			submit.setAgent_No(SMSUtil.DEFAULT_NO);
			submit.setBusiness_User_Id(0);
			submit.setEnterprise_User_Id(user.getId());
			submit.setIs_LMS(SMSUtil.isLms(content));
			submit.setMsg_Batch_No(CodeUtil.buildMsgNo());
			submit.setMessage_Type_Code(MessageType.SMS.toString());
			submit.setContent(content);
			submit.setContent_Length(content.length());
			submit.setPhone_No(phone);
			submit.setSub_Code(subCode);
			submit.setProtocol_Type_Code(ProtocolType.SYSTEM.toString());
			submit.setData_Status_Code(DataStatus.NORMAL.toString());
            submit.setInput_Log_Date(new Date());
            submit.setSignature(signature);
			submit.setCreate_Date(new Date());
			submit = SMSUtil.buildSubmitAreaAndOperator(submit);
			int priorityLevel = Priority.valueOf(user.getPriority_Level()).value();
			submit.setPriority_Level(priorityLevel);

			//WEB提交的放到submitAwait表里面
			QueueUtil.notifySubmit(submit, "");
		});
	}

	@Override
	public List<ChannelDiversion> queryChannelDiversionList(ChannelDiversion channelDiversion) {
		ChannelDiversionExample example = assemblyChannelDiversionListConditon(channelDiversion);
		example.setPagination(channelDiversion.getPagination());
		example.setOrderByClause(" id desc ");
		return channelDiversionDAO.selectByExamplePage(example);
	}


	protected ChannelDiversionExample assemblyChannelDiversionListConditon(
			ChannelDiversion condition) {
		ChannelDiversionExample example = new ChannelDiversionExample();
		ChannelDiversionExample.Criteria criteria = example.createCriteria();
		if (null != condition.getId()) {
			criteria.andIdEqualTo(condition.getId());
		}
		if (null != condition.getChannel_No()) {
			criteria.andChannel_NoEqualTo(condition.getChannel_No());
		}
		if (StringUtils.isNotBlank(condition.getStatus_Code())) {
			criteria.andStatus_CodeEqualTo(condition.getStatus_Code());
		}
		if (StringUtils.isNotBlank(condition.getStrategy_Type_Code())) {
			criteria.andStrategy_Type_CodeEqualTo(condition.getStrategy_Type_Code());
		}
		if (StringUtils.isNotBlank(condition.getStrategy_Rule()) && condition.getStrategy_Rule().contains("%")) {
			criteria.andStrategy_RuleLike(condition.getStrategy_Rule());
		} else if (StringUtils.isNotBlank(condition.getStrategy_Rule())) {
			criteria.andStrategy_RuleEqualTo(condition.getStrategy_Rule());
		}
		if (StringUtils.isNotEmpty(condition.getDescription())) {
			criteria.andDescriptionEqualTo(condition.getDescription());
		}
		if (StringUtils.isNotEmpty(condition.getRemark())) {
			criteria.andRemarkEqualTo(condition.getRemark());
		}
		return example;
	}

	@Override
	public void addChannelDiversion(ChannelDiversion diversion) {
		//添加的时候是开启状态的时候广播，往缓存中添加
		if(ChannelStatus.START.toString().equalsIgnoreCase(diversion.getStatus_Code())){
			//创建广播通知其他服务更新
			//MQUtil.notifyUpdateSystemCache(new BroadcastMessage(BroadcastingMessageType.CHANNEL_DIVERSION_UPDATE, BroadcastingOperateType.ADD, diversion));
		}
		diversion.setUpdate_Date(new Date());
		diversion.setCreate_Date(new Date());
		channelDiversionDAO.insert(diversion);
	}

	@Override
	public ChannelDiversion queryChannelDiversionById(Integer id) {
		if (id == null || id == 0) {
			throw new ServiceException("请选择一条数据");
		}
		return channelDiversionDAO.selectByPrimaryKey(id);
	}

	@Override
	public void updateChannelDiversionStatus(List<Integer> ckIds, String statusCode, String type) {
		ChannelDiversionExample example = new ChannelDiversionExample();
		example.createCriteria().andIdIn(ckIds);
		List<ChannelDiversion> list = channelDiversionDAO.selectByExample(example);
		if(ChannelStatus.STOP.toString().equalsIgnoreCase(statusCode) && !ProductChannelDiversionType.PHONE_NO_POLL.toString().equalsIgnoreCase(type)){
			list.forEach(entity -> {
				//发广播通知其他服务更新内容
				//MQUtil.notifyUpdateSystemCache(new BroadcastMessage(BroadcastingMessageType.CHANNEL_DIVERSION_UPDATE, BroadcastingOperateType.DELETE, entity));
			});
		} else if(ChannelStatus.START.toString().equalsIgnoreCase(statusCode) && !ProductChannelDiversionType.PHONE_NO_POLL.toString().equalsIgnoreCase(type)){
			list.forEach(entity -> {
				//发广播通知其他服务更新内容
				//MQUtil.notifyUpdateSystemCache(new BroadcastMessage(BroadcastingMessageType.CHANNEL_DIVERSION_UPDATE, BroadcastingOperateType.ADD, entity));
			});
		}
		ChannelDiversion diversion = new ChannelDiversion();
		diversion.setStatus_Code(statusCode);
		diversion.setUpdate_Date(new Date());
		channelDiversionDAO.updateByExampleSelective(diversion, example);
	}

	@Override
	public void delChannelDiversion(List<Integer> ckIds, String type) {
		ChannelDiversionExample example = new ChannelDiversionExample();
		example.createCriteria().andIdIn(ckIds);
		List<ChannelDiversion> list = channelDiversionDAO.selectByExample(example);
		if(!ProductChannelDiversionType.PHONE_NO_POLL.toString().equalsIgnoreCase(type)){
			list.forEach(entity -> {
				//发广播通知其他服务更新内容
				//MQUtil.notifyUpdateSystemCache(new BroadcastMessage(BroadcastingMessageType.CHANNEL_DIVERSION_UPDATE, BroadcastingOperateType.DELETE, entity));
			});
		}
		channelDiversionDAO.deleteByExample(example);
	}

	@Override
	public void importChannelDiversion(MultipartFile importFile, ChannelDiversion channelSignature, String strategyType) {
		readFile(importFile, channelSignature, new IImportFile() {
			@Override
			public void doBusiness(InputStreamReader inputStreamReader, BufferedReader reader) throws Exception {
				String textLine = "";
				List<ChannelDiversion> insertList = new ArrayList<>();
				while ((textLine = reader.readLine()) != null) {
					if (StringUtils.isBlank(textLine)) {
						continue;
					}
					if (ProductChannelDiversionType.SIGNATURE.toString().equals(strategyType)) {
						String[] lineArray = textLine.split(",");
						String key = lineArray[0].trim();
						if (key.startsWith(BeanUtil.UFEFF)) {
							key = key.replaceAll(BeanUtil.UFEFF, "");
						}
						String subCode = lineArray.length > 1 ? lineArray[1].trim() : "";
						if (ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(subCode)) {
							throw new ServiceException("导入失败，请勿上传空值");
						}
						Map<String, String> map = new TreeMap<>();
						map.put("signature", key);
						map.put("subCode", subCode);
						textLine = JsonUtil.STANDARD.writeValueAsString(map);
					}
					channelSignature.setStrategy_Rule(textLine);
					//导入数据的时候，开启状态的才发广播，往缓存中加载
					if(ChannelStatus.START.toString().equalsIgnoreCase(channelSignature.getStatus_Code())){
						//发广播通知其他服务更新内容，在此广播，在下面添加广播的话，需要添加两处
						//MQUtil.notifyUpdateSystemCache(new BroadcastMessage(BroadcastingMessageType.CHANNEL_DIVERSION_UPDATE, BroadcastingOperateType.ADD, channelSignature));
					}
					channelSignature.setUpdate_Date(new Date());
					channelSignature.setCreate_Date(new Date());
					ChannelDiversion insertData = new ChannelDiversion();
					BeanUtils.copyProperties(channelSignature, insertData);
					insertList = batchInsert(insertList, insertData);
				}
				if (insertList.size() > 0) {
					channelDiversionDAO.insertList(insertList);
				}
			}
		});
	}

	@Override
	public void exportChannelDiversion(String baseExportBasePath, String channelNo, ExportFileExt exportFile, String strategyType) {
		String redisKey = newThreadBefore(Constant.THREAD_TOTAL_EXPORT);// 校验
		new Thread(() -> {
			int page = 1;
			int pageSize = 5000;
			while (true) {
				ChannelDiversionExample example = new ChannelDiversionExample();
				example.createCriteria().andChannel_NoEqualTo(channelNo).andStrategy_Type_CodeEqualTo(strategyType);
				Pagination pagination = new Pagination(page, pageSize);
				example.setPagination(pagination);
				List<ChannelDiversion> list = channelDiversionDAO.selectByExample(example);
				if (ObjectUtils.isEmpty(list)) {
					break;
				}
				exportFile.setBatch_Id(UUID.randomUUID().toString());
				exportFile.setCreate_Date(new Date());
				exportFile.setId(null);
				exportChannelSignatureFile(baseExportBasePath, list, exportFile, strategyType);
				page = page + 1;
			}
			newThreadAfter(redisKey);
		}).start();
	}

	@Override
	public List<ChannelDiversion> queryChannelDiversionList(int page, int pageSize, ChannelDiversion channelDiversion) {
		ChannelDiversionExample example = assemblyChannelDiversionListConditon(channelDiversion);
		Pagination pagination = new Pagination(page, pageSize);
		example.setPagination(pagination);
		return channelDiversionDAO.selectByExample(example);
	}

	@Override
	public void initChannelDiversion() {
		//直接发MQ让其他服务更新本地缓存
		//MQUtil.notifyUpdateSystemCache(new BroadcastMessage(BroadcastingMessageType.CHANNEL_DIVERSION_UPDATE, BroadcastingOperateType.REFRESH_ALL, new ChannelDiversion()));
	}

	protected void exportChannelSignatureFile(String baseExportBasePath, List<ChannelDiversion> list,
											  ExportFile exportFile, String strategyType) {
		try {
			SuperLogger.debug(baseExportBasePath);
			String path = baseExportBasePath + "/admin/export";
			exportFile.setFIle_Name(exportFile.getBatch_Id() + ".txt");
			exportFile.setFile_Url(path + "/" + exportFile.getFIle_Name());
			// 生成的文件路径
			File file = new File(exportFile.getFile_Url());
			if (!file.exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			BufferedWriter bw = new BufferedWriter(fw);
			StringBuffer sb = new StringBuffer();
			for (ChannelDiversion entity : list) {
				if (ProductChannelDiversionType.SIGNATURE.toString().equals(strategyType)) {
					Map<String, String> map = JsonUtil.readValue(entity.getStrategy_Rule(),
							new TypeReference<Map<String, String>>() {
							});
					sb.append(map.get("signature")).append(",");
					sb.append(map.get("subCode"));
				} else {
					sb.append(entity.getStrategy_Rule());
				}
				sb.append("\r\n");
			}
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			fw.close();
			this.exportFileDAO.insert(exportFile);
		} catch (Exception e) {
			SuperLogger.error(e.getMessage(), e);
		}
	}

	protected List<ChannelDiversion> batchInsert(List<ChannelDiversion> insertList, ChannelDiversion insertData) {
		insertList.add(insertData);
		if (insertList.size() == 200) {
			channelDiversionDAO.insertList(insertList);
			insertList = new ArrayList<>();
		}
		return insertList;
	}

	private void readFile(MultipartFile phoneNoFile, ChannelDiversion channelSignature,
						  IImportFile importFile) {
		BufferedReader reader = null;
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(phoneNoFile.getInputStream());
			reader = new BufferedReader(inputStreamReader);
			importFile.doBusiness(inputStreamReader, reader);
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				reader.close();
				inputStreamReader.close();
			} catch (IOException e) {
				SuperLogger.error(e.getMessage(), e);
			}
		}
	}
}

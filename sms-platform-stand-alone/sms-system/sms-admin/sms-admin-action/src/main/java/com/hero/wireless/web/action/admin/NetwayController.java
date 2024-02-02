package com.hero.wireless.web.action.admin;

import com.drondea.wireless.util.ServiceException;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.enums.ChannelStatus;
import com.hero.wireless.enums.ProductChannelDiversionType;
import com.hero.wireless.json.LayUiJsonObjectFmt;
import com.hero.wireless.json.LayUiObjectMapper;
import com.hero.wireless.json.LayuiResultUtil;
import com.hero.wireless.json.SmsUIObjectMapper;
import com.hero.wireless.web.action.BaseAdminController;
import com.hero.wireless.web.action.entity.BaseParamEntity;
import com.hero.wireless.web.action.interceptor.AvoidRepeatableCommitAnnotation;
import com.hero.wireless.web.action.interceptor.OperateAnnotation;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.business.Channel;
import com.hero.wireless.web.entity.business.ChannelDiversion;
import com.hero.wireless.web.entity.business.ChannelFee;
import com.hero.wireless.web.exception.BaseException;
import com.hero.wireless.web.service.INetwayManage;
import com.hero.wireless.web.util.ChannelUtil;
import com.hero.wireless.web.util.ChannelUtil.OtherParameter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class NetwayController extends BaseAdminController {

	@Resource(name = "netwayManage")
	private INetwayManage netwayManage;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setAutoGrowNestedPaths(true);
        dataBinder.setAutoGrowCollectionLimit(1024);
		dataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * admin-->通道列表
	 */
	@RequestMapping("netway_channelList")
	@ResponseBody
	public String channelList(Channel channel) {
		List<Channel> channelList = netwayManage.queryChannelList(channel);
		return new LayUiObjectMapper().asSuccessString(channelList, channel.getPagination().getTotalCount());
	}

	@RequestMapping("netway_channelSave")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道管理", option = "添加通道")
    @AvoidRepeatableCommitAnnotation(systemModuleName = ADMIN_PLATFORM+"netway_channelSave")
	public LayUiJsonObjectFmt channelSave(Channel channel) {
		try {
			this.netwayManage.addChannel(channel);
		} catch (Exception e) {
			SuperLogger.error(e.getMessage(), e);
			return LayuiResultUtil.error(e);
		}
		return LayuiResultUtil.success();
	}

	/**
	 * 修改前置
	 *
	 * @return
	 */
	@RequestMapping("netway_preEditChannel")
	public String preEditCmpp(@RequestParam(name = "ckNos") List<String> ckNos) {
		if (ckNos == null || ckNos.size() == 0) {
			return "/netway/channel_edit";
		}
		Channel channel = DatabaseCache.getChannelByNo(ckNos.get(0));
		if (channel != null) {
			request.setAttribute("channel", channel);
		}
		return "/netway/channel_edit";
	}

	/**
	 * 修改保存
	 *
	 * @return
	 */
	@RequestMapping("netway_editChannel")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道管理", option = "修改通道")
	public LayUiJsonObjectFmt editChannel(Channel channel) {
		netwayManage.editChannel(channel);
		return LayuiResultUtil.success();
	}

	/**
	 * 修改参数配置前置
	 *
	 * @return
	 */
	@RequestMapping("netway_preEditChannelParameters")
	public String preEditChannelParameters(@RequestParam(name = "ckNos") List<String> ckNos) {
		Channel channel = DatabaseCache.getChannelByNo(ckNos.get(0));
		Map<String, OtherParameter> parameterMap = ChannelUtil.getParameter(channel);
		if (channel != null) {
			request.setAttribute("channel", channel);
			request.setAttribute("reMap", parameterMap);
		}
		return "/netway/" + channel.getProtocol_Type_Code() + "_parameters_config";
	}

	/**
	 * 修改参数配置
	 *
	 * @return
	 */
	@RequestMapping("netway_editChannelParameters")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道管理", option = "修改参数配置")
	public LayUiJsonObjectFmt editChannelParameters(@RequestParam Map<String, String> parMap) {
		netwayManage.updateChannelByPrimaryKey(parMap);
		return LayuiResultUtil.success();
	}

	@RequestMapping("netway_channelBalance")
	public String channelBalance(String no) {
		try {
			String result = this.netwayManage.channelBalance(no);
			try {
				request.setAttribute("res", result);
			} catch (Exception e) {
				request.setAttribute("res", e.getMessage());
			}
		} catch (Exception e) {
			SuperLogger.error(e.getMessage(), e);
		}
		return "/netway/http_balance";
	}

	@RequestMapping("netway_editChannelStatus")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道管理", option = "修改通道状态")
	public LayUiJsonObjectFmt editCmppStatus(@RequestParam(name = "ckIds") List<Integer> ckIds, String status) {
		netwayManage.editChannelStatus(ckIds, status);
		return LayuiResultUtil.success();
	}

	@RequestMapping("netway_try2Try")
	@ResponseBody
	public LayUiJsonObjectFmt try2Try(String channelNo, String phoneNos
            , String content, String subCode, String countryCode) {
		this.netwayManage.try2Try(channelNo, phoneNos, content, subCode,countryCode);
		return LayuiResultUtil.success();
	}

	@RequestMapping("netway_channelFeeIndex")
	public String channelFeeIndex(String limitCode, String ckNos) {
		request.setAttribute("limitCode",limitCode);
		request.setAttribute("channelNo",ckNos);
        return "/netway/channel_fee_list";
	}

	/**
	 * 资费列表
	 *
	 */
	@RequestMapping("netway_channelFeeList")
	@ResponseBody
	public String channelFeeList(ChannelFee channelFee) {
		List<ChannelFee> channelFeeList = this.netwayManage.queryChannelFeeList(channelFee);
		return new LayUiObjectMapper().asSuccessString(channelFeeList, channelFee.getPagination().getTotalCount());
	}


	/**
	 * 保存资费
	 *
	 */
	@RequestMapping("netway_addChannelFee")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道管理", option = "添加资费")
	@AvoidRepeatableCommitAnnotation(systemModuleName = ADMIN_PLATFORM+"netway_addChannelFee")
	public LayUiJsonObjectFmt addChannelFee(ChannelFee channelFee) {
	    try {
            this.netwayManage.addChannelFee(channelFee);
            return LayuiResultUtil.success();
        }catch (ServiceException e){
            return LayuiResultUtil.fail(e.getMessage());
        }catch (Exception e){
	        SuperLogger.error(e.getMessage(),e);
            return LayuiResultUtil.error(e);
        }
	}

	/**
	 * 资费修改前置
	 *
	 */
	@RequestMapping("netway_preEditChannelFee")
	public ModelAndView preEditChannelFee(BaseParamEntity entity) {
		ModelAndView mv = new ModelAndView();
		ChannelFee channelFee = new ChannelFee();
		channelFee.setId(entity.getCkIds().get(0));
		channelFee = this.netwayManage.queryChannelFeeList(channelFee).get(0);
        mv.setViewName("/netway/channel_fee_edit");
		mv.addObject("channelFee", channelFee);
		return mv;
	}

	/**
	 * 资费修改
	 *
	 */
	@RequestMapping("netway_editChannelFee")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道管理", option = "资费修改")
	@AvoidRepeatableCommitAnnotation(systemModuleName = ADMIN_PLATFORM+"netway_editChannelFee")
	public LayUiJsonObjectFmt editChannelFee(ChannelFee channelFee) {
        try {
            this.netwayManage.editChannelFee(channelFee);
            return LayuiResultUtil.success();
        }catch (ServiceException e){
            return LayuiResultUtil.fail(e.getMessage());
        }catch (Exception e){
            SuperLogger.error(e.getMessage(),e);
            return LayuiResultUtil.error(e);
        }
	}
	/**
	 * 删除资费
	 *
	 */
	@RequestMapping("netway_delChannelFee")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道管理", option = "删除资费")
	public LayUiJsonObjectFmt delChannelFee(@RequestParam(value ="ckIds")List<Integer> ckIds) {
		this.netwayManage.delChannelFee(ckIds);
		return LayuiResultUtil.success();
	}

    /**
     * 通道产品=》通道管理=》区域配置前置
     */
    @RequestMapping("netway_preEditChannelAreaCode")
    public String preEditChannelAreaCode(BaseParamEntity entity) {
        Channel channel = new Channel();
        channel.setId(entity.getCkIds().get(0));
        request.setAttribute("channel",netwayManage.queryChannelList(channel).get(0));
        request.setAttribute("locationCode", DatabaseCache.getCodeListBySortCode("location"));
        return "/netway/channel_area_limit";
    }


    /**
     * 通道产品=》通道管理=》区域配置保存
     */
    @RequestMapping("netway_editChannelAreaCode")
    @ResponseBody
    public LayUiJsonObjectFmt editChannelAreaCode(Channel channel) {
        try {
            netwayManage.editChannel(channel);
            return LayuiResultUtil.success();
        }catch (Exception e){
            SuperLogger.error(e.getMessage(),e);
            return LayuiResultUtil.fail("保存失败");
        }
    }

	/**
	 * 通道提交速度
	 * @return
	 */
	@RequestMapping("netway_preSubmitSpeed")
	public String preNetwaySubmitSpeed(BaseParamEntity entity) {
		List<Integer> ckIds = entity.getCkIds();
		request.setAttribute("ckIds",ckIds);
		return "/netway/channel_submit_speed";
	}

	/**
	 * 通道签名导流前置
	 */
	@RequestMapping("preChannelSignature")
	public String preChannelSignature(String limitCode, String ckNos) {
		request.setAttribute("limitCode", limitCode);
		request.setAttribute("channelNo", ckNos);
		return "/netway/diversion/include_signature_list";
	}

	/**
	 * 通道签名导流列表
	 */
	@RequestMapping("channelSignatureList")
	@ResponseBody
	public String channelSignatureList(ChannelDiversion channelSignature) {
		channelSignature.setStrategy_Type_Code(ProductChannelDiversionType.SIGNATURE.toString());
		if (StringUtils.isNotEmpty(channelSignature.getStrategy_Rule())) {
			channelSignature.setStrategy_Rule("%" + channelSignature.getStrategy_Rule() + "%");
		}
		List<ChannelDiversion> list = this.netwayManage.queryChannelDiversionList(channelSignature);
		return new SmsUIObjectMapper().asSuccessString(list, channelSignature.getPagination());
	}

	/**
	 * 通道导流策略签名保存
	 */
	@RequestMapping("addChannelSignature")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道管理", option = "通道签名导流新增")
	@AvoidRepeatableCommitAnnotation(systemModuleName = ADMIN_PLATFORM + "channel:addChannelSignature")
	public LayUiJsonObjectFmt addChannelSignature(ChannelDiversion channelSignature) {
		channelSignature.setStrategy_Type_Code(ProductChannelDiversionType.SIGNATURE.toString());
		this.netwayManage.addChannelDiversion(channelSignature);
		return LayuiResultUtil.success();
	}

	/**
	 * 批量启动通道签名
	 *
	 * @param entity
	 * @return
	 */
	@RequestMapping("startChannelDiversion")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道签名管理", option = "通道签名启用")
	public LayUiJsonObjectFmt startChannelDiversion(BaseParamEntity entity) {
		if (ObjectUtils.isEmpty(entity.getCkIds())) {
			throw new ServiceException("至少选择一条数据");
		}
		netwayManage.updateChannelDiversionStatus(entity.getCkIds(), ChannelStatus.START.toString(), null);
		return LayuiResultUtil.success();
	}

	/**
	 * 批量停止通道签名
	 *
	 * @param entity
	 * @return
	 */
	@RequestMapping("stopChannelDiversion")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道签名管理", option = "通道签名停用")
	public LayUiJsonObjectFmt stopChannelDiversion(BaseParamEntity entity) {
		if (ObjectUtils.isEmpty(entity.getCkIds())) {
			throw new ServiceException("至少选择一条数据");
		}
		netwayManage.updateChannelDiversionStatus(entity.getCkIds(), ChannelStatus.STOP.toString(), null);
		return LayuiResultUtil.success();
	}

	/**
	 * 通道导流策略删除
	 *
	 * @param entity
	 * @return
	 */
	@RequestMapping("delChannelDiversion")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道签名管理", option = "通道签名删除")
	@AvoidRepeatableCommitAnnotation(systemModuleName = ADMIN_PLATFORM + "channel:delChannelDiversion")
	public LayUiJsonObjectFmt delChannelDiversion(BaseParamEntity entity) {
		if (entity.getCkIds() == null || entity.getCkIds().size() < 1) {
			throw new ServiceException("至少选择一条数据");
		}
		netwayManage.delChannelDiversion(entity.getCkIds(), null);
		return LayuiResultUtil.success();
	}


	//导流策略导入签名
	@RequestMapping("importChannelSignature")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道签名管理", option = "通道签名导入签名")
	@AvoidRepeatableCommitAnnotation(systemModuleName = ADMIN_PLATFORM + "channel:importChannelSignature")
	public LayUiJsonObjectFmt importChannelSignature(@RequestParam(value = "importFile") MultipartFile importFile,
													 ChannelDiversion channelSignature) {
		try {
			String fileTyle = importFile.getOriginalFilename().substring(importFile.getOriginalFilename().lastIndexOf("."));
			if (!fileTyle.equalsIgnoreCase(".txt")) {
				throw new BaseException("文件格式不正确!");
			}
			channelSignature.setName("签名导流");
			channelSignature.setStrategy_Type_Code(ProductChannelDiversionType.SIGNATURE.toString());
			this.netwayManage.importChannelDiversion(importFile, channelSignature, ProductChannelDiversionType.SIGNATURE.toString());
		} catch (ServiceException se) {
			return LayuiResultUtil.fail(se.getMessage());
		}
		return LayuiResultUtil.success();
	}

	//导流策略导出签名
	@RequestMapping("exportChannelSignature")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道签名管理", option = "通道签名导出签名")
	public LayUiJsonObjectFmt exportChannelSignature(String channelNo) {
		try {
			netwayManage.exportChannelDiversion(DatabaseCache.getCodeBySortCodeAndCode("system_env", "export_dir").getValue(), channelNo,
					getAdminDefaultExportFile(), ProductChannelDiversionType.SIGNATURE.toString());
			return new LayUiJsonObjectFmt(LayUiObjectMapper.CODE_SUCCESS, "已提交后台导出任务!");
		} catch (ServiceException e) {
			return LayuiResultUtil.fail(e.getMessage());
		} catch (Exception e) {
			SuperLogger.error(e.getMessage(), e);
			return LayuiResultUtil.error(e);
		}
	}

	/**
	 * 初始化通道导流
	 *
	 * @return
	 */
	@RequestMapping("initChannelDiversion")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道签名管理", option = "初始化通道导流")
	public LayUiJsonObjectFmt initChannelDiversion() {
		netwayManage.initChannelDiversion();
		return LayuiResultUtil.success();
	}

	/**
	 * 通道导流策略包含关键字前置
	 */
	@RequestMapping("preChannelDiversionKeyWord")
	public ModelAndView preChannelDiversionKeyWord(String limitCode, String ckNos) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("limitCode", limitCode);
		mv.addObject("channelNo", ckNos);
		mv.setViewName("/netway/diversion/include_keyword_list");
		return mv;
	}

	/**
	 * 通道导流策略包含关键字列表
	 */
	@RequestMapping("channelDiversionKeyWordList")
	@ResponseBody
	public String channelDiversionKeyWordList(ChannelDiversion diversion) {
		diversion.setStrategy_Type_Code(ProductChannelDiversionType.INCLUDE_KEYWORD.toString());
		if (StringUtils.isEmpty(diversion.getChannel_No())) {
			return null;
		}
		List<ChannelDiversion> list = this.netwayManage.queryChannelDiversionList(diversion);
		return new SmsUIObjectMapper().asSuccessString(list, diversion.getPagination());
	}

	/**
	 * 通道导流策略包含关键字新增保存
	 */
	@RequestMapping("addChannelDiversionKeyWord")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道导流管理", option = "通道导流策略关键字新增")
	@AvoidRepeatableCommitAnnotation(systemModuleName = ADMIN_PLATFORM + "channel:addChannelDiversionKeyWord")
	public LayUiJsonObjectFmt addChannelDiversionKeyWord(ChannelDiversion diversion) {
		diversion.setStrategy_Type_Code(ProductChannelDiversionType.INCLUDE_KEYWORD.toString());
		this.netwayManage.addChannelDiversion(diversion);
		return LayuiResultUtil.success();
	}

	//导流策略导入包含关键字
	@RequestMapping("importChannelKeyword")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道导流管理", option = "通道导流策略导入关键字")
	@AvoidRepeatableCommitAnnotation(systemModuleName = ADMIN_PLATFORM + "channel:importChannelKeyword")
	public LayUiJsonObjectFmt importChannelKeyword(@RequestParam(value = "importFile") MultipartFile importFile,
												   ChannelDiversion channelDiversion) {
		try {
			String fileTyle = importFile.getOriginalFilename().substring(importFile.getOriginalFilename().lastIndexOf("."));
			if (!fileTyle.equalsIgnoreCase(".txt")) {
				throw new BaseException("文件格式不正确!");
			}
			channelDiversion.setName("包含关键字");
			channelDiversion.setStrategy_Type_Code(ProductChannelDiversionType.INCLUDE_KEYWORD.toString());
			this.netwayManage.importChannelDiversion(importFile, channelDiversion, ProductChannelDiversionType.INCLUDE_KEYWORD.toString());
		} catch (ServiceException se) {
			return LayuiResultUtil.fail(se.getMessage());
		}
		return LayuiResultUtil.success();
	}

	//导流策略导出包含关键字
	@RequestMapping("exportChannelKeyword")
	@ResponseBody
	public LayUiJsonObjectFmt exportChannelKeyword(String channelNo) {
		try {
			netwayManage.exportChannelDiversion(DatabaseCache.getCodeBySortCodeAndCode("system_env", "export_dir").getValue(), channelNo,
					getAdminDefaultExportFile(), ProductChannelDiversionType.INCLUDE_KEYWORD.toString());
			return new LayUiJsonObjectFmt(LayUiObjectMapper.CODE_SUCCESS, "已提交后台导出任务!");
		} catch (ServiceException e) {
			return LayuiResultUtil.fail(e.getMessage());
		} catch (Exception e) {
			SuperLogger.error(e.getMessage(), e);
			return LayuiResultUtil.error(e);
		}
	}


	/**
	 * 通道导流策略排除关键字前置
	 */
	@RequestMapping("preChannelDiversionExcludeKeyWord")
	public ModelAndView preChannelDiversionExcludeKeyWord(String limitCode, String ckNos) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("limitCode", limitCode);
		mv.addObject("channelNo", ckNos);
		mv.setViewName("/netway/diversion/exclude_keyword_list");
		return mv;
	}

	/**
	 * 通道导流策略排除关键字列表
	 */
	@RequestMapping("channelDiversionExcludeKeyWordList")
	@ResponseBody
	public String channelDiversionExcludeKeyWordList(ChannelDiversion diversion) {
		diversion.setStrategy_Type_Code(ProductChannelDiversionType.EXCLUDE_KEYWORD.toString());
		if (StringUtils.isEmpty(diversion.getChannel_No())) {
			return null;
		}
		List<ChannelDiversion> list = this.netwayManage.queryChannelDiversionList(diversion);
		return new SmsUIObjectMapper().asSuccessString(list, diversion.getPagination());
	}

	/**
	 * 通道导流策略排除关键字新增保存
	 */
	@RequestMapping("addChannelDiversionExcludeKeyWord")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道导流管理", option = "通道导流策略排除关键字新增")
	@AvoidRepeatableCommitAnnotation(systemModuleName = ADMIN_PLATFORM + "channel:addChannelDiversionExcludeKeyWord")
	public LayUiJsonObjectFmt addChannelDiversionExcludeKeyWord(ChannelDiversion diversion) {
		diversion.setName("排除关键字");
		diversion.setStrategy_Type_Code(ProductChannelDiversionType.EXCLUDE_KEYWORD.toString());
		this.netwayManage.addChannelDiversion(diversion);
		return LayuiResultUtil.success();
	}

	//通道导流策略导入排除关键字
	@RequestMapping("importChannelExcludeKeyword")
	@ResponseBody
	@OperateAnnotation(moduleName = "通道导流管理", option = "通道导流策略导入排除关键字")
	@AvoidRepeatableCommitAnnotation(systemModuleName = ADMIN_PLATFORM + "channel:importChannelExcludeKeyword")
	public LayUiJsonObjectFmt importChannelExcludeKeyword(@RequestParam(value = "importFile") MultipartFile importFile,
														  ChannelDiversion channelDiversion) {
		try {
			String fileTyle = importFile.getOriginalFilename().substring(importFile.getOriginalFilename().lastIndexOf("."));
			if (!fileTyle.equalsIgnoreCase(".txt")) {
				throw new BaseException("文件格式不正确!");
			}
			channelDiversion.setName(ProductChannelDiversionType.EXCLUDE_KEYWORD.getName());
			channelDiversion.setStrategy_Type_Code(ProductChannelDiversionType.EXCLUDE_KEYWORD.toString());
			this.netwayManage.importChannelDiversion(importFile, channelDiversion,
					ProductChannelDiversionType.EXCLUDE_KEYWORD.toString());
		} catch (ServiceException se) {
			return LayuiResultUtil.fail(se.getMessage());
		}
		return LayuiResultUtil.success();
	}

	//通道导流策略导出排除关键字
	@RequestMapping("exportChannelExcludeKeyword")
	@ResponseBody
	public LayUiJsonObjectFmt exportChannelExcludeKeyword(String channelNo) {
		try {
			netwayManage.exportChannelDiversion(DatabaseCache.getCodeBySortCodeAndCode("system_env", "export_dir").getValue(), channelNo,
					getAdminDefaultExportFile(), ProductChannelDiversionType.EXCLUDE_KEYWORD.toString());
			return new LayUiJsonObjectFmt(LayUiObjectMapper.CODE_SUCCESS, "已提交后台导出任务!");
		} catch (ServiceException e) {
			return LayuiResultUtil.fail(e.getMessage());
		} catch (Exception e) {
			SuperLogger.error(e.getMessage(), e);
			return LayuiResultUtil.error(e);
		}
	}
}
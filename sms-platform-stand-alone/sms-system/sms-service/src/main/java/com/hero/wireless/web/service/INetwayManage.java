package com.hero.wireless.web.service;

import com.hero.wireless.web.entity.business.Channel;
import com.hero.wireless.web.entity.business.ChannelDiversion;
import com.hero.wireless.web.entity.business.ChannelFee;
import com.hero.wireless.web.entity.business.Product;
import com.hero.wireless.web.entity.business.ProductChannels;
import com.hero.wireless.web.entity.business.ext.ExportFileExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 网关管理
 * 
 * @author Administrator
 * 
 */
public interface INetwayManage {
	/**
	 * 查询移动网关配置列表
	 * 
	 * @param condition
	 * @return
	 */
	List<Channel> queryChannelList(Channel condition);

	/**
	 * 保存移动网关信息
	 * 
	 * @param data
	 * @return
	 */
	Channel addChannel(Channel data);

	/**
	 * 修改移动网关配置
	 * 
	 * @param data
	 * @return
	 */
	Channel editChannel(Channel data);
	
	/**
	 * 通过主健修改通道属性
	 * @param parMap
	 * @return
	 */
    Channel updateChannelByPrimaryKey(Map<String,String> parMap);

	/**
	 * 接口余额查询
	 *
	 * @param
	 * @return
	 */
	String channelBalance(String no);

	/**
	 * 修改通道状态，启用停用
	 * 
	 * @param ids
	 * @param status
	 */
	void editChannelStatus(List<Integer> ids, String status);

	/**
	 * 通道资费列表
	 * @param channelFee
	 * @return
	 * @author volcano
	 * @date 2019年9月13日上午6:31:45
	 * @version V1.0
	 */
	List<ChannelFee> queryChannelFeeList(ChannelFee channelFee);
	
	
	/**
	 * 添加通道资费
	 * 
	 * @param channelFee
	 */
	void addChannelFee(ChannelFee channelFee);
	
	/**
	 * 修改通道资费
	 * 
	 * @param channelFee
	 */
	void editChannelFee(ChannelFee channelFee);
	
	/**
	 * 
	 * 删除通道资费
	 * 
	 * @param ckIds
	 */
	void delChannelFee(List<Integer> ckIds);
	
	
	/**
	 * 获取产品
	 * 
	 * @param product
	 * @return
	 * @author volcano
	 * @date 2019年9月14日上午11:45:40
	 * @version V1.0
	 */
	List<Product> queryProductList(Product product);

	

	/**
	 * 产品通道
	 * 
	 * @param productChannels
	 * @return
	 * @author volcano
	 * @date 2019年9月14日上午11:33:49
	 * @version V1.0
	 */
	List<ProductChannels> queryProductChannelsList(ProductChannels productChannels);


	/**
	 * 通道试一试
	* @param channelNo
	 * @param phoneNos
	 * @param content
	 * @param subCode
	 * @param countryCode
	* @author volcano
	* @date 2019年9月23日下午9:44:36
	* @version V1.0
	 */
	void try2Try(String channelNo,String phoneNos,String content, String subCode, String countryCode);

	/**
	 * 查询通道签名列表
	 * @param channelSignature
	 * @return
	 */
	List<ChannelDiversion> queryChannelDiversionList(ChannelDiversion channelSignature);

	/**
	 * 添加通道导流签名
	 * @param diversion
	 */
	void addChannelDiversion(ChannelDiversion diversion);

	/**
	 * 根据Id查询导流签名信息
	 * @param id
	 * @return
	 */
	ChannelDiversion queryChannelDiversionById(Integer id);


	/**
	 * 停用、启用通道导流签名
	 * @param ckIds
	 * @param statusCode
	 * @param type
	 */
	void updateChannelDiversionStatus(List<Integer> ckIds, String statusCode, String type);

	/**
	 * 删除通道导流签名
	 * @param ckIds
	 * @param type
	 */
	void delChannelDiversion(List<Integer> ckIds, String type);

	/**
	 * 导入通道签名
	 * @param importFile
	 * @param channelSignature
	 */
	void importChannelDiversion(MultipartFile importFile, ChannelDiversion channelSignature, String strategyType);

	/**
	 * 导出通道签名
	 * @param baseExportBasePath
	 * @param channelNo
	 * @param adminDefaultExportFile
	 */
	void exportChannelDiversion(String baseExportBasePath, String channelNo, ExportFileExt adminDefaultExportFile, String strategyType);

	/**
	 * 分页查询通道签名数据
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<ChannelDiversion> queryChannelDiversionList(int page, int pageSize, ChannelDiversion channelDiversion);

	/**
	 * 手动初始化通道签名
	 */
	void initChannelDiversion();

}

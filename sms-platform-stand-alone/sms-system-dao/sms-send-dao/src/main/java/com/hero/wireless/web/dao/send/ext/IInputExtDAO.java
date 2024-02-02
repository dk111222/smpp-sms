package com.hero.wireless.web.dao.send.ext;

import com.hero.wireless.web.dao.ext.IExtDAO;
import com.hero.wireless.web.dao.send.IInputDAO;
import com.hero.wireless.web.entity.send.Input;
import com.hero.wireless.web.entity.send.InputExample;
import com.hero.wireless.web.entity.send.Submit;
import com.hero.wireless.web.entity.send.SubmitExample;
import com.hero.wireless.web.entity.send.ext.InputExt;

import java.util.List;
import java.util.Map;

public interface IInputExtDAO extends IInputDAO<InputExt>,IExtDAO<InputExt, InputExample> {
	Integer insertExt(List<Input> list);

    List<Map<String,Object>> queryInputListForExportPage(InputExample example);

	List<InputExt> selectAuditingExtByExample(InputExample example);

	int selectAuditingExtByExampleCount(InputExample example);

	/**
	 * 查询待提交短信数据
	 * @param example
	 * @return
	 */
	List<Input> queryInputAwaitList(InputExample example);
}

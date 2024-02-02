package com.hero.wireless.web.dao.send;

import com.hero.wireless.web.dao.base.IDao;
import com.hero.wireless.web.entity.send.InputAwait;
import com.hero.wireless.web.entity.send.InputAwaitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IInputAwaitDAO<T extends InputAwait> extends IDao<InputAwait, InputAwaitExample> {
    int countByExample(InputAwaitExample example);

    int deleteByExample(InputAwaitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InputAwait record);

    int insertList(List<InputAwait> list);

    int insertSelective(InputAwait record);

    List<InputAwait> selectByExample(InputAwaitExample example);

    InputAwait selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InputAwait record, @Param("example") InputAwaitExample example);

    int updateByExample(@Param("record") InputAwait record, @Param("example") InputAwaitExample example);

    int updateByPrimaryKeySelective(InputAwait record);

    int updateByPrimaryKey(InputAwait record);
}
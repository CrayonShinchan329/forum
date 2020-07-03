package com.lxinet.jeesns.dao.group;

import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.group.Match;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMatchDao extends IBaseDao<Match> {
    List<Match> listByPage(Page page, @Param("status") Integer status, @Param("key") String key);


    Integer changeStatus(@Param("status")Integer status, @Param("id")Integer id);
}

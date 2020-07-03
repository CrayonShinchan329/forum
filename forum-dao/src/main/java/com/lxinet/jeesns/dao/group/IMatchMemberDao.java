package com.lxinet.jeesns.dao.group;

import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.group.MatchMember;

import java.util.List;

public interface IMatchMemberDao extends IBaseDao<MatchMember> {
    List<MatchMember> listByPage(Page page, Integer status, String key);

    Integer changeStatus(Integer status, Integer id);
}

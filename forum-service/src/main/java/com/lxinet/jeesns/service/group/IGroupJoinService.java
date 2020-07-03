package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.group.GroupJoin;
import com.lxinet.jeesns.model.member.Member;


/**
 * Created by zchuanzhao on 16/12/23.
 */
public interface IGroupJoinService {

    ResponseModel save(Member loginMember, GroupJoin groupJoin);

    ResponseModel listByPage(int status, Page page, String key, Integer groupId);

    ResponseModel audit(int joinId, int groupId, int memberId);

}

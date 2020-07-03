package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.group.MatchMember;
import com.lxinet.jeesns.model.member.Member;

public interface IMatchMemberService {

    ResponseModel findDetail(int id);

    ResponseModel save(Member loginMember, MatchMember matchMember);

    ResponseModel update(Member loginMember, MatchMember matchMember);

    ResponseModel delete(Member loginMember, int id);

    ResponseModel listByPage(int status, Page page, String key);


    ResponseModel changeStatus(int id, int status);
}

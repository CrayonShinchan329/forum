package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.group.Match;
import com.lxinet.jeesns.model.member.Member;
import org.apache.ibatis.annotations.Param;

public interface IMatchService {

    Match findDetail(int id);

    ResponseModel save(Member loginMember, Match match);

    ResponseModel update(Member loginMember, Match match);

    ResponseModel delete(Member loginMember, int id);

    ResponseModel listByPage( @Param("status")int status, Page page, @Param("key") String key);

    ResponseModel changeStatus(int id, int status);
}

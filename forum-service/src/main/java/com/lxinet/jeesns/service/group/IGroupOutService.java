package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.group.GroupOut;
import com.lxinet.jeesns.model.member.Member;


/**
 * Created by zchuanzhao on 16/12/23.
 */
public interface IGroupOutService {

    ResponseModel save(GroupOut groupOut);

    ResponseModel listByPage(int status, Page page, Integer groupId);

    ResponseModel out(int outId, int groupId, int memberId);

}

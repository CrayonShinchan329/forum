package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.group.Group;
import com.lxinet.jeesns.model.member.Member;

import java.util.List;


/**
 * Created by zchuanzhao on 16/12/23.
 */
public interface IGroupService {

    Group findById(int id);

    ResponseModel findDetail(int id);

    ResponseModel save(Member loginMember, Group group);

    ResponseModel update(Member loginMember, Group group);

    ResponseModel delete(Member loginMember, int id);

    /**
     *
     * @param status
     * @param page
     * @param key
     * @param memberId
     * @param type 0 我的社团 1我加入的社团
     * @return
     */
    ResponseModel listByPage(int status, Page page, String key, int memberId, int type);

    ResponseModel follow(Member loginMember, Integer groupId,int type);

    ResponseModel changeStatus(int id);

    List<Group> listByCustom(int status, int num, String sort);
}

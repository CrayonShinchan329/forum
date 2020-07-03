package com.lxinet.jeesns.service.group.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.group.IMatchMemberDao;
import com.lxinet.jeesns.model.group.Match;
import com.lxinet.jeesns.model.group.MatchMember;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.group.IMatchMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("matchMemberService")
public class MatchMemberServiceImpl implements IMatchMemberService {
    @Resource
    IMatchMemberDao dao;

    @Override
    public ResponseModel findDetail(int id) {
        return new ResponseModel(0, "获取成功",  dao.findById(id));
    }

    @Override
    public ResponseModel save(Member loginMember, MatchMember matchMember) {
        matchMember.setMemberId(loginMember.getId());
        matchMember.setStatus(0);
        dao.save(matchMember);
        return new ResponseModel(0, "保存成功");
    }

    @Override
    public ResponseModel update(Member loginMember, MatchMember matchMember) {
        dao.update(matchMember);
        return new ResponseModel(0, "修改成功");
    }

    @Override
    public ResponseModel delete(Member loginMember, int id) {
        dao.delete(id);
        return new ResponseModel(0, "删除成功");
    }

    @Override
    public ResponseModel listByPage(int status, Page page, String key) {
        List<MatchMember> list = dao.listByPage(page, status, key);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public ResponseModel changeStatus(int id, int status) {
        dao.changeStatus(status, id);
        return new ResponseModel(0, "操作成功");
    }
}

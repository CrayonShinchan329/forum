package com.lxinet.jeesns.service.group.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.group.IMatchDao;
import com.lxinet.jeesns.model.group.Match;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.group.IMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("matchService")
public class MatchServiceImpl implements IMatchService {
    @Resource
    IMatchDao dao;
    @Override
    public Match findDetail(int id) {
        return dao.findById(id);
    }

    @Override
    public ResponseModel save(Member loginMember, Match match) {
        match.setStatus(0);
        match.setCharge(loginMember.getId());
        match.setCreateTime(new Date());
        dao.save(match);
        return new ResponseModel(0, "保存成功");
    }

    @Override
    public ResponseModel update(Member loginMember, Match match) {
        dao.update(match);
        return new ResponseModel(0, "修改成功");
    }

    @Override
    public ResponseModel delete(Member loginMember, int id) {
        dao.delete(id);
        return new ResponseModel(0, "删除成功");
    }

    @Override
    public ResponseModel listByPage(int status, Page page, String key) {
        List<Match> list = dao.listByPage(page, status, key);
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

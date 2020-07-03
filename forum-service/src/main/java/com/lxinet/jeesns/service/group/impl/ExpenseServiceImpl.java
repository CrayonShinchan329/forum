package com.lxinet.jeesns.service.group.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.group.IExpenseDao;
import com.lxinet.jeesns.dao.group.IMatchDao;
import com.lxinet.jeesns.model.group.Expense;
import com.lxinet.jeesns.model.group.Match;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.group.IExpenseService;
import com.lxinet.jeesns.service.group.IMatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("expenseService")
public class ExpenseServiceImpl implements IExpenseService {
    @Resource
    IExpenseDao dao;

    @Override
    public Expense findDetail(int id) {
        return dao.findById(id);
    }

    @Override
    public ResponseModel save(Member loginMember, Expense expense) {
        dao.save(expense);
        return new ResponseModel(0, "保存成功");
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

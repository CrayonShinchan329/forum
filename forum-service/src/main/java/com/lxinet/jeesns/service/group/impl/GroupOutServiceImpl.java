package com.lxinet.jeesns.service.group.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.group.IGroupFansDao;
import com.lxinet.jeesns.dao.group.IGroupOutDao;
import com.lxinet.jeesns.model.group.GroupOut;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.group.IGroupOutService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/23.
 */
@Service("groupOutService")
public class GroupOutServiceImpl implements IGroupOutService {
    @Resource
    private IGroupOutDao groupOutDao;
    @Resource
    private IGroupFansDao groupFansDao;

    @Override
    public ResponseModel listByPage(int status, Page page, Integer groupId) {
        List<GroupOut> list = groupOutDao.listByPage(page, status, groupId);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }


    @Override
    public ResponseModel out(int outId, int groupId, int memberId) {
        if(groupOutDao.changeStatus(outId) == 1){
            if(groupFansDao.findByMemberAndGroup(groupId,memberId)!= null){
                if(groupFansDao.delete(groupId,memberId) == 1){
                    return new ResponseModel(1,"操作成功");
                }
            }
        }
        return new ResponseModel(-1,"操作失败");
    }
    @Override
    @Transactional
    public ResponseModel save( GroupOut groupOut) {
        groupOutDao.save(groupOut);
        return new ResponseModel(1,"操作成功");
    }

}

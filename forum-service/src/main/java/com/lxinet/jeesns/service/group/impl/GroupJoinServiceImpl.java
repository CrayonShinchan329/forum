package com.lxinet.jeesns.service.group.impl;

import com.lxinet.jeesns.common.utils.ActionUtil;
import com.lxinet.jeesns.common.utils.ConfigUtil;
import com.lxinet.jeesns.common.utils.ScoreRuleConsts;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.dao.group.IGroupDao;
import com.lxinet.jeesns.dao.group.IGroupFansDao;
import com.lxinet.jeesns.dao.group.IGroupJoinDao;
import com.lxinet.jeesns.model.group.Group;
import com.lxinet.jeesns.model.group.GroupJoin;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.group.IGroupFansService;
import com.lxinet.jeesns.service.group.IGroupJoinService;
import com.lxinet.jeesns.service.group.IGroupService;
import com.lxinet.jeesns.service.member.IMemberService;
import com.lxinet.jeesns.service.member.IScoreDetailService;
import com.lxinet.jeesns.service.system.IActionLogService;
import com.lxinet.jeesns.service.system.IConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 2016/12/23.
 */
@Service("groupJoinService")
public class GroupJoinServiceImpl implements IGroupJoinService {
    @Resource
    private IGroupJoinDao groupJoinDao;
    @Resource
    private IGroupFansDao groupFansDao;

    @Override
    public ResponseModel listByPage(int status, Page page, String key, Integer groupId) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key.trim()+"%";
        }
        List<GroupJoin> list = groupJoinDao.listByPage(page, status,key, groupId);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }


    @Override
    public ResponseModel audit(int joinId, int groupId, int memberId) {
        if(groupJoinDao.changeStatus(joinId) == 1){
            if(groupFansDao.findByMemberAndGroup(groupId,memberId) == null){
                if(groupFansDao.save(groupId,memberId) == 1){
                    return new ResponseModel(1,"操作成功");
                }
            }
        }
        return new ResponseModel(-1,"操作失败");
    }
    @Override
    @Transactional
    public ResponseModel save(Member loginMember, GroupJoin groupJoin) {
        groupJoin.setMemberId(loginMember.getId());
        groupJoin.setStatus(0);
        groupJoinDao.save(groupJoin);
        return new ResponseModel(1,"操作成功");
    }

}

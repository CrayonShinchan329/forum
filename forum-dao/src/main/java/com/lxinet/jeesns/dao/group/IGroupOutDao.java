package com.lxinet.jeesns.dao.group;

import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.group.GroupOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGroupOutDao extends IBaseDao<GroupOut> {

    /**
     * 获取社团
     * @return
     */
    List<GroupOut> listByPage(@Param("page") Page page, @Param("status") Integer status,@Param("groupId") Integer groupId);

    /**
     * 修改状态
     *
     * @param id
     * @return
     */
    Integer changeStatus(@Param("id") Integer id);
}

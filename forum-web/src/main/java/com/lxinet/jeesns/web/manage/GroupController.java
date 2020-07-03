package com.lxinet.jeesns.web.manage;

import com.lxinet.jeesns.common.utils.MemberUtil;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.group.IExpenseService;
import com.lxinet.jeesns.service.group.IGroupService;
import com.lxinet.jeesns.service.group.IMatchService;
import com.lxinet.jeesns.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 16/12/23.
 */
@Controller("manageGroupController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class GroupController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/group/";
    @Resource
    private IGroupService groupService;
    @Resource
    private IMatchService matchService;
    @Resource
    private IExpenseService expenseService;

    @RequestMapping(value = "${managePath}/group/index")
    public String index(@RequestParam(value = "status",required = false,defaultValue = "-1") Integer status,
                        String key,
                        Model model) {
        Page page = new Page(request);
        ResponseModel responseModel = groupService.listByPage(status,page,key,-1,-1);
        model.addAttribute("model",responseModel);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "index";
    }
    //后台管理员社团比赛管理
    @RequestMapping(value = "${managePath}/group/indexone")
    public String indexone(@RequestParam(value = "status",required = false,defaultValue = "-1") Integer status,
                        String key,
                        Model model) {
        Page page = new Page(request);
        ResponseModel responseModel = matchService.listByPage(status,page, key);
        model.addAttribute("model",responseModel);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "indexone";
    }
    //后台管理员社团经费管理
    @RequestMapping(value = "${managePath}/group/indextwo")
    public String indextwo(@RequestParam(value = "status",required = false,defaultValue = "-1") Integer status,
                           String key,
                           Model model) {
        Page page = new Page(request);
        ResponseModel responseModel = expenseService.listByPage(status,page,key);
        model.addAttribute("model",responseModel);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "indextwo";
    }
    @RequestMapping(value = "${managePath}/group/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        ResponseModel response = groupService.delete(loginMember,id);
        return response;
    }

    @RequestMapping(value = "${managePath}/group/changeStatus/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object changeStatus(@PathVariable("id") int id){
        ResponseModel response = groupService.changeStatus(id);
        return response;
    }
    @RequestMapping(value = "${managePath}/group/detail",method = RequestMethod.GET)
    public String detail(@RequestParam int id,Model model){
        ResponseModel response = groupService.findDetail(id);
        model.addAttribute("model",response);
        return MANAGE_FTL_PATH + "detail";
    }



}

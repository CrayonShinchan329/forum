package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.common.utils.MemberUtil;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.group.*;
import com.lxinet.jeesns.service.common.IArchiveService;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.service.group.*;
import com.lxinet.jeesns.web.common.BaseController;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.IMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zchuanzhao on 16/12/26.
 */
@Controller("frontGroupController")
@RequestMapping("/group")
public class GroupController extends BaseController {
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private IGroupService groupService;
    @Resource
    private IGroupJoinService groupJoinService;
    @Resource
    private IGroupOutService groupOutService;
    @Resource
    private IGroupTopicService groupTopicService;
    @Resource
    private IGroupFansService groupFansService;
    @Resource
    private IGroupTopicCommentService groupTopicCommentService;
    @Resource
    private IArchiveService archiveService;
    @Resource
    private IMemberService memberService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(String key,Model model) {
        Page page = new Page(request);
        ResponseModel responseModel = groupService.listByPage(1,page,key, -1, -1);
        model.addAttribute("model",responseModel);
        model.addAttribute("key",key);
        return jeesnsConfig.getFrontTemplate() + "/group/index";
    }
    @RequestMapping(value = "/myClub",method = RequestMethod.GET)
    public String myClub(String key,Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Page page = new Page(request);
        ResponseModel myClub = groupService.listByPage(1,page,key, loginMember.getId(), 0);
        ResponseModel myJoinClub = groupService.listByPage(1,page,key, loginMember.getId(), 1);
        model.addAttribute("myClub",myClub);
        model.addAttribute("myJoinClub",myJoinClub);
        model.addAttribute("key",key);
        return jeesnsConfig.getFrontTemplate() + "/myClub";
    }
    @RequestMapping(value = "/myJoinClub",method = RequestMethod.GET)
    public String myJoinClub(String key,Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Page page = new Page(request);
        ResponseModel myClub = groupService.listByPage(1,page,key, loginMember.getId(), 0);
        ResponseModel myJoinClub = groupService.listByPage(1,page,key, loginMember.getId(), 1);
        model.addAttribute("myClub",myClub);
        model.addAttribute("myJoinClub",myJoinClub);
        model.addAttribute("key",key);
        return jeesnsConfig.getFrontTemplate() + "/myJoinClub";
    }
    /**
     *
     * 比赛申请
     */
    @RequestMapping(value = "/createMatch",method = RequestMethod.GET)
    public String createMatch(@RequestParam Integer groupId,Model model) {
        Group group = groupService.findById(groupId);
        model.addAttribute("group",group);
        return jeesnsConfig.getFrontTemplate() + "/group/createMatch";
    }

    /**
     *
     * 经费申请
     */
    @RequestMapping(value = "/expense",method = RequestMethod.GET)
    public String expense(@RequestParam Integer groupId,Model model) {
        Group group = groupService.findById(groupId);
        model.addAttribute("group",group);
        return jeesnsConfig.getFrontTemplate() + "/group/expense";
    }

    @RequestMapping(value = "/apply",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String apply(){
        String judgeLoginJump = MemberUtil.judgeLoginJump(request, RedirectUrlUtil.GROUP_APPLY);
        if(StringUtils.isNotEmpty(judgeLoginJump)){
            return judgeLoginJump;
        }
        return jeesnsConfig.getFrontTemplate() + "/group/apply";
    }

    /**
     * 社团详情页面
     * @param groupId
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/{groupId}",method = RequestMethod.GET)
    public String detail(@PathVariable("groupId") Integer groupId, Model model) {
        Page page = new Page(request);
        Group group = groupService.findById(groupId);
        if(group == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1002,Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("group",group);
        Member loginMember = MemberUtil.getLoginMember(request);
        int memberId = 0;
        if(loginMember != null){
            memberId = loginMember.getId();
        }
        //判断是否已关注该社团
        GroupFans groupFans = groupFansService.findByMemberAndGroup(groupId,memberId);
        if(groupFans == null){
            model.addAttribute("isfollow",false);
        }else {
            model.addAttribute("isfollow",true);
        }
        //获取社团活动列表
        ResponseModel responseModel = groupTopicService.listByPage(page,null,groupId,1,0);
        model.addAttribute("model",responseModel);
        String managerIds = group.getManagers();
        List<Member> managerList = new ArrayList<>();
        if(StringUtils.isNotEmpty(managerIds)){
            String[] idArr = managerIds.split(",");
            for (String id : idArr){
                Member member = memberService.findById(Integer.parseInt(id));
                if(member != null){
                    managerList.add(member);
                }
            }
        }
        model.addAttribute("managerList",managerList);

        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        if(loginMember == null){
            model.addAttribute("isManager",0);
        }else {
            boolean isManager = false;
            for (String manager : groupManagerArr){
                if(loginMember.getId() == Integer.parseInt(manager)){
                    isManager = true;
                }
            }
            if(isManager || loginMember.getId() == group.getCreator()){
                model.addAttribute("isManager",1);
            }
        }
        //获取社团粉丝列表,第一页，20条数据
        Page groupFansPage = new Page(1,20);
        List<GroupFans> groupFansList = (List<GroupFans>) groupFansService.listByPage(groupFansPage,groupId).getData();
        model.addAttribute("groupFansList",groupFansList);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/group/detail";
    }

    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    @ResponseBody
    public Object apply(Group group){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return groupService.save(loginMember,group);
    }


    @RequestMapping(value = "/edit/{groupId}",method = RequestMethod.GET)
    public String edit(@PathVariable("groupId") Integer groupId,Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        String judgeLoginJump = MemberUtil.judgeLoginJump(request, RedirectUrlUtil.GROUP_EDIT+"/"+groupId);
        if(StringUtils.isNotEmpty(judgeLoginJump)){
            return judgeLoginJump;
        }

        Group group = groupService.findById(groupId);
        if(group == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1002,Const.INDEX_ERROR_FTL_PATH);
        }
        if(group.getCreator().intValue() != loginMember.getId().intValue()){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1001,Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("group",group);
        String managerIds = group.getManagers();
        String newManagerNames = "";
        if(StringUtils.isNotEmpty(managerIds)){
            String[] idArr = managerIds.split(",");
            for (String id : idArr){
                Member member = memberService.findById(Integer.parseInt(id));
                if(member != null){
                    newManagerNames += member.getName() + ",";
                }
            }
            if(newManagerNames.length() > 0){
                newManagerNames = newManagerNames.substring(0,newManagerNames.length()-1);
            }
        }
        model.addAttribute("managerNames",newManagerNames);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/group/edit";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(Group group){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return groupService.update(loginMember,group);
    }

    @RequestMapping(value = "/topic/{topicId}",method = RequestMethod.GET)
    public String topic(@PathVariable("topicId") Integer topicId,Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        GroupTopic groupTopic = groupTopicService.findById(topicId,loginMember);
        if(groupTopic == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1004,Const.INDEX_ERROR_FTL_PATH);
        }
        archiveService.updateViewCount(groupTopic.getArchiveId());
        model.addAttribute("groupTopic",groupTopic);

        Group group = groupService.findById(groupTopic.getGroup().getId());
        if(group == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1000,Const.INDEX_ERROR_FTL_PATH);
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isfollow = false;
        if(loginMember == null){
            model.addAttribute("isPermission",0);
        }else {
            boolean isManager = false;
            for (String manager : groupManagerArr){
                if(loginMember.getId() == Integer.parseInt(manager)){
                    isManager = true;
                }
            }
            if(loginMember.getId().intValue() == groupTopic.getMember().getId().intValue() || loginMember.getIsAdmin() > 0 ||
                    isManager || loginMember.getId().intValue() == group.getCreator().intValue()){
                model.addAttribute("isPermission",1);
            }
            //判断是否已关注该社团
            GroupFans groupFans = groupFansService.findByMemberAndGroup(groupTopic.getGroup().getId(),loginMember.getId());
            if(groupFans != null){
                isfollow = true;
            }
        }
        model.addAttribute("isfollow",isfollow);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/group/topic";
    }

    @RequestMapping(value = "/post/{groupId}",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String post(@PathVariable("groupId") Integer groupId,Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        String judgeLoginJump = MemberUtil.judgeLoginJump(request, RedirectUrlUtil.GROUP_POST+"/"+groupId);
        if(StringUtils.isNotEmpty(judgeLoginJump)){
            return judgeLoginJump;
        }
        Group group = groupService.findById(groupId);
        if(group == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1002, Const.INDEX_ERROR_FTL_PATH);
        }
        GroupFans groupFans = groupFansService.findByMemberAndGroup(groupId,loginMember.getId());
        if(groupFans == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1003, Const.INDEX_ERROR_FTL_PATH);
        }
        if(group.getCanPost() == 0){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1006, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("group",group);
        return jeesnsConfig.getFrontTemplate() + "/group/post";
    }

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    @ResponseBody
    public Object post(GroupTopic groupTopic){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return groupTopicService.save(loginMember,groupTopic);
    }

    @RequestMapping(value = "/topicEdit/{topicId}",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String topicEdit(@PathVariable("topicId") Integer topicId,Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        String judgeLoginJump = MemberUtil.judgeLoginJump(request, RedirectUrlUtil.GROUP_TOPIC_EDIT+"/"+topicId);
        if(StringUtils.isNotEmpty(judgeLoginJump)){
            return judgeLoginJump;
        }
        GroupTopic groupTopic = groupTopicService.findById(topicId,loginMember);
        if(groupTopic == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1004, Const.INDEX_ERROR_FTL_PATH);
        }
        if(loginMember.getId().intValue() != groupTopic.getMember().getId().intValue()){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1001, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("groupTopic",groupTopic);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/group/topicEdit";
    }

    @RequestMapping(value = "/topicUpdate",method = RequestMethod.POST)
    @ResponseBody
    public Object topicUpdate(GroupTopic groupTopic){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        ResponseModel responseModel = groupTopicService.update(loginMember,groupTopic);
        if(responseModel.getCode() == 0){
            responseModel.setCode(2);
            responseModel.setUrl(request.getContextPath()+"/group/topic/"+groupTopic.getId());
        }
        return responseModel;
    }

    /**
     * 跳转到社团申请页面
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/join",method = RequestMethod.GET)
    public String join(@RequestParam Integer groupId, Model model){
        Group group = groupService.findById(groupId);
        model.addAttribute("group",group);
        return jeesnsConfig.getFrontTemplate() + "/group/join";
    }

    /**
     * 跳转到社团申请页面
     * @param groupJoin
     * @return
     */
    @RequestMapping(value = "/join/push",method = RequestMethod.POST)
    @ResponseBody
    public Object joinPush(GroupJoin groupJoin, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        return groupJoinService.save(loginMember, groupJoin);
    }

    /**
     * 社团审批列表页
     *
     * @param groupId
     * @param model
     * @return
     */
    @RequestMapping(value = "/auditNewMemberList/{groupId}",method = RequestMethod.GET)
    public String auditNewMemberList(@PathVariable("groupId") Integer groupId, Model model){
        ResponseModel responseModel = groupJoinService.listByPage(-1, null, null, groupId);
        model.addAttribute("model",responseModel);
        return jeesnsConfig.getFrontTemplate() + "/group/auditNewMemberList";
    }

    /**
     * 社团申请审批
     *
     * @param joinId
     * @param model
     * @return
     */
    @RequestMapping(value = "/auditNewMember",method = RequestMethod.GET)
    @ResponseBody
    public Object auditNewMember(
            @RequestParam Integer joinId,
            @RequestParam Integer groupId,
            @RequestParam Integer memberId,
            @RequestParam Integer joinStatus,
            Model model){
        if (joinStatus == 1) {
            return new ResponseModel(-2);
        }
        return groupJoinService.audit(joinId, groupId, memberId);
    }

    /**
     * 退团审批列表页
     *
     * @param groupId
     * @param model
     * @return
     */
    @RequestMapping(value = "/outMemberList/{groupId}",method = RequestMethod.GET)
    public String outMemberList(@PathVariable("groupId") Integer groupId, Model model){
        ResponseModel responseModel = groupOutService.listByPage(-1, null, groupId);
        model.addAttribute("model",responseModel);
        return jeesnsConfig.getFrontTemplate() + "/group/outMemberList";
    }

    /**
     * 社团申请审批
     *
     * @param outId
     * @param model
     * @return
     */
    @RequestMapping(value = "/out",method = RequestMethod.GET)
    @ResponseBody
    public Object out(
            @RequestParam Integer outId,
            @RequestParam Integer groupId,
            @RequestParam Integer memberId,
            @RequestParam Integer outStatus,
            Model model){
        if (outStatus == 1) {
            return new ResponseModel(-2);
        }
        return groupOutService.out(outId, groupId, memberId);
    }

    @RequestMapping(value = "/addOut",method = RequestMethod.GET)
    @ResponseBody
    public Object addOut(
            @RequestParam Integer groupId,
            Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        GroupOut out = new GroupOut();
        out.setGroupId(groupId);
        out.setMemberId(loginMember.getId());
        out.setStatus(0);
        return  groupOutService.save(out);
    }

    /**
     * 关注社团
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/follow/{groupId}",method = RequestMethod.GET)
    @ResponseBody
    public Object follow(@PathVariable("groupId") Integer groupId){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return groupService.follow(loginMember,groupId,0);
    }

    /**
     * 取消关注社团
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/nofollow/{groupId}",method = RequestMethod.GET)
    @ResponseBody
    public Object nofollow(@PathVariable("groupId") Integer groupId){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return groupService.follow(loginMember,groupId,1);
    }

    /**
     * 活动评论
     * @param groupTopicId
     * @param content
     * @return
     */
    @RequestMapping(value="/comment/{groupTopicId}",method = RequestMethod.POST)
    @ResponseBody
    public Object comment(@PathVariable("groupTopicId") Integer groupTopicId, String content,Integer groupTopicCommentId){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return groupTopicCommentService.save(loginMember,content,groupTopicId,groupTopicCommentId);
    }


    @RequestMapping(value="/commentList/{groupTopicId}.json",method = RequestMethod.GET)
    @ResponseBody
    public Object commentList(@PathVariable("groupTopicId") Integer groupTopicId){
        Page page = new Page(request);
        if(groupTopicId == null){
            groupTopicId = 0;
        }
        return groupTopicCommentService.listByGroupTopic(page,groupTopicId);
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        ResponseModel responseModel = groupTopicService.indexDelete(request,loginMember,id);
        return responseModel;
    }

    /**
     * 未审核活动列表
     */
    @RequestMapping(value = "/auditList/{groupId}",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String auditList(@PathVariable("groupId") Integer groupId, Model model) {
        Page page = new Page(request);
        Group group = groupService.findById(groupId);
        if(group == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1002,Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("group",group);
        Member loginMember = MemberUtil.getLoginMember(request);
        int memberId = 0;
        if(loginMember != null){
            memberId = loginMember.getId();
        }
        //判断是否已关注该社团
        GroupFans groupFans = groupFansService.findByMemberAndGroup(groupId,memberId);
        if(groupFans == null){
            model.addAttribute("isfollow",false);
        }else {
            model.addAttribute("isfollow",true);
        }
        //获取社团活动列表
        ResponseModel responseModel = groupTopicService.listByPage(page,null,groupId,0,0);
        model.addAttribute("model",responseModel);
        String managerIds = group.getManagers();
        List<Member> managerList = new ArrayList<>();
        if(StringUtils.isNotEmpty(managerIds)){
            String[] idArr = managerIds.split(",");
            for (String id : idArr){
                Member member = memberService.findById(Integer.parseInt(id));
                if(member != null){
                    managerList.add(member);
                }
            }
        }
        model.addAttribute("managerList",managerList);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/group/auditList";
    }


    @RequestMapping(value = "/audit/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object audit(@PathVariable("id") Integer id) {
        Member loginMember = MemberUtil.getLoginMember(request);
        ResponseModel responseModel = groupTopicService.audit(loginMember,id);
        return responseModel;
    }

    @RequestMapping(value = "/fans/{groupId}",method = RequestMethod.GET)
    public String fans(@PathVariable("groupId") Integer groupId, Model model) {
        Page page = new Page(request);
        Group group = groupService.findById(groupId);
        if(group == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1002,Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("group",group);
        //获取社团粉丝列表,第一页，20条数据
        ResponseModel<GroupFans> responseModel = groupFansService.listByPage(page,groupId);
        model.addAttribute("model",responseModel);
        return jeesnsConfig.getFrontTemplate() + "/group/fans";
    }

    /**
     * 置顶、取消置顶
     * @param id
     * @param top
     * @return
     */
    @RequestMapping(value = "/topic/top/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object top(@PathVariable("id") Integer id,@RequestParam("top") Integer top) {
        Member loginMember = MemberUtil.getLoginMember(request);
        ResponseModel responseModel = groupTopicService.top(loginMember,id,top);
        return responseModel;
    }

    /**
     * 加精、取消加精
     * @param id
     * @param essence
     * @return
     */
    @RequestMapping(value = "/topic/essence/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object essence(@PathVariable("id") Integer id,@RequestParam("essence") Integer essence) {
        Member loginMember = MemberUtil.getLoginMember(request);
        ResponseModel responseModel = groupTopicService.essence(loginMember,id,essence);
        return responseModel;
    }


    /**
     * 活动、喜欢
     * @param id
     * @return
     */
    @RequestMapping(value="/topic/favor/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object favor(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        if(id == null) {
            return new ResponseModel(-1, "非法操作");
        }
        return groupTopicService.favor(loginMember,id);
    }
}

package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.common.utils.MemberUtil;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.model.group.Expense;
import com.lxinet.jeesns.model.group.Match;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.group.IExpenseService;
import com.lxinet.jeesns.service.group.IMatchService;
import com.lxinet.jeesns.service.member.IMessageService;
import com.lxinet.jeesns.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("match")
public class IMatchController extends BaseController {
    @Resource
    IMatchService matchService;
    @Resource
    IExpenseService expenseService;
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private IMessageService messageService;

    @RequestMapping("list")
    public String list(String key, Model model) {
        Page page = new Page(request);
        ResponseModel responseModel = matchService.listByPage(1, page, key);
        model.addAttribute("model", responseModel);
        model.addAttribute("key", key);
        return jeesnsConfig.getFrontTemplate() + "/cms/matchList";
    }

    @RequestMapping("mgrList")
    public String mgrList(String key, Model model) {
        Page page = new Page(request);
        ResponseModel responseModel = matchService.listByPage(1, page, key);
        model.addAttribute("model", responseModel);
        model.addAttribute("key", key);
        return jeesnsConfig.getManageTemplate() + "/match/index";
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(String name, String introduce, String place, String startTime, String endTime, String clubId) {
        Match match = new Match();
        match.setName(name);
        match.setIntroduce(introduce);
        match.setPlace(place);
        match.setStartTime(startTime);
        match.setEndTime(endTime);
        match.setClubId(Integer.parseInt(clubId));
        Member loginMember = MemberUtil.getLoginMember(request);
        return matchService.save(loginMember, match);
    }

    @RequestMapping("addExpense")
    @ResponseBody
    public Object addExpense(String name, String applyPeople, String applyReason) {
        Expense ex = new Expense();
        ex.setName(name);
        ex.setApplyPeople(applyPeople);
        ex.setApplyReason(applyReason);
        Member loginMember = MemberUtil.getLoginMember(request);
        ex.setProposer(loginMember.getId());
        ex.setStatus(0);
        return expenseService.save(loginMember, ex);
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(Match match) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return matchService.update(loginMember, match);
    }

    @RequestMapping("changeStatus")
    @ResponseBody
    public Object changeStatus(@RequestParam int id, @RequestParam int status) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Match match = matchService.findDetail(id);
        if (status == 1) {
            messageService.sentMsg(loginMember.getId(), match.getCharge(), "您比赛申请\"" + match.getName() + "\"已通过.");
        } else {
            messageService.sentMsg(loginMember.getId(), match.getCharge(), "您比赛申请\"" + match.getName() + "\"已驳回.");
        }
        return matchService.changeStatus(id, status);
    }

    @RequestMapping("changeExStatus")
    @ResponseBody
    public Object changeExStatus(@RequestParam int id, @RequestParam int status) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Expense ex = expenseService.findDetail(id);
        if (status == 1) {
            messageService.sentMsg(loginMember.getId(), ex.getProposer(), "您发起的经费申请\"" + ex.getName() + "\"已通过.");
        } else {
            messageService.sentMsg(loginMember.getId(), ex.getProposer(), "您发起的经费申请\"" + ex.getName() + "\"已驳回.");
        }
        return expenseService.changeStatus(id, status);
    }

    @RequestMapping("find")
    public Object find(@RequestParam int id, Model model) {
        model.addAttribute("model", matchService.findDetail(id));
        return "/manage/group/matchInfo";
    }

    @RequestMapping("findEx")
    public Object findEx(@RequestParam int id, Model model) {
        model.addAttribute("model", expenseService.findDetail(id));
        return "/manage/group/exInfo";
    }
}

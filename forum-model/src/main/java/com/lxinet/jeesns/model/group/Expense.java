package com.lxinet.jeesns.model.group;
import java.io.Serializable;

/**
 * 社团经费
 */
public class Expense implements Serializable {
    private Integer id;
    private String name;//申请经费的社团名称
    private String applyPeople;//申请经费负责人
    private String applyReason;//申请经费理由及用途
    private Integer proposer;//发起人
    private Integer status; //0待审核 1通过 2驳回

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplyPeople() {
        return applyPeople;
    }

    public void setApplyPeople(String applyPeople) {
        this.applyPeople = applyPeople;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProposer() {
        return proposer;
    }

    public void setProposer(Integer proposer) {
        this.proposer = proposer;
    }
}

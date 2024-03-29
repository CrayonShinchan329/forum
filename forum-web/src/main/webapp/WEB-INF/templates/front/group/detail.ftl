<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${group.name} - ${GROUP_ALIAS} - ${SITE_NAME} - </title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    
    <link href="${basePath}/res/common/css/zui.min.css" rel="stylesheet">
    <link href="${basePath}/res/front/css/app.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/res/common/js/html5shiv.min.js"></script>
    <script src="${basePath}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/zui.min.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js"></script>
    <script src="${basePath}/res/common/js/extendPagination.js"></script>
</head>
<body class="gray-bg">
<#include "/${frontTemplate}/common/header.ftl"/>
<div class="container">
    <div class="main-content">
        <div class="row">
            <div class="col-md-8">
                <div class="group white-bg">
                    <div class="group-logo">
                        <img alt="${group.name}" src="${basePath}${group.logo}" width="80px" height="80px"/>
                    </div>
                    <div class="group-detail">
                        <p>
                            <span>
                                <strong>${group.name}</strong>
                            </span>
                            <span class="text-right">
                                <#if loginUser?? && loginUser.id == group.creator>
                                   <a href="${basePath}/group/edit/${group.id}">编辑</a>
                                   . <a href="${basePath}/group/createMatch?groupId=${group.id}">创建比赛</a>
                                   . <a href="${basePath}/group/expense?groupId=${group.id}">经费申请</a>
                                <#else>
                                    <#if isfollow == true>
                                        . <a title="申请退出社团" href="${basePath}/group/addOut?groupId=${group.id}"
                                           target="_jeesnsLink"><i class="icon-minus"></i> 申请退出社团</a>
                                    <#else>
                                        . <a title="申请加入" href="${basePath}/group/join?groupId=${group.id}" target="_blank"><i
                                                    class="icon-plus"></i> 申请加入</a>
                                    </#if>
                                </#if>
                                <#if isManager == 1>
                                   . <a href="${basePath}/group/auditList/${group.id}">审核活动</a>
                                   . <a href="${basePath}/group/auditNewMemberList/${group.id}">加入社团审核</a>
                                   . <a href="${basePath}/group/outMemberList/${group.id}">退出社团审核</a>

                                </#if>
                            </span>
                        </p>
                        <p>${model.page.totalCount}活动 · ${groupFansList?size}关注</p>
                        <p><a href="${basePath}/u/${group.creatorMember.id}">${group.creatorMember.name}</a>
                            创建于${group.createTime?string("yyyy-MM-dd")}</p>
                    </div>
                    <div class="group-introduce">
                    ${group.introduce!''}
                    </div>
                </div>
                <@ads id=2>
                    <#include "/tp/ad.ftl"/>
                </@ads>
                <div class="panel group-topic-list no-border">
                    <div class="panel-heading">
                        全部
                        <span class="pull-right">
                            <a class="btn btn-primary m-t-n4" href="${basePath}/group/post/${group.id}">发布活动</a>
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="items">
                        <#list model.data as topic>
                            <div class="item">
                                <div class="item-content">
                                    <div class="media pull-left">
                                        <img src="${basePath}${topic.member.avatar}" class="img-circle"
                                             alt="${topic.member.name}" width="50px" height="50px">
                                    </div>
                                    <div class="text">
                                        <p>
                                        <h4>
                                            <a href="${basePath}/group/topic/${topic.id}">${topic.title}</a>
                                        <#if topic.isTop==1>
                                            <span class="label label-badge label-primary">置顶</span>
                                        <#elseif topic.isTop==2>
                                            <span class="label label-badge label-success">超级置顶</span>
                                        </#if>
                                        <#if topic.isEssence==1>
                                            <span class="label label-badge label-danger">精华</span>
                                        </#if>
                                        </h4>
                                        </p>
                                        <p>
                                            <a href="${basePath}/group/topic/${topic.id}" class="text-muted"><i
                                                    class="icon-eye-open"></i> ${topic.viewCount}</a> &nbsp;
                                            <span class="text-muted">${topic.createTime?string('yyyy-MM-dd HH:mm')}</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </#list>
                        </div>
                    </div>
                    <ul class="pager pagination pagination-sm no-margin pull-right"
                        url="${basePath}/group/detail/${group.id}"
                        currentPage="${model.page.pageNo}"
                        pageCount="${model.page.totalPage}">
                    </ul>
                </div>
            </div>
            <div class="col-md-4 float-left">
                <div class="panel group-detail-fans">
                    <div class="panel-heading">
                        全部成员(${groupFansList?size})
                    </div>
                    <div class="panel-body list">
                        <#list groupFansList as groupFans>
                        <div class="fan">
                            <div class="avatar">
                                <a href="${basePath}/u/${groupFans.member.id}" target="_blank">
                                    <img class="img-circle" src="${basePath}${groupFans.member.avatar}" width="50px"
                                         height="50px"/>
                                </a>
                            </div>
                            <div class="name">
                                <a href="${basePath}/u/${groupFans.member.id}" target="_blank">
                                    ${groupFans.member.name}
                                </a>
                            </div>
                        </div>
                        </#list>
                    </div>
                </div>
                <@ads id=1>
                    <#include "/tp/ad.ftl"/>
                </@ads>
            </div>
        </div>
    </div>
</div>
<#include "/${frontTemplate}/common/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>
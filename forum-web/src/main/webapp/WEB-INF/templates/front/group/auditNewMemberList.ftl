<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>加入社团审核</title>
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
            <div class="col-md-12">
                <div class="panel group-topic-list no-border">
                    <div class="panel-heading">
                        加入社团审核
                    </div>
                    <div class="panel-body">
                        <div class="items">
                            <div class="item">
                                <div class="item-content">
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>昵称</th>
                                            <th>真实姓名</th>
                                            <th>班级</th>
                                            <th>学号</th>
                                            <th>加入理由</th>
                                            <th>手机</th>
                                            <th>是否遵守社团章程</th>
                                            <th>是否缴费</th>
                                            <th>申请时间</th>
                                            <th width="50px">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#list model.data as join>
                                            <tr>
                                                <td>${join.id}</td>
                                                <td>${join.memberName}</td>
                                                <td>${join.realName}</td>
                                                <td>${join.sClass}</td>
                                                <td>${join.sNo}</td>
                                                <td>${join.reason}</td>
                                                <td>${join.phone}</td>
                                                <td>
                                                   <#if join.isFollowPrepare==0>
                                                       是
                                                   <#elseif join.isFollowPrepare==1>
                                                       否
                                                   </#if>
                                                </td>
                                                <td>
                                                    <#if join.isVip==0>
                                                        是
                                                    <#elseif join.isVip==1>
                                                        否
                                                    </#if>
                                                </td>

                                                <td>${join.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                                <td>
                                                    <#if join.status==0>
                                                        <a class="marg-l-5" target="_jeesnsLink" href="${basePath}/group/auditNewMember?joinId=${join.id}&groupId=${join.groupId}&memberId=${join.memberId}&joinStatus=${join.status}">
                                                            <span class="label label-danger">未审核</span>
                                                        </a>
                                                    <#elseif join.status==1>
                                                        <span class="label label-success">已审核</span>
                                                    </#if>
                                                </td>
                                            </tr>
                                        </#list>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/${frontTemplate}/common/footer.ftl"/>
</body>
</html>
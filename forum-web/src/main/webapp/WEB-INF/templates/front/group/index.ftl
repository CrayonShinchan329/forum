<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${GROUP_ALIAS} - ${SITE_NAME} - </title>
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
        <div class="row white-bg group-list">
            <div class="panel-heading row" style="margin-bottom: 30px">
                <div class="col-md-9">
                    ${GROUP_ALIAS}
                </div>
                <span class="col-md-2">
                    <form action="${basePath}/group/" method="get" style="margin-top: -4px;">
                        <div class="input-group">
                            <input type="text" class="form-control" name="key">
                            <span class="input-group-btn">
                                    <button class="btn btn-success" type="submit"><i class="icon-search"></i></button>
                                </span>
                        </div>
                    </form>
                </span>
                <span class="col-md-1 pull-right">
                    <a class="btn btn-primary m-t-n4" href="${basePath}/group/apply">申请社团</a>
                </span>
            </div>
            <#list model.data as group>
                <div class="col-md-3">
                    <div class="group-detail">
                        <div class="group-logo">
                            <a href="${basePath}/group/detail/${group.id}">
                                <img class="img-rounded" src="${basePath}${group.logo}" width="100px" height="100px">
                            </a>
                        </div>
                        <div class="group-info">
                            <h4><strong><a href="${basePath}/group/detail/${group.id}">${group.name}</a></strong></h4>
                            <h5>社团类型：${group.type}</h5>
                            <h5>指导老师：${group.teacher}</h5>
                            <h5>社团负责人：${group.creatorMember.name}</h5>
                            <p class="text-muted" title="${group.introduce}"
                               style="margin: auto;display:block;text-align:center;width:200px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
                                <#if group.introduce?length &gt; 50>
                                    ${group.introduce?substring(0,50)}...
                                <#else>
                                    ${group.introduce}
                                </#if>
                            </p>
                            <small class="text-muted">${group.topicCount}篇社团信息 · ${group.fansCount}人关注</small>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <ul class="pager pagination pagination-sm no-margin pull-right"
            url="${basePath}/group/index"
            currentPage="${model.page.pageNo}"
            pageCount="${model.page.totalPage}">
        </ul>
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
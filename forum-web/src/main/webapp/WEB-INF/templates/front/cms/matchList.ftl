<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>社团比赛 - ${SITE_NAME} - </title>
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
                <div class="items">
                    <#list model.data as match>
                        <div class="item article-list shadow">
                            <div class="item-content">
                                <div class="text">
                                    <h3><a href="#">${match.name}</a></h3>
                                </div>
                                <div class="text">
                                    <p>介绍：${match.introduce}</p>
                                    <p>场地：${match.place}</p>
                                    <p>时间：${match.startTime} - ${match.endTime}</p>
                                    <p>${match.charge}</p>
                                </div>
                            </div>
                        </div>
                    </#list>
                    <ul class="pager pagination pagination-sm no-margin pull-right"
                        url="${basePath}/article/list?key="
                        currentPage="${model.page.pageNo}"
                        pageCount="${model.page.totalPage}">
                    </ul>
                </div>
            </div>
            <div class="col-md-4 float-left">
                <form action="${basePath}/match/list" method="get">
                    <div class="input-group">
                        <input type="text" class="form-control" name="key">
                        <span class="input-group-btn">
                            <button class="btn btn-success" type="submit"><i class="icon-search"></i></button>
                        </span>
                    </div>
                </form>
                <div class="panel">
                    <div class="panel-heading">
                        发布栏目
                        <span class="pull-right">
                            <a class="btn btn-primary m-t-n4" href="${basePath}/article/add">发布社团信息</a>
                        </span>
                    </div>
                    <div class="panel-body">
                        <a href="${basePath}/article/list" class="btn btn-primary">全部</a>
                        <#list articleCateList as articleCate>
                            <a href="${basePath}/article/list?cid=${articleCate.id}"
                               class="btn btn-primary">${articleCate.name}</a>
                        </#list>
                    </div>
                </div>
                <@ads id=1>
                    <#include "/tp/ad.ftl"/>
                </@ads>
                <div class="panel">
                    <div class="panel-heading">
                        热门社团信息
                    </div>
                    <div class="panel-body article-hot-list">
                        <ul>
                            <@cms_article_list cid=0 sort='view_count' num=10 day=30; article>
                                <#list articleList as article>
                                    <li><i class="icon-hand-right main-text-color"></i> <a
                                                href="${basePath}/article/detail/${article.id}">${article.title}</a>
                                    </li>
                                </#list>
                            </@cms_article_list>
                        </ul>
                    </div>
                </div>
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
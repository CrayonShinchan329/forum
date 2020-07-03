<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>社团管理 - ${SITE_NAME} - 高校学生社团管理系统系统- </title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    
    <link href="${basePath}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath}/res/manage/css/AdminLTE.css" rel="stylesheet">
    <link href="${basePath}/res/manage/css/skins/_all-skins.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/res/common/js/html5shiv.js"></script>
    <script src="${basePath}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/res/manage/js/app.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js"></script>
    <script src="${basePath}/res/common/js/extendPagination.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "/manage/common/header.ftl"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>社团经费管理</h1>
            <ol class="breadcrumb">
                <li><a href="${managePath}/index"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">经费管理</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-primary">
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th>申请经费社团名称</th>
                                    <th>申请经费负责人</th>
                                    <th>申请金额·理由·及用途陈述</th>
                                    <th width="200px">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list model.data as ex>
                                    <tr>
                                        <td>${ex.id}</td>
                                        <td>${ex.name}/td>
                                        <td>${ex.applyPeople}</td>
                                        <td>${ex.applyReason}</td>
                                        <td>
                                            <a class="marg-l-5" target="_blank" href="/match/findEx/?id=${ex.id}">
                                                <span class="label label-danger">查看</span>
                                            </a>
                                            <#if ex.status==0>
                                                <a class="marg-l-5 approve" target="_jeesnsLink" href="/match/changeExStatus/?id=${ex.id}&status=1">
                                                    <span class="label label-danger">通过</span>
                                                </a>
                                                <a class="marg-l-5 approve" target="_jeesnsLink" href="/match/changeExStatus/?id=${ex.id}&status=2">
                                                    <span class="label label-danger">驳回</span>
                                                </a>
                                            <#elseif ex.status==1>
                                                <span class="label label-success">已通过</span>
                                            <#elseif ex.status==2>
                                                <span class="label label-success">已驳回</span>
                                            </#if>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="box-footer clearfix">
                            <ul class="pagination pagination-sm no-margin pull-right"
                                url="${managePath}/group/index?key=${key}"
                                currentPage="${model.page.pageNo}"
                                pageCount="${model.page.totalPage}">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <#include "/manage/common/footer.ftl"/>
</div>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
        $(".approve").click(function () {
            setTimeout(function () {
                window.location.reload()
            },500);
        })
    })
</script>
</body>
</html>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>社团详情 - ${SITE_NAME} - 高校学生社团管理系统系统- </title>
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
            <h1>社团详情</h1>
            <ol class="breadcrumb">
                <li><a href="${managePath}/index"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">社团管理</li>
            </ol>
        </section>
        <section class="content">

            <div class="row">
                <div class="box box-primary">
                    <dl class="row">
                        <dt class="col-sm-3 text-right">名称</dt><dd class="col-sm-9">${model.data.name}</dd>
                        <dt class="col-sm-3 text-right">创建人</dt><dd class="col-sm-9">${model.data.creatorMember.name}</dd>
                        <dt class="col-sm-3 text-right">社团类别</dt><dd class="col-sm-9">${model.data.name}</dd>
                        <dt class="col-sm-3 text-right">社团人数</dt><dd class="col-sm-9">${model.data.count}</dd>
                        <dt class="col-sm-3 text-right">指导老师</dt><dd class="col-sm-9">${model.data.teacher}</dd>
                        <dt class="col-sm-3 text-right">指导老师基本情况介绍</dt>
                        <dd class="col-sm-9">${model.data.teacherDes}<br></dd>
                        <dt class="col-sm-3 text-right">申请人基本情况介绍</dt>
                        <dd class="col-sm-9">${model.data.userDes}<br></dd>
                        <dt class="col-sm-3 text-right">筹备申请书</dt>
                        <dd class="col-sm-9">${model.data.prepare}<br></dd>
                        <dt class="col-sm-3 text-right">章程草案</dt>
                        <dd class="col-sm-9">${model.data.design}<br></dd>
                        <dt class="col-sm-3 text-right">介绍</dt>
                        <dd class="col-sm-9">${model.data.introduce}<br></dd>
                    </dl>
                </div>
            </div>
        </section>
    </div>
<#include "/manage/common/footer.ftl"/>
</div>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>


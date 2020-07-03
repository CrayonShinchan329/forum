<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>申请${GROUP_ALIAS} - ${SITE_NAME} - </title>
    <link href="${basePath}/res/common/css/zui.min.css" rel="stylesheet">
    <link href="${basePath}/res/front/css/app.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/res/common/js/html5shiv.js"></script>
    <script src="${basePath}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/zui.min.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/front/js/jeesns.js"></script>
    <script src="${basePath}/res/plugins/webuploader/webuploader.min.js"></script>
    <script src="${basePath}/res/plugins/jquerytags/js/jquery.tags.js"></script>
    <script type="text/javascript">
        var basePath = "${basePath}";
        var uploadServer = "${basePath}/uploadImage";
    </script>
    <script src="${basePath}/res/plugins/webuploader/upload.js"></script>
</head>
<body class="gray-bg">
<#include "/${frontTemplate}/common/header.ftl"/>
<div class="container">
    <div class="main-content">
        <div class="row">
            <div class="col-md-12 article-detail">
                <form class="form-horizontal jeesns_form" role="form" action="${basePath}/match/add" method="post">
                    <input type="text" class="hidden" id="clubId" name="clubId" value="${group.id}" data-type="require">
                    <div class="form-group">
                        <label class="col-sm-1 control-label">比赛名称</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="name" name="name" placeholder="比赛名称" data-type="require">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">简介</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="introduce" name="introduce" placeholder="简介" data-type="require">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-1 control-label">场地</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="place" name="place" placeholder="场地" data-type="require">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">开始时间</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="startTime" name="startTime" placeholder="开始时间" data-type="require">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">结束时间</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="endTime" name="endTime" placeholder="结束时间" data-type="require">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <button type="submit" class="btn btn-info jeesns-submit">申请${GROUP_ALIAS}比赛</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<#include "/${frontTemplate}/common/footer.ftl"/>
<script>
    $(function () {
        $('#inputmanagers').tags();
        setInterval(function () {
            $('#managers').val($('#inputmanagers').val());
        }, 500);
        $('#inputtags').tags();
        setInterval(function () {
            $('#tags').val($('#inputtags').val());

        }, 500);
    });
</script>
</body>
</html>
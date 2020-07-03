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
                <form class="form-horizontal jeesns_form" role="form" action="${basePath}/group/join/push" method="post">
                    <input type="text" class="hidden" id="groupId" name="groupId" value="${group.id}" data-type="require">
                    <div class="form-group">
                        <label class="col-sm-1 control-label">班级</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="sClass" name="sClass" placeholder="班级" data-type="require">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">真实姓名</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="realName" name="realName" placeholder="真实姓名" data-type="require">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">学号</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="sNo" name="sNo" placeholder="学号" data-type="require">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">联系方式</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="phone" name="phone" placeholder="联系方式" data-type="require">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">加入理由</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="reason" name="reason" placeholder="加入理由" data-type="require">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">是否遵守社团章程</label>
                        <div class="col-sm-3">
                            <input class="check-box" type="radio" name="isFollowPrepare" value="0"> 是
                            <input class="check-box" type="radio" name="isFollowPrepare" value="1"> 否
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">是否缴纳会费</label>
                        <div class="col-sm-3">
                            <input class="check-box" type="radio" name="isVip" value="0"> 是
                            <input class="check-box" type="radio" name="isVip" value="1"> 否
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <button type="submit" class="btn btn-info jeesns-submit">申请${GROUP_ALIAS}</button>
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
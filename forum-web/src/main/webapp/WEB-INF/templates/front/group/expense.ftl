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
<form class="form-horizontal jeesns_form" role="form" action="${basePath}/match/addExpense" method="post">
    <div class="form-group">
        <label class="col-sm-1 control-label">申请经费社团名称</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="name" name="name" placeholder="申请经费的社团名称" data-type="require">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">申请经费负责人</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="applyPeople" name="applyPeople" placeholder="申请经费负责人" data-type="require">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">申请金额·理由·及用途陈述</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="applyReason" name="applyReason" placeholder="申请经费理由及用途" data-type="require">
        </div>
    </div>

    <
    <div class="form-group">
        <div class="col-sm-offset-1 col-sm-10">
            <button type="submit" class="btn btn-info jeesns-submit">申请${GROUP_ALIAS}经费</button>
        </div>
    </div>
</form>
</body>
</html>
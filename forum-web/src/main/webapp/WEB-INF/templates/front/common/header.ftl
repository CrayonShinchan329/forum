<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header m-navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">YOU</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand jeesns-logo" href="${basePath}/"><img src="${basePath}${SITE_LOGO}" height="50px"/></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li>
                                <a href="${basePath}/"> 首页</a>
                            </li>
                            <li>
                                <a href="${basePath}/article/list?cid=1">规章制度</a>
                            </li>
                             <li>
                                <a href="${basePath}/article/list?cid=2">公告</a>
                            </li>
                             <li>
                                <a href="${basePath}/article/list?cid=3">高校新闻</a>
                             </li>
                              <li>
                                <a href="${basePath}/match/list">社团比赛</a>
                             </li>
                            <li>
                                <a href="${basePath}/weibo/picturelist">社团联相册</a>
                            </li>
                            <li>
                                <a href="${basePath}/group/">高校社团</a>
                            </li>
                            <li>
                                <a href="${basePath}/picture/">社团图库</a>
                            </li>
                            <li>
                             
                            </li>
            </ul>
            <ul class="nav navbar-top-links navbar-right">
                <div class="nav navbar-nav navbar-nav-right">
                <#if loginUser == null>
                    <li><a href="${basePath}/member/login">登录</a></li>
                    <!--<li><a href="${basePath}/member/register">注册</a></li>-->
                <#else>
                    <div class="btn-group nav-username m-t-10">
                        <img src="${basePath}${loginUser.avatar}" class="img-circle" width="25px" height="25px" style="margin-top: 1px;margin-right:5px;"/>
                        <a class="header-action-link" href="javascript:void(0)">
                        ${loginUser.name}
                            <#if unReadMessageNum+systemUnReadMessageNum &gt; 0><i class="icon-comments"></i></#if>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${basePath}/member/">个人中心</a></li>
                            <li><a href="${basePath}/member/systemMessage">系统信息 ${(systemUnReadMessageNum > 0)?string("("+systemUnReadMessageNum+")","")}</a></li>
                            <li><a href="${basePath}/member/message">私信 ${(unReadMessageNum > 0)?string("("+unReadMessageNum+")","")}</a></li>
                            <li><a href="${basePath}/member/editInfo">设置</a></li>
                            <li><a href="${basePath}/group/myClub">我管理的社团</a></li>
                            <li><a href="${basePath}/group/myJoinClub">我加入的的社团</a></li>
                            <#if loginUser?? && loginUser.isAdmin &gt; 0>
                                <li><a href="${managePath}/" target="_blank">管理</a></li>
                            </#if>
                            <li class="divider"></li>
                            <li><a href="${basePath}/member/logout">退出</a></li>
                        </ul>
                        <script>
                            $(function () {
                                $(".nav-username").hover(function(){
                                    $(this).addClass("open");
                                },function(){
                                    $(this).removeClass("open");
                                });
                            })
                        </script>
                    </div>
                </#if>
                </div>
            </ul>
        </div>
    </div>
</nav>
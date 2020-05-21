<div class="col-sm-3 blog-sidebar">
    <div class="sidebar-module article-module hide" style="top: 0;">
        <h5 class="custom-title"><i class="fa fa-book fa-fw icon"></i><strong>本文目录</strong><i
                    class="fa fa-close pull-right close-article-menu hide pointer"></i>
            <small></small>
        </h5>
        <div id="article-menu">
            <ul class="list-unstyled"></ul>
        </div>
    </div>
    <div class="sidebar-module">
        <h5 class="custom-title"><i class="fa fa-tags fa-fw icon"></i><strong>标签</strong>
            <small></small>
        </h5>
        <@zhydTag method="tagsList" pageSize="10">
            <#if tagsList?exists && (tagsList?size > 0)>
                <#assign buttonClass = ['green button','blue button','red button','magenta button','orange button','orangellow button']>
                <#list tagsList as item>
                    <a class="<@zhydTag method="random" max="6" type="int">${buttonClass[random]}</@zhydTag>" style="font-size: <@zhydTag method="random" max="15" min="10">${random}</@zhydTag>px;margin: 5px;"
                       href="${config.siteUrl}/tag/${item.id?c}" title="${item.name?if_exists}" data-toggle="tooltip"
                       data-placement="bottom">
                        ${item.name?if_exists}
                    </a>
                </#list>
            </#if>
        </@zhydTag>
    </div>
    <@zhydTag method="recentComments" pageSize="10">
        <#if recentComments?? && recentComments?size gt 0>
            <div class="sidebar-module">
                <h5 class="custom-title"><i class="fa fa-comments fa-fw icon"></i><strong>近期评论</strong>
                    <small></small>
                </h5>
                <ul class="list-unstyled list-inline comments">
                    <#list recentComments as item>
                        <li>
                            <a href="${item.sourceUrl}#comment-${item.id?c}" title="${item.briefContent?if_exists}"
                               rel="external nofollow" data-toggle="tooltip" data-placement="bottom">
                                <img alt="${item.nickname?if_exists}" src="${item.avatar?if_exists}"
                                     class="avatar auto-shake" height="64" width="64"
                                     onerror="this.src='${config.staticWebSite}/img/user.png'"/>
                                <span class="comment-author">${item.nickname?if_exists}</span> ${item.briefContent?if_exists}
                            </a>
                        </li>
                    </#list>
                </ul>
            </div>
        </#if>
    </@zhydTag>
    <div class="sidebar-module recommended-tag">
        <div class="tab-content">
            <h5 class="custom-title"><i class="fa fa-tags fa-fw icon"></i><strong>站长推荐</strong>
                <small></small>
            </h5>
            <ol>
                <@articleTag method="recommendedList" pageSize="10">
                    <#if recommendedList?exists && (recommendedList?size > 0)>
                        <#list recommendedList as item>
                            <li>
                                <#if item.coverImage?exists && (item.coverImage?length > 7)>
                                    <span class="thumbnail"> <a href="${config.siteUrl}/article/${item.id?c}"><img
                                                    width="100" height="10" data-original="${item.coverImage}"
                                                    class="img-responsive lazy-img"
                                                    alt="${item.title?if_exists}"></a> </span>
                                <#else>
                                    <span class="thumbnail"> <a href="${config.siteUrl}/article/${item.id?c}"><img
                                                    width="100" height="100"
                                                    data-original="${config.staticWebSite}/img/loading.gif"
                                                    class="img-responsive lazy-img"
                                                    alt="${item.title?if_exists}"></a> </span>
                                </#if>
                                <span class="recommended-tag-title"><a href="{config.siteUrl}/article/${item.id?c}"
                                                                   rel="bookmark">${item.title}</a></span>
                                <span class="recommended-tag-title">
                                        <span class="date" title="文章发表日期" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-clock-o fa-fw"></i>${item.createTime?string('yyyy-MM-dd')}</span>
                                <span class="views" title="文章阅读次数" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-eye fa-fw"></i>浏览(${item.lookCount!(0)})</span>
                                </span>
                            </li>
                        </#list>
                    <#else>
                        <li class="empty-list">
                            <i class="fa fa-bookmark-o fa-fw"></i> 暂无相关文章
                        </li>
                    </#if>
                </@articleTag>
            </ol>
        </div>
    </div>
    <div class="clear"></div>
    <div class="sidebar-module">
        <h5 class="custom-title"><i class="fa fa-info fa-fw icon"></i><strong>网站信息</strong>
            <small></small>
        </h5>
        <ul class="ul-default site-info">
            <@zhydTag method="siteInfo">
                <li><i class="fa fa-file fa-fw"></i> 文章总数：${siteInfo.articleCount!(0)} 篇</li>
                <li><i class="fa fa-tags fa-fw"></i> 标签总数：${siteInfo.tagCount!(0)} 个</li>
                <li><i class="fa fa-folder-open fa-fw"></i> 分类总数：${siteInfo.typeCount!(0)} 个</li>
                <li><i class="fa fa-comments fa-fw"></i> 留言数量：${siteInfo.commentCount!(0)} 条</li>
                <li><i class="fa fa-users fa-fw"></i> 在线人数：<span class="online">1</span>人</li>
                <li><i class="fa fa-calendar fa-fw"></i> 运行天数：${siteInfo.buildSiteDate!(0)}天</li>
                <li><i class="fa fa-pencil-square fa-fw"></i> 最后更新：${siteInfo.lastUpdateTime}</li>
            </@zhydTag>
        </ul>
    </div>
</div>

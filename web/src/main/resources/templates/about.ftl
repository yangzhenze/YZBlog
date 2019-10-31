<#include "include/macros.ftl">
<@compress single_line=true>
<@header title="关于 | ${config.siteName}" keywords="${config.siteName},关于博客" description="一个程序员的个人博客,关于我的个人原创博客 - ${config.siteName}" canonical="/about"></@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>关于
    </nav>
    <div class="row about-body">
        <@blogHeader title="关于本站"></@blogHeader>
        <div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <h5 class="custom-title"><i class="fa fa-user-secret fa-fw"></i><strong>博主简介</strong><small></small></h5>
                <div class="info">
                    <p>
                        码农一枚。无所事事，只能发呆！
                    </p>
                </div>
                <h5 class="custom-title"><i class="fa fa-coffee fa-fw"></i><strong>关于博客</strong><small></small></h5>
                <div class="info">
                    本博客主要分享作者原创的技术文章以及在日常工作和学习中读过的好文。希望通过博客分享的方式为读者精选一些我认为在企业应用架构实践中非常实用的干货内容。<br>
                </div>
                <h5 class="custom-title"><i class="fa fa-copyright fa-fw"></i><strong>关于版权</strong><small></small></h5>
                <div class="info">
                    本站所有标注为原创的文章，转载请标明出处。<br>
                    本站所有转载的文章，一般都会在文章中注明原文出处。<br>
                    所有转载的文章皆来源于互联网，若因此对原作者造成侵权，烦请原作者<a target="_blank" href="mailto:zhenze.yang0607@gmail.com" title="点击给我发邮件" data-toggle="tooltip" data-placement="bottom" rel="external nofollow"><strong>告知</strong></a>，我会立刻删除相关内容。
                </div>
                <@praise></@praise>
            </div>
        </div>
        <#--<div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <div id="comment-box" data-id="-3"></div>
            </div>
        </div>-->
    </div>
</div>

<@footer>
    <script src="https://v1.hitokoto.cn/?encode=js&c=d&select=%23hitokoto" defer></script>
</@footer>
</@compress>

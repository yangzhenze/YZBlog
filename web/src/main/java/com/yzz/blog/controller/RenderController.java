package com.yzz.blog.controller;

import com.github.pagehelper.PageInfo;
import com.yzz.blog.business.annotation.BussinessLog;
import com.yzz.blog.business.entity.Article;
import com.yzz.blog.business.enums.ArticleStatusEnum;
import com.yzz.blog.business.enums.PlatformEnum;
import com.yzz.blog.business.service.BizArticleArchivesService;
import com.yzz.blog.business.service.BizArticleService;
import com.yzz.blog.business.service.SysLinkService;
import com.yzz.blog.business.service.SysUpdateRecordeService;
import com.yzz.blog.business.vo.ArticleConditionVO;
import com.yzz.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 页面跳转类
 */
@Controller
public class RenderController {
    /**
     * sidebar部分的推荐、近期和随机tab页中显示的文章数
     */
    private static final int SIDEBAR_ARTICLE_SIZE = 8;
    private static final String INDEX_URL = "index";
    private static final String REDIS_KEYWORD = "keywords";
    private static final String REDIS_KEYWORD_SORT = "keywordSort";

    @Autowired
    private BizArticleService bizArticleService;
    @Autowired
    private BizArticleArchivesService bizArticleArchivesService;
    @Autowired
    private SysLinkService sysLinkService;
    @Autowired
    private SysUpdateRecordeService updateRecordeService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 加载首页的数据
     *
     * @param vo
     * @param model
     * @return
     */
    private void loadIndexPage(ArticleConditionVO vo, Model model) {
        vo.setStatus(ArticleStatusEnum.PUBLISHED.getCode());
        PageInfo<Article> pageInfo = bizArticleService.findPageBreakByCondition(vo);
        model.addAttribute("page", pageInfo);
        model.addAttribute("model", vo);
        model.addAttribute("indexLinkList", sysLinkService.listOfIndex());
        model.addAttribute(REDIS_KEYWORD_SORT, redisTemplate.opsForZSet().reverseRangeByScore(REDIS_KEYWORD_SORT,0,100000,0,5));


    }

    /**
     * 首页
     *
     * @param vo
     * @param model
     * @return
     */
    @RequestMapping("/")
    @BussinessLog(value = "进入首页", platform = PlatformEnum.WEB)
    public ModelAndView home(ArticleConditionVO vo, Model model) {
        model.addAttribute("url", INDEX_URL);
        long start = System.currentTimeMillis();
        loadIndexPage(vo, model);
        System.out.println("首页耗时：" + (System.currentTimeMillis() - start));

        if(!StringUtils.isEmpty(vo.getKeywords())){
            String keyword = vo.getKeywords().replace(" ","").toLowerCase();
            Integer searchNum = (Integer) redisTemplate.opsForHash().get(REDIS_KEYWORD,keyword);

            if(null == searchNum){
                searchNum = 1;
            }else{
                searchNum+=1;
            }
            redisTemplate.opsForHash().put(REDIS_KEYWORD,keyword,searchNum);
            redisTemplate.opsForZSet().add(REDIS_KEYWORD_SORT,keyword,searchNum);
        }

        return ResultUtil.view(INDEX_URL);
    }

    /**
     * 首页（分页）
     *
     * @param pageNumber
     * @param vo
     * @param model
     * @return
     */
    @RequestMapping("/index/{pageNumber}")
    @BussinessLog(value = "进入文章列表第{1}页", platform = PlatformEnum.WEB)
    public ModelAndView type(@PathVariable("pageNumber") Integer pageNumber, ArticleConditionVO vo, Model model) {
        vo.setPageNumber(pageNumber);
        model.addAttribute("url", INDEX_URL);
        loadIndexPage(vo, model);

        return ResultUtil.view(INDEX_URL);
    }

    /**
     * 分类列表
     *
     * @param typeId
     * @param model
     * @return
     */
    @GetMapping("/type/{typeId}")
    @BussinessLog(value = "进入文章分类[{1}]列表页", platform = PlatformEnum.WEB)
    public ModelAndView type(@PathVariable("typeId") Long typeId, Model model) {
        ArticleConditionVO vo = new ArticleConditionVO();
        vo.setTypeId(typeId);
        model.addAttribute("url", "type/" + typeId);
        loadIndexPage(vo, model);

        return ResultUtil.view(INDEX_URL);
    }

    /**
     * 分类列表（分页）
     *
     * @param typeId
     * @param pageNumber
     * @param model
     * @return
     */
    @GetMapping("/type/{typeId}/{pageNumber}")
    @BussinessLog(value = "进入文章分类[{1}]列表第{2}页", platform = PlatformEnum.WEB)
    public ModelAndView type(@PathVariable("typeId") Long typeId, @PathVariable("pageNumber") Integer pageNumber, Model model) {
        ArticleConditionVO vo = new ArticleConditionVO();
        vo.setTypeId(typeId);
        vo.setPageNumber(pageNumber);
        model.addAttribute("url", "type/" + typeId);
        loadIndexPage(vo, model);

        return ResultUtil.view(INDEX_URL);
    }

    /**
     * 标签列表
     *
     * @param tagId
     * @param model
     * @return
     */
    @GetMapping("/tag/{tagId}")
    @BussinessLog(value = "进入文章标签[{1}]列表页", platform = PlatformEnum.WEB)
    public ModelAndView tag(@PathVariable("tagId") Long tagId, Model model) {
        ArticleConditionVO vo = new ArticleConditionVO();
        vo.setTagId(tagId);
        model.addAttribute("url", "tag/" + tagId);
        loadIndexPage(vo, model);

        return ResultUtil.view(INDEX_URL);
    }

    /**
     * 标签列表（分页）
     *
     * @param tagId
     * @param pageNumber
     * @param model
     * @return
     */
    @GetMapping("/tag/{tagId}/{pageNumber}")
    @BussinessLog(value = "进入文章标签[{1}]列表第{2}页", platform = PlatformEnum.WEB)
    public ModelAndView tag(@PathVariable("tagId") Long tagId, @PathVariable("pageNumber") Integer pageNumber, Model model) {
        ArticleConditionVO vo = new ArticleConditionVO();
        vo.setTagId(tagId);
        vo.setPageNumber(pageNumber);
        model.addAttribute("url", "tag/" + tagId);
        loadIndexPage(vo, model);

        return ResultUtil.view(INDEX_URL);
    }

    /**
     * 文章详情
     *
     * @param model
     * @param articleId
     * @return
     */
    @GetMapping("/article/{articleId}")
    @BussinessLog(value = "进入文章[{2}]详情页", platform = PlatformEnum.WEB)
    public ModelAndView article(Model model, @PathVariable("articleId") Long articleId) {
        Article article = bizArticleService.getByPrimaryKey(articleId);
        if (article == null || ArticleStatusEnum.UNPUBLISHED.getCode() == article.getStatusEnum().getCode()) {
            return ResultUtil.redirect("/error/404");
        }
        model.addAttribute("article", article);
        // 上一篇下一篇
        model.addAttribute("other", bizArticleService.getPrevAndNextArticles(article.getCreateTime()));
        // 相关文章
        model.addAttribute("relatedList", bizArticleService.listRelatedArticle(SIDEBAR_ARTICLE_SIZE, article));
        model.addAttribute("articleDetail", true);
        return ResultUtil.view("article");
    }

    /**
     * 关于
     *
     * @return
     */
    @GetMapping("/about")
    @BussinessLog(value = "进入关于页", platform = PlatformEnum.WEB)
    public ModelAndView about() {
        return ResultUtil.view("about");
    }

    /**
     * 友情链接
     *
     * @param model
     * @return
     */
    @GetMapping("/links")
    @BussinessLog(value = "进入友情链接页", platform = PlatformEnum.WEB)
    public ModelAndView links(Model model) {
        model.addAttribute("link", sysLinkService.listAllByGroup());
        return ResultUtil.view("links");
    }

    /**
     * 留言板
     *
     * @return
     */
    @GetMapping("/guestbook")
    @BussinessLog(value = "进入留言板页", platform = PlatformEnum.WEB)
    public ModelAndView guestbook() {
        return ResultUtil.view("guestbook");
    }

    /**
     * 归档目录
     *
     * @param model
     * @return
     */
    @GetMapping("/archives")
    @BussinessLog(value = "进入归档目录页", platform = PlatformEnum.WEB)
    public ModelAndView archives(Model model) {
        Map<String, List> map = bizArticleArchivesService.listArchives();
        model.addAttribute("archives", map);
        return ResultUtil.view("archives");
    }

    /**
     * 免责声明
     *
     * @return
     */
    @GetMapping("/disclaimer")
    @BussinessLog(value = "进入免责声明页", platform = PlatformEnum.WEB)
    public ModelAndView disclaimer() {
        return ResultUtil.view("disclaimer");
    }

    /**
     * 站长推荐
     *
     * @param model
     * @return
     */
    @GetMapping("/recommended")
    @BussinessLog(value = "进入站长推荐页", platform = PlatformEnum.WEB)
    public ModelAndView recommended(Model model) {
        model.addAttribute("list", bizArticleService.listRecommended(100));
        return ResultUtil.view("recommended");
    }

    /**
     * 更新日志
     *
     * @param model
     * @return
     */
    @GetMapping("/updateLog")
    @BussinessLog(value = "进入更新记录页", platform = PlatformEnum.WEB)
    public ModelAndView updateLog(Model model) {
        model.addAttribute("list", updateRecordeService.listAll());
        return ResultUtil.view("updateLog");
    }

    /**
     * 测试websocket
     *
     * @return
     */
    @GetMapping("/testWebsocket")
    @BussinessLog(value = "进入测试websocket页", platform = PlatformEnum.WEB)
    public ModelAndView testWebsocket() {
        return new ModelAndView("testWebsocket");
    }

}

package com.yzz.blog.business.vo;

import com.yzz.blog.business.entity.ArticleTags;
import com.yzz.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleTagsConditionVO extends BaseConditionVO {
	private ArticleTags articleTags;
}


package com.yzz.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.yzz.blog.business.entity.Tags;
import com.yzz.blog.business.vo.TagsConditionVO;
import com.yzz.blog.framework.object.AbstractService;

/**
 * 标签
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface BizTagsService extends AbstractService<Tags, Long> {

    PageInfo<Tags> findPageBreakByCondition(TagsConditionVO vo);

    Tags getByName(String name);
}

package com.yzz.blog.persistence.mapper;

import com.yzz.blog.business.vo.ArticleLookConditionVO;
import com.yzz.blog.persistence.beans.BizArticleLook;
import com.yzz.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Repository
public interface BizArticleLookMapper extends BaseMapper<BizArticleLook>{

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<BizArticleLook> findPageBreakByCondition(ArticleLookConditionVO vo);
}

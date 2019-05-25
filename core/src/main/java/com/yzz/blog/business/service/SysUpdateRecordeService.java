package com.yzz.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.yzz.blog.business.entity.UpdateRecorde;
import com.yzz.blog.business.vo.UpdateRecordeConditionVO;
import com.yzz.blog.framework.object.AbstractService;

/**
 * 更新记录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface SysUpdateRecordeService extends AbstractService<UpdateRecorde, Long> {

    PageInfo<UpdateRecorde> findPageBreakByCondition(UpdateRecordeConditionVO vo);
}

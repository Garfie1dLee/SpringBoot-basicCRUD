package com.yu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yu.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.pojo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author Garfi
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-03-17 03:30:01
* @Entity com.yu.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectMyPage(IPage page, @Param("portalVo")PortalVo portalVo);

    Map selectHeadline(Integer hid);
}





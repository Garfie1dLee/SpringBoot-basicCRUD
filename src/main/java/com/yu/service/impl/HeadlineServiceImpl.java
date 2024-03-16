package com.yu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.mapper.UserMapper;
import com.yu.pojo.Headline;
import com.yu.pojo.PortalVo;
import com.yu.pojo.User;
import com.yu.service.HeadlineService;
import com.yu.mapper.HeadlineMapper;
import com.yu.utils.JwtHelper;
import com.yu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author Garfi
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-03-17 03:30:01
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HeadlineMapper headlineMapper;
    @Override
    public Result findNewsPage(PortalVo portalvo) {
        IPage<Map> page=new Page<>();
        page.setCurrent(portalvo.getPageNum());
        page.setSize(portalvo.getPageSize());
        IPage<Map> mapIPage = headlineMapper.selectMyPage(page, portalvo);
        Map map =new HashMap<>();
        map.put("pageData",mapIPage.getRecords());
        map.put("pageNum",mapIPage.getCurrent());
        map.put("pageSize",mapIPage.getSize());
        map.put("totalPage",mapIPage.getPages());
        map.put("totalSize",mapIPage.getTotal());
        Map result=new HashMap<>();
        result.put("pageInfo",map);
        return Result.ok(result);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {

        Map data = headlineMapper.selectHeadline(hid);
        Map result=new HashMap<>();
        result.put("headline",data);
        Headline headline=new Headline();
        headline.setHid(hid);
        headline.setVersion((Integer) data.get("version"));
        headline.setPageViews((Integer) data.get("pageViews")+1);
        headlineMapper.updateById(headline);
        return Result.ok(result);

    }

    @Override
    public Result publish(String token,Headline headline) {
        Integer id= Math.toIntExact(jwtHelper.getUserId(token));
        headline.setPublisher(id);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map map=new HashMap();
        map.put("headline",headline);
        return Result.ok(map);
    }

    @Override
    public Result updateHeadline(Headline headline) {
        int hid=headline.getHid();
        Headline headline1 = headlineMapper.selectById(hid);
        headline.setVersion(headline1.getVersion());
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }

    @Override
    public Result removeByHid(Integer hid) {
        int i = headlineMapper.deleteById(hid);
        return Result.ok(null);
    }


}





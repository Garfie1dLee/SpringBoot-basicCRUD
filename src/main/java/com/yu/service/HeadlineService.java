package com.yu.service;

import com.yu.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.pojo.PortalVo;
import com.yu.utils.Result;

/**
* @author Garfi
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-03-17 03:30:01
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalvo);

    Result showHeadlineDetail(Integer hid);

    Result publish(String token,Headline headline);

    Result findHeadlineByHid(Integer hid);


    Result updateHeadline(Headline headline);

    Result removeByHid(Integer hid);
}

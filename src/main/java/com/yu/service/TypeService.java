package com.yu.service;

import com.yu.pojo.PortalVo;
import com.yu.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.utils.Result;

/**
* @author Garfi
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-03-17 03:30:01
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();


}

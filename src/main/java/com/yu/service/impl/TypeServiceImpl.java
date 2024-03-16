package com.yu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.pojo.PortalVo;
import com.yu.pojo.Type;
import com.yu.service.TypeService;
import com.yu.mapper.TypeMapper;
import com.yu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author Garfi
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-03-17 03:30:01
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;
    @Override
    public Result findAllTypes() {

        return Result.ok(typeMapper.selectList(null));
    }


}





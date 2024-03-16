package com.yu.pojo;

import lombok.Data;

@Data
public class PortalVo {
    private String KeyWords;

    private Integer type=0;

    private  Integer pageNum=1;

    private Integer pageSize=10;
}

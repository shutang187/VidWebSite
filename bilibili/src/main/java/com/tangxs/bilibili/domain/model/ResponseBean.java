package com.tangxs.bilibili.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tangxs
 * @Description 与前端交互实体类
 * @Date 2023/10/2 17:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean<T> {
    private int code;
    private String msg;
    private String reqId;
    private T data;
}

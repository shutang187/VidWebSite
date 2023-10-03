package com.tangxs.bilibili.domain.vo;

import lombok.Data;

/**
 * @Author tangxs
 * @Description
 * @Date 2023/10/3 19:24
 **/
@Data
public class LoginUserVo {

    /**
     * 手机号
     */
    private String phone;


    /**
     * 密码
     */
    private String password;

}

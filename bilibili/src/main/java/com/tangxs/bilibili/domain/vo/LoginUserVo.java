package com.tangxs.bilibili.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author tangxs
 * @Description
 * @Date 2023/10/3 19:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo implements Serializable {

    /**
     * 手机号
     */
    private String phone;


    /**
     * 密码
     */
    private String password;

}

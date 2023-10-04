package com.tangxs.bilibili.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author tangxs
 * @Description
 * @Date 2023/10/3 17:49
 **/
@Data
public class UserInfoVo implements Serializable {


    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 签名
     */
    private String sign;

    /**
     * 性别：0男，1女，2未知
     */
    private String gender;

    /**
     * 生日
     */
    private String birth;

}

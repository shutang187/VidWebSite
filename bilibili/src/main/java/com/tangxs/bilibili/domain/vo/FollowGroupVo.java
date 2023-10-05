package com.tangxs.bilibili.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author tangxs
 * @Description //TODO
 * @Date 2023/10/5 14:07
 **/
@Data
public class FollowGroupVo implements Serializable {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 关注分组名称
     */

    private String name;


    /**
     * 关注分组类型：0特别关注，1悄悄关注，2默认关注，3用户自定义关注
     */
    private String type;

    private static final long serialVersionUID = 1L;

}

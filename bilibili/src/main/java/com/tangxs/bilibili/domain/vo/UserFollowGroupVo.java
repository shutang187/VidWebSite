package com.tangxs.bilibili.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author tangxs
 * @Description //TODO
 * @Date 2023/10/4 22:16
 **/
@Data
public class UserFollowGroupVo implements Serializable {

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
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    private String type;

    private List<UserInfoVo> followingUserInfoList;

    private static final long serialVersionUID = 1L;
}

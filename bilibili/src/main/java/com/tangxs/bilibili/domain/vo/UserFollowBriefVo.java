package com.tangxs.bilibili.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author tangxs
 * @Description //TODO
 * @Date 2023/10/5 13:32
 **/
@Data
public class UserFollowBriefVo implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 关注用户id
     */
    private Long followingId;

    /**
     * 关注分组id
     */
    private Long groupId;

}

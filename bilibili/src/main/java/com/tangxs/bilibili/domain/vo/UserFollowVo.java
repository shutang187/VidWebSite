package com.tangxs.bilibili.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author tangxs
 * @Description
 * @Date 2023/10/4 21:46
 **/
@Data
public class UserFollowVo implements Serializable {

    /**
     * 主键id
     */
    private Long id;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private UserInfoVo userInfoVo;

    private static final long serialVersionUID = 1L;

}

package com.tangxs.bilibili.domain.vo;

import com.amazonaws.services.s3.model.PartSummary;
import com.tangxs.bilibili.domain.dao.SysUploadTask;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author tangxs
 * @Description
 * @Date 2023/10/6 22:15
 **/
@Data
@Accessors(chain = true)
public class TaskInfoVo {

    /**
     * 是否完成上传（是否已经合并分片）
     */
    private boolean finished;

    /**
     * 文件地址
     */
    private String path;

    /**
     * 已上传完的分片
     */
    private List<PartSummary> exitPartList;

    /**
     * 上传记录
     */
    private SysUploadTask taskRecord;
}

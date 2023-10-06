package com.tangxs.bilibili.service;

import com.tangxs.bilibili.domain.dao.SysUploadTask;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tangxs.bilibili.domain.vo.TaskInfoVo;
import com.tangxs.bilibili.domain.vo.UploadTaskVo;

import java.util.Map;

/**
* @author tangxs
* @description 针对表【t_sys_upload_task(分片上传-分片任务记录)】的数据库操作Service
* @createDate 2023-10-06 21:41:58
*/
public interface SysUploadTaskService extends IService<SysUploadTask> {

    /**
     * 根据md5标识获取分片上传任务
     * @param identifier
     * @return
     */
    SysUploadTask getByIdentifier (String identifier);

    /**
     * 初始化一个任务
     */
    TaskInfoVo initTask (UploadTaskVo uploadTaskVo);

    /**
     * 获取文件地址
     * @param bucket
     * @param objectKey
     * @return
     */
    String getPath (String bucket, String objectKey);

    /**
     * 获取上传进度
     * @param identifier
     * @return
     */
    TaskInfoVo getTaskInfo (String identifier);

    /**
     * 生成预签名上传url
     * @param bucket 桶名
     * @param objectKey 对象的key
     * @param params 额外的参数
     * @return
     */
    String genPreSignUploadUrl (String bucket, String objectKey, Map<String, String> params);

    /**
     * 合并分片
     * @param identifier
     */
    void merge (String identifier);

}

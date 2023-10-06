package com.tangxs.bilibili.domain.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分片上传-分片任务记录
 * @TableName t_sys_upload_task
 */
@TableName(value ="t_sys_upload_task")
@Data
@Accessors(chain = true)
public class SysUploadTask implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 分片上传的uploadId
     */
    @TableField(value = "upload_id")
    private String uploadId;

    /**
     * 文件唯一标识（md5）
     */
    @TableField(value = "file_identifier")
    private String fileIdentifier;

    /**
     * 文件名
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 所属桶名
     */
    @TableField(value = "bucket_name")
    private String bucketName;

    /**
     * 文件的key
     */
    @TableField(value = "object_key")
    private String objectKey;

    /**
     * 文件大小（byte）
     */
    @TableField(value = "total_size")
    private Long totalSize;

    /**
     * 每个分片大小（byte）
     */
    @TableField(value = "chunk_size")
    private Long chunkSize;

    /**
     * 分片数量
     */
    @TableField(value = "chunk_num")
    private Integer chunkNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysUploadTask other = (SysUploadTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUploadId() == null ? other.getUploadId() == null : this.getUploadId().equals(other.getUploadId()))
            && (this.getFileIdentifier() == null ? other.getFileIdentifier() == null : this.getFileIdentifier().equals(other.getFileIdentifier()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getBucketName() == null ? other.getBucketName() == null : this.getBucketName().equals(other.getBucketName()))
            && (this.getObjectKey() == null ? other.getObjectKey() == null : this.getObjectKey().equals(other.getObjectKey()))
            && (this.getTotalSize() == null ? other.getTotalSize() == null : this.getTotalSize().equals(other.getTotalSize()))
            && (this.getChunkSize() == null ? other.getChunkSize() == null : this.getChunkSize().equals(other.getChunkSize()))
            && (this.getChunkNum() == null ? other.getChunkNum() == null : this.getChunkNum().equals(other.getChunkNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUploadId() == null) ? 0 : getUploadId().hashCode());
        result = prime * result + ((getFileIdentifier() == null) ? 0 : getFileIdentifier().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getBucketName() == null) ? 0 : getBucketName().hashCode());
        result = prime * result + ((getObjectKey() == null) ? 0 : getObjectKey().hashCode());
        result = prime * result + ((getTotalSize() == null) ? 0 : getTotalSize().hashCode());
        result = prime * result + ((getChunkSize() == null) ? 0 : getChunkSize().hashCode());
        result = prime * result + ((getChunkNum() == null) ? 0 : getChunkNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uploadId=").append(uploadId);
        sb.append(", fileIdentifier=").append(fileIdentifier);
        sb.append(", fileName=").append(fileName);
        sb.append(", bucketName=").append(bucketName);
        sb.append(", objectKey=").append(objectKey);
        sb.append(", totalSize=").append(totalSize);
        sb.append(", chunkSize=").append(chunkSize);
        sb.append(", chunkNum=").append(chunkNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
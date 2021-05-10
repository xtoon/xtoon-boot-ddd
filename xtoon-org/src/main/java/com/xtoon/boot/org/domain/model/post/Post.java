package com.xtoon.boot.org.domain.model.post;

import com.xtoon.boot.common.domain.Entity;
import com.xtoon.boot.common.domain.StatusEnum;

/**
 * 岗位
 *
 * @author haoxin
 * @date 2021-05-08
 **/
public class Post implements Entity<Post> {

    /**
     * 岗位ID
     */
    private PostId postId;

    /**
     * 岗位名称
     */
    private PostName postName;

    /**
     * 岗位编码
     */
    private PostCode postCode;

    /**
     * 岗位类型
     */
    private String postType;

    /**
     * 排序
     */
    private int orderNum;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private StatusEnum status;

    public Post(PostId postId, PostName postName, PostCode postCode, String postType, int orderNum, String remarks, StatusEnum status) {
        this.postId = postId;
        this.postName = postName;
        this.postCode = postCode;
        this.postType = postType;
        this.orderNum = orderNum;
        this.remarks = remarks;
        this.status = status;
    }

    @Override
    public boolean sameIdentityAs(Post other) {
        return other != null && postId.sameValueAs(other.postId);
    }
}

package com.diy.community.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Comment.java
 * @Description 评论实体类
 */
@Data
public class Comment {
    private int id;
    private int userId;
    private int entityType;
    private int entityId;
    private int targetId;
    private String content;
    private int status;
    private Date createTime;
}

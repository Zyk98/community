package com.diy.community.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Message.java
 * @Description 私信实体类
 */
@Data
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String conversationId;
    private String content;
    private int status;
    private Date createTime;
}

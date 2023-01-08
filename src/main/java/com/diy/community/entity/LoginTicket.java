package com.diy.community.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author coolsen
 * @version 1.0.0
 * @ClassName LoginTicket.java
 * @Description 登录凭证
 * @createTime 4/29/2020 6:14 PM
 */
@Data
public class LoginTicket {
    private int id;
    private int userId;
    private String ticket;
    private int status;
    private Date expired;
}

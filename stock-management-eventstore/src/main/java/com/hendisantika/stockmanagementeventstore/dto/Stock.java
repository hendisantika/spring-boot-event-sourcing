package com.hendisantika.stockmanagementeventstore.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-event-sourcing
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/16/2023
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
@Data
public class Stock {

    private String name;

    private int quantity;

    private String user;
}

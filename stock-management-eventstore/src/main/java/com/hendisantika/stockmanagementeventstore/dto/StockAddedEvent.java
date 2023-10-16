package com.hendisantika.stockmanagementeventstore.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-event-sourcing
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/16/2023
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Data
public class StockAddedEvent implements StockEvent {
    private Stock stockDetails;
}

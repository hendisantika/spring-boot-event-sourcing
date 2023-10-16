package com.hendisantika.stockmanagementeventstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hendisantika.stockmanagementeventstore.dto.Stock;
import com.hendisantika.stockmanagementeventstore.dto.StockAddedEvent;
import com.hendisantika.stockmanagementeventstore.dto.StockRemovedEvent;
import com.hendisantika.stockmanagementeventstore.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-event-sourcing
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/16/2023
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final EventService eventService;

    @PostMapping("/stocks")
    public void addStock(@RequestBody Stock stockRequest) throws JsonProcessingException {
        StockAddedEvent event = StockAddedEvent.builder().stockDetails(stockRequest).build();
        eventService.addEvent(event);
    }

    @DeleteMapping("/stocks")
    public void removeStock(@RequestBody Stock stock) throws JsonProcessingException {
        StockRemovedEvent event = StockRemovedEvent.builder().stockDetails(stock).build();
        eventService.addEvent(event);
    }
}

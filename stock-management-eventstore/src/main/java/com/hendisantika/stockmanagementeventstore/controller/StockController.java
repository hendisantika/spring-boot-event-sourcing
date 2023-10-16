package com.hendisantika.stockmanagementeventstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.hendisantika.stockmanagementeventstore.dto.Stock;
import com.hendisantika.stockmanagementeventstore.dto.StockAddedEvent;
import com.hendisantika.stockmanagementeventstore.dto.StockRemovedEvent;
import com.hendisantika.stockmanagementeventstore.entity.EventStore;
import com.hendisantika.stockmanagementeventstore.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/stocks")
public class StockController {

    private final EventService eventService;

    @PostMapping
    public EventStore addStock(@RequestBody Stock stockRequest) throws JsonProcessingException {
        StockAddedEvent event = StockAddedEvent.builder().stockDetails(stockRequest).build();
        return eventService.addEvent(event);
    }

    @DeleteMapping
    public void removeStock(@RequestBody Stock stock) throws JsonProcessingException {
        StockRemovedEvent event = StockRemovedEvent.builder().stockDetails(stock).build();
        eventService.addEvent(event);
    }

    @GetMapping
    public Stock getStock(@RequestParam(value = "name", required = false) String name) throws JsonProcessingException {
        Iterable<EventStore> events = eventService.fetchAllEvents(name);

        Stock currentStock = new Stock();

        currentStock.setName(name);
        currentStock.setUser("NA");

        for (EventStore event : events) {

            Stock stock = new Gson().fromJson(event.getEventData(), Stock.class);

            if (event.getEventType().equals("STOCK_ADDED")) {

                currentStock.setQuantity(currentStock.getQuantity() + stock.getQuantity());
            } else if (event.getEventType().equals("STOCK_REMOVED")) {

                currentStock.setQuantity(currentStock.getQuantity() - stock.getQuantity());
            }
        }

        return currentStock;
    }

    @GetMapping("/events")
    public Iterable<EventStore> getEvents(@RequestParam("name") String name) throws JsonProcessingException {
        return eventService.fetchAllEvents(name);
    }
}

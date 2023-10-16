package com.hendisantika.stockmanagementeventstore.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.stockmanagementeventstore.dto.StockAddedEvent;
import com.hendisantika.stockmanagementeventstore.dto.StockRemovedEvent;
import com.hendisantika.stockmanagementeventstore.entity.EventStore;
import com.hendisantika.stockmanagementeventstore.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository eventRepository;

    public void addEvent(StockAddedEvent event) throws JsonProcessingException {

        EventStore eventStore = new EventStore();

        eventStore.setEventData(new ObjectMapper().writeValueAsString(event.getStockDetails()));

        eventStore.setEventType("STOCK_ADDED");

        eventStore.setEntityId(event.getStockDetails().getName());

        eventStore.setEventTime(LocalDateTime.now());

        eventRepository.save(eventStore);
    }

    public void addEvent(StockRemovedEvent event) throws JsonProcessingException {

        EventStore eventStore = new EventStore();

        eventStore.setEventData(new ObjectMapper().writeValueAsString(event.getStockDetails()));

        eventStore.setEventType("STOCK_REMOVED");
        eventStore.setEntityId(event.getStockDetails().getName());

        eventStore.setEventTime(LocalDateTime.now());

        eventRepository.save(eventStore);
    }
}

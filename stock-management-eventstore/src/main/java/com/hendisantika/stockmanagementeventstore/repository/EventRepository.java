package com.hendisantika.stockmanagementeventstore.repository;

import com.hendisantika.stockmanagementeventstore.entity.EventStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-event-sourcing
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/16/2023
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public interface EventRepository extends CrudRepository<EventStore, Long> {
    Iterable<EventStore> findByEntityId(String entityId);

    Iterable<EventStore> findByEntityIdAndEventTimeLessThanEqual(String entityId, LocalDateTime date);
}

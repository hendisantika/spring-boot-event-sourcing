package com.hendisantika.stockmanagementtraditional.repository;

import com.hendisantika.stockmanagementtraditional.entity.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-event-sourcing
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/16/2023
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public interface StockRepository extends CrudRepository<Stock, Integer> {

    List<Stock> findByName(String name);
}

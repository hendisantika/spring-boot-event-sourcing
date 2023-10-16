package com.hendisantika.stockmanagementtraditional.controller;

import com.hendisantika.stockmanagementtraditional.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-event-sourcing
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/16/2023
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockRepository stockRepository;
}

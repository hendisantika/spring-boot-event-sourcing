package com.hendisantika.stockmanagementtraditional.controller;

import com.hendisantika.stockmanagementtraditional.entity.Stock;
import com.hendisantika.stockmanagementtraditional.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/stock")
    public void addStock(@RequestBody Stock stock) {
        List<Stock> existingStockList = stockRepository.findByName(stock.getName());

        if (existingStockList != null && !existingStockList.isEmpty()) {
            Stock existingStock = existingStockList.get(0);

            int newQuantity = existingStock.getQuantity() + stock.getQuantity();

            existingStock.setQuantity(newQuantity);
            existingStock.setAddedBy(stock.getAddedBy());
            stockRepository.save(existingStock);
        } else {
            stockRepository.save(stock);
        }
    }

    @DeleteMapping("/stock")
    public void removeStock(@RequestBody Stock stock) {
        int newQuantity = 0;

        List<Stock> existingStockList = stockRepository.findByName(stock.getName());

        if (existingStockList != null && existingStockList.size() > 0) {

            Stock existingStock = existingStockList.get(0);

            newQuantity = existingStock.getQuantity() - stock.getQuantity();

            if (newQuantity <= 0) {
                stockRepository.delete(existingStock);
            } else {
                existingStock.setQuantity(newQuantity);
                existingStock.setAddedBy(stock.getAddedBy());
                stockRepository.save(existingStock);
            }
        }
    }

    @GetMapping("/stock")
    public List<Stock> getStock(@RequestParam("name") String name) {
        return stockRepository.findByName(name);
    }
}

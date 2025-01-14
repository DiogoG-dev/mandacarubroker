package com.mandacarubroker.controller;


import com.mandacarubroker.domain.stock.*;
import com.mandacarubroker.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    public Stock getStockById(@PathVariable String id) {
        Stock stock = stockService.getStockById(id).orElse(null);
        return stock != null ? ResponseEntity.ok(stock) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody @Valid RequestStockDTO data) {
        Stock createdStock = stockService.createStock(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }

    @PutMapping("/{id}")
    public Stock updateStock(@PathVariable String id, @RequestBody Stock updatedStock) {
        return stockService.updateStock(id, updatedStock).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable String id) {
        stockService.deleteStock(id);
    }

}

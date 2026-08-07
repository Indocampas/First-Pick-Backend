package com.hragro.backend.controller;

// Temporarily comment out WebSocket controller
/*
import com.hragro.backend.model.Product;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/product/update")
    @SendTo("/topic/products")
    public Product productUpdate(Product product) {
        return product;
    }

    @MessageMapping("/product/delete")
    @SendTo("/topic/products")
    public String productDelete(String productId) {
        return productId;
    }

    @MessageMapping("/franchise/update")
    @SendTo("/topic/franchise")
    public Object franchiseUpdate(Object update) {
        return update;
    }
}
*/
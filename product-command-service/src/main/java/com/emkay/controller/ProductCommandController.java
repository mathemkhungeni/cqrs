package com.emkay.controller;

import com.emkay.dto.ProductEvent;
import com.emkay.entity.Product;
import com.emkay.service.ProductCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductCommandController {

    private final ProductCommandService commandService;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent productEvent) {
        return commandService.createProduct(productEvent);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody ProductEvent productEvent) {
        return commandService.updateProduct(id, productEvent);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable long id) {
        return commandService.deleteProduct(id);
    }
}

package com.emkay.service;

import com.emkay.dto.ProductEvent;
import com.emkay.entity.Product;
import com.emkay.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCommandService {

    private final ProductRepository repository;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public Product createProduct(ProductEvent productEvent){
        Product productDO = repository.save(productEvent.getProduct());
        ProductEvent event=new ProductEvent("CreateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);
        return productDO;
    }

    public Product updateProduct(long id,ProductEvent productEvent){
        Product existingProduct = repository.findById(id).get();
        Product newProduct=productEvent.getProduct();
        existingProduct.setName(newProduct.getName());
        existingProduct.setPrice(newProduct.getPrice());
        existingProduct.setDescription(newProduct.getDescription());
        Product productDO = repository.save(existingProduct);
        ProductEvent event=new ProductEvent("UpdateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);
        return productDO;
    }

    public Product deleteProduct(long id) {
        Product existingProduct = repository.findById(id).get();
        ProductEvent event=new ProductEvent("DeleteProduct", existingProduct);
        repository.delete(existingProduct);
        kafkaTemplate.send("product-event-topic", event);
        return existingProduct;
    }
}

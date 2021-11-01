package com.master.examples.drools.controller;

import com.master.examples.drools.model.Product;
import com.master.examples.drools.service.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductServiceImp productServiceImp;

    @Autowired
    public ProductController(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }

    @PostMapping("/new/product")
    public ResponseEntity<?> addProduct(@RequestParam(name = "vehicle", required = false) boolean vehicle,
                                        @RequestParam(name = "property", required = false) boolean property,
                                        @RequestParam(name = "life", required = false) boolean life,
                                        @RequestParam(name = "productName") String productName) {
        Collection<Product> collection = (Collection<Product>) productServiceImp.addProducts(vehicle, property, life, productName);
        if (collection.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            productServiceImp.saveProducts(collection);
            return ResponseEntity.status(HttpStatus.CREATED).body(collection);
        }
    }

    @GetMapping("/products")
    public Collection<?> getAllProducts() {
        return productServiceImp.getAll();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable long productId) {
        return productServiceImp
                .getById(productId)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}


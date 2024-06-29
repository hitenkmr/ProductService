package com.scaler.productservice.controllers;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping()
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}

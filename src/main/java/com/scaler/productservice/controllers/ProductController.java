package com.scaler.productservice.controllers;

import com.scaler.productservice.controlleradvice.GlobalExceptionHandler;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    GlobalExceptionHandler globalExceptionHandler;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        //        throw new RuntimeException("Something went wrong");
        //        ResponseEntity<Product> responseEntity = null;
        //
        //        try {
        //            Product product = productService.getSingleProduct(id);
        //
        //            responseEntity = new ResponseEntity<>(
        //                    product,
        //                    HttpStatus.OK
        //            );
        //        } catch (RuntimeException e) {
        //            responseEntity = new ResponseEntity<>(
        //                    HttpStatus.NOT_FOUND
        //            );
        //        }

        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );

        return response;
    }

    @GetMapping()
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PatchMapping("/{id}")
    Product updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }
}

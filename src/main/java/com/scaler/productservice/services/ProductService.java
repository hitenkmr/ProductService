package com.scaler.productservice.services;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
     Product getSingleProduct(long id) throws ProductNotFoundException;
     List<Product> getAllProducts();
     Product updateProduct(Long id, Product product);
     Product replaceProduct(Long id, Product product);
     Product deleteProduct(Long id);
}

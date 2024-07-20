package com.scaler.productservice.services;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public interface ProductService {
     Product getSingleProduct(long id) throws ProductNotFoundException;
     List<Product> getAllProducts();
     Product updateProduct(Long id, Product product) throws ProductNotFoundException;
     Product replaceProduct(Long id, Product product) throws ProductNotFoundException;
     void deleteProduct(Long id) throws ProductNotFoundException;
     Product addNewProduct(Product product);
}

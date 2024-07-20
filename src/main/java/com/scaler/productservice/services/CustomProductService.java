package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("CustomProductService")
public class CustomProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    CustomProductService(ProductRepository productRepository,
                         CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException("product with id: " + id + " does not exist");
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id : " + id + " doesn't exist");
        }

        Product productInDB = optionalProduct.get();

        // In upadte / or PATCH request only request attributes
        // that are not null will be updated other attributes that are coming null will
        // remain as it is
        if (product.getTitle() != null) {
            productInDB.setTitle(product.getTitle());
        }

        productInDB.setPrice(product.getPrice());

        return productRepository.save(productInDB);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id : " + id + " doesn't exist");
        }
        Product productInDB = optionalProduct.get();

        // In PUT request(replace model) attributes that are coming will be updated
        // other attributes not coming will be set to null so do not add null check
        // on incoming model attributes
        productInDB.setTitle(product.getTitle());
        productInDB.setPrice(product.getPrice());

        return productRepository.save(productInDB);
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product addNewProduct(Product product) {
        Category category = product.getCategory();

        if (category.getId() == 0) {
            // We need to create a new Category object in the DB first.
            category = categoryRepository.save(category);
            product.setCategory(category);
        }

        return productRepository.save(product);
    }
}

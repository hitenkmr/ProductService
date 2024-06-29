package com.scaler.productservice.services;

import com.scaler.productservice.DTO.FakeStoreProductDTO;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//https://fakestoreapi.com/docs

@Service
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long id) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);

        return this.convertFakeStoreProductDTOtoProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] fakeProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDTO[].class
        );

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDTO fakeProduct: fakeProducts) {
            products.add(convertFakeStoreProductDTOtoProduct(fakeProduct));
        }
        return products;
    }

    // convert FakeStoreProductDTO to Product
    private Product convertFakeStoreProductDTOtoProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setTitle(fakeStoreProductDTO.getTitle());

        Category category = new Category();
        category.setDescription(fakeStoreProductDTO.getCategory());
        product.setCategory(category);

        return product;
    }
}

package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.model.Category;
import de.telran.onlineshop.model.Product;
import de.telran.onlineshop.repository.ProductsRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;
    private List<Product> productList;

    @PostConstruct
    void initProducts() {

        long currentMillis = System.currentTimeMillis();

        // Преобразование миллисекунд в Timestamp
        Timestamp timestamp = new Timestamp(currentMillis);

        Timestamp dateCr = timestamp;
        Timestamp dateUp = timestamp;

        ProductsEntity productsEntity1 = new ProductsEntity((Long) null, "Milk", "Fresh liter of milk", 1.2, 1L, "https://example.com/milk.jpg", 0.0, dateCr, dateUp);
        productsRepository.save(productsEntity1);
        ProductsEntity productsEntity2 = new ProductsEntity(null, "Bread", "Fresh loaf of bread", 0.8, 1L, "https://example.com/bread.jpg", 10.0, dateCr, dateUp);
        productsRepository.save(productsEntity2);

        ProductsEntity productsEntity3 = new ProductsEntity((Long) null, "Dishwashing Liquid", "For cleaning dishes", 2.5, 2L, "https://example.com/detergent.jpg", 5.0, dateCr, dateUp);
        productsRepository.save(productsEntity3);
        ProductsEntity productsEntity4 = new ProductsEntity(null,"Glass Cleaner", "Effective glass cleaner", 3.0, 2L, "https://example.com/glass_cleaner.jpg", 0.0, dateCr, dateUp);
        productsRepository.save(productsEntity4);

        ProductsEntity productsEntity5 = new ProductsEntity((Long) null,"Television", "LED TV", 150.0, 3L, "https://example.com/tv.jpg", 3.5, dateCr, dateUp);
        productsRepository.save(productsEntity5);
        ProductsEntity productsEntity6 = new ProductsEntity(null,"Radio", "FM radio receiver", 30.0, 3L, "https://example.com/radio.jpg", 3.5, dateCr, dateUp);
        productsRepository.save(productsEntity6);

        ProductsEntity productsEntity7 = new ProductsEntity((Long) null,"Teddy Bear", "Plush teddy bear", 10.0, 4L, "https://example.com/teddy_bear.jpg", 0.0, dateCr, dateUp);
        productsRepository.save(productsEntity7);
        ProductsEntity productsEntity8 = new ProductsEntity(null,"Building Blocks", "Kids' building blocks set", 15.0, 4L, "https://example.com/blocks.jpg", 0.0, dateCr, dateUp);
        productsRepository.save(productsEntity8);

        ProductsEntity productsEntity9 = new ProductsEntity((Long) null,"T-Shirt", "Graphic T-shirt", 12.0, 5L, "https://example.com/tshirt.jpg", 10.0, dateCr, dateUp);
        productsRepository.save(productsEntity9);
        ProductsEntity productsEntity10 = new ProductsEntity(null,"Jeans", "Stylish jeans", 25.0, 5L, "https://example.com/jeans.jpg", 20.0, dateCr, dateUp);
        productsRepository.save(productsEntity10);

        ProductsEntity productsEntity11 = new ProductsEntity((Long) null,"Notebook", "School notebook", 1.5, 6L, "https://example.com/notebook.jpg", 0.0, dateCr, dateUp);
        productsRepository.save(productsEntity11);
        ProductsEntity productsEntity12 = new ProductsEntity(null,"Pen", "Gel pen", 0.5, 6L, "https://example.com/pen.jpg", 0.0, dateCr, dateUp);
        productsRepository.save(productsEntity12);


        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    public List<Product> getAllProducts() {
        List<ProductsEntity> productsEntities = productsRepository.findAll();
        return productsEntities.stream()
                .map(entity -> new Product(entity.getProductId(), entity.getName(),entity.getDescription(), entity.getPrice(), entity.getCategoryId(), entity.getImageUrl(), entity.getDiscountPrice(), entity.getCreatedAt(), entity.getUpdatedAt()))
                .collect(Collectors.toList());
    }


    public Product getProductByID(@PathVariable Long id) {
        return productList.stream()
                .filter(product -> product.getProductID() == id)
                .findFirst()
                .orElse(null);
    }

    public Product getProductByName(@RequestParam String name) {
        return productList.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public boolean createProduct(@RequestBody Product newProduct) {
        return productList.add(newProduct);
    }

    public Product updateProduct(@RequestBody Product updProduct) {
        Product result = productList.stream()
                .filter(product -> product.getProductID() == updProduct.getProductID())
                .findFirst()
                .orElse(null);
        if (result != null) {
            result.setProductID(updProduct.getProductID());
            result.setName(updProduct.getName());
            result.setDescription(updProduct.getDescription());
            result.setPrice(updProduct.getPrice());
            result.setCategoryID(updProduct.getCategoryID());
            result.setImageURL(updProduct.getImageURL());
            result.setDiscountPrice(updProduct.getDiscountPrice());
            result.setCreatedAt(updProduct.getCreatedAt());
            result.setUpdatedAt(updProduct.getUpdatedAt());
        }
        return result;
    }
    
    public void deleteProduct(@PathVariable Long id) {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductID() == id) {
                iterator.remove();
            }
        }
    }

    @PreDestroy
    void destroy() {
        productList.clear();
        System.out.println("Выполняем логику при окончании работы с  объектом " + this.getClass().getName());
    }

}

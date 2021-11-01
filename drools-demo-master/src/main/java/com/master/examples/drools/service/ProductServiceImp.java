package com.master.examples.drools.service;

import com.master.examples.drools.model.Product;
import com.master.examples.drools.repository.ProductRepository;
import com.master.examples.drools.service.serviceImp.CRUDService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImp implements CRUDService {

    private final ProductRepository productRepository;

    private final KieContainer kieContainer;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, KieContainer kieContainer) {
        this.productRepository = productRepository;
        this.kieContainer = kieContainer;
    }

    public Collection<?> addProducts(boolean vehicle, boolean property, boolean life, String productName) {
        return createProducts(vehicle, property, life, productName);
    }

    private Collection<?> createProducts(boolean vehicle, boolean property, boolean life, String productName) {
        KieSession kieSession = kieContainer.newKieSession();
        Set<Product> products = new HashSet<>();

        try {
            if (vehicle) {
                Product product = new Product();
                product.setProductName("Vehicle Insurance " + productName);
                product.setProductType(Product.ProductType.VEHICLE);
                kieSession.insert(product);
                kieSession.fireAllRules();
                products.add(product);
            }
            if (property) {
                Product product = new Product();
                product.setProductName("Property Insurance " + productName);
                product.setProductType(Product.ProductType.HOUSE);
                kieSession.insert(product);
                kieSession.fireAllRules();
                products.add(product);
                logger.info("Product model: {}", product.toString());

            }
            if (life) {
                Product product = new Product();
                product.setProductName("Life Insurance " + productName);
                product.setProductType(Product.ProductType.LIFE);
                kieSession.insert(product);
                kieSession.fireAllRules();
                products.add(product);
                logger.info("Product model: {}", product.toString());
            }
            kieSession.dispose();
        } catch (IllegalStateException illegalStateException) {
            logger.error("Exception thrown", illegalStateException.fillInStackTrace());
        } finally {
            kieSession.dispose();
        }
        return products;
    }

    @Override
    public Collection<?> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<?> getById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
    }

    public void saveProducts(Collection<Product> products) {
        products.forEach(productRepository::save);
    }
}

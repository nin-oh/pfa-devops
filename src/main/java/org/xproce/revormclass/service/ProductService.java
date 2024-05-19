package org.xproce.revormclass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.dao.entities.Product;
import org.xproce.revormclass.dao.repositories.ProductRepository;


import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService implements ProductManager {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {

            return productRepository.save(product);
    }

//    @Override
//    public Product addProduct(MultipartFile img,Product p) {
//
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
//        if(fileName.contains(".."))
//        {
//            System.out.println("not a a valid file");
//        }
//        try {
//            p.setImage(Base64.getEncoder().encodeToString(img.getBytes()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return productRepository.save(p);
//    }
    @Override
    public Product addProduct(String desc, MultipartFile img,Double price, Category category, int quan , Product p) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setImage(Base64.getEncoder().encodeToString(img.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {


            p.setDescription(desc);
            p.setPrice(price);

            p.setQuantity(quan);
            p.setCategory(category);
        }
        catch (Exception e)
        {System.out.println( e.getMessage());}
        return productRepository.save(p);
    }

    @Override
    public Product updateProduct(Product product) {


            return productRepository.save(product);


        }


    @Override
    public boolean deleteProduct(Product product) {
        try {
            productRepository.delete(product);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Product getProduct(Integer Id) {
        return productRepository.findById(Id).get();
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> searchProducts(Pageable pageable, String keyword) {
        return productRepository.findByDescriptionContaining(keyword, pageable);
    }
 @Override
    public long countByCategory(String categoryTitle) {
        return productRepository.countByCategory(categoryTitle);
    }

    @Override
    public long countByVendor(String vendorTitle) {
        return productRepository.countByVendor(vendorTitle);
    }


    @Override
    public Page<Product> searchProductsad(String description, Double minPrice, Double maxPrice, String category,  String vendor,Pageable pageable) {
        return productRepository.searchProducts(description, minPrice, maxPrice, category, vendor,pageable);
    }

    public List<Product> searchProductsPrice(Double keyword){
        return productRepository.findByPrice(keyword);
    }

}

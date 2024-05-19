package org.xproce.revormclass.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.dao.entities.Product;
import java.util.List;

public interface ProductManager {
    public Product addProduct(Product product);
    public Product addProduct(String desc, MultipartFile img,Double price, Category category , int quan,Product p);

    //public Product addProduct(MultipartFile img,Product p);
    public Product updateProduct(Product product);

    public Page<Product> getAllProducts(Pageable pageable);

    public boolean deleteProduct(Product product);
    public Product getProduct(Integer Id);
    public List<Product> getAllProducts();
    public Page<Product> searchProducts(Pageable pageable,String keyword);
    public long countByCategory(String categoryTitle);
    public long countByVendor(String vendorTitle);
    public Page<Product> searchProductsad(String description, Double minPrice, Double maxPrice, String category,  String vendor,Pageable pageable);
}

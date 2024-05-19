package org.xproce.revormclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.dao.entities.Product;
import org.xproce.revormclass.dao.entities.Vendor;
import org.xproce.revormclass.service.Categorymanager;
import org.xproce.revormclass.service.ProductManager;
import org.xproce.revormclass.service.VendorManager;
import org.xproce.revormclass.user.entities.UserModel;
//import org.xproce.revormclass.service.inf.VendeurManager;
//import org.xproce.revormclass.user.entities.Vendeur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RevormclassApplication implements CommandLineRunner {

    @Autowired
    private ProductManager productManager;
    @Autowired
    private Categorymanager categorymanager;
    @Autowired
    private VendorManager vendorManager;
//    @Autowired
   // private VendeurManager vendeurManager;

    public static void main(String[] args) {
        SpringApplication.run(RevormclassApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        UserModel u1 = new UserModel();
        u1.getEmail();


        Vendor v= new Vendor();
        v.setTitle("Nintendo");
        vendorManager.addVendor(v);
//        v.setEmail("ob.gmail");
//        v.setPassword("fff");
               Category cat1= new Category(null, "book",null,0);
        Category cat2= new Category(null, "consol",null,0);
        Category cat3=    new Category(null, "disque",null,0);
         List<Category> categories = new ArrayList<>();
        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);

      categorymanager.addCategory(cat1);
        categorymanager.addCategory(cat2);
        categorymanager.addCategory(cat3);

        Product product = new Product(null, "The Compound Effect", 1223.0, "imageProduct/img.png", cat1,1);
      product.setVendor(v);
        Product product1 = new Product(null, "Switch", 1235.0,"imageProduct/img_1.png",cat2,0);
        product1.setVendor(v);
        Product product2 = new Product(null, "Mario Odyssey", 1823.0 ,"imageProduct/img_2.png",cat3,3);
        product2.setVendor(v);
        productManager.addProduct(product);
        productManager.addProduct(product1);
        productManager.addProduct(product2);
        categories.forEach(cat -> cat.setProductCount(productManager.countByCategory(cat.getTitle())));
        System.out.println(cat1.getProductCount());
        System.out.println(cat2.getProductCount());
        System.out.println(cat3.getProductCount());
////        List<Product> products = new ArrayList<>();
////        products.add(product1);
////        products.add(product2);
////        v.setProducts(products);
////        vendeurManager.addVendeur(v);
//        System.out.println(productManager.getAllProducts());

    }
}

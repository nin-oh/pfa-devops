package org.xproce.revormclass.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.dao.entities.Product;
import org.xproce.revormclass.dao.entities.Vendor;
import org.xproce.revormclass.service.Categorymanager;
import org.xproce.revormclass.service.ProductManager;
import jakarta.validation.Valid;
import jakarta.validation.*;
import org.springframework.data.domain.Pageable;
import org.xproce.revormclass.service.VendorManager;
import org.xproce.revormclass.service.inf.UserService;

import org.xproce.revormclass.user.entities.UserModel;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Objects;



@Controller
public class ProductController {

    @Autowired
    private ProductManager productManager;
    @Autowired
    private Categorymanager categorymanager;
    @Autowired
    private VendorManager vendorManager;

//    @GetMapping("/getProductsList")
//    public String getALlProducts(Model model) {
//        List<Product> products = productManager.getAllProducts();
//        model.addAttribute("listProduits", products);
//        return "listProduit" ;
//    }

    @GetMapping("/getALlProductsList")
    public String getALlProducts(Model model, @RequestParam(value = "keyword", required = false) String keyword
            , @RequestParam(defaultValue = "0") int page,  @AuthenticationPrincipal UserDetails userDetails) {


        String username = userDetails.getUsername();

        model.addAttribute("userLogin", username);
        Pageable pageable = PageRequest.of(page, 2);
        Page<Product> productPage;

        if (keyword != null && !keyword.isEmpty()) {
             productPage = productManager.searchProducts(pageable,keyword);
        } else {
             productPage = productManager.getAllProducts(pageable);
        }
//        List<Category> categories = categorymanager.getAllCategories();
//        model.addAttribute("categories", categories);
        model.addAttribute("listProduits", productPage);
        return "listProduit"; // Assuming you have a Thymeleaf/HTML template named "listproduct.html"

   }
    @GetMapping("/searchProducts")
    public String showProducts(@RequestParam(name = "description", required = false) String description,
                               @RequestParam(name = "minPrice", required = false) Double minPrice,
                               @RequestParam(name = "maxPrice", required = false) Double maxPrice,
                               @RequestParam(name = "category", required = false) String category,
                               @RequestParam(name = "vendor", required = false) String vendor,@RequestParam(defaultValue = "0") int page,
                               @AuthenticationPrincipal UserDetails userDetails ,Model model) {

            String username = userDetails.getUsername();

            model.addAttribute("userLogin", username);
        Pageable pageable = PageRequest.of(page, 3);
        Page<Product> products;

        // Ensure empty strings are treated as null
        if (description != null && description.isEmpty()) description = null;
        if (category != null && category.isEmpty()) category = null;
        if (vendor != null && vendor.isEmpty()) vendor = null;

        // Fetch products based on search criteria
        if (description == null && minPrice == null && maxPrice == null && category == null && vendor == null) {
            products = productManager.getAllProducts(pageable);
        } else {
            products = productManager.searchProductsad(description, minPrice, maxPrice, category, vendor,pageable);
        }

        List<Category> categories = categorymanager.getAllCategories();
        List<Vendor> vendors = vendorManager.getAllVendors();
        categories.forEach(cat -> cat.setProductCount(productManager.countByCategory(cat.getTitle())));
        vendors.forEach(vend -> vend.setProductCount(productManager.countByVendor(vend.getTitle())));
        model.addAttribute("categories", categories);
        model.addAttribute("vendors", vendors);
        model.addAttribute("listProductsn", products);

        return "listPFC";
    }

//    @GetMapping("/getALlProductsListCL")
//    public String getAllProductsCL(Model model, @RequestParam(value = "keyword", required = false) String keyword,
//                                   @RequestParam(defaultValue = "0") int page, @AuthenticationPrincipal UserDetails userDetails) {
//
//
//        model.addAttribute("userLogin", userDetails.getUsername());
//
//        Pageable pageable = PageRequest.of(page, 2);
//        Page<Product> productPage;
//        if (keyword != null && !keyword.isEmpty()) {
//            productPage = productManager.searchProducts(pageable, keyword);
//        } else {
//            productPage = productManager.getAllProducts(pageable);
//        }
//        List<Category> categories = categorymanager.getAllCategories();
//        List<Vendor> vendors = vendorManager.getAllVendors();
//        categories.forEach(cat -> cat.setProductCount(productManager.countByCategory(cat.getTitle())));
//        vendors.forEach(vend -> vend.setProductCount(productManager.countByVendor(vend.getTitle())));
//        model.addAttribute("categories", categories);
//        model.addAttribute("vendors", vendors);
//        model.addAttribute("listProductsn", productPage);
//        return "listPFC"; // Assurez-vous que c'est le nom correct de votre template Thymeleaf
//    }

//    @PostMapping("/searchProducts")
//    public String searchProducts(Model model,
//                                 @RequestParam(value = "description", required = false) String description,
//                                 @RequestParam(value = "category", required = false) Integer category,
//                                 @RequestParam(value = "minPrice", required = false) Double minPrice,
//                                 @RequestParam(value = "maxPrice", required = false) Double maxPrice,
//                                 @RequestParam(value = "vendor", required = false) String vendor,
//                                 @RequestParam(defaultValue = "0") int page) {
//        Pageable pageable = PageRequest.of(page, 2);
//        List<Category> categories = categorymanager.getAllCategories();
//        model.addAttribute("categories", categories);
//        Page<Product> productPage = productManager.advancedSearch(pageable, description, category, minPrice, maxPrice, vendor);
//        model.addAttribute("listProducts", productPage);
//        return "listPFC"; // Assurez-vous que c'est le nom correct de votre template Thymeleaf
//    }
//@PostMapping("/searchProductsresult")
//public String searchProducts(@RequestParam(name = "description",required = false) String description,
//                             @RequestParam(name = "minPrice",required = false) Double minPrice,
//                             @RequestParam(name = "maxPrice",required = false) Double maxPrice,
//                             @RequestParam(name = "category",required = false)  String category,
//                             @RequestParam(name = "vendor",required = false) String vendor,
//                             Model model) {
//    List<Product> products = productManager.searchProductsad(description, minPrice, maxPrice, category, vendor);
//    System.out.println(products);
//    model.addAttribute("listProductsn", products);
//    model.addAttribute("categories", categorymanager.getAllCategories());
//    return "listPFC";
//}
//    @GetMapping("/getALlProductsListCL")
//    public String getALlProductsCL(Model model, @RequestParam(value = "keyword", required = false) String keyword
//            , @RequestParam(defaultValue = "0") int page) {
//
//
//
//        Pageable pageable = PageRequest.of(page, 8);
//        Page<Product> productPage;
//
//        if (keyword != null && !keyword.isEmpty()) {
//             productPage = productManager.searchProducts(pageable,keyword);
//        } else {
//             productPage = productManager.getAllProducts(pageable);
//        }
////        List<Category> categories = categorymanager.getAllCategories();
////        model.addAttribute("categories", categories);
//        model.addAttribute("listProducts", productPage.getContent());
//        return "listPFC";
//    }
//    @PostMapping("/ajouter")
//    public String addproductdb(Model model, @RequestParam(name = "description") String description,
//                               @RequestParam(name = "price") double price) {
//        Product product = new Product();
//        product.setDescription(description);
//        product.setPrice(price);
//        productManager.addProduct(product);
//        return "redirect:/getALlProductsList";
//    }
//    @PostMapping("/ajouterOnce")
//    public String ajouterProduitdb(Model model,
//                                 @Valid Product product,
//                                 BindingResult result) {
//        if(result.hasErrors()){
//            return "addProduit" ;
//        }
//        productManager.addProduct(product);
//        return "redirect:/getALlProductsList";
//    }
@PostMapping("/ajouterOnce")
public String ajouterProduitdb(Model model,
                               @Valid Product product,
                               BindingResult result,@RequestParam("file") MultipartFile file) {

    List<Category> categories = categorymanager.getAllCategories();
    model.addAttribute("categories", categories);
    List<Vendor> vendors = vendorManager.getAllVendors();
    model.addAttribute("vendors", vendors);
    if (result.hasErrors()) {

        return "addProduit";
    } else {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        productManager.addProduct(product);

        return "redirect:/getALlProductsList";
    }
}




    @GetMapping("/ajouterproduit")
    public String ajouterproduit(Model model) {
        List<Category> categories = categorymanager.getAllCategories();
        List<Vendor> vendors = vendorManager.getAllVendors();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());

        model.addAttribute("vendors", vendors);
        return "addProduit";
    }
//CATEGORY
        @PostMapping("/ajouterOnceCateg")
    public String ajouterProduitdb(Model model,
                                 @RequestParam(name = "title") String  title
                                 ) {
Category category = new Category();
category.setTitle(title);
        categorymanager.addCategory(category);
        return "redirect:/getALlProductsList";
    }

    @GetMapping("/ajoutercateg")
    public String ajoutercateg(Model model) {


        model.addAttribute("category", new Category());
        return "ajouterCateg";
    }




//VENDOR
@PostMapping("/ajouterOnceVen")
public String ajouterVENonce(Model model,
                               @RequestParam(name = "title") String  title
) {
    Vendor vendor = new Vendor();
    vendor.setTitle(title);
    vendorManager.addVendor(vendor);
    return "redirect:/getALlProductsList";
}
    @GetMapping("/ajouterven")
    public String ajouterven(Model model) {


        model.addAttribute("vendor", new Vendor());
        return "ajouterven";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id,Model model) {
        Product product1 = productManager.getProduct(id);
        List<Category> categories = categorymanager.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product1);
        return "update"; // Assuming there's a Thymeleaf/HTML template named "edit-product.html"
    }
   // @PostMapping("/edit/{id}")
    //public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
      //  productManager.updateProduct(product);
        //return "redirect:/getProductsList";
    //}
    @PostMapping("/edit")
    public String handleEditForm(@RequestParam("id") Integer id,
                                 @RequestParam("description") String desc,@RequestParam("price") double price,@RequestParam("quantity") int qu,
                                 @RequestParam("category") Category category,Model model) {

        Product updatedProduct = productManager.getProduct(id);
        updatedProduct.setDescription(desc);
        updatedProduct.setPrice(price);
        updatedProduct.setQuantity(qu);
        updatedProduct.setCategory(category);
        productManager.updateProduct(updatedProduct);
        return "redirect:/getALlProductsList";//return "redirect:/getProductsList"; // Redirect to the product list page after updating
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id,@ModelAttribute Product product) {
        productManager.deleteProduct(product);
        return "redirect:/getALlProductsList";
    }



    @PostMapping("/login")
    public String login() {
        // Handle login logic
        return "redirect:/getALlProductsList"; // Redirect to home page after login
    }

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Ajouter un objet User vide au modèle pour lier le formulaire Thymeleaf
        model.addAttribute("user", new UserModel());
        return "register"; // Nom du fichier Thymeleaf de registration (sans l'extension .html)
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }
    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute("user") @Valid UserModel usermodel,
                                         BindingResult result) {
        if (userService.existsByEmail(usermodel.getEmail())) {
            result.rejectValue("email", "error.user", "This email is already taken");
        }

        // Vérifiez si le nom d'utilisateur est déjà utilisé
        if (userService.existsByUsername(usermodel.getUsername())) {
            result.rejectValue("username", "error.user", "This username is already taken");
        }
        if (result.hasErrors()) {

            return "register";
        }
        else {


            userService.register(usermodel);
            return "redirect:/login?success";
        }
    }
//    @PostMapping("/register")
//    public String submitRegistrationForm(@ModelAttribute("user") UserModel user) {
//        userService.register(user);
//        // Ici, vous pouvez traiter l'enregistrement de l'utilisateur, par exemple en utilisant un service
//
//
//        // Rediriger vers une page de confirmation
////        return "redirect:/getALlProductsList"; // Nom du fichier Thymeleaf de confirmation
//        return "redirect:/login?success";
//    }
    @GetMapping("/home")
    public String getHomePage(Model model,@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();

        model.addAttribute("userLogin", username);

        return "home_page";
    }

    @GetMapping("/")
    public String getHomePage() {

        return "home_page_visit";
    }
}

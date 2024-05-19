package org.xproce.revormclass.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.dao.entities.Vendor;
import org.xproce.revormclass.service.VenderService;

import java.util.List;

// VendorController.java
@Controller
public class VendorController {

    @Autowired
    private VenderService vendorService;

    @GetMapping("/vendors")
    public String listVendors(Model model) {
        List<Vendor> vendors = vendorService.getAllVendors();
        model.addAttribute("vendors", vendors);
        return "vendorlist"; // Name of the Thymeleaf template
    }
    @GetMapping("/deletevendor/{id}")
    public String deleteVendor(@PathVariable("id") Integer id,@ModelAttribute Vendor vendor) {
        vendorService.deleteVendor(vendor);
        return "redirect:/vendors";
    }

    // Other methods for update and delete
}

// CategoryController.java


package org.xproce.revormclass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xproce.revormclass.dao.entities.OrderBasket;
import org.xproce.revormclass.dao.entities.Product;
import org.xproce.revormclass.service.inf.UserService;
import org.xproce.revormclass.user.entities.UserModel;
import org.xproce.revormclass.service.OrderBasketService;
import org.xproce.revormclass.service.ProductService;


import java.util.List;

@Controller
@RequestMapping("/orderBaskets")
public class OrderBasketController {

    @Autowired
    private OrderBasketService orderBasketService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllOrderBaskets(Model model) {
        List<OrderBasket> orderBaskets = orderBasketService.getAllOrderBaskets();
        model.addAttribute("orderBaskets", orderBaskets);
        return "orderBasketList";
    }


}

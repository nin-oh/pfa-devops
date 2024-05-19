package org.xproce.revormclass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xproce.revormclass.dao.entities.OrderBasket;
import org.xproce.revormclass.dao.entities.Product;
import org.xproce.revormclass.dao.repositories.OrderBasketRepository;
import org.xproce.revormclass.user.entities.UserModel;

import java.util.List;

@Service
public class OrderBasketService {

    @Autowired
    private OrderBasketRepository orderBasketRepository;

    public List<OrderBasket> getAllOrderBaskets() {
        return orderBasketRepository.findAll();
    }

    public OrderBasket save(OrderBasket product){return orderBasketRepository.save(product);}
}

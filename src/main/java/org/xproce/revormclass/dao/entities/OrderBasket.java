package org.xproce.revormclass.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import org.xproce.revormclass.user.entities.UserModel;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "order_basket")
public class OrderBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "orderBasket", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserModel user;

    private int quantity;
    public double totalPrice() {
        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantityordered();
        }
        return totalPrice;
    }
}

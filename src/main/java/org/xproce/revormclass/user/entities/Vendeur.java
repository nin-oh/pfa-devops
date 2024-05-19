//package org.xproce.revormclass.user.entities;
//
//import jakarta.persistence.OneToMany;
//import lombok.*;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import org.xproce.revormclass.dao.entities.Product;
//
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "vendeurs") // Nom de la table pour les vendeurs
//public class Vendeur extends User {
//
//    @OneToMany(mappedBy = "vendeur")
//    private List<Product> products;
//
//    public Vendeur(String firstName, String lastName, String email, String password, Role role) {
//        super(firstName, lastName, email, password, role);
//    }
//}
//

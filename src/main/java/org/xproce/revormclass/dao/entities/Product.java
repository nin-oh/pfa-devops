package org.xproce.revormclass.dao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
//import org.xproce.revormclass.dao.entities.Vendor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "[A-Za-z0-9 ]*")
    private String description;


    @Min(0)
    @NotNull
    private Double price;


    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotEmpty
    @Pattern(regexp = "available|out of stock")
    private String status;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @NotNull
    @Min(0)
    private int quantity;




    // Update status based on quantity
    @PrePersist
    @PreUpdate
    private void updateStatus() {
        if (quantity > 0) {
            status = "available";
        } else {
            status = "out of stock";
        }
    }

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<OrderBasket> orderBaskets;


//    @ManyToOne
//    @JoinColumn(name = "id") // Nom de la colonne dans la table Product faisant référence à l'ID du vendeur
//    private Vendeur vendeur;
    public Product(Integer id, String description, double price, String image, Category category, int quantity) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
        this.quantity = quantity;
        if (quantity > 0) {
            status = "available";
        } else {
            status = "out of stock";
        }
       // vendeur = v;
    }

    public Product(String description, double price,  Category category, int quantity) {

        this.description = description;
        this.price = price;

        this.category = category;
        this.quantity = quantity;
        if (quantity > 0) {
            status = "available";
        } else {
            status = "out of stock";
        }
        // vendeur = v;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if (quantity > 0) {
            status = "available";
        } else {
            status = "out of stock";
        }
    }
}

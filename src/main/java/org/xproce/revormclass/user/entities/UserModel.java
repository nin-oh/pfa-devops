//
package org.xproce.revormclass.user.entities;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import org.xproce.revormclass.dao.entities.OrderBasket;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class UserModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;

    String lastName;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL, CascadeType.PERSIST})
    @ToString.Exclude
    private List<OrderBasket> orderBaskets;

    @Column(unique = true)
    String username;

    @Column(unique = true)
    String email;

    String password;

//    @Enumerated(EnumType.STRING)
    String role;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

//    public UserModel(String firstName, String lastName, String email, String password, Role role) {
//        this.firstName =firstName;
//        this.lastName =lastName;
//        this.email =email;
//        this.password =password;
//        this.role =role;
//    }



//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}

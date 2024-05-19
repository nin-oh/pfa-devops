package org.xproce.revormclass.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.revormclass.user.entities.UserModel;
import org.xproce.revormclass.user.entities.UserModel;

import java.util.Optional;

public interface Userrepos extends JpaRepository<UserModel,Long> {
    UserModel findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    UserModel findByUsername(String username);
//    UserModel findById(Long id);


}

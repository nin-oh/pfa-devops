package org.xproce.revormclass.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xproce.revormclass.dao.entities.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}

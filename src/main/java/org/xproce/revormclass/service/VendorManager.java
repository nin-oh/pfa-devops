package org.xproce.revormclass.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.xproce.revormclass.dao.entities.Vendor;

import java.util.List;

public interface VendorManager {
    Vendor addVendor(Vendor vendor);

    Vendor updateVendor(Vendor vendor);

    Vendor getVendor(Integer id);

    boolean deleteVendor(Vendor vendor);

    List<Vendor> getAllVendors();
}

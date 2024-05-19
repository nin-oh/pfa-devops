package org.xproce.revormclass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xproce.revormclass.dao.entities.Vendor;
import org.xproce.revormclass.dao.repositories.VendorRepository;
import org.xproce.revormclass.service.VendorManager;

import java.util.List;
import java.util.Optional;

@Service
public class VenderService implements VendorManager {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public Vendor addVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(Vendor vendor) {
        Optional<Vendor> existingVendor = vendorRepository.findById(vendor.getId());
        if (existingVendor.isPresent()) {
            return vendorRepository.save(vendor);
        }
        throw new RuntimeException("Vendor not found");
    }

    @Override
    public Vendor getVendor(Integer id) {
        return vendorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    @Override
    public boolean deleteVendor(Vendor vendor) {
        if (vendorRepository.existsById(vendor.getId())) {
            vendorRepository.delete(vendor);
            return true;
        }
        return false;
    }



    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}

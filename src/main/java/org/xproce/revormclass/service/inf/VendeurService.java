//package org.xproce.revormclass.service.inf;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.xproce.revormclass.dao.repositories.CategoryRep;
//import org.xproce.revormclass.dao.repositories.VendeurRep;
//import org.xproce.revormclass.user.entities.Vendeur;
//
//@Service
//public class VendeurService implements VendeurManager{
//
//    @Autowired
//    private VendeurRep vendeurRep;
//    @Override
//    public Vendeur addVendeur(Vendeur vendeur) {
//        return addVendeur(vendeur);
//    }
//
//    @Override
//    public Vendeur updateVendeur(Vendeur vendeur) {
//        return addVendeur(vendeur);
//    }
//
//    @Override
//    public Vendeur getVendeur(Long Id) {
//        return vendeurRep.findById(Id).get();
//    }
//
//    @Override
//    public boolean deleteVendeur(Vendeur vendeur) {
//        try {
//            vendeurRep.delete(vendeur);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Error deleting product: " + e.getMessage());
//            return false;
//        }
//    }
//}

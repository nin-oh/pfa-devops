package org.xproce.revormclass.web;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.dao.entities.Vendor;
import org.xproce.revormclass.service.Categorymanager;
import org.xproce.revormclass.service.OrderBasketService;
import org.xproce.revormclass.service.ProductManager;
import org.xproce.revormclass.service.VendorManager;
import org.xproce.revormclass.service.inf.UserService;
import org.xproce.revormclass.user.entities.UserModel;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductManager productManager;

    @MockBean
    private Categorymanager categorymanager;

    @MockBean
    private VendorManager vendorManager;

    @MockBean
    private OrderBasketService orderBasketService;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "testUser")
    public void testGetAllProducts() throws Exception {
        // Configurer le comportement des mocks
        when(productManager.getAllProducts(PageRequest.of(0, 2))).thenReturn(Page.empty());

        // Effectuer la requête et vérifier les résultats
        mockMvc.perform(MockMvcRequestBuilders.get("/getALlProductsList"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userLogin"))
                .andExpect(model().attributeExists("listProduits"));
    }



}

package org.csu.petstoremanage;

import org.csu.petstoremanage.domain.Item;
import org.csu.petstoremanage.domain.Product;
import org.csu.petstoremanage.service.CatalogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PetstoreManageApplicationTests {

    @Autowired
    private CatalogService catalogService;
    @Test
    void contextLoads() {

        System.out.println("------------");
        //catalogService.removeCategory("aaa");
        //catalogService.updateCategory("FISH","FISH-1","Fish");
        catalogService.removeProduct("aaa");
    }

}

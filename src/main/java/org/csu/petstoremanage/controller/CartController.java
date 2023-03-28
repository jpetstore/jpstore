package org.csu.petstoremanage.Controller;

import org.csu.petstoremanage.domain.Category;
import org.csu.petstoremanage.domain.Product;
import org.csu.petstoremanage.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CatalogService catalogService;
    @GetMapping("category")
    public String category(Model model){
        List<Category> categories=catalogService.getCategoryList();
        categories.stream().forEach(System.out::println);
        model.addAttribute("categories",categories);
        return "category";
    }
    @GetMapping(value = "categories/{categoryId}")
    public String getProductByCategoryId(@PathVariable String categoryId){
        System.out.println(categoryId);
        return "category";
    }
    @GetMapping(value = "categoryEdit")
    public String categoryEdit(
            @RequestParam String categoryId,
            Model model
    ){
        System.out.println(categoryId);
        Category category=catalogService.getCategory(categoryId);
        model.addAttribute("category",category);
        return "categoryEdit";
    }

    @GetMapping(value = "productEdit")
    public String productedit(
            @RequestParam String productId,
            Model model
    ){
        System.out.println(productId);
        Product product=catalogService.getProduct(productId);
        model.addAttribute("product",product);
        return "productEdit";
    }
    @GetMapping(value = "categoryRemove")
    public String categoryRemove(@RequestParam String categoryId){
        catalogService.removeCategory(categoryId);
        System.out.println("remove success");
        return "redirect:category";
    }
    @PostMapping("updateCategory")
    public String updateCategory(
            @RequestParam String oldCategoryId,
            @RequestParam String categoryId,
            @RequestParam String name
    ){
        System.out.println(oldCategoryId);
        System.out.println(categoryId);
        System.out.println(name);

        catalogService.updateCategory(oldCategoryId,categoryId,name);
        System.out.println("--------------");
        System.out.println("update sucess");

        return "redirect:category";
    }

    @PostMapping("updateProduct")
    public String updateProduct(
            @RequestParam String oldProductId,
            @RequestParam String productId,
            @RequestParam String name
    ){
        catalogService.updateProduct(oldProductId,productId,name);
        return "redirect:product";
    }
    @GetMapping("categoryAddForm")
    public String categoryAddForm(){
        return "categoryAddForm";
    }

    @GetMapping("productAddForm")
    public String productAddForm(){
        return "productAddForm";
    }
    @PostMapping("productAdd")
    public String productAdd(
            @RequestParam String categoryId,
            @RequestParam String productId,
            @RequestParam String name
    ){
        catalogService.insertProduct(categoryId,productId,name);
        return "redirect:product";
    }
    @PostMapping("categoryAdd")
    public String categoryAdd(
            @RequestParam String categoryId,
            @RequestParam String name
    ){
        Category category=new Category();
        category.setName(name);
        category.setCategoryId(categoryId);
        catalogService.insertCategory(category);
        return "redirect:category";
    }

    @GetMapping("product")
    public String product(Model model){
        List<Product> products=catalogService.getProductList();
        model.addAttribute("products",products);
        return "product";
    }

    @GetMapping("productRemove")
    public String productRemove(@RequestParam String productId){
        catalogService.removeProduct(productId);
        return "redirect:product";
    }
}

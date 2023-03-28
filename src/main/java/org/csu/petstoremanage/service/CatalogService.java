package org.csu.petstoremanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.csu.petstoremanage.domain.Category;
import org.csu.petstoremanage.domain.Item;
import org.csu.petstoremanage.domain.Product;
import org.csu.petstoremanage.mapper.CategoryMapper;
import org.csu.petstoremanage.mapper.ItemMapper;
import org.csu.petstoremanage.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ItemMapper itemMapper;

    QueryWrapper<Category> queryWrapper_Category= new QueryWrapper<>();
    QueryWrapper<Product> queryWrapper_product= new QueryWrapper<>();

    QueryWrapper<Item> queryWrapper_item=new QueryWrapper<>();
    UpdateWrapper<Product> updateWrapper_product=new UpdateWrapper<>();
    UpdateWrapper<Category> updateWrapper_category=new UpdateWrapper<>();

    UpdateWrapper<Item>updateWrapper_item=new UpdateWrapper<>();

    public List<Category>getCategoryList()
    {
        return  categoryMapper.selectList(null);
    }
    public List<Product> getProductList(){
        return productMapper.selectList(null);
    }
    public Category getCategory(String categoryId)
    {
        queryWrapper_Category.eq("catid",categoryId);
        Category category=categoryMapper.selectOne(queryWrapper_Category);
        return category;
    }
    public Product getProduct(String productId)
    {
        queryWrapper_product.eq("productid",productId);
        return productMapper.selectOne(queryWrapper_product);
    }
    public List<Product> getProductListByCategory(String categoryId)
    {
        queryWrapper_product.eq("catid",categoryId);
        return productMapper.selectList(queryWrapper_product);
    }
    public void insertCategory(Category category){
        categoryMapper.insert(category);
    }

    public void insertProduct(String categoryId,String productId,String name){
        Category category=new Category();
        category.setCategoryId(categoryId);
        insertCategory(category);
        Product product=new Product();
        product.setProductId(productId);
        product.setName(name);
        productMapper.insert(product);
    }
    //update
    public void updateCategory(String oldCategoryId,String categoryId,String name){

        List<Product>productList=searchProductByCategory(oldCategoryId);

        //将Product的category置空
        for(int i=0;i<productList.size();i++){
            updateWrapper_product.eq("productid",productList.get(i).getProductId());
            updateWrapper_product.set("category",null);
            productMapper.update(null,updateWrapper_product);
            updateWrapper_product.clear();
        }

        //修改数据
        updateWrapper_category.eq("catid",oldCategoryId);
        updateWrapper_category.set("catid",categoryId);
        updateWrapper_category.set("name",name);
        categoryMapper.update(null,updateWrapper_category);

    }

    public void updateProduct(String oldProductId,String productId,String name){

        List<Item>itemList=searchItemByProduct(oldProductId);

        //将Product的category置空
        for(int i=0;i<itemList.size();i++){
            updateWrapper_item.eq("itemid",itemList.get(i).getItemId());
            updateWrapper_item.set("productid",null);
            itemMapper.update(null,updateWrapper_item);
            updateWrapper_item.clear();
        }

        //修改数据
        updateWrapper_product.eq("productid",oldProductId);
        updateWrapper_product.set("productid",productId);
        updateWrapper_product.set("name",name);
        productMapper.update(null,updateWrapper_product);
    }
    //通过category搜索product
    public List<Product> searchProductByCategory(String category){
        queryWrapper_product.eq("category",category);
        List<Product> productList=new ArrayList<>();
        productList=productMapper.selectList(queryWrapper_product);
        return productList;
    }

    //通过productlist搜索item
    public List<Item> searchItemByProductList(List<Product> productList){
        List<Item> itemList=new ArrayList<>();
        for(int i=0;i<productList.size();i++){
            queryWrapper_item.eq("productid",productList.get(i).getProductId());
            itemList.addAll(itemMapper.selectList(queryWrapper_item));
            queryWrapper_item.clear();
        }
        return itemList;
    }
    public List<Item>searchItemByProduct(String productId){
        List<Item> itemList=new ArrayList<>();
        queryWrapper_item.eq("productid",productId);
        itemList=itemMapper.selectList(queryWrapper_item);
        return itemList;
    }

    public void removeCategory(String categoryId){
        List<Product> productList=searchProductByCategory(categoryId);
        List<Item> itemList=searchItemByProductList(productList);
        productList.forEach(System.out::println);
        System.out.println("------------");
        itemList.forEach(System.out::println);
        //先删除item
        for(int i=0;i<itemList.size();i++){
            queryWrapper_item.eq("productid",itemList.get(i).getProductId());
            itemMapper.delete(queryWrapper_item);
        }
        //再删除product
        for(int i=0;i<productList.size();i++){
            queryWrapper_product.eq("category",productList.get(i).getCategoryId());
            productMapper.delete(queryWrapper_product);
        }
        //最后删除category
        categoryMapper.deleteById(categoryId);
    }

    public void removeProduct(String productId){
        List<Item> itemList=searchItemByProduct(productId);
        //先删除item
        for(int i=0;i<itemList.size();i++){
            queryWrapper_item.eq("productid",itemList.get(i).getProductId());
            itemMapper.delete(queryWrapper_item);
        }
        queryWrapper_product.eq("productid",productId);
        productMapper.delete(queryWrapper_product);
    }

}

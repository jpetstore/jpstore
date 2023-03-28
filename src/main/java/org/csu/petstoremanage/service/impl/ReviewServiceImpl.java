package org.csu.petstoremanage.service.impl;

import org.csu.petstoremanage.domain.Review;
import org.csu.petstoremanage.persistence.ReviewMapper;
import org.csu.petstoremanage.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public Review getReview(int id){
        return reviewMapper.selectById(id);
    }

    public void deleteReview(int id){
        reviewMapper.deleteById(id);
    }

    public void insertReview(Review review){
        reviewMapper.insert(review);
    }

    public List<Review> getAllReview(){
        return reviewMapper.selectList(null);
    }

    public List<Review> getItemReview(String itemid){
        Map<String, Object> map = new HashMap<>();
        map.put("itemid", itemid);
        return reviewMapper.selectByMap(map);
    }

    //将一个评论在其所在商品下置顶，其余商品取消置顶
    public int setTop(int id){
        Review result1 = reviewMapper.selectById(id);

        String itemid = result1.getItemid();
        Map<String, Object> map = new HashMap<>();
        map.put("itemid", itemid);
        map.put("top", true);
        List<Review> result2 = reviewMapper.selectByMap(map);

        result1.setTop(true);
        reviewMapper.updateById(result1);
        if(!result2.isEmpty()){
            result2.get(0).setTop(false);
            reviewMapper.updateById(result2.get(0));
            return result2.get(0).getId();
        }else{
            return 0;
        }
    }

    //取消置顶
    public void cancelTop(int id){
        Review review = reviewMapper.selectById(id);
        review.setTop(false);
        reviewMapper.updateById(review);
    }
}

package org.csu.petstoremanage.controller;

import com.alibaba.fastjson.JSON;
import org.csu.petstoremanage.domain.Review;
import org.csu.petstoremanage.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/reviewtable")
    public String table(Model model){
        List<Review> reviewList = reviewService.getAllReview();
        model.addAttribute("reviewList", reviewList);
        return "review-table";
    }

    @GetMapping ("/reviewtop")
    @ResponseBody
    public int reviewtop(int id){
        return reviewService.setTop(id);
    }

    @GetMapping ("/canceltop")
    @ResponseBody
    public void canceltop(int id){
        reviewService.cancelTop(id);
    }


    @GetMapping ("/reviewdelete")
    @ResponseBody
    public String reviewdelete(int id){
        reviewService.deleteReview(id);
        String msg = "评论" + id + "已被删除";
        System.out.println(msg);
        return msg;
    }

    @GetMapping("/reviewstatistic")
    public String statistic(Model model){
        List<Review> reviewList = reviewService.getAllReview();
        String reviewListJSON = JSON.toJSONString(reviewList);
        model.addAttribute("reviewListJSON", reviewListJSON);
        return "review-statistic";
    }
}

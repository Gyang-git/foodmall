package com.atghy.foodmall.search.controller;

import com.atghy.foodmall.search.service.MallSearchService;
import com.atghy.foodmall.search.vo.SearchParam;
import com.atghy.foodmall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-27
 * Description:
 */
@Controller
public class SearchController {
    @Autowired
    MallSearchService mallSearchServicel;

    @GetMapping("/list.html")
    public String listPage(SearchParam param, Model model, HttpServletRequest request){
        SearchResult result = mallSearchServicel.search(param);
        model.addAttribute("result",result);
        return "list";
    }
}

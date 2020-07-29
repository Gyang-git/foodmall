package com.atghy.foodmall.search.service;

import com.atghy.foodmall.search.vo.SearchParam;
import com.atghy.foodmall.search.vo.SearchResult;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-27
 * Description:
 */
public interface MallSearchService {
    /**
     *
     * @param param 检索的所有参数
     * @return 返回检索的结果 里面包含页面需要的所有信息
     */
    SearchResult search(SearchParam param);
}

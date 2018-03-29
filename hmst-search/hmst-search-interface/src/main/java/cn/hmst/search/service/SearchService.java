package cn.hmst.search.service;

import cn.hmst.search.SearchResult;

public interface SearchService {

	SearchResult search(String keyword, int page, int rows)  throws Exception;
}

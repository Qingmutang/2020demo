package com.tony.demo.obj;

import lombok.Data;

@Data
public class PageInfoDemo {
	public static final int DEFAULT_PAGE_SIZE = 20;
	
    /**当前页面的记录数*/
	private int rowCount	= 0;
	
	/**当前页*/
	private int currentPage	= 1;
	
	/**页面大小*/
	private int pageSize	= DEFAULT_PAGE_SIZE;
	
	/**总记录*/
	private int totalCount	= 0;
	
	/**页面总数*/
	private int pageCount	= 0;
	
	/**排序*/
	private String orderBy;
	
	public int calculatePageCount() {
		
         pageCount = totalCount / pageSize + ((totalCount % pageSize == 0) ? 0 : 1);
		
		if(currentPage < pageCount)
			rowCount = pageSize;
		else if(currentPage == pageCount)
			rowCount = totalCount - (pageCount - 1) * pageSize;
		
		return pageCount;
	}

}

/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.prl.ui.tag; 

import java.io.IOException;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * 페이징 Custom Tag
 * 
 * @author : 
 * @date : 2014. 11. 19.
 * @version : 1.0
 */
@SuppressWarnings("serial")
public final class FwkPaginationTag extends RequestContextAwareTag {

//	private static final long serialVersionUID = 1L;

	/**
	 * 총건수
	 */
	private int totalCount = 0;

	/**
	 * 페이지번호
	 */
	private int pageNo = 1;

	/**
	 * 페이지사이즈
	 */
	private int pageSize = 10;

	/**
	 * 총페이지
	 */
	private int totalPage = 0;

	/**
	 * Display 페이지 수
	 */
	private int displayPageCount = 10;

	private String clickPage = "";

	/**
	 * 페이지번호 클릭 Function명 Set 한다.
	 * 
	 * @Method Name : setTotalCount 
	 * @param totalCount
	 * @author :  
	 * @date : 2014. 11. 27.
	 */
	public void setClickPage(String clickPage) {
		this.clickPage = clickPage;
	}

	/**
	 * 총건수를 Set 한다.
	 * 
	 * @Method Name : setTotalCount 
	 * @param totalCount
	 * @author :  
	 * @date : 2014. 11. 27.
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 페이지번호를 Set 한다.
	 * 
	 * @Method Name : setPageNo
	 * @param pageNo
	 * @author :  
	 * @date : 2014. 11. 27.
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo < 1 ? 1 : pageNo;
	}

	/**
	 * 페이지사이즈를 Set 한다.
	 * 
	 * @Method Name : setPageSize 
	 * @param pageSize
	 * @author :  
	 * @date : 2014. 11. 27.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize < 1 ? 10 : pageSize;
	}

	/**
	 * Display 페이지 건수를 Set 한다.
	 * 
	 * @Method Name : setDisplayPageCount 
	 * @param displayPageCount
	 * @author :  
	 * @date : 2014. 11. 27.
	 */
	public void setDisplayPageCount(int displayPageCount) {
		this.displayPageCount = displayPageCount < 1 ? 10 : displayPageCount;
	}



	@Override
	protected int doStartTagInternal() throws Exception {


		drawPaging();
		return SKIP_BODY;
	}

	private void drawPaging() throws IOException {
//		System.out.println("+++++++++++++++++++++++++ spring in drawPaging 00");
//		System.out.println("+++++++++++++++++++++++++ spring in drawPaging this.totalCount " + this.totalCount);
//		System.out.println("+++++++++++++++++++++++++ spring in drawPaging this.pageSize " + this.pageSize);
//		System.out.println("+++++++++++++++++++++++++ spring in drawPaging this.pageNo " + this.pageNo);
//		System.out.println("+++++++++++++++++++++++++ spring in drawPaging this.displayPageCount " + this.displayPageCount);
		
		this.totalPage = (this.totalCount / this.pageSize) + ((this.totalCount*1.0) % (this.pageSize*1.0) > 0 ? 1 :  0);		

		int prev		= this.pageNo > 1 ? this.pageNo - 1 : 1;
		int next		= this.pageNo < this.totalPage ? this.pageNo + 1 : this.totalPage;

		int curPos = (this.pageNo / this.displayPageCount) + ((this.pageNo*1.0) % (this.displayPageCount*1.0) > 0 ? 1 : 0);		

		int prevPage	= curPos > 1 ?  (curPos - 1) * this.displayPageCount : 0;
		int nextPage = (curPos * this.displayPageCount + 1) <= this.totalPage ? curPos * this.displayPageCount + 1 : 0;

		int start =((this.pageNo / this.displayPageCount) + ((this.pageNo*1.0) % (this.displayPageCount*1.0) > 0 ? 1 : 0)) * this.displayPageCount - (this.displayPageCount - 1);
		int end = ((this.pageNo / this.displayPageCount) + ((this.pageNo*1.0) % (this.displayPageCount*1.0) > 0 ? 1 : 0)) * this.displayPageCount;


		if(end > this.totalPage) {
			end = this.totalPage;
		}		

		StringBuffer sb = new StringBuffer();

		sb.append("<div class=\"paging_wrap\">");

		if( start > end ){
			sb.append("<a href=\"#\" class=\"pprev\" title=\"맨앞으로\" style=\"display: none;\"><span style=\"display: none;\">맨앞으로</span></a>");
		}else{
			sb.append("<a href=\"javascript:" + clickPage + "(1)\" class=\"pprev\" title=\"맨앞으로\"><span style=\"display: none;\">맨앞으로</span></a>");
		}
		
//		if(this.pageNo > 1) {
//			sb.append("<a href=\"javascript:" + clickPage + "(1)\" class=\"pprev\" title=\"맨앞으로\"></a>");			
//		} else {
//			sb.append("<a href=\"#\" class=\"pprev\" title=\"맨앞으로\" style=\"display: none;\"></a>");
//		}

		if(this.pageNo > this.displayPageCount) {
			sb.append("<a href=\"javascript:" + clickPage + "(" + prevPage + ")\" class=\"prev\" title=\"앞으로\"><span style=\"display: none;\">앞으로</span></a>");
		} else {
			sb.append("<a href=\"#\" class=\"prev\" title=\"앞으로\"><span style=\"display: none;\">앞으로</span></a>");
		}
		sb.append("<span>");

		if( start > end ){
			sb.append("<a href=\"#\" class=\"active\" title=\"1\">1</a>");
		}else{

			for(int i=start ; i<=end ; i++) {
				if(i == this.pageNo) {
					sb.append("<a href=\"javascript:" + clickPage + "(" + i + ");\" class=\"active\" title=\"" + i + "\">" + i + "</a>");
				} else {
					sb.append("<a href=\"javascript:" + clickPage + "(" + i + ");\" title=\"" + i + "\">" + i + "</a>");
				}
			}

		}

		sb.append("</span>");

//		System.out.println("this.totalPage => " + this.totalPage);
//		System.out.println("this.totalCount => " + this.totalCount);
//		System.out.println("this.pageSize => " + this.pageSize);
//		System.out.println("this.nextPage => " + nextPage);


		if(this.totalPage >= nextPage && nextPage != 0) {
			sb.append("<a href=\"javascript:" + clickPage + "(" + nextPage + ");\" class=\"next\" title=\"뒤로\"><span style=\"display: none;\">뒤로</span></a>");
		} else {
			sb.append("<a href=\"#\" class=\"next\" title=\"뒤로\"><span style=\"display: none;\">뒤로</span></a>");
		}

		if( start > end ){
			sb.append("<a href=\"#\" class=\"nnext\" title=\"맨뒤로\" style=\"display: none;\"><span style=\"display: none;\">맨뒤로</span></a>");
		}else{
			sb.append("<a href=\"javascript:" + clickPage + "(" + totalPage + ")\" class=\"nnext\" title=\"맨뒤로\"><span style=\"display: none;\">맨뒤로</span></a>");
		}
		
//		if(this.pageNo < this.totalPage) {
//			sb.append("<a href=\"javascript:" + clickPage + "(" + totalPage + ")\" class=\"nnext\" title=\"맨뒤로\"></a>");
//		} else {
//			sb.append("<a href=\"#\" class=\"nnext\" title=\"맨뒤로\" style=\"display: none;\"></a>");
//		}

		sb.append("</div>");
		pageContext.getOut().write(sb.toString());
	}

}

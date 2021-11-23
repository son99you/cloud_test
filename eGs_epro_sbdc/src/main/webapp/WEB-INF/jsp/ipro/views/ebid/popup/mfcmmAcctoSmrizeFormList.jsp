<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 기술평가현황 > 평가위원별 총괄표
 *
 * <pre>
 * ebid 
 *    |_ popup
              |_ mfcmmAcctoSmrizeFormList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 전상훈
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/mfcmmAcctoSmrizeFormList.js"></script> 
 
<div id="windowPopup" class="w_980">
	<div class="formLayer">
		<h3 class="windowTitle">평가위원 총괄표</h3>
		<div id="searchWrap">
			<form name="biSearchForm" method="post">
				<table class="contable2">
					<tr>
						<td>
							<table class="contable">
								<tr>
									<td>
										<table>
											<colgroup>
												<col width="130px" align="left">
												<col width="280px" align="left">
												<col width="130px" align="left">
												<col width="280px" align="left">
											</colgroup>
											<tr height="24px">
												<td>
													
													입찰명
												</td>
												<td>
													<input type="text" class="w_95p">
												</td>
												<td>
													
													기술평가년도
												</td>
												<td>
													<select>
														<option>2017</option>
														<option>2016</option>
														<option>2015</option>
														<option>2014</option>
														<option>2013</option>
														<option>2012</option>
													</select>
												</td>
											</tr>
											<tr>
												<td class=" searchBtnTd" colspan="4">
													<button type="button" class="grayBtn ico pointer" id="searchBtn">
									                    <img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼"> 조회
									                </button>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<br>
		<div class="tableLayer">
			<div class="tableComment">
		        <p class="list_count">총 <span>4</span>건</p>
		    </div>
			<table class="table">
				<caption>평가위원 총괄표</caption>
           		<colgroup>
                   	<col width="8%"/>
                   	<col width="*"/>
                   	<col width="7%"/>
                   	<col width="7%"/>
                   	<col width="5%"/>
                   	<col width="7%"/>
                   	<col width="5%"/>
                   	<col width="5%"/>
                   	<col width="5%"/>
                   	<col width="5%"/>
                   	<col width="5%"/>
                   	<col width="5%"/>
                   	<col width="5%"/>
            	</colgroup>
		    	<tr>
		            <th class="txtc" rowspan="2">기술<br>평가일자</th>
		            <th class="txtc" rowspan="2">입찰명</th>
		            <th class="txtc" rowspan="2">평가<br>방법</th>
		            <th class="txtc" rowspan="2">위원<br>구분</th>
		            <th class="txtc" rowspan="2">평가<br>위원</th>
		            <th class="txtc" rowspan="2">평가<br>내역</th>
		            <th class="last txtc" colspan="7">업체평가점수</th>
		        </tr>
		        <tr>
		            <th class="txtc">A</th>
		            <th class="txtc">B</th>
		            <th class="txtc">C</th>
		            <th class="txtc">D</th>
		            <th class="txtc">E</th>
		            <th class="txtc">F</th>
		            <th class="last txtc">G</th>
		        </tr>
		        <tr>
		            <td class="txtc" rowspan="2" style="border-left: 1px solid #d5ddfd">2017-06-28</td>
		            <td  rowspan="2">2017년 하반기 NCS기반 신입직원 채용대행 용역</td>
		            <td class="txtc" rowspan="2">소집</td>
		            <td class="txtc">상임위원</td>
		            <td class="txtc">고길동</td>
		            <td class="txtc"></td>
		            <td class="txtc">78</td>
		            <td class="txtc">71</td>
		            <td class="txtc">74</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		        </tr>
		        <tr>
		            <td class="txtc">비상임위원</td>
		            <td class="txtc">이상수</td>
		            <td class="txtc"></td>
		            <td class="txtc">76</td>
		            <td class="txtc">75</td>
		            <td class="txtc">74</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		        </tr>
		        <tr>
		            <td class=" txtc" rowspan="3" style="border-left: 1px solid #d5ddfd">2017-06-24</td>
		            <td  rowspan="3">서울센터 에어컨 배관 이설공사</td>
		            <td class=" txtc" rowspan="3">소집</td>
		            <td class=" txtc">상임위원</td>
		            <td class=" txtc">가나다</td>
		            <td class=" txtc"></td>
		            <td class=" txtc">75.1</td>
		            <td class=" txtc">46.2</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		        </tr>
		        <tr>
		            <td class=" txtc">상임위원</td>
		            <td class=" txtc">라마바</td>
		            <td class=" txtc"></td>
		            <td class=" txtc">76.8</td>
		            <td class=" txtc">48.3</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		        </tr>
		        <tr>
		            <td class=" txtc">비상임위원</td>
		            <td class=" txtc">김혁수</td>
		            <td class=" txtc"></td>
		            <td class=" txtc">75</td>
		            <td class=" txtc">47.1</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		        </tr>
				<tr>
		            <td class="txtc" rowspan="2" style="border-left: 1px solid #d5ddfd">2017-06-22</td>
		            <td  rowspan="2">소모성 물품(MRO) 물품대행 사업자 선정</td>
		            <td class="txtc" rowspan="2">소집</td>
		            <td class="txtc">상임위원</td>
		            <td class="txtc">김혁수</td>
		            <td class="txtc"></td>
		            <td class="txtc">66</td>
		            <td class="txtc">71</td>
		            <td class="txtc">59</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		        </tr>
		        <tr>
		            <td class="txtc">비상임위원</td>
		            <td class="txtc">이요한</td>
		            <td class="txtc"></td>
		            <td class="txtc">68</td>
		            <td class="txtc">70</td>
		            <td class="txtc">65</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		            <td class="txtc">-</td>
		        </tr>	
		        <tr>
		        	<td class=" txtc" rowspan="2" style="border-left: 1px solid #d5ddfd">2017-06-20</td>
		            <td  rowspan="2">원조조달기업지원센터 위탁운영 용역</td>
		            <td class=" txtc" rowspan="2">소집</td>
		            <td class=" txtc">비상임위원</td>
		            <td class=" txtc">우종찬</td>
		            <td class=" txtc"></td>
		            <td class=" txtc">73</td>
		            <td class=" txtc">69</td>
		            <td class=" txtc">71</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		        </tr>
		        <tr>
		            <td class=" txtc">비상임위원</td>
		            <td class=" txtc">고창민</td>
		            <td class=" txtc"></td>
		            <td class=" txtc">68</td>
		            <td class=" txtc">66</td>
		            <td class=" txtc">69</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		            <td class=" txtc">-</td>
		        </tr>	
		    </table>
	    </div>
	</div>
	<div class="T_btnLayer fr top10">
    	<button type="button" class="blueBtn L pointer" id="closeBtn" >닫기</button>
    </div>
</div> <!--// content E-->

<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="offset" required="true" type="java.lang.Integer"
	description="CurrentPage"%>
<%@ attribute name="limit" required="true" type="java.lang.Integer"
	description="itemPerPage"%>
<div class="container text-center">
	<ul class="pagination">
	
		<c:if test="${offset > 1}">
			<li><a href="<c:url value="dashboard">
						<c:param name="offset" value="${1}" />
						<c:param name="limit" value="${limit}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="option" value="${page.option }" />
						<c:param name="search" value="${page.searchBy }" />
						</c:url>" aria-label="firstPage"> <span aria-hidden=true>&#8606;</span>
			</a></li>
		</c:if>
		
		<c:if test="${offset > 1}">
			<li><a href="<c:url value="dashboard">
						<c:param name="offset" value="${offset-1}" />
						<c:param name="limit" value="${limit}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="option" value="${page.option }" />
						<c:param name="search" value="${page.searchBy }" />
						</c:url>" aria-label="Previous"> <span aria-hidden=true>&laquo;</span>
			</a></li>
		</c:if>
		
		<c:if test="${offset-2 > 0}">
			<li><a href="<c:url value="dashboard">
						<c:param name="offset" value="${offset-2}" />
						<c:param name="limit" value="${limit}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="search" value="${page.searchBy }" />
						<c:param name="option" value="${page.option }" /></c:url>">${offset-2}</a></li>
		</c:if>
		
		<c:if test="${offset-1 > 0}">
			<li><a href="<c:url value="dashboard">
						<c:param name="offset" value="${offset-1}" />
						<c:param name="limit" value="${limit}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="search" value="${page.searchBy }" />
						<c:param name="option" value="${page.option }" /></c:url>">${offset-1}</a></li>
		</c:if>
		
		<li class="active"><a href="#">${offset}</a></li>
		
		<c:if test="${offset+1 <= page.totalPage}">
			<li><a href="<c:url value="dashboard">
						<c:param name="offset" value="${offset+1}" />
						<c:param name="limit" value="${limit}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="search" value="${page.searchBy }" />
						<c:param name="option" value="${page.option }" /></c:url>">${offset+1}</a></li>
		</c:if>
		
		<c:if test="${offset+2 <= page.totalPage}">
			<li><a href="<c:url value="dashboard">
						<c:param name="offset" value="${offset+2}" />
						<c:param name="limit" value="${limit}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="search" value="${page.searchBy }" />
						<c:param name="option" value="${page.option }" /></c:url>">${offset+2}</a></li>
		</c:if>
		
		<c:if test="${offset < page.totalPage}">
			<li><a href="<c:url value="dashboard">
						<c:param name="offset" value="${offset+1}" />
						<c:param name="limit" value="${limit}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="option" value="${page.option }" />
						<c:param name="search" value="${page.searchBy }" />
						</c:url>" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
		
		<c:if test="${offset < page.totalPage}">
			<li><a href="<c:url value="dashboard">
						<c:param name="offset" value="${page.totalPage}" />
						<c:param name="limit" value="${limit}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="option" value="${page.option }" />
						<c:param name="search" value="${page.searchBy }" />
						</c:url>" aria-label="LastPage"> <span aria-hidden="true">&#8608;</span>
			</a></li>
		</c:if>
	</ul>
	
	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?limit=10&search=${page.searchBy }&order=${page.orderBy }&option=${page.option }'">10</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?limit=50&search=${page.searchBy }&order=${page.orderBy }&option=${page.option }'">50</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?limit=100&search=${page.searchBy }&order=${page.orderBy }&option=${page.option }'">100</button>
	</div>
</div>
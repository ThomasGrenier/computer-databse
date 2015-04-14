<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ attribute name="method" required="true" type="java.lang.Integer"
	description="parameters"%>
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
	
		<c:if test="${method == 1}">
			<span style="float: right"><a href="<c:url value="dashboard">
						<c:param name="offset" value="${page.currentPage}" />
						<c:param name="limit" value="${page.nbResult}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="option" value="${page.option }" />
						<c:param name="search" value="${page.searchBy }" />
						<c:param name="locale" value="fr" />
						</c:url>" aria-label="langFr"> <span aria-hidden="true" id="langFr">fr</span></a>
						|
						<a href="<c:url value="dashboard">
						<c:param name="offset" value="${page.currentPage}" />
						<c:param name="limit" value="${page.nbResult}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="option" value="${page.option }" />
						<c:param name="search" value="${page.searchBy }" />
						<c:param name="locale" value="en" />
						</c:url>" aria-label="langEn"> <span aria-hidden="true" id="langEn">en</span>
			</a></span>
		</c:if>
		<c:if test="${method == 2}">
			<span style="float: right"><a href="<c:url value="addComputer">
						<c:param name="locale" value="fr" />
						</c:url>" aria-label="langFr"> <span aria-hidden="true" id="langFr">fr</span></a>
						|
						<a href="<c:url value="addComputer">
						<c:param name="locale" value="en" />
						</c:url>" aria-label="langEn"> <span aria-hidden="true" id="langEn">en</span>
			</a></span>
		</c:if>
		<c:if test="${method == 3}">
			<span style="float: right"><a href="<c:url value="editComputer">
						<c:param name="id" value="${computer.id}" />
						<c:param name="locale" value="fr" />
						</c:url>" aria-label="langFr"> <span aria-hidden="true" id="langFr">fr</span></a>
						|
						<a href="<c:url value="editComputer">
						<c:param name="id" value="${computer.id}" />
						<c:param name="locale" value="en" />
						</c:url>" aria-label="langEn"> <span aria-hidden="true" id="langEn">en</span>
			</a></span>
		</c:if>
		
		<a class="navbar-brand" href="dashboard"> Application - Computer
			Database </a>
	</div>
</header>
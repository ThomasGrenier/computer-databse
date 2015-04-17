<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<mylib:header />
<body>
	<mylib:bodyHeader method="1"/>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${page.totalResult} <spring:message code="label.computerFound"></spring:message></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="<spring:message code="label.search"></spring:message>" /> <input
							type="submit" id="searchsubmit" value="<spring:message code="label.filter"></spring:message>"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="label.addComputer">
					</spring:message></a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message code="label.edit"></spring:message></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a
							href="<c:url value="dashboard">
							<c:param name="offset" value="${page.currentPage}" />
							<c:param name="limit" value="${page.nbResult}" />
							<c:param name="search" value="${page.searchBy }" />
							<c:param name="order" value="name" />
							<c:choose>
								<c:when test="${page.option == 'DESC' }">
									<c:param name="option" value="" />
								</c:when>
								<c:otherwise>
									<c:param name="option" value="DESC" />
								</c:otherwise>
							</c:choose>
							</c:url>"
							aria-label="LastPage"> <span aria-hidden="true">
									<spring:message code="label.computerName"></spring:message></span>
						</a> <c:if test="${page.orderBy == 'name' }">
								<div class="right">
									<c:choose>
										<c:when test="${page.option == 'DESC'}">
										&#x21E7;
									</c:when>
										<c:otherwise>
											&#x21E9;
										</c:otherwise>
									</c:choose>
								</div>
							</c:if></th>
						<th><a
							href="<c:url value="dashboard">
							<c:param name="offset" value="${page.currentPage}" />
							<c:param name="limit" value="${page.nbResult}" />
						<c:param name="search" value="${page.searchBy }" />
							<c:param name="order" value="introduced" />
							<c:choose>
								<c:when test="${page.option == 'DESC' }">
									<c:param name="option" value="" />
								</c:when>
								<c:otherwise>
									<c:param name="option" value="DESC" />
								</c:otherwise>
							</c:choose>
							</c:url>"
							aria-label="LastPage"> <span aria-hidden="true">
									<spring:message code="label.introduced"></spring:message></span>
						</a> <c:if test="${page.orderBy == 'introduced' }">
								<div class="right">
									<c:choose>
										<c:when test="${page.option == 'DESC'}">
										&#x21E7;
									</c:when>
										<c:otherwise>
											&#x21E9;
										</c:otherwise>
									</c:choose>
								</div>
							</c:if></th>
						<!-- Table header for Discontinued Date -->
						<th><a
							href="<c:url value="dashboard">
							<c:param name="offset" value="${page.currentPage}" />
							<c:param name="limit" value="${page.nbResult}" />
						<c:param name="search" value="${page.searchBy }" />
							<c:param name="order" value="discontinued" />
							<c:choose>
								<c:when test="${page.option == 'DESC' }">
									<c:param name="option" value="" />
								</c:when>
								<c:otherwise>
									<c:param name="option" value="DESC" />
								</c:otherwise>
							</c:choose>
							</c:url>"
							aria-label="LastPage"> <span aria-hidden="true">
									<spring:message code="label.discontinued"></spring:message></span>
						</a> <c:if test="${page.orderBy == 'discontinued' }">
								<div class="right">
									<c:choose>
										<c:when test="${page.option == 'DESC'}">
										&#x21E7;
									</c:when>
										<c:otherwise>
											&#x21E9;
										</c:otherwise>
									</c:choose>
								</div>
							</c:if></th>
						<!-- Table header for Company -->
						<th><a
							href="<c:url value="dashboard">
							<c:param name="offset" value="${page.currentPage}" />
							<c:param name="limit" value="${page.nbResult}" />
						<c:param name="search" value="${page.searchBy }" />
							<c:param name="order" value="company_name" />
							<c:choose>
								<c:when test="${page.option == 'DESC' }">
									<c:param name="option" value="" />
								</c:when>
								<c:otherwise>
									<c:param name="option" value="DESC" />
								</c:otherwise>
							</c:choose>
							</c:url>"
							aria-label="LastPage"> <span aria-hidden="true">
									<spring:message code="label.company"></spring:message></span>
						</a> <c:if test="${page.orderBy == 'company_name' }">
								<div class="right">
									<c:choose>
										<c:when test="${page.option == 'DESC'}">
										&#x21E7;
									</c:when>
										<c:otherwise>
											&#x21E9;
										</c:otherwise>
									</c:choose>
								</div>
							</c:if></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach items="${page.list}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id }" id="editCheck"/></td>
							<td id="computerName"><a id="computerLink"
								href="<c:url value="/editComputer?id=${computer.id}" />">${computer.name}</a>
								<%-- <a href="editComputer.html" onclick="">${computer.name}</a> --%>
							</td>
							<td hidden="true" ><input id="companyId" value="${computer.company.id }" /></td>
							<td id="computerIntro">${computer.introduced}</td>
							<td id="computerDisco">${computer.discontinued}</td>
							<td id="computerCompId"><c:choose>
									<c:when test="${empty computer.company}">
									</c:when>
									<c:otherwise>
											${computer.company.name}
										</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<mylib:pages offset="${page.currentPage}" limit="${page.nbResult}" />
	</footer>

	<mylib:script />

</body>
</html>
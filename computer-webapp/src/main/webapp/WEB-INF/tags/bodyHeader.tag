<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ attribute name="method" required="true" type="java.lang.Integer"
	description="parameters"%>
<%@ attribute name="pageName" required="false" type="java.lang.String"
	description="page name"%>
<script type="text/javascript">
	var strings = new Array();
	strings['confirmation'] = "<spring:message code='label.delete' javaScriptEscape='true' />"
	strings['viewText'] = "<spring:message code='label.view' javaScriptEscape='true' />"
	strings['editText'] = "<spring:message code='label.edit' javaScriptEscape='true' />"
	strings['choosenLang'] = "<spring:message code='label.lang' javaScriptEscape='true' />"
</script>
<header class="navbar navbar-inverse navbar-fixed-top">
	<c:if test="${method != 5}">
		<div id="deco">
			<form name="logoutForm" action="<c:url value='/logout' />"
				method="POST" id="logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<button id="buttondeco" type="submit" class="btn btn-primary"
					aria-label="Left Align">
					<span
						class="glyphicon glyphicon glyphicon-log-out glyphicon-align-left"
						aria-hidden="true"></span>
					<spring:message code="label.deco"></spring:message>
				</button>
			</form>
		</div>
	</c:if>
	<div class="container">
		<c:if test="${method == 1}">
			<span style="float: right"><a
				href="<c:url value="dashboard">
						<c:param name="offset" value="${page.currentPage}" />
						<c:param name="limit" value="${page.nbResult}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="option" value="${page.option }" />
						<c:param name="search" value="${page.searchBy }" />
						<c:param name="locale" value="fr" />
						</c:url>"
				aria-label="langFr"> <span aria-hidden="true" id="langFr"><img
						id="dfr" class="flag" src="<c:url value="/resources/img/fr.gif"/>" /></span></a>
				<a
				href="<c:url value="dashboard">
						<c:param name="offset" value="${page.currentPage}" />
						<c:param name="limit" value="${page.nbResult}" />
						<c:param name="order" value="${page.orderBy }" />
						<c:param name="option" value="${page.option }" />
						<c:param name="search" value="${page.searchBy }" />
						<c:param name="locale" value="en" />
						</c:url>"
				aria-label="langEn"> <span aria-hidden="true" id="langEn"><img
						id="den" class="flag"
						src="<c:url value="/resources/img/drapeau-gb.png"/>" /></span>
			</a></span>
		</c:if>
		<c:if test="${method == 2}">
			<span style="float: right"><a
				href="<c:url value="${pageName }">
						<c:param name="locale" value="fr" />
						</c:url>"
				aria-label="langFr"> <span aria-hidden="true" id="langFr"><img
						id="dfr" class="flag" src="<c:url value="/resources/img/fr.gif"/>" /></span></a>
				<a
				href="<c:url value="${pageName }">
						<c:param name="locale" value="en" />
						</c:url>"
				aria-label="langEn"> <span aria-hidden="true" id="langEn"><img
						id="den" class="flag"
						src="<c:url value="/resources/img/drapeau-gb.png"/>" /></span>
			</a></span>
		</c:if>
		<c:if test="${method == 3}">
			<span style="float: right"><a
				href="<c:url value="editComputer">
						<c:param name="id" value="${computer.id}" />
						<c:param name="locale" value="fr" />
						</c:url>"
				aria-label="langFr"> <span aria-hidden="true" id="langFr"><img
						id="dfr" class="flag" src="<c:url value="/resources/img/fr.gif"/>" /></span></a>
				<a
				href="<c:url value="editComputer">
						<c:param name="id" value="${computer.id}" />
						<c:param name="locale" value="en" />
						</c:url>"
				aria-label="langEn"> <span aria-hidden="true" id="langEn"><img
						id="den" class="flag"
						src="<c:url value="/resources/img/drapeau-gb.png"/>" /></span>
			</a></span>
		</c:if>
		<c:if test="${method == 5}">
			<span style="float: right"><a
				href="<c:url value="loginUser">
						<c:param name="state" value="${logout }" />
						<c:param name="error" value="${error }" />
						<c:param name="locale" value="fr" />
						</c:url>"
				aria-label="langFr"> <span aria-hidden="true" id="langFr"><img
						id="dfr" class="flag" src="<c:url value="/resources/img/fr.gif"/>" /></span></a>
				<a
				href="<c:url value="loginUser">
						<c:param name="state" value="${logout }" />
						<c:param name="error" value="${error }" />
						<c:param name="locale" value="en" />
						</c:url>"
				aria-label="langEn"> <span aria-hidden="true" id="langEn"><img
						id="den" class="flag"
						src="<c:url value="/resources/img/drapeau-gb.png"/>" /></span>
			</a></span>
		</c:if>

		<a class="navbar-brand" href="${pageContext.request.contextPath }/dashboard"> <spring:message
				code="label.appliname"></spring:message></a>
	</div>
</header>
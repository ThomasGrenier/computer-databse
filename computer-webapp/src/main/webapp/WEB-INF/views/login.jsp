<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<mylib:header />
<body>
	<mylib:bodyHeader method="4" />

	<section id="main">
		<div class="container">
			<c:if test="${error == 'err'}">
				<div class="alert alert-danger">
					Login ou mot de passe invalide ! <br />
				</div>
			</c:if>
			<form name="loginForm" action="<c:url value='/perform_login' />"
				method="POST" id="login">
				<fieldset>
					<div class="form-group">
						<label for="UserName">Login</label> <input type="text"
							class="form-control" id="username" name="username">
					</div>
					<div class="form-group">
						<label for="UserPassword">password</label> <input type="password"
							class="form-control" id="password" name="password">
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</fieldset>
				<div class="actions pull-right">
					<input value="Login" name="submit" type="submit"
						class="btn btn-primary" />
				</div>
			</form>
		</div>
	</section>

	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/dashboard.js" />"></script>

</body>
</html>
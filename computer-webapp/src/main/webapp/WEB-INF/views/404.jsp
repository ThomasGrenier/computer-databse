<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<mylib:header />
<body>
	<mylib:bodyHeader method="4"/>

    <section id="main">
        <div class="container">
            <div class="alert alert-danger">
                <spring:message code="label.err404"></spring:message>
                <br/>
                <!-- stacktrace -->
            </div>
        </div>
    </section>

	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/dashboard.js" />"></script>

</body>
</html>
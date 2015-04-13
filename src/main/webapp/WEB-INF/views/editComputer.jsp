<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
	<mylib:header />
<body>
	<mylib:bodyHeader />
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right" id="computerId">
                        id: ${computer.id }
                    </div>
                    <h1><spring:message code="label.editComputer"></spring:message></h1>

                    <form action="editComputer" method="POST" id="editComputer">
                        <input type="hidden" value="${computer.id }" name="id"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="label.computerName"></spring:message></label>
                                <input type="text" class="form-control" id="computerName" name="name" value="${computer.name}">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="label.introduced"></spring:message></label>
                                <input type="text" class="form-control" id="introduced" name="intro" value="${parseIntro }">
                                <div id="errorIntro"><c:if test="${errorIntro != null}"><spring:message code="${errorIntro }"></spring:message></c:if></div>
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="label.discontinued"></spring:message></label>
                                <input type="text" class="form-control" id="discontinued" name="disco" value="${parseDisco }">
                                <div id="errorDisco"><c:if test="${errorDisco != null}"><spring:message code="${errorDisco }"></spring:message></c:if></div>
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="label.company"></spring:message></label>
                                <select class="form-control" id="companyId" name="comp">
                                	<option value="-1">--</option>
                                <c:forEach items="${companies}" var="company">
                                   	<option value="${company.id }" 
                                   		<c:if test="${company.id == computer.company.id }">
                                   		selected</c:if>>${company.name }</option>
								</c:forEach>
                                </select>
                            </div>      
                            <div><c:if test="${errorComp != null}"><spring:message code="${errorComp }"></spring:message></c:if></div>      
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="label.edit"></spring:message>" class="btn btn-primary" id="submitEdit">
                            <spring:message code="label.or"></spring:message>
                            <a href="dashboard" class="btn btn-default" id="cancelEdit"><spring:message code="label.cancel"></spring:message></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.validate.min.js" />"></script>
	<script src="<c:url value="/resources/js/validate.js" />"></script>
</body>
</html>
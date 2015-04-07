<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<!DOCTYPE html>
<html>
	<mylib:header />
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form action="addComputer" method="POST" id="addComputer">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="name" placeholder="Computer name" value="${name }">
                                <div id="errorName"><c:if test="${errorName != null}">${errorName }</c:if></div>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="text" class="form-control" id="introduced" name="intro" placeholder="Introduced date" value="${intro }">
                                <div id="errorIntro"><c:if test="${errorIntro != null}">${errorIntro }</c:if></div>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="text" class="form-control" id="discontinued" name="disco" placeholder="Discontinued date" value="${disco }">
                                <div id="errorDisco"><c:if test="${errorDisco != null}">${errorDisco }</c:if></div>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="comp">
                                	<option value="-1">--</option>
	                                <c:forEach items="${companies}" var="company">
	                                   	<option value="${company.id }"
	                                   	<c:if test="${company.id == comp }">
                                   		selected</c:if>>${company.name }</option>
									</c:forEach>
                                </select>
                            </div> 
                            <div><c:if test="${errorComp != null}">${errorComp }</c:if></div>                 
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default" id="cancelAdd">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/validate.js"></script>
</body>
</html>
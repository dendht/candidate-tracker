<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
    <head>
        <title>Welcome to CMT - Candidates management tool!</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Reference Bootstrap files -->
		<link rel="stylesheet"
			 href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2 class="p-3 mb-2 bg-secondary text-white" style="text-align: center;">Candidates management tool</h2>
            </div>
        </div>
        <div id="container" style="margin-left: 20px;margin-right: 20px;">
            <div id="content">
                <p style="float: right;">
                    <sec:authorize access="isAuthenticated()">
                        You're logged as: ${userName} (<security:authentication property = "principal.username" />, ${userEmail})
                        </br>
                        Role(s): <security:authentication property = "principal.authorities" />
                    </sec:authorize>
                </p>
                </br>
                <div style="margin-bottom: 20px;">
                    <input type="button" value="Add candidate"
                        onClick="window.location.href='showAddForm'; return false;"
                        class="btn btn-secondary btn-sm" />
                    <br/>
                    <!-- add a search box -->
                    <form:form action="search" method="GET">
                        Search candidate:
                        <div class="input-group mb-3" style="width: 35%;">
                            <input class="form-control" placeholder="Candidate first/last name"
                                type="text" name="searchName" value="${searchName}" aria-label="Candidate first/last name" aria-describedby="basic-addon2"/>
                            <div class="input-group-append">
                                <input type="submit" value="Search" class="btn btn-outline-secondary" />
                            </div>
                        </div>
                    </form:form>
                </div>
                <table class="table table-sm table-bordered table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th>id</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- loop over candidates to display them -->
                    <c:forEach var="candidate" items="${candidates}">
                        <!-- create an update link on candidate id-->
                        <c:url var="updateLink" value="/candidate/showUpdateForm">
                            <c:param name="candidateId" value="${candidate.id}" />
                        </c:url>
                        <!-- create a delete link on candidate id-->
                        <c:url var="deleteLink" value="/candidate/deleteCandidate">
                            <c:param name="candidateId" value="${candidate.id}" />
                        </c:url>

                        <tr>
                            <td>${candidate.id}</td>
                            <td>${candidate.firstName}</td>
                            <td>${candidate.lastName}</td>
                            <td>${candidate.email}</td>
                            <td>
                                <security:authorize access = "hasRole('MANAGER') || hasRole('ADMIN')">
                                    <a href="${updateLink}" class="btn btn-secondary btn-sm">Edit</a>
                                </security:authorize>
                                <security:authorize access = "hasRole('ADMIN')">
                                    <a href="${deleteLink}"
                                     onClick="if(!confirm('Are you sure you want to delete candidate?')) return false"
                                     class="btn btn-danger btn-sm">Delete</a>
                                </security:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <form:form action = "${pageContext.request.contextPath}/logout" method = "POST">
                <input type = "submit" value = "Logout" class="btn btn-dark btn-sm" />
            </form:form>
        </div>
        <br/>
    </body>
</html>
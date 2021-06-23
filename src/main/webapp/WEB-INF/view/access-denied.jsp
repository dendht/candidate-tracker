<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Access denied</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
         href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
    <body>
        <h2 class="p-3 mb-2 bg-secondary text-white" style="text-align: center;">Access denied. You are not allowed to see this page</h2>
        <hr>
        <div id="container" style="margin-left: 20px;margin-right: 20px;">
            <a href = "${pageContext.request.contextPath}/"
             class="btn btn-secondary btn-sm" style="margin-bottom: 10px;">Back to home</a>
            <form:form action = "${pageContext.request.contextPath}/logout" method = "POST">
                <input type = "submit" value = "Logout" class="btn btn-danger btn-sm"/>
            </form:form>
        </div>
    </body>
</html>
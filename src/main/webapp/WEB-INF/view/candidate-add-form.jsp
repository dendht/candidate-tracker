<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
    <head>
        <title>Save candidate</title>
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
            <h3 class="p-3 mb-2">Add new candidate</h3>
            <div class="input-group mb-3" style="width: 35%;">
                <form:form action="saveCandidate" modelAttribute="candidate" method="POST">
                    <!--associate data with candidate id-->
                    <form:hidden path="id" />
                    <table>
                        <tbody>
                            <tr>
                                <td><label>First name:</label></td>
                                <td><form:input path="firstName" placeholder="First name" class="form-control"/></td>
                            </tr>
                            <tr>
                                <td><label>Last name:</label></td>
                                <td><form:input path="lastName" placeholder="Last name" class="form-control"/></td>
                            </tr>
                            <tr>
                                <td><label>Email:</label></td>
                                <td><form:input path="email" placeholder="Email" class="form-control"/></td>
                            </tr>
                            <tr>
                                <td><label></label></td>
                                <td><input type="submit" value="Save" class="btn btn-secondary btn-sm"/></td>
                            </tr>
                        </tbody>
                    </table>
                </form:form>
            </div>

            <div style="clear; both;"></div>
            <p>
                <a href="/candidate/list" class="btn btn-dark btn-sm">Back to list</a>
            </p>
        </div>
    </body>
</html>
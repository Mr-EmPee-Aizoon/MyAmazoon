<%@ page import="tk.empee.myamazoon.model.dto.products.Category" %>
<%@ page import="tk.empee.myamazoon.model.dto.users.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>My Amazoon</title>

    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="../../../css/common/header.css" rel="stylesheet">
    <link href="../../../css/users/admin/dashboard.css" rel="stylesheet">

    <script src="../../../scripts/validators/userValidator.js"></script>
</head>

<body>
<%@include file="../../../includes/nav/nav.jsp" %>

<main>
    <%@include file="../includes/sidebar.jsp" %>

    <section>

        <form class="row g-3" action="${param.action}" method="post" onsubmit="return validateForm(this);">
            <input id="userID" name="userID" value="${requestScope.user.id}" hidden>
            <div class="row d-flex flex-column align-items-center">
                <h2 class="fs-2 m-0 text-black text-center" style="font-variant: small-caps;">${requestScope.title}</h2>
                <p id="errorMessage" class="text-danger small"></p>
            </div>
            <div class="col-md-6">
                <label for="name">Nome: </label>
                <input id="name" type="text" class="form-control" placeholder="Nome" name="name" value="${requestScope.user.name}">
            </div>
            <div class="col-md-6">
                <label for="surname">Cognome: </label>
                <input id="surname" type="text" class="form-control" placeholder="Cognome" name="surname" value="${requestScope.user.surname}">
            </div>
            <div class="col-md-6">
                <label for="username">Username: </label>
                <input id="username" type="text" class="form-control" placeholder="Username" name="username" value="${requestScope.user.username}">
            </div>
            <div class="col-md-6">
                <label for="role">Ruolo:</label>
                <select id="role" name="role" class="form-control">
                    <option>Scegli...</option>

                    <c:forEach items="${Role.values()}" var="c">
                        <option value="${c.name()}" ${c.equals(requestScope.user.role) ? "selected" : ""}>
                            ${c.name().replace("_", " ").toLowerCase()}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-12">
                <label for="password">Password: </label>
                <input id="password" type="password" class="form-control" placeholder="Password" name="password">
            </div>

            <div class="col-6">
                <button type="submit" class="btn btn-success w-100">${requestScope.sendButton}</button>
            </div>
            <div class="col-6">
                <button type="reset" class="btn btn-danger w-100">Elimina</button>
            </div>
        </form>

    </section>
</main>

</body>
</html>

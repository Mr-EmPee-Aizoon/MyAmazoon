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
    <link href="../../../css/users/admin/usersList.css" rel="stylesheet">

    <script src="../../../scripts/usersManager.js"></script>
</head>

<body>
<%@include file="../../../includes/nav/nav.jsp" %>


<main>
    <%@include file="../includes/sidebar.jsp" %>

    <section>

        <div class="container w-100 m-4 d-flex justify-content-between">
            <h2 style="font-variant: small-caps;" class="m-0">riepilogo utenti presenti</h2>
            <a href="form?action=createUser">
                <button class="btn btn-success">Nuovo Utente</button>
            </a>
        </div>
        <table class="table bg-light table-bordered table-striped table-hover" id="usersTable">
            <thead>
            <tr>
                <th>Username</th>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Ruolo</th>
            </tr>
            </thead>
            <tbody id="tableBody">
                <c:forEach items="${requestScope.users}" var="u">
                    <tr>
                        <td>${u.username}</td>
                        <td>${u.name}</td>
                        <td>${u.surname}</td>
                        <td>${u.role}</td>
                        <td class="text-center">
                            <a href="form?action=updateUser&userID=${u.id}"><button class="btn btn-sm btn-warning">Modifica</button></a>
                            <button class="btn btn-sm btn-danger" onclick="removeUserFromList(this, '${u.id}')">
                                Cancella
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </section>
</main>

</body>
</html>

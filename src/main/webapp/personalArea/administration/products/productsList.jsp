<%@ page import="tk.empee.myamazoon.model.dto.products.Category" %>
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
    <link href="../../../css/users/admin/productList.css" rel="stylesheet">

    <script src="../../../scripts/productsManager.js"></script>
</head>

<body>
<%@include file="../../../includes/nav/nav.jsp" %>


<main>
    <%@include file="../includes/sidebar.jsp" %>

    <section>

        <div class="container w-100 m-4 d-flex justify-content-between">
            <h2 style="font-variant: small-caps;" class="col-6 m-0">riepilogo prodotti presenti</h2>

            <select name="category" class="col-3 mx-2" onchange="filterProducts(this.value)">
                <option value="">Non filtrare</option>
                <c:forEach items="${Category.values()}" var="c">
                    <option value="${c.name()}" ${c.name().equals(param.category) ? "selected" : ""}>
                        Filtra per ${c.name().replace("_", " ").toLowerCase()}
                    </option>
                </c:forEach>
            </select>

            <a href="form?action=createProduct">
                <button class="btn btn-success">Nuovo Prodotto</button>
            </a>
        </div>
        <table class="table bg-light table-bordered table-striped table-hover" id="productsTable">
            <thead class="table">
            <tr>
                <th>Nome</th>
                <th>Descrizione</th>
                <th>Categoria</th>
                <th>Prezzo</th>
            </tr>
            </thead>
            <tbody id="tableBody">

            <c:forEach items="${requestScope.products}" var="p">
                <tr>
                    <td>${p.title}</td>
                    <td>${p.description}</td>
                    <td>${p.category}</td>
                    <td class="text-right">${p.price} €</td>
                    <td class="text-center">
                        <a href="form?action=updateProduct&prodID=${p.id}"><button class="btn btn-sm btn-warning">Modifica</button></a>
                        <button class="btn btn-sm btn-danger" onclick="removeProductFromList(this, '${p.id}')">Cancella</button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

    </section>
</main>

</body>
</html>

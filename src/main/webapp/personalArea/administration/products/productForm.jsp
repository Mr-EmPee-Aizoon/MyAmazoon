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

    <script src="../../../scripts/validators/productValidator.js"></script>
</head>

<body>
<%@include file="../../../includes/nav/nav.jsp" %>

<%
    //Block if trying to access directly
    if(request.getAttribute("title") == null) {
        response.setStatus(500);
        return;
    }
%>

<main>
    <%@include file="../includes/sidebar.jsp" %>

    <section>

        <form class="row g-3" action="${param.action}" method="post" onsubmit="return validateProduct(this);">
            <input id="prodID" name="prodID" value="${requestScope.product.id}" hidden>
            <div class="row d-flex flex-column align-items-center">
                <h2 class="fs-2 m-0 text-black text-center" style="font-variant: small-caps;">
                    ${requestScope.title}
                </h2>
                <p id="errorMessage" class="text-danger small"></p>
            </div>
            <div class="col-md-6">
                <label for="name">Titolo prodotto: </label>
                <input id="name" type="text" class="form-control" placeholder="Titolo" name="title" value="${requestScope.product.title}">
            </div>
            <div class="col-md-6">
                <label for="category">Categoria:</label>
                <select id="category" name="category" class="form-control">
                    <option>Scegli...</option>
                    <c:forEach items="${Category.values()}" var="c">
                        <option value="${c.name()}" ${c.equals(requestScope.product.category) ? "selected" : ""}>
                            ${c.name().replace("_", " ").toLowerCase()}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-6">
                <label for="price">Prezzo: </label>
                <input id="price" type="number" class="form-control" placeholder="€" name="price" value="${requestScope.product.price}">
            </div>
            <div class="col-md-6">
                <label for="date">Data inserimento: </label>
                <input id="date" type="date" class="form-control" name="date">
            </div>
            <div class="col-12">
                <label for="description" class="form-label">Descrizione Prodotto:</label>
                <textarea id="description" type="text" class="form-control" rows="3" placeholder="Descrivi il prodotto" name="description" style="height: 10rem">${requestScope.product.description}</textarea>
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

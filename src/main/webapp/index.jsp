<%@ page import="java.util.List" %>
<%@ page import="tk.empee.myamazoon.model.dto.products.Product" %>
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

    <link href="css/common/header.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">

</head>

<body>

<%@include file="includes/nav/nav.jsp" %>

<main>
    <div id="main-container">
        <aside>
            <ul>
                <li>
                    <a href="index" class="active">Tutte le categorie</a>
                    <ul> <!-- TODO DYNAMIC ACTIVE -->
                        <c:forEach items="<%=Category.values() %>" var="c">
                            <li><a href="?category=${c}" >${c.name().toLowerCase().replace("_", " ")}</a></li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>
        </aside>
        <div class="separator"></div>
        <section>
            <div id="productWindow" class="card-group">
                <c:forEach items="${requestScope.products}" var="p">
                    <div class="card p-4">
                        <div style="background-image: url('images/products/${p.imagePath}')" class="card-img-top"></div>
                        <div class="card-body">
                            <h5 class="card-title">${p.title}</h5>
                            <p class="card-text">${p.description}</p>
                            <div class="card-text card-end fs-5 fw-bold">
                                ${p.price} €
                                <button class="btn btn-success ms-3 p-2">Add to cart</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
    </div>

</main>

</body>
</html>

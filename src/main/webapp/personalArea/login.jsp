<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <link href="../css/common/header.css" rel="stylesheet">
    <link href="../css/users/login.css" rel="stylesheet">

    <script src="../scripts/validators/loginValidator.js"></script>

</head>

<body>

<%@include file="../includes/nav/nav.jsp" %>

<main>
    <form id="login-form" action="login" method="post" onsubmit="return validateForm(this)">
        <div class="container mt-4 mb-3 d-flex flex-column">
            <h1 class="fs-2 m-0 text-black text-center fw-bold">LOGIN FORM</h1>
            <p id="errorMessage" class="text-danger small">
                <br>
                ${requestScope.errorMessage}
            </p>
        </div>

        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" type="text" class="form-control" name="username">
        </div>
        <div class="form-group mt-2">
            <label for="password">Password</label>
            <input id="password" type="password" class="form-control" name="password">
        </div>

        <div class="form-check mt-2">
            <input id="rememberCredentials" class="form-check-input" type="checkbox" name="remember-credentials"
                   value="">
            <label class="form-check-label" for="rememberCredentials">
                Remember Me
            </label>
        </div>

        <button type="submit" class="btn w-100 btn-success mt-5">Login</button>
    </form>
</main>

</body>
</html>

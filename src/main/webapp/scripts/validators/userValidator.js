let errorMessage;

window.onload = function() {
    errorMessage = document.getElementById("errorMessage");
}

function validateForm(form) {

    let formElements = form.elements;
    let error = "";

    if(formElements["name"].value.length == 0) {
        error += "<br>Nome non valido"
    }

    if(formElements["surname"].value.length == 0) {
        error += "<br>Cognome non valido"
    }

    if (formElements["username"].value.length < 5) {
        error += "<br>Lo username deve essere lungo almeno 5 caratteri"
    }

    if(formElements["role"].selectedIndex == 0) {
        error += "<br>Devi scegliere un ruolo"
    }

    if (formElements["password"].value.length == 0) {
        error += "<br>La password non è valida"
    }

    if(error.length == 0) {
        errorMessage.innerHTML = "";
        return true;
    } else {
        errorMessage.innerHTML = error;
        return false;
    }
}


let errorMessage;

window.onload = function() {
    errorMessage = document.getElementById("errorMessage");
}

function validateForm(form) {

    let formElements = form.elements;
    let error = "";

    if(formElements["username"].value.length < 5) {
        error += "<br>Username non valido"
    }

    if(formElements["password"].value.length == 0) {
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
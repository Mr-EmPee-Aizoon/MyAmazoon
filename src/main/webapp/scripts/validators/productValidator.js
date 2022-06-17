let errorMessage;

window.onload = function() {
    errorMessage = document.getElementById("errorMessage");
}

function validateProduct(form) {

    let formElements = form.elements;
    let error = "";

    if(formElements["title"].value.length == 0) {
        error += "<br>Il titolo deve essere presente";
    }

    if(formElements["price"].value.length == 0) {
        error += "<br>Inserisci un prezzo";
    }

    if(formElements["description"].value.length == 0) {
        error += "<br>Inserisci una descrizione";
    }

    if(formElements["category"].selectedIndex == 0) {
        error += "<br>Devi scegliere una categoria";
    }

    let productDate = formElements["date"].valueAsDate;
    if(productDate != null) {
        let currentDate = new Date();
        if(productDate.getTime() > currentDate.getTime()) {
            error += "<br>La data di inserimento non può essere nel futuro"
        } else if(productDate.getYear() != currentDate.getYear()) {
            error += "<br>La data di inserimento non può essere in un anno diverso da quello attuale";
        } 
    } else {
        error += "<br>Devi scegliere una data di inserimento"
    }

    if(error.length == 0) {
        errorMessage.innerHTML = "";
        return true;
    } else {
        errorMessage.innerHTML = error;
        return false;
    }

}
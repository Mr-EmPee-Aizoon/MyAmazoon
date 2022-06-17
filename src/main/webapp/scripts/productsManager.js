function filterProducts(category) {
    if(category != "") {
        window.location = "productsList?category=" + category;
    } else {
        window.location = "productsList";
    }
}

function removeProductFromList(e, id) {
    let request = new XMLHttpRequest();
    request.open("GET", "removeProduct?prodID=" + id, true)
    request.send();

    document.getElementById("tableBody").removeChild(e.parentElement.closest('tr'));
}
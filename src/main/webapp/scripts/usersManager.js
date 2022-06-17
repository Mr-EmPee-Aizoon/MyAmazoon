function removeUserFromList(e, id) {
    let request = new XMLHttpRequest();
    request.open("GET", "removeUser?userID=" + id, true)
    request.send();

    document.getElementById("tableBody").removeChild(e.parentElement.closest('tr'));
}
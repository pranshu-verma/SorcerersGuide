function toMarkup(text) {
    text = text.replace(/\*\*(.*?)\*\*/g, "<b>$1</b>");
    text = text.replace(/__(.*?)__/g, "<u>$1</u>");
    text = text.replace(/~~(.*?)~~/g, "<i>$1</i>");
    text = text.replace(/--(.*?)--/g, "<del>$1</del>");
    text = text.replace(/(?:\r\n|\r|\n)/g, " <br>");
    text = text.replace(/(?<!!)(https?:\/\/[^\s]+)/g, "<a href='$1'>$1</a>");
    text = text.replace(/!!(.*?)!!/g, "<img src='$1' style='width:100%;'>");
    return text;
}

let query_bodies = document.querySelectorAll(".query-body p");
let current_page_select = document.getElementById("currentPageSelect");

for (let query_body of query_bodies) {
    query_body.innerHTML = toMarkup(query_body.textContent);
}


function enableAccordion() {
    var queries = document.getElementsByClassName("query-heading");

    for (let i = 0; i < queries.length; i++) {
        queries[i].addEventListener("click", function () {
            this.classList.toggle("query-active");
            var query = this.nextElementSibling;
            if (query.style.maxHeight) {
                query.style.maxHeight = null;
            } else {
                query.style.maxHeight = query.scrollHeight + "px";
            }
        });
    }
}

enableAccordion();

$(function(){
    // bind change event to select
    $('#currentPageSelect').on('change', function () {
        var url = $(this).val(); // get selected value
        if (url) { // require a URL
            window.location = url; // redirect
        }
        return false;
    });
  });
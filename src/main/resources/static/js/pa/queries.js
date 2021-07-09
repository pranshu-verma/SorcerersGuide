let query_bodies = document.querySelectorAll(".query-body p");
let current_page_select = document.getElementById("currentPageSelect");

for (let query_body of query_bodies) {
    query_body.innerHTML = toMarkdown(query_body.textContent);
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
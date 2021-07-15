let some = document.querySelectorAll(".update-body p");

for (let s of some) {
    s.innerHTML = toMarkdown(s.textContent);
}


function enableAccordion() {
    var updates = document.getElementsByClassName("update-heading");

    for (let i = 0; i < updates.length; i++) {
        updates[i].addEventListener("click", function () {
            this.classList.toggle("update-active");
            var update_body = this.nextElementSibling;
            if (update_body.style.maxHeight) {
                update_body.style.maxHeight = null;
            } else {
                update_body.style.maxHeight = update_body.scrollHeight + "px";
            }
        });
    }
}

enableAccordion();
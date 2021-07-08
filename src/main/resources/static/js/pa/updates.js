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

let some = document.querySelectorAll(".update-body p");

for (let s of some) {
    s.innerHTML = toMarkup(s.textContent);
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
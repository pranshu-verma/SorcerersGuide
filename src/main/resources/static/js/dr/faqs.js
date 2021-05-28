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

let faq_bodies = document.querySelectorAll(".faq-body p");

for (let faq_body of faq_bodies) {
    faq_body.innerHTML = toMarkup(faq_body.textContent);
}


function enableAccordion() {
    var faqs = document.getElementsByClassName("faq-heading");

    for (let i = 0; i < faqs.length; i++) {
        faqs[i].addEventListener("click", function () {
            this.classList.toggle("faq-active");
            var faq = this.nextElementSibling;
            if (faq.style.maxHeight) {
                faq.style.maxHeight = null;
            } else {
                faq.style.maxHeight = faq.scrollHeight + "px";
            }
        });
    }
}

enableAccordion();
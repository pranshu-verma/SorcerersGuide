let faq_bodies = document.querySelectorAll(".faq-body p");

for (let faq_body of faq_bodies) {
    faq_body.innerHTML = toMarkdown(faq_body.textContent);
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
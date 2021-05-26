var btn_toggle_theme = document.getElementById("btn-toggle-theme");

function setTheme(theme) {
    var body = document.querySelector("body");

    if (theme === "dark") {
        body.classList.add("dark");
        btn_toggle_theme.firstElementChild.className = "fa fa-sun text-warning";
        return;
    }
    body.classList.remove("dark");
    btn_toggle_theme.firstElementChild.className = "fa fa-moon text-black";
}

let theme = localStorage.getItem("theme");
setTheme(theme);

btn_toggle_theme.addEventListener("click", () => {
    var body = document.querySelector("body");
    body.style.transition = "background-color 0.5s";

    let theme = localStorage.getItem("theme");
    if (theme == "light") {
        btn_toggle_theme.firstElementChild.className = "fa fa-sun text-warning";
        localStorage.setItem("theme", "dark");
    } else {
        btn_toggle_theme.firstElementChild.className = "fa fa-moon text-black";
        localStorage.setItem("theme", "light");
    }
    theme = localStorage.getItem("theme");
    setTheme(theme);
})
    
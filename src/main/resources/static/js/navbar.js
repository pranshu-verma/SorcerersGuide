function openNav() {
    document.getElementById("side-navbar").style.width = "250px";
    document.getElementById("side-navbar-btn-close").style.marginLeft = "250px";
    document.getElementById("side-navbar-btn-open").style.display = "none";
    document.getElementById("content").style.filter = "blur(4px)";
}

function closeNav() {
    document.getElementById("side-navbar").style.width = "0";
    document.getElementById("side-navbar-btn-close").style.marginLeft = "-100px";
    document.getElementById("side-navbar-btn-open").style.display = "inline-block";
    document.getElementById("content").style.filter = "blur(0px)";
}
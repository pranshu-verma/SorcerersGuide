let sidebarNavWidth = "300px";

function openNav() {
    document.getElementById("side-navbar").style.width = sidebarNavWidth;
    document.getElementById("side-navbar-btn-close").style.marginLeft = sidebarNavWidth;
    document.getElementById("side-navbar-btn-open").style.display = "none";
    document.getElementById("content").style.filter = "blur(4px)";
}

function closeNav() {
    document.getElementById("side-navbar").style.width = "0";
    document.getElementById("side-navbar-btn-close").style.marginLeft = "-100px";
    document.getElementById("side-navbar-btn-open").style.display = "inline-block";
    document.getElementById("content").style.filter = "blur(0px)";
}

document.getElementById("content").addEventListener("click", closeNav);
document.getElementById("side-navbar-btn-close").addEventListener("click", closeNav);
document.getElementById("side-navbar-btn-open").addEventListener("click", openNav);


$(function() {
    $('[data-toggle="popover"]').popover();
});

$('.popover-dismiss').popover({
    trigger: 'focus'
});
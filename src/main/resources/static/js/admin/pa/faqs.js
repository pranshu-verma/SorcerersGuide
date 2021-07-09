var file_upload_form = document.querySelector('#file-upload');
var delete_form = document.querySelector('#delete-form');
var file_input = document.querySelector('#file-input');
var error_msg = document.querySelector('#error-msg');
var success_msg = document.querySelector('#success-msg');
var response_msg = document.querySelector('#response-msg');


function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);
    formData.append("table", "faqs");

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/admin/upload");

    xhttp.onload = function() {
        console.log(xhttp.responseText);
        var response = JSON.parse(xhttp.responseText);
        if (xhttp.status == 200) {
            error_msg.style.display = "none";
            success_msg.innerHTML = `
                <p>${response.message}</p>
                <p>DownloadUrl : 
                    <a href="${response.fileDownloadUri}" target="_blank">${response.fileDownloadUri}</a>
                </p>
                `;
            success_msg.style.display = "block";
        } else {
            success_msg.style.display = "none";
            error_msg.innerHTML = "<p>" + (response && response.message) + "<p>" || "Some Error Occurred";
            success_msg.style.display = "block";

        }
    }

    xhttp.send(formData);
}

function deleteAllFaqs() {
    var formData = new FormData();
    formData.append("table", "faqs");
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/admin/delete");

    xhttp.onload = function() {
        console.log(xhttp.responseText);
        var response = JSON.parse(xhttp.responseText);
        response_msg.innerHTML = response.message;
    }

    xhttp.send(formData);
}

file_upload_form.addEventListener('submit', function(event){
    var files = file_input.files;
    if (files.length === 0) {
        error_msg.innerHTML = "Please select a file";
        error_msg.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);


delete_form.addEventListener("submit", (e) => {
    deleteAllFaqs();
    e.preventDefault();
}, true);
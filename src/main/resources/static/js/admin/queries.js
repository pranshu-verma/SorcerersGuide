var file_upload_form = document.querySelector('#file-upload');
var file_input = document.querySelector('#file-input');
var error_msg = document.querySelector('#error-msg');
var success_msg = document.querySelector('#success-msg');


function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);
    formData.append("table", "queries");

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/admin/upload");

    xhttp.onload = function() {
        console.log(xhttp.responseText);
        var response = JSON.parse(xhttp.responseText);
        if (xhttp.status == 200) {
            error_msg.style.display = "none";
            success_msg.innerHTML = `
                <p>File Uploaded Successfully.</p>
                <p>DownloadUrl : 
                    <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a>
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

file_upload_form.addEventListener('submit', function(event){
    var files = file_input.files;
    if (files.length === 0) {
        error_msg.innerHTML = "Please select a file";
        error_msg.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);

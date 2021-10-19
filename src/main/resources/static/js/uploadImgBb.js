function uploadFile() {
  var file = document.getElementById("fileOb");
  var form = new FormData();
  form.append("image", file.files[0]);
  var inputs = {
    url: "https://api.imgbb.com/1/upload?key=85d8dd2bea41b501d407820831fdde71",
    method: "POST",
    timeout: 0,
    processData: false,
    mimeType: "multipart/form-data",
    contentType: false,
    data: form
  };

  $.ajax(inputs).done(function (response) {
    var job = JSON.parse(response);
    //for image dispaly in Doctor/Edit page
    $("#photoLoc").attr('src',job.data.url);
    
    $("#photoLoc").val(job.data.url);
  });
}
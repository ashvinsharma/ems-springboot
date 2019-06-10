var designation= {};

$(document).ready(function () {
    $.get("/designation/read/all", function (data) {
        $.each(data.content, function () {
            $('#designation').append($('<option />').val(this.id).text(this.name));
        });
    });

    $("#create-record").submit(function (event) {
        event.preventDefault();
        submit_entry();
    });

});
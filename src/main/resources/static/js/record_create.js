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

function submit_entry() {
    var formObject = {};
    var designationObject = {};
    formObject['name'] = $('#name').val().trim();
    formObject['email'] = $('#email').val().trim();
    designationObject['id'] = $('#designation').val();
    designationObject['name'] = $('#designation option:selected').html();
    formObject['designationId'] = JSON.stringify(designationObject);
    formObject['contact'] = $('#contact').val().trim();

    $.ajax({
        type: 'POST',
        url: '/employee/create',
        data: formObject,
        success: function (response) {
            if (response.content === 'success') {
                window.location.replace('/');
            }
        }
    });
}
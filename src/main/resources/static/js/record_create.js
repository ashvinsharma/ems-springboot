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
    var object = {};
    var desgObject = {};
    object['name'] = $('#name').val().trim();
    object['email'] = $('#email').val().trim();
    desgObject['id'] = $('#designation').val();
    desgObject['name'] = $('#designation option:selected').html();
    object['designationId'] = JSON.stringify(desgObject);
    object['contact'] = $('#contact').val().trim();

    $.ajax({
        type: 'POST',
        url: '/employee/create',
        data: object,
        success: function (response) {
            if (response.content === 'success') {
                window.location.replace('/');
            }
        }
    });
}

// function fire_ajax_submit() {
//
//     var search = {};
//     search["username"] = $("#username").val();
//     //search["email"] = $("#email").val();
//
//     $("#btn-search").prop("disabled", true);
//
//     $.ajax({
//         type: "POST",
//         contentType: "application/json",
//         url: "/api/search",
//         data: JSON.stringify(search),
//         dataType: 'json',
//         cache: false,
//         timeout: 600000,
//         success: function (data) {
//
//             var json = "<h4>Ajax Response</h4><pre>"
//                 + JSON.stringify(data, null, 4) + "</pre>";
//             $('#feedback').html(json);
//
//             console.log("SUCCESS : ", data);
//             $("#btn-search").prop("disabled", false);
//
//         },
//         error: function (e) {
//
//             var json = "<h4>Ajax Response</h4><pre>"
//                 + e.responseText + "</pre>";
//             $('#feedback').html(json);
//
//             console.log("ERROR : ", e);
//             $("#btn-search").prop("disabled", false);
//
//         }
//     });
//
// }
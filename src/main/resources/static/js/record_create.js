$(document).ready(() => {
    $.get('/designation/read/all', data => {
        $.each(data.content, function () {
            $('#designation').append($('<option />').val(this.id).text(this.name));
        });
    });

    $('#create-record').submit(event => {
        event.preventDefault();
        submit_entry();
    });

});

const submit_entry = () => {
    const formObject = {};
    const designationObject = {};
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
        success: response => {
            if (response.content === 'success') {
                window.location.replace('/');
            }
        }
    });
};
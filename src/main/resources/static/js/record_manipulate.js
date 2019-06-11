let designation = {};

$(document).ready(async () => {
    await $.get('/designation/read/all', data => {
        designation = data.content;
        // put the data in the select options of all the entries in the table
    }).then();

    const params = new URL(window.location.href).searchParams;
    let url;
    if (params.has('email')) {
        url = `/employee/read/${params.get('email')}`;
    } else {
        url = '/employee/read/all';
    }

    $.get(url, data => {
        const employee = data.content;
        populate_table(employee);
        // update table contents
    });

    $('#search-record').submit(event => {
        event.preventDefault();
        submit_search_query(event);
    });


});

const populate_table = employee => {
    $.each(employee, function () {
        const $tr = $(`<tr id='row_${this.id}'>`).append(
            $(`<th scope="row" id='id_${this.id}'>`).text(this.id),
            $(`<td id='email_${this.id}'>`).text(this.email).wrapInner('<div contenteditable>'),
            $(`<td id='name_${this.id}'>`).text(this.name).wrapInner('<div contenteditable>'),
            $(`<td id='contact_${this.id}'>`).text(this.contact).wrapInner('<div contenteditable>'),
            $(`<td id='action_${this.id}'>`).append(`<button type='button' id='update_${this.id}'>Update</button>` +
                `<button type='button' id='delete_${this.id}'>Delete</button>`)
        );
        $('#tbody-employees').append($tr);
    });
};
const submit_search_query = event => {
    window.location.href = `/record?email=${event.target[0].value.trim()}`
};

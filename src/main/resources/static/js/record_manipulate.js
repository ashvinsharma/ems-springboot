let designation = {};

$(document).ready(async () => {
    await $.get('/designation/read/all', data => {
        designation = data.content;
    });

    const params = new URL(window.location.href).searchParams;
    const url = params.has('email') ? `/employee/read/${params.get('email')}` : '/employee/read/all';
    $.get(url, data => {
        populate_table(data.content);
    });

    $('#search-record').submit(event => {
        event.preventDefault();
        submit_search_query(event);
    });

    $(document).on('click', '.update_button', function () {
        const id = $(this).parent().closest('tr').attr('id');
        const email = $(`#email_${id}`).trim();
        const name = $(`#name_${id}`).trim();
        const contact = $(`#contact_${id}`).trim();
    });

    $(document).on('click', '.delete_button', function () {
        const id = $(this).parent().closest('tr').attr('id');
        click_delete_button(id);
    });
});

const click_delete_button = id => {
    $.get(`/employee/delete/${id}`, ({ content }) => {
        if (content === 'success') {
            window.location.reload();
        }
    });
};


const populate_table = employee => {
    $.each(employee, function () {
        const $select = $('<select></select>').attr('id', `designation_${this.id}`);
        designation.map(d => {
            $select.append($('<option />').val(d.id).text(d.name)
                .attr('selected', (this.designationId.id === d.id)));
        });
        const $tr = $(`<tr id=${this.id}>`).append(
            $(`<th scope="row" id='id_${this.id}'>`).text(this.id),
            $(`<td id='email_${this.id}'>`).text(this.email).wrapInner('<div contenteditable>'),
            $(`<td id='name_${this.id}'>`).text(this.name).wrapInner('<div contenteditable>'),
            $(`<td id='designationId_${this.id}'>`).append($select),
            $(`<td id='contact_${this.id}'>`).text(this.contact).wrapInner('<div contenteditable>'),
            $(`<td id='action_${this.id}'>`).append(`<button type='button' id='update_${this.id}' class='update_button'>Update</button>` +
                `<button type='button' id='delete_${this.id}' class='delete_button'>Delete</button>`)
        );
        $('#tbody-employees').append($tr);
    });
};
const submit_search_query = event => {
    window.location.href = `/record?email=${event.target[0].value.trim()}`
};

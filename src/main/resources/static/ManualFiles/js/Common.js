function ajaxCallPost(url,data){
    let response;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(data),
        dataType: 'json',
        cache: false,
        async:false,
        success: function (data) {
            response=data;
            console.log(data);
        },
        error: function (xhr, status, error) {
            ErrorMessage(error+"--"+status);
            response=data;
            console.log(data);

        }
    });
    return response;
}
function SuccessMessage(message){
    Swal.fire({
        icon:'success',
        title:message
    });
}
function SubmitMessage(message){
    Swal.fire({
        icon:'success',
        title:message,
        timer: 1500
    });
}
function ErrorMessage(message){
    Swal.fire({
        icon:'error',
        title:message
    });
}
function DestroyDataTable(id){
    if($.fn.DataTable.isDataTable('#'+id)){
        $('#'+id).DataTable().destroy();
        $('#'+id).empty();
    }

}

$(function(){
    var url = window.location;
    // for single sidebar menu
    $('ul.nav-sidebar a').filter(function () {
        return this.href == url;
    }).addClass('active');

    // for sidebar menu and treeview
    $('ul.nav-treeview a').filter(function () {
        return this.href == url;
    }).parentsUntil(".nav-sidebar > .nav-treeview")
       
        .addClass('menu-open').prev('a')
        .addClass('active');
});


$(document).ready(function () {

});

function makeEntry(){
    let name=$("#name").val();
    let email=$("#email").val();
    let phone=$("#phone").val();
    let purpose=$("#purpose").val();
    let postData={'name':name,'email':email,'phone':phone,'purpose':purpose};
    SubmitMessage("Submitting Data");
    setTimeout(function() {
        console.log(postData);
        let url="/submitEntry";
        let data=ajaxCallPost(url,postData);
        if(data['data'].includes("Success")){
            SuccessMessage('Success! <br\> Entry logged.');
        }
        else{
            ErrorMessage("Some error Encountered");
        }
        console.log(data);

    }, 2500);



}
function clearForm(){
    $("#name").val("");
    $("#email").val('');
    $("#phone").val("");
    $("#purpose").val("");

    SuccessMessage("Cleared");



}
function SubmitExit(){
    let searchStr=$("#SearchVisitor").val();
    setTimeout(function() {
        let postData={'searchStr':searchStr};
        let url="/searchVisitorForExit";
        let data=ajaxCallPost(url,postData);
        console.log(data);
        CreateTableForExitSuggestions(data['data']);

    }, 1000);

}
function ClearSuggestions(){
    DestroyDataTable('SuggestionForExitTable');

}

function CreateTableForExitSuggestions(data){
    DestroyDataTable('SuggestionForExitTable');
    $('#SuggestionForExitTable').DataTable({
        data: data,
        columns: [
            // { title: 'Id', visible: false},
            { title: 'Name',data:'1' },
            // { title: 'Email' , visible: false},
            { title: 'Contact' ,data:'3'},
            { title: 'Visiting From' ,data:'4'},
            { title: 'InTime',data:'5' },
            // { title: 'ExitTime', visible: false },
            // { title: 'Purpose', visible: false },
            {
                title: 'Options' ,
                data:'0',
                render:function (data,type,row,meta){
                    return "<div id='"+data+"' onClick=submitExit(this.id) class='btn btn-primary'>Exit</div>";
                }
            }


        ],
    });
}


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
        },
        error: function (xhr, status, error) {
            ErrorMessage(error+"--"+status);
            response=data;

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
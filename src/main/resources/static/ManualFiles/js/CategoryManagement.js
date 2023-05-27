let catData;
$(document).ready(function () {

    $('#submitNewCat').click(function(){
        AddCategory();
    });
    $('#pills-modify-tab').click(function(){
        fetchCategories();
    });
});

function fetchCategories(){
    let url="/fetchCategory";
    let postData={'categoryType':"all"};
    let response=ajaxCallPost(url,postData);
    catData=response;

    CreateDataTableForCategories(response);


}
function CreateDataTableForCategories(data){
    DestroyDataTable('CategoryTable');
    $('#CategoryTable').DataTable({
        data: data,
        columns: [
            { title: 'Category',data:'1' ,width:'30%'},
            {
                title: 'Options' ,
                data:'0',
                width:'30%',
                render:function (data,type,row,meta){
                    return "<div style='display:flex' ><div class='icon-rename-delete' id='"+data+"' onClick=RenameCat(this.id,"+meta.row+") ><i class='fas fa-edit'></i></div><div class='icon-rename-delete' id='"+data+"' onClick=DeleteCat(this.id,"+meta.row+") ><i class='fa fa-trash' aria-hidden='true'></i></div></div>";
                }
            }


        ],
    });
}
function AddCategory(){
    let url="/addNewCategory";
    let newCategory=$('#addNewCat').val();
    let postData={'newCategory':newCategory};
    let response=ajaxCallPost(url,postData);
    if(response['data'].includes("Failed!")){
        ErrorMessage(response['data']);
    }
    else{
        SuccessMessage(response['data']+'<br>'+response['newCategory']+' has been added');
    }
    $('#addNewCat').val('');
}
function RenameCat(id){

}
function DeleteCat(id,row){
    let url='/deleteCategory';
    let postData={'categoryId':id};
    let category;
    let response=ajaxCallPost(url,postData);
    console.log(response);
    if(response['data'].includes("Failed!")){
        ErrorMessage(response['data']);
    }
    else{
        for(i=0;i<catData.length;i++){
            if(catData[i][0]==id){
                category=catData[i][1];
                catData.splice(i,1);
                break;
            }
        }
        CreateDataTableForCategories(catData);
        SuccessMessage(response['data']+'<br>'+category+' has been deleted');

    }
}
let planData;
$(document).ready(function () {

    $('#submitNewPlan').click(function(){
        AddPlan();
    });
    $('#pills-modify-tab').click(function(){
        FetchPlans();
    });
});

function AddPlan(){
    let url="/AddNewPlan"
    let planDict=
    {
        planName:$('#PlanName').val(),
        planAmount:$('#PlanAmount').val(),
        planValidity:$('#PlanValidity').val(),
        planDataVolume:$('#PlanVolume').val(),
        planSpeed:$('#PlanSpeed').val(),
        planDescription:$('#PlanDesc').val()
    }
    
    

    if(ValidatePlanBeforeAdd(planDict)){
        let response=ajaxCallPost(url,planDict);
        console.log(response);
        if(response['data'].includes("Failed!")){
            ErrorMessage(response['data']);
        }
        else{
            SuccessMessage(response['data']+'<br>'+response['newCategory']+' has been added');
        }

    }
    else{
        //Show Mistakes and disable Submit Butoon
    }

}
function ModifyPlan(){

}
function FetchPlans(){
    let url="/getFixedPlanNames";
    let postData={
        "isEnabled":1
    }
    let response=ajaxCallPost(url,postData);
    console.log(response);
}
function ValidatePlanBeforeAdd(planDetails){
    let allOk=false;
    return allOk;
}
function ClearForm(){

}
function ValidatePlanName(){

}

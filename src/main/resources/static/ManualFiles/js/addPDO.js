let navigateVar=false;
$(document).ready(function(){

    createCategoriesDropdown();
    $("#pincode").on('input', function() {
        if($('#pincode').val().length<6){
            $('#area').empty();
            let areaDropdown=document.getElementById('area');
            const option = document.createElement('option');
            option.value ='-1';
            option.text = 'Please Enter Pincode';
            areaDropdown.appendChild(option);
            $('#area').attr('disabled',true);
            return;
        }
        fillUpUsingPincode();
    });
    $('#Validate1').on('click',function(event){
        
        if(validatePdoDetails()){
            $('#pdoDetailsNext').attr('disabled',false);
            $('#pdoDetailsNext').attr('hidden',false);
        }
    });
    $('.btn-navigate-form-step').on('click',function(event){
        let num=$(this).attr('step_number')
        navigateToFormStep(num);

    });
    $('#area').select2({
        placeholder: "Select Area",
        allowClear: true
        
    });
    FetchPlanNamesAndRender();

});
const navigateToFormStep = (stepNumber) => {
    /**
     * Hide all form steps.
     */
    document.querySelectorAll(".form-step").forEach((formStepElement) => {
        formStepElement.classList.add("d-none");
    });
    /**
     * Mark all form steps as unfinished.
     */
    document.querySelectorAll(".form-stepper-list").forEach((formStepHeader) => {
        formStepHeader.classList.add("form-stepper-unfinished");
        formStepHeader.classList.remove("form-stepper-active", "form-stepper-completed");
    });
    /**
     * Show the current form step (as passed to the function).
     */
    document.querySelector("#step-" + stepNumber).classList.remove("d-none");
    /**
     * Select the form step circle (progress bar).
     */
    const formStepCircle = document.querySelector('li[step="' + stepNumber + '"]');
    /**
     * Mark the current form step as active.
     */
    formStepCircle.classList.remove("form-stepper-unfinished", "form-stepper-completed");
    formStepCircle.classList.add("form-stepper-active");
    /**
     * Loop through each form step circles.
     * This loop will continue up to the current step number.
     * Example: If the current step is 3,
     * then the loop will perform operations for step 1 and 2.
     */
    for (let index = 0; index < stepNumber; index++) {
        /**
         * Select the form step circle (progress bar).
         */
        const formStepCircle = document.querySelector('li[step="' + index + '"]');
        /**
         * Check if the element exist. If yes, then proceed.
         */
        if (formStepCircle) {
            /**
             * Mark the form step as completed.
             */
            formStepCircle.classList.remove("form-stepper-unfinished", "form-stepper-active");
            formStepCircle.classList.add("form-stepper-completed");
        }
    }
};
/**
 * Select all form navigation buttons, and loop through them.
 */
document.querySelectorAll(".btn-navigate-form-step").forEach((formNavigationBtn) => {
    /**
     * Add a click event listener to the button.
     */
    formNavigationBtn.addEventListener("click", () => {
        /**
         * Get the value of the step.
         */
        const stepNumber = parseInt(formNavigationBtn.getAttribute("step_number"));
        /**
         * Call the function to navigate to the target form step.
         */
        navigateToFormStep(stepNumber);
    });
});

function fetchCategories(){
    let url="/fetchCategory";
    let postData={'categoryType':"enabled"};
    let response=ajaxCallPost(url,postData);
    return response;

}
function createCategoriesDropdown(){
    let categories=fetchCategories();
    let dropdown=document.getElementById('category');
    categories.forEach(function(category) {
        const option = document.createElement('option');
        option.value = category[0];
        option.text = category[1];
        dropdown.appendChild(option);
    });
    $('#category').select2({
        placeholder: "Select Category",
        allowClear: true
    });

}
async function fillUpUsingPincode(){
    let pin=$('#pincode').val();
    if(pin.length==6){
        
        $('#area').attr('disabled',false);
        let postalData=null;
        await $.getJSON("https://api.postalpincode.in/pincode/" + pin,function(data){
            postalData=data;
        })
        console.log(postalData);
        let distirct=postalData[0]['PostOffice'][0]['District'];
        $('#distirct').val(distirct);
        let state=postalData[0]['PostOffice'][0]['State'];
        $('#state').val(state);
        let block=postalData[0]['PostOffice'][0]['Block'];
        $('#block').val(block);
        let areas=postalData[0]['PostOffice'];
        let areaDropdown=document.getElementById('area');
        $('#area').empty();
        const option = document.createElement('option');
        option.value ='-1';
        option.text = '--Select an option--';
        areaDropdown.appendChild(option);
        areas.forEach(function(area){
            const option = document.createElement('option');
            option.value = area['Name'];
            option.text = area['Name'];
            areaDropdown.appendChild(option);
        })
        
    }
    else{
        return;
    }
    
    

}

function validatePdoDetails(){
    let allGood=true;
    let fname=$('#fname').val();
    if(fname == "" || fname.length<3){
        $('#validfname').attr('hidden',false);
        allGood=false;
    }
    else{
        $('#validfname').attr('hidden',true);
    }
    let lname=$('#lname').val();
    if(lname =="" || lname.length<3){
        $('#validlname').attr('hidden',false);
        allGood=false;
    }
    else{
        $('#validlname').attr('hidden',true);
    }
    let email=$('#email').val();
    let regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if(!regex.test(email) || email == ""){
        $('#validmail').attr('hidden',false);
        allGood=false;
    }
    else{
        $('#validmail').attr('hidden',true);
    }
    let phonePattern = /^\d{10}$/;
    let primaryContact=$('#phone1').val();
    if(!phonePattern.test(primaryContact)){
        $('#validpcontact').attr('hidden',false);
        allGood=false;
    }
    else{
        $('#validpcontact').attr('hidden',true);
    }
    let secondaryContact=$('#phone2').val();
    if(secondaryContact.length>0 && !phonePattern.test(secondaryContact)){
        $('#validscontact').attr('hidden',false);
        allGood=false;
    }
    else{
        $('#validscontact').attr('hidden',true);
    }
    let address1=$('#addLine1').val();
    if(address1.length<=10){
        $('#validaddress1').attr('hidden',false);
        allGood=false;
    }
    else{
        $('#validaddress1').attr('hidden',true);
    }
    let area=$('#area').val();
    if(area=='-1'){
        $('#validarea').attr('hidden',false);
        allGood=false;
    }
    else{
        $('#validarea').attr('hidden',true);
    }
    let pincode=$('#pincode').val();
    if(pincode.length<6){
        $('#validpincode').attr('hidden',false);
        allGood=false;
    }
    else{
        $('#validpincode').attr('hidden',true);
    }
    let category=$('#category').val();
    if(category=='-1'){
        $('#validcategory').attr('hidden',false);
        allGood=false;
    }
    else{
        $('#validcategory').attr('hidden',true);
    }
    return allGood;
}

function AddNewPdo(){
    let url='/addNewPdo';
    const pdoForm = document.getElementById('AddNewPdoForm');
    //const formData = new FormData(pdoForm);
    //console.log('Fetching Submitted data');
    //console.log(formData);
    let postData="";
    //let status =ajaxCallPost(url,postData);


}
function FetchPlanNamesAndRender(){
    let url='/getPlanNames';
    let postData={
        'isEnabled':true,
        'isMandatory':true
    };
    let planNamesPost=ajaxCallPost(url,postData);
    console.log(planNamesPost);
    let data=planNamesPost['data'];
    for(i=1;i<=data.length;i++){
        $('#PlanName-'+i).val(data[i-1][1]);
        $('#PlanValidity-'+i).val(data[i-1][2]);
    }
}
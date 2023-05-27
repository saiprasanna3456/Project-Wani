package com.example.visitorproject.Controllers;


import com.example.visitorproject.DataAccessLayer.PdoDal;


import com.example.visitorproject.Entities.PdoManagement.PdoCategories;
import com.example.visitorproject.Entities.PdoManagement.PdoDetails;
import com.example.visitorproject.Entities.PdoManagement.PdoPlanNames;
import com.example.visitorproject.Repository.PdoManagementRepository;

import jakarta.persistence.EntityManager;
import org.json.JSONObject;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PdoController {
    private PdoDal dal;

    public PdoController(PdoManagementRepository pdo, EntityManager entityManager){
        dal=new PdoDal(pdo, entityManager);
    }

    @GetMapping("/addNewPdo")
    public ModelAndView addNewPdo() {

        return new ModelAndView("Pages/pdoManagement/AddPDO");
    }
    @GetMapping("/CategoryManagement")
    public ModelAndView CatManagement() {

        return new ModelAndView("Pages/pdoManagement/CategoryManagement");
    }
    @GetMapping("/PlanManagement")
    public ModelAndView RechargePlanManagement() {

        return new ModelAndView("Pages/pdoManagement/RechargePlanManagement");
    }
    @PostMapping("/fetchCategory")
    @ResponseBody
    public ResponseEntity<List<PdoCategories>> getCategories(@RequestBody String data){
        JSONObject jsonObject = new JSONObject(data);
        String categoryType=jsonObject.getString("categoryType");

        try{

            var sol=dal.GetCategories(categoryType);
            jsonObject.put("data", sol);

            return ResponseEntity.ok(sol);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            jsonObject.put("data", e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/addNewCategory")
    @ResponseBody
    public String AddNewCategory(@RequestBody String newCategory){
        JSONObject jsonObject=new JSONObject(newCategory);
        try{
            String newCat=jsonObject.getString("newCategory");
            String status=dal.AddNewCategory(newCat);
            jsonObject.put("data",status);
            return jsonObject.toString();
        }
        catch(Exception e){
            jsonObject.put("data","Failed! "+e.toString());
            return jsonObject.toString();
        }
    }


    @PostMapping("/deleteCategory")
    @ResponseBody
    public String DeleteCategory(@RequestBody String categoryId){
        JSONObject jsonObject=new JSONObject(categoryId);

        try{
            String id=jsonObject.getString("categoryId");
            int catId=Integer.parseInt(id);
            String status=dal.DeleteCategory(catId);
            jsonObject.put("data",status);
            return jsonObject.toString();
        }
        catch(Exception e){
            jsonObject.put("data","Failed! "+e.toString());
            return jsonObject.toString();
        }
    }

    @PostMapping("/addNewPdo")
    @ResponseBody
    public String AddNewPdo(@RequestBody PdoDetails newPdo){
        JSONObject jsonObject=new JSONObject();
        String status=dal.AddNewPdo(newPdo);
        jsonObject.put("status",status);
        return jsonObject.toString();
        
    }

    @PostMapping("/modifyPdo")
    @ResponseBody
    public String ModifyPdo(@RequestBody PdoDetails newPdo){
        JSONObject jsonObject=new JSONObject();
        String status=dal.AddNewPdo(newPdo);
        jsonObject.put("status",status);
        return jsonObject.toString();
        
    }

    @PostMapping("/getFixedPlanNames")
    @ResponseBody
    public ResponseEntity<List<PdoPlanNames>>  GetFixedPlanNames(@RequestBody String request){
        JSONObject jsonObject=new JSONObject(request);
        int enabled=jsonObject.getInt("isEnabled");
        if(enabled<-1 || enabled>1){
            jsonObject.put("data","Failed!.lol");
            return ResponseEntity.notFound().build();
        }
        List<PdoPlanNames> planNames=dal.getFixedPlanNames(enabled);
        jsonObject.put("data",planNames);
        return ResponseEntity.ok(planNames);

    }

    @PostMapping("/AddNewPlan")
    @ResponseBody
    public String AddNewPlan(@RequestBody PdoPlanNames newPlan){
        JSONObject jsonObject=new JSONObject();
        try{
            String status=dal.AddNewPlan(newPlan);
            jsonObject.put("status",status);
            return jsonObject.toString();
        }
        catch(Exception e){
            jsonObject.put("data","Failed! "+e.toString());
            return jsonObject.toString();
        }
    }


}

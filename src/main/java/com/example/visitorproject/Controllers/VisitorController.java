package com.example.visitorproject.Controllers;

import com.example.visitorproject.DataAccessLayer;
import com.example.visitorproject.Entities.SubmitEntryAjaxDataModel;
import com.example.visitorproject.Entities.VisitorInfo;
import com.example.visitorproject.Repository.VisitorRepository;
import jakarta.persistence.EntityManager;
import org.apache.tomcat.util.modeler.BaseAttributeFilter;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class VisitorController {
    private DataAccessLayer dal;//private int dal;

    public VisitorController(VisitorRepository visito, EntityManager entityManager){
        dal=new DataAccessLayer(visito, entityManager);
    }

    @GetMapping("/myPage")
    public ModelAndView myPage() {
        ModelAndView modelAndView = new ModelAndView("_Layout");
        modelAndView.addObject("table", "VisitorData");
        modelAndView.addObject("fragment", "content");

        return modelAndView;
    }

    public List<VisitorInfo> GetVisitorDataApi(){
        String exMessage="No Exception Occurred";
        List<VisitorInfo> visitorData;
        try{
            visitorData=dal.GetAllVisitors();

        }
        catch(Exception ex){
            exMessage=ex.getMessage();
            System.out.println(exMessage);
            visitorData=null;
        }


        return visitorData;
    }
    @PostMapping("/submitEntry")
    @ResponseBody
    public String makeEntry(@RequestBody SubmitEntryAjaxDataModel data){
        JSONObject jsonObject = new JSONObject();

        try{
            var name="sai";
            String sol=dal.SaveVisiorInfo(data);
            jsonObject.put("data", sol);

            return jsonObject.toString();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            jsonObject.put("data", e.getMessage());
            return jsonObject.toString();
        }

    }
    @PostMapping("/searchVisitorForExit")
    @ResponseBody
    public String GetVisitorDataForExit(@RequestBody String searchStr){

        JSONObject jsonObject = new JSONObject(searchStr);

        try{
            var str=jsonObject.getString("searchStr");
            var sol=dal.GetVisitorSuggestions(str);
            jsonObject.put("data", sol);

            return jsonObject.toString();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            jsonObject.put("data", e.getMessage());
            return jsonObject.toString();
        }

    }

}

package com.example.pmwani.Controllers;




import org.springframework.stereotype.Controller;

@Controller
public class VisitorController {
    // private final DataAccessLayer1 dal;//private int dal;

    // public VisitorController(VisitorRepository visito, EntityManager entityManager){
    //     dal=new DataAccessLayer1(visito, entityManager);
    // }

//     @GetMapping("/myPage")
//     public ModelAndView myPage() {
//         //        modelAndView.addObject("page", "Pages/EntryAndExit");
// //        modelAndView.addObject("fragment", "content");

//         return new ModelAndView("Pages/EntryAndExit");
//     }

//     @GetMapping("/visitorHistory")
//     public ModelAndView VisitorHistory() {
//         ModelAndView modelAndView = new ModelAndView("Pages/VisitorData");
// //        modelAndView.addObject("page", "Pages/VisitorData");
// //        modelAndView.addObject("fragment", "content");

//         return modelAndView;
//     }

//     public List<VisitorInfo> GetVisitorDataApi(){
//         String exMessage="No Exception Occurred";
//         List<VisitorInfo> visitorData;
//         try{
//             visitorData=dal.GetAllVisitors();

//         }
//         catch(Exception ex){
//             exMessage=ex.getMessage();
//             System.out.println(exMessage);
//             visitorData=null;
//         }


//         return visitorData;
//     }
//     @PostMapping("/submitEntry")
//     @ResponseBody
//     public String makeEntry(@RequestBody SubmitEntryAjaxDataModel data){
//         JSONObject jsonObject = new JSONObject();

//         try{

//             String sol=dal.SaveVisiorInfo(data);
//             jsonObject.put("data", sol);

//             return jsonObject.toString();
//         }
//         catch(Exception e){
//             System.out.println(e.getMessage());
//             jsonObject.put("data", e.getMessage());
//             return jsonObject.toString();
//         }

//     }
//     @PostMapping("/searchVisitorForExit")
//     @ResponseBody
//     public String GetVisitorDataForExit(@RequestBody String searchStr){

//         JSONObject jsonObject = new JSONObject(searchStr);

//         try{
//             var str=jsonObject.getString("searchStr");
//             var sol=dal.GetVisitorSuggestions(str);
//             jsonObject.put("data", sol);

//             return jsonObject.toString();
//         }
//         catch(Exception e){
//             System.out.println(e.getMessage());
//             jsonObject.put("data", e.getMessage());
//             return jsonObject.toString();
//         }

//     }

}

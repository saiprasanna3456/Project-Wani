package com.example.visitorproject;

import com.example.visitorproject.Entities.SubmitEntryAjaxDataModel;
import com.example.visitorproject.Entities.VisitorInfo;
import com.example.visitorproject.Repository.VisitorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Component
public class DataAccessLayer {



    private VisitorRepository repo1;
    private EntityManager entityManager;

    public DataAccessLayer(VisitorRepository visit,EntityManager entityManager){

        this.repo1=visit;
        this.entityManager=entityManager;

    }





    public List<VisitorInfo> GetAllVisitors(){
        var list=repo1.findAll();
        return list;
        //return repo.findAll();
    }
    public String SaveVisiorInfo(SubmitEntryAjaxDataModel data){
        LocalTime localTime = LocalTime.now(); // replace this with your actual LocalTime value
        LocalDate localDate = LocalDate.now(); // get today's date
        LocalDateTime localDateTime = localTime.atDate(localDate); // combine the LocalTime and LocalDate
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault()); // convert to ZonedDateTime with default time zone
        Instant instant = zonedDateTime.toInstant(); // convert to Instant
        Timestamp timestamp = Timestamp.from(instant);
        VisitorInfo visito=new VisitorInfo();
        visito.contact=Long.parseLong(data.phone);
        visito.email=data.email;
        visito.purpose=data.purpose;
        visito.entryTime=timestamp;
        visito.fullName=data.name;
        try{
            var sol=repo1.save(visito);
            return "Success";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Failed";
        }


        //return repo.findAll();
    }
    public List<VisitorInfo> GetVisitorSuggestions(String searchStr){

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_getVisitorMatches");

        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);


        query.setParameter(1, searchStr);
        List<VisitorInfo> sol= query.getResultList();
        return sol;


    }


}

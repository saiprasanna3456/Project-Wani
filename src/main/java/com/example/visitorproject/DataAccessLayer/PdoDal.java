package com.example.visitorproject.DataAccessLayer;

import com.example.visitorproject.Entities.PdoManagement.PdoCategories;
import com.example.visitorproject.Entities.PdoManagement.PdoDetails;
import com.example.visitorproject.Entities.PdoManagement.PdoPlanNames;
import com.example.visitorproject.Repository.PdoManagementRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

import org.hibernate.grammars.hql.HqlParser.EntityIdExpressionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
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
public class PdoDal {
    final PdoManagementRepository repo1;
    final EntityManager entityManager;

    public PdoDal(PdoManagementRepository pdo, EntityManager entityManager){

        this.repo1=pdo;
        this.entityManager=entityManager;

    }
    public List<PdoCategories> getAllCategories(){
        return repo1.findAll();
    }
    public String AddNewCategory(String newCategory){
        PdoCategories cat=new PdoCategories();
        cat.CategoryName=newCategory;
        cat.isEnabled=true;
        try{
            repo1.save(cat);
            return "Success";
        }
        catch(Exception e){
            return "Failed! "+e.toString();
        }
    }

    public List<PdoCategories> GetCategories(String categoryTypes){
        int categories=0;
        if(categoryTypes.contains("enabled")){
            categories=1;
        }
        else if(categoryTypes.contains("disabled")){
            categories=0;
        }
        else if(categoryTypes.contains("all")){
            categories=-1;
        }
        
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_getCategories");

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);


        query.setParameter(1, categories);
        List<PdoCategories> sol= query.getResultList();
        return sol;


    }

    public String ModifyCategory(int categoryId,String newCategoryName){
        String status="";
        StoredProcedureQuery query=entityManager.createNamedStoredProcedureQuery("sp_RenameCategory");
        query.registerStoredProcedureParameter(1,Integer.class,ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        query.setParameter(1, categoryId );
        query.setParameter(2, newCategoryName);
        query.execute();
        status=query.getOutputParameterValue(3).toString();
        return status;
    }
    public String DeleteCategory(int categoryId){
        String status="";
        StoredProcedureQuery query=entityManager.createStoredProcedureQuery("sp_DeleteCategory");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        query.setParameter(1, categoryId);
        status=query.getOutputParameterValue(2).toString();
        return status;
    }
    public String SaveNewPdo(){
        String status="";
        return status;
    }

    public String AddNewPdo(PdoDetails newPdo){
        String status="";
        StoredProcedureQuery query=entityManager.createNamedStoredProcedureQuery("sp_addNewPdo");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.setParameter(1,newPdo.FirstName );
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2,newPdo.LastName );
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.setParameter(3,newPdo.Email );
        query.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN);
        query.setParameter(4, newPdo.PrimaryContact);
        query.registerStoredProcedureParameter(5, Long.class, ParameterMode.IN);
        query.setParameter(5, newPdo.SecondaryContact);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        query.setParameter(6, newPdo.AddressL1);
        query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
        query.setParameter(7,newPdo.AddressL2 );
        query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
        query.setParameter(8,newPdo.Area );
        query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);
        query.setParameter(9,newPdo.Region );
        query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN);
        query.setParameter(10,newPdo.Distirct );
        query.registerStoredProcedureParameter(11, String.class, ParameterMode.IN);
        query.setParameter(11,newPdo.State );
        query.registerStoredProcedureParameter(12, Integer.class, ParameterMode.IN);
        query.setParameter(12,newPdo.PinCode );
        query.registerStoredProcedureParameter(13, Integer.class, ParameterMode.IN);
        query.setParameter(13, newPdo.CategoryId);
        query.registerStoredProcedureParameter(14, String.class, ParameterMode.OUT);
        
        try{
            query.execute();
            status=query.getOutputParameterValue(14).toString();
        }
        catch(Exception e){
            status="Failed! "+e.toString();
        }
        return status;
    }

    public String ModifyPdo(PdoDetails editPdo){
        String status="";
        StoredProcedureQuery query=entityManager.createNamedStoredProcedureQuery("sp_modifyPdo");
        query.registerStoredProcedureParameter(14, Integer.class, ParameterMode.IN);
        query.setParameter(1,editPdo.PdoId );
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.setParameter(1,editPdo.FirstName );
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.setParameter(2,editPdo.LastName );
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.setParameter(3,editPdo.Email );
        query.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN);
        query.setParameter(4, editPdo.PrimaryContact);
        query.registerStoredProcedureParameter(5, Long.class, ParameterMode.IN);
        query.setParameter(5, editPdo.SecondaryContact);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        query.setParameter(6, editPdo.AddressL1);
        query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
        query.setParameter(7,editPdo.AddressL2 );
        query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
        query.setParameter(8,editPdo.Area );
        query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);
        query.setParameter(9,editPdo.Region );
        query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN);
        query.setParameter(10,editPdo.Distirct );
        query.registerStoredProcedureParameter(11, String.class, ParameterMode.IN);
        query.setParameter(11,editPdo.State );
        query.registerStoredProcedureParameter(12, Integer.class, ParameterMode.IN);
        query.setParameter(12,editPdo.PinCode );
        query.registerStoredProcedureParameter(13, Integer.class, ParameterMode.IN);
        query.setParameter(13, editPdo.CategoryId);
        query.registerStoredProcedureParameter(15, String.class, ParameterMode.OUT);
        
        try{
            query.execute();
            status=query.getOutputParameterValue(15).toString();
        }
        catch(Exception e){
            status="Failed! "+e.toString();
        }
        return status;

    }

    public List<PdoPlanNames> getFixedPlanNames(int enabled){
        List<PdoPlanNames> planList;
        String status="";
        StoredProcedureQuery query=entityManager.createStoredProcedureQuery("sp_get_fixedPlanNames");
        query.registerStoredProcedureParameter(1, Integer.class,ParameterMode.IN);
        query.registerStoredProcedureParameter(2,String.class,ParameterMode.OUT);
        if(enabled<-1 || enabled>1){
            query.setParameter(1,-1);

        }
        else{
            query.setParameter(1,enabled);
        }
        
        
        planList=query.getResultList();
        status=query.getOutputParameterValue(2).toString();
        return planList;



    }

    public String AddNewPlan(PdoPlanNames newPlan){
        String status="";
        StoredProcedureQuery query=entityManager.createStoredProcedureQuery("sp_add_newPlan");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.setParameter(1, newPlan.PlanName);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.setParameter(2,newPlan.PlanDataVolume);
        query.registerStoredProcedureParameter(3, Double.class, ParameterMode.IN);
        query.setParameter(3,newPlan.PlanPrice);
        query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
        query.setParameter(4,newPlan.PlanValidity);
        query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
        query.setParameter(5,newPlan.PlanSpeed);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        query.setParameter(6,newPlan.PlanDesc);
        query.registerStoredProcedureParameter(7, Boolean.class, ParameterMode.IN);
        query.setParameter(7,newPlan.IsEnabled);
        query.registerStoredProcedureParameter(8,String.class,ParameterMode.OUT);
        
        try{
            query.execute();
            status=query.getOutputParameterValue(8).toString();
        }
        catch(Exception e){
            status="Failed!. "+e.toString();
        }
        return status;
    }

     
}

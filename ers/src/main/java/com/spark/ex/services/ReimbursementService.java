package com.spark.ex.services;

import com.spark.ex.data.ReimbursementDao;
import com.spark.ex.data.ReimbursementDaoImpl;
import com.spark.ex.pojos.Reimbursement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {
    private ReimbursementDao reimbursementDao;

    public ReimbursementService(){
        this.reimbursementDao = new ReimbursementDaoImpl();
    }

    public List<Reimbursement> getReimbursementsByAuthorId(int authorId){
        List<Reimbursement> reimbursementList = null;

        try{
            reimbursementList = reimbursementDao.getReimbursementsByAuthorId(authorId);
        } catch (SQLException e){
            e.printStackTrace();
        }
         return reimbursementList;
    }

    public List<Reimbursement> getAllReimbursements(){
        List<Reimbursement> reimbursementList = null;

        try{
            reimbursementList = reimbursementDao.getAllReimbursements();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursementList;
    }

    public Reimbursement addReimbursement(Reimbursement reimbursement){
        Reimbursement returnedReimbursement = null;

        try{
            returnedReimbursement = reimbursementDao.addReimbursement(reimbursement);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return returnedReimbursement;
    }

    public Reimbursement updateReimbursement(Reimbursement reimbursement){
        Reimbursement returnedReimbursement = null;

        try{
            returnedReimbursement = reimbursementDao.updateReimbursement(reimbursement);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return  returnedReimbursement;
    }
}
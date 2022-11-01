package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.pojos.Reimbursement;

import java.util.List;
import java.sql.ResultSet;

public class ReimbursementService {

    private ReimbursementDAO dao;

    public ReimbursementService() {
        this.dao = new ReimbursementDAO();
    }

    public void saveReimbursement(Reimbursement reimbursement) {
        dao.create(reimbursement);
    }

    public Reimbursement getReimbursement(int id) {
        return dao.read(id);
    }

    public Reimbursement getSingleReimbursementForUser(int reimbursementId, int userId){
        return dao.getSingleReimbursementForUser(reimbursementId, userId);
    }
    public List<Reimbursement> getReimbursementsForUser(int id){
        return dao.readReimbursementsByUser(id);
    }

    public List<Reimbursement> readComplete(String complete){
        return dao.readComplete(complete);
    }

    public List<Reimbursement> getListReimbursement(){
        return dao.readAll();
    }

    public void updateReimbursement(Reimbursement reimbursement, Integer reimbursementId, String complete) {
        dao.updateComplete(reimbursement, reimbursementId, complete);
    }

    public void updateReimbursementById(Reimbursement reimbursement, Integer reimbursementId, Integer userId) {
        dao.update(reimbursement, reimbursementId, userId);
    }

    public void deleteReimbursement(int id) {
        dao.delete(id);
    }
}
package com.revature.daos;

import com.revature.pojos.Reimbursement;
import com.revature.services.DatasourceService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReimbursementDAO implements DatasourceCRUD<Reimbursement> {

    Connection connection;
    public ReimbursementDAO() {
        this.connection = DatasourceService.ConnectionManager.getConnection();
    }

    @Override
    public void create(Reimbursement reimbursement) {
        try{
            String sql = "INSERT INTO reimbursements (title, amount, message, user_id, complete)" +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, reimbursement.getTitle());
            pstmt.setFloat(2, reimbursement.getAmount());
            pstmt.setString(3, reimbursement.getMessage());
            pstmt.setInt(4, reimbursement.getUserId());
            pstmt.setString(5, "PENDING");

            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                keys.getInt("reimbursement_id");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reimbursement read(int id) {
        Reimbursement reimbursement = new Reimbursement();
        try{
            String sql = "SELECT * FROM reimbursements WHERE reimbursement_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();

            if(results.next()) {
                reimbursement.setReimbursementId(results.getInt("reimbursement_id"));
                reimbursement.setTitle(results.getString("title"));
                reimbursement.setAmount(results.getFloat("amount"));
                reimbursement.setMessage(results.getString("message"));
                reimbursement.setUserId(results.getInt("user_id"));
                reimbursement.setComplete(results.getString("complete"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    public List<Reimbursement> readComplete(String complete){
        List<Reimbursement> reimbursementList = new LinkedList<>();
        try{
            String sql = "SELECT * FROM reimbursements WHERE complete = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, complete);
            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setReimbursementId(results.getInt("reimbursement_id"));
                reimbursement.setTitle(results.getString("title"));
                reimbursement.setAmount(results.getFloat("amount"));
                reimbursement.setMessage(results.getString("message"));
                reimbursement.setUserId(results.getInt("user_id"));
                reimbursement.setComplete(results.getString("complete"));
                reimbursementList.add(reimbursement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reimbursementList;
    }

    public List<Reimbursement> readAll() {
        List<Reimbursement> reimbursementList = new LinkedList<>();
        try{
            String sql = "SELECT * FROM reimbursements";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setReimbursementId(results.getInt("reimbursement_id"));
                reimbursement.setTitle(results.getString("title"));
                reimbursement.setAmount(results.getFloat("amount"));
                reimbursement.setMessage(results.getString("message"));
                reimbursement.setUserId(results.getInt("user_id"));
                reimbursement.setComplete(results.getString("complete"));
                reimbursementList.add(reimbursement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reimbursementList;
    }

    public Reimbursement getSingleReimbursementForUser(Integer reimbursementId, Integer userId){
        Reimbursement reimbursement = new Reimbursement();
        try{
            String sql = "SELECT * FROM reimbursements WHERE reimbursement_id = ? AND user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, reimbursementId);
            pstmt.setInt(2, userId);
            ResultSet results = pstmt.executeQuery();

            if(results.next()) {
                reimbursement.setReimbursementId(results.getInt("reimbursement_id"));
                reimbursement.setTitle(results.getString("title"));
                reimbursement.setAmount(results.getFloat("amount"));
                reimbursement.setMessage(results.getString("message"));
                reimbursement.setUserId(results.getInt("user_id"));
                reimbursement.setComplete(results.getString("complete"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    public List<Reimbursement> readReimbursementsByUser(Integer id){
        List<Reimbursement> reimbursementList = new LinkedList<>();
        try{
            String sql = "SELECT * FROM reimbursements WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setReimbursementId(results.getInt("reimbursement_id"));
                reimbursement.setTitle(results.getString("title"));
                reimbursement.setAmount(results.getFloat("amount"));
                reimbursement.setMessage(results.getString("message"));
                reimbursement.setUserId(results.getInt("user_id"));
                reimbursement.setComplete(results.getString("complete"));
                reimbursementList.add(reimbursement);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reimbursementList;
    }

    @Override
    public void update(Reimbursement reimbursement, Integer i) {

    }

    public void updateComplete(Reimbursement reimbursement, Integer reimbursementId, String complete) {
        try{
            String sql = "UPDATE reimbursements SET title = ?, amount = ?, message = ?, " +
                    "complete = ? WHERE reimbursement_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, reimbursement.getTitle());
            pstmt.setFloat(2, reimbursement.getAmount());
            pstmt.setString(3, reimbursement.getMessage());
            pstmt.setString(4, complete);
            pstmt.setInt(5, reimbursementId);


            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Reimbursement reimbursement, Integer reimbursementId, Integer userId) {
        try{
            String sql = "UPDATE reimbursements SET title = ?, amount = ?, message = ?, " + "complete = false WHERE reimbursement_id = ? AND user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, reimbursement.getTitle());
            pstmt.setFloat(2, reimbursement.getAmount());
            pstmt.setString(3, reimbursement.getMessage());
            pstmt.setInt(4, reimbursementId);
            pstmt.setInt(5, userId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try{
            String sql = "DELETE FROM reimbursements WHERE reimbursement_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
package com.spark.ex.data;

import com.spark.ex.pojos.Reimbursement;
import com.spark.ex.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao{

    @Override
    public List<Reimbursement> getReimbursementsByAuthorId(int authorId) throws SQLException {
        Connection connection = null;
        List<Reimbursement> reimbursementList = new ArrayList<>();

        try{
            connection = ConnectionUtil.getConnectionManager().newConnection();

            String sqlShow = "select * from ers.ers_reimbursement WHERE reimb_author_id = ?";
            PreparedStatement ps = connection.prepareStatement(sqlShow);
            ps.setInt(1,authorId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();

                reimbursement.setId(rs.getInt("remimb_id"));
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                reimbursement.setDateSubmitted(rs.getTimestamp("reimb_submitted"));
                reimbursement.setDateResolved(rs.getTimestamp("reimb_resolved"));
                reimbursement.setDescription(rs.getString("reimb_description"));
                reimbursement.setAuthorId(rs.getInt("reimb_author_id"));
                reimbursement.setResolverId(rs.getInt("reimb_resolver_id"));
                reimbursement.setStatusId(rs.getInt("reimb_status_id"));
                reimbursement.setTypeId(rs.getInt("reimb_type_id"));

                reimbursementList.add(reimbursement);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursementList;
    }

    @Override
    public List<Reimbursement> getAllReimbursements() throws SQLException {
        Connection connection = null;
        List<Reimbursement> reimbursementList = new ArrayList<>();

        try{
            connection = ConnectionUtil.getConnectionManager().newConnection();

            String sql = "select * from ers.ers_reimbursement";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();

                reimbursement.setId(rs.getInt("remimb_id"));
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                reimbursement.setDateSubmitted(rs.getTimestamp("reimb_submitted"));
                reimbursement.setDateResolved(rs.getTimestamp("reimb_resolved"));
                reimbursement.setDescription(rs.getString("reimb_description"));
                reimbursement.setAuthorId(rs.getInt("reimb_author_id"));
                reimbursement.setResolverId(rs.getInt("reimb_resolver_id"));
                reimbursement.setStatusId(rs.getInt("reimb_status_id"));
                reimbursement.setTypeId(rs.getInt("reimb_type_id"));

                reimbursementList.add(reimbursement);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursementList;
    }

    @Override
    public Reimbursement addReimbursement(Reimbursement reimbursement) throws SQLException {
        Connection connection = null;
        Reimbursement returnedReimbursement = new Reimbursement();

        try{
            connection = ConnectionUtil.getConnectionManager().newConnection();

            String sql = "insert into ers.ers_reimbursement(reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author_id, reimb_status_id, reimb_type_id)\n"+
                    "\tvalues(?,?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql, new String []{"remimb_id"});

            ps.setDouble(1, reimbursement.getAmount());
            ps.setTimestamp(2, reimbursement.getDateSubmitted());
            ps.setTimestamp(3, reimbursement.getDateResolved(new Timestamp(System.currentTimeMillis())));
            ps.setString(4, reimbursement.getDescription());
            ps.setInt(5, reimbursement.getAuthorId());
            ps.setInt(6, reimbursement.getStatusId());
            ps.setInt(7,reimbursement.getTypeId());

            int rowsInserted = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if(rowsInserted != 0){
                while(rs.next()){
                    reimbursement.setId(rs.getInt(1));
                }

               // connection.commit();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return reimbursement;
    }

    @Override
    public Reimbursement updateReimbursement(Reimbursement reimbursement) throws SQLException {
        Connection connection = null;
        Reimbursement returnedReimbursement = new Reimbursement();

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);


        try{
            connection = ConnectionUtil.getConnectionManager().newConnection();

            String sql = "update ers.ers_reimbursement set \"reimb_status_id\" = ?, \"reimb_resolver_id\" = ?, \"reimb_resolved\" = ? where \"remimb_id\" = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, reimbursement.getStatusId());
            ps.setInt(2, reimbursement.getResolverId());
            ps.setTimestamp(3, ts);
            ps.setInt(4, reimbursement.getId());

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();

        }
        return reimbursement;
    }


}

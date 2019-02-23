package com.spark.ex.data;
//import com.spark.ex.pojos.Reimbursement;

import com.spark.ex.pojos.Reimbursement;

import java.sql.SQLException;
import java.util.List;

public interface ReimbursementDao {
    List<Reimbursement> getReimbursementsByAuthorId(int id) throws SQLException;

    List<Reimbursement> getAllReimbursements() throws SQLException;

    Reimbursement addReimbursement(Reimbursement reimbursement) throws SQLException;

    Reimbursement updateReimbursement(Reimbursement reimbursement) throws SQLException;
}

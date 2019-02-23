package com.spark.ex.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spark.ex.pojos.Reimbursement;
import com.spark.ex.pojos.User;
import com.spark.ex.services.ReimbursementService;
import com.spark.ex.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

public class ReimbursementServlet extends HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ObjectMapper map = new ObjectMapper();
        String uri = req.getRequestURI();

        ReimbursementService reimbursementService = new ReimbursementService();
        List<Reimbursement> reimbursementList = null;
        reimbursementList = reimbursementService.getAllReimbursements();

        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
        String reimbursementsListJSON = map.writeValueAsString(reimbursementList);
        pw.write(reimbursementsListJSON);



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        ReimbursementService reimbursementService = new ReimbursementService();

        Reimbursement reimbursement = mapper.readValue(req.getInputStream(), Reimbursement.class);
        reimbursement.setDateSubmitted(new Timestamp(System.currentTimeMillis()));

        reimbursement = reimbursementService.addReimbursement(reimbursement);

        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(reimbursement));

    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ReimbursementService reimbursementService = new ReimbursementService();
        Reimbursement reimbursement = mapper.readValue(req.getInputStream(), Reimbursement.class);
        reimbursement = reimbursementService.updateReimbursement(reimbursement);
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(reimbursement));

    }

}

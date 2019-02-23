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

public class ReimbursementsByAuthorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //gets the author id from the uri
        ObjectMapper mapper = new ObjectMapper();
        String uri = request.getRequestURI();

        ReimbursementService reimbursementService = new ReimbursementService();
        List<Reimbursement> reimbursementList = null;

        reimbursementList = reimbursementService.getAllReimbursements();

        PrintWriter pw = response.getWriter();
        response.setContentType("application/json");
        String reimbursementsListJSON = mapper.writeValueAsString(reimbursementList);
        pw.write(reimbursementsListJSON);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        ReimbursementService reimbursementService = new ReimbursementService();

        Reimbursement reimbursement = mapper.readValue(request.getInputStream(), Reimbursement.class);
        reimbursement.setDateSubmitted(new Timestamp(System.currentTimeMillis()));

        reimbursement = reimbursementService.addReimbursement(reimbursement);

        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(reimbursement));

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ReimbursementService reimbursementService = new ReimbursementService();

        Reimbursement reimbursement = mapper.readValue(request.getInputStream(), Reimbursement.class);

        reimbursement = reimbursementService.updateReimbursement(reimbursement);

        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(reimbursement));

    }
}

import leave.LeaveBean;
import leave.LeaveDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchAllLeaves extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String toDate= request.getParameter("toDate");
        String fromDate= request.getParameter("fromDate");

        String staus= request.getParameter("status");
        String type= request.getParameter("type");
        LeaveBean result=new LeaveBean();

        if(staus.equals("All")){
            result.setstatus(" ");
        }else if(staus .equals ("0")){
            result.setstatus("Staff Rejected ");
        }else if(staus.equals ("1")){
            result.setstatus("Staff Pending ");
        }else if(staus.equals ("2")){
            result.setstatus("Staff Approved ");
        }
        if(type .equals ("All") ){
            result.setType(" Leaves");
        }else{
            result.setType(type+ " Leaves");
        }
        if(toDate.isEmpty()){
            result.settoDate(" ");
        }
        else if(toDate != null){
            result.settoDate(" To : "+toDate);
        }

        if(fromDate.isEmpty()){
            result.setfromDate(" ");
        }
        else if(toDate != null){
            result.setfromDate(" From : "+fromDate);
        }

        if(fromDate.isEmpty() && toDate.isEmpty() && type .equals ("All") && staus.equals("All")){
            result.setstatus("All Staff Leave Details......");
            result.setType(" ");
        }


        LeaveDao empDao = new LeaveDao();
        List<LeaveBean> leaveList = empDao.allLeaves(toDate,fromDate,staus,type);
        request.setAttribute("leaves",leaveList);
        request.setAttribute("results",result);
        request.getRequestDispatcher("/staffLeaveHistory.jsp").forward(request, response);
    }
}

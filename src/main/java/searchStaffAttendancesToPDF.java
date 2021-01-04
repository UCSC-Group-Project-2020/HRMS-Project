import attendance.attendanceBean;
import attendance.attendanceDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class searchStaffAttendancesToPDF extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String empId= request.getParameter("employeeID");
        System.out.println(empId);
        String workedHoursFrom= request.getParameter("workedHoursFrom");
        String workedHoursTo= request.getParameter("workedHoursTo");
        String otHoursFrom= request.getParameter("otHoursFrom");
        String otHoursTo= request.getParameter("otHoursTo");
        String from= request.getParameter("fromDate");
        String to= request.getParameter("toDate");


        attendanceBean result =new attendanceBean();
        if(from != null){
            result.setFromDate("Staff Attendances From : " +from);
        }else if(to != null){
            result.setFromDate("Staff Attendances ");
        }else if(from == null && to == null) {
            result.setFromDate("Staff Attendances ");
        }
        if (to != null){
            result.setToDate(" To : "+to +" , ");
        }else{
            result.setToDate("  ");
        }
        if (workedHoursFrom!=null){
            result.setWorkedHoursFrom("Worked Hours From : "+workedHoursFrom );
        }else if(workedHoursTo != null){
            result.setWorkedHoursFrom("Worked Hours ");
        }else{
            result.setWorkedHoursFrom(" ");
        }
        if (workedHoursTo!=null){
            result.setWorkedHoursTo(" To : "+workedHoursTo +" , ");
        }
        if (otHoursFrom!=null){
            result.setOtHoursFrom("OT From : "+otHoursFrom +" Hours ");
        }
        if (otHoursTo!=null){
            result.setOtHoursTo(" To : "+otHoursTo+" Hours ");
        }
        if(to.isEmpty() && from.isEmpty()&& otHoursFrom.equals("0") && otHoursTo.equals("24") && (workedHoursFrom == null || workedHoursFrom.length()!=5) &&  (workedHoursTo == null || workedHoursTo.length()!=5)){
            result.setFromDate("All Staff Members ");
            result.setToDate("Attendances ");
            result.setWorkedHoursFrom("Details ... ");
            result.setWorkedHoursTo("");
            result.setOtHoursFrom("");
            result.setOtHoursTo("");
            result.setEmpId("");
        }
        if(!empId.equals("10000")){
            result.setFromDate("");
            result.setToDate("");
            result.setWorkedHoursFrom("");
            result.setWorkedHoursTo("");
            result.setOtHoursFrom("");
            result.setOtHoursTo("");
            result.setEmpId("");
            result.setEmpId("Attendance Details Of the Employee : "+empId);
        }
        attendanceDao attendance = new attendanceDao();
        attendanceBean attendBean =new attendanceBean();
        attendBean.setEmpId(empId);
        attendBean.setFromDate(from);
        attendBean.setToDate(to);
        attendBean.setOtHoursFrom(otHoursFrom);
        attendBean.setOtHoursTo(otHoursTo);
        attendBean.setWorkedHoursTo(workedHoursTo);
        attendBean.setWorkedHoursFrom(workedHoursFrom);
        List<attendanceBean> attendanceListDefault = attendance.staffAttendance(empId,from,to,workedHoursFrom,workedHoursTo,otHoursFrom,otHoursTo);
        request.setAttribute("attendances",attendanceListDefault);
        request.setAttribute("results",result);

        request.getRequestDispatcher("/attendanceReport.jsp").forward(request, response);
    }
}


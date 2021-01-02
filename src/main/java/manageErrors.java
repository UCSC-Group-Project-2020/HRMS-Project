import salary.SalaryBean;
import salary.SalaryDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class manageErrors extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String totalSalary,salaryId,empId;
        totalSalary= request.getParameter("total");
        salaryId= request.getParameter("salaryId");
        empId= request.getParameter("empId");
        System.out.println(totalSalary);
        System.out.println(salaryId);
        System.out.println(empId);

        SalaryBean salary =new SalaryBean();
        salary.setTotalSalary(Float.parseFloat(totalSalary));
        salary.setSalaryId(salaryId);
        salary.setEmpId(empId);

        SalaryDao sDao=new SalaryDao();
        String result = null;
        try {
            result = sDao.resolveErrors(salary);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        request.setAttribute("result", result);

        if(result.equals("SuccessfulEnd")){
            request.getRequestDispatcher("/staffSalaryOverview.jsp").forward(request, response);

        }else  if(result.equals("Successful")){
            request.getRequestDispatcher("/manageSalaryErrors.jsp").forward(request, response);

        }
    }

}
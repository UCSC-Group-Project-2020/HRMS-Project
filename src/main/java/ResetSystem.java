import Customize.CustomizeDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResetSystem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            CustomizeDao com = new CustomizeDao();
            String result = com.resetSystem();
            request.setAttribute("show", result);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
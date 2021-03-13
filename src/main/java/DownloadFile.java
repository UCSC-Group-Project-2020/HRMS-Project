import DBconnection.DBconn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.sql.*;

public class DownloadFile extends HttpServlet
{
    private static final int BUFFER_SIZE = 4096;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int msgId;
        String guestId,guestName;
        msgId = Integer.parseInt(request.getParameter("msgFileId"));
        guestId = request.getParameter("gId");
        guestName = request.getParameter("gName");

        Connection conn = null;

        try
        {
            conn = DBconn.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT fileName,msgFile FROM chat WHERE msgId = ?");
            statement.setInt(1, msgId);

            ResultSet result = statement.executeQuery();
            if (result.next())
            {
                String fileName = result.getString("fileName");
                Blob blob = result.getBlob("msgFile");
                InputStream inputStream = blob.getBinaryStream();
                int fileLength = inputStream.available();

                ServletContext context = getServletContext();

                String mimeType = context.getMimeType(fileName);
                if (mimeType == null)
                {
                    mimeType = "application/octet-stream";
                }

                response.setContentType(mimeType);
                response.setContentLength(fileLength);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", fileName);
                response.setHeader(headerKey, headerValue);

                OutputStream outStream = response.getOutputStream();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1)
                {
                    outStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outStream.close();
            }
            else
            {
                response.getWriter().print("File not found for the id: " + msgId);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            response.getWriter().print("SQL Error: " + ex.getMessage());
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            response.getWriter().print("IO Error: " + ex.getMessage());
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            request.setAttribute("guestId",guestId);
            request.setAttribute("guestName",guestName);
            request.setAttribute("fId",0);
            request.getRequestDispatcher("/chatSystem.jsp").forward(request, response);
        }
    }
}
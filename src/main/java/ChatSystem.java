import chat.MessagesBean;
import chat.MessagesDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatSystem extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String senderId,guestName,guestId,msgText,msgFileName,msgFile,pDate;
        int fId;

        senderId = request.getParameter("sId");
        guestId = request.getParameter("gId");
        guestName = request.getParameter("gName");
        msgText = request.getParameter("msg");
        fId = Integer.parseInt(request.getParameter("fId"));
        if(fId == 0)
        {
            MessagesBean newMsg = new MessagesBean();
            newMsg.setSenderId(senderId);
            newMsg.setReceiverId(guestId);
            newMsg.setMsgText(msgText);

            MessagesDao sendMsg = new MessagesDao();
            sendMsg.sendMessage(newMsg);
        }
        request.setAttribute("guestId",guestId);
        request.setAttribute("guestName",guestName);
        request.setAttribute("fId",0);
        request.getRequestDispatcher("/chatSystem.jsp").forward(request, response);



    }
}

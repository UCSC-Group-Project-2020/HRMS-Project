import chat.MessagesBean;
import chat.MessagesDao;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@MultipartConfig
public class DeleteMessage extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        MessagesDao delMsg = new MessagesDao();
        String msgId,logId,pDate,guestId,guestName;
        guestId = request.getParameter("gId");
        guestName = request.getParameter("gName");
        String [] st = new String[4];
        Date msgDateTime,dateTimeNow;
        long timeDiff,timeDiffMin,timeDiffHr,timeDiffDay,timeDiffYr;
        msgId = request.getParameter("msgFileId");
        logId = request.getParameter("sId");
        st = delMsg.getMsgStatus(msgId);

        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        pDate = dtf.format(now);
        try {
            dateTimeNow = dtf.parse(pDate);
            msgDateTime = dtf.parse(st[3]);
            timeDiff = dateTimeNow.getTime() - msgDateTime.getTime();
            timeDiffMin = (timeDiff/(1000*60))%60;
            timeDiffHr = (timeDiff/(1000*60*60))%24;
            timeDiffDay = (timeDiff/(1000*60*60*24))%365;
            timeDiffYr = (timeDiff/(1000*60*60*24*365));

            System.out.println("Yr:"+timeDiffYr+" Day:"+timeDiffDay+" Hr:"+timeDiffHr+" Min:"+timeDiffMin);
            System.out.println(dateTimeNow);
            System.out.println(msgDateTime);

            if(((timeDiffYr<=0 && timeDiffDay<=0 && timeDiffHr<=0 && timeDiffMin<=59) && st[1]==logId) || st[0].equals("delete"))
            {
                System.out.println("DelE");
                delMsg.delMessageEveryone(msgId);
            }
            else
            {
                System.out.println("DelU");
                MessagesBean dMsg = new MessagesBean();
                dMsg.setMsgId(msgId);
                dMsg.setDelEmpId(logId);
                delMsg.delMessageFromUser(dMsg);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        request.setAttribute("guestId",guestId);
        request.setAttribute("guestName",guestName);
        request.setAttribute("fId",0);
        request.getRequestDispatcher("/chatSystem.jsp").forward(request, response);
    }

}
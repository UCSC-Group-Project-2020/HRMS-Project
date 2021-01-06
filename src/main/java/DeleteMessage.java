import chat.MessagesBean;
import chat.MessagesDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DeleteMessage extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        MessagesDao delMsg = new MessagesDao();
        String msgId,logId,pDate;
        String [] st = new String[4];
        Date msgDateTime,dateTimeNow;
        long timeDiff,timeDiffMin,timeDiffHr,timeDiffDay,timeDiffYr;
        msgId = request.getParameter("");
        logId = request.getParameter("");
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
            if(timeDiffYr<=0 && timeDiffDay<=0 && timeDiffHr<=0 && timeDiffMin<=59)
            {
                System.out.println("Deleted");
            }
            else
            {
                System.out.println("Cannot Delete");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

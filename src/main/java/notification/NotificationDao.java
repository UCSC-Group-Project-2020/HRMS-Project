package notification;

import notification.NotificationBean;
import DBconnection.DBconn;
import java.sql.*;

public class NotificationDao
{
    public NotificationBean getNotificationSt(Object logId)
    {
        NotificationBean nSt = new NotificationBean();
        int chatN, salaryN, leaveApplyN, leaveResponseN, complainN;

        Connection con = null;
        Statement statement = null;
        ResultSet rsNotifi = null;

        try
        {
            con = DBconn.getConnection();
            statement = con.createStatement();
            rsNotifi = statement.executeQuery("SELECT * FROM notification where receiverId = '"+logId+"'");

            while(rsNotifi.next())
            {
                chatN = rsNotifi.getInt("messageFlag");
                leaveApplyN = rsNotifi.getInt("leaveFlag");
                complainN = rsNotifi.getInt("complainSuggestionFlag");
                salaryN = rsNotifi.getInt("salaryFlag");
                leaveResponseN = rsNotifi.getInt("leaveResponseFlag");

                nSt.setChatN(chatN);
                nSt.setLeaveApplyN(leaveApplyN);
                nSt.setLeaveResponseN(leaveResponseN);
                nSt.setComplainN(complainN);
                nSt.setSalaryN(salaryN);
            }
            con.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return nSt;
    }

    public int updateChatNotificationSt(Object logId)
    {
        int msgCount = 0;
        try {
            Connection con = DBconn.getConnection();
            Statement statement = con.createStatement();
            ResultSet rsMsg = null;

            rsMsg = statement.executeQuery("SELECT COUNT(seenSt) as unseen FROM chat WHERE receiverId = '" + logId + "'  && seenSt = 0");
            if (rsMsg.next())
            {
                msgCount = rsMsg.getInt("unseen");
            }

            PreparedStatement st2 = con.prepareStatement("UPDATE notification SET messageFlag=?  WHERE receiverId = ?");
            if (msgCount == 0)
            {
                st2.setInt(1, 0);
                st2.setString(2, (String) logId);
                st2.executeUpdate();
            }
            else if (msgCount != 0)
            {
                st2.setInt(1, 1);
                st2.setString(2, (String) logId);
                st2.executeUpdate();
            }
            st2.close();
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return msgCount;
    }
}

package chat;

import chat.MessagesBean;
import DBconnection.DBconn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDao
{
    public List<MessagesBean> getMessages(Object myId, String guestId)
    {
        List<MessagesBean> msgList = new ArrayList<MessagesBean>();

        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        String msgId,senderId,receiverId,msgText,msgFileName;
        Blob msgFile;

        try
        {
            con = DBconn.getConnection();
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM chat WHERE (((senderId = '"+myId+"' AND receiverId = '"+guestId+"') OR (senderId = '"+guestId+"' AND receiverId = '"+myId+"')) AND (NOT(delEmpId = '"+myId+"') OR delEmpId IS NULL ))");
            while(rs.next())
            {
                MessagesBean msg = new MessagesBean();
                msgId = rs.getString("msgId");
                senderId = rs.getString("senderId");
                receiverId = rs.getString("receiverId");
                msgText = rs.getString("messageText");
                //msgFileName = rs.getString("fileName");
                //msgFile = rs.getBlob("msgFile");

                msg.setMsgId(msgId);
                msg.setSenderId(senderId);
                msg.setReceiverId(receiverId);
                msg.setMsgText(msgText);
                //msg.setMsgFileName(msgFileName);
                //msg.setMsgFile(msgFile);

                msgList.add(msg);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return msgList;
    }

    public void sendMessage(MessagesBean newMsg)
    {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        int senderId,receiverId;
        String msgText,msgFileName,res;
        Blob msgFile;

        senderId = Integer.parseInt(newMsg.getSenderId());
        receiverId = Integer.parseInt(newMsg.getReceiverId());
        msgText = newMsg.getMsgText();
        try
        {
            con = DBconn.getConnection();
            PreparedStatement st1 = con.prepareStatement("INSERT INTO chat (senderId,receiverId,messageText) VALUES(?,?,?)");
            st1.setInt(1,senderId);
            st1.setInt(2,receiverId);
            st1.setString(3,msgText);
            st1.executeUpdate();
            st1.close();
            con.close();
            res = "success";
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            res = "failed";
        }
    }
    public String[] getMsgStatus(String msgId)
    {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        String senderId,receiverId,delEmpId,msgDateTime;
        String [] status = new String[4];
        try
        {
            status[0] = "delete";
            con = DBconn.getConnection();
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT senderId,receiverId,delEmpId,dateTime FROM chat WHERE msgId = '"+msgId+"'");
            while(rs.next())
            {
                delEmpId = rs.getString("delEmpId");
                senderId = rs.getString("senderId");
                receiverId = rs.getString("receiverId");
                msgDateTime = rs.getString("dateTime");
                if(delEmpId == null)
                {
                    status[0] = "notDelete";
                }
                status[1] = senderId;
                status[2] = receiverId;
                status[3] = msgDateTime;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            status[0] = "Error";
            status[1] = "Error";
            status[2] = "Error";
            status[3] = "Error";
        }
        return status;
    }

    public void delMessageEveryone(int msgId)
    {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        try
        {
            con = DBconn.getConnection();
            PreparedStatement st = con.prepareStatement("DELETE From chat WHERE msgId=?");
            st.setInt(1,msgId);
            st.executeUpdate();
            st.close();
            con.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void delMessageFromUser(MessagesBean delMsg)
    {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        int msgId,delEmpId;
        msgId = Integer.parseInt(delMsg.getMsgId());
        delEmpId = Integer.parseInt(delMsg.getDelEmpId());
        try
        {
            con = DBconn.getConnection();
            PreparedStatement st = con.prepareStatement("UPDATE chat SET delEmpId=? WHERE msgId=?");
            st.setInt(1,delEmpId);
            st.setInt(2,msgId);
            st.executeUpdate();
            st.close();
            con.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}

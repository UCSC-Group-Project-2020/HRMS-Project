<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="user.EmployeeDao" %>
<%@ page import="user.UserBean" %>
<%@ page import="java.util.List" %>
<%@ page import="chat.MessagesDao" %>
<%@ page import="chat.MessagesBean" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Human Resource Management System</title>
    <link rel="icon" href="img/logo.png" sizes="25x25" type="image/png">
    <link rel="stylesheet" href="style/mainStyle.css">
    <link rel="stylesheet" href="style/chatSystem.css">
</head>

<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<div class="content">
    <form id="chatSys" action="chatmessages" method="POST">
        <div class="heading">
            <h3>Chat Corner</h3>
        </div>
        <div class="members">
            <table id="tableNames" class="employees">
                <tr></tr>
                <%
                    EmployeeDao empDao = new EmployeeDao();
                    List<UserBean> empList = empDao.searchChatEmployees();
                    for(UserBean employee:empList){
                        if(session.getAttribute("empId").equals(employee.getEmpId())){}
                        else{
                %>
                <tr>
                    <td hidden><%=employee.getEmpId()%></td>
                    <td><%=employee.getFName()%> <%=employee.getLName()%></td>
                </tr>
                <%}}%>
            </table>
        </div>
        <div class="chat">
            <%
                Object myId = session.getAttribute("empId");
                String gId =  (String) request.getAttribute("guestId");
                String gName = (String) request.getAttribute("guestName");
                if(gName==null){ gName = "Name";}
                MessagesDao msgDao = new MessagesDao();
                List<MessagesBean> msgList = msgDao.getMessages(myId, gId);
            %>
            <h3 name="gN" id="gN" class="name"><%=gName%></h3>
            <input type="text" class="input" name="sId" id="sId" value="<%=myId%>" hidden readonly>
            <input type="text" class="input" name="gId" id="gId" value="<%=gId%>" hidden readonly>
            <input type="text" class="input" name="gName" id="gName" value="<%=gName%>" hidden readonly>
            <input type="text" class="input" name="fId" id="fId" value="0" hidden readonly>

            <%if(gId!=null){%>
            <div id="chatMsg" class="msgs">
                <%
                    for(MessagesBean msg:msgList){
                        if(myId.equals(msg.getReceiverId())){
                %>
                <div class="chatMsgs">
                    <h2 class="received"><%=msg.getMsgText()%></h2>
                    <!--<a href="#"><h2 class="received"></h2><span class="sub-arrow"></span></a>
                    <ul>
                        <li><a href="#">Reply</a></li>
                        <li><a href="#">Delete Message</a></li>
                    </ul>!-->
                </div>
                <br>
                <%
                }else{
                %>
                <div class="chatMsgs">
                    <h2 class="sent"><%=msg.getMsgText()%></h2>
                    <!--<a href="#"><h2 class="sent"></h2><span class="sub-arrow"></span></a>
                    <ul>
                        <li><a href="#">Reply</a></li>
                        <li><a href="#">Delete Message</a></li>
                    </ul>!-->
                </div>
                <br><br>
                <%}}%>
            </div>
            <div class="sendMsg">
                <textarea id="msg" name="msg" class="input" cols="50" rows="3" placeholder="Type a Message"></textarea>
                <input type="file" class="fileChoose" name="fbtn">
                <input type="submit" class="send" name="Send" id="btnSend" value="Send">
            </div>
            <%}%>
        </div>
    </form>
</div>
<%@include file="mainDashboard.jsp" %>
<script type="text/javascript" src="js/chatSystem.js"></script>
</body>
</html>
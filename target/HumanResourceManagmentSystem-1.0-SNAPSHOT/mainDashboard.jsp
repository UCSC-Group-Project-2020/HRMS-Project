<%@ page import="notification.NotificationDao" %>
<%@ page import="notification.NotificationBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="head">
    <a href="login.jsp" class="Logout">Logout</a>
    <%
        Object logId = session.getAttribute("empId");

        NotificationDao notificationSt = new NotificationDao();
        NotificationBean nSt = notificationSt.getNotificationSt(logId);
        int msgCount = notificationSt.updateChatNotificationSt(logId);

    %>
    <%if(session.getAttribute("chatSys").equals(1)) {%><a href="chatSystem.jsp" class="Msgs" aria-readonly="true"<%if(nSt.getChatN()==1){%>style="background-color: crimson"<%}%>><%if(msgCount==0){%>Messages<%}else {%>Messages (<%=msgCount%>)<%}%></a><%}%>
    <%if(session.getAttribute("viewMySalary").equals(1)) {%><a href="mySalaryOverview.jsp" class="Salary"<%if(nSt.getSalaryN()==1){%>style="background-color: crimson" <%}%>>Calculated Salary</a><%}%>
    <%if(session.getAttribute("decisionLeave").equals(1)) {%><a href="approveOrRejectLeave.jsp" class="Leave" <%if(nSt.getLeaveApplyN()==1){%> style="background-color: forestgreen"<%}%>>Leave Requests</a><%}%>
    <%if(session.getAttribute("viewMyLeaves").equals(1)) {%><a href="myLeaveHistory.jsp" class="Leave" <%if(nSt.getLeaveResponseN()==1){%> style="background-color: forestgreen"<%}%> >Leave Response</a><%}%>
    <%if(session.getAttribute("viewComSug").equals(1)) {%><a href="viewComplains.jsp" class="com" <%if(nSt.getComplainN()==1){%>style="background-color: forestgreen"<%}%>>Complain/Suggestion</a><%}%>
</div>
<div class="main-menu">
    <img href="home.jsp" class="avater" src="img/avatar.svg" alt="">
    <h2 class="greeting" href="home.jsp">
        Hi,<%=session.getAttribute("firstName")%>!

    </h2>
    <nav class="menu">

        <ul class="main-nav-ul">
            <li>
                <a href="home.jsp">Home</a></li>
            <%if(session.getAttribute("empAdd").equals(1) || (session.getAttribute("empAdd").equals(1))
                    || (session.getAttribute("empDel").equals(1)) ) {%>
            <li>
                <a href="#">Employee<span class="sub-arrow"></span></a>
                <ul class="emp-show">
                    <%if(session.getAttribute("empAdd").equals(1)) {%><li><a href="addEmployee.jsp" >Add Employee</a></li><%}%>
                    <%if(session.getAttribute("empAdd").equals(1)) {%><li><a href="searchEmployee.jsp">Update Employee</a></li><%}%>
                    <%if(session.getAttribute("empDel").equals(1)) {%><li><a href="removeEmployee.jsp">Remove Employee</a></li><%}%>
                </ul>
            </li>
            <%}%>
            <%if(session.getAttribute("giveComSug").equals(1) || (session.getAttribute("viewComSug").equals(1))){%>
            <li>
                <a href="#">Complain/Suggestions<span class="sub-arrow"></span></a>
                <ul>
                    <%if(session.getAttribute("giveComSug").equals(1)) {%><li><a href="addComplains.jsp">Add Complain/Suggestions</a></li><%}%>
                    <%if(session.getAttribute("viewComSug").equals(1)) {%><li><a href="viewComplains.jsp">View Complain/Suggestions</a></li><%}%>
                </ul>
            </li>
            <%}%>
            <%if(session.getAttribute("viewMyAttend").equals(1) || (session.getAttribute("viewAllAttend").equals(1))){%>
            <li>
                <a href="#">Attendance<span class="sub-arrow"></span></a>
                <ul>
                    <%if(session.getAttribute("viewMyAttend").equals(1)) {%><li><a href="myAttendanceHistory.jsp">My Attendance History</a></li><%}%>
                    <%if(session.getAttribute("viewAllAttend").equals(1)) {%><li><a href="staffAttendanceHistory.jsp">Staff Attendance History</a></li><%}%>
                </ul>
            </li>
            <%}%>


            <%if(session.getAttribute("viewMySalary").equals(1) || (session.getAttribute("viewAllSalary").equals(1))
                    || (session.getAttribute("salaryManage").equals(1))){%>
            <li>
                <a href="#">Salary<span class="sub-arrow"></span></a>
                <ul>
                    <%if(session.getAttribute("viewMySalary").equals(1)) {%><li><a href="mySalaryOverview.jsp">My Salary Overview</a></li><%}%>
                    <%if(session.getAttribute("viewAllSalary").equals(1)) {%><li><a href="staffSalaryOverview.jsp">Staff Salary Overview</a></li><%}%>
                    <%if(session.getAttribute("salaryManage").equals(1)) {%><li><a href="salaryManagement.jsp">Salary Management</a></li><%}%>
                </ul>
            </li>

            <%}%>
            <%if(session.getAttribute("applyLeave").equals(1) || (session.getAttribute("viewMyLeaves").equals(1)) ||
                    (session.getAttribute("viewAllLeaves").equals(1))|| (session.getAttribute("decisionLeave").equals(1))){%>
            <li>
                <a href="#">Leave<span class="sub-arrow"></span></a>
                <ul>
                    <%if(session.getAttribute("applyLeave").equals(1)) {%><li><a href="applyForLeave.jsp">Apply For Leave</a></li><%}%>
                    <%if(session.getAttribute("viewMyLeaves").equals(1)) {%><li><a href="myLeaveHistory.jsp">My Leave History</a></li><%}%>
                    <%if(session.getAttribute("viewAllLeaves").equals(1)) {%><li><a href="staffLeaveHistory.jsp">Staff Leave History</a></li><%}%>
                    <%if(session.getAttribute("decisionLeave").equals(1)) {%> <li><a href="approveOrRejectLeave.jsp">Approve Or Reject Leaves</a></li><%}%>
                </ul>
            </li>
            <%}%>

            <%if(session.getAttribute("postAdd").equals(1) || (session.getAttribute("postAdd").equals(1))||
                    (session.getAttribute("postDel").equals(1)) || (session.getAttribute("chatSys").equals(1))) {%>

            <li>
                <a href="#">Social Intranet<span class="sub-arrow"></span></a>
                <ul><%if(session.getAttribute("chatSys").equals(1)) {%><li><a href="chatSystem.jsp">Chat System</a></li><%}%>
                    <%if(session.getAttribute("postAdd").equals(1)) {%><li><a href="addPost.jsp">Add Post</a></li><%}%>
                    <%if(session.getAttribute("postAdd").equals(1)) {%><li><a href="editPost.jsp">Update Post</a></li><%}%>
                    <%if(session.getAttribute("postAdd").equals(1)) {%><li><a href="deleteMyPost.jsp">Delete Post</a></li><%}%>
                </ul>
            </li>
            <%}%>
            <%if(session.getAttribute("genReport").equals(1)) {%>
            <li>
                <a>Reports<span class="sub-arrow"></span></a>
                <ul>
                    <%if(session.getAttribute("genReport").equals(1)) {%><li><a href="attendanceReport.jsp">Attendance Reports</a></li><%}%>
                    <%if(session.getAttribute("genReport").equals(1)) {%><li><a href="salaryReport.jsp">Salary Reports</a></li><%}%>
                    <%if(session.getAttribute("genReport").equals(1)) {%><li><a href="leaveReport.jsp">Leave Reports</a></li><%}%>
                </ul>
            </li>
            <%}%>
            <%if(session.getAttribute("editPersonalDetails").equals(1)) {%><li><a href="editAccountDetails.jsp">Change Personal Details</a></li><%}%>

            <%if(session.getAttribute("customizeData").equals(1)) {%><li><a href="customization.jsp">Customize the System</a></li><%}%>
        </ul>
    </nav>
</div>

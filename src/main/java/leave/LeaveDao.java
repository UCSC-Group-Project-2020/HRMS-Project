package leave;
import DBconnection.DBconn;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
public class LeaveDao {
    public List<LeaveBean> allLeaves(String toDateS,String fromDateS,String status,String type){
        List<LeaveBean> leaveList = new ArrayList<LeaveBean>();

        Connection con = null;
        Statement statement = null;
        ResultSet rs1,rs2 = null;
        String empId,leaveId,toDate,fromDate,appDate,authorizedPersonId,reson;

        if(toDateS==null){
            toDateS="9999-12-31";
        }
        if(fromDateS==null){
            fromDateS="0000-01-01";
        }
        if (status==null || status.equals("All")){
            status=null;
        }
        if (type==null || type.equals("All") ){
            type=null;
        }
        if(toDateS.isEmpty()){
            toDateS="9999-12-31";
        }
        if(fromDateS.isEmpty()){
            fromDateS="0000-01-01";
        }

        if(status==null && type==null && toDateS !=null && fromDateS!=null){
            if(toDateS.isEmpty()){
                toDateS="9999-12-31";
            }
            if(fromDateS.isEmpty()){
                fromDateS="0000-01-01";
            }

            try
            {
                con = DBconn.getConnection();
                statement = con.createStatement();
                rs1 = statement.executeQuery("SELECT * FROM `leavedetails` WHERE leaveFrom >= '"+fromDateS+"' and  leaveTo <= '"+toDateS+"'   ORDER BY `leavedetails`.`leaveId` DESC");
                while (rs1.next())
                {
                    LeaveBean leave = new LeaveBean();
                    leaveId= rs1.getString("leaveId");
                    empId=rs1.getString("empId");
                    appDate = rs1.getString("appliedDate");
                    fromDate=rs1.getString("leaveFrom");
                    toDate=rs1.getString("leaveTo");
                    status=rs1.getString("status");
                    type=rs1.getString("LeaveType");
                    empId=rs1.getString("empId");
                    leave.setEmpId(empId);
                    if(status.equals("0")){
                        status="Rejected";
                    }else if(status.equals("1")){
                        status="Pending";
                    }else if(status.equals("2")){
                        status="Approved";
                    }
                    authorizedPersonId=rs1.getString("authorizedPerson");
                    reson=rs1.getString("reason");

                    leave.setLeaveId(leaveId);
                    leave.setappDate(appDate);
                    leave.setfromDate(fromDate);
                    leave.settoDate(toDate);
                    leave.setReason(reson);
                    leave.setstatus(status);
                    leave.setType(type);
                    leave.setAuthorizedPersonId(authorizedPersonId);

                    leaveList.add(leave);

                }
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return leaveList;
        }

        if(status==null && type!=null && toDateS !=null && fromDateS!=null){
            if(toDateS.isEmpty()){
                toDateS="9999-12-31";
            }
            if(fromDateS.isEmpty()){
                fromDateS="0000-01-01";
            }

            try
            {
                con = DBconn.getConnection();
                statement = con.createStatement();
                rs1 = statement.executeQuery("SELECT * FROM `leavedetails` WHERE leaveFrom >= '"+fromDateS+"'  And  leaveTo <= '"+toDateS+"' and LeaveType='"+type+"'   ORDER BY `leavedetails`.`leaveId` DESC");
                while (rs1.next())
                {
                    LeaveBean leave = new LeaveBean();
                    leaveId= rs1.getString("leaveId");
                    appDate = rs1.getString("appliedDate");
                    fromDate=rs1.getString("leaveFrom");
                    toDate=rs1.getString("leaveTo");
                    status=rs1.getString("status");
                    type=rs1.getString("LeaveType");
                    empId=rs1.getString("empId");
                    leave.setEmpId(empId);

                    if(status.equals("0")){
                        status="Rejected";
                    }else if(status.equals("1")){
                        status="Pending";
                    }else if(status.equals("2")){
                        status="Approved";
                    }
                    authorizedPersonId=rs1.getString("authorizedPerson");
                    reson=rs1.getString("reason");

                    leave.setLeaveId(leaveId);
                    leave.setappDate(appDate);
                    leave.setfromDate(fromDate);
                    leave.settoDate(toDate);
                    leave.setReason(reson);
                    leave.setstatus(status);
                    leave.setType(type);
                    leave.setAuthorizedPersonId(authorizedPersonId);

                    leaveList.add(leave);
                }
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return leaveList;


        }

        if(status!=null && type==null && toDateS !=null && fromDateS!=null){
            if(toDateS.isEmpty()){
                toDateS="9999-12-31";
            }
            if(fromDateS.isEmpty()){
                fromDateS="0000-01-01";
            }

            try
            {
                con = DBconn.getConnection();
                statement = con.createStatement();
                rs1 = statement.executeQuery("SELECT * FROM `leavedetails` WHERE leaveFrom >= '"+fromDateS+"'  And  leaveTo <= '"+toDateS+"' and status='"+status+"'  ORDER BY `leavedetails`.`leaveId` DESC");
                while (rs1.next())
                {
                    LeaveBean leave = new LeaveBean();
                    leaveId= rs1.getString("leaveId");
                    appDate = rs1.getString("appliedDate");
                    fromDate=rs1.getString("leaveFrom");
                    toDate=rs1.getString("leaveTo");
                    status=rs1.getString("status");
                    type=rs1.getString("LeaveType");
                    empId=rs1.getString("empId");
                    leave.setEmpId(empId);

                    if(status.equals("0")){
                        status="Rejected";
                    }else if(status.equals("1")){
                        status="Pending";
                    }else if(status.equals("2")){
                        status="Approved";
                    }
                    authorizedPersonId=rs1.getString("authorizedPerson");
                    reson=rs1.getString("reason");

                    leave.setLeaveId(leaveId);
                    leave.setappDate(appDate);
                    leave.setfromDate(fromDate);
                    leave.settoDate(toDate);
                    leave.setReason(reson);
                    leave.setstatus(status);
                    leave.setType(type);
                    leave.setAuthorizedPersonId(authorizedPersonId);

                    leaveList.add(leave);

                }
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return leaveList;


        }

        if(status!=null && type!=null && toDateS !=null && fromDateS!=null){
            if(toDateS.isEmpty()){
                toDateS="9999-12-31";
            }
            if(fromDateS.isEmpty()){
                fromDateS="0000-01-01";
            }

            try
            {
                con = DBconn.getConnection();
                statement = con.createStatement();
                rs1 = statement.executeQuery("SELECT * FROM `leavedetails` WHERE leaveFrom >= '"+fromDateS+"'  And  leaveTo <= '"+toDateS+"' and status='"+status+"' and LeaveType='"+type+"' ORDER BY `leavedetails`.`leaveId` DESC");
                while (rs1.next())
                {
                    LeaveBean leave = new LeaveBean();
                    leaveId= rs1.getString("leaveId");
                    appDate = rs1.getString("appliedDate");
                    fromDate=rs1.getString("leaveFrom");
                    toDate=rs1.getString("leaveTo");
                    status=rs1.getString("status");
                    type=rs1.getString("LeaveType");
                    empId=rs1.getString("empId");
                    leave.setEmpId(empId);
                    if(status.equals("0")){
                        status="Rejected";
                    }else if(status.equals("1")){
                        status="Pending";
                    }else if(status.equals("2")){
                        status="Approved";
                    }
                    authorizedPersonId=rs1.getString("authorizedPerson");
                    reson=rs1.getString("reason");

                    leave.setLeaveId(leaveId);
                    leave.setappDate(appDate);
                    leave.setfromDate(fromDate);
                    leave.settoDate(toDate);
                    leave.setReason(reson);
                    leave.setstatus(status);
                    leave.setType(type);
                    leave.setAuthorizedPersonId(authorizedPersonId);

                    leaveList.add(leave);

                }
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return leaveList;


        }

        return leaveList;


    }
    public List<LeaveBean> myLeaves(String empId,String toDateS,String fromDateS,String status,String type){
        List<LeaveBean> leaveList = new ArrayList<LeaveBean>();

        Connection con = null;
        Statement statement = null;
        ResultSet rs1,rs2 = null;
        String leaveId,toDate,fromDate,appDate,authorizedPersonId,reson;

        if(toDateS==null){
            toDateS="9999-12-31";
        }
        if(fromDateS==null){
            fromDateS="0000-01-01";
        }
        if (status==null || status.equals("All")){
            status=null;
        }
        if (type==null || type.equals("All") ){
            type=null;
        }
        if(toDateS.isEmpty()){
            toDateS="9999-12-31";
        }
        if(fromDateS.isEmpty()){
            fromDateS="0000-01-01";
        }

        if(status==null && type==null && toDateS !=null && fromDateS!=null){
            if(toDateS.isEmpty()){
                toDateS="9999-12-31";
            }
            if(fromDateS.isEmpty()){
                fromDateS="0000-01-01";
            }

            try
            {
                con = DBconn.getConnection();
                statement = con.createStatement();
                rs1 = statement.executeQuery("SELECT * FROM `leavedetails` WHERE leaveFrom >= '"+fromDateS+"' and  leaveTo <= '"+toDateS+"' and   empId="+empId+" ORDER BY `leavedetails`.`leaveId` DESC");
                while (rs1.next())
                {
                    LeaveBean leave = new LeaveBean();
                    leaveId= rs1.getString("leaveId");
                    appDate = rs1.getString("appliedDate");
                    fromDate=rs1.getString("leaveFrom");
                    toDate=rs1.getString("leaveTo");
                    status=rs1.getString("status");
                    type=rs1.getString("LeaveType");
                    if(status.equals("0")){
                        status="Rejected";
                    }else if(status.equals("1")){
                        status="Pending";
                    }else if(status.equals("2")){
                        status="Approved";
                    }
                    authorizedPersonId=rs1.getString("authorizedPerson");
                    reson=rs1.getString("reason");

                    leave.setLeaveId(leaveId);
                    leave.setappDate(appDate);
                    leave.setfromDate(fromDate);
                    leave.settoDate(toDate);
                    leave.setReason(reson);
                    leave.setstatus(status);
                    leave.setType(type);
                    leave.setAuthorizedPersonId(authorizedPersonId);

                    leaveList.add(leave);
                    PreparedStatement st3= con.prepareStatement("UPDATE notification SET leaveResponseFlag=?  " +
                            "WHERE receiverId=?");

                    st3.setInt(1, 0);
                    st3.setString(2, empId);

                    st3.executeUpdate();
                }
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return leaveList;
        }

        if(status==null && type!=null && toDateS !=null && fromDateS!=null){
            if(toDateS.isEmpty()){
                toDateS="9999-12-31";
            }
            if(fromDateS.isEmpty()){
                fromDateS="0000-01-01";
            }

            try
            {
                con = DBconn.getConnection();
                statement = con.createStatement();
                rs1 = statement.executeQuery("SELECT * FROM `leavedetails` WHERE leaveFrom >= '"+fromDateS+"'  And  leaveTo <= '"+toDateS+"' and LeaveType='"+type+"' and empId="+empId+" ORDER BY `leavedetails`.`leaveId` DESC");
                while (rs1.next())
                {
                    LeaveBean leave = new LeaveBean();
                    leaveId= rs1.getString("leaveId");
                    appDate = rs1.getString("appliedDate");
                    fromDate=rs1.getString("leaveFrom");
                    toDate=rs1.getString("leaveTo");
                    status=rs1.getString("status");
                    type=rs1.getString("LeaveType");
                    if(status.equals("0")){
                        status="Rejected";
                    }else if(status.equals("1")){
                        status="Pending";
                    }else if(status.equals("2")){
                        status="Approved";
                    }
                    authorizedPersonId=rs1.getString("authorizedPerson");
                    reson=rs1.getString("reason");

                    leave.setLeaveId(leaveId);
                    leave.setappDate(appDate);
                    leave.setfromDate(fromDate);
                    leave.settoDate(toDate);
                    leave.setReason(reson);
                    leave.setstatus(status);
                    leave.setType(type);
                    leave.setAuthorizedPersonId(authorizedPersonId);

                    leaveList.add(leave);
                    PreparedStatement st3= con.prepareStatement("UPDATE notification SET leaveResponseFlag=?  " +
                            "WHERE receiverId=?");

                    st3.setInt(1, 0);
                    st3.setString(2, empId);

                    st3.executeUpdate();
                }
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return leaveList;


        }

        if(status!=null && type==null && toDateS !=null && fromDateS!=null){
            if(toDateS.isEmpty()){
                toDateS="9999-12-31";
            }
            if(fromDateS.isEmpty()){
                fromDateS="0000-01-01";
            }

            try
            {
                con = DBconn.getConnection();
                statement = con.createStatement();
                rs1 = statement.executeQuery("SELECT * FROM `leavedetails` WHERE leaveFrom >= '"+fromDateS+"'  And  leaveTo <= '"+toDateS+"' and status='"+status+"' and empId="+empId+" ORDER BY `leavedetails`.`leaveId` DESC");
                while (rs1.next())
                {
                    LeaveBean leave = new LeaveBean();
                    leaveId= rs1.getString("leaveId");
                    appDate = rs1.getString("appliedDate");
                    fromDate=rs1.getString("leaveFrom");
                    toDate=rs1.getString("leaveTo");
                    status=rs1.getString("status");
                    type=rs1.getString("LeaveType");
                    if(status.equals("0")){
                        status="Rejected";
                    }else if(status.equals("1")){
                        status="Pending";
                    }else if(status.equals("2")){
                        status="Approved";
                    }
                    authorizedPersonId=rs1.getString("authorizedPerson");
                    reson=rs1.getString("reason");

                    leave.setLeaveId(leaveId);
                    leave.setappDate(appDate);
                    leave.setfromDate(fromDate);
                    leave.settoDate(toDate);
                    leave.setReason(reson);
                    leave.setstatus(status);
                    leave.setType(type);
                    leave.setAuthorizedPersonId(authorizedPersonId);

                    leaveList.add(leave);
                    PreparedStatement st3= con.prepareStatement("UPDATE notification SET leaveResponseFlag=?  " +
                            "WHERE receiverId=?");

                    st3.setInt(1, 0);
                    st3.setString(2, empId);

                    st3.executeUpdate();
                }
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return leaveList;


        }

        if(status!=null && type!=null && toDateS !=null && fromDateS!=null){
            if(toDateS.isEmpty()){
                toDateS="9999-12-31";
            }
            if(fromDateS.isEmpty()){
                fromDateS="0000-01-01";
            }

            try
            {
                con = DBconn.getConnection();
                statement = con.createStatement();
                rs1 = statement.executeQuery("SELECT * FROM `leavedetails` WHERE leaveFrom >= '"+fromDateS+"'  And  leaveTo <= '"+toDateS+"' and status='"+status+"' and LeaveType='"+type+"' and empId="+empId+" ORDER BY `leavedetails`.`leaveId` DESC");
                while (rs1.next())
                {
                    LeaveBean leave = new LeaveBean();
                    leaveId= rs1.getString("leaveId");
                    appDate = rs1.getString("appliedDate");
                    fromDate=rs1.getString("leaveFrom");
                    toDate=rs1.getString("leaveTo");
                    status=rs1.getString("status");
                    type=rs1.getString("LeaveType");
                    if(status.equals("0")){
                        status="Rejected";
                    }else if(status.equals("1")){
                        status="Pending";
                    }else if(status.equals("2")){
                        status="Approved";
                    }
                    authorizedPersonId=rs1.getString("authorizedPerson");
                    reson=rs1.getString("reason");

                    leave.setLeaveId(leaveId);
                    leave.setappDate(appDate);
                    leave.setfromDate(fromDate);
                    leave.settoDate(toDate);
                    leave.setReason(reson);
                    leave.setstatus(status);
                    leave.setType(type);
                    leave.setAuthorizedPersonId(authorizedPersonId);

                    leaveList.add(leave);
                    PreparedStatement st3= con.prepareStatement("UPDATE notification SET leaveResponseFlag=?  " +
                            "WHERE receiverId=?");

                    st3.setInt(1, 0);
                    st3.setString(2, empId);

                    st3.executeUpdate();
                }
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return leaveList;


        }

        return leaveList;


    }
    public List<LeaveBean> searchEmployeeToApproveLeave(){
        List<LeaveBean> leaveList = new ArrayList<LeaveBean>();

        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        String fName,lName,NIC,empId;
        try
        {
            con = DBconn.getConnection();
            statement = con.createStatement();

            rs = statement.executeQuery("SELECT user.empId, user.firstName, user.lastName, " +
                    "user.NIC ,userprivilege.leavesApprovalRejection " +
                    "FROM user INNER JOIN userprivilege ON " +
                    "user.empId = userprivilege.empId " +
                    "WHERE userprivilege.leavesApprovalRejection = 1");

            while (rs.next())
            {
                LeaveBean leave = new LeaveBean();
                empId = rs.getString("empId");
                fName = rs.getString("firstName");
                lName = rs.getString("lastName");
                NIC = rs.getString("NIC");


                leave.setEmpId(empId);
                leave.setFName(fName);
                leave.setLName(lName);
                leave.setNIC(NIC);
                leaveList.add(leave);

            }
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return leaveList;
    }
    public List<LeaveBean> employeeLeaveRequests(String empId) {
        List<LeaveBean> leaveRequestList = new ArrayList<LeaveBean>();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        String fName,lName,reson,leaveId,fromDate,toDate,authorizedPersonId,type;
        int remPayed,remNoPay,remMedical;
        try
        {
            con = DBconn.getConnection();
            statement = con.createStatement();

            rs = statement.executeQuery("SELECT user.empId, user.firstName,user.lastName ,leavedetails.* ,employeeleavedetails.*  FROM user INNER JOIN employeeleavedetails ON user.empId = employeeleavedetails.empId  INNER JOIN leavedetails ON user.empId = leavedetails.empId WHERE leavedetails.status = 1 and leavedetails.authorizedPerson ="+empId);

            while (rs.next())
            {
                LeaveBean leave = new LeaveBean();
                leaveId = rs.getString("leaveId");
                fName = rs.getString("firstName");
                empId = rs.getString("empId");
                fromDate=rs.getString("leaveFrom");
                toDate=rs.getString("leaveTo");
                lName = rs.getString("lastName");
                reson = rs.getString("reason");
                type=rs.getString("LeaveType");

                remPayed=rs.getInt("remainingPayedLeaves");
                remNoPay= rs.getInt("remainingNoPayLeaves");
                remMedical=rs.getInt("remainingMedicalLeaves");

                authorizedPersonId=rs.getString("authorizedPerson");

                leave.setAuthorizedPersonId(authorizedPersonId);
                leave.setFName(fName);
                leave.setLName(lName);
                leave.setEmpId(empId);
                leave.setfromDate(fromDate);
                leave.settoDate(toDate);
                leave.setReason(reson);
                leave.setLeaveId(leaveId);
                leave.setType(type);
                leave.setremPayedLeaves(remPayed);
                leave.setremNoPayedLeaves(remNoPay);
                leave.setremMedicalLeaves(remMedical);
                leaveRequestList.add(leave);


            }
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return leaveRequestList;
    }


    public String approveOrRejectLeave(LeaveBean newLeave) {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        String status,type=null, authorizedPersonId, leaveId, empId = null, res, fromDate = null, toDate = null, appDate = null;
        int totalLeaves = 0, remainingLeaves = 0, takenLeaves = 0;

        int remPayed=0,remNoPay=0,remMedical=0;
        int tackenPayed=0,tackenNoPay=0,tackenMedical=0;


        leaveId = newLeave.getLeaveId();
        status = newLeave.getstatus();
        authorizedPersonId = newLeave.getAuthorizedPersonId();
        //type=newLeave.getType();


        try {
            con = DBconn.getConnection();
            statement = con.createStatement();

            rs = statement.executeQuery("SELECT leavedetails.*, employeeleavedetails.* FROM leavedetails INNER join employeeleavedetails ON leavedetails.empId=employeeleavedetails.empId where leavedetails.leaveId = " + leaveId);

            if (rs.next()) {

                empId = rs.getString("empId");
                fromDate = rs.getString("leaveFrom");
                toDate = rs.getString("leaveTo");
                appDate = rs.getString("appliedDate");
                type=rs.getString("LeaveType");
                System.out.println("*****************************************************************************Type = "+type);

                remPayed=rs.getInt("remainingPayedLeaves");
                remNoPay= rs.getInt("remainingNoPayLeaves");
                remMedical=rs.getInt("remainingMedicalLeaves");

                tackenPayed=rs.getInt("takenPayedLeaves");
                tackenNoPay= rs.getInt("tackenNoPayLeaves");
                tackenMedical=rs.getInt("tackenMedicalLeaves");

                if(type .equals("Payed") ){
                    remainingLeaves=remPayed;
                    takenLeaves=tackenPayed;
                    System.out.println("Payed payed payed");
                    System.out.println(" remainingLeaves ........." + remainingLeaves);
                    System.out.println(" takenLeaves ..........." + takenLeaves);
                }
                else if(type.equals("NoPay") ){
                    remainingLeaves=remNoPay;
                    takenLeaves=tackenNoPay;
                    System.out.println("Nopay Nopay Nopay");
                    System.out.println(" remainingLeaves ............." + remainingLeaves);
                    System.out.println(" takenLeaves ......." + takenLeaves);
                }
               else if(type .equals("Medical") ){
                    remainingLeaves=remMedical;
                    takenLeaves=tackenMedical;
                    System.out.println("Medical Medical Medical");
                    System.out.println(" remainingLeaves ........" + remainingLeaves);
                    System.out.println(" takenLeaves ................" + takenLeaves);
                }

            }
            con.close();
        } catch (SQLException e) {
            res ="UnSuccessful";
            e.printStackTrace();
            return res;

        }


        if (0 >= remPayed || 0 >= remNoPay ||0 >= remMedical  ) {

            status = "0";
        }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {

                Date d1 = sdf.parse(fromDate);
                Date d2 = sdf.parse(toDate);
                Date d3 = sdf.parse(appDate);


                long diffOfAppandFrom = d3.getTime() - d1.getTime();

                Calendar c1 = Calendar.getInstance();
                c1.setTime(d1);

                int w1 = c1.get(Calendar.DAY_OF_WEEK);
                c1.add(Calendar.DAY_OF_WEEK, -w1);

                Calendar c2 = Calendar.getInstance();
                c2.setTime(d2);

                int w2 = c2.get(Calendar.DAY_OF_WEEK);
                c2.add(Calendar.DAY_OF_WEEK, -w2);

                long days = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
                long daysWithoutWeekendDays = days - (days * 2 / 7);
                int numberOfLeaves = 0;
                if (w1== Calendar.SATURDAY || w1==Calendar.SUNDAY){

                    numberOfLeaves= -1;
                    System.out.println("            numberOfLeaves = " + numberOfLeaves);
                    System.out.println(w1);
                    System.out.println(w2);
                }
                if (w1 == Calendar.SUNDAY && w2 != Calendar.SATURDAY) {
                    w1 = Calendar.MONDAY;
                } else if (w1 == Calendar.SATURDAY && w2 != Calendar.SUNDAY) {
                    w1 = Calendar.FRIDAY;
                }

                if (w2 == Calendar.SUNDAY) {
                    w2 = Calendar.MONDAY;
                } else if (w2 == Calendar.SATURDAY) {
                    w2 = Calendar.FRIDAY;
                }

                numberOfLeaves = numberOfLeaves +(int) (daysWithoutWeekendDays - w1 + w2)+1;

                System.out.println("            numberOfLeaves = " + numberOfLeaves);
                if(numberOfLeaves>remainingLeaves){
                    System.out.println(numberOfLeaves+">"+remainingLeaves);
                    status="0";
                }

                long diffOfAppandFromDay = (diffOfAppandFrom / (1000 * 60 * 60 * 24)) % 365;
                if(status.equals("0")){
                    //status is rejected
                    System.out.println("status is rejected");
                    numberOfLeaves = 0;
                    remainingLeaves = remainingLeaves - numberOfLeaves;
                    takenLeaves = numberOfLeaves + takenLeaves;

                }
                else if (diffOfAppandFromDay > 0) {
                    //Applied Date > From Date
                    System.out.println("        Applied Date > From Date");
                    numberOfLeaves = 0;
                    remainingLeaves = remainingLeaves - numberOfLeaves;
                    takenLeaves = numberOfLeaves + takenLeaves;
                    status = "0";
                } else if (numberOfLeaves < 0) {
                    //From Date > To Date
                    System.out.println("        From Date > To Date");
                    numberOfLeaves = 0;
                    remainingLeaves = remainingLeaves - numberOfLeaves;
                    takenLeaves = numberOfLeaves + takenLeaves;
                    status = "0";
                } else {
                    System.out.println(" remainingLeaves = " + remainingLeaves);
                    System.out.println(" takenLeaves = " + numberOfLeaves);
                    remainingLeaves = remainingLeaves - numberOfLeaves;
                    takenLeaves = numberOfLeaves + takenLeaves;
                }

            System.out.println("            numberOfLeaves ==" + numberOfLeaves);
            System.out.println("            remainingLeaves ==" + remainingLeaves);
            System.out.println("            takenLeaves ==" + numberOfLeaves);

        }catch(ParseException e){
            e.printStackTrace();
        }
        System.out.println("Remaining  = "+remPayed+ " , "+remNoPay+ " , " +remMedical);
        System.out.println("Tacken  = "+tackenPayed+ " , "+tackenNoPay+ " , " +tackenMedical);
        if(type .equals("Payed") ){
            remPayed=remainingLeaves;
            tackenPayed=takenLeaves;
            System.out.println("Payed ============================= payed");
        }else if(type.equals("NoPay") ){
            remNoPay=remainingLeaves;
            tackenNoPay=takenLeaves;
            System.out.println("NoPay ========================= Nopay");
        }else if(type .equals("Medical") ){
            remMedical=remainingLeaves;
            tackenMedical=takenLeaves;
            System.out.println("Medical ============================ Medical");
        }
        System.out.println("Remaining  = "+remPayed+ " , "+remNoPay+ " , " +remMedical);
        System.out.println("Tacken  = "+tackenPayed+ " , "+tackenNoPay+ " , " +tackenMedical);
        try {
            con = DBconn.getConnection();
            PreparedStatement st1 = con.prepareStatement("update leavedetails set status=?  where leaveId=" + leaveId);
            st1.setInt(1, Integer.parseInt(status));
            st1.executeUpdate();

            PreparedStatement st2 = con.prepareStatement("UPDATE employeeleavedetails SET remainingPayedLeaves=? , takenPayedLeaves=? , remainingNoPayLeaves=? , tackenNoPayLeaves=? , remainingMedicalLeaves=? , tackenMedicalLeaves=? WHERE empId=?");
            st2.setInt(1,remPayed);
            st2.setInt(2,tackenPayed);
            st2.setInt(3, remNoPay);
            st2.setInt(4,tackenNoPay);
            st2.setInt(5,remMedical);
            st2.setInt(6, tackenMedical);
            st2.setString(7, empId);
            st2.executeUpdate();

            PreparedStatement st3 = con.prepareStatement("UPDATE notification SET leaveResponseFlag=? WHERE receiverId=?");
            st3.setInt(1, 1);
            st3.setString(2, empId);
            st3.executeUpdate();


        }catch (SQLException throwables) {
                throwables.printStackTrace();
                res ="UnSuccessful";
            }

        try {
            con = DBconn.getConnection();
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT user.empId, user.firstName,user.lastName ,leavedetails.* ,leavedetails.reason FROM user INNER JOIN leavedetails ON user.empId = leavedetails.empId WHERE leavedetails.status = 1 and leavedetails.authorizedPerson ="+authorizedPersonId);

            boolean i= rs.next();

            if(i){}
            else {
                PreparedStatement st3 = con.prepareStatement("UPDATE notification SET leaveFlag=? " +
                        " WHERE receiverId=?");

                st3.setInt(1, 0);
                st3.setString(2, authorizedPersonId);

                st3.executeUpdate();
            }

            res ="Successful";

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            res ="UnSuccessful";
        }
        return res;
    }
    public String addplyLeave(LeaveBean newLeave) {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        String toDate,res, authorizedPersonId, fromDate, appDate,reason,empId,leaveId,type;


          empId = newLeave.getEmpId();
         leaveId = newLeave.getLeaveId();

        toDate = newLeave.gettoDate();
        fromDate = newLeave.getfromDate();
        appDate = newLeave.getappDate();
        authorizedPersonId = newLeave.getAuthorizedPersonId();
        reason = newLeave.getReason();
        type = newLeave.getType();


        try {
            con = DBconn.getConnection();
            PreparedStatement st1 = con.prepareStatement("INSERT INTO leavedetails VALUES(?,?,?,?,?,?,?,?,?)");


            st1.setString(1, leaveId);
            st1.setString(2, empId);
            st1.setString(3, appDate);
            st1.setString(4,fromDate);
            st1.setString(5, toDate);
            st1.setString(6, reason);
            st1.setInt(7, 1);

            //0-rejected
            //1-pending
            //2-approve
            st1.setString(8, type);
            st1.setString(9, authorizedPersonId);

            st1.executeUpdate();
            PreparedStatement st3= con.prepareStatement("UPDATE notification SET leaveFlag=?  " +
                    "WHERE receiverId=?");

            st3.setInt(1, 1);
            st3.setString(2, authorizedPersonId);

            st3.executeUpdate();

            res ="Successful";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            res ="Unsuccessful";
        }
        return res;
    }
}
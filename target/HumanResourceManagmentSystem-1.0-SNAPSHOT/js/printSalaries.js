function SalariestoPDF() {

    const  rowCount = document.getElementById("myTable").rows.length;
    const row = document.getElementById("myTable").rows[0].cells.length;
    const generateData = function (amount) {
        var result = [];

        for (var i = 0; i < amount; i += 1) {

            const EmployeeId = document.getElementById("myTable").rows[i].cells.item(0).innerHTML;
            var  From= document.getElementById("myTable").rows[i].cells.item(1).innerHTML;
            var To = document.getElementById("myTable").rows[i].cells.item(2).innerHTML;
            var NoPay = document.getElementById("myTable").rows[i].cells.item(3).innerHTML;
            var Absences = document.getElementById("myTable").rows[i].cells.item(4).innerHTML;
            var Basic = document.getElementById("myTable").rows[i].cells.item(6).innerHTML;
            var Daily = document.getElementById("myTable").rows[i].cells.item(5).innerHTML;
            var OTRate = document.getElementById("myTable").rows[i].cells.item(7).innerHTML;
            var OTHours = document.getElementById("myTable").rows[i].cells.item(8).innerHTML;
            var Total = document.getElementById("myTable").rows[i].cells.item(9).innerHTML;


            var data = {
                EmployeeId: EmployeeId,
                From: From,
                To: To,
                NoPay: NoPay,
                Absences: Absences,
                Basic: Basic,
                Daily: Daily,
                OTRate: OTRate,
                OTHours: OTHours,
                Total: Total
            };
            result.push(Object.assign({}, data));
        }
        return result;
    };
    function createHeaders(keys) {
        var result = [];
        for (var i = 0; i < keys.length; i += 1) {
            result.push({
                id: keys[i],
                name: keys[i],
                prompt: keys[i],
                width: 38,
                top:5,
                align: "center",
                padding: 0
            });
        }
        return result;
    }
    var headers = createHeaders([
        "EmployeeId",
        "From",
        "To",
        "NoPay",
        "Absences",
        "Basic",
        "Daily",
        "OTRate",
        "OTHours",
        "Total"
    ]);


    var doc = new jsPDF({orientation: "landscape" });
    doc.setFontType("bold");
    doc.setFontSize(32);
    doc.text("Human Resource Management System",50,25);


    doc.setFontType("normal");
    doc.setFontSize(16);
    doc.text("Staff Members Salaries ",125,35);

    var date = new Date();
    var d=date.getDate();
    var m = date.getMonth();
    var y =date.getFullYear();
    doc.setLineWidth(0.5);
    doc.line(6, 42, 291, 42);


    doc.setFontSize(14);
    doc.text("Date  "+y+"-"+m+1+"-"+d,10,50);

    var detail = document.getElementById("result").rows[0].cells.item(0).innerHTML;

    doc.setFontSize(14);
    doc.text(detail,40,45);

    doc.setFontSize(14);
    doc.text(rowCount+" Records ",262,50);

    doc.setLineWidth(0.5);
    doc.line(6, 55, 291, 55);

    doc.setFontSize(20);
    doc.setTextColor("#000");
    doc.table(6,60, generateData(rowCount), headers);
    doc.save("HRMS_Staff_Salaries");

}
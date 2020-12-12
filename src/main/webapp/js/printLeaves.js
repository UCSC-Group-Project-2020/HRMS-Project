function toPDF() {

    const  rowCount = document.getElementById("myTable").rows.length;
    const row = document.getElementById("myTable").rows[0].cells.length;
    const generateData = function (amount) {
        var result = [];

        for (var i = 0; i < amount; i += 1) {

            const Leave = document.getElementById("myTable").rows[i].cells.item(0).innerHTML;
            var Employee = document.getElementById("myTable").rows[i].cells.item(1).innerHTML;
            var Applied = document.getElementById("myTable").rows[i].cells.item(2).innerHTML;
            var From = document.getElementById("myTable").rows[i].cells.item(3).innerHTML;
            var To = document.getElementById("myTable").rows[i].cells.item(4).innerHTML;
            var Authorized = document.getElementById("myTable").rows[i].cells.item(6).innerHTML;
            var Reason = document.getElementById("myTable").rows[i].cells.item(5).innerHTML;
            var Status = document.getElementById("myTable").rows[i].cells.item(7).innerHTML;
            var Type = document.getElementById("myTable").rows[i].cells.item(8).innerHTML;


            var data = {
                Leave: Leave,
                Employee: Employee,
                Applied: Applied,
                From: From,
                To: To,
                Reason: Reason,
                Status: Status,
                Type: Type,
                Authorized: Authorized
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
                width: 40,

                top:5,
                align: "center",
                padding: 0
            });
        }
        return result;
    }
    var headers = createHeaders([
        "Leave",
        "Employee",
        "Applied",
        "From",
        "To",
        "Reason",
        "Status",
        "Type",
        "Authorized"
    ]);


    var doc = new jsPDF({orientation: "landscape" });
    doc.setFontType("bold");
    doc.setFontSize(32);
    doc.text("Human Resource Management System",50,25);


    doc.setFontType("normal");
    doc.setFontSize(16);
    doc.text("Staff Members Leaves ",125,35);

    var date = new Date();
    var d=date.getDate();
    var m = date.getMonth();
    var y =date.getFullYear();

    doc.setLineWidth(0.5);
    doc.line(15, 42, 285, 42);

    doc.setFontSize(14);
    doc.text("Date  "+y+" - "+m+" - "+d,15,50);

    var detail = document.getElementById("result").rows[0].cells.item(0).innerHTML;

    doc.setFontSize(14);
    doc.text(detail,50,45);

    doc.setFontSize(14);
    doc.text(rowCount+" Records ",255,50);

    doc.setLineWidth(0.5);
    doc.line(15, 55, 285, 55);

    doc.setFontSize(20);
    doc.setTextColor("#000");
    doc.table(15,60, generateData(rowCount), headers);
    doc.save("HRMS_Staff_Leaves");

}
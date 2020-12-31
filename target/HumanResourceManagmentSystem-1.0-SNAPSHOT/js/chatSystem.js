var table = document.getElementById('tableNames');

for (var i = 1; i < table.rows.length; i++) {
    table.rows[i].onclick = function () {

        document.getElementById("gName").value = this.cells[1].innerHTML;
        document.getElementById("gId").value = this.cells[0].innerHTML;
        document.getElementById("fId").value = "1";
        document.getElementById('chatSys').action = "chatmessages";
        document.getElementById('chatSys').submit();
    };
}
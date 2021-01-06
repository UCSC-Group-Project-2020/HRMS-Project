window.onload = function()
{
    $("#chatMsg").scrollTop(600)
}

function getFileName() {
    var fullPath = document.getElementById('fbtn').value;
    if (fullPath) {
        var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
        var filename = fullPath.substring(startIndex);
        if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0)
        {
            filename = filename.substring(1);
            document.getElementById("fileName").value = filename;
        }
    }
}

$(document).ready(function(){
    $("#msg").bind("change", function() {
        if ($(this).val() == ""){
            $("#btnSend").hidden = true;
        } else {
            $("#btnSend").hidden = false;
        };
    });
});
function downloadFile(mId)
{
    document.getElementById("msgFileId").value = mId;
    document.getElementById('chatSys').action = "downloadfile";
    document.getElementById('chatSys').submit();
}

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
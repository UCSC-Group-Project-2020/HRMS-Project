let n=0;
function remImage(obj)
{
    obj.remove();
}

$(document).ready(getSelectedCB);
CountSelectedCB = [];
function getSelectedCB()
{
    $("input:checkbox").change(function()
    {

        CountSelectedCB.length = 0;
        $("input:checkbox").each(function()
        {
            if ($(this).is(":checked"))
            {
                CountSelectedCB.push($(this).attr("value"));
            }
        });

        $('input[name=imgId]').val(CountSelectedCB);
    });
}

let i=1;
function addImage()
{
    var filein = document.createElement("input");
    filein.type = "file";
    filein.name = "imgFile"+i;
    filein.id = "imgFile"+i;
    filein.className = "fileChoose";
    filein.accept = "image/*";

    var filelbl = document.createElement("label");
    filelbl.id = "lblFile"+i;
    filelbl.htmlFor = "imgFile"+i;
    filelbl.className = "limg";
    filelbl.onclick = function(){remSelectImg(this.htmlFor,this)};
    filelbl.innerHTML = "-";

    document.getElementById("m").appendChild(filelbl);
    document.getElementById("m").appendChild(filein);

    var elem = document.getElementById("imgFile"+i);
    if(elem && document.createEvent)
    {
        var evt = document.createEvent("MouseEvents");
        evt.initEvent("click", true, false);
        elem.dispatchEvent(evt);
    }
    document.getElementById("tot").value=i;
    i++;
}

function remSelectImg(eId,obj2)
{
    var obj1 = document.getElementById(eId);
    obj2.remove();
    obj1.remove();
}
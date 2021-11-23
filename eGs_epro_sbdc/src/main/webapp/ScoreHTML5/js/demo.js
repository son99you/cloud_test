/**
 * Created by jhkoo77 on 2015-09-03.
 */

function objectToTable(caption, data) {
    caption = caption || "";
    var table = '<table class="custom table table-striped"><captoin>'+caption+'</captoin><col width=30% ></col><col width=70%></col>';
    for(var prop in data) {
        table += '<tr><td>';
        table += prop;
        table += '</td><td style="word-break:break-all" >';
        if(typeof data[prop] == "object") {
            table += objectToTable("",data[prop]);
        }
        else {
            table += data[prop];
        }
        table += '</td></tr>';

    }
    table += '</table>';
    return table;
}

function add0(num) {
    var ret = "0";
    if(num >= 10)
        return num;
    return ret + num;
}
function getDateString(date) {
    var ret = "";
    ret += date.getFullYear();
    ret += "-";
    ret += add0(date.getMonth());
    ret += "-";
    ret += add0(date.getDay());
    ret += " ";
    ret += add0(date.getHours());
    ret += ":";
    ret += add0(date.getMinutes());
    ret += ":";
    ret += add0(date.getSeconds());
    return ret;

}

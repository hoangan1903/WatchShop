$(document).ready(function(){
    loadWithAPI();
})
function loadWithAPI() {
    var url = "/rest/alerts"
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var obj = JSON.parse(this.responseText);
            $('#mytable thead').append('<th>ID</th>' +
            '<th>Content</th>'+
            '<th>Edit</th>'+
            '<th>Delete</th>');
            $.each(obj.alerts,function(key,value){
                $('#mytable tbody').append('<tr>'
                + '<td>'
                + value.id
                + '</td>'
                + '<td>'
                + value.content
                + '</td>'
                + '<td>'
                + '<button class="btn btn-primary" data-title="Edit"'
                + 'data-toggle="modal" data-target="#edit" onclick="editOnClick('
                + value.id
                + ')">'
                + '<span class="icon text-white-50"> <i class="material-icons">edit</i>'
                + '</span>'
                + '</button>'
                + '</p>'
                + '</td>'
                + '<td>'
                + '<button class="btn btn-danger" data-title="Delete"'
                + 'data-toggle="modal" data-target="#delete" onclick="deleteOnClick('
                + value.id
                + ')">'
                + '<span class="icon text-white-50"><i class="material-icons">delete</i>'
                + '</span>'
                + '</button>'
                + '</p>'
                + '</td>'
                + '</tr>');
            })
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}
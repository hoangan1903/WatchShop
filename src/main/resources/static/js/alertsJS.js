var id=null;
$(document).ready(function(){
    loadWithAPI();
})
function loadWithAPI() {
	$('#mytable thead').empty();
	$('#mytable tbody').empty();
    var url = "/rest/alerts";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var obj = JSON.parse(this.responseText);
            $('#mytable thead').append('<th>ID</th>' +
            '<th>Content</th>'+
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
function deleteById(){
	if (id!==null) {
		var url = "/rest/alerts/"+id;
		console.log(url);
		var xhttp = new XMLHttpRequest();
		xhttp.open("DELETE",url,true);
		xhttp.onload = function() {
			if (xhttp.readyState == 4 && xhttp.status == "200") {
				console.log("delete ok");
				alert("Delete Success");
				location.reload();
			}
		}
		xhttp.send();
	}
	
}
function deleteOnClick(idTemp) {
    id = idTemp;
}
function submitSave() {
	var url = "/rest/alerts";
	var content = $('#addContent').val();
	var data = {"content":content}
	var jsonData = JSON.stringify(data);
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", url, true);
	xhttp.setRequestHeader('Content-type',
			'application/json; charset=utf-8');
	xhttp.onload = function() {
		if (xhttp.readyState == 4 && xhttp.status == "200") {
			console.log("Add ok");
			alert("Add Success");
			location.reload();
		}
	}
	console.log(url);
	xhttp.send(jsonData);
	
}

function deleteAll(){
	var url = "/rest/alerts";
	var xhttp = new XMLHttpRequest();
	xhttp.open("DELETE",url,true);
	xhttp.onload = function() {
		if (xhttp.readyState == 4 && xhttp.status == "200") {
			console.log("delete ok");
			alert("Delete Success");
			location.reload();
		}
	}
	xhttp.send();
}

var idFunct = -1;
var idValueFunct = -1;
window.onload = function () {
    loadWithAPI(null);
}

function loadWithAPI(id) {
    /*
    id= 1 : firm
    id= 2 : Origin
    id= 3 : Model
    */
    appendAddArea(id);
    setTitleInforForFunction(id);
    var url = chooseUrlToGetAPI(id);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var obj = JSON.parse(this.responseText);
            chooseTableTHeadAppend(id);
            loopForAppendTBodyTable(id, obj, false);

        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function fliterOtherFunction(id) {
    $('#mytable thead').empty();
    $('#mytable tbody').empty();
    $('#addArea').empty();
    setTimeout(function () {
        loadWithAPI(id);
    }, 100);
    idFunct = id;
    //Sleep 0.1s Vì nhanh quá khung hình sẽ giựt do bất đồng bộ 
}
function chooseUrlToGetAPI(id) {
    /*
   id= 1 : firm
   id= 2 : Origin
   id= 3 : Model
   */
    switch (id) {
        case 1:
            return "/rest/firms";
        case 2:
            return "/rest/origins";
        case 3:
            return "/rest/models";
        default:
            return "/rest/firms";
    }
}

function setTitleInforForFunction(id) {
    /*
   id= 1 : firm
   id= 2 : Origin
   id= 3 : Model
   */
    switch (id) {
        case 1:
            $('#functionName').html("Firms");
            $('#titleSearch').html("Type name of firm in the Search");
            break;
        case 2:
            $('#functionName').html("Origins");
            $('#titleSearch').html("Type name of origin in the Search");
            break;
        case 3:
            $('#functionName').html("Models");
            $('#titleSearch').html("Type name of model in the Search");
            break;
        default:
            $('#functionName').html("Firms");
            $('#titleSearch').html("Type name of firms in the Search");
            break;
    }
}

function chooseTableTHeadAppend(id) {
    /*
   id= 1 : firm
   id= 2 : Origin
   id= 3 : Model
   */
    var url = null;
    switch (id) {
        case 1:
            url = '<th>ID</th>' +
                '<th>Name</th>' +
                '<th>Action</th>';
            break;
        case 2:
            url = '<th>ID</th>' +
                '<th>Name</th>' +
                '<th>Action</th>';
            break;
        case 3:
            url = '<th>ID</th>' +
                '<th>Name</th>' +
                '<th>Action</th>';
            break;
        default:
            url = '<th>ID</th>' +
                '<th>Name</th>' +
                '<th>Action</th>';
            break;
    }
    $('#mytable thead').append(url);
}

function loopForAppendTBodyTable(id, obj, isFind) {
    /*
   id= 1 : firm
   id= 2 : Origin
   id= 3 : Model
   */
    switch (id) {
        case 1:
            if (isFind === false) {
                obj = obj.firms;
            }
            $.each(obj, function (key, value) {
                $('#mytable tbody').append('<tr>' +
                    '<td><a href="#">#' + value.id + '</a></td>' +
                    '<td>' + value.name + '</td>' +
                    '<td>' +
                    '<button class="btn btn-danger" data-title="Delete"' +
                    'data-toggle="modal" data-target="#delete" onclick="deleteOnClick(' + id + ',' + value.id + ')">' +
                    '<span class="icon text-white-50"><i class="material-icons">delete</i>' +
                    '</span></button></p></td>' +
                    '</tr>'
                )
            })
            break;
        case 2:
            if (isFind === false) {
                obj = obj.origins;
            }
            $.each(obj, function (key, value) {
                $('#mytable tbody').append('<tr>' +
                    '<td><a href="#">#' + value.id + '</a></td>' +
                    '<td>' + value.name + '</td>' +
                    '<td>' +
                    '<button class="btn btn-danger" data-title="Delete"' +
                    'data-toggle="modal" data-target="#delete" onclick="deleteOnClick(' + id + ',' + value.id + ')">' +
                    '<span class="icon text-white-50"><i class="material-icons">delete</i>' +
                    '</span></button></p></td>' +
                    '</tr>'
                )
            })
            break;
        case 3:
            if (isFind === false) {
                obj = obj.models;
            }
            $.each(obj, function (key, value) {
                $('#mytable tbody').append('<tr>' +
                    '<td><a href="#">#' + value.id + '</a></td>' +
                    '<td>' + value.name + '</td>' +
                    '<td>' +
                    '<button class="btn btn-danger" data-title="Delete"' +
                    'data-toggle="modal" data-target="#delete" onclick="deleteOnClick(' + id + ',' + value.id + ')">' +
                    '<span class="icon text-white-50"><i class="material-icons">delete</i>' +
                    '</span></button></p></td>' +
                    '</tr>'
                )
            })
            break;
        default:
            if (isFind === false) {
                obj = obj.firms;
            }
            $.each(obj, function (key, value) {
                $('#mytable tbody').append('<tr>' +
                    '<td><a href="#">#' + value.id + '</a></td>' +
                    '<td>' + value.name + '</td>' +
                    '<td>' +
                    '<button class="btn btn-danger" data-title="Delete"' +
                    'data-toggle="modal" data-target="#delete" onclick="deleteOnClick(' + id + ',' + value.id + ')">' +
                    '<span class="icon text-white-50"><i class="material-icons">delete</i>' +
                    '</span></button></p></td>' +
                    '</tr>'
                )
            })
            break;
    }
}

function appendAddArea(id) {
    /*
   id= 1 : firm
   id= 2 : Origin
   id= 3 : Model
   */
    switch (id) {
        case 1:
            $('#addArea').append(
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal"' +
                'aria-hidden="true">' +
                '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                '</button>' +
                '<h4 class="modal-title custom_align" id="Heading">Add' +
                ' Firm</h4>' +
                '</div >' +
                '<div class="modal-body">' +
                '<div class="form-group">' +
                'Name : <input class="form-control " type="text"' +
                'id="addName">' +
                '</div>' +
                '</div>' +
                '<div class="modal-footer ">' +
                '<button type="button" class="btn btn-warning btn-lg"' +
                'style="width: 100%;" onclick="submitSave(1)">' +
                '<span class="glyphicon glyphicon-ok-sign"></span>Save' +
                '</button>' +
                '</div>')
            break;
        case 2:
            $('#addArea').append(
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal"' +
                'aria-hidden="true">' +
                '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                '</button>' +
                '<h4 class="modal-title custom_align" id="Heading">Add' +
                ' Origin</h4>' +
                '</div >' +
                '<div class="modal-body">' +
                '<div class="form-group">' +
                'Name : <input class="form-control " type="text"' +
                'id="addName">' +
                '</div>' +
                '</div>' +
                '<div class="modal-footer ">' +
                '<button type="button" class="btn btn-warning btn-lg"' +
                'style="width: 100%;" onclick="submitSave(2)">' +
                '<span class="glyphicon glyphicon-ok-sign"></span>Save' +
                '</button>' +
                '</div>')
            break;
        case 3:
            $('#addArea').append(
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal"' +
                'aria-hidden="true">' +
                '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                '</button>' +
                '<h4 class="modal-title custom_align" id="Heading">Add' +
                ' Model</h4>' +
                '</div >' +
                '<div class="modal-body">' +
                '<div class="form-group">' +
                'Name : <input class="form-control " type="text"' +
                'id="addName">' +
                '</div>' +
                '</div>' +
                '<div class="modal-footer ">' +
                '<button type="button" class="btn btn-warning btn-lg"' +
                'style="width: 100%;" onclick="submitSave(3)">' +
                '<span class="glyphicon glyphicon-ok-sign"></span>Save' +
                '</button>' +
                '</div>')
            break;
        default:
            $('#addArea').append(
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal"' +
                'aria-hidden="true">' +
                '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                '</button>' +
                '<h4 class="modal-title custom_align" id="Heading">Add' +
                ' Firm</h4>' +
                '</div >' +
                '<div class="modal-body">' +
                '<div class="form-group">' +
                'Name : <input class="form-control " type="text"' +
                'id="addName">' +
                '</div>' +
                '</div>' +
                '<div class="modal-footer ">' +
                '<button type="button" class="btn btn-warning btn-lg"' +
                'style="width: 100%;" onclick="submitSave(1)">' +
                '<span class="glyphicon glyphicon-ok-sign"></span>Save' +
                '</button>' +
                '</div>')
            break;
    }
}

function chooseDataToPostAjax(id) {
    var data = {};
    switch (id) {
        case 1:
            data.name = $('#addName').val();
            break;
        case 2:
            data.name = $('#addName').val();
            break;
        case 3:
            data.name = $('#addName').val();
            break;
        default:
            data.name = $('#addName').val();
            break;
    }
    return data;
}

function submitSave(id) {
    var data = chooseDataToPostAjax(id);
    console.log(data);
    var url = chooseUrlToGetAPI(id);
    var xhttp = new XMLHttpRequest();
    var json = JSON.stringify(data);
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader('Content-type',
        'application/json; charset=utf-8');
    xhttp.onload = function () {
        if (xhttp.readyState == 4 && xhttp.status == "200") {
            location.reload();
        } else {
            console.log("Add error");
            alert("Add Error");
        }
    }
    xhttp.send(json);
}


function deleteOnClick(id, valueId) {
    idFunct = id;
    idValueFunct = valueId;
}

function deleteById() {
    var id = idFunct;
    var valueId = idValueFunct;
    var xhttp = new XMLHttpRequest();
    var url = chooseUrlToGetAPI(id) + "/" + valueId;
    //console.log(url);
    xhttp.open("DELETE", url, true);
    xhttp.onload = function () {
        if (xhttp.readyState == 4 && xhttp.status == "200") {
            console.log("delete ok");
            location.reload();
        } else {
            console.log("delete error");
            alert("delete Error");
        }
    }
    xhttp.send();
}

function deleteAll() {
    var id = idFunct;
    var url = chooseUrlToGetAPI(id);
    //console.log(url);
    xhttp.open("DELETE", url, true);
    xhttp.onload = function () {
        if (xhttp.readyState == 4 && xhttp.status == "200") {
            console.log("delete ok");
            location.reload();
        } else {
            console.log("delete error");
            alert("delete Error");
        }
    }
    xhttp.send();
}

$('#mySearch').on('keyup', delay(function () {
    $('#mytable thead').empty();
    $('#mytable tbody').empty();
    $('#addArea').empty();
    var id = idFunct;
    var keyword = document.getElementById('mySearch').value;
    if (keyword != "") {
        var url = chooseUrlToGetAPI(id) + "/find/" + keyword;
        console.log(url);
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var obj = JSON.parse(this.responseText);
                chooseTableTHeadAppend(id);
                loopForAppendTBodyTable(id, obj, true);
            }
        };
        xhttp.open("GET", url, true);
        xhttp.send();
    } else {
        loadWithAPI(id);
    } 
    },500));
        
function delay(callback, ms) {
    var timer = 0;
    return function () {
        var context = this, args = arguments;
        clearTimeout(timer);
        timer = setTimeout(function () {
            callback.apply(context, args);
        }, ms || 0);
    };
}
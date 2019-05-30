
window.onload = function () {
    loadOrderWithAPI();
}

function pagination(){
    var url = "/rest/order/count";
    var xhttp = new XMLHttpRequest();
    $('#pagination ul').empty();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var obj = JSON.parse(this.responseText);
            var elementSize = document.getElementById('chooseSize');
            var size = elementSize.options[elementSize.selectedIndex].value;
            var countNumberPage = obj/size; 
            countNumberPage = Math.round(countNumberPage);
            var soDu = obj%size;
            if(soDu<5){
                countNumberPage++;
            }
            console.log(countNumberPage);
            for(var i=0;i<countNumberPage;i++){
                $('#pagination ul').append('<li class="page-item"><a class="page-link" href="#" onclick="return chooseURLForListOrderPagination('+i+')">'+(i+1)+'</a></li>');
            }
        }
    }
    xhttp.open("GET", url, true);
    xhttp.send();
}

function loadOrderWithAPI(idStatus,isPagination) {
    var url = "/rest/order";
    if(idStatus!=null && isPagination==false){
        if(url.indexOf('?')==-1){
            url+="?orderStatus="+idStatus;
        }else{
            url+="&orderStatus="+idStatus;
        }   
    }
    if(idStatus!=null && isPagination==true){
        if(url.indexOf('?')==-1){
            url+="?pageId="+idStatus;
        }else{
            url+="&pageId="+idStatus;
        }  
    }
    url = chooseURLForListOrder(url);
    console.log(url);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var obj = JSON.parse(this.responseText);
            console.log(obj);
            $.each(obj, function (key, value) {
                $('#mytable tbody').append('<tr>' +
                    '<td><a href="#">#' + value.id + '</a></td>' +
                    '<td>' + formatNumber(value.price) + ' đồng</td>' +
                    '<td>' + chooseLabelForStatus(value.orderStatusO.orderStatus) + '</td>' +
                    '<td>' + getDateTimeWithRegularEx(value.createAt) + '</td>' +
                    '<td>' + value.paymentO.name + '</td>' +
                    '<td><a data-title="viewProduct" data-toggle="modal" data-target="#viewProduct" href="#" onclick="return viewProduct(' + value.id + ',\'' + value.orderStatusO.orderStatus + '\')">View</a></td>' +
                    '</tr>')
            })

        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function loadOrderProductWithAPI(id, status) {
    var url = "/rest/order/detail/" + id;
    var xhttp = new XMLHttpRequest();
    $('#orderDetailBody').empty();
    $('#orderDetailFooter').empty();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var obj = JSON.parse(this.responseText);
            var products = obj.products;
            var customer = obj.customer;
            var productAppend;
            var customerAppend;
            $.each(products, function (key, value) {
                var product = value.productO;
                productAppend +=
                    '<p><b>ID:</b><a href="#">#' + product.id + '</a></p>' +
                    '<p><b>CODE: </b>' + product.codeName + '</p>' +
                    '<p><b>FIRM: </b>' + product.firm.name + '</p>' +
                    '<p><b>AMOUNT: </b>' + value.amount + '</p>' +
                    '<p><b>TOTAL PRICE: </b>' + formatNumber(product.price * value.amount) + ' đồng</p>' +
                    '<img alt="Product Image" src="' + product.image + '" width="100" height="100"></img>' +
                    '<hr>'
            })
            customerAppend = '<p><b>ID: </b><a href="#">#' + customer.id + '</a></p>' +
                '<p><b>NAME: </b>' + customer.name + '</p>' +
                '<p><b>PHONE: </b>' + customer.phone + '</p>' +
                '<p><b>ADDRESS: </b>' + customer.address + '</p>';
            $('#orderDetailBody').append(
                '<h3 class="model-title text-center">Customer Information</h3>' +
                customerAppend +
                '<hr>' +
                '<h3 class="model-title text-center">Product Information</h3>' +
                productAppend)
            $('#orderDetailFooter').append(
                '<button type="button" class="btn btn-warning btn-lg"' +
                'style="width: 100%;" onclick="confirmedOrder(' + id + ',' + true + ')">' +
                '<span class="glyphicon glyphicon-ok-sign"></span>' + chooseeNameButtonUpdateStatus(status) +
                '</button>'
            )
            if (status === "confirmed") {
                $('#orderDetailFooter').append(
                    '<button type="button" class="btn btn-danger btn-lg"' +
                    'style="width: 100%;" onclick="confirmedOrder(' + id + ',' + false + ')">' +
                    '<span class="glyphicon glyphicon-ok-sign"></span>Unpaid' +
                    '</button>'
                )
            }

        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function formatNumber(num) {
    //using angular ex
    return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
}

function chooseLabelForStatus(status) {
    switch (status) {
        case 'unconfirmed':
            return '<span class="label warning">Unconfirmed</span>';
            break;
        case 'confirmed':
            return '<span class="label info">Confirmed</span>';
            break;
        case 'paid':
            return '<span class="label success">Paid</span>';
            break;
        case 'unpaid':
            return '<span class="label danger">Unpaid</span>';
            break;
        default:
            break;
    }
}
function getDateTimeWithRegularEx(dateTimeFromAPI) {
    var regDate = /\d{4}-.*?(?=T\d\d:\d\d:\d\d)/;
    var regTime = /(?<=\d{4}-\d\d-\d\dT)\d\d:\d\d:\d\d/;
    var date = regDate.exec(dateTimeFromAPI)[0];
    var time = regTime.exec(dateTimeFromAPI)[0];
    return date + ' ' + time;
}

function viewProduct(id, status) {
    loadOrderProductWithAPI(id, status);
}

function confirmedOrder(id, isSuccess) {
    if (confirm('Are you sure you want to update order status?')) {
        var url = "/rest/order/" + id + "/" + isSuccess;
        var xhttp = new XMLHttpRequest();
        xhttp.open("PUT", url, true);
        xhttp.onload = function () {
            if (xhttp.readyState == 4 && xhttp.status == "200") {
                location.reload();
            }
        }
        xhttp.send();
    } else {
        //do nothing
    }
}

function chooseeNameButtonUpdateStatus(status) {
    switch (status) {
        case 'unconfirmed':
            return 'Confirmed';
        case 'confirmed':
            return 'Paid';
        default:
            return status;
    }
}
function chooseURLForListOrder(url) {
    var elementCreate = document.getElementById('selectCreate');
    var elementPrice = document.getElementById('selectPrice');
    var elementPayment = document.getElementById('selectPayment');
    var elementSize = document.getElementById('chooseSize');
    var selectCreate = elementCreate.options[elementCreate.selectedIndex].value;
    var selectPrice = elementPrice.options[elementPrice.selectedIndex].value;
    var selectPayment = elementPayment.options[elementPayment.selectedIndex].value;
    var selectSize = elementSize.options[elementSize.selectedIndex].value;
    if (selectCreate != 0) {
        if(url.indexOf('?')==-1){
            url+= "?orderCreateStatus="+selectCreate;
        }else{
            url+= "&orderCreateStatus="+selectCreate;
        }
    }
    if (selectPrice != 0) {
        if(url.indexOf('?')==-1){
            url+= "?orderPriceStatus="+selectPrice;  
        }else{
            url+= "&orderPriceStatus="+selectPrice;  
        }
    }
    if (selectPayment != 0) {
        if(url.indexOf('?')==-1){
            url+= "?orderPaymentStatus="+selectPayment;
        }else{
            url+= "&orderPaymentStatus="+selectPayment;
        }
    }
    if(selectSize!=0){
        pagination();
        if(url.indexOf('?')==-1){
            url+= "?size="+selectSize;
        }else{
            url+= "&size="+selectSize;
        }
    }
    return url;
}

// function chooseURLForListOrderPagination(pageId){
//     isURLHasParamSpecify("pageId",pageId);
// }

// function isURLHasParam(url){
//     if(url.indexOf('?')==-1){
//         //url chua co tham so 
//         return false;
//     }else{
//         return true;
//     }
// }
// function isURLHasParamSpecify(param,idParam){
//     var key = null;
//     if(window.location.href.indexOf('?orderStatus=')==-1){
//         key = "?";
//         if(window.location.href.indexOf(param)==-1){
//             //url chua co param nay
//             window.location.href=key+param+"="+idParam;
//         }else{
//             var valueOfParam = getUrlParameter(param);
//             console.log(valueOfParam);
//             console.log(String(idParam));
//             window.location.href = window.location.href.replace(param+"="+valueOfParam,param+"="+String(idParam));
//         }
//     }else{
//         key ="&";
//         if(window.location.href.indexOf(param)==-1){
//             //url chua co param nay
//             window.location.href+=key+param+"="+idParam;
//         }else{
//             var valueOfParam = getUrlParameter(param);
//             console.log(valueOfParam);
//             console.log(String(idParam));
//             window.location.href = window.location.href.replace(param+"="+valueOfParam,param+"="+String(idParam));
//         }
//     }
// }

function isRestURLHasParamSpecify(url,param,idParam){
    var key = null;
    if(url.indexOf('?')==-1){
        //url chua co tham so 
        key="?"; 
    }else{
        key="&";
    }
    if(url.indexOf(param)==-1){
        //url chua co param nay
        url+=key+param+"="+idParam;
    }else{
        var valueOfParam = getUrlParameter(param);
        console.log(valueOfParam);
        console.log(String(idParam));
        url = url.replace(param+"="+valueOfParam,param+"="+String(idParam));
    }
    return url;
}

function chooseURLForListOrderPagination(idStatus){
    $('#mytable tbody').empty();
    setTimeout(function () {
        loadOrderWithAPI(idStatus,true);
    }, 100);
    //Sleep 0.1s Vì nhanh quá khung hình sẽ giựt do bất đồng bộ 
}

function fliterOrderStatus(idStatus){
    $('#mytable tbody').empty();
        setTimeout(function () {
            loadOrderWithAPI(idStatus,false);
        }, 100);
        //Sleep 0.1s Vì nhanh quá khung hình sẽ giựt do bất đồng bộ 
}

$(document).ready(function () {
    $('select').on('change', function () {
        $('#mytable tbody').empty();
        setTimeout(function () {
            loadOrderWithAPI();
        }, 100);
        //Sleep 0.1s Vì nhanh quá khung hình sẽ giựt do bất đồng bộ 
    });
});
//stackoverflow
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};
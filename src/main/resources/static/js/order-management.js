window.onload = function (){
    loadOrderWithAPI();
}

function loadOrderWithAPI(){
    var url = "/rest/order";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var obj = JSON.parse(this.responseText);
            console.log(obj);
            $.each(obj,function(key,value){
               $('#mytable tbody').append('<tr>'+
                '<td><a href="#">#'+value.id+'</a></td>'+
                '<td>'+formatNumber(value.price)+' đồng</td>'+
                '<td>'+chooseLabelForStatus(value.orderStatusO.orderStatus)+'</td>'+
                '<td>'+getDateTimeWithRegularEx(value.createAt)+'</td>'+
                '<td>'+value.paymentO.name+'</td>'+
                '<td><a href="#" onclick="return viewProduct('+value.id+')">View</a></td>'+
              '</tr>')
            })
            
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function loadOrderProductWithAPI(){
    var url = "/rest/order/detail/3";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var obj = JSON.parse(this.responseText);
            console.log(obj);
            $.each(obj,function(key,value){
                var product = value.productO;
                console.log(product.codeName);
            })
            
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function formatNumber(num) {
    //using angular ex
    return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
  }

  function chooseLabelForStatus(status){
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
  function getDateTimeWithRegularEx(dateTimeFromAPI){
      var regDate = /\d{4}-.*?(?=T\d\d:\d\d:\d\d)/;
      var regTime = /(?<=\d{4}-\d\d-\d\dT)\d\d:\d\d:\d\d/;
      var date = regDate.exec(dateTimeFromAPI)[0];
      var time = regTime.exec(dateTimeFromAPI)[0];
      return date+' '+time;
  }

  function viewProduct(id){
    loadOrderProductWithAPI();
  }
  
  
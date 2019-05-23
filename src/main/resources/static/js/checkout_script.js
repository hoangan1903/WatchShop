$(document).ready(function () {
    // Get customer's basic info
    valib.ajaxGET('/rest/me', function (obj) {
        console.log(obj);

        var name = obj.name || 'Unknown',
            phone = obj.phone || 'Unknown',
            address = obj.address || 'Unknown';

        $('.card-entry.customer-name p').text(name);
        $('.card-entry.customer-phone p').text(phone);
        $('.card-entry.customer-address p').text(address);
    });

    valib.ajaxGET('/rest/cart', function (obj) {
    });

});
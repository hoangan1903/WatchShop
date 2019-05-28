$(document).ready(function () {
    var inputName = $('#inputCustomerName'),
        inputPhone = $('#inputCustomerPhone'),
        inputAddress = $('#inputCustomerAddress'),
        invalidInfoAlert = $('#invalidInfoAlert');

    function getCustomerInfo() {
        valib.ajaxGET('/rest/me', function (obj) {
            var name = obj.name || '&nbsp;',
                phone = obj.phone || '&nbsp;',
                address = obj.address || '&nbsp;';

            $('.card-entry.customer-name p').html(name);
            $('.card-entry.customer-phone p').html(phone);
            $('.card-entry.customer-address p').html(address);
        });
    }

    function setClickListeners() {

        // Set click listener for "Edit Customer Info"
        $('#openInfoEdit').click(function () {
            valib.ajaxGET('/rest/me', function (obj) {
                var name = obj.name || '',
                    phone = obj.phone || '',
                    address = obj.address || '';

                inputName.val(name);
                inputPhone.val(phone);
                inputAddress.val(address);
            });

            $("#editCustomerInfoModal").modal("toggle");

            return false;
        });

        // Set click listener for "Save Info"
        $('#saveCustomerInfo').click(function () {
            var name = inputName.val(),
                phone = inputPhone.val(),
                address = inputAddress.val();

            if (name && phone && address) {
                invalidInfoAlert.addClass('hidden');

                valib.ajaxPUT({
                    url: '/rest/customers/update',
                    data: {
                        name: name,
                        phone: phone,
                        address: address
                    },
                    onSuccess: function (response) {
                        var successful = Boolean(parseInt(response));
                        if (successful) {
                            getCustomerInfo();
                        } else {
                            console.log("Customer's info not successfully updated");
                        }
                        $("#editCustomerInfoModal").modal("toggle");
                    }
                });

            } else {
                invalidInfoAlert.removeClass('hidden');
            }
        });
    }

    getCustomerInfo();
    setClickListeners();

    // setInterval(checkLogin, LOGIN_CHECK_INTERVAL);
});
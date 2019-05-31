$(document).ready(function () {
    var inputName = $('#inputCustomerName'),
        inputPhone = $('#inputCustomerPhone'),
        inputAddress = $('#inputCustomerAddress'),
        invalidInfoAlert = $('#invalidInfoAlert');

    const orderStatus = {
        UNCONFIRMED: '#ff9800',
        CONFIRMED: '#2196f3',
        PAID: '#4caf50',
        UNPAID: '#f44336'
    };

    function getTdStatus(status) {
        var hex = '#212529';
        switch (status) {
            case 'unconfirmed':
                hex = orderStatus.UNCONFIRMED;
                break;
            case 'confirmed':
                hex = orderStatus.CONFIRMED;
                break;
            case 'paid':
                hex = orderStatus.PAID;
                break;
            case 'unpaid':
                hex = orderStatus.UNPAID;
                break;
        }
        return `<td style="color: ${hex};">${status}</td>`;
    }

    function showNotification(message) {
        $('#accountNotification p').html(message);
        $('#accountNotification').show();
    }

    function showAlert(message) {
        $('#accountAlert p').html(message);
        $('#accountAlert').show();
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

            $('#editCustomerInfoModal').modal('toggle');

            return false;
        });

        // Set click listener for "Save Info"
        $('#saveCustomerInfo').click(function () {
            // Dismiss the modal
            $('#editCustomerInfoModal').modal('toggle');

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
                            showNotification('Chỉnh sửa thông tin thành công');
                        } else {
                            console.log("Customer's info not successfully updated");
                            showAlert('Chỉnh sửa thông tin không thành công. Có vấn đề xảy ra khi lưu thông tin chỉnh sửa của bạn');
                        }
                    }
                });

            } else {
                invalidInfoAlert.removeClass('hidden');
            }
        });
    }

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

    function getOrders() {
        valib.ajaxGET('/rest/customers/orders', function (obj) {
            console.log(obj);

            var html = '';
            obj.forEach((order, index) => {
                var number = index + 1,
                    price = order.price.toLocaleString() + 'đ',
                    status = order.orderStatusO.orderStatus,
                    payment = order.paymentO.name,
                    dateTime = valib.formatDateTime(order.createAt);

                html += `
                <tr>
                    <th scope="row">${number}</th>
                    <td>${price}</td>
                    `
                    + getTdStatus(status) +
                    `
                    <td>${payment}</td>
                    <td>${dateTime}</td>
                </tr>
                `;
            });
            $('#customerOrderTable tbody').html(html);
        });
    }

    setClickListeners();
    getCustomerInfo();
    getOrders();

    setInterval(checkLogin, LOGIN_CHECK_INTERVAL);
});
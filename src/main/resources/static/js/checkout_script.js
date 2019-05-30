$(document).ready(function () {
    var placeOrderBtn = $('button#place-order'),
        itemContainer = $('.order-item-container');

    const PLACE_ORDER_FAILED =
        "Có vấn đề khi tiến đặt hàng. Vui lòng kiểm tra lại thông tin của bạn và sản phẩm.";

    function showAlert(message) {
        $('#checkoutAlert p').html(message);
        $('#checkoutAlert').show();
    }

    function showDialog(message) {
        $('#checkoutAlertModal .modal-body').html(message);
        $('#checkoutAlertModal').modal('toggle');
    }

    function setClickListeners() {
        placeOrderBtn.click(() => $('#placeOrderConfirmModal').modal('toggle'));

        $('#placeOrderConfirm').click(function () {
            // Dismiss the modal
            $('#placeOrderConfirmModal').modal('toggle');

            // Get payment method from input
            const paymentMethodId = $('#paymentMethods input:checked').val();

            // Send order to server
            valib.ajaxPOST({
                url: '/rest/order/' + paymentMethodId,
                data: {},
                onSuccess: function (response) {
                    var successful = Boolean(parseInt(response));
                    if (successful) {
                        // Back to homepage or go to Order Success page if order is successful
                        window.location.href = '/';
                    } else {
                        // Notify user that their order was not successfully placed
                        console.log('Order not successful');
                        showAlert(PLACE_ORDER_FAILED);
                        showDialog(PLACE_ORDER_FAILED);
                    }
                }
            });
        });
    }

    function getData() {
        // Get customer's basic info
        valib.ajaxGET('/rest/me', function (obj) {
            var name = obj.name || 'Unknown',
                phone = obj.phone || 'Unknown',
                address = obj.address || 'Unknown';

            $('.card-entry.customer-name p').text(name);
            $('.card-entry.customer-phone p').text(phone);
            $('.card-entry.customer-address p').text(address);
        });


        // Get customer's order
        valib.ajaxGET('/rest/cart', function (obj) {
            let items = obj.cart || [],
                total = obj.totals || 0;

            const cartIsEmpty = (items.length == 0);

            // Show total prices
            $('p#order-subtotal').text(total.toLocaleString() + 'đ');
            $('p#order-total').text(total.toLocaleString() + 'đ');

            if (!cartIsEmpty) {
                // Show order items
                let html = '';
                items.forEach((item, index) => {
                    const product = item.product;

                    html += `
                    <div id="${product.id}-order-item" class="order-item d-flex flex-row">
                        <div class="d-flex align-items-start mr-3" style="width: 30%;">
                            <img src="${product.image}" class="order-item-img" alt="...">
                        </div>

                        <div class="flex-grow-1 d-flex flex-column">
                            <div class="item-details flex-grow-1 d-flex flex-column">
                                <div class="mb-1">
                                    <a href="/product-details?id=${product.id}" class="order-product-link">${product.firm.name} ${product.codeName}</a>
                                </div>
                                <p class="card-text price-small mb-1">${product.price.toLocaleString()}đ</p>
                                <div>
                                    <p class="card-text" style="display: inline-block;">Số lượng: </p>
                                    <p class="card-text bold" style="display: inline-block;">${valib.toString(item.amount)}</p>
                                </div>
                                <div>
                                    <p class="card-text" style="display: inline-block;">Còn lại: </p>
                                    <p class="card-text bold" style="display: inline-block;">${valib.toString(product.available)}</p>
                                </div>
                            </div>

                            <div class="item-price d-flex justify-content-end mt-3">
                                <p class="card-text price-secondary-small">${item.subtotal.toLocaleString()}đ</p>
                            </div>
                        </div>
                    </div>
                    `;

                    // Add a divider after each order item except for the last one
                    if (index < items.length - 1) {
                        html +=
                            '<div class="item-divider d-flex flex-column"><div class="pb-3 card-item-divider"></div><div class="pt-3"></div></div>';
                    }
                });

                itemContainer.html(html);

                // Enable Place Order button
                placeOrderBtn.removeAttr('disabled');

            } else {

                itemContainer.empty();

                // Disable Place Order button
                placeOrderBtn.attr('disabled', 'disabled');
            }
        });
    }

    setClickListeners();
    getData();

    setInterval(checkLogin, LOGIN_CHECK_INTERVAL);
});
$(document).ready(function () {
    var placeOrderBtn = $('button#place-order'),
        itemContainer = $('.order-item-container');

    function checkLogin() {
        valib.ajaxGET('/rest/users/isLoggedIn', function (obj) {
            var isLoggedIn = Boolean(parseInt(obj));
            if (!isLoggedIn) {
                // Redirect to Login page
                window.location.href = 'login';
            }
        });
    }

    function setClickListeners() {
        placeOrderBtn.click(function () {
            // Make data
            paymentMethodId = $('#paymentMethods input:checked').val();

            // Send order to server
            valib.ajaxGET('/rest/order/' + paymentMethodId, function (response) {
                var successful = Boolean(parseInt(response));
                if (successful) {
                    // Back to homepage if order is successful
                    window.location.href = '/';
                } else {
                    // Notify user that their order was not successfully placed
                    console.log('Order not successful');
                }
            });
        });
    }

    function getPageData() {
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
                total = obj.total || 0;

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
                        <div class="item-image d-flex align-items-start mr-3" style="width: 30%;">
                            <img src="${product.image}" style="width: 100%;"
                                alt="...">
                        </div>

                        <div class="flex-grow-1 d-flex flex-column">
                            <div class="item-details flex-grow-1 d-flex flex-column">
                                <div class="mb-1">
                                    <a href="product-details?id=${product.id}" class="order-product-link">${product.firm.name} ${product.codeName}</a>
                                </div>
                                <p class="card-text price-small mb-1">${product.price.toLocaleString()}đ</p>
                                <div>
                                    <p class="card-text" style="display: inline-block;">Số lượng: </p>
                                    <p class="card-text bold" style="display: inline-block;">${valib.toString(item.amount)}</p>
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

    checkLogin();
    setClickListeners();
    getPageData();
});
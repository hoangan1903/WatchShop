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

    // Get customer's order
    valib.ajaxGET('/rest/cart', function (obj) {
        let items = obj.cart || [],
            count = obj.totalAmount || 0,
            total = obj.total || 0;

        // Show total prices
        $('p#order-subtotal').text(total.toLocaleString() + 'đ');
        $('p#order-total').text(total.toLocaleString() + 'đ');

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
            if (index < count - 1) {
                html +=
                    '<div class="item-divider d-flex flex-column"><div class="pb-3 card-item-divider"></div><div class="pt-3"></div></div>';
            }
        });

        $('.order-item-container').html(html);
    });

    $('a#place-order').click(function () {
        // Check order validity, e.g Customers who have a pending order cannot place another one

        // Make data

        // Send order to server
        valib.ajaxPOST({
            url: '/',
            data: {},
            onSuccess: function (response) {
                var successful = Boolean(parseInt(response));
                if (successful) {
                    
                }
            }
        });
    });
});
$(document).ready(function () {

    const DELAY_AFTER_TASK = 400;

    let goToCheckout = $('a#go-to-checkout'),
        removeAll = $('a.cart-remove-all'),
        itemContainer = $('.cart-item-container'),
        loadingScreen = $('.screen-cover');

    function showLoadingScreen() {
        loadingScreen.removeClass('hidden');
    }

    function hideLoadingScreen(delay) {
        delay = delay || DELAY_AFTER_TASK;
        setTimeout(() => loadingScreen.addClass('hidden'), delay);
    }

    // Get user's cart from server
    function fetchCart() {
        valib.ajaxGET('/rest/cart', function (obj) {
            let items = obj.cart || [],
                count = obj.totalAmount || 0,
                total = obj.total || 0;

            const cartIsEmpty = (count == 0);

            // Show total products in cart
            $('#cart-count-badge').text(count);
            $('#cart-count').text(count);

            // Show total prices
            $('p#cart-subtotal').text(total.toLocaleString() + 'đ');
            $('p#cart-total').text(total.toLocaleString() + 'đ');

            if (!cartIsEmpty) {
                // Show cart items
                let html = '';
                items.forEach(item => {
                    const product = item.product;

                    html += `
                    <div id="${product.id}-cart-item" class="cart-item card mb-3" style="width: auto;">
                        <div class="card-body">
                            <div class="cart-item-content d-flex flex-row">
                                <div class="cart-item-summary flex-grow-1">
                                    <div class="mb-2">
                                        <a href="product-details?id=${product.id}" class="cart-product-link">${product.firm.name} ${product.codeName}</a>
                                    </div>
                                    <p class="card-text">Giá</p>
                                    <p class="card-text price">${product.price.toLocaleString()}đ</p>
                                    <div class="full-height pt-4">
                                        <img class="cart-item-img" src="${product.image}" alt="...">
                                    </div>
                                </div>

                                <div class="cart-item-control d-flex flex-column ml-4">
                                    <div class="quantity-control d-flex justify-content-end">
                                        <div class="text-align-center">
                                            <p class="card-text mb-2">Số lượng</p>
                                            <div class="btn-group" role="group" aria-label="Second group">
                                                <button type="button" class="decrease-qty btn btn-secondary">&ndash;</button>
                                                <div class="product-quantity d-flex align-items-center px-3">${valib.toString(item.amount)}</div>
                                                <button type="button" class="increase-qty btn btn-secondary">&plus;</button>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item-total-price flex-grow-1 d-flex justify-content-end align-items-end py-4">
                                        <div class="text-align-center">
                                            <p class="card-text mb-1">Thành tiền</p>
                                            <p class="card-text price">${item.subtotal.toLocaleString()}đ</p>
                                        </div>
                                    </div>

                                    <div class="card-item-actions d-flex justify-content-end">
                                        <a href="#" class="card-item-action" onclick="return false;">
                                            <ion-icon name="ios-heart"
                                                class="cart-action-icon wishlist-icon small-icon">
                                            </ion-icon>
                                        </a>
                                        <a href="#" class="card-item-action remove-cart-item" onclick="return false;">
                                            <ion-icon name="ios-trash"
                                                class="cart-action-icon remove-icon small-icon">
                                            </ion-icon>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    `;
                });

                itemContainer.html(html);

                // Enable Checkout button
                goToCheckout.removeClass('disabled');

            } else {

                itemContainer.empty();

                // Disable Checkout button
                goToCheckout.addClass('disabled');
            }
        });
    }

    // Execution starts here

    fetchCart();

    // Set click listener for "Remove all" button
    removeAll.click(function () {
        valib.ajaxGET('/rest/cart', function (obj) {
            const cartIsEmpty = (obj.cart.length == 0);

            if (!cartIsEmpty) {
                showLoadingScreen();

                valib.ajaxDELETE({
                    url: '/rest/cart/all',
                    onSuccess: function (response) {
                        var successful = Boolean(parseInt(response));
                        if (!successful) {
                            console.log('Remove all products unsuccessfully');
                        }

                        // Refresh cart to see changes
                        fetchCart();
                        hideLoadingScreen(500);
                    }
                });
            }
        });
        return false;
    });

    // Handle: clicking on buttons inside a cart item
    itemContainer.click(function (e) {
        let clicked, domId, productId, currentQty;

        clicked = $(e.target);
        domId = clicked.closest('.cart-item').attr('id');
        productId = parseInt(domId.split('-')[0]);
        currentQty = parseInt($(e.target.parentNode).find('.product-quantity').text());

        if (clicked.hasClass('increase-qty')) {
            showLoadingScreen();
            valib.ajaxPUT({
                url: '/rest/cart/up',
                data: {
                    idProduct: productId,
                    amount: 1
                },
                onSuccess: function (response) {
                    var successful = Boolean(parseInt(response));
                    if (!successful) {
                        console.log('Increase quantity unsuccessfully');
                    }

                    // Refresh cart to see changes
                    fetchCart();
                    hideLoadingScreen(null);
                }
            });

        } else if (clicked.hasClass('decrease-qty') && currentQty > 1) {
            showLoadingScreen();
            valib.ajaxPUT({
                url: '/rest/cart/down',
                data: {
                    idProduct: productId,
                    amount: 1
                },
                onSuccess: function (response) {
                    var successful = Boolean(parseInt(response));
                    if (!successful) {
                        console.log('Decrease quantity unsuccessfully');
                    }

                    // Refresh cart to see changes
                    fetchCart();
                    hideLoadingScreen(null);
                }
            });

        } else if (clicked.parent().hasClass('remove-cart-item')) {
            showLoadingScreen();
            valib.ajaxDELETE({
                url: '/rest/cart/product/' + productId,
                onSuccess: function (response) {
                    var successful = Boolean(parseInt(response));
                    if (!successful) {
                        console.log('Remove product unsuccessfully');
                    }

                    // Refresh cart to see changes
                    fetchCart();
                    hideLoadingScreen(null);
                }
            });
        }
    });
});
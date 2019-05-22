$(document).ready(function () {

    function init() {

        $('a.cart-remove-all').click(function (e) {
            e.preventDefault();

            valib.ajaxDELETE('/rest/cart/all', function (obj) {
                location.reload();
            });
        });

        valib.ajaxGET('/rest/cart', function (obj) {
            console.log(obj);

            let items = obj.cart || [],
                count = obj.totalAmount,
                total = obj.total;

            // Show total products in cart
            $('#cart-count').text(count);

            // Show cart items
            if (items.length > 0) {
                let html = '';
                items.forEach(item => {
                    const product = item.product;

                    html += `
                    <div class="card mb-3" style="width: auto;">
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
                                                <button type="button" class="btn btn-secondary">&ndash;</button>
                                                <div class="product-quantity d-flex align-items-center px-3">${valib.toString(item.amount)}</div>
                                                <button type="button" class="btn btn-secondary">&plus;</button>
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
                                        <a href="#" class="card-item-action">
                                            <ion-icon name="ios-heart"
                                                class="cart-action-icon wishlist-icon small-icon">
                                            </ion-icon>
                                        </a>
                                        <a href="#" class="card-item-action">
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

                $('.cart-item-container').html(html);
            }

            // Show total prices
            $('p#cart-subtotal').eq(0).text(total.toLocaleString() + 'đ');
            $('p#cart-total').eq(0).text(total.toLocaleString() + 'đ');
        });
    }

    init();
});
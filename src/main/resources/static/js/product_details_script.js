$(document).ready(function () {

    var productCarousel = $('.product-carousel'),
        brand$ = $('a.brand-link'),
        name$ = $('h3.product-name'),
        price$ = $('p.price'),
        available$ = $('span.available'),
        quantity = $('.product-quantity'),
        quantityUp = $('button.quantity-up'),
        quantityDown = $('button.quantity-down'),
        addToCartBtn = $('button.add-to-cart'),
        table = $('table#details'),
        cartBadge = $('#cart-count-badge'),
        popover = $('a#cart');

    const id = parseInt(valib.getValueFromURL('id'));

    const POPOVER_TIMEOUT = 6500;

    var minQty = 1,
        maxQty;

    function initStickyNavbar() {
        $('.section-product-overview').waypoint({
            handler: function (direction) {
                if (direction === 'down') {
                    $('.navigation').addClass('stick');
                } else {
                    $('.navigation').removeClass('stick');
                }
            },
            offset: -1
        });
    }

    function initComponents() {
        // Initialize product carousel
        productCarousel.carousel({
            interval: 0
        });
    }

    function setClickListeners() {
        // Initialize quantity section
        // and set click handlers for buttons
        quantity.text(valib.toString(1));

        quantityUp.click(function () {
            var currentQty, newQty;

            currentQty = parseInt(quantity.text());
            newQty = currentQty + 1;

            if (newQty <= maxQty) {
                quantity.text(valib.toString(newQty));
            }
        });

        quantityDown.click(function () {
            var currentQty, newQty;

            currentQty = parseInt(quantity.text());
            newQty = currentQty - 1;

            if (newQty >= minQty) {
                quantity.text(valib.toString(newQty));
            }
        });

        // Set click listener for "Add to cart" button
        addToCartBtn.click(function () {
            valib.ajaxGET('/rest/users/isLoggedIn', function (obj) {
                var isLoggedIn = Boolean(parseInt(obj));

                if (isLoggedIn) {
                    // Prepare data for submission to server
                    var data = {
                        idProduct: id,
                        amount: parseInt(quantity.text())
                    };

                    // Submit data to server
                    valib.ajaxPOST({
                        url: '/rest/cart',
                        data: data,
                        onSuccess: function (response) {
                            var successful = Boolean(parseInt(response));
                            if (successful) {
                                // Show user that the products have been put in their cart
                                valib.ajaxGET('/rest/products/details/' + id, function (obj) {
                                    var brand = obj.product.firm.name,
                                        codeName = obj.product.codeName;

                                    popover
                                        .attr('data-content', `Sản phẩm: ${brand} ${codeName} (x${data.amount}) đã được thêm vào giỏ hàng.`)
                                        .popover('show');

                                    setTimeout(() => popover.popover('hide'), POPOVER_TIMEOUT);
                                });

                                // Update cart badge
                                valib.ajaxGET('/rest/cart', function (obj) {
                                    // Get cart count (total items)
                                    var count = obj.totalAmount;
                                    cartBadge.text(count);
                                });

                            } else {
                                popover
                                    .attr('data-content', 'Không thể thêm vào giỏ hàng vì sẽ vượt quá số lượng có sẵn của sản phẩm.')
                                    .popover('show');

                                setTimeout(() => popover.popover('hide'), POPOVER_TIMEOUT);
                            }
                        }
                    });

                } else {
                    // Redirect to Login page
                    window.location.href = 'login';
                }
            });
        });
    }

    function checkAvailability() {
        if (maxQty === 0) {
            // Disable these buttons when product is unavailable
            quantityUp.attr('disabled', 'disabled');
            quantityDown.attr('disabled', 'disabled');
            addToCartBtn.attr('disabled', 'disabled');

            // And let customer know about this
            $('p.availability-info').text('Sản phẩm chưa có sẵn hoặc đã hết hàng');
        }
    }

    function getData() {
        valib.ajaxGET('/rest/products/details/' + id, function (obj) {
            var brand = obj.product.firm.name,
                carousel = $('.product-carousel .carousel-indicators, .product-carousel .carousel-inner'),
                indicators, inner;

            indicators = carousel.eq(0);
            inner = carousel.eq(1);

            // Get images for product carousel
            carousel.empty();
            obj.images.forEach((image, index) => {
                indicators.append(`<li data-target="#productCarouselIndicators" data-slide-to="${index}"></li>`);
                inner.append(`
                    <div class="carousel-item">
                        <img src="${image.url}" class="d-block w-100" alt="">
                    </div>
                `);

                if (index === 0) {
                    carousel.children().addClass('active');
                }
            });

            // Get product info
            brand$.text(brand).attr('href', `${brand.toLowerCase()}-watches`);
            name$.text(obj.product.codeName);
            price$.text(obj.product.price.toLocaleString() + 'đ');

            maxQty = obj.product.available;
            available$.text(maxQty);
            checkAvailability();

            // Get data for table of product details
            table.html(`
            <tr>
                <td class="spec-title">Bảo hành/Bảo hiểm</td>
                <td class="spec-content">${obj.insurance}</td>
            </tr>
            <tr>
                <td class="spec-title">Đổi trả</td>
                <td class="spec-content">30 ngày</td>
            </tr>
            <tr>
                <td class="spec-title">Giao hàng</td>
                <td class="spec-content">Miễn phí toàn quốc</td>
            </tr>
            <tr>
                <td class="spec-title">Nhãn hiệu</td>
                <td class="spec-content">${brand}</td>
            </tr>
            <tr>
                <td class="spec-title">Nguồn gốc</td>
                <td class="spec-content">${obj.origin.name}</td>
            </tr>
            <tr>
                <td class="spec-title">Kiểu máy</td>
                <td class="spec-content">${obj.model.name}</td>
            </tr>
            <tr>
                <td class="spec-title">Đồng hồ dành cho</td>
                <td class="spec-content">Nam/Nữ</td>
            </tr>
            <tr>
                <td class="spec-title">Kích cỡ</td>
                <td class="spec-content">${obj.size}</td>
            </tr>
            <tr>
                <td class="spec-title">Chất liệu</td>
                <td class="spec-content">Vỏ: ${obj.caseMaterial}; Dây: ${obj.chainMaterial}; Kính: ${obj.glassMaterial}</td>
            </tr>
            <tr>
                <td class="spec-title">Khả năng chịu nước</td>
                <td class="spec-content">${obj.waterResistance}</td>
            </tr>
            `);
        });
    }

    /*
    function getComments() {
        valib.ajaxGET('/rest/comments/productDetail/' + id, function (obj) {
            if (obj.length > 0) {
                var html = '';

                // Process retrieved data into HTML

                $('.comment-container').html(html);
            } else {

            }
        });
    }

    function postComment() {
        valib.ajaxGET('/rest/users/isLoggedIn', function (obj) {
            var isLoggedIn = Boolean(parseInt(obj));
            if (isLoggedIn) {
                // 1. Make comment object from Comment Form

                // 2. Post this object to server
                valib.ajaxPOST({
                    url: '/url where you want to submit your data',
                    data: 'data you want to submit to server',
                    onSuccess: function (response) {
                        var successful = Boolean(parseInt(response));
                        if (successful) {
                            // 3. Do something when the request is successful
                            // e.g Refresh the comments to see the new comment
                            getComments();
                        }
                    }
                });
            }
        });
    }

    function initComments() {
        getComments();
        $('button.post-comment').click(postComment);
    }
    */

    initStickyNavbar();

    initComponents();
    setClickListeners();
    getData();
});
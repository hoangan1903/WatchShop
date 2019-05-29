/* Common functionality for all pages */

$(document).ready(function () {

    function loadCustomerProps() {
        valib.ajaxGET('/rest/users/isLoggedIn', function (obj) {
            const isLoggedIn = Boolean(parseInt(obj));

            var cartBadge = $('#cart-count-badge'),
                accountDropdown = $('.account-dropdown ul.dropdown-content');

            if (isLoggedIn) {
                valib.ajaxGET('/rest/cart', function (obj) {
                    // Get cart count (total items) and all products
                    var count = obj.totalAmount || 0;
                    cartBadge.text(count);
                });

                valib.ajaxGET('/rest/me', function (obj) {
                    // Get customer's name
                    var name = obj.name;

                    accountDropdown
                        .css('width', '230px')
                        .html(`
                            <li>
                                <p class="m-0 p-3">
                                    <em>Xin chào, <span class="text-wt-bold">${name}</span>!</em>
                                </p>
                            </li>
                            <li><a href="/account#personal-info">Thông tin khách hàng</a></li>
                            <li><a href="/account#orders">Đơn hàng đã đặt</a></li>
                            <li><a href="/logout">Đăng xuất</a></li>
                        `);
                });

            } else {
                cartBadge.text(null);
                accountDropdown.html(`
                    <li><a href="/login">Đăng nhập</a></li>
                    <li><a href="/register">Đăng ký</a></li>
                `);
            }
        });
    }

    function setClickListeners() {

        $('button.hamburger').click(function () {
            var icon = $(this).children();
            var iconName = icon.attr('name');

            if (iconName.includes('menu')) {
                let navHeight = $('nav.mobile-navigation').css('height');
                $('.hamburger-menu').css('top', navHeight).addClass('show');
                icon.attr('name', 'ios-close');

            } else if (iconName.includes('close')) {
                $('.hamburger-menu').removeClass('show');
                icon.attr('name', 'ios-menu');
            }
        });
    }

    function getProductClassification() {
        valib.ajaxGET('/rest/menu', function (obj) {
            var firms = obj.firms.firms,
                models = obj.models.models,
                origins = obj.origins.origins;

            var brandDropdown = $('#productsByBrandDropdown'),
                modelDropdown = $('#productsByModelDropdown'),
                originDropdown = $('#productsByOriginDropdown');

            brandDropdown.empty();
            modelDropdown.empty();
            originDropdown.empty();

            firms.forEach(item => {
                brandDropdown.append(`
                    <li class="dropdown-item">
                        <a href="/products/brand?id=${item.id}">Đồng hồ ${item.name}</a>
                    </li>
                `);
            });

            models.forEach(item => {
                modelDropdown.append(`
                    <li class="dropdown-item">
                        <a href="/products/model?id=${item.id}">${item.name}</a>
                    </li>
                `);
            });

            origins.forEach(item => {
                originDropdown.append(`
                    <li class="dropdown-item">
                        <a href="/products/origin?id=${item.id}">${item.name}</a>
                    </li>
                `);
            });
        });
    }

    loadCustomerProps();
    setClickListeners();
    getProductClassification();
});
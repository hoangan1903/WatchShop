/* Common functionality for all pages */

$(document).ready(function () {

    // Define sticky navigation bar behavior
    // when to appear and when to disappear
    function initStickyNavbar() {
        $('section').first().waypoint({
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

    function loadCustomerProps() {
        valib.ajaxGET('/rest/users/isLoggedIn', function (obj) {
            const isLoggedIn = Boolean(parseInt(obj));

            var cartBadges = $('#cart-count-badge, #cart-count-badge-m');

            // Desktop
            var accountDropdown = $('.account-dropdown ul.dropdown-content');

            // Mobile
            var hamburgerMenu = $('ul.hamburger-menu');

            if (isLoggedIn) {
                valib.ajaxGET('/rest/cart', function (obj) {
                    // Get cart count (total items) and all products
                    var count = obj.totalAmount || 0;
                    cartBadges.text(count);
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

                    hamburgerMenu.append(`
                        <li class="hamburger-item">
                            <p class="account-greetings">
                                <em>Xin chào, <span class="text-wt-bold">${name}</span>!</em>
                            </p>
                        </li>
                        <li class="hamburger-item"><a class="hamburger-link" href="/account#personal-info">Thông tin khách hàng</a>
                        <li class="hamburger-item"><a class="hamburger-link" href="/account#orders">Đơn hàng đã đặt</a>
                        <li class="hamburger-item"><a class="hamburger-link" href="/logout">Đăng xuất</a></li>
                    `);
                });

            } else {
                cartBadges.text(null);

                accountDropdown.html(`
                    <li><a href="/login">Đăng nhập</a></li>
                    <li><a href="/register">Đăng ký</a></li>
                `);

                hamburgerMenu.append(`
                    <li class="hamburger-item"><a class="hamburger-link" href="/login">Đăng nhập</a>
                    <li class="hamburger-item"><a class="hamburger-link" href="/register">Đăng ký</a></li>
                `);
            }
        });
    }

    function setClickListeners() {

        $('button.hamburger').click(function () {
            var icon = $(this).children();
            var iconName = icon.attr('name');

            if (iconName.includes('menu')) {
                const navHeight = $('nav.mobile-navigation').css('height');

                $('.hamburger-menu-container').css('top', navHeight).addClass('show');
                icon.attr('name', 'ios-close');

            } else if (iconName.includes('close')) {
                $('.hamburger-menu-container').removeClass('show');
                icon.attr('name', 'ios-menu');
            }
        });
    }

    function getProductClassification() {
        valib.ajaxGET('/rest/menu', function (obj) {
            var firms = obj.firms.firms,
                models = obj.models.models,
                origins = obj.origins.origins;

            // Desktop
            var brandDropdown = $('#productsByBrandDropdown'),
                modelDropdown = $('#productsByModelDropdown'),
                originDropdown = $('#productsByOriginDropdown');

            // Mobile
            var brandCollapse = $('#collapseByBrand .card-body ul.collapse-menu');
            var modelCollapse = $('#collapseByModel .card-body ul.collapse-menu');
            var originCollapse = $('#collapseByOrigin .card-body ul.collapse-menu');

            brandDropdown.empty();
            modelDropdown.empty();
            originDropdown.empty();

            brandCollapse.empty();
            modelCollapse.empty();
            originCollapse.empty();

            firms.forEach(item => {
                const url = '/products/brand?id=' + item.id;

                brandDropdown.append(`
                    <li class="dropdown-item">
                        <a href="${url}">${item.name}</a>
                    </li>
                `);

                brandCollapse.append(`
                    <li class="hamburger-item"><a class="hamburger-link" href="${url}">${item.name}</a>
                `);
            });

            models.forEach(item => {
                const url = '/products/model?id=' + item.id;

                modelDropdown.append(`
                    <li class="dropdown-item">
                        <a href="${url}">${item.name}</a>
                    </li>
                `);

                modelCollapse.append(`
                    <li class="hamburger-item"><a class="hamburger-link" href="${url}">${item.name}</a>
                `);
            });

            origins.forEach(item => {
                const url = '/products/origin?id=' + item.id;

                originDropdown.append(`
                    <li class="dropdown-item">
                        <a href="${url}">${item.name}</a>
                    </li>
                `);

                originCollapse.append(`
                    <li class="hamburger-item"><a class="hamburger-link" href="${url}">${item.name}</a>
                `);
            });
        });
    }

    loadCustomerProps();
    getProductClassification();
    setClickListeners();
    initStickyNavbar();
});
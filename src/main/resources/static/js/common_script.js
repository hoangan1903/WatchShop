/* Common functionality for all pages */

$(document).ready(function () {

    function initHoverDropdown() {

        function toggleDropdown(e) {
            const _d = $(e.target).closest('.dropdown');
            const _m = $('.dropdown-menu', _d);

            setTimeout(function () {
                const shouldOpen = e.type !== 'click' && _d.is(':hover');

                _m.toggleClass('show', shouldOpen);
                _d.toggleClass('show', shouldOpen);
                $('[data-toggle="dropdown"]', _d).attr('aria-expanded', shouldOpen);

            }, e.type === 'mouseleave' ? 200 : 0);
        }

        $('body')
            .on('mouseenter mouseleave', '.dropdown', toggleDropdown)
            .on('click', '.dropdown-menu a', toggleDropdown);
    }

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
                    accountDropdown.html(`
                        <li>
                            <p class="m-0 p-3">
                                <em>Xin chào, <span class="text-wt-bold">${name}</span>!</em>
                            </p>
                        </li>
                        <li><a href="account#personal-info">Thông tin khách hàng</a></li>
                        <li><a href="account#orders">Đơn hàng đã đặt</a></li>
                        <li><a href="logout">Đăng xuất</a></li>
                    `);
                });

            } else {
                cartBadge.text(null);
                accountDropdown.html(`
                    <li><a href="login">Đăng nhập</a></li>
                    <li><a href="register">Đăng ký</a></li>
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

    initHoverDropdown();
    loadCustomerProps();
    setClickListeners();
});
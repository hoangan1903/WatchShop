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

    function updateCartBadge() {
        valib.ajaxGET('/rest/users/isLoggedIn', function (obj) {
            var isLoggedIn = Boolean(obj),
                cartBadge = $('#cart-count-badge');

            if (isLoggedIn) {
                valib.ajaxGET('/rest/cart', function (obj) {
                    // Get cart count (total items) and all products
                    var count = obj.totalAmount || 0;
                    cartBadge.text(count);
                });
            } else {
                cartBadge.text(null);
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

        $('#account').click(function () {
            valib.ajaxGET('/rest/users/isLoggedIn', function (obj) {
                var isLoggedIn = Boolean(obj);

                if (isLoggedIn) {
                    // Do something if the user is logged in
                } else {
                    // Do something else if the user is logged in
                }
            });
            return false;
        });

        $('#cart').click(function () {
            valib.ajaxGET('/rest/users/isLoggedIn', function (obj) {
                var isLoggedIn = Boolean(obj);

                if (isLoggedIn) {
                    window.location.href = 'cart';
                } else {
                    // Redirect to Login page
                    window.location.href = 'login';
                }
            });
            return false;
        });
    }

    initHoverDropdown();
    updateCartBadge();
    setClickListeners();
});
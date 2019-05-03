/* Common functionality for all pages */

$(document).ready(function () {

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

    function initHoverDropdown() {
        $('body')
            .on('mouseenter mouseleave', '.dropdown', toggleDropdown)
            .on('click', '.dropdown-menu a', toggleDropdown);
    }

    function setOnClickHamburger() {
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
    setOnClickHamburger();
});

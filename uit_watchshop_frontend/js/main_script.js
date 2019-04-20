_va.ready(function () {

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

    $('.section-banners').waypoint({
        handler: function (direction) {
            if (direction === 'down') {
                $('.navigation').addClass('stick');
            } else {
                $('.navigation').removeClass('stick');
            }
        },
        offset: -1
    });
});

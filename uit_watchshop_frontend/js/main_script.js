/* Homepage's functionality implementation */

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

    function initSliders() {
        let sliders, nextButtons, length;

        sliders = $('.custom_slider');
        nextButtons = $('.custom_slider_nav');
        length = sliders.length;

        sliders.owlCarousel({
            loop: true,
            autoplay: false,
            center: false,
            margin: 16,
            nav: false,
            dots: false,
            responsive: {
                0: {
                    items: 1
                },
                576: {
                    items: 2
                },
                768: {
                    items: 3
                },
                992: {
                    items: 4
                }
            }
        });

        for (let i = 0; i < length; i++) {
            let slider = sliders.eq(i),
                next = nextButtons.eq(i);

            next.click(function () {
                slider.trigger('next.owl.carousel');
            });
        }
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

    initSliders();
});

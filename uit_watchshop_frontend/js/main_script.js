/* Homepage functionality implementation */

$(document).ready(function () {

    // Define sticky navigation bar behavior
    // when to appear and when to disappear
    function initStickyNavbar() {
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
    }

    function initSliders() {
        let sliders, nextButtons, length;

        // get all sliders
        sliders = $('.custom_slider');
        // get "next" buttons
        nextButtons = $('.custom_slider_nav');
        // get number of sliders
        length = sliders.length;

        // make every slider an Owl Carousel
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
            // get each and every slider and button
            let slider = sliders.eq(i),
                next = nextButtons.eq(i);

            // set click handler for each next button
            // slide to the next item in the list (step = 1)
            next.click(function () {
                slider.trigger('next.owl.carousel');
            });
        }
    }

    initStickyNavbar()
    initSliders();
});

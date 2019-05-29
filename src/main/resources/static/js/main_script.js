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
        let prevButtons = $('.custom-slider-nav.prev'),
            nextButtons = $('.custom-slider-nav.next');

        $('.custom-slider').each(function (index) {
            let slider = $(this),
                prev = prevButtons.eq(index),
                next = nextButtons.eq(index);

            slider.owlCarousel(
                {
                    loop: false,
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
                }
            );

            prev.click(function () {
                slider.trigger('prev.owl.carousel');
            });

            next.click(function () {
                slider.trigger('next.owl.carousel');
            });
        });
    }

    function getBanners() {
        let carousel, indicators, inner;

        carousel = $('.main-carousel .carousel-indicators, .main-carousel .carousel-inner');
        indicators = carousel.eq(0);
        inner = carousel.eq(1);

        valib.ajaxGET('/rest/banners', function (obj) {
            if (obj.totals > 0) {
                carousel.empty();
                obj.banners.forEach((banner, index) => {
                    indicators.append(`<li data-target="#mainCarouselIndicators" data-slide-to="${index}"></li>`);
                    inner.append(`
                        <div class="carousel-item">
                            <img src="${banner.url}" class="d-block w-100" alt="">
                        </div>
                    `);

                    if (index === 0) {
                        carousel.children().addClass('active');
                    }
                });
            }
        });
    }

    function getCatalogue() {
        let topSlider = $('#top-slider'),
            citizenSlider = $('#citizen-slider'),
            ogivalSlider = $('#ogival-slider'),
            orientSlider = $('#orient-slider'),
            bulovaSlider = $('#bulova-slider');

        function fillSlider(slider, data) {
            data.forEach(item => {
                let html = `
                <div class="card product-card" style="width: auto;">
                    <img src="${item.image}" class="card-img-top card-product-img" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${item.firm.name} ${item.codeName}</h5>
                        <p class="price">${item.price.toLocaleString()}đ</p>
                        <a href="/product-details?id=${item.id}" class="btn btn-outline-success">Xem chi tiết</a>
                    </div>
                </div>
                `;

                slider.trigger('add.owl.carousel', [html]).trigger('refresh.owl.carousel');
            });
        }

        valib.ajaxGET('/rest/products/catalogue', function (obj) {

            fillSlider(topSlider, obj.top.products);
            fillSlider(citizenSlider, obj.citizens.products);
            fillSlider(ogivalSlider, obj.ogivals.products);
            fillSlider(orientSlider, obj.orients.products);
            fillSlider(bulovaSlider, obj.bulovas.products);

        });
    }

    $('a#home-page-link').addClass('nav-link-active');
    initStickyNavbar();

    initSliders();
    getBanners();
    getCatalogue();
});

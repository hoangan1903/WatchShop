$(document).ready(function () {

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

    function initProductCarousel() {
        $('.product-carousel').carousel({
            interval: 0
        });
    }

    function getData() {
        var id = _va.getValueByKeyFromURL('id');

        _va.ajaxGET('/rest/products/details/' + id, function (obj) {
            console.log(obj);
        });
    }

    initStickyNavbar();
    initProductCarousel();
    getData();
});
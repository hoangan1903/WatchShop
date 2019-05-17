$(document).ready(function () {

    var productCarousel = $('.product-carousel'),
        brandObj = $('a.brand-link'),
        nameObj = $('h3.product-name'),
        priceObj = $('p.price'),
        available = $('span.available'),
        table = $('table#details');

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

    function initCarousel() {
        productCarousel.carousel({
            interval: 0
        });
    }

    function init() {
        var id = valib.getValueFromURL('id');

        valib.ajaxGET('/rest/products/details/' + id, function (obj) {
            console.log(obj);
            var brand = obj.product.firm.name;

            brandObj.text(brand).attr('href', `${brand.toLowerCase()}-watches`);
            nameObj.text(obj.product.codeName);
            priceObj.text(obj.product.price);
            available.text(obj.product.available);

            table.html(`
            <tr>
                <td class="spec-title">Bảo hành/Bảo hiểm</td>
                <td class="spec-content">${obj.insurance}</td>
            </tr>
            <tr>
                <td class="spec-title">Đổi trả</td>
                <td class="spec-content">30 ngày</td>
            </tr>
            <tr>
                <td class="spec-title">Giao hàng</td>
                <td class="spec-content">Miễn phí toàn quốc</td>
            </tr>
            <tr>
                <td class="spec-title">Nhãn hiệu</td>
                <td class="spec-content">${brand}</td>
            </tr>
            <tr>
                <td class="spec-title">Nguồn gốc</td>
                <td class="spec-content">${obj.origin.name}</td>
            </tr>
            <tr>
                <td class="spec-title">Kiểu máy</td>
                <td class="spec-content">${obj.model.name}</td>
            </tr>
            <tr>
                <td class="spec-title">Đồng hồ dành cho</td>
                <td class="spec-content">Nam/Nữ</td>
            </tr>
            <tr>
                <td class="spec-title">Kích cỡ</td>
                <td class="spec-content">${obj.size}</td>
            </tr>
            <tr>
                <td class="spec-title">Chất liệu</td>
                <td class="spec-content">Vỏ: ${obj.caseMaterial}; Dây: ${obj.chainMaterial}; Kính: ${obj.glassMaterial}</td>
            </tr>
            <tr>
                <td class="spec-title">Khả năng chịu nước</td>
                <td class="spec-content">${obj.waterResistance}</td>
            </tr>
            `);
        });
    }

    function initComments() {

    }

    initStickyNavbar();
    initCarousel();
    init();
});
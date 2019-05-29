/* Products page functionality implementation */

$(document).ready(function () {
    var title = $('h2.section-heading');

    function getSearchResults(keyword) {
        paginator(
            {
                info: {
                    type: 'search',
                    keyword: keyword,
                    pageSize: 8
                },
                selectors: {
                    container: '.section-product-list .row',
                    pagination: '.section-pagination-links .pagination'
                },
                paginationStyle: 'advanced'
            }
        );
    }

    function getProductsByBrand(brandId) {
        paginator(
            {
                info: {
                    type: 'show-products',
                    brand: brandId,
                    pageSize: 8
                },
                selectors: {
                    container: '.section-product-list .row',
                    pagination: '.section-pagination-links .pagination'
                },
                paginationStyle: 'advanced'
            }
        );
    }

    function init() {
        var pathName = location.pathname;

        if (pathName.includes('search')) {

            var keyword = valib.getValueFromURL('q');
            title.text("Kết quả tìm kiếm cho '" + keyword + "'");
            getSearchResults(keyword);

        } else if (pathName.includes('citizen-watches')) {

            title.text("Đồng hồ Citizen");
            getProductsByBrand(1);

        } else if (pathName.includes('ogival-watches')) {

            title.text("Đồng hồ Ogival");
            getProductsByBrand(2);

        } else if (pathName.includes('orient-watches')) {

            title.text("Đồng hồ Orient");
            getProductsByBrand(6);

        } else if (pathName.includes('bulova-watches')) {

            title.text("Đồng hồ Bulova");
            getProductsByBrand(4);

        } else if (pathName.includes('products')) {

            const id = parseInt(valib.getValueFromURL('id'));

            if (pathName.includes('brand')) {

                getProductsByBrand(id);

            } else if (pathName.includes('model')) {

            } else if (pathName.includes('origin')) {

            }
        }
    }

    init();
});

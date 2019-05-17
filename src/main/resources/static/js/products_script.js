/* Products page functionality implementation */

$(document).ready(function () {

    function init() {
        var pathName = location.pathname;

        if (pathName.includes('search')) {

            getSearchResults();

        } else if (pathName.includes('citizen-watches')) {

            getProductsByBrand(1);

        } else if (pathName.includes('ogival-watches')) {

            getProductsByBrand(2);

        } else if (pathName.includes('orient-watches')) {

            getProductsByBrand(6);

        } else if (pathName.includes('bulova-watches')) {

            getProductsByBrand(4);

        }
    }

    function getSearchResults() {
        var keyword = valib.getValueFromURL('q');
        paginator(
            {
                info: {
                    type: 'search',
                    keyword: keyword,
                    pageSize: 12
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
                    pageSize: 12
                },
                selectors: {
                    container: '.section-product-list .row',
                    pagination: '.section-pagination-links .pagination'
                },
                paginationStyle: 'advanced'
            }
        );
    }

    init();
});

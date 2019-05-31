/* Products page functionality implementation */

$(document).ready(function () {
    const pathName = location.pathname;

    // Determines how many items will be displayed each page
    const PAGE_SIZE = 8;

    var title = $('h2.section-heading');
    var func;

    function getSearchResults(keyword, sort) {
        paginator(
            {
                info: {
                    type: 'search',
                    keyword: keyword,
                    sort: sort,
                    pageSize: PAGE_SIZE
                },
                selectors: {
                    container: '.section-product-list .row',
                    pagination: '.section-pagination-links .pagination'
                },
                paginationStyle: 'advanced'
            }
        );
    }

    function getProducts(by, id, sort) {
        paginator({
            info: {
                type: 'show-products',
                by: by,
                id: id,
                sort: sort,
                pageSize: PAGE_SIZE
            },
            selectors: {
                container: '.section-product-list .row',
                pagination: '.section-pagination-links .pagination'
            },
            paginationStyle: 'advanced'
        });
    }

    function init() {

        if (pathName.includes('search')) {

            const keyword = valib.getValueFromURL('q');
            title.text("Kết quả tìm kiếm cho '" + keyword + "'");
            func = getSearchResults.bind(this, keyword);

        } else if (pathName.includes('products')) {

            const id = parseInt(valib.getValueFromURL('id'));

            if (pathName.includes('brand')) {

                valib.ajaxGET('/rest/firms/' + id, function (obj) {
                    title.text('Đồng hồ ' + obj.name);
                });

                func = getProducts.bind(this, 'firm', id);

            } else if (pathName.includes('model')) {

                valib.ajaxGET('/rest/models/' + id, function (obj) {
                    title.text('Đồng hồ ' + obj.name);
                });

                func = getProducts.bind(this, 'model', id);

            } else if (pathName.includes('origin')) {

                valib.ajaxGET('/rest/origins/' + id, function (obj) {
                    title.text('Đồng hồ ' + obj.name);
                });

                func = getProducts.bind(this, 'origin', id);

            } else if (pathName.includes('all')) {

                title.text("Tất cả sản phẩm");
                func = getProducts.bind(this, null, null);
            }
        }

        func(null);
    }

    $('#sortInput').change(function () {
        const selectedValue = $(this).val();
        const sort = isNaN(parseInt(selectedValue)) ? selectedValue : null;

        func(sort);
    });

    init();
});

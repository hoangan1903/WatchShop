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

    function getProducts(by, id) {
        paginator({
            info: {
                type: 'show-products',
                by: by,
                id: id,
                pageSize: 8
            },
            selectors: {
                container: '.section-product-list .row',
                pagination: '.section-pagination-links .pagination'
            },
            paginationStyle: 'advanced'
        });
    }

    function init() {
        var pathName = location.pathname;

        if (pathName.includes('search')) {

            const keyword = valib.getValueFromURL('q');

            title.text("Kết quả tìm kiếm cho '" + keyword + "'");
            getSearchResults(keyword);

        } else if (pathName.includes('products')) {

            const id = parseInt(valib.getValueFromURL('id'));

            if (pathName.includes('brand')) {

                valib.ajaxGET('/rest/firms/' + id, function (obj) {
                    title.text('Đồng hồ ' + obj.name);
                });
                getProducts('firm', id);

            } else if (pathName.includes('model')) {

                valib.ajaxGET('/rest/models/' + id, function (obj) {
                    title.text('Đồng hồ ' + obj.name);
                });
                getProducts('model', id);

            } else if (pathName.includes('origin')) {

                valib.ajaxGET('/rest/origins/' + id, function (obj) {
                    title.text('Đồng hồ ' + obj.name);
                });
                getProducts('origin', id);
            }
        }
    }

    init();
});

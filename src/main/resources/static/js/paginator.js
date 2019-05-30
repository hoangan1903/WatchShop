/*
 * paginator.js v3.0 by Viet Anh
 * Data paginator based on Bootstrap 4 pagination
 * Requires jQuery. Import this file after jQuery import in your HTML document
 * See https://getbootstrap.com/docs/4.3/components/pagination/
 * for more information about this Bootstrap component
 */

(function (global, $, valib) {

    /* Local variables */

    var info,
        totalPages,
        currentPage,
        container,
        pagination,
        paginationStyle;


    function paginator(specs) {
        info = specs.info;
        container = $(specs.selectors.container);
        pagination = $(specs.selectors.pagination);
        paginationStyle = specs.paginationStyle;

        showPage(1);
    };

    /* Functions */

    function getApiUrl() {
        let url,
            pageId = currentPage - 1;

        if (info.type === 'search') {

            let paramsObject = {
                page: pageId,
                size: info.pageSize,
                keyword: info.keyword
            };
            if (info.sort) {
                paramsObject.sort = info.sort;
            }

            url = valib.getURLWithParams('/rest/products/find', paramsObject);

        } else if (info.type === 'show-products') {

            let paramsObject = {
                page: pageId,
                size: info.pageSize
            };
            paramsObject[info.by] = info.id;
            if (info.sort) {
                paramsObject.sort = info.sort;
            }

            url = valib.getURLWithParams('/rest/products', paramsObject);
        }

        return url;
    }

    function getHTMLOf(pageData) {
        let pageHTML = '';

        pageData.forEach(item => {
            const html = `
            <div class="col-lg-3 col-md-4 col-sm-6 p-2">
                <div class="card product-card" style="width: auto;">
                    <img src="${item.image}" class="card-img-top card-product-img" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${item.firm.name} ${item.codeName}</h5>
                        <p class="price">${item.price.toLocaleString()}đ</p>
                        <a href="/product-details?id=${item.id}" class="btn btn-outline-success">Xem chi tiết</a>
                    </div>
                </div>
            </div>
            `;

            pageHTML += html;
        });

        return pageHTML;
    }

    function designBasicPagination(totalPages, selectedPage) {
        let design = [];

        for (let i = 1; i <= totalPages; i++) {

            let page = i.toString();
            if (i === selectedPage) {
                page += ' active';
            }
            design.push(page);
        }

        return design;
    }

    function designAdvancedPagination(totalPages, selectedPage) {
        // Proceed if (0 < selectedPage <= totalPages)
        if (selectedPage > 0 && selectedPage <= totalPages) {
            let left = [],
                prev = selectedPage - 1;

            if (prev > 0) {
                left.unshift(prev);
                prev--;

                if (prev > 0) {
                    left.unshift(prev);
                    prev--;

                    if (prev > 0) {
                        left.unshift('...');
                    }
                }

                left.unshift('prev');

            } else {
                left.unshift('prev disabled');
            }
            left.unshift('first');

            let right = [],
                next = selectedPage + 1;

            if (next <= totalPages) {
                right.push(next);
                next++;

                if (next <= totalPages) {
                    right.push(next);
                    next++;

                    if (next <= totalPages) {
                        right.push('...');
                    }

                }

                right.push('next');

            } else {
                right.push('next disabled');
            }
            right.push('last');

            return left.concat(selectedPage + ' active', right).map(String);
        }

        return null;
    }

    // Generates a design following some conventions
    // from which the actual pagination bar will be built
    function designPagination(totalPages, selectedPage) {
        if (!paginationStyle || paginationStyle === 'basic') {

            return designBasicPagination(totalPages, selectedPage);

        } else if (paginationStyle === 'advanced') {

            return designAdvancedPagination(totalPages, selectedPage);

        } else {
            throw new Error('Pagination style not properly specified');
        }
    }

    function getHTMLFromDesign(design) {

        let HTMLArr = design.map(item => {
            if (item === 'first') {

                return '<li class="page-item"><a class="page-link first-page" href="#">&larrb;</a></li>';

            } else if (item === 'last') {

                return '<li class="page-item"><a class="page-link last-page" href="#">&rarrb;</a></li>';

            } else if (item === 'prev') {

                return '<li class="page-item"><a class="page-link prev-page" href="#">&laquo;</a></li>';

            } else if (item === 'next') {

                return '<li class="page-item"><a class="page-link next-page" href="#">&raquo;</a></li>';

            } else if (item === 'prev disabled') {

                return '<li class="page-item disabled"><span class="page-link">&laquo;</span></li>';

            } else if (item === 'next disabled') {

                return '<li class="page-item disabled"><span class="page-link">&raquo;</span></li>';

            } else if (item === '...') {

                return '<li class="page-item disabled"><span class="page-link">...</span></li>';

            } else if (item.includes('active')) {

                return `
                <li class="page-item active" aria-current="page">
                    <span class="page-link">
                        ${item.split(" ")[0]}
                        <span class="sr-only">(current)</span>
                    </span>
                </li>
                `;

            } else {
                return `<li class="page-item"><a class="page-link" href="#">${item}</a></li>`;
            }
        });

        return HTMLArr.join('');
    }

    function setClickPagination() {
        let paginationItems = pagination.find('a');

        paginationItems.click(function (event) {
            event.preventDefault();
            let clickedItem = $(this);

            if (clickedItem.hasClass('first-page')) {

                showPage(1);

            } else if (clickedItem.hasClass('last-page')) {

                showPage(totalPages);

            } else if (clickedItem.hasClass('prev-page')) {

                showPage(currentPage - 1);

            } else if (clickedItem.hasClass('next-page')) {

                showPage(currentPage + 1);

            } else {

                let clickedPage = parseInt(clickedItem.text());
                showPage(clickedPage);
            }
        });
    }

    function buildPagination() {
        let design = designPagination(totalPages, currentPage);
        let html = getHTMLFromDesign(design);

        pagination.html(html);
        setClickPagination();
    }

    function showPage(pageNumber) {
        showLoadingScreen();

        currentPage = pageNumber;
        const url = getApiUrl();

        valib.ajaxGET(url, function (obj) {
            totalPages = obj.pageCountTotal;
            if (totalPages > 0) {
                const html = getHTMLOf(obj.products);
                container.html(html);
                buildPagination();
            }
            hideLoadingScreen(450);
        });
    }


    // Expose paginator function to global context for use
    global.paginator = paginator;

})(window, jQuery, valib);
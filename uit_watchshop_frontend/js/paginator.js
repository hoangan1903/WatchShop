/*
 * Paginator.js v2.1 by Viet Anh
 * Data paginator based on Bootstrap 4 pagination
 * Requires jQuery. Import this file after jQuery import in your HTML document
 * See https://getbootstrap.com/docs/4.3/components/pagination/
 * for more information about this Bootstrap component
 */

(function (global, $) {

    /* Local variables */

    var obj,
        totalPages,
        currentPage,
        container,
        pagination,
        paginationStyle;


    /* Constructor function
    The 'specs' object should look like this
    {
        obj: (required) data to be displayed, must be an array of objects (each element in it represents a page item)
        selectors: (required) {
            container: CSS class selector of the HTML container
            pagination: CSS class selector of the Bootstrap Pagination component (default: 'ul.pagination')
        },
        paginationStyle: 'basic'/'advanced'
    }
    */
    function Paginator(specs) {
        this.obj = obj = specs.obj;
        this.totalPages = totalPages = this.obj.length;
        this.container = container = $(specs.selectors.container);
        this.pagination = pagination = $(specs.selectors.pagination);
        this.paginationStyle = paginationStyle = specs.paginationStyle;
    };

    Paginator.prototype.init = () => showPage(1);

    /* Functions */

    function indexOfPage(pageNumber) {
        return pageNumber - 1;
    }

    function getHTMLOf(pageNumber) {
        let pageData = obj[indexOfPage(pageNumber)], // get data of the page as an array of objects
            pageHTML = '';

        pageData.forEach(item => {
            const html = `
            <div class="col-lg-3 col-md-4 col-sm-6 p-2">
                <div class="card product-card" style="width: auto;">
                    <div class="product-img-container">
                        <img src="${item.image}" class="card-img-top" alt="...">
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${item.name} (ID: ${item.id})</h5>
                        <p class="card-text">${item.price}</p>
                        <a href="#" class="btn btn-outline-success">Xem chi tiết</a>
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

    function setClickPagination(paginationItems) {

        paginationItems.click(function (event) {
            event.preventDefault();
            let obj = $(this);

            if (obj.hasClass('first-page')) {

                showPage(1);

            } else if (obj.hasClass('last-page')) {

                showPage(totalPages);

            } else if (obj.hasClass('prev-page')) {

                showPage(currentPage - 1);

            } else if (obj.hasClass('next-page')) {

                showPage(currentPage + 1);

            } else {

                let clickedPage = parseInt(obj.text());
                showPage(clickedPage);
            }
        });
    }

    function buildPagination() {
        let design, html;

        design = designPagination(totalPages, currentPage);
        html = getHTMLFromDesign(design);

        pagination.empty();
        pagination.html(html);

        setClickPagination(pagination.find('a'));
    }

    function showPage(pageNumber) {
        currentPage = pageNumber;

        container.html(getHTMLOf(currentPage));
        buildPagination();
    }


    // Expose Paginator constructor function to global context for use
    global.Paginator = Paginator;

})(window, jQuery);
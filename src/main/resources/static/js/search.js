$(document).ready(function () {

    var search = $('#main-search'),
        suggestion = $('.search-suggestion'),
        searchBtn = $('#mainSearch');

    var mobileSearch = $('#search-input-m'),
        mobileSuggestion = $('.search-suggestion-m'),
        mobileSearchBtn = $('#btn-search-m');

    function goToSearchResults(keyword) {
        if (keyword.length > 0) {
            var destURL = valib.getURLWithParams('/search', { q: keyword });
            window.location.href = destURL;
        }
    }

    search.on('input', function () {
        var input = this.value;
        if (input && input.length >= 2) {

            // 1. Get data from server
            valib.ajaxGET('/rest/products/find?keyword=' + encodeURI(input), function (obj) {
                var products = obj.products,
                    html = '';

                // 2. Process data retrieved from server
                for (let index = 0; index < products.length; index++) {
                    const item = products[index];
                    html += `
                    <li>
                        <a class="product-link" href="/product-details?id=${item.id}">
                            <div class="suggestion-item d-flex flex-row p-3">
                                <div class="d-flex align-items-start mr-3" style="width: 20%;">
                                    <img src="${item.image}" class="search-item-img" alt="...">
                                </div>
                                <div class="item-details flex-grow-1 d-flex flex-column">
                                    <h6 class="product-name mb-1">${item.firm.name} ${item.codeName}</h6>
                                    <p class="card-text price-small">${item.price.toLocaleString()}đ</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    `;
                }

                // 3. Render data to suggestions
                suggestion.html(html);

                // 4. Show suggestion
                if (suggestion.html()) {
                    suggestion.addClass('show');
                } else {
                    suggestion.removeClass('show');
                }
            });

        } else {
            suggestion.removeClass('show');
        }
    });

    search.keypress(function (e) {
        var key = e.keyCode || e.which;
        if (key == 13) { // Enter press
            e.preventDefault();
            goToSearchResults(this.value);
        }
    });

    searchBtn.click(() => goToSearchResults(search.val()));

    // Mobile
    $('a#search-m').click(() => $('.search-view-m').addClass('show'));
    $('button#btn-search-exit-m').click(() => $('.search-view-m').removeClass('show'));

    mobileSearch.on('input', function () {
        var input = this.value;
        if (input && input.length >= 2) {
            // 1. Get data from server
            valib.ajaxGET('/rest/products/find?keyword=' + encodeURI(input), function (obj) {
                var products = obj.products,
                    html = '';

                const length = products.length;

                // 2. Process data retrieved from server
                for (let index = 0; index < length; index++) {
                    const item = products[index];
                    html += `
                    <li>
                        <a class="product-link" href="/product-details?id=${item.id}">
                            <div class="suggestion-item d-flex flex-row p-3">
                                <div class="d-flex justify-content-center align-items-start mr-3" style="width: 15%;">
                                    <img src="${item.image}" alt="...">
                                </div>
                                <div class="item-details flex-grow-1 d-flex flex-column">
                                    <h6 class="product-name mb-1">${item.firm.name} ${item.codeName}</h6>
                                    <p class="card-text price-small">${item.price.toLocaleString()}đ</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    `;
                }

                // 3. Render data to suggestions
                mobileSuggestion.html(html);

                // 4. Show suggestion
                if (mobileSuggestion.html()) {
                    mobileSuggestion.addClass('show');
                } else {
                    mobileSuggestion.removeClass('show');
                }
            });
        }
    });

    mobileSearch.keypress(function (e) {
        var key = e.keyCode || e.which;
        if (key == 13) { // Enter press
            e.preventDefault();
            goToSearchResults(this.value);
        }
    });

    mobileSearchBtn.click(() => goToSearchResults(mobileSearch.val()));
});
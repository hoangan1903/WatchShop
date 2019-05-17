$(document).ready(function () {
    var search = $('#main-search'),
        suggestion = $('.search-suggestion'),
        searchBtn = $('#btn-main-search'),
        timeout;

    const maxSuggestions = 5;

    function goToSearchResults() {
        var keyword, destURL;
        keyword = search.val();

        if (keyword.length > 0) {
            destURL = valib.getURLWithParams('search', { q: keyword });
            window.location.href = destURL;
        }
    }

    search.on('input', function () {
        var input = this.value;
        if (input && input.length >= 2) {

            // 1. Get data from server
            valib.ajaxGET('/rest/products/find?keyword=' + encodeURI(input), function (obj) {
                var products = obj.products;
                var totalSuggestions = products.length <= maxSuggestions ? products.length : maxSuggestions;
                var html = '';

                // 2. Process data retrieved from server
                for (let index = 0; index < totalSuggestions; index++) {
                    const item = products[index];
                    html += `
                    <li>
                        <a class="product-link" href="product-details?id=${item.id}">
                            <div class="suggestion-item d-flex flex-row p-3">
                                <div class="item-image d-flex align-items-start mr-3" style="width: 20%;">
                                    <img src="${item.image}" style="width: 100%;"
                                        alt="Citizen BL8144-89H">
                                </div>
                                <div class="item-details flex-grow-1 d-flex flex-column">
                                    <h6 class="product-name mb-1">${item.firm.name} ${item.codeName}</h6>
                                    <p class="card-text price-small">${item.price}</p>
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
            goToSearchResults();
        }
    });

    searchBtn.click(goToSearchResults);

});
$(document).ready(function () {
    var search = $('#main-search'),
        suggestion = $('.search-suggestion');

    search.on('input', function () {
        var input = this.value;
        if (input && input.length >= 2) {

            // 1. AJAX GET
            // _va.ajaxGET('', function (obj, jsonText) {
            // 2. Process data retrieved from server

            // 3. Render data to suggestions
            var html = '';
            for (let i = 1; i <= 3; i++) {
                html += `
                <li>
                    <a class="product-link" href="#">
                        <div class="suggestion-item d-flex flex-row p-3">
                            <div class="item-image d-flex align-items-start mr-3" style="width: 20%;">
                                <img src="images/citizen-watch-03.jpg" style="width: 100%;"
                                    alt="Citizen BL8144-89H">
                            </div>
                            <div class="item-details flex-grow-1 d-flex flex-column">
                                <h6 class="product-name mb-1">Citizen BL8144-89H</h6>
                                <p class="card-text price-small">16,500,000đ</p>
                            </div>
                        </div>
                    </a>
                </li>
                `;
            }
            suggestion.html(html);

            // 4. Show suggestion
            suggestion.addClass('show');

            // });

        } else {
            suggestion.removeClass('show');
        }
    });
});
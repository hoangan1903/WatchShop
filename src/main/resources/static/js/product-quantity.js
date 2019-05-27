$(document).ready(function () {
    var qtyInput = $('input.quantity-input');
    var focusedId;

    $('button.quantity-up').click(function () {
        var currentQty, newQty;

        currentQty = parseInt(qtyInput.val());
        newQty = !isNaN(currentQty) ? currentQty + 1 : 0;

        qtyInput.val(newQty);
    });

    $('button.quantity-down').click(function () {
        var currentQty, newQty;

        currentQty = parseInt(qtyInput.val());
        newQty = !isNaN(currentQty) ? currentQty - 1 : 0;

        qtyInput.val(newQty);
    });

    $('#mgr-product-list').click(function (e) {
        focusedId = parseInt($(e.target).closest('tr').children().eq(0).text());
    });

    $('button#submit-quantity').click(function (e) {
        e.preventDefault();

        let currentQty = parseInt(qtyInput.val());
        if (!isNaN(currentQty) && isFinite(currentQty)) {
            const data = {
                id: focusedId,
                quantity: currentQty
            };

            valib.ajaxPOST({
                url: valib.getURLWithParams('/rest/products/updown', data),
                data: data,
                onSuccess: function (response) {
                    const successful = Boolean(parseInt(response));
                    if (successful) {
                        window.location.reload();
                    } else {
                        console.log('Update quantity unsuccessfully')
                    }
                }
            });
        } else {
            throw new Error('Invalid quantity');
        }
    });
});
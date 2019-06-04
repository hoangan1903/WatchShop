function showSpinner(message) {
    $('p.loading-message').html(message ? message : '');
    $('.screen-cover').removeClass('hidden');
}

function hideSpinner() {
    $('.screen-cover').addClass('hidden');
}
/* Global functions that can be used by any pages if necessary */

const DELAY_AFTER_TASK = 350;

function showLoadingScreen() {
    $('.screen-cover').removeClass('hidden');
}

function hideLoadingScreen(delay) {
    delay = delay || DELAY_AFTER_TASK;
    setTimeout(() => $('.screen-cover').addClass('hidden'), delay);
}
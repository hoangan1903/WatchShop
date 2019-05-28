/* Global functions that can be used by any pages if necessary */

const DELAY_AFTER_TASK = 350;
const LOGIN_CHECK_INTERVAL = 5000;

function showLoadingScreen() {
    $('.screen-cover').removeClass('hidden');
}

function hideLoadingScreen(delay) {
    delay = delay || DELAY_AFTER_TASK;
    setTimeout(() => $('.screen-cover').addClass('hidden'), delay);
}

function checkLogin() {
    valib.ajaxGET('/rest/users/isLoggedIn', function (obj) {
        var isLoggedIn = Boolean(parseInt(obj));
        if (!isLoggedIn) {
            // Redirect to Login page
            window.location.href = 'login';
        }
    });
}
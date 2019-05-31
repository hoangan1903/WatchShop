$(document).ready(function () {

    function showDialog(message) {
        $('#contactModal .modal-body').html(message);
        $('#contactModal').modal('toggle');
    }

    function initFeedback() {
        $('#submitFeedback').click(function () {
            var name = $('#inputName').val(),
                email = $('#inputEmail').val(),
                subject = $('#inputSubject').val(),
                message = $('#inputMessage').val();

            if (name && subject && message) {
                // 1. Make feedback object
                const feedbackObj = {
                    name: name,
                    email: email,
                    title: subject,
                    content: message
                };

                // 2. Post this feedback to server
                valib.ajaxPOST({
                    url: '/rest/feedback',
                    data: feedbackObj,
                    onSuccess: function (response) {
                        var successful = Boolean(parseInt(response));
                        if (successful) {
                            $('#inputName, #inputEmail, #inputSubject, #inputMessage').val('');
                            showDialog(
                                'Phản hồi đã được gửi thành công. Cảm ơn bạn đã đóng góp ý kiến cho chúng tôi. Chúc bạn mua sắm vui vẻ!'
                            );
                        } else {
                            showDialog('Có lỗi xảy ra khi gửi phản hồi. Vui lòng thử lại sau.');
                        }
                    }
                });
            } else {
                showDialog('Vui lòng không bỏ trống các trường: Tên, Chủ đề và Lời nhắn.');
            }
        });
    }

    $('a#contact-page-link').addClass('nav-link-active');
    initFeedback();
});
$(function () {
    $("#account").focus();

    var phoneRule = /^\d{3}\d{3,4}\d{4}$/;
    var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    $("#saveMember").click(function () {
        if ($("#account").val().length == 0) {
            $("#account").addClass("is-invalid");
            $("#accountMessage").html("<span class='text-danger'>이름를 입력하세요</span>");
            $("#account").focus();
            return;
        }

        if ($("#email").val().length == 0) {
            $("#email").addClass("is-invalid");
            $("#emailMessage").html("<span class='text-danger'>이메일을 입력하세요</span>");
            $("#email").focus();
            return;
        }

        if ($("#password").val().length == 0) {
            $("#password").addClass("is-invalid");
            $("#passwordMessage").html("<span class='text-danger'>비밀번호를 입력하세요</span>");
            $("#password").focus();
            return;
        }

        if ($("#repassword").val().length == 0) {
            $("#repassword").addClass("is-invalid");
            $("#repasswordMessage").html("<span class='text-danger'>비밀번호 확인을 입력하세요</span>");
            $("#repassword").focus();
            return;
        }

        if ($("#password").val() != $("#repassword").val()) {
            $("#repassword").addClass("is-invalid");
            $("#repasswordMessage").html(
                "<span class='text-danger'>비밀번호와 비밀번호 확인이 일치 하지 않습니다.</span>"
            );
            $("#password").val("");
            $("#repassword").focus();
            return;
        }

        if ($("#phone").val().length == 0) {
            $("#phone").addClass("is-invalid");
            $("#phoneMessage").html("<span class='text-danger'>휴대전화를 입력하세요</span>");
            $("#phone").focus();
            return;

        }

        var params = "account=" + $("#account").val() +
                    "&email=" + $("#email").val() +
                    "&phoneNumber=" + $("#phone").val() +
                    "&password=" + $("#password").val();

        $.ajax({
            type: 'POST',
            url: 'users',
            data: params,
            dataType: 'text',
            success: function (data) {
                var result = Number(data);
                switch (result) {
                    case 0:
                        alert("성공적으로 회원가입이 되었습니다.");
                        location.href = "/login";
                        break;
                    case 1:
                        alert("이전에 등록된 이메일이 존재합니다.");
                        $("#email").addClass("is-invalid");
                        $("#emailMessage").html("<span class='text-danger'>이전에 등록된 이메일이 존재합니다.</span>");
                        break;
                    default:
                        alert("회원가입에 오류가 발생했습니다. 잠시 후 다시 진행해주세요.");
                        break;
                }
            },
            error: function(error) {
                alert("오류 발생 " + error);
            }
        })
    });

    $("#email").keyup(function () {
        $("#emailMessage").html('');

        var email = $("#email").val();

        if (!emailRule.test(email)) {
            $("#email").addClass("is-invalid");
            $("#emailMessage").html("<span class='text-danger'>이메일 주소 규칙에 맞게 입력해주세요.</span>");
        } else {
            $("#email").removeClass("is-invalid");
            $("#emailMessage").html('');
        }

        if ($("#email").val() == '') {
            $("#email").removeClass("is-invalid");
            $("#emailMessage").html('');
        }
    });

    $("#phone").keyup(function () {
        $("#phoneMessage").html('');

        var phoneNumber = $("#phone").val();

        if (!phoneRule.test(phoneNumber)) {
            $("#phone").addClass("is-invalid");
            $("#phoneMessage").html("<span class='text-danger'>번호 입력 규칙에 맞게 입력해주세요.</span>");
        } else {
            $("#phone").removeClass("is-invalid");
            $("#phoneMessage").html('');
        }

        if ($("#phone").val() == '') {
            $("#phone").removeClass("is-invalid");
            $("#phoneMessage").html('');
        }
    });

    $("#password").keyup(function () {
        $("#password").removeClass("is-invalid");
        $("#passwordMessage").html('');
    });

    $("#repassword").keyup(function () {
        $("#repassword").removeClass("is-invalid");
        $("#repasswordMessage").html('');
    });

});
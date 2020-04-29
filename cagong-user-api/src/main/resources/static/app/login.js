$(function() {
		$("#id").focus();
		$("#loginMember")
				.click(
						function() {
							if ($("#email").val().length == 0) {
								$("#email").addClass("is-invalid");
								$("#emailMessage")
										.html(
												"<span class='text-danger'>이메일을 입력하세요</span>");
								$("#email").focus();
								return;
							}
							if ($("#password").val().length == 0) {
								$("#password").addClass("is-invalid");
								$("#passwordMessage")
										.html(
												"<span class='text-danger'>비밀번호를 입력하세요</span>");
								$("#password").focus();
								return;
							}
							f.submit();

						});

		$("#email").keyup(function() {
			$("#email").removeClass("is-invalid");
			$("#emailMessage").html('');
		});

		$("#password").keyup(function() {
			$("#password").removeClass("is-invalid");
			$("#passwordMessage").html('');
		});

	});

$(function() {
//		$("#cmm").focus();

//		$('.starRev span').click(function() {
//			$(this).parent().children('span').removeClass('on');
//			$(this).addClass('on').prevAll('span').addClass('on');
//			return false;
//		});

		$("#cancel").click(function(){
			history.back(-1);
		});

		$("#saveCafe").click(function() {
			    f.submit();
		});


		$(".mood").on('click', function() {
			var idx = $(this).index();
			var point = (idx + 1) / 2;
			$("#moodRate").val(point);
			$(".star-mood").removeClass("on");
			for (var i = 0; i <= idx; i++) {
				$(".star-mood").eq(i).addClass("on");
			}
		});

		$(".light").on('click', function() {
			var idx = $(this).index();
			var point = (idx + 1) / 2;
			$("#lightRate").val(point);
			$(".star-light").removeClass("on");
			for (var i = 0; i <= idx; i++) {
				$(".star-light").eq(i).addClass("on");
			}
		});

		$(".price").on('click', function() {
			var idx = $(this).index();
			var point = (idx + 1) / 2;
			$("#priceRate").val(point);
			$(".star-price").removeClass("on");
			for (var i = 0; i <= idx; i++) {
				$(".star-price").eq(i).addClass("on");
			}
		});

		$(".taste").on('click', function() {
			var idx = $(this).index();
			var point = (idx + 1) / 2;
			$("#tasteRate").val(point);
			$(".star-taste").removeClass("on");
			for (var i = 0; i <= idx; i++) {
				$(".star-taste").eq(i).addClass("on");
			}
		});
	});
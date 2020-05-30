$(function() {
		// 중복제거를 위한 함수변수 설정
		const ajax_button = function(category) {
			$.ajax({
						type : 'GET',
						url : '/scoresets?index=' + category,
						dataType : 'json',
						error : function() {
							alert('error');
						},
						success : function(data) {
							console.log(data);
							let rate = category;
							for (let i = 0; i < data.length; i++) {
								let name = data[i].cafeName;
								let value = data[i][rate];
								let url = 'http://d2dzfaqwlhqkso.cloudfront.net/cagong-ranking-project/img/cafe-list' + data[i].cafeImgUrl;
								let linkName = "#link" + i;
								let imgName = "#shop-img" + i;
								let shopName = "#shop" + i;
								let rateId = "#rate-no" + i;
								$(linkName).attr('href',
										'/cafes/' + data[i].cafeId + '/detail');
								$(imgName).attr('src', url);
								$(shopName).html(name);
								$(rateId).html(value);
							}
						}
					});
		}
		$(".list-group-item").click(function() {
			$(".title-description").hide();
			$(".title-rating").show();
			$(".list-group-item").removeClass("active");
		});

		$("#mood").click(function() {
			$("#subtitle").html("분위기");
			ajax_button("mood");
			$("#mood").addClass("active");
		});
		$("#light").click(function() {
			$("#subtitle").html("조명");
			ajax_button("light");
			$("#light").addClass("active");
		});
		$("#price").click(function() {
			$("#subtitle").html("가격");
			ajax_button("price");
			$("#price").addClass("active");
		});
		$("#taste").click(function() {
			$("#subtitle").html("맛");
			ajax_button("taste");
			$("#taste").addClass("active");
		});

		$("#showRank").click(function(){
			$(".mainShow").hide();
			$(".row").css({"animation-duration": "3s", "antimation-name": "fadeInUp"});
			$(".row").show();
			$("#activeBack").attr("style", "background:none");
		})

	});


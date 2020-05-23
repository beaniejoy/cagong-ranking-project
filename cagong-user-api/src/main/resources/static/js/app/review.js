var review = {
    init: function () {
        var _this = this;

        _this.calculate_score();

        $("#cancel").click(function () {
            history.back(-1);
        });

        $('#btn-save').on('click', function () {
            if (_this.is_right()) _this.save();
        });

        $('#btn-update').on('click', function () {
            if (_this.is_right()) _this.update();
        });
    },
    save: function () {
        var data = {
            mood: $('#mood-rate').val(),
            light: $('#light-rate').val(),
            price: $('#price-rate').val(),
            taste: $('#taste-rate').val(),
            comment: $('#comment').val(),
            userId: $('#user-id').val()
        };

        $.ajax({
            type: 'POST',
            url: '/cafes/' + $('#cafe-id').val() + '/reviews',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function (data) {
                alert('성공적으로 리뷰가 등록되었습니다.');
                window.location.href = '/cafes/' + $('#cafe-id').val() + '/detail';
            },
            error: function (error) {
                alert(error);
            }
        })
    },
    update: function () {
        var data = {
            mood: $('#mood-rate').val(),
            light: $('#light-rate').val(),
            price: $('#price-rate').val(),
            taste: $('#taste-rate').val(),
            comment: $('#comment').val(),
            userId: $('#user-id').val()
        };

        $.ajax({
            type: 'PUT',
            url: '/cafes/' + $('#cafe-id').val() + '/reviews/' + $('#review-id').val(),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function (data) {
                alert('성공적으로 리뷰가 수정되었습니다.');
                window.location.href = '/cafes/' + $('#cafe-id').val() + '/detail';
            },
            error: function (error) {
                alert(error);
            }
        })
    },
    is_right: function () {
        if ($('#mood-rate').val() == '') {
            alert('mood에 대한 평가를 해주세요.');
            return false;
        }

        if ($('#light-rate').val() == '') {
            alert('light 대한 평가를 해주세요.');
            return false;
        }

        if ($('#price-rate').val() == '') {
            alert('price 대한 평가를 해주세요.');
            return false;
        }

        if ($('#taste-rate').val() == '') {
            alert('taste 대한 평가를 해주세요.');
            return false;
        }

        if ($('#comment').val() == '') {
            alert('Comment를 작성해주세요.');
            return false;
        }

        return true;
    },
    calculate_score: function(){
        $(".mood").on('click', function () {
            var idx = $(this).index();
            var point = (idx + 1) / 2;
            console.log(point);
            $("#mood-rate").val(point);
            $(".star-mood").removeClass("on");
            for (var i = 0; i <= idx; i++) {
                $(".star-mood").eq(i).addClass("on");
            }
        });

        $(".light").on('click', function () {
            var idx = $(this).index();
            var point = (idx + 1) / 2;
            console.log(point);
            $("#light-rate").val(point);
            $(".star-light").removeClass("on");
            for (var i = 0; i <= idx; i++) {
                $(".star-light").eq(i).addClass("on");
            }
        });

        $(".price").on('click', function () {
            var idx = $(this).index();
            var point = (idx + 1) / 2;
            console.log(point);
            $("#price-rate").val(point);
            $(".star-price").removeClass("on");
            for (var i = 0; i <= idx; i++) {
                $(".star-price").eq(i).addClass("on");
            }
        });

        $(".taste").on('click', function () {
            var idx = $(this).index();
            var point = (idx + 1) / 2;
            console.log(point);
            $("#taste-rate").val(point);
            $(".star-taste").removeClass("on");
            for (var i = 0; i <= idx; i++) {
                $(".star-taste").eq(i).addClass("on");
            }
        });
    }
};

review.init();


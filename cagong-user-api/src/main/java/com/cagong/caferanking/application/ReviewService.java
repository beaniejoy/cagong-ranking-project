package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.Pagination;
import com.cagong.caferanking.domain.entity.*;
import com.cagong.caferanking.domain.network.request.ReviewApiRequest;
import com.cagong.caferanking.domain.network.response.CommentApiResponse;
import com.cagong.caferanking.domain.network.response.ReviewApiResponse;
import com.cagong.caferanking.error.*;
import com.cagong.caferanking.interfaces.dto.DataWithPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final CafeRepository cafeRepository;

    private final UserRepository userRepository;

    private final ScoreSetRepository scoreSetRepository;

    // TODO: CommentService로 아예 분리할 것
    public DataWithPageResponseDto getComments(Long cafeId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAllByCafeId(cafeId, pageable);

        // 카페의 Comment가 한 개도 존재하지 않는 경우
        if (reviews.getTotalElements() == 0) {
            throw new CommentNotExistedException(cafeId);
        }

        List<CommentApiResponse> commentList = reviews.stream()
                .map(review -> {
                    return CommentApiResponse.builder()
                            .id(review.getId())
                            .account(review.getUser().getAccount())
                            .content(review.getComment())
                            .build();
                })
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .currentPage(reviews.getNumber())
                .currentElements(reviews.getNumberOfElements())
                .build();


        return DataWithPageResponseDto.builder()
                .data(commentList)
                .pagination(pagination)
                .build();
    }

    public ReviewApiResponse getReview(Long cafeId, Long userId) {
        Review review = reviewRepository.findByCafeIdAndUserId(cafeId, userId)
                .orElseThrow(() -> new ReviewNotFoundException(cafeId, userId));

        return response(review);
    }

    @Transactional
    public ReviewApiResponse addReview(Long cafeId, ReviewApiRequest resource) {
        // 1. 작성할 Review 대상 카페 조회
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new CafeNotFoundException(cafeId));
        
        // 2. 작성자(User) 조회
        User user = userRepository.findById(resource.getUserId())
                .orElseThrow(() -> new UserNotFoundException(resource.getUserId()));

        // 3. request 로 받아온 객체에 cafe, user 객체 삽입
        Review saved = reviewRepository.save(resource.toEntity(cafe, user));

        // 4. Changed Average ScoreSet Update
        Object changedAverage = reviewRepository.findAverageByCafeId(cafeId);

        ScoreSet scoreSet = scoreSetRepository.findByCafeId(cafeId)
                .orElseThrow(() -> new ScoreSetNotFoundException(cafeId));

        scoreSet.updateAverage(changedAverage);

        return response(saved);

    }

    @Transactional
    public ReviewApiResponse updateReview(Long reviewId, Long cafeId, ReviewApiRequest resource) {
        // 1. review_id를 통한 upadte할 Review 조회
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(cafeId, resource.getUserId()));
        
        // 2. 해당 리뷰에 대한 update 진행
        review.updateReview(
                resource.getMood(),
                resource.getLight(),
                resource.getPrice(),
                resource.getTaste(),
                resource.getComment()
        );

        // 3. Changed Average ScoreSet Update
        Object changedAverage = reviewRepository.findAverageByCafeId(cafeId);

        // TODO: cafeId도 scoreSet에서 유일한 값인데 이에 대한 수정 필요?
        ScoreSet scoreSet = scoreSetRepository.findByCafeId(cafeId)
                .orElseThrow(() -> new ScoreSetNotFoundException(cafeId));

        scoreSet.updateAverage(changedAverage);

        return response(review);
    }

    // TODO: delete 기능 넣을 것

    public ReviewApiResponse response(Review review) {

        return ReviewApiResponse.builder()
                .id(review.getId())
                .userName(review.getUser().getAccount())
                .cafeName(review.getCafe().getName())
                .mood(review.getMood())
                .light(review.getLight())
                .price(review.getPrice())
                .taste(review.getTaste())
                .comment(review.getComment())
                .build();
    }

}

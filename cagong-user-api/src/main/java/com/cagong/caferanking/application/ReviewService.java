package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.Review;
import com.cagong.caferanking.domain.entity.ScoreSet;
import com.cagong.caferanking.domain.network.response.CommentApiResponse;
import com.cagong.caferanking.domain.network.response.ReviewApiResponse;
import com.cagong.caferanking.page.Pagination;
import com.cagong.caferanking.repository.CafeRepository;
import com.cagong.caferanking.repository.ReviewRepository;
import com.cagong.caferanking.repository.ScoreSetRepository;
import com.cagong.caferanking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;

    private CafeRepository cafeRepository;

    private UserRepository userRepository;

    private ScoreSetRepository scoreSetRepository;

    // TODO: CommentService로 아예 분리할 것
    public Map<String, Object> getComments(Long cafeId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAllByCafeId(cafeId, pageable);

        List<CommentApiResponse> comments = reviews.stream()
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

        Map<String, Object> commentPageMap = new HashMap<>();
        commentPageMap.put("comments", comments);
        commentPageMap.put("page", pagination);

        return commentPageMap;
    }

    public ReviewApiResponse getReview(Long cafeId, Long userId) {
        Review review = reviewRepository.findByCafeIdAndUserId(cafeId, userId).orElse(null);

        if (review == null) {
            return ReviewApiResponse.builder()
                    .cafeName(cafeRepository.getOne(cafeId).getName())
                    .userName(userRepository.getOne(userId).getAccount())
                    .mood(.0)
                    .light(.0)
                    .price(.0)
                    .taste(.0)
                    .comment(null)
                    .build();
        }

        return response(review);
    }

    public ReviewApiResponse addReview(Long cafeId,
                                       Long userId,
                                       Double mood, Double light, Double price, Double taste,
                                       String comment) {

        Review setReview = reviewRepository.findByCafeIdAndUserId(cafeId, userId)
                .map(review -> {
                    review
                            .setMood(mood)
                            .setLight(light)
                            .setPrice(price)
                            .setTaste(taste)
                            .setComment(comment);

                    return review;
                })
                .orElse(null);

        if (setReview == null) {
            setReview = Review.builder()
                    .cafe(cafeRepository.findById(cafeId).orElse(null))
                    .user(userRepository.findById(userId).orElse(null))
                    .mood(mood).light(light).price(price).taste(taste)
                    .comment(comment)
                    .build();

        }

        Review saved = reviewRepository.save(setReview);

        setNewAverage(cafeId);

        return response(saved);

    }

    // review 작성 완료 후 avg 새로운 결과 다시 ScoreSet table에 적용
    private void setNewAverage(Long cafeId) {
        Object avgResult = reviewRepository.findAverageByCafeId(cafeId);
        Object[] avgSet = (Object[]) avgResult;

        scoreSetRepository.findByCafeId(cafeId)
                .map(scoreSet -> {
                    scoreSet
                            .setMood((Double) avgSet[0])
                            .setLight((Double) avgSet[1])
                            .setPrice((Double) avgSet[2])
                            .setTaste((Double) avgSet[3])
                    ;

                    return scoreSet;
                })
                .map(scoreSet -> scoreSetRepository.save(scoreSet));
        // TODO: orElseGet 처리하기

    }

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

package br.org.venturus.fti.web.microservices.composite.model;

/**
 * Created by rodrigoleite on 27/02/16.
 */
public class ReviewSummary {

    private int reviewId;
    private String author;
    private String subject;

    public ReviewSummary(int reviewId, String author, String subject) {
        this.reviewId = reviewId;
        this.author = author;
        this.subject = subject;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }
}

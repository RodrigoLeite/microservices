package br.org.venturus.fti.web.microservices.core.model;

/**
 * Created by rodrigoleite on 27/02/16.
 */
public class Recommendation {

    private int productId;
    private int recommendationId;
    private String author;
    private int rate;
    private String content;

    public Recommendation() {
    }

    public Recommendation(int productId, int recommendationId, String author, int rate, String content) {
        this.productId = productId;
        this.recommendationId = recommendationId;
        this.author = author;
        this.rate = rate;
        this.content = content;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

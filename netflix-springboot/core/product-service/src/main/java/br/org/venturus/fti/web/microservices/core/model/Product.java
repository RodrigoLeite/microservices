package br.org.venturus.fti.web.microservices.core.model;

/**
 * Created by rodrigoleite on 27/02/16.
 */
public class Product {

    private int productId;
    private String name;
    private int weight;

    public Product() {

    }

    public Product(int productId, String name, int weight) {
        this.productId = productId;
        this.name = name;
        this.weight = weight;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

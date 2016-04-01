package br.org.venturus.fti.web.microservices.core.service;

import br.org.venturus.fti.web.microservices.core.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rodrigoleite on 27/02/16.
 */
@RestController
public class ProductService {

    /**
     * Sample usage: curl $HOST:$PORT/product/1
     *
     * @param productId
     * @return
     */
    @RequestMapping("/product/{productId}")
    public Product getProduct(@PathVariable int productId) {

        return new Product(productId, "name", 123);
    }
}

package br.org.venturus.fti.web.microservices.composite.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Created by rodrigoleite on 27/02/16.
 */
@Component
public class RibbonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(RibbonUtil.class);

    @Autowired
    private LoadBalancerClient loadBalancer;

    /**
     * TODO: Complement this with a simpler version without fallback-url!
     *
     * @param serviceId
     * @param fallbackUri
     * @return
     */
    public URI getServiceUrl(String serviceId, String fallbackUri) {
        URI uri = null;
        try {
            ServiceInstance instance = loadBalancer.choose(serviceId);
            uri = instance.getUri();
            LOG.debug("Resolved serviceId '{}' to URL '{}'.", serviceId, uri);

        } catch (RuntimeException e) {
            // Eureka not available, use fallback
            uri = URI.create(fallbackUri);
            LOG.warn("Failed to resolve serviceId '{}'. Fallback to URL '{}'.", serviceId, uri);
        }

        return uri;
    }

    public <T> ResponseEntity<T> createOkResponse(T body) {
        return createResponse(body, HttpStatus.OK);
    }

    public <T> ResponseEntity<T> createResponse(T body, HttpStatus httpStatus) {
        return new ResponseEntity<>(body, httpStatus);
    }
}

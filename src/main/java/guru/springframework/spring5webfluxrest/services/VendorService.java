package guru.springframework.spring5webfluxrest.services;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorService {
    public Flux<Vendor> getAllVendors();
    public Mono<Vendor> getVendorById(String id);
}

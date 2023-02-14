package guru.springframework.spring5webfluxrest.services;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorService {
    public Flux<Vendor> getAllVendors();
    public Mono<Vendor> getVendorById(String id);
    public Mono<Void> createVendor(Publisher<Vendor> vendorStream);
    public Mono<Vendor> updateVendor(String id, Vendor vendor);
}

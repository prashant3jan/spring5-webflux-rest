package guru.springframework.spring5webfluxrest.services;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VendorServiceImpl implements VendorService {
    private VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Flux<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Mono<Vendor> getVendorById(String id) {
        return vendorRepository.findById(id);
    }
}

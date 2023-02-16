package guru.springframework.spring5webfluxrest.services;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.reactivestreams.Publisher;
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
    @Override
    public Mono<Void> createVendor(Publisher<Vendor> vendorStream) {
        return vendorRepository.saveAll(vendorStream).then();
    }
    @Override
    public Mono<Vendor> updateVendor(String id, Vendor vendor) {
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }
    @Override
    public Mono<Vendor> patchVendor(String id, Vendor vendor) {
        Vendor foundVendor = vendorRepository.findById(id).block();
        if(foundVendor.getFirstName()!= vendor.getFirstName()||foundVendor.getLastName()!=vendor.getLastName()){
            if(foundVendor.getFirstName()!= vendor.getFirstName()){
                foundVendor.setFirstName(vendor.getFirstName());
            }
            if (foundVendor.getLastName()!=vendor.getLastName()){
                foundVendor.setLastName(vendor.getLastName());
            }
            return vendorRepository.save(foundVendor);
        }
        return Mono.just(foundVendor);
    }
}

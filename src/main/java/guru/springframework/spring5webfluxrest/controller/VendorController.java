package guru.springframework.spring5webfluxrest.controller;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.services.VendorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VendorController {
    private VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/api/v1/vendors")
    public Flux<Vendor> getAllVendors(){
        return vendorService.getAllVendors();
    }

    @GetMapping("/api/v1/vendors/{id}")
    public Mono<Vendor> getVendorById(@PathVariable String id){
        return vendorService.getVendorById(id);
    }
}

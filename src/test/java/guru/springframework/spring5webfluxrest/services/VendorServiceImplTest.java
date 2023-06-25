package guru.springframework.spring5webfluxrest.services;

import guru.springframework.spring5webfluxrest.controller.VendorController;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;

public class VendorServiceImplTest {
    @Mock
    private VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        vendorRepository= Mockito.mock(VendorRepository.class);
    }
    @Test
    public void getAllVendors(){
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder().firstName("Jim").lastName("connors").build()));
        Vendor vendor1 = Vendor.builder().firstName("first1").lastName("last1").build();
        vendorRepository.save(vendor1);
        Vendor vendor2 = Vendor.builder().firstName("first2").lastName("last2").build();
        vendorRepository.save(vendor2);
        Flux<Vendor> fluxVendor = vendorRepository.findAll();
        BDDMockito.verify(vendorRepository).findAll();

    }

    @Test
    public void getVendorById() {
        BDDMockito.given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(Vendor.builder().build()));
        Vendor vendor = Vendor.builder().firstName("firstName").lastName("lastName").build();
        Mono<Vendor> monoVendor = vendorRepository.save(vendor);
        vendorRepository.findById(vendor.getId());
        BDDMockito.verify(vendorRepository).findById(vendor.getId());
    }
    @Test
    public void createVendor(){
        BDDMockito.given(vendorRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Vendor.builder().firstName("first").lastName("last").build()));
        Mono<Vendor> vendorMono= Mono.just(Vendor.builder().firstName("first").lastName("last").build());
        vendorRepository.saveAll(vendorMono);
        BDDMockito.verify(vendorRepository).saveAll(vendorMono);

    }
    @Test
    public void updateVendor(){
        BDDMockito.given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().firstName("firstName").lastName("lastName").build()));
        Vendor vendor = Vendor.builder().firstName("firstName1").lastName("lastName1").build();
        Mono<Vendor> monoVendor = vendorRepository.save(vendor);
        BDDMockito.verify(vendorRepository).save(any());

    }

    @Test
    public void patchVendor() {
        BDDMockito.given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(Vendor.builder().firstName("first").lastName("last").build()));
        BDDMockito.given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().firstName("first").lastName("first").build()));
        Vendor vendor = Vendor.builder().firstName("first1").lastName("first1").build();
        Mono<Vendor> monoVendor = vendorRepository.save(vendor);
        vendorRepository.findById(vendor.getId());
        BDDMockito.verify(vendorRepository).save(any());
        BDDMockito.verify(vendorRepository).findById(vendor.getId());

    }
}
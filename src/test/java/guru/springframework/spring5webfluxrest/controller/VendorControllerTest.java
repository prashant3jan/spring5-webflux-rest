package guru.springframework.spring5webfluxrest.controller;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
public class VendorControllerTest {
    private VendorService vendorService;
    private VendorController vendorController;
    private WebTestClient webTestClient;

    @Before
    public void setUp() {
       vendorService = BDDMockito.mock(VendorService.class);
       vendorController = new VendorController(vendorService);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void getAllVendors() {
        BDDMockito.given(vendorService.getAllVendors())
                .willReturn(Flux.just(Vendor.builder().firstName("Joe").lastName("Buck").build(),
                Vendor.builder().firstName("Micheal").lastName("Weston").build()));
        webTestClient.get().uri("/api/v1/vendors").exchange()
                .expectBodyList(Vendor.class).hasSize(2);
    }

    @Test
    public void getVendorById() {
        BDDMockito.given(vendorService.getVendorById("newid"))
                .willReturn(Mono.just(Vendor.builder().firstName("Jessie").lastName("Waters").build()));
        webTestClient.get().uri("/api/v1/vendors/newid")
                .exchange().expectBody(Vendor.class);
    }

    @Test
    public void createVendorTest(){
        BDDMockito.given(vendorService.createVendor(any(Publisher.class))).willReturn(Mono.empty());
        Mono<Vendor> vendorToSaveMono = Mono.just(Vendor.builder().firstName("J@").lastName("Brett").build());
        webTestClient.post()
                .uri("/api/v1/vendors")
                .body(vendorToSaveMono,Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void updateVendorTest(){
        BDDMockito.given(vendorService.updateVendor(any(String.class),any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));
        Mono<Vendor> vendorMonoToUpdate = Mono.just(Vendor.builder().build());
        webTestClient.put()
                .uri("/api/v1/vendors/someid")
                .body(vendorMonoToUpdate,Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

}
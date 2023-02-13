package guru.springframework.spring5webfluxrest.controller;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.ArgumentMatchers.any;

public class CategoryControllerTest {
    private WebTestClient webTestClient;

    private CategoryService categoryService;
    private CategoryController categoryController;

    @Before
    public void setUp() throws Exception {
        categoryService = Mockito.mock(CategoryService.class);
        categoryController = new CategoryController(categoryService);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    public void getAllCategories() {
        BDDMockito.given(categoryService.getAllCategories())
                .willReturn(Flux.just(Category.builder().description("cat1").build(),
                        Category.builder().description("cat2").build()));
        webTestClient.get().uri("/api/v1/categories")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }
    @Test
    public void getCategoryById() {
        BDDMockito.given(categoryService.getCategoryById("someid")).willReturn(Mono.just(Category.builder().description("Cat").build()));
        webTestClient.get().uri("/api/v1/categories/someid")
                .exchange()
                .expectBody(Category.class);
    }
    @Test
    public void createCategory(){
        BDDMockito.given(categoryService.createCategory(any(Publisher.class)))
                .willReturn(Mono.empty());
        Mono<Category> catToSaveMono = Mono.just(Category.builder().description("Some cat").build());
        webTestClient.post()
                .uri("/api/v1/categories")
                .body(catToSaveMono,Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void testUpdateCategory(){
        BDDMockito.given(categoryService.updateCategory(any(String.class),any(Category.class)))
                .willReturn(Mono.just(Category.builder().description("Some cat").build()));
        Mono<Category> catToUpdateMono = Mono.just(Category.builder().description("Some cat").build());

        webTestClient.put()
                .uri("/api/v1/categories/asdfasd")
                .body(catToUpdateMono, Category.class)
                .exchange()
                .expectStatus()
                .isOk();

    }
}
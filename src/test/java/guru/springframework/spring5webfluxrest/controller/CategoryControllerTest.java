package guru.springframework.spring5webfluxrest.controller;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import guru.springframework.spring5webfluxrest.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;

import static org.junit.Assert.*;
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
}
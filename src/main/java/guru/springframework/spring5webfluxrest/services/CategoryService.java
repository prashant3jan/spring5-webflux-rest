package guru.springframework.spring5webfluxrest.services;

import guru.springframework.spring5webfluxrest.domain.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {
    public Mono<Category> getCategoryById(String id);
    public Flux<Category> getAllCategories();
}

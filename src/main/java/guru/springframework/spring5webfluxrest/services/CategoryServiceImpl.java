package guru.springframework.spring5webfluxrest.services;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {
    public final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Mono<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Flux<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Void> createCategory(Publisher<Category> categoryStream) {
        return categoryRepository.saveAll(categoryStream).then();
    }
    @Override
    public Mono<Category> updateCategory(String id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }
}

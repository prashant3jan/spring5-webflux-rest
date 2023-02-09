package guru.springframework.spring5webfluxrest;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/v1/categories")
    public Flux<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/api/v1/categories/{id}")
    public Mono<Category> getCategoryById(@PathVariable String id){
        return categoryService.getCategoryById(id);
    }
}

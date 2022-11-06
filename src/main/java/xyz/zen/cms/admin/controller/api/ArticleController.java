package xyz.zen.cms.admin.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.zen.cms.admin.exception.ValidationException;
import xyz.zen.cms.admin.model.dto.ArticleContentDto;
import xyz.zen.cms.admin.model.dto.ArticleInfoDto;
import xyz.zen.cms.admin.model.dto.ArticleUpdateDto;
import xyz.zen.cms.admin.service.ArticleService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
        addArticles();

    }

    private void addArticles() {
        articleService.create(new ArticleContentDto(null, "Spring boot", "Author", "<h1>Spring boot</h1><h2>Content</h2><p><strong>BOLD</strong></p><p><em>italic</em></p><p><u>underscore</u></p>", "spring-boot"));
        articleService.create(new ArticleContentDto(null, "Angular", "Author", "<h1>Angular</h1><h2>Content</h2><p><em>add some content</em></p>", "angular"));
        articleService.create(new ArticleContentDto(null, "Lorem Ipsum", "Cicero", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi a malesuada est. In eu est a turpis tincidunt venenatis a a nisi. Nulla fringilla risus augue, ut euismod lectus placerat id. Cras ut nisi sed mi lacinia tincidunt. Sed dapibus metus non elit egestas volutpat. Vivamus odio augue, efficitur vel ornare ac, elementum laoreet magna. Donec porta ex turpis, eget aliquam magna tempor eu. Nullam a nunc suscipit, condimentum velit vitae, imperdiet nibh. Donec accumsan ultricies eros, sed varius nibh. Vestibulum viverra nisl et sem consequat, id sodales dui fermentum. Sed sollicitudin massa sed nibh rutrum blandit. Sed mauris lorem, convallis in imperdiet eget, malesuada id felis.\n" +
                "\n" +
                "Aliquam placerat fringilla iaculis. Fusce et massa id enim fermentum vehicula. Quisque imperdiet libero ac dui efficitur, sit amet dignissim sem malesuada. Proin nec pharetra felis, sit amet congue risus. Nullam id sollicitudin nibh. Donec vel rhoncus arcu. Nunc in tempor dui. Duis a ornare ipsum. Suspendisse tristique iaculis est, in sodales orci posuere sit amet. Vivamus nisl nibh, placerat non porttitor ut, condimentum accumsan tellus. Proin a consectetur orci. Ut facilisis ante at hendrerit finibus. Cras nulla turpis, mattis id vehicula semper, molestie ac orci.\n" +
                "\n" +
                "Etiam congue egestas quam quis convallis. Nunc imperdiet, dolor ut maximus hendrerit, enim mi pellentesque purus, vitae egestas ligula justo a risus. Vestibulum sed tempus sem, eu molestie massa. Proin molestie volutpat massa, sed egestas erat maximus fermentum. Fusce eget felis vehicula, euismod eros sit amet, venenatis risus. Nullam condimentum tortor eu arcu dictum pretium. Vivamus at scelerisque sem. Praesent hendrerit et lectus vitae gravida.\n" +
                "\n" +
                "Integer vehicula condimentum nisl, sit amet convallis risus. Proin sit amet tellus tempor, congue neque a, tincidunt urna. Donec vitae congue elit. Aenean odio nunc, dapibus et arcu at, luctus condimentum ligula. Aliquam a tortor varius, dapibus lacus nec, blandit ligula. Curabitur pretium efficitur justo, in convallis est condimentum eu. Sed consectetur et nibh vitae egestas. Sed congue et sapien id sodales.\n" +
                "\n" +
                "Nunc ac elit sed lorem ultricies hendrerit. Ut sagittis nisl nec luctus rutrum. Integer turpis massa, sagittis eu tempus at, elementum vel sapien. Vestibulum pellentesque leo purus, et ultrices enim efficitur malesuada. Etiam consequat, justo ac dictum semper, risus neque condimentum massa, sit amet lacinia est felis et nisl. Nullam et purus eget sapien pretium porttitor. Integer euismod urna ac augue pretium, ac dignissim erat tristique. Nulla efficitur placerat nulla, ac efficitur ligula convallis at.",
                "lorem-ipsum"));

    }

    @GetMapping
    public List<ArticleInfoDto> getAllArticles() {
        return articleService.getAll();
    }

    @GetMapping(path = "/count")
    public long getCount() {
        return articleService.getCount();
    }

    @GetMapping(path = "/{id}")
    public ArticleContentDto getContent(@PathVariable Long id) {
        return articleService.getContent(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ArticleContentDto articleDto) {
        articleService.create(articleDto);
    }

    @PutMapping
    public void update(@RequestBody ArticleContentDto articleDto) {
        articleService.update(articleDto);
    }

    @PatchMapping()
    public void publish(@RequestBody ArticleUpdateDto dto) {
        if (Objects.isNull(dto.getId())) {
            throw new ValidationException("Id should be provided");
        }
        articleService.partUpdate(dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        articleService.delete(id);
    }

}

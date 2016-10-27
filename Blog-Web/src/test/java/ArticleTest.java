import com.jy.config.AppConfig;
import com.jy.entity.Article;
import com.jy.entity.User;
import com.jy.service.ArticleService;
import com.jy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@Commit
//@Rollback
@ContextConfiguration(classes = AppConfig.class)
public class ArticleTest {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @Test
    public void saveArticleTest() {

        User user = userService.queryById(User.class, 3L);

        Article article = new Article();
        article.setCreateTime(new Date());
        article.setContent("this is the article content");
        article.setTitle("article title");
        article.setCount(0);
        article.setDeleted(false);
        article.setKeyworks("test");
        article.setLikeCount(0);
        article.setSummary("this is the article summary");
        article.setOwner(user);

        articleService.save(article);

    }
}

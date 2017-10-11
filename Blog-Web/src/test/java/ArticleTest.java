import com.jy.blog.config.AppConfig;
import com.jy.blog.blog.dao.ArticleDao;
import com.jy.blog.entity.Article;
import com.jy.blog.entity.User;
import com.jy.blog.service.ArticleService;
import com.jy.blog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

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

        User user = userService.queryById(3L);

        Article article = new Article();
        article.setCreateTime(new Date());
        article.setContent("this is the article content");
        article.setTitle("article title");
        article.setReadCount(0);
        article.setDeleted(false);
        article.setKeyworks("test");
        article.setLikeCount(0);
        article.setSummary("this is the article summary");
        article.setOwner(user);

        articleService.save(article);

    }

    @Test
    public void queryArticleByParamTest() {
        ArticleDao.ArticleParam param = new ArticleDao.ArticleParam();
        param.setId(1L);
        List<Article> articleList = articleService.findArticleByArticleParam(param);
        System.out.println(articleList);
    }
}

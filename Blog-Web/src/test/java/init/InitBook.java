package init;

import com.jy.config.AppConfig;
import com.jy.entity.Book;
import com.jy.entity.UserProfile;
import com.jy.service.BookService;
import com.jy.service.UserProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class InitBook {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserProfileService userProfileService;

    @Test
    public void initBook() {
        Book book1 = new Book();
        book1.setName("水浒传");
        book1.setCreateTime(new Date());
        book1.setUpdateTime(null);
        bookService.save(book1);

        Book book2 = new Book();
        book2.setName("西游记");
        book2.setCreateTime(new Date());
        book2.setUpdateTime(null);
        bookService.save(book2);

        Book book3 = new Book();
        book3.setName("红楼梦");
        book3.setCreateTime(new Date());
        book3.setUpdateTime(null);
        bookService.save(book3);

        Book book4 = new Book();
        book4.setName("三国演义");
        book4.setCreateTime(new Date());
        book4.setUpdateTime(null);
        bookService.save(book4);

        UserProfile userProfile = userProfileService.findByUsername("362961910");
        userProfileService.saveUserBookLike(userProfile.getOwnerId(), 1L);
        userProfileService.saveUserBookLike(userProfile.getOwnerId(), 2L);
        userProfileService.saveUserBookLike(userProfile.getOwnerId(), 3L);
        userProfileService.saveUserBookLike(userProfile.getOwnerId(), 4L);
    }


}

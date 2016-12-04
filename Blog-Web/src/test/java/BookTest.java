import com.jy.config.AppConfig;
import com.jy.entity.Book;
import com.jy.entity.UserBookLike;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@Commit
//@Rollback
@ContextConfiguration(classes = AppConfig.class)
public class BookTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserProfileService userProfileService;

    @Test
    public void saveBookTest() {
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
    }

    @Test
    public void userAddLikeBookTest() {
        UserProfile userProfile = userProfileService.findByUsername("362961910");
        userProfileService.saveUserBookLike(userProfile.getOwnerId(), 9L);
        userProfileService.saveUserBookLike(userProfile.getOwnerId(), 10L);
        userProfileService.saveUserBookLike(userProfile.getOwnerId(), 11L);
        userProfileService.saveUserBookLike(userProfile.getOwnerId(), 12L);
    }

    @Test
    public void queryUserBookLikeTest() {
        UserProfile userProfile = userProfileService.findByUsername("362961910");
        List<UserBookLike> userBookLikes = userProfileService.findUserBookLikeByOwnerId(userProfile.getOwnerId());
        System.out.println(userBookLikes);
    }

    @Test
    public void removeUserBookLikeTest() {
        UserProfile userProfile = userProfileService.findByUsername("362961910");
        userProfileService.removeUserBookLikeByOwnerIdAndBookId(userProfile.getOwnerId(), 12L);
    }

}

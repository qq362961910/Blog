package init;

import com.jy.blog.config.AppConfig;
import com.jy.blog.entity.Mood;
import com.jy.blog.entity.User;
import com.jy.blog.service.MoodService;
import com.jy.blog.service.UserService;
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
public class InitMood {

    @Autowired
    private UserService userService;
    @Autowired
    private MoodService moodService;

    @Test
    public void initMoods() {

        User owner = userService.findUserByUsername("362961910");

        Mood mood1 = new Mood();
        mood1.setCoverImage("http://localhost:8080/static/images/001.png");
        mood1.setContent("我希望我的爱情是这样的，相濡以沫，举案齐眉，平淡如水。");
        mood1.setCreateTime(new Date());
        mood1.setOwner(owner);
        mood1.setDeleted(Boolean.FALSE);
        moodService.save(mood1);

        Mood mood2 = new Mood();
        mood2.setCoverImage("");
        mood2.setContent("我希望我的爱情是这样的，相濡以沫，举案齐眉，平淡如水。我在岁月中找到他，依靠他，将一生交付给他。做他的妻子，他孩子的母亲，为他做饭，洗衣服，缝一颗掉了的纽扣。然后，我们一起在时光中变老。");
        mood2.setCreateTime(new Date());
        mood2.setOwner(owner);
        mood2.setDeleted(Boolean.FALSE);
        moodService.save(mood2);

        Mood mood3 = new Mood();
        mood3.setCoverImage("http://localhost:8080/static/images/001.png");
        mood3.setContent("我希望我的爱情是这样的，相濡以沫，举案齐眉，平淡如水。我在岁月中找到他，依靠他，将一生交付给他。做他的妻子，他孩子的母亲，为他做饭，洗衣服，缝一颗掉了的纽扣。然后，我们一起在时光中变老。");
        mood3.setCreateTime(new Date());
        mood3.setOwner(owner);
        mood3.setDeleted(Boolean.FALSE);
        moodService.save(mood3);


        Mood mood4 = new Mood();
        mood4.setCoverImage("http://www.yangqq.com/d/file/newstalk/mood/2015-02-01/271e2c42f80489b4dc68e14f8109d631.jpg");
        mood4.setContent("生活的坑都是自己挖的！譬如，听别人讲话，听到最后，耳朵里只会记住两类话：最愿意听的和最不愿意听的。然后，喜欢听的未必化成快乐，但不喜欢听的一定化成了痛苦，其他的都化成了风。");
        mood4.setCreateTime(new Date());
        mood4.setOwner(owner);
        mood4.setDeleted(Boolean.FALSE);
        moodService.save(mood4);

    }
}

package init;

import com.jy.config.AppConfig;
import com.jy.entity.User;
import com.jy.entity.UserProfile;
import com.jy.enums.Sex;
import com.jy.service.UserProfileService;
import com.jy.service.UserService;
import com.jy.util.PasswordUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * 初始化用户数据
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class InitUser {

    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;

    private static final String username = "362961910";

    @Test
    public void initUserTable() throws Exception {
        User user = new User();
        user.setSex(Sex.M);
        user.setAvatar("htttp://www.baidu.com/aaaa.jpg");
        user.setEmail("362961910@qq.com");
        user.setPassword(PasswordUtil.createPassword("123456"));
        user.setUsername(username);
        user.setPhone("18713538092");
        user.setNickname("阿门");
        user.setCreateTime(new Date());
        userService.save(user);
    }

    @Test
    public void initUserProfile() {
        User user = userService.findUserByUsername(username);
        UserProfile profile = new UserProfile();
        profile.setCreateTime(new Date());
        profile.setCompanyName("Join Mind");
        profile.setIdCardNo("130838166110052656");
        profile.setPosition("java engineer");
        profile.setUserId(user.getId());
        userProfileService.save(profile);
    }
}

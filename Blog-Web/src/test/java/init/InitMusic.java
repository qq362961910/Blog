package init;

import com.jy.config.AppConfig;
import com.jy.entity.Music;
import com.jy.entity.UserProfile;
import com.jy.service.MusicService;
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
public class InitMusic {
    @Autowired
    private MusicService musicService;
    @Autowired
    private UserProfileService userProfileService;

    @Test
    public void initMusic() {
        Music music1 = new Music();
        music1.setName("fade");
        music1.setCreateTime(new Date());
        music1.setUpdateTime(null);
        musicService.save(music1);

        Music music2 = new Music();
        music2.setName("等一个晴天");
        music2.setCreateTime(new Date());
        music2.setUpdateTime(null);
        musicService.save(music2);

        Music music3 = new Music();
        music3.setName("求佛");
        music3.setCreateTime(new Date());
        music3.setUpdateTime(null);
        musicService.save(music3);

        Music music4 = new Music();
        music4.setName("一千个伤心的理由");
        music4.setCreateTime(new Date());
        music4.setUpdateTime(null);
        musicService.save(music4);

        UserProfile userProfile = userProfileService.findByUsername("362961910");
        userProfileService.saveUserMusicLike(userProfile.getOwnerId(), 1L);
        userProfileService.saveUserMusicLike(userProfile.getOwnerId(), 2L);
        userProfileService.saveUserMusicLike(userProfile.getOwnerId(), 3L);
        userProfileService.saveUserMusicLike(userProfile.getOwnerId(), 4L);
    }
}

import com.jy.blog.config.AppConfig;
import com.jy.blog.helper.QiNiuHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class QiNiuHelperTest {

    @Autowired
    private QiNiuHelper qiNiuHelper;

    @Test
    public void testUploadImage() {
        System.out.println(qiNiuHelper);
    }

}

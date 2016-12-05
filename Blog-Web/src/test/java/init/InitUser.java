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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 初始化用户数据
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class InitUser {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;

    private static final String username = "362961910";

    @Test
    public void initUserTable() throws Exception {
        User user = new User();
        user.setSex(Sex.M);
        user.setAvatar("http://ogoysg5ko.bkt.clouddn.com/avatar.jpg");
        user.setEmail("362961910@qq.com");
        user.setPassword(PasswordUtil.createPassword("123456"));
        user.setUsername(username);
        user.setPhone("18713538092");
        user.setNickname("阿门");
        user.setCreateTime(new Date());
        userService.save(user);
    }

    @Test
    public void initUserProfile() throws Exception{
        User user = userService.findUserByUsername(username);
        UserProfile profile = new UserProfile();
        profile.setCreateTime(new Date());
        profile.setCompanyName("Join Mind");
        profile.setIdCardNo("130838166110052656");
        profile.setPosition("中级");
        profile.setAddress("北京市昌平区沙河镇");
        profile.setNativePlace("河北秦皇岛");
        profile.setOccupation("java软件开发师");
        profile.setBirthday(sdf.parse("1991-10-10 10:10:10"));
        profile.setRealName("杨建");
        profile.setSelfIntroduction("Just about me\n" +
                "\n" +
                "杨建，男，一个90后草根女站长！09年入行，从业已经有三四年。从搬砖一样的生活方式换成了现在有“单”而居的日子。当然这个单不是单身的单，跟我的职业相比，爱情脱单并不是问题！虽然极尽苛刻的征婚条件但也远不及客户千奇百怪的要求。告别了朝九晚五，躲过了风吹日晒，虽然不再有阶梯式的工资，但是偶尔可以给自己放放假，挽着老公，一起轻装旅行。\n" +
                "\n" +
                "人生就是一个得与失的过程，而我却是一个幸运者，得到的永远比失去的多。生活的压力迫使我放弃了轻松的前台接待，放弃了体面的编辑，换来虽有些蓬头垢面的工作，但是我仍然很享受那些熬得只剩下黑眼圈的日子，因为我在学习使用Photoshop、Flash、Dreamweaver、ASP、PHP、JSP...中激发了兴趣，然后越走越远....\n" +
                "\n" +
                "在这条路上，我要感谢三个人，第一个是我从事编辑的老板，是他给了我充分学习研究div的时间，第二个人是我的老师，如果不是街上的一次偶遇，如果不是因为我正缺钱，我不会去强迫自己做不会的事情，但是金钱的诱惑实在是抵挡不了，于是我选择了“接招”，东拼西凑的把一个网站做好了，当时还堪称佳作的网站至今已尘归尘土归土了。第三个人，我总说他是我的伯乐，因为我当初应聘技术员的时候，我说我什么都不会，但是他却给了我机会，而我就牢牢的把握了那次机会，直到现在如果不是我主动把域名和空间转出来，我会一直霸占着公司资源，免费下去（可我就偏偏不是喜欢爱占便宜的人，总感觉欠了就得还）...\n" +
                "\n" +
                "还要特别感谢一个人，是我的老公。他是我的百科，我不会的，他会，最后我还是不会。博客能做到今天这样，一半都有他的功劳。他不仅仅支持我的事业作为我有力的经济后盾，还毫无怨言的包容我所有工作、生活当中有理无理的坏脾气，曾经我是多么有自己原则的一个人，但是遇到他，打破了我自己毕生坚持的原则，喜欢一句话“冥冥中该来则来，无处可逃”。\n" +
                "\n" +
                "About my blog\n" +
                "\n" +
                "域 名：www.yangqq.com 创建于2011年01月12日 注册域名邀你点评\n" +
                "\n" +
                "服务器：阿里云服务器购买空间参考我的空间配置\n" +
                "\n" +
                "程 序：PHP 帝国CMS7.0");
        profile.setOwnerId(user.getId());
        userProfileService.save(profile);
    }

}

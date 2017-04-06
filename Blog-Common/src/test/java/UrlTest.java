import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlTest {
    public static void main(String[] args) throws Exception{
//        String url = URLDecoder.decode("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx22972e1527274740&redirect_uri=http%3a%2f%2ftest-pay.51play.com%2fwx%2fpay%2f&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect", "utf-8");
//        System.out.println(url);
//
//        String s = URLEncoder.encode("http://test-pay.51play.com/wx/authCallback/" ,"UTF-8");
//        System.out.println(s);

//        String s = URLEncoder.encode("http://" + Configure.CALL_BACK_HOST + "/wx/authCallback/" ,"UTF-8");
//        System.out.println(s);
//
//        String url = URLDecoder.decode(s, "UTF-8");
//        System.out.println(url);

        String s = URLEncoder.encode("test-pay.51play.com" ,"UTF-8");
        System.out.println(s);

        String url = URLDecoder.decode(s, "UTF-8");
        System.out.println(url);

    }
}

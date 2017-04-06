import helper.WxHelper;
import xml.UnionPayRequestConfigure;

public class WeixinHelperTest {
    public static void main(String[] args) throws Exception{

        WxHelper helper = new WxHelper();
        UnionPayRequestConfigure unionPayRequestConfigure = new UnionPayRequestConfigure();
        unionPayRequestConfigure.setAppid("appid");
        unionPayRequestConfigure.setAttach("attach");
        unionPayRequestConfigure.setBody("body");
        unionPayRequestConfigure.setDetail("detail");
        unionPayRequestConfigure.setMchId("mchId");
        unionPayRequestConfigure.setNonceStr("nonceStr");
        unionPayRequestConfigure.setNotifyUrl("notifyUrl");
        unionPayRequestConfigure.setOpenid("openid");
        unionPayRequestConfigure.setOutTradeNo("outTraceNo");
        unionPayRequestConfigure.setSignTypeE("signType");
        unionPayRequestConfigure.setSpbillCreateIp("spbillCreateIp");
        unionPayRequestConfigure.setTotalFee("199.99");
        unionPayRequestConfigure.setTradeType("Ali");

        String xml = helper.buildXml(unionPayRequestConfigure, true);
        System.out.println(xml);

    }

}

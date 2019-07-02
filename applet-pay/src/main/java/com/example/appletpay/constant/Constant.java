package com.example.appletpay.constant;

/**
 * Created by Hyman on 2017/2/27.
 */
public class Constant {

    public static final String DOMAIN = "https://i-test.com.cn";

    public static final String APP_ID = "wxc2684fab7d1b8b76";

    public static final String APP_SECRET = "a1ee1c6cd34adf1c830664a2805f0c85";

    public static final String APP_KEY = "qingwaqingwa12345612432534546345";

    public static final String MCH_ID = "1541268161";  //商户号

    public static final String URL_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static final String URL_NOTIFY = Constant.DOMAIN + "/wxpay/views/payInfo.jsp";

    public static final String TIME_FORMAT = "yyyyMMddHHmmss";

    public static final int TIME_EXPIRE = 2;  //单位是day

}

package com.example.appletpay.controller;

import com.example.appletpay.constant.Constant;
import com.example.appletpay.entity.PayInfo;
import com.example.appletpay.util.CommonUtil;
import com.example.appletpay.util.HttpUtil;
import com.example.appletpay.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @description: 小程序支付
 * @author: ningcs
 * @create: 2019-06-25 11:19
 **/
@RestController
@RequestMapping("/applet/pay")
@Slf4j
public class AppletPayController {


//
//    @ResponseBody
//    @RequestMapping(value = "/prepay", produces = "text/html;charset=UTF-8")
//    public String prePay(String code, ModelMap model, HttpServletRequest request) {
//
//        String content = null;
//        Map map = new HashMap();
//        ObjectMapper mapper = new ObjectMapper();
//
//        boolean result = true;
//        String info = "";
//
//        log.error("\n======================================================");
//        log.error("code: " + code);
//
//        String openId = getOpenId(code);
//        if(StringUtils.isBlank(openId)) {
//            result = false;
//            info = "获取到openId为空";
//        } else {
//            openId = openId.replace("\"", "").trim();
//
//            String clientIP = CommonUtil.getClientIp(request);
//
//            log.error("openId: " + openId + ", clientIP: " + clientIP);
//
//            String randomNonceStr = RandomUtils.generateMixString(32);
//            String prepayId = unifiedOrder(openId, clientIP, randomNonceStr);
//
//            log.error("prepayId: " + prepayId);
//
//            if(StringUtils.isBlank(prepayId)) {
//                result = false;
//                info = "出错了，未获取到prepayId";
//            } else {
//                map.put("prepayId", prepayId);
//                map.put("nonceStr", randomNonceStr);
//            }
//        }
//
//        try {
//            map.put("result", result);
//            map.put("info", info);
//            content = mapper.writeValueAsString(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return content;
//    }
//
//
//    private String getOpenId(String code) {
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constant.APP_ID +
//                "&secret=" + Constant.APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";
//
//        HttpUtil httpUtil = new HttpUtil();
//        try {
//
//            HttpResult httpResult = httpUtil.doGet(url, null, null);
//
//            if(httpResult.getStatusCode() == 200) {
//
//                JsonParser jsonParser = new JsonParser();
//                JsonObject obj = (JsonObject) jsonParser.parse(httpResult.getBody());
//
//                log.error("getOpenId: " + obj.toString());
//
//                if(obj.get("errcode") != null) {
//                    log.error("getOpenId returns errcode: " + obj.get("errcode"));
//                    return "";
//                } else {
//                    return obj.get("openid").toString();
//                }
//                //return httpResult.getBody();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    /**
     * 调用统一下单接口
     * @param openId
     */
    @ResponseBody
    @RequestMapping(value = "/unifiedorder", produces = "text/html;charset=UTF-8")
    private String unifiedOrder(String openId, String clientIP, String randomNonceStr) {

        try {

            String url = Constant.URL_UNIFIED_ORDER;

            PayInfo payInfo = createPayInfo(openId, clientIP, randomNonceStr);
            String md5 = getSign(payInfo);
            payInfo.setSign(md5);

            log.error("md5 value: " + md5);

            String xml = CommonUtil.payInfoToXML(payInfo);
            xml = xml.replace("__", "_").replace("<![CDATA[1]]>", "1");
            //xml = xml.replace("__", "_").replace("<![CDATA[", "").replace("]]>", "");
            log.error(xml);

            StringBuffer buffer = HttpUtil.httpsRequest(url, "POST", xml);
            log.error("unifiedOrder request return body: \n" + buffer.toString());
            Map<String, String> result = CommonUtil.parseXml(buffer.toString());


            String return_code = result.get("return_code");
            if(StringUtils.isNotBlank(return_code) && return_code.equals("SUCCESS")) {

                String return_msg = result.get("return_msg");
                if(StringUtils.isNotBlank(return_msg) && !return_msg.equals("OK")) {
                    //log.error("统一下单错误！");
                    return "";
                }

                String prepay_Id = result.get("prepay_id");
                return prepay_Id;

            } else {
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


//    @ApiOperation(value = "接收微信回调消息")
//    @PostMapping(value = "receive_notify")
//    @ResponseBody
//    public void receiveNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        String line = null;
//        String xmlString = null;
//        BufferedReader reader = request.getReader();
//        StringBuffer inputString = new StringBuffer();
//        while ((line = reader.readLine()) != null) {
//            inputString.append(line);
//        }
//        xmlString = inputString.toString();
//        request.getReader().close();
//
//        log.info("----接收到的数据如下：---" + xmlString);
//        String returnXml = payService.receiveNotify(xmlString,(BatteryPay pay)->{
//            orderService.paySucc(pay);
//        });
//
//        // 处理业务完毕
//        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//        out.write(returnXml.getBytes());
//        out.flush();
//        out.close();
//    }


//    /**
//     * 微信小程序支付成功回调函数
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping(value = "/weixin/callback")
//    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//        while((line = br.readLine()) != null){
//            sb.append(line);
//        }
//        br.close();
//        //sb为微信返回的xml
//        String notityXml = sb.toString();
//        String resXml = "";
//        System.out.println("接收到的报文：" + notityXml);
//
//        Map map = PayUtil.doXMLParse(notityXml);
//
//        String returnCode = (String) map.get("return_code");
//        if("SUCCESS".equals(returnCode)){
//            //验证签名是否正确
//            Map<String, String> validParams = PayUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数
//            String validStr = PayUtil.createLinkString(validParams);//把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
//            String sign = PayUtil.sign(validStr, Configure.getKey(), "utf-8").toUpperCase();//拼装生成服务器端验证的签名
//            // 因为微信回调会有八次之多,所以当第一次回调成功了,那么我们就不再执行逻辑了
//
//            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
//            if(sign.equals(map.get("sign"))){
//                /**此处添加自己的业务逻辑代码start**/
//                // bla bla bla....
//                /**此处添加自己的业务逻辑代码end**/
//                //通知微信服务器已经支付成功
//                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
//                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
//            } else {
//                System.out.println("微信支付回调失败!签名不一致");
//            }
//        }else{
//            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
//                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
//        }
//        System.out.println(resXml);
//        System.out.println("微信支付回调数据结束");
//
//        BufferedOutputStream out = new BufferedOutputStream(
//                response.getOutputStream());
//        out.write(resXml.getBytes());
//        out.flush();
//        out.close();
//    }
//}


    private PayInfo createPayInfo(String openId, String clientIP, String randomNonceStr) {

        Date date = new Date();
        String timeStart = TimeUtils.getFormatTime(date, Constant.TIME_FORMAT);
        String timeExpire = TimeUtils.getFormatTime(TimeUtils.addDay(date, Constant.TIME_EXPIRE), Constant.TIME_FORMAT);

        String randomOrderId = CommonUtil.getRandomOrderId();

        PayInfo payInfo = new PayInfo();
        payInfo.setAppid(Constant.APP_ID);
        payInfo.setMch_id(Constant.MCH_ID);
        payInfo.setDevice_info("WEB");
        payInfo.setNonce_str(randomNonceStr);
        payInfo.setSign_type("MD5");  //默认即为MD5
        payInfo.setBody("JSAPI支付测试");
        payInfo.setAttach("支付测试4luluteam");
        payInfo.setOut_trade_no(randomOrderId);
        payInfo.setTotal_fee(1);
        payInfo.setSpbill_create_ip(clientIP);
        payInfo.setTime_start(timeStart);
        payInfo.setTime_expire(timeExpire);
        payInfo.setNotify_url(Constant.URL_NOTIFY);
        payInfo.setTrade_type("JSAPI");
        payInfo.setLimit_pay("no_credit");
        payInfo.setOpenid(openId);

        return payInfo;
    }

    private String getSign(PayInfo payInfo) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("appid=" + payInfo.getAppid())
                .append("&attach=" + payInfo.getAttach())
                .append("&body=" + payInfo.getBody())
                .append("&device_info=" + payInfo.getDevice_info())
                .append("&limit_pay=" + payInfo.getLimit_pay())
                .append("&mch_id=" + payInfo.getMch_id())
                .append("&nonce_str=" + payInfo.getNonce_str())
                .append("&notify_url=" + payInfo.getNotify_url())
                .append("&openid=" + payInfo.getOpenid())
                .append("&out_trade_no=" + payInfo.getOut_trade_no())
                .append("&sign_type=" + payInfo.getSign_type())
                .append("&spbill_create_ip=" + payInfo.getSpbill_create_ip())
                .append("&time_expire=" + payInfo.getTime_expire())
                .append("&time_start=" + payInfo.getTime_start())
                .append("&total_fee=" + payInfo.getTotal_fee())
                .append("&trade_type=" + payInfo.getTrade_type())
                .append("&key=" + Constant.APP_KEY);

        log.error("排序后的拼接参数：" + sb.toString());

        return CommonUtil.getMD5(sb.toString().trim()).toUpperCase();
    }

}

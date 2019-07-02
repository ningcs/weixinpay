package com.example.appletnewpay.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.appletnewpay.config.Configure;
import com.example.appletnewpay.entity.Template;
import com.example.appletnewpay.entity.TemplateParam;
import com.example.appletnewpay.utils.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: ningcs
 * @create: 2019-07-02 13:51
 **/
@RestController
@RequestMapping("/temp")
public class TempleteController {

    @PostMapping("/get")
    public String  getToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url).append(Configure.getAppID()).append("&")
                .append("secret=").append(Configure.getSecret());
        JSONObject jsonObject = HttpRequest.httpsRequest(String.valueOf(stringBuilder), "GET", null);
        if(jsonObject!=null) {
            String access_token = jsonObject.getString("access_token");
        }

        return "";
    }


//    public  String getTokens(String appId, String secret) {
//        Token accessTocken = getToken(appId, secret, );
//        return accessTocken.getAccessToken();
//    }

    /**
     * 处理要通知的数据（完善模板消息）
     */
    public boolean sendMessage(String openid,String title, String defeated, String instructions,
                               HttpServletRequest request) {
        Template tem=new Template();
        tem.setTemplateId("模板消息id");
        tem.setTopColor("#00DD00");//颜色
        tem.setToUser(openid);//接收方ID
        System.out.println(openid+"=====================");
        //设置超链接（点击模板可跳转相应链接中）
        tem.setUrl("你要跳转的链接");
        List<TemplateParam> paras=new ArrayList<TemplateParam>();//消息主体
        paras.add(new TemplateParam("first",title,"#333")); //标题
        paras.add(new TemplateParam("keyword1",defeated,"#333"));//审核类型
        paras.add(new TemplateParam("keyword2",instructions,"#333"));//时间
//        paras.add(new TemplateParam("keyword3",createds,"#333"));
        paras.add(new TemplateParam("remark","点击此消息查看详情","#333"));
        tem.setTemplateParamList(paras);
        boolean result=sendTemplateMsg(tem);
        System.out.println(result);
        return result;
    }

    /**
     * 发送模板消息
     * @param template
     * @return
     */
    public static boolean sendTemplateMsg(Template template){
        String token = getToken(template);
//           String token = "";
        boolean flag=false;
        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

        requestUrl=requestUrl.replace("ACCESS_TOKEN", token);
        String jsonString = template.toJSON();//把String拼接转为json类型
        JSONObject jsonResult=HttpRequest.httpsRequest(requestUrl, "POST", jsonString);
        if(jsonResult!=null){
            int errorCode=jsonResult.getIntValue("errcode");
            String errorMessage=jsonResult.getString("errmsg");
            if(errorCode==0){
                flag=true;
            }else{
                System.out.println("模板消息发送失败:"+errorCode+","+errorMessage);
                System.out.println(token+"================");
                flag=false;
            }
        }
        return flag;
    }

    /**
     *获取token
     * @param template
     * @return
     */
    public static String getToken(Template template){
        String requestUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=你的appid&secret=你的appid对应的秘钥";
        JSONObject jsonResult=HttpRequest.httpsRequest(requestUrl, "POST", template.toJSON());
        if(jsonResult!=null){
            String access_token=jsonResult.getString("access_token");
            return access_token;
        }else{
            return "";
        }
    }
}
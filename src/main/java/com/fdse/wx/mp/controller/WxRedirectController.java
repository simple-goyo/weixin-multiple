package com.fdse.wx.mp.controller;

import com.fdse.wx.mp.config.WxMpConfiguration;
import com.fdse.wx.mp.constant.UrlConstant;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Edward
 */
@Controller
@RequestMapping("/wx/redirect/{appid}")
public class WxRedirectController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{redirectUrl}")
    public String allService(RedirectAttributes attr , @PathVariable String appid, @PathVariable String redirectUrl, @RequestParam String code) throws WxErrorException {
        WxMpService mpService = WxMpConfiguration.getMpServices().get(appid);
        WxMpOAuth2AccessToken accessToken = mpService.oauth2getAccessToken(code);
        WxMpUser user = mpService.oauth2getUserInfo(accessToken, null);
        String openId=user.getOpenId();
        String getUserIdURL = UrlConstant.getAppBackEndServiceURL(UrlConstant.APP_BACK_END_GET_USERID_BY_OPENID);
        getUserIdURL+="openId="+openId;
        int userId = restTemplate.getForEntity(getUserIdURL, Integer.class).getBody();
        if(userId==-1){
            attr.addAttribute("openId",openId);
            String gzhRegisterURL = UrlConstant.getAppBackEndServiceURL(UrlConstant.APP_BACK_END_WX_REGISTER_HTML_);
            String redirectURL="redirect:"+gzhRegisterURL;
            return redirectURL;
        }else {
            attr.addAttribute("userId",userId);
            String url="";
            if("all_service".equals(redirectUrl)){
                url = UrlConstant.getAppBackEndServiceURL(UrlConstant.APP_BACK_END_WX_ALL_SERVICE_HTML_);
            }else if("my_service".equals(redirectUrl)){
                url = UrlConstant.getAppBackEndServiceURL(UrlConstant.APP_BACK_END_WX_MY_SERVICE_HTML_);
            }
            String redirectURL="redirect:"+url;
            return redirectURL;
        }
    }


}

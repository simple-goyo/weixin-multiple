package com.fdse.wx.mp.controller;

import com.fdse.wx.mp.config.WxMpConfiguration;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static me.chanjar.weixin.common.api.WxConsts.MenuButtonType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@RestController
@RequestMapping("/wx/msg/{appid}")
public class WxMsgController {

    /**
     * <pre>
     * 自定义菜单创建接口
     * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
     * 如果要创建个性化菜单，请设置matchrule属性
     * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
     * </pre>
     *
     * @return 如果是个性化菜单，则返回menuid，否则返回null
     */
    @RequestMapping("/sendTemplateMsg")
    public String sendTemplateMsg(HttpServletRequest request,@PathVariable String appid) throws WxErrorException {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().toUser("o1l-Y1KcDyhECFiV15WIql7-MJzk")
                .templateId("KnzsPgO70DMEHBBIdIpro5Zbdg6MS5fat00ivyom0u0").url("https://www.jianshu.com/p/cb69ce1e637f").build();
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss.SSS");
        templateMessage.addData(new WxMpTemplateData("description", request.getParameter("description"),"#FF00FF"));
        templateMessage.addData(new WxMpTemplateData("location",request.getParameter("location"),"#FF00FF"));
        templateMessage.addData(new WxMpTemplateData("duration",request.getParameter("duration"),"#FF00FF"));
        templateMessage.addData(new WxMpTemplateData("bonus",request.getParameter("bonus"),"#FF00FF"));
        templateMessage.addData(new WxMpTemplateData("person", request.getParameter("person"), "#FF00FF"));
        String msgId=WxMpConfiguration.getMpServices().get(appid).getTemplateMsgService().sendTemplateMsg(templateMessage);

        return msgId;
//        return WxMpConfiguration.getMpServices().get(appid).getMenuService().menuCreate(menu);
    }

    @RequestMapping("/sendShortMsg")
    public String sendShortMsg(HttpServletRequest request,@PathVariable String appid) throws WxErrorException {
        String openId=request.getParameter("openId");
        String wxContent=request.getParameter("wxContent");
        String wxUrl=request.getParameter("wxUrl");
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().toUser(openId)
                .templateId("2BREzDujeb9dGu7QZbpXnh1oG5M0j5-AhHeH2KQtGts").url(wxUrl).build();
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss.SSS");
        templateMessage.addData(new WxMpTemplateData("wxContent", wxContent,"#FF00FF"));
        String msgId=WxMpConfiguration.getMpServices().get(appid).getTemplateMsgService().sendTemplateMsg(templateMessage);

        return msgId;
//        return WxMpConfiguration.getMpServices().get(appid).getMenuService().menuCreate(menu);
    }

    @GetMapping("/create")
    public String menuCreateSample(@PathVariable String appid) throws WxErrorException, MalformedURLException {

        return "";
    }


}

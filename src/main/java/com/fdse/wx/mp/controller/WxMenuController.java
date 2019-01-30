package com.fdse.wx.mp.controller;

import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fdse.wx.mp.config.WxMpConfiguration;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;

import static me.chanjar.weixin.common.api.WxConsts.MenuButtonType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@RestController
@RequestMapping("/wx/menu/{appid}")
public class WxMenuController {

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
    @PostMapping("/create")
    public String menuCreate(@PathVariable String appid, @RequestBody WxMenu menu) throws WxErrorException {
        return WxMpConfiguration.getMpServices().get(appid).getMenuService().menuCreate(menu);
    }

    @GetMapping("/create")
    public String menuCreateSample(@PathVariable String appid) throws WxErrorException, MalformedURLException {
        WxMenu menu = new WxMenu();

        WxMenuButton button1 = new WxMenuButton();
        button1.setName("服务");
        WxMenuButton button2 = new WxMenuButton();
        button2.setName("众包");
        WxMenuButton button3 = new WxMenuButton();
        button3.setType(MenuButtonType.CLICK);
        button3.setName("平台介绍");
        button3.setKey("V1001_TODAY_MUSIC");



        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        menu.getButtons().add(button3);

        WxMenuButton button11 = new WxMenuButton();
        button11.setType(MenuButtonType.VIEW);
        button11.setName("调用服务");

        WxMenuButton button12 = new WxMenuButton();
        button12.setType(MenuButtonType.VIEW);
        button12.setName("我的服务");

        WxMenuButton button21 = new WxMenuButton();
        button21.setType(MenuButtonType.VIEW);
        button21.setName("可接众包");

        WxMenuButton button22 = new WxMenuButton();
        button22.setType(MenuButtonType.VIEW);
        button22.setName("我的众包");

        ServletRequestAttributes servletRequestAttributes =
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            URL requestURL = new URL(request.getRequestURL().toString());

            String url11 = WxMpConfiguration.getMpServices().get(appid)
                .oauth2buildAuthorizationUrl(
                    String.format("%s://%s/wm/wx/redirect/%s/all_service", requestURL.getProtocol(), requestURL.getHost(), appid),
                    WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
            button11.setUrl(url11);

            String url12 = WxMpConfiguration.getMpServices().get(appid)
                .oauth2buildAuthorizationUrl(
                    String.format("%s://%s/wm/wx/redirect/%s/my_service", requestURL.getProtocol(), requestURL.getHost(), appid),
                    WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
            button12.setUrl(url12);

            String url21 = WxMpConfiguration.getMpServices().get(appid)
                    .oauth2buildAuthorizationUrl(
                            String.format("%s://%s/wx/redirect/%s/all_crowdsourcing", requestURL.getProtocol(), requestURL.getHost(), appid),
                            WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
            button21.setUrl(url21);

            String url22 = WxMpConfiguration.getMpServices().get(appid)
                    .oauth2buildAuthorizationUrl(
                            String.format("%s://%s/wx/redirect/%s/my_crowdsourcing", requestURL.getProtocol(), requestURL.getHost(), appid),
                            WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
            button22.setUrl(url22);
        }

        button1.getSubButtons().add(button11);
        button1.getSubButtons().add(button12);
        button2.getSubButtons().add(button21);
        button2.getSubButtons().add(button22);

        return WxMpConfiguration.getMpServices().get(appid).getMenuService().menuCreate(menu);
    }

    /**
     * <pre>
     * 自定义菜单创建接口
     * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
     * 如果要创建个性化菜单，请设置matchrule属性
     * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
     * </pre>
     *
     * @param json
     * @return 如果是个性化菜单，则返回menuid，否则返回null
     */
    @PostMapping("/createByJson")
    public String menuCreate(@PathVariable String appid, @RequestBody String json) throws WxErrorException {
        return WxMpConfiguration.getMpServices().get(appid).getMenuService().menuCreate(json);
    }

    /**
     * <pre>
     * 自定义菜单删除接口
     * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015&token=&lang=zh_CN
     * </pre>
     */
    @GetMapping("/delete")
    public void menuDelete(@PathVariable String appid) throws WxErrorException {
        WxMpConfiguration.getMpServices().get(appid).getMenuService().menuDelete();
    }

    /**
     * <pre>
     * 删除个性化菜单接口
     * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
     * </pre>
     *
     * @param menuId 个性化菜单的menuid
     */
    @GetMapping("/delete/{menuId}")
    public void menuDelete(@PathVariable String appid, @PathVariable String menuId) throws WxErrorException {
        WxMpConfiguration.getMpServices().get(appid).getMenuService().menuDelete(menuId);
    }

    /**
     * <pre>
     * 自定义菜单查询接口
     * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014&token=&lang=zh_CN
     * </pre>
     */
    @GetMapping("/get")
    public WxMpMenu menuGet(@PathVariable String appid) throws WxErrorException {
        return WxMpConfiguration.getMpServices().get(appid).getMenuService().menuGet();
    }

    /**
     * <pre>
     * 测试个性化菜单匹配结果
     * 详情请见: http://mp.weixin.qq.com/wiki/0/c48ccd12b69ae023159b4bfaa7c39c20.html
     * </pre>
     *
     * @param userid 可以是粉丝的OpenID，也可以是粉丝的微信号。
     */
    @GetMapping("/menuTryMatch/{userid}")
    public WxMenu menuTryMatch(@PathVariable String appid, @PathVariable String userid) throws WxErrorException {
        return WxMpConfiguration.getMpServices().get(appid).getMenuService().menuTryMatch(userid);
    }

    /**
     * <pre>
     * 获取自定义菜单配置接口
     * 本接口将会提供公众号当前使用的自定义菜单的配置，如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置。
     * 请注意：
     * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自定义菜单配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
     * 2、本接口与自定义菜单查询接口的不同之处在于，本接口无论公众号的接口是如何设置的，都能查询到接口，而自定义菜单查询接口则仅能查询到使用API设置的菜单配置。
     * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
     * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
     * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
     *  接口调用请求说明:
     * http请求方式: GET（请使用https协议）
     * https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN
     * </pre>
     */
    @GetMapping("/getSelfMenuInfo")
    public WxMpGetSelfMenuInfoResult getSelfMenuInfo(@PathVariable String appid) throws WxErrorException {
        return WxMpConfiguration.getMpServices().get(appid).getMenuService().getSelfMenuInfo();
    }
}

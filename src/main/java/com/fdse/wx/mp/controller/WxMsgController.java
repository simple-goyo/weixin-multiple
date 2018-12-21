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
    @PostMapping("/sendTemplateMsg")
    public String sendTemplateMsg(@PathVariable String appid) throws WxErrorException {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().toUser("o1l-Y1KcDyhECFiV15WIql7-MJzk")
                .templateId("OEtHMkILLVI0xcqeDKq_aU3tZIXyzVBDesSN3HuLIIo").url("https://www.jianshu.com/p/cb69ce1e637f").build();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS");
        templateMessage.addData(new WxMpTemplateData("first", dateFormat.format(new Date()), "#FF00FF"));
        templateMessage.addData(new WxMpTemplateData("keynote1",RandomStringUtils.randomAlphanumeric(100), "#FF00FF"));
        templateMessage.addData(new WxMpTemplateData("keynote2",RandomStringUtils.randomAlphanumeric(100), "#FF00FF"));
        templateMessage.addData(new WxMpTemplateData("keynote3",RandomStringUtils.randomAlphanumeric(100), "#FF00FF"));
        templateMessage.addData(new WxMpTemplateData("remark", "eq", "#FF00FF"));
        String msgId=WxMpConfiguration.getMpServices().get(appid).getTemplateMsgService().sendTemplateMsg(templateMessage);

        return msgId;
//        return WxMpConfiguration.getMpServices().get(appid).getMenuService().menuCreate(menu);
    }

    @GetMapping("/create")
    public String menuCreateSample(@PathVariable String appid) throws WxErrorException, MalformedURLException {

        return "";
    }


}

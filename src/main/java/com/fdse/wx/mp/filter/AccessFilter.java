package com.fdse.wx.mp.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     author : shenbiao
 *     e-mail : 1105125966@qq.com
 *     time   : 2018/10/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Component
public class AccessFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public String filterType() {
        //前置过滤器
        return "pre";
    }

    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器，true代表需要过滤
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        Enumeration<String> names = request.getParameterNames();
        StringBuilder params = new StringBuilder("?");
        if( request.getMethod().equals("GET") ) {
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                params.append(name);
                params.append("=");
                params.append(request.getParameter(name));
                params.append("&");
            }
        }
        //获取传来的参数code
        Object accessToken = request.getParameter("code");
        if (accessToken == null) {
            log.warn("code is empty");
            //过滤该请求，不往下级服务去转发请求，到此结束
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"code is empty!\"}");
            return null;
        }
        //如果有token，则进行路由转发
        log.info("access token ok");

        // 在json参数中添加 userId
        try {
            InputStream in = (InputStream) ctx.get("requestEntity");
            if (in == null) {
                in = ctx.getRequest().getInputStream();
            }
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            System.out.println("body:" + body);
            // 如果body为空初始化为空json
            if (StringUtils.isBlank(body)) {
                body = "{}";
            }
            body = "{}";
            JSONObject json = JSONObject.parseObject(body);

            json.put("userId", "2121");
            String newBody = json.toString();
            System.out.println("newBody:" + newBody);
            // 关键步骤，一定要get一下,下面才能取到值requestQueryParams
            request.getParameterMap();
            Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
            List<String> arrayList = new ArrayList<>();
            arrayList.add(2132 + "");
            requestQueryParams.put("userId",arrayList);
            ctx.setRequestQueryParams(requestQueryParams);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //这里return的值没有意义，zuul框架没有使用该返回值
        return null;
    }

}

package com.fdse.wx.mp.constant;


/**
 * <pre>
 *     author : shenbiao
 *     e-mail : 1105125966@qq.com
 *     time   : 2018/08/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class UrlConstant {

    /**
     * App后端url
     */
//    public static final String APP_BACK_END_IP = "119.29.194.211";//142,148给前端返回IP地址
    public static final String APP_BACK_END_IP = "192.168.1.120";//142,148给前端返回IP地址
    //    public static final String APP_BACK_END_IP = "www.fudanse.club";//142,148给前端返回IP地址
//    public static final String APP_BACK_END_PORT = "80/sc/";
    public static final String APP_BACK_END_PORT = "8080/";

    //微信注册用户信息页面
    public static final String APP_BACK_END_WX_REGISTER_HTML_ = "wxr.html";

    //微信调用服务页面
    public static final String APP_BACK_END_WX_ALL_SERVICE_HTML_ = "wx_all_service.html";

    //微信我的服务页面
    public static final String APP_BACK_END_WX_MY_SERVICE_HTML_ = "wx_my_service.html";

    //通过openid获取userid
    public static final String APP_BACK_END_GET_USERID_BY_OPENID = "user/getUserIdByOpenId/?";

    public static final String APP_BACK_END_USER_LOGIN_SERVICE = "user/login";

    public static final String APP_BACK_END_USER_TEST = "user/test";

    //保存位置信息
    public static final String APP_BACK_END_USER_SAVE_LOCATION = "user/saveLocation";

    //测试用--获取owls
    public static final String APP_BACK_END_TASKS_GET_OWLS = "task/getOwls";

    //测试用--获取bpmn
    public static final String APP_BACK_END_TASKS_GET_BPMN = "task/getBPMN";

    //保存任务
    public static final String APP_BACK_END_TASKS_SAVE_TASK = "task/saveTask";

    //获取所有任务列表
    public static final String APP_BACK_END_TASKS_GET_ONGOING_TASKS = "task/getOngoingTasks";

    public static String getAppBackEndServiceURL(String service) {
        String serviceURL = String.format("http://%s:%s/%s", APP_BACK_END_IP, APP_BACK_END_PORT, service);
        return serviceURL;
    }

    public static final String APP_BACK_END_CLOUD_IP = "www.fudanse.club";//142,148给前端返回IP地址
    public static final String APP_BACK_END_CLOUD_PORT = "80/sc/";

    public static String getAppBackEndCloudServiceURL(String service) {
        String serviceURL = String.format("http://%s:%s/%s", APP_BACK_END_CLOUD_IP, APP_BACK_END_CLOUD_PORT, service);
        return serviceURL;
    }

    /**
     * 本体库平台url
     */
    public static final String ONTOLOGY_IP = "47.100.23.182";//142
    public static final String ONTOLOGY_PORT = "8004";

    //获取有没有人服务
    public static final String ONTOLOGY_GET_SERVICE_OWLS = "query/compositeServiceQuery?";

    //获取喝水服务
    public static final String ONTOLOGY_GET_DrinkWaterService_OWLS = "query/compositeServiceQuery?serviceName=DrinkWaterService";

    //获取服务列表
    public static final String ONTOLOGY_GET_SERVICE_LIST = "query/serviceInfoQuery/all";

    public static final String ONTOLOGY_GET_SERVICE_LIST_BY_ROLE = "query/serviceInfoQuery/byAuthority";


    public static String getOntologyServiceURL(String service) {
        String serviceURL = String.format("http://%s:%s/%s", ONTOLOGY_IP, ONTOLOGY_PORT, service);
        return serviceURL;
    }

    /**
     * 流程执行引擎url
     */
    public static final String ACTIVITI_IP = "123.207.47.155";//142
    //    public static final String ACTIVITI_IP = "192.168.1.108";//142
    public static final String ACTIVITI_PORT = "8003";

    //用户登录
    public static final String ACTIVITI_RUN_BPMN_ENGINE = "runBpmnEngine";

    public static String getActivitiServiceURL(String service) {
        String serviceURL = String.format("http://%s:%s/%s", ACTIVITI_IP, ACTIVITI_PORT, service);
        return serviceURL;
    }

    /**
     * 众包平台url
     */
    public static final String CROWD_BACK_END_IP = "192.168.1.168";//142,148给前端返回IP地址
    public static final String CROWD_BACK_END_PORT = "8080";


    //微信所有众包
    public static final String CROWD_BACK_END_WX_ALL_CROWDSOURCING_ = "/";

    //微信我的众包
    public static final String CROWD_BACK_END_WX_MY_CROWDSOURCING = "wx_my_service.html";


    public static String getCrowdBackEndServiceURL(String service) {
        String serviceURL = String.format("http://%s:%s/%s", CROWD_BACK_END_IP, CROWD_BACK_END_PORT, service);
        return serviceURL;
    }


    /**
     * 定时任务平台
     */
    public static final String QUARTZ_IP = "119.29.194.211";//142
    public static final String QUARTZ_PORT = "8080";

    //保存定时任务
    public static final String QUARTZ_SAVE_SCHEDULE_JOB = "save-schedule-job.shtml";

    public static String getQuartzServiceURL(String service) {
        String serviceURL = String.format("http://%s:%s/%s", QUARTZ_IP, QUARTZ_PORT, service);
        return serviceURL;
    }

    /**
     * 微信平台
     */
    public static final String WECHATE_IP = "119.29.194.211";//142
    public static final String WECHATE_PORT = "8080";

    //保存定时任务
    public static final String WECHATE_SEND_MESSAGE = "sendMessage";

    public static String getWeChatServiceURL(String service) {
        String serviceURL = String.format("http://%s:%s/%s", WECHATE_IP, WECHATE_PORT, service);
        return serviceURL;
    }

}

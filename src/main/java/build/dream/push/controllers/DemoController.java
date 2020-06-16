package build.dream.push.controllers;

import build.dream.common.utils.ApplicationHandler;
import build.dream.common.utils.LogUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("demo/index");

        String instanceId = "post-cn-45918p55a02";
        String groupId = "GID-eleme_message";
        String accessKeyId = "";
        String accessKeySecret = "";
        String clientId = groupId + "@@@" + UUID.randomUUID().toString();

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("host", "post-cn-45918p55a02.mqtt.aliyuncs.com");
        model.put("port", 80);
        model.put("topic", "_eleme_message");
        model.put("useTLS", false);
        model.put("clientId", clientId);
        model.put("username", "Signature|" + accessKeyId + "|" + instanceId);
        model.put("password", Base64.encodeBase64String(HmacUtils.hmacSha1(accessKeySecret, clientId)));

        LogUtils.info(ApplicationHandler.getRemoteAddress() + "@@@" + clientId);
        modelAndView.addAllObjects(model);
        return modelAndView;
    }
}

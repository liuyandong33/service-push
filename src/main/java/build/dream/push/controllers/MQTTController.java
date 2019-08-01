package build.dream.push.controllers;

import build.dream.common.utils.ApplicationHandler;
import build.dream.common.utils.MQTTUtils;
import build.dream.push.constants.Constants;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/mqtt")
public class MQTTController {
    @RequestMapping(value = "/publish")
    @ResponseBody
    public String publish() throws MqttException {
        Map<String, String> requestParameters = ApplicationHandler.getRequestParameters();
        String clientId = requestParameters.get("clientId");
        String message = requestParameters.get("message");

        String topic = "_eleme_message/p2p/" + clientId;
        MqttMessage mqttMessage = new MqttMessage(message.getBytes(Constants.CHARSET_UTF_8));
        mqttMessage.setQos(0);
        MQTTUtils.publish(topic, mqttMessage);
        return Constants.SUCCESS;
    }
}

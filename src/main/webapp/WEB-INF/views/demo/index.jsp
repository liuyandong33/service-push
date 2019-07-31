<%--
  Created by IntelliJ IDEA.
  User: liuyandong
  Date: 2018-06-05
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0，minimum-scale=1.0">
    <title>测试MQTT</title>
    <script type="text/javascript" src="../libraries/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.1/mqttws31.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.js"></script>
    <script type="text/javascript">
        instanceId = "post-cn-45918p55a02";//实例 ID，购买后从控制台获取
        host = "post-cn-45918p55a02.mqtt.aliyuncs.com";// 设置当前用户的接入点域名，接入点获取方法请参考接入准备章节文档，先在控制台创建实例
        port = 80;//WebSocket 协议服务端口，如果是走 HTTPS，设置443端口
        topic = "_eleme_message";//需要操作的 Topic,第一级父级 topic 需要在控制台申请
        useTLS = false;//是否走加密 HTTPS，如果走 HTTPS，设置为 true
        accessKey = "LTAI13Q9MtL90vHh";//账号的 AccessKey，在阿里云控制台查看
        secretKey = "xL9bYrZ6MqyzYkAwjwGqQE4NGaDPlt";//账号的的 SecretKey，在阿里云控制台查看
        cleansession = true;
        groupId = "GID-eleme_message";//MQTT GroupID,创建实例后从 MQTT 控制台创建
        clientId = groupId + "${deviceId}";//GroupId@@@DeviceId，由控制台创建的 Group ID 和自己指定的 Device ID 组合构成
        var mqtt;
        var reconnectTimeout = 2000;
        var username = "Signature|" + accessKey + "|" + instanceId;//username和 Password 签名模式下的设置方法，参考文档 https://help.aliyun.com/document_detail/48271.html?spm=a2c4g.11186623.6.553.217831c3BSFry7
        var password = CryptoJS.HmacSHA1(clientId, secretKey).toString(CryptoJS.enc.Base64);

        function MQTTconnect() {
            mqtt = new Paho.MQTT.Client(
                host,//MQTT 域名
                port,//WebSocket 端口，如果使用 HTTPS 加密则配置为443,否则配置80
                clientId//客户端 ClientId
            );
            var options = {
                timeout: 3,
                onSuccess: onConnect,
                mqttVersion: 4,
                onFailure: function (message) {
                    setTimeout(MQTTconnect, reconnectTimeout);
                }
            };
            mqtt.onConnectionLost = onConnectionLost;
            mqtt.onMessageArrived = onMessageArrived;
            if (username != null) {
                options.userName = username;
                options.password = password;
                options.useSSL = useTLS;//如果使用 HTTPS 加密则配置为 true
            }
            mqtt.connect(options);
        }

        function onConnect() {
            // Connection succeeded; subscribe to our topic
            mqtt.subscribe(topic, {qos: 0});
            message = new Paho.MQTT.Message("Hello mqtt!!");//set body
            message.destinationName = topic;// set topic
            //发送 P2P 消息，topic 设置方式参考https://help.aliyun.com/document_detail/96176.html?spm=a2c4g.11186623.6.586.694f7cb4oookL7
            message = new Paho.MQTT.Message("Hello mqtt P2P Msg!!");//set body
            message.destinationName = topic + "/p2p/" + clientId;// set topic
            mqtt.send(message);
        }

        function onConnectionLost(response) {
            setTimeout(MQTTconnect, reconnectTimeout);
        };

        function onMessageArrived(message) {
            var topic = message.destinationName;
            var payload = message.payloadString;
            alert("recv msg : " + topic + "   " + payload);
        };
        MQTTconnect();
    </script>
</head>
<body>
<div id="message"></div>
</body>
</html>
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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0，minimum-scale=1.0">
    <title>测试MQTT</title>
    <script type="text/javascript" src="../libraries/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.1/mqttws31.js"></script>
    <script type="text/javascript">
        host = "${host}";
        port = ${port};
        topic = "${topic}";
        useTLS = ${useTLS};
        clientId = "${clientId}";
        var mqtt;
        var reconnectTimeout = 2000;
        var username = "${username}";
        var password = "${password}";
        var subTopic = topic + "/p2p/" + clientId;

        function MQTTconnect() {
            mqtt = new Paho.MQTT.Client(
                host,
                port,
                clientId
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
                options.useSSL = useTLS;
            }
            mqtt.connect(options);
        }

        function onConnect() {
            mqtt.subscribe(topic, {qos: 0});
            // message = new Paho.MQTT.Message("Hello mqtt!!");
            // message.destinationName = topic;
            message = new Paho.MQTT.Message("Hello mqtt P2P Msg!!");
            message.destinationName = subTopic;
            mqtt.send(message);
        }

        function onConnectionLost(response) {
            setTimeout(MQTTconnect, reconnectTimeout);
        }

        function onMessageArrived(message) {
            var topic = message.destinationName;
            var payload = message.payloadString;
            alert("recv msg : " + topic + "   " + payload);
        }

        MQTTconnect();
    </script>
</head>
<body>
<div id="message"></div>
</body>
</html>
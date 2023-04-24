package com.example.myiotapp_java;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

//import org.eclipse.paho.android.service.MqttAndroidClient;

import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import info.mqtt.android.service.MqttAndroidClient;
import android.content.SharedPreferences;

public class MQTTHelper {
    public MqttAndroidClient mqttAndroidClient;

    public final String[] arrayTopics = {
            "vietkhang/feeds/cambien1",
            "vietkhang/feeds/cambien2",
            "vietkhang/feeds/nutnhan1",
            "vietkhang/feeds/nutnhan2"};
    public String clientId, username, password, serverUri;

    //public MQTTHelper(Context context, String serverUri, String clientId, String username, String password){
    public MQTTHelper(Context context){
        MQTTConfig MqttConfig = new MQTTConfig();
        this.serverUri = MqttConfig.serverUri;
        this.clientId = MqttConfig.clientId;
        this.username = MqttConfig.username;
        this.password = MqttConfig.password;
        mqttAndroidClient = new MqttAndroidClient(context, serverUri, clientId);
        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.w("mqtt", s);
            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }

//            @Override
//            public void publishToTopic (String topic, MqttMessage mqttMessage) throws Exception {
//
//            }
        });
        connect();
    }

    public void setCallback(MqttCallbackExtended callback) {
        mqttAndroidClient.setCallback(callback);
    }

    private void connect(){
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());

        //try {

            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d("Mqtt", "Successfully connected to: " + serverUri);
                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                    subscribeToTopic();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.w("Mqtt", "Failed to connect to: " + serverUri + exception.toString());
                }
            });


        //} catch (MqttException ex){
            //ex.printStackTrace();
        //}
    }

    private void subscribeToTopic() {
        for(int i = 0; i < arrayTopics.length; i++) {
            mqttAndroidClient.subscribe(arrayTopics[i], 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d("TEST", "Subscribed!");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d("TEST", "Subscribed fail!");
                }
            });

        }
    }

    public void publishToTopic (String topic, MqttMessage message){
        mqttAndroidClient.publish(topic, message.getPayload(), 0, false, null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.d("PUB", "Published successfully!");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.d("PUB", "Published fail!");
            }
        });
    }

}

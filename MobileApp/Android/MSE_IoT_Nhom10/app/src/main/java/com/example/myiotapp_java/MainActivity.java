package com.example.myiotapp_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    MQTTHelper mqttHelper;
    TextView txtTemp, txtHumi, txtConn, txtLastUpdate;
    Switch switchLight, switchFan;
    boolean isMqttConnected = false;
    Button bttConnect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTemp = findViewById(R.id.txtTemperature);
        txtHumi = findViewById(R.id.txtHumidity);
        txtConn = findViewById(R.id.txtConnection);
        txtLastUpdate = findViewById(R.id.txtLastUpdate);
        switchLight = findViewById(R.id.switchLight);
        switchFan = findViewById(R.id.switchFan);
        bttConnect = findViewById(R.id.btnConnect);
        isMqttConnected = false;
    }

    public void connectMQTT(View view){
        txtConn.setText("Connecting to server...");
        startMQTT();
    }

    public void controlLight (View view){
        MqttMessage message = new MqttMessage();
        String payload = switchLight.isChecked()? "1" : "0";
        message.setPayload(payload.getBytes());
        mqttHelper.publishToTopic("vietkhang/feeds/nutnhan1", message);
    }
    public void controlFan (View view){
        MqttMessage message = new MqttMessage();
        String payload = switchFan.isChecked()? "1" : "0";
        message.setPayload(payload.getBytes());
        mqttHelper.publishToTopic("vietkhang/feeds/nutnhan2", message);
    }

    public void startMQTT(){
        mqttHelper = new MQTTHelper(this);
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                isMqttConnected = true;
                txtConn.setText("Connected to: " + serverURI);
                txtConn.setTextColor(Color.BLUE);
                bttConnect.setClickable(false);
                bttConnect.setActivated(false);
            }

            @Override
            public void connectionLost(Throwable cause) {
                Log.e("MQTT", "Connection lost with Adafruit.io MQTT server");
                isMqttConnected = false;
                txtConn.setText("Connection Lost!");
                txtConn.setTextColor(Color.RED);
                bttConnect.setClickable(true);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.w("MQTT", message.toString());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                txtLastUpdate.setText("Last update: " + dtf.format(now));
                if(topic.contains("cambien1")){
                    txtTemp.setText(message.toString() + "°C");
                    if (Integer.valueOf(message.toString()) > 35){
                        txtTemp.setTextColor(Color.RED);
                    }
                    else txtTemp.setTextColor(Color.BLACK);
                } else if (topic.contains("cambien2")) {
                    txtHumi.setText(message.toString() + "%");
                    if (Integer.valueOf(message.toString()) > 70) {
                        txtHumi.setTextColor(Color.RED);
                    }
                    else txtHumi.setTextColor(Color.BLACK);
                } else if (topic.contains("nutnhan1")) {
                    switchLight.setChecked(message.toString().contains("1"));
                } else if (topic.contains("nutnhan2")) {
                    switchFan.setChecked(message.toString().contains("1"));
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.d("MQTT", "Message delivery complete");
            }

//            @Override
//            public void publishToTopic (String topic, MqttMessage message)  {
//                Log.w("MQTT", message.toString());
//            }
        });
    }
}
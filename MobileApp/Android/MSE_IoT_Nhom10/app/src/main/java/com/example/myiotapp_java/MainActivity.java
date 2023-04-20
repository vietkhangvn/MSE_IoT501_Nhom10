package com.example.myiotapp_java;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {

    MQTTHelper mqttHelper;
    TextView txtTemp, txtHumi, txtConn;
    Button bttConnect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTemp = findViewById(R.id.txtTemperature);
        txtHumi = findViewById(R.id.txtHumidity);
        txtConn = findViewById(R.id.txtConnection);
        bttConnect = findViewById(R.id.btnConnect);
        // startMQTT();
    }

    public void connectMQTT(View view){
        txtConn.setText("Connecting to server...");
        startMQTT();
    }

    public void startMQTT(){
        mqttHelper = new MQTTHelper(this);
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                txtConn.setText("Connected to: " + serverURI.toString());
                txtConn.setTextColor(Color.BLUE);
                bttConnect.setClickable(false);
                bttConnect.setActivated(false);
            }

            @Override
            public void connectionLost(Throwable cause) {
                Log.e("MQTT", "Connection lost with Adafruit.io MQTT server");
                txtConn.setText("Connection Lost!");
                txtConn.setTextColor(Color.RED);
                bttConnect.setClickable(true);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.w("MQTT", message.toString());
                if(topic.contains("cambien1")){
                    txtTemp.setText(message.toString() + "°C");
                } else if (topic.contains("cambien2")) {
                    txtHumi.setText(message.toString() + "%");
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.d("MQTT", "Message delivery complete");
            }
        });
    }
}
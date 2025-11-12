package com.codewave.iotgateway.service;

import com.codewave.iotgateway.model.SensorData;
import com.codewave.iotgateway.repository.SensorDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MqttService implements MqttCallback {
    private final SensorDataRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();
    private MqttClient client;

    @Value("${mqtt.broker}")
    private String broker;

    @Value("${mqtt.clientId}")
    private String clientId;

    @Value("${mqtt.topicFilter}")
    private String topicFilter;

    public MqttService(SensorDataRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions opts = new MqttConnectOptions();
            opts.setAutomaticReconnect(true);
            opts.setCleanSession(true);
            client.setCallback(this);
            client.connect(opts);
            client.subscribe(topicFilter);
            System.out.println("Connected to MQTT broker and subscribed to: " + topicFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.err.println("MQTT connection lost: " + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());
        try {
            SensorData data = mapper.readValue(payload, SensorData.class);
            repository.save(data);
            System.out.println("Saved sensor data: " + data);
        } catch (Exception ex) {
            System.err.println("Failed to parse MQTT payload: " + payload);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // not used for subscriber
    }
}

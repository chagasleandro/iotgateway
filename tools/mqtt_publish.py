# Simple MQTT publisher to simulate sensors
import time
import json
import random
import paho.mqtt.client as mqtt

BROKER = "localhost"
PORT = 1883

client = mqtt.Client()
client.connect(BROKER, PORT, 60)

try:
    while True:
        sensor_id = f"esp-{random.randint(1,4)}"
        payload = {
            "sensorId": sensor_id,
            "timestamp": int(time.time() * 1000),
            "type": "temperature",
            "value": round(random.uniform(20.0, 35.0), 2)
        }
        topic = f"sensors/{sensor_id}/data"
        client.publish(topic, json.dumps(payload))
        print("Published", topic, payload)
        time.sleep(2)
except KeyboardInterrupt:
    client.disconnect()

import MqttConfig as MqttConfig
from Adafruit_IO import MQTTClient
class MQTTHelper:
    AIO_servername = ""
    AIO_username = ""
    AIO_apikey = ""

    def __init__(self):
        self.connect()

    def connect(self):
        client = MQTTClient(MqttConfig.AIO_USERNAME, MqttConfig.AIO_KEY)
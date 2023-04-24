# This is a sample Python script.
import sys
import time
import uart
import physical
import MqttConfig as MqttConfig
from FaceRecognition import FaceRecognition


from Adafruit_IO import MQTTClient


def connected(client):
    print("Ket noi thanh cong ...")
    for topic in MqttConfig.AIO_FEED_IDs:
        print("Subscribe topic: " + topic)
        client.subscribe(topic)


def subscribe(client, userdata, mid, granted_qos):
    print("Subscribed thanh cong ...")


def disconnected(client):
    print("Ngat ket noi ...")
    sys.exit(1)

def message(client, feed_id, payload):
    print("Nhan du lieu tu " + feed_id + ": "+ payload)
    if feed_id == "nutnhan1":
        data = "Light" + payload
        uart.writeData(data)
    elif feed_id == "nutnhan2":
        data = "Fan" + payload
        uart.writeData(data)  # Write data to serial so YoloBit can receive


client = MQTTClient(MqttConfig.AIO_USERNAME, MqttConfig.AIO_KEY)
client.on_connect = connected  # Khi có sự kiện on_connect thì gọi tới hàm connected
client.on_disconnect = disconnected
client.on_message = message
client.on_subscribe = subscribe
client.connect()
client.loop_background()  # Chạy vô tận dưới background.

counter = 5
LightStatus = None
FanStatus = None

# physical = physical.openCom()
isSending = True
# FaceRecognition facedetect
processType = 0     # 1: Read data from YoloBit, 2: Check Light, 3: Check Fan

while True:
    counter = counter - 1
    if counter <= 0:
        counter = 5
        processType = processType + 1
        if processType >= 4:
            processType = 1
        if processType == 1:
            uart.readSerial(client)
        elif processType == 2:
            value = client.receive("nutnhan1")
            if LightStatus != value:
                LightStatus = value
                print("Dieu khien den: ", value)
        elif processType == 3:
            value = client.receive("nutnhan2")
            if FanStatus != value:
                FanStatus = value
                print("Dieu khien quat: ", value)

    time.sleep(1)

# Press the green button in the gutter to run the script.
# See PyCharm help at https://www.jetbrains.com/help/pycharm/

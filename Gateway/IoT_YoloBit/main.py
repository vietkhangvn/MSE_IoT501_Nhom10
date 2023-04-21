# This is a sample Python script.
import sys
import time
import serial.tools.list_ports
import uart
import physical
import MqttConfig as MqttConfig

# button_a.on_pressed = None
# button_b.on_pressed = None
# button_a.on_pressed_ab = button_b.on_pressed_ab = -1

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.

from Adafruit_IO import MQTTClient

def connected(client):
    print("Ket noi thanh cong ...")
    for topic in MqttConfig.AIO_FEED_IDs:
        client.subscribe(topic)


def subscribe(client, userdata, mid, granted_qos):
    print("Subscribe thanh cong ...")


def disconnected(client):
    print("Ngat ket noi ...")
    sys.exit(1)


def message(client, feed_id, payload):
    print("Nhan du lieu: " + payload)
    uart.writeData(payload)


client = MQTTClient(MqttConfig.AIO_USERNAME, MqttConfig.AIO_KEY)
client.on_connect = connected  # Khi có sự kiện on_connect thì gọi tới hàm connected
client.on_disconnect = disconnected
client.on_message = message
client.on_subscribe = subscribe
client.connect()
client.loop_background()  # Chạy vô tận dưới background.

counter = 5
sensortype = 0
Device1_status = True
Device2_status = False
#physical = physical.openCom()
isSending = True
while True:
  counter = counter - 1
  if counter <= 0:
       counter = 5
       isSending = False
       # sensortype = 1
       # value = np.random.randint(0, 100)
       # value = tensor.image_detector()
       # uart.readSerial(client)
       # value = client.receive("nutnhan1")
       #print ("Nut nhan 01: ", value)



  time.sleep(1)

  if isSending == False:
      if Device1_status == True:
          physical.setDevice1(False)
      else:
          physical.setDevice1(True)

      if Device2_status == True:
          physical.setDevice1(False)
      else:
          physical.setDevice1(True)
      isSending = True
  pass

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

# See PyCharm help at https://www.jetbrains.com/help/pycharm/

# MSE_IoT501_Nhom10
Project nhóm 10 lớp MSE11
- Nguyễn Viết Khang
- Hồ Văn Chương
- Hồ Bích Thủy

Mục tiêu nhóm 10 xây dựng một ứng dụng theo dõi dành cho công ty, nhà riêng hoặc tổ chức muốn theo dõi người ra vào.

Ứng dụng sẽ có 3 thành phần:

## Ứng dụng Android để theo dõi trực tiếp các thông tin cần thiết:
- Cho phép người dùng chủ động kết nối đến Server, thông báo tình trạng kết nối và message cuối cùng nhận được là khi nào:
![image](https://user-images.githubusercontent.com/90981819/234106382-d7c22fe9-7b4b-49c7-8681-557428c43b29.png)
![image](https://user-images.githubusercontent.com/90981819/234107012-f97acfbd-d0dc-4f0c-a537-812942976010.png)

- Theo dõi Nhiệt độ và Độ ẩm phòng (cập nhật sau mỗi 5s). 
- Cảnh báo trên điện thoại khi nhiệt độ quá cao hoặc quá thấp. Nếu nhiệt độ vượt quá 35 độ hoặc độ ẩm vượt quá 70% sẽ xuất hiện cảnh báo trên màn hình (màu đỏ).

![image](https://user-images.githubusercontent.com/90981819/234107727-1bf569d7-58cf-45d6-998e-b431da5ee24b.png)
![image](https://user-images.githubusercontent.com/90981819/234107856-a7a8fbfa-5258-495b-a241-6af029936769.png)

- Tự động bật quạt khi nhiệt độ quá cao: Cấu hình bằng Action trên Adafruit: khi nhiệt độ từ cambien1 lớn hơn 35 thì gửi 1 tín hiệu đến cambien2 (điều khiển quạt) để bật quạt:
![image](https://user-images.githubusercontent.com/90981819/234108658-58f4d251-59b1-4857-91e0-54df45b93dd7.png =120x120)
![image](https://user-images.githubusercontent.com/90981819/234108796-d449810f-670e-4a0b-9636-3f6c3817be1a.png)

- Cho phép người dùng chủ động bật quạt trên điện thoại, Cho phép chủ động bật tắt đèn trên ứng dụng điện thoại (đồng bộ với trạng thái trên Adafruit.io):

https://user-images.githubusercontent.com/90981819/234116107-eba03a41-627d-4215-a9f9-982a3b4cdf4a.mov

- Chủ động bật tắt đèn và quạt:

![image](https://user-images.githubusercontent.com/90981819/234117483-d67da3d7-8403-420f-ac21-3f6163d68ad9.png)
![image](https://user-images.githubusercontent.com/90981819/234117502-11dc0411-a351-415e-9fc6-2d796263d975.png)


- Thông báo khi có người lạ vào.
- Thông báo nếu có nhân viên cty không mang khẩu trang.
- Tính năng nâng cao: lưu cấu hình MQTT server vào SharedPreferences để tăng tính bảo mật và tăng tính đóng gói của chương trình.

## Ứng dụng trên YoloBit:
- Theo dõi nhiệt độ và độ ẩm phòng, gửi thông tin lên server sau mỗi 5s
- Tự động bật quạt khi phát hiện nhiệt độ quá cao (sử dụng Action trên Adafruit.io
- Tự động bật đèn khi trời tối: cảm biến ánh sáng phát hiện trời tối: tự động bật đèn.

## Ứng dụng theo dõi có sử dụng AI: hiện tại nhóm phát triển ứng ụng trên laptop bằng Python.
- Tự động theo dõi một khu vực định trước bằng camera tích hợp.
- Tự động nhận diện gương mặt bằng AI. Khi phát hiện gương mặt:
    - Nếu nhận diện được gương mặt đã khai báo trước (VD: gương mặt nhân viên cty), sẽ tự động phát lời chào: "Xin chào" + tên nhân viên. Đồng thời sáng đèn màu xanh.
    - Nếu phát hiện người lạ: đèn chuyển sang màu đỏ, đồng thời phát thông báo: **"Xin chào quý khách"**.
- Phát hiện gương mặt đeo khẩu trang: kích hoạt mở cửa, cửa sẽ không mở khi phát hiện gương mặt không đeo khẩu trang.
- Tính năng nâng cao:
    - Lưu cấu hình các thư viện cần thiết trong requirements.txt -> không phải cài đặt thư viện thủ công.
    - Lưu Adafruit API Key trong file Configuration riêng biệt để bảo mật, tránh bị lộ key khi commit code lên Github.

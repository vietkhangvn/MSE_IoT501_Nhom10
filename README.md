# MSE_IoT501_Nhom10
Project nhóm 10 lớp MSE11
- Nguyễn Viết Khang
- Hồ Văn Chương
- Hồ Bích Thủy

Mục tiêu nhóm 10 xây dựng một ứng dụng theo dõi dành cho công ty, nhà riêng hoặc tổ chức muốn theo dõi người ra vào.

Ứng dụng sẽ có 3 thành phần:

## Ứng dụng Android để theo dõi trực tiếp các thông tin cần thiết:
- Nhiệt độ và Độ ẩm phòng
- Cảnh báo trên điện thoại khi nhiệt độ quá cao hoặc quá thấp
    - Tự động bật quạt khi nhiệt độ quá cao
    - Cho phép người dùng chủ động bật quạt trên điện thoại
- Cho phép chủ động bật tắt đèn trên ứng dụng điện thoại
- Thông báo khi có người lạ vào.
- Thông báo nếu có nhân viên cty không mang khẩu trang.
- Tính năng nâng cao: lưu cấu hình MQTT server vào SharedPreferences để tăng tính bảo mật và tăng tính đóng gói của chương trình.

## Ứng dụng trên YoloBit:
- Theo dõi nhiệt độ và độ ẩm phòng, gửi thông tin lên server sau mỗi 5s
- Tự động bật quạt khi phát hiện nhiệt độ quá cao
- Tự động bật đèn khi trời tối

## Ứng dụng theo dõi có sử dụng AI: hiện tại nhóm phát triển ứng ụng trên laptop bằng Python.
- Tự động theo dõi một khu vực định trước bằng camera tích hợp.
- Tự động nhận diện gương mặt bằng AI. Khi phát hiện gương mặt:
    - Nếu nhận diện được gương mặt đã khai báo trước (VD: gương mặt nhân viên cty), sẽ tự động phát lời chào: "Xin chào" + tên nhân viên. Đồng thời sáng đèn màu xanh.
    - Nếu phát hiện người lạ: đèn chuyển sang màu đỏ, đồng thời phát thông báo: **"Xin chào quý khách"**.
- Phát hiện gương mặt đeo khẩu trang: kích hoạt mở cửa, cửa sẽ không mở khi phát hiện gương mặt không đeo khẩu trang.

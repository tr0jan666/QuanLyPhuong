Hướng dẫn Backup và Restore MySQL Database

1. Backup MySQL Database:
Để có thể sao lưu dữ liệu (export) chúng ta click vào Data Export ở Management:
-Lựa chọn data mà bạn muốn export: 
	Export to Dump Project Folder: kiểu này sẽ export từng table thành từng file khác nhau trong 1 folder.
	Export to Self-Contained File: kiểu này sẽ export tất cả thành 1 file SQL
-Tùy chọn Nâng cao (mặc định)
- Nhấn Start Export để bắt đầu quá trình export
-Chờ quá trình export hoàn tất: >> enjoy =))

2. Phục hồi dữ liệu ( Import )
- Click vào Data Import/Restore
- Chọn file dữ liệu bạn muốn import sau đó chọn Start Import để tiến hành import dữ liệu
- Import from Dump Project Folder: đây là kiểu import từ 1 folder giống như option export.
  Import from Self-Contained File: kiểu import từ 1 file.
- Chờ hoàn thành >> Refresh Data >> enjoy -.-

3. Update database
- Tải file backup đã update dữ liệu trên github về máy tính của mình
- Restore/ Import Data file backup đó >> dữ liệu sẽ tự động cập nhập trong database 
*Lưu ý:  import dữ liệu vào file database cần update dữ liệu
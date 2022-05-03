# Đồ án Xây dựng Website Blog Review Phim

## I. Tổng quan về đồ án:

### **1. Thông tin đồ án:**
```
- Đồ án cuối kỳ môn Search Engine của sinh viên trường Đại học Sư phạm Kỹ thuật Thành phố Hồ Chí Minh
- GVHD: TS. HUỲNH XUÂN PHỤNG
- SVTH:
    1. Phạm Xuân Nhuận  18110331
    2. Hồ Văn Hiếu      18110282
```
### **2. Công nghệ sử dụng:**
```
- Ngôn ngữ: Java
- Cơ sở dữ liệu: Elastic Search
- Công nghệ sử dụng:
  1. Spring Boot 2.5.12
  2. Thymeleaf
```
### **3. Các chức năng chính của website:**
#### *3.1. Client:*
```
- Đăng nhập/ đăng xuất theo phân quyền người dùng
- Tìm kiếm thông tin bài viết
- Xem danh mục bài viết
- Xem chi tiết bài viết
- Viết bài
- Quản lý thông tin các nhân
```
#### *3.2. Admin:*
```
- Quản lý người dùng
- Duyệt bài viết
```

## II. Hướng dẫn cài đặt:
- Bước 1: Cài đặt Java 1.8 [tại đây.](https://stackjava.com/java/huong-dan-cai-dat-java-8-tren-windows.html)
- Bước 2: Cài đặt Apache Maven [tại đây.](https://stackjava.com/install/maven-phan-1-maven-la-gi-cai-dat-maven.html)
- Bước 3: Cài đặt IDE Intellij [tại đây.](https://stackjava.com/intellij-idea/intellij-idea-la-gi-cai-dat-intellij-idea-tren-windows.html)
- Bước 4: Download source code [tại đây.](https://github.com/xuannhuan2018/BlogMovieWebsiteProject.git)
- Bước 5: Chỉnh sửa file _application.properties_ theo cấu trúc:
```
spring.data.elasticsearch.repositories.enabled=true
spring.elasticsearch.rest.uris=http://[username]:[password]@[địa chỉ ip aws]:9200
```
- Bước 6: Chỉnh sửa thông tin source code trên intellijj:
* Chỉnh sửa thông tin Java:
_ Vào File -> Project Structure -> Chọn đường dẫn đến thư mục java vừa cài đặt ở bước 1._
![image](https://user-images.githubusercontent.com/88767208/166103403-1f8feafe-1df3-4a6b-be9e-dfd4bb22d8cb.png)
* Chỉnh sửa thông tin maven:
_Vào File -> Settings -> Tìm Maven -> Chọn đường dẫn đến thư mục Maven vừa cài đặt ở bước 2._
![image](https://user-images.githubusercontent.com/88767208/166103465-46573534-e8ee-44d9-9cae-f13bbed2dc84.png)

- Bước 7: Run ứng dụng 
![image](https://user-images.githubusercontent.com/88767208/166103520-446c735c-f90a-40af-8486-e385f5829bee.png)

- Bước 8: Khởi động Elasticsearch và test website [tại đây](http://localhost:8080/)


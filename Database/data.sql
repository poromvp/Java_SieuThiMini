INSERT INTO `chitietdh` (`MaDH`, `MaSP`, `SoLuong`, `TrangThai`) VALUES
(1, 1, 5, 'ACTIVE'),
(1, 3, 5, 'ACTIVE'),
(1, 4, 5, 'ACTIVE'),
(2, 2, 5, 'ACTIVE'),
(2, 1, 5, 'ACTIVE'),
(2, 4, 5, 'ACTIVE'),
(3, 3, 6, 'ACTIVE'),
(4, 4, 8, 'ACTIVE'),
(5, 5, 2, 'ACTIVE'),
(6, 1, 10, 'ACTIVE'),
(6, 2, 5, 'ACTIVE'),
(7, 3, 8, 'ACTIVE'),
(8, 4, 20, 'ACTIVE'),
(9, 5, 7, 'ACTIVE'),
(10, 1, 15, 'ACTIVE'),
(11, 2, 10, 'ACTIVE'),
(12, 3, 12, 'ACTIVE'),
(13, 4, 25, 'ACTIVE'),
(14, 5, 5, 'ACTIVE'),
(15, 1, 12, 'ACTIVE'),
(16, 2, 8, 'ACTIVE'),
(17, 3, 10, 'ACTIVE'),
(18, 4, 15, 'ACTIVE'),
(19, 5, 8, 'ACTIVE'),
(20, 1, 10, 'ACTIVE');

INSERT INTO `chitietkm` (`MaKM`, `MaSP`, `TileGiam`, `TrangThai`) VALUES
(1, 1, 10.00, 'ACTIVE'),
(2, 2, 15.50, 'ACTIVE'),
(3, 3, 20.00, 'ACTIVE'),
(4, 4, 5.00, 'ACTIVE'),
(5, 5, 12.75, 'ACTIVE');

INSERT INTO `chitietpnh` (`MaCTPNH`, `MaPNH`, `MaSP`, `MaLH`, `NgayHH`, `NgaySX`, `SoLuong`, `GiaNhap`, `TrangThai`) VALUES
(1, 1, 1, 1, NULL, NULL, 100, 45000.00, 'ACTIVE'),
(2, 2, 2, 2, NULL, NULL, 200, 70000.00, 'ACTIVE'),
(3, 3, 3, 3, NULL, NULL, 150, 95000.00, 'ACTIVE'),
(4, 4, 4, 4, NULL, NULL, 180, 120000.00, 'ACTIVE'),
(5, 5, 5, 5, NULL, NULL, 220, 140000.00, 'ACTIVE');

INSERT INTO `diemtichluy` (`MaDTL`, `DiemTL`, `TiLeGiam`, `GiamMax`, `TrangThai`) VALUES
(1, 1000, 10.00, 100000, 'ACTIVE'),
(2, 2000, 15.50, 200000, 'ACTIVE'),
(3, 3000, 20.00, 300000, 'ACTIVE'),
(4, 4000, 5.00, 400000, 'ACTIVE'),
(5, 5000, 12.75, 50000, 'ACTIVE');


INSERT INTO `donhang` (`MaDH`, `MaKH`, `MaKM`, `MaNV`, `PTTToan`, `NgayTT`, `maDTL`, `tienKD`, `TrangThai`, `TongTien`) VALUES
(1, 1, 1, 1, 'BANK', '2024-03-10 00:00:00', 1, -1, 'FINISHED', 209253.38),
(2, 2, 2, 2, 'CASH', '2024-03-11 00:00:00', 2, 1000000, 'FINISHED', 140021.45),
(3, 3, 2, 3, 'BANK', '2024-03-12 00:00:00', 3, -1, 'FINISHED', 120000.00),
(4, 4, NULL, 4, 'CASH', '2024-03-13 00:00:00', 4, 1000000, 'FINISHED', 60805.70),
(5, 5, 3, 5, 'CASH', '2024-03-14 00:00:00', 5, 1000000, 'FINISHED', 61075.00),
(6, 1, 1, 1, 'CASH', '2023-01-05 10:00:00', 1, 1000000, 'FINISHED', 210002.50),
(7, 2, NULL, 2, 'BANK', '2023-02-10 14:30:00', 2, -1, 'FINISHED', 200000.00),
(8, 3, 2, 3, 'CASH', '2023-03-15 09:00:00', NULL, 500000, 'FINISHED', 160015.00),
(9, 4, 3, 4, 'BANK', '2023-06-20 16:00:00', 3, -1, 'FINISHED', 245000.00),
(10, 5, 4, 5, 'CASH', '2023-09-25 11:00:00', 4, 2000000, 'FINISHED', 225000.00),
(11, 6, NULL, 1, 'BANK', '2023-12-30 13:00:00', 5, -1, 'FINISHED', 120005.00),
(12, 8, 5, 2, 'CASH', '2024-01-10 15:00:00', NULL, 1000000, 'FINISHED', 300000.00),
(13, 9, 1, 3, 'BANK', '2024-04-05 12:00:00', 1, -1, 'FINISHED', 200018.75),
(14, 10, 2, 4, 'CASH', '2024-07-15 17:00:00', 2, 1500000, 'FINISHED', 175000.00),
(15, 1, 3, 5, 'BANK', '2024-10-20 10:30:00', 3, -1, 'FINISHED', 180000.00),
(16, 2, NULL, 1, 'CASH', '2024-12-25 14:00:00', 4, 2000000, 'FINISHED', 96004.00),
(17, 3, 4, 2, 'BANK', '2025-01-05 09:00:00', NULL, -1, 'FINISHED', 250000.00),
(18, 4, 5, 3, 'CASH', '2025-02-10 16:00:00', 5, 1000000, 'FINISHED', 120011.25),
(19, 5, 1, 4, 'BANK', '2025-03-15 11:00:00', 1, -1, 'FINISHED', 280000.00),
(20, 6, NULL, 5, 'CASH', '2025-04-20 13:00:00', 2, 500000, 'FINISHED', 150000.00);


INSERT INTO `khuyenmai` (`MaKM`, `TenKM`, `NgayKT`, `NgayBD`, `TrangThai`) VALUES
(1, 'Giảm giá 10%', '2025-12-31', '2025-01-01', 'ACTIVE'),
(2, 'Giảm giá 15%', '2024-12-31', '2024-01-01', 'ACTIVE'),
(3, 'Giảm giá 20%', '2025-06-30', '2025-01-01', 'ACTIVE'),
(4, 'Giảm giá 5%', '2025-03-31', '2025-01-01', 'ACTIVE'),
(5, 'Giảm giá 12.75%', '2025-09-30', '2025-06-01', 'ACTIVE');

INSERT INTO `loaisp` (`MaLSP`, `TenLoaiSP`, `TrangThai`) VALUES
(1, 'Thực phẩm', 'ACTIVE'),
(2, 'Đồ uống', 'ACTIVE'),
(3, 'Gia dụng', 'ACTIVE'),
(4, 'Thời trang', 'ACTIVE'),
(5, 'Mỹ phẩm', 'ACTIVE');

INSERT INTO `nhancc` (`MaNCC`, `TenNCC`, `SDT`, `DiaChi`, `TrangThai`) VALUES
(1, 'Công ty Vinamilk', '0988881111', 'TP Hồ Chí Minh', 'ACTIVE'),
(2, 'Công ty TH True Milk', '0988882222', 'Nghệ An', 'ACTIVE'),
(3, 'Công ty Nestlé', '0988883333', 'Hà Nội', 'ACTIVE'),
(4, 'Công ty Orion', '0988884444', 'Bình Dương', 'ACTIVE'),
(5, 'Công ty Ajinomoto', '0988885555', 'Đồng Nai', 'ACTIVE');


INSERT INTO `nhanvien` (`MaNV`, `TenNV`, `GioiTinh`, `NgaySinh`, `CCCD`, `DiaChi`, `SDT`, `Luong`, `TrangThai`, `image`) VALUES
(1, 'VÕ TRỊNH KHANG', 'Nam', '2005-04-27', '123456789012', 'QUẢNG NGÃI', '0762779663', 10000000.00, b'1', 'haha.jpeg'),
(2, 'TRỊNH KHANG', 'Nam', '2005-04-27', '123456789013', 'QUẢNG NGÃI kkk', '0762779664', 10000000.00, b'1', 'haha.jpeg'),
(3, 'KHANG', 'Nam', '2005-04-27', '123456789014', 'QUẢNG NGÃI KKK', '0762779665', 10000000.00, b'1', 'haha.jpeg'),
(4, 'Nguyễn Văn An', 'Nam', '1990-05-10', '123456789012', 'Hà Nội', '0912345671', 10000000.00, b'1', 'haha.jpeg'),
(5, 'Trần Thị Bích', 'Nữ', '1992-07-15', '234567890123', 'Hải Phòng', '0912345672', 12000000.00, b'1', NULL),
(6, 'Lê Văn Cường', 'Nam', '1993-09-20', '345678901234', 'Đà Nẵng', '0912345673', 15000000.00, b'1', NULL),
(7, 'Phạm Thị Dung', 'Nữ', '1994-11-25', '456789012345', 'TP Hồ Chí Minh', '0912345674', 11000000.00, b'1', NULL),
(8, 'Võ Minh Tuấn', 'Nam', '1995-03-30', '567890123456', 'Cần Thơ', '0912345675', 9000000.00, b'1', NULL);


INSERT INTO `phieunhaphang` (`MaPNH`, `TenPNH`, `MaNCC`, `MaNV`, `NgayNhap`, `TrangThai`) VALUES
(1, 'Nhập hàng tháng 3', 1, 1, '2024-03-10', 'FINISHED'),
(2, 'Nhập hàng thực phẩm', 2, 2, '2024-03-12', 'FINISHED'),
(3, 'Nhập hàng gia dụng', 3, 3, '2024-03-15', 'FINISHED'),
(4, 'Nhập hàng điện máy', 4, 4, '2024-03-18', 'FINISHED'),
(5, 'Nhập hàng thời trang', 5, 5, '2024-03-20', 'FINISHED');


INSERT INTO `sanpham` (`MaSP`, `MaNCC`, `MaLSP`, `TenAnh`, `Gia`, `TenSP`, `MoTa`, `TrangThai`, `SoLuongTon`) VALUES
(51, 1, 1, 'sp1.jpg', 15000.00, 'Bánh Oreo', 'Bánh quy nhân kem', 'ACTIVE', 0),
(52, 2, 2, 'sp2.jpg', 12000.50, 'Sữa Vinamilk', 'Sữa tươi nguyên chất', 'ACTIVE', 0),
(53, 3, 3, 'sp3.jpg', 25000.00, 'Mì Hảo Hảo', 'Mì gói chua cay', 'ACTIVE', 0),
(54, 4, 4, 'sp4.jpg', 8000.75, 'Nước suối Lavie', 'Nước khoáng thiên nhiên', 'ACTIVE', 0),
(55, 5, 5, 'sp5.jpg', 35000.00, 'Cà phê G7', 'Cà phê hòa tan đậm đà', 'ACTIVE', 0);

INSERT INTO `sanpham` (`MaSP`, `MaNCC`, `MaLSP`, `TenAnh`, `Gia`, `TenSP`, `MoTa`, `TrangThai`, `SoLuongTon`) VALUES
(1, 1, 1, 'thucpham1.jpg', 10000, 'Gạo ST25', 'Gạo ngon đặc sản', 'ACTIVE', 50),
(2, 1, 1, 'thucpham2.jpg', 20000, 'Nước mắm Nam Ngư', 'Nước mắm truyền thống', 'ACTIVE', 100),
(3, 1, 1, 'thucpham3.jpg', 15000, 'Trứng gà', 'Trứng gà ta', 'ACTIVE', 200),
(4, 1, 1, 'thucpham4.jpg', 25000, 'Thịt heo', 'Thịt heo tươi sạch', 'ACTIVE', 80),
(5, 1, 1, 'thucpham5.jpg', 5000, 'Muối i-ốt', 'Muối ăn tốt cho sức khỏe', 'ACTIVE', 300),
(6, 1, 1, 'thucpham6.jpg', 12000, 'Dầu ăn Neptune', 'Dầu ăn cao cấp', 'ACTIVE', 120),
(7, 1, 1, 'thucpham7.jpg', 8000, 'Đường trắng', 'Đường tinh luyện', 'ACTIVE', 150),
(8, 1, 1, 'thucpham8.jpg', 18000, 'Sữa tươi', 'Sữa tươi tiệt trùng', 'ACTIVE', 100),
(9, 1, 1, 'thucpham9.jpg', 3000, 'Mì tôm', 'Mì ăn liền Hảo Hảo', 'ACTIVE', 500),
(10, 1, 1, 'thucpham10.jpg', 4000, 'Bánh mì', 'Bánh mì truyền thống', 'ACTIVE', 90);


INSERT INTO `sanpham` VALUES
(11, 2, 2, 'douong1.jpg', 10000, 'Coca Cola', 'Nước ngọt có ga', 'ACTIVE', 200),
(12, 2, 2, 'douong2.jpg', 9000, 'Pepsi', 'Nước ngọt vị cola', 'ACTIVE', 180),
(13, 2, 2, 'douong3.jpg', 12000, 'Trà xanh C2', 'Trà xanh giải nhiệt', 'ACTIVE', 160),
(14, 2, 2, 'douong4.jpg', 15000, 'Revive', 'Nước bù khoáng', 'ACTIVE', 140),
(15, 2, 2, 'douong5.jpg', 20000, 'Nước cam Twister', 'Nước ép cam', 'ACTIVE', 130),
(16, 2, 2, 'douong6.jpg', 11000, 'Number 1', 'Nước tăng lực', 'ACTIVE', 170),
(17, 2, 2, 'douong7.jpg', 25000, 'Cafe sữa', 'Cà phê sữa đá', 'ACTIVE', 100),
(18, 2, 2, 'douong8.jpg', 13000, 'Trà sữa', 'Trà sữa vị truyền thống', 'ACTIVE', 120),
(19, 2, 2, 'douong9.jpg', 8000, 'Lavie 500ml', 'Nước suối đóng chai', 'ACTIVE', 300),
(20, 2, 2, 'douong10.jpg', 7000, 'Sting dâu', 'Nước tăng lực vị dâu', 'ACTIVE', 220);

INSERT INTO `sanpham` VALUES
(21, 3, 3, 'giadung1.jpg', 50000, 'Chảo chống dính', 'Chảo nấu ăn chất lượng', 'ACTIVE', 80),
(22, 3, 3, 'giadung2.jpg', 30000, 'Bộ dao nhà bếp', 'Dao thép không gỉ', 'ACTIVE', 100),
(23, 3, 3, 'giadung3.jpg', 40000, 'Nồi inox', 'Nồi inox 3 lớp đáy', 'ACTIVE', 90),
(24, 3, 3, 'giadung4.jpg', 60000, 'Bình đun siêu tốc', 'Bình 1.8L tự ngắt điện', 'ACTIVE', 70),
(25, 3, 3, 'giadung5.jpg', 20000, 'Máy xay sinh tố mini', 'Máy xay hoa quả mini', 'ACTIVE', 50),
(26, 3, 3, 'giadung6.jpg', 10000, 'Bàn chải nhà tắm', 'Dụng cụ vệ sinh đa năng', 'ACTIVE', 150),
(27, 3, 3, 'giadung7.jpg', 8000, 'Kéo cắt thực phẩm', 'Kéo bếp đa năng', 'ACTIVE', 200),
(28, 3, 3, 'giadung8.jpg', 25000, 'Khăn lau bếp', 'Khăn siêu thấm', 'ACTIVE', 300),
(29, 3, 3, 'giadung9.jpg', 35000, 'Thớt gỗ', 'Thớt gỗ tự nhiên', 'ACTIVE', 120),
(30, 3, 3, 'giadung10.jpg', 75000, 'Bàn ủi hơi nước', 'Bàn ủi mini tiện lợi', 'ACTIVE', 60);

INSERT INTO `sanpham` VALUES
(31, 4, 4, 'thoitrang1.jpg', 150000, 'Áo thun nam', 'Áo cotton 100%', 'ACTIVE', 100),
(32, 4, 4, 'thoitrang2.jpg', 250000, 'Quần jeans nữ', 'Quần co giãn thời trang', 'ACTIVE', 80),
(33, 4, 4, 'thoitrang3.jpg', 300000, 'Áo sơ mi nam', 'Áo sơ mi công sở', 'ACTIVE', 70),
(34, 4, 4, 'thoitrang4.jpg', 100000, 'Mũ lưỡi trai', 'Mũ thời trang mùa hè', 'ACTIVE', 90),
(35, 4, 4, 'thoitrang5.jpg', 120000, 'Vớ thể thao', 'Vớ cotton thoáng khí', 'ACTIVE', 200),
(36, 4, 4, 'thoitrang6.jpg', 350000, 'Giày thể thao', 'Giày sneaker nam nữ', 'ACTIVE', 60),
(37, 4, 4, 'thoitrang7.jpg', 180000, 'Túi xách nữ', 'Túi da công sở', 'ACTIVE', 70),
(38, 4, 4, 'thoitrang8.jpg', 90000, 'Thắt lưng nam', 'Thắt lưng da PU', 'ACTIVE', 150),
(39, 4, 4, 'thoitrang9.jpg', 220000, 'Đầm dự tiệc', 'Váy thời trang cao cấp', 'ACTIVE', 40),
(40, 4, 4, 'thoitrang10.jpg', 50000, 'Khăn choàng cổ', 'Khăn vải lụa mềm mại', 'ACTIVE', 110);


INSERT INTO `sanpham` VALUES
(41, 5, 5, 'mypham1.jpg', 120000, 'Sữa rửa mặt', 'Làm sạch sâu da mặt', 'ACTIVE', 100),
(42, 5, 5, 'mypham2.jpg', 250000, 'Kem chống nắng', 'SPF50+, dưỡng ẩm da', 'ACTIVE', 80),
(43, 5, 5, 'mypham3.jpg', 180000, 'Son môi', 'Son lì không trôi', 'ACTIVE', 90),
(44, 5, 5, 'mypham4.jpg', 300000, 'Kem dưỡng da', 'Dưỡng trắng da ban đêm', 'ACTIVE', 70),
(45, 5, 5, 'mypham5.jpg', 95000, 'Tẩy trang', 'Nước tẩy trang dịu nhẹ', 'ACTIVE', 150),
(46, 5, 5, 'mypham6.jpg', 200000, 'Nước hoa mini', 'Hương thơm quyến rũ', 'ACTIVE', 60),
(47, 5, 5, 'mypham7.jpg', 175000, 'Phấn nền', 'Làm mịn da, che khuyết điểm', 'ACTIVE', 100),
(48, 5, 5, 'mypham8.jpg', 110000, 'Kẻ mắt', 'Bút kẻ mắt không lem', 'ACTIVE', 120),
(49, 5, 5, 'mypham9.jpg', 220000, 'Mặt nạ dưỡng da', 'Dưỡng ẩm và làm sáng da', 'ACTIVE', 90),
(50, 5, 5, 'mypham10.jpg', 80000, 'Bông tẩy trang', 'Bông cotton 100%', 'ACTIVE', 200);


INSERT INTO `taikhoan` (`MaNV`, `TenTK`, `MatKhau`, `Quyen`, `Gmail`, `TrangThai`) VALUES
(1, 'VOTRINHKHANG', 'VOTRINHKHANG', 'ADMIN', 'VOTRINHKHANG@gmail.com', 'ACTIVE'),
(2, 'TRINHKHANG', 'TRINHKHANG', 'QUẢN LÝ KHO', 'TRINHKHANG@gmail.com', 'ACTIVE'),
(3, 'KHANG', 'KHANG', 'NHÂN VIÊN', 'KHANG@gmail.com', 'ACTIVE'),
(4, 'admin1', '123456', 'ADMIN', 'admin1@example.com', 'ACTIVE'),
(5, 'kho01', 'kho123', 'QUẢN LÝ KHO', 'kho01@example.com', 'ACTIVE'),
(6, 'nhanvien01', 'nv123', 'NHÂN VIÊN', 'nv01@example.com', 'INACTIVE');


INSERT INTO `thethanhvien` (`MaTV`, `TenTV`, `NgaySinh`, `DiaChi`, `DiemTL`, `SDT`, `NgayBD`, `NgayKT`, `TenAnh`, `TrangThai`) VALUES
(1, 'Nguyễn Hữu Hạnh', '1985-01-14', 'Hà Nộii', 200, '0987456123', '2023-04-01', '2027-04-30', 'hanh.JPG', 'ACTIVE'),
(2, 'Trần Ngọc Minh', '1988-06-22', 'Hải Dương', 150, '0987456124', '2023-02-01', '2025-02-01', 'minh.jpg', 'ACTIVE'),
(3, 'Lê Thành Đạt', '1990-09-05', 'Đà Nẵng', 300, '0987456125', '2023-03-01', '2025-03-01', 'dat.jpg', 'ACTIVE'),
(4, 'Phạm Quang Dũng', '1992-12-18', 'Bắc Ninh', 180, '0987456126', '2023-04-01', '2025-04-01', 'dung.jpg', 'ACTIVE'),
(5, 'Võ Hoài Nam', '1995-08-30', 'Cần Thơ', 250, '0987456127', '2023-05-01', '2025-05-01', 'nam.jpg', 'ACTIVE'),
(6, 'ooo', '2005-04-11', 'aaaa', 0, '123456789', '2025-04-25', '2027-04-25', 'nv3.jpg', 'ACTIVE'),
(8, 'Nguyễn Ngọc Yến Nhi', '2005-02-23', 'asdfjadf', 0, '2345621', '2025-04-27', '2027-04-27', 'nv2.jpeg', 'ACTIVE'),
(9, 'Lê Quang Kiệt', '2005-02-23', 'Bãi Sau, TP.HCM', 0, '0977723621', '2025-04-27', '2027-04-27', 'nv4.jpeg', 'ACTIVE'),
(10, 'jkl;', '2005-02-23', 'sdfgsgdf', 0, '1', '2025-04-28', '2027-04-28', 'nv3.jpg', 'ACTIVE');
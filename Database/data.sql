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





INSERT INTO `khuyenmai` (`MaKM`, `TenKM`, `NgayKT`, `NgayBD`, `TrangThai`) VALUES
(1, 'Giảm giá 10%', '2025-12-31', '2025-01-01', 'ACTIVE'),
(2, 'Giảm giá 15%', '2024-12-31', '2024-01-01', 'ACTIVE'),
(3, 'Giảm giá 20%', '2025-06-30', '2025-01-01', 'ACTIVE'),
(4, 'Giảm giá 5%', '2025-03-31', '2025-01-01', 'ACTIVE'),
(5, 'Giảm giá 12.75%', '2025-09-30', '2025-06-01', 'ACTIVE');

INSERT INTO `loaisp` (`MaLSP`, `TenLoaiSP`, `TrangThai`) VALUES
(1, 'Thực phẩm', 'ACTIVE'),
(2, 'Các loại gia vị', 'ACTIVE'),
(3, 'Đồ uống', 'ACTIVE'),
(4, 'Bánh kẹo', 'ACTIVE'),
(5, 'Vật dụng gia đình', 'ACTIVE');

INSERT INTO `nhancc` (`MaNCC`, `TenNCC`, `SDT`, `DiaChi`, `TrangThai`) VALUES
(1, 'Công ty A', '0988881111', 'TP Hồ Chí Minh', 'ACTIVE'),
(2, 'Công ty B', '0988882222', 'Nghệ An', 'ACTIVE'),
(3, 'Công ty C', '0988883333', 'Hà Nội', 'ACTIVE'),
(4, 'Công ty D', '0988884444', 'Bình Dương', 'ACTIVE'),
(5, 'Công ty E', '0988885555', 'Đồng Nai', 'ACTIVE');


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
(1, 1, 1, 'camoi.png', 10000, 'Cá mòi', 'Cá mòi ngon, đậm đà', 'ACTIVE', 50),
(2, 1, 1, 'thithop.png', 20000, 'Thịt hộp', 'Thịt hộp siêu ngon', 'ACTIVE', 100),
(3, 1, 1, 'xucxich.png', 15000, 'Xúc xích', 'Xúc xích hihi', 'ACTIVE', 200),
(4, 1, 1, 'trungga.png', 25000, 'Trứng gà ', 'Trứng gà công nghiệp', 'ACTIVE', 80),
(5, 1, 1, 'mitom.png', 5000, 'Mì tôm', 'Mì ăn liền Hảo Hảo', 'ACTIVE', 500),
(6, 1, 2, 'dauan.png', 12000, 'Dầu ăn Neptune', 'Dầu ăn cao cấp', 'ACTIVE', 120),
(7, 1, 2, 'duong.png', 8000, 'Đường trắng', 'Đường tinh luyện', 'ACTIVE', 150),
(8, 1, 2, 'muoi.png', 18000, 'Muối', 'Muối sạch', 'ACTIVE', 100),
(9, 1, 2, 'hatnem.png', 3000, 'Hạt nêm', 'Hạt nêm siêu ngon', 'ACTIVE', 500),
(10, 1, 2, 'nuocmam.png', 4000, 'Nước mắm', 'Nước mắm cá cơm', 'ACTIVE', 90);


INSERT INTO `sanpham` VALUES
(11, 2, 3, 'cocacola.png', 10000, 'Coca Cola', 'Nước ngọt có ga', 'ACTIVE', 200),
(12, 2, 3, 'pepsi.png', 9000, 'Pepsi', 'Nước ngọt vị cola', 'ACTIVE', 180),
(13, 2, 3, 'c2.png', 12000, 'Trà xanh C2', 'Trà xanh giải nhiệt', 'ACTIVE', 160),
(14, 2, 3, 'revive.png', 15000, 'Revive', 'Nước bù khoáng', 'ACTIVE', 140),
(15, 2, 3, 'nuoccam.png', 20000, 'Nước cam Twister', 'Nước ép cam', 'ACTIVE', 130),
(16, 2, 4, 'banhoshi.png', 11000, 'Bánh oshi', 'Bánh ngon', 'ACTIVE', 170),
(17, 2, 4, 'banhmi.png', 25000, 'Bánh mỳ Kinh Đô', 'Bánh ngon', 'ACTIVE', 100),
(18, 2, 4, 'banhxep.png', 13000, 'Bánh xếp', 'Bánh ngon', 'ACTIVE', 120),
(19, 2, 5, 'banchai.png', 8000, 'Bàn chải PS', 'Bàn chải xịn', 'ACTIVE', 300),
(20, 2, 5, 'thamchuichan.png', 7000, 'Thảm chùi chân', 'Thảm chùi chân xịn ', 'ACTIVE', 220);



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
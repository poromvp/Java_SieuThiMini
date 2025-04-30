-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2025 at 08:04 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sieuthimini`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietdh`
--

CREATE TABLE `chitietdh` (
  `MaDH` int(11) DEFAULT NULL,
  `MaSP` int(11) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chitietdh`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `chitietkm`
--

CREATE TABLE `chitietkm` (
  `MaKM` int(11) NOT NULL,
  `MaSP` int(11) NOT NULL,
  `TileGiam` decimal(5,2) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chitietkm`
--

INSERT INTO `chitietkm` (`MaKM`, `MaSP`, `TileGiam`, `TrangThai`) VALUES
(1, 1, 10.00, 'ACTIVE'),
(2, 2, 15.50, 'ACTIVE'),
(3, 3, 20.00, 'ACTIVE'),
(4, 4, 5.00, 'ACTIVE'),
(5, 5, 12.75, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `chitietpnh`
--

CREATE TABLE `chitietpnh` (
  `MaCTPNH` int(11) NOT NULL,
  `MaPNH` int(11) DEFAULT NULL,
  `MaSP` int(11) DEFAULT NULL,
  `MaLH` int(11) DEFAULT NULL,
  `NgayHH` date DEFAULT NULL,
  `NgaySX` date DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `GiaNhap` decimal(10,2) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chitietpnh`
--

INSERT INTO `chitietpnh` (`MaCTPNH`, `MaPNH`, `MaSP`, `MaLH`, `NgayHH`, `NgaySX`, `SoLuong`, `GiaNhap`, `TrangThai`) VALUES
(1, 1, 1, 1, NULL, NULL, 100, 45000.00, 'ACTIVE'),
(2, 2, 2, 2, NULL, NULL, 200, 70000.00, 'ACTIVE'),
(3, 3, 3, 3, NULL, NULL, 150, 95000.00, 'ACTIVE'),
(4, 4, 4, 4, NULL, NULL, 180, 120000.00, 'ACTIVE'),
(5, 5, 5, 5, NULL, NULL, 220, 140000.00, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `diemtichluy`
--

CREATE TABLE `diemtichluy` (
  `MaDTL` int(11) NOT NULL,
  `DiemTL` int(11) DEFAULT NULL,
  `TiLeGiam` decimal(5,2) DEFAULT NULL,
  `GiamMax` int(11) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `diemtichluy`
--

INSERT INTO `diemtichluy` (`MaDTL`, `DiemTL`, `TiLeGiam`, `GiamMax`, `TrangThai`) VALUES
(1, 1000, 10.00, 100000, 'ACTIVE'),
(2, 2000, 15.50, 200000, 'ACTIVE'),
(3, 3000, 20.00, 300000, 'ACTIVE'),
(4, 4000, 5.00, 400000, 'ACTIVE'),
(5, 5000, 12.75, 50000, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `MaDH` int(11) NOT NULL,
  `MaKH` int(11) DEFAULT NULL,
  `MaKM` int(11) DEFAULT NULL,
  `MaNV` int(11) DEFAULT NULL,
  `PTTToan` enum('CASH','BANK') NOT NULL DEFAULT 'CASH',
  `NgayTT` datetime DEFAULT NULL,
  `maDTL` int(11) DEFAULT NULL,
  `tienKD` int(11) DEFAULT NULL,
  `TrangThai` enum('FINISHED') NOT NULL DEFAULT 'FINISHED',
  `TongTien` decimal(10,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `donhang`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKM` int(11) NOT NULL,
  `TenKM` varchar(100) DEFAULT NULL,
  `NgayKT` date DEFAULT NULL,
  `NgayBD` date DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE','DELETEED') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`MaKM`, `TenKM`, `NgayKT`, `NgayBD`, `TrangThai`) VALUES
(1, 'Giảm giá 10%', '2025-12-31', '2025-01-01', 'ACTIVE'),
(2, 'Giảm giá 15%', '2024-12-31', '2024-01-01', 'ACTIVE'),
(3, 'Giảm giá 20%', '2025-06-30', '2025-01-01', 'ACTIVE'),
(4, 'Giảm giá 5%', '2025-03-31', '2025-01-01', 'ACTIVE'),
(5, 'Giảm giá 12.75%', '2025-09-30', '2025-06-01', 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `loaisp`
--

CREATE TABLE `loaisp` (
  `MaLSP` int(11) NOT NULL,
  `TenLoaiSP` varchar(100) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `loaisp`
--

INSERT INTO `loaisp` (`MaLSP`, `TenLoaiSP`, `TrangThai`) VALUES
(1, 'Thực phẩm', 'ACTIVE'),
(2, 'Đồ uống', 'ACTIVE'),
(3, 'Gia dụng', 'ACTIVE'),
(4, 'Thời trang', 'ACTIVE'),
(5, 'Mỹ phẩm', 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `nhancc`
--

CREATE TABLE `nhancc` (
  `MaNCC` int(11) NOT NULL,
  `TenNCC` varchar(255) DEFAULT NULL,
  `SDT` varchar(20) DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nhancc`
--

INSERT INTO `nhancc` (`MaNCC`, `TenNCC`, `SDT`, `DiaChi`, `TrangThai`) VALUES
(1, 'Công ty Vinamilk', '0988881111', 'TP Hồ Chí Minh', 'ACTIVE'),
(2, 'Công ty TH True Milk', '0988882222', 'Nghệ An', 'ACTIVE'),
(3, 'Công ty Nestlé', '0988883333', 'Hà Nội', 'ACTIVE'),
(4, 'Công ty Orion', '0988884444', 'Bình Dương', 'ACTIVE'),
(5, 'Công ty Ajinomoto', '0988885555', 'Đồng Nai', 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` int(11) NOT NULL,
  `TenNV` varchar(100) DEFAULT NULL,
  `GioiTinh` enum('Nam','Nữ') DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `CCCD` varchar(12) DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `SDT` varchar(10) DEFAULT NULL,
  `Luong` decimal(10,2) DEFAULT NULL,
  `TrangThai` bit(1) DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `TenNV`, `GioiTinh`, `NgaySinh`, `CCCD`, `DiaChi`, `SDT`, `Luong`, `TrangThai`, `image`) VALUES
(1, 'Nguyễn Văn An', 'Nam', '1990-05-10', '123456789012', 'Hà Nội', '0912345671', 10000000.00, b'1', 'haha.jpeg'),
(2, 'Trần Thị Bích', 'Nữ', '1992-07-15', '234567890123', 'Hải Phòng', '0912345672', 12000000.00, b'1', NULL),
(3, 'Lê Văn Cường', 'Nam', '1993-09-20', '345678901234', 'Đà Nẵng', '0912345673', 15000000.00, b'1', NULL),
(4, 'Phạm Thị Dung', 'Nữ', '1994-11-25', '456789012345', 'TP Hồ Chí Minh', '0912345674', 11000000.00, b'1', NULL),
(5, 'Võ Minh Tuấn', 'Nam', '1995-03-30', '567890123456', 'Cần Thơ', '0912345675', 9000000.00, b'1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `phieunhaphang`
--

CREATE TABLE `phieunhaphang` (
  `MaPNH` int(11) NOT NULL,
  `TenPNH` varchar(255) DEFAULT NULL,
  `MaNCC` int(11) DEFAULT NULL,
  `MaNV` int(11) DEFAULT NULL,
  `NgayNhap` date DEFAULT NULL,
  `TrangThai` enum('FINISHED','UNFINISHED','DELETED') NOT NULL DEFAULT 'UNFINISHED'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `phieunhaphang`
--

INSERT INTO `phieunhaphang` (`MaPNH`, `TenPNH`, `MaNCC`, `MaNV`, `NgayNhap`, `TrangThai`) VALUES
(1, 'Nhập hàng tháng 3', 1, 1, '2024-03-10', 'FINISHED'),
(2, 'Nhập hàng thực phẩm', 2, 2, '2024-03-12', 'FINISHED'),
(3, 'Nhập hàng gia dụng', 3, 3, '2024-03-15', 'FINISHED'),
(4, 'Nhập hàng điện máy', 4, 4, '2024-03-18', 'FINISHED'),
(5, 'Nhập hàng thời trang', 5, 5, '2024-03-20', 'FINISHED');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` int(11) NOT NULL,
  `MaNCC` int(11) DEFAULT NULL,
  `MaLSP` int(11) DEFAULT NULL,
  `TenAnh` varchar(255) DEFAULT NULL,
  `Gia` decimal(10,2) DEFAULT NULL,
  `TenSP` varchar(255) DEFAULT NULL,
  `MoTa` varchar(555) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `SoLuongTon` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `MaNCC`, `MaLSP`, `TenAnh`, `Gia`, `TenSP`, `MoTa`, `TrangThai`, `SoLuongTon`) VALUES
(1, 1, 1, 'sp1.jpg', 15000.00, 'Bánh Oreo', 'Bánh quy nhân kem', 'ACTIVE', 0),
(2, 2, 2, 'sp2.jpg', 12000.50, 'Sữa Vinamilk', 'Sữa tươi nguyên chất', 'ACTIVE', 0),
(3, 3, 3, 'sp3.jpg', 25000.00, 'Mì Hảo Hảo', 'Mì gói chua cay', 'ACTIVE', 0),
(4, 4, 4, 'sp4.jpg', 8000.75, 'Nước suối Lavie', 'Nước khoáng thiên nhiên', 'ACTIVE', 0),
(5, 5, 5, 'sp5.jpg', 35000.00, 'Cà phê G7', 'Cà phê hòa tan đậm đà', 'ACTIVE', 0);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaNV` int(11) NOT NULL,
  `TenTK` varchar(100) DEFAULT NULL,
  `MatKhau` varchar(255) DEFAULT NULL,
  `Quyen` enum('ADMIN','QUẢN LÝ KHO','NHÂN VIÊN') DEFAULT NULL,
  `Gmail` varchar(100) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`MaNV`, `TenTK`, `MatKhau`, `Quyen`, `Gmail`, `TrangThai`) VALUES
(1, 'admin1', '123456', 'ADMIN', 'admin1@example.com', 'ACTIVE'),
(2, 'kho01', 'kho123', 'QUẢN LÝ KHO', 'kho01@example.com', 'ACTIVE'),
(3, 'nhanvien01', 'nv123', 'NHÂN VIÊN', 'nv01@example.com', 'INACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `thethanhvien`
--

CREATE TABLE `thethanhvien` (
  `MaTV` int(11) NOT NULL,
  `TenTV` varchar(100) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `DiemTL` int(11) DEFAULT 0,
  `SDT` varchar(20) DEFAULT NULL,
  `NgayBD` date DEFAULT NULL,
  `NgayKT` date DEFAULT NULL,
  `TenAnh` varchar(255) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `thethanhvien`
--

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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietdh`
--
ALTER TABLE `chitietdh`
  ADD KEY `FK_ChiTietDH_DonHang` (`MaDH`),
  ADD KEY `FK_ChiTietDH_SanPham` (`MaSP`);

--
-- Indexes for table `chitietkm`
--
ALTER TABLE `chitietkm`
  ADD PRIMARY KEY (`MaKM`,`MaSP`),
  ADD KEY `FK_ChiTietKM_SanPham` (`MaSP`);

--
-- Indexes for table `chitietpnh`
--
ALTER TABLE `chitietpnh`
  ADD PRIMARY KEY (`MaCTPNH`),
  ADD KEY `FK_ChiTietPNH_PhieuNhapHang` (`MaPNH`),
  ADD KEY `FK_ChiTietPNH_SanPham` (`MaSP`);

--
-- Indexes for table `diemtichluy`
--
ALTER TABLE `diemtichluy`
  ADD PRIMARY KEY (`MaDTL`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`MaDH`),
  ADD KEY `FK_DonHang_TheThanhVien` (`MaKH`),
  ADD KEY `FK_DonHang_KhuyenMai` (`MaKM`),
  ADD KEY `FK_DonHang_NhanVien` (`MaNV`),
  ADD KEY `FK_DonHang_DiemTL` (`maDTL`);

--
-- Indexes for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MaKM`);

--
-- Indexes for table `loaisp`
--
ALTER TABLE `loaisp`
  ADD PRIMARY KEY (`MaLSP`);

--
-- Indexes for table `nhancc`
--
ALTER TABLE `nhancc`
  ADD PRIMARY KEY (`MaNCC`),
  ADD UNIQUE KEY `SDT` (`SDT`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`),
  ADD UNIQUE KEY `SDT` (`SDT`);

--
-- Indexes for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD PRIMARY KEY (`MaPNH`),
  ADD KEY `FK_PhieuNhapHang_NhaNCC` (`MaNCC`),
  ADD KEY `FK_PhieuNhapHang_NhanVien` (`MaNV`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `FK_SanPham_NhaNCC` (`MaNCC`),
  ADD KEY `FK_SanPham_LoaiSP` (`MaLSP`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `thethanhvien`
--
ALTER TABLE `thethanhvien`
  ADD PRIMARY KEY (`MaTV`),
  ADD UNIQUE KEY `SDT` (`SDT`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chitietpnh`
--
ALTER TABLE `chitietpnh`
  MODIFY `MaCTPNH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `diemtichluy`
--
ALTER TABLE `diemtichluy`
  MODIFY `MaDTL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `MaDH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `MaKM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `loaisp`
--
ALTER TABLE `loaisp`
  MODIFY `MaLSP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `nhancc`
--
ALTER TABLE `nhancc`
  MODIFY `MaNCC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  MODIFY `MaPNH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `MaSP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `thethanhvien`
--
ALTER TABLE `thethanhvien`
  MODIFY `MaTV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietdh`
--
ALTER TABLE `chitietdh`
  ADD CONSTRAINT `FK_ChiTietDH_DonHang` FOREIGN KEY (`MaDH`) REFERENCES `donhang` (`MaDH`),
  ADD CONSTRAINT `FK_ChiTietDH_SanPham` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `chitietkm`
--
ALTER TABLE `chitietkm`
  ADD CONSTRAINT `FK_ChiTietKM_KhuyenMai` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`),
  ADD CONSTRAINT `FK_ChiTietKM_SanPham` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `chitietpnh`
--
ALTER TABLE `chitietpnh`
  ADD CONSTRAINT `FK_ChiTietPNH_PhieuNhapHang` FOREIGN KEY (`MaPNH`) REFERENCES `phieunhaphang` (`MaPNH`),
  ADD CONSTRAINT `FK_ChiTietPNH_SanPham` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `FK_DonHang_DiemTL` FOREIGN KEY (`maDTL`) REFERENCES `diemtichluy` (`MaDTL`),
  ADD CONSTRAINT `FK_DonHang_KhuyenMai` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`),
  ADD CONSTRAINT `FK_DonHang_NhanVien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `FK_DonHang_TheThanhVien` FOREIGN KEY (`MaKH`) REFERENCES `thethanhvien` (`MaTV`);

--
-- Constraints for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD CONSTRAINT `FK_PhieuNhapHang_NhaNCC` FOREIGN KEY (`MaNCC`) REFERENCES `nhancc` (`MaNCC`),
  ADD CONSTRAINT `FK_PhieuNhapHang_NhanVien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_SanPham_LoaiSP` FOREIGN KEY (`MaLSP`) REFERENCES `loaisp` (`MaLSP`),
  ADD CONSTRAINT `FK_SanPham_NhaNCC` FOREIGN KEY (`MaNCC`) REFERENCES `nhancc` (`MaNCC`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_TaiKhoan_NhanVien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

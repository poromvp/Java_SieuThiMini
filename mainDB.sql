-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2025 at 08:20 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

DROP DATABASE IF EXISTS SIEUTHIMINI;
CREATE DATABASE IF NOT EXISTS SIEUTHIMINI
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;
USE SIEUTHIMINI;
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
(20, 1, 10, 'ACTIVE'),
(21, 3, 2, 'ACTIVE'),
(21, 6, 1, 'ACTIVE'),
(21, 1, 1, 'ACTIVE'),
(22, 2, 1, 'ACTIVE'),
(22, 3, 1, 'ACTIVE'),
(22, 4, 1, 'ACTIVE'),
(23, 1, 1, 'ACTIVE'),
(23, 6, 1, 'ACTIVE'),
(24, 2, 5, 'ACTIVE'),
(24, 6, 2, 'ACTIVE'),
(24, 3, 5, 'ACTIVE'),
(24, 4, 20, 'ACTIVE'),
(25, 1, 24, 'ACTIVE'),
(25, 2, 1, 'ACTIVE'),
(25, 6, 2, 'ACTIVE'),
(25, 11, 20, 'ACTIVE'),
(25, 13, 20, 'ACTIVE'),
(25, 17, 8, 'ACTIVE'),
(25, 18, 2, 'ACTIVE'),
(25, 16, 50, 'ACTIVE'),
(25, 15, 1, 'ACTIVE'),
(26, 14, 6, 'ACTIVE'),
(26, 16, 12, 'ACTIVE'),
(26, 18, 5, 'ACTIVE'),
(27, 16, 5, 'ACTIVE'),
(27, 18, 3, 'ACTIVE'),
(27, 15, 3, 'ACTIVE'),
(27, 17, 2, 'ACTIVE'),
(27, 14, 1, 'ACTIVE'),
(27, 8, 2, 'ACTIVE'),
(27, 11, 2, 'ACTIVE'),
(28, 6, 1, 'ACTIVE'),
(28, 8, 1, 'ACTIVE'),
(28, 11, 1, 'ACTIVE'),
(28, 4, 1, 'ACTIVE'),
(28, 18, 2, 'ACTIVE'),
(28, 2, 3, 'ACTIVE'),
(28, 1, 2, 'ACTIVE'),
(28, 3, 1, 'ACTIVE'),
(28, 16, 2, 'ACTIVE'),
(28, 17, 1, 'ACTIVE'),
(29, 3, 10, 'ACTIVE'),
(29, 16, 10, 'ACTIVE'),
(29, 18, 1, 'ACTIVE'),
(30, 11, 2, 'ACTIVE'),
(30, 13, 2, 'ACTIVE'),
(30, 17, 20, 'ACTIVE'),
(31, 16, 33, 'ACTIVE'),
(31, 17, 12, 'ACTIVE'),
(32, 17, 10, 'ACTIVE'),
(32, 3, 15, 'ACTIVE'),
(32, 2, 10, 'ACTIVE'),
(33, 15, 50, 'ACTIVE'),
(34, 3, 20, 'ACTIVE'),
(34, 1, 11, 'ACTIVE'),
(35, 3, 25, 'ACTIVE'),
(35, 6, 5, 'ACTIVE'),
(35, 15, 5, 'ACTIVE');

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
(1, 1, 1, 1, '2023-05-01', '2025-05-31', 100, 45000.00, 'ACTIVE'),
(2, 2, 2, 2, '2021-05-01', '2025-05-31', 200, 70000.00, 'ACTIVE'),
(3, 3, 3, 3, '2021-05-01', '2025-05-31', 150, 95000.00, 'ACTIVE'),
(4, 4, 4, 4, '2021-05-01', '2025-05-31', 180, 120000.00, 'ACTIVE'),
(5, 5, 5, 5, '2021-05-01', '2025-05-31', 220, 140000.00, 'ACTIVE');

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
(20, 6, NULL, 5, 'CASH', '2025-04-20 13:00:00', 2, 500000, 'FINISHED', 150000.00),
(21, NULL, 1, 3, 'CASH', '2025-05-07 15:08:13', NULL, 54000, 'FINISHED', 45000.00),
(22, NULL, 1, 3, 'BANK', '2025-05-09 15:25:58', NULL, -1, 'FINISHED', 57000.00),
(23, 6, 1, 9, 'CASH', '2025-05-01 10:07:13', NULL, 22000, 'FINISHED', 21000.00),
(24, 8, 1, 9, 'CASH', '2025-05-02 10:21:47', NULL, 700000, 'FINISHED', 684000.00),
(25, 9, 1, 9, 'CASH', '2025-05-03 10:24:49', NULL, 1600000, 'FINISHED', 1496000.00),
(26, 11, 1, 9, 'CASH', '2025-05-04 10:27:41', NULL, 300000, 'FINISHED', 287000.00),
(27, 12, 1, 9, 'CASH', '2025-05-05 10:31:14', NULL, 275000, 'FINISHED', 275000.00),
(28, 13, 1, 9, 'CASH', '2025-05-06 10:32:37', NULL, 230000, 'FINISHED', 228000.00),
(29, 6, 1, 9, 'CASH', '2025-05-08 10:36:55', NULL, 243000, 'FINISHED', 243000.00),
(30, 8, 1, 9, 'CASH', '2025-05-10 10:39:55', NULL, 544000, 'FINISHED', 544000.00),
(31, 12, 1, 9, 'CASH', '2025-05-11 10:40:52', NULL, 670000, 'FINISHED', 663000.00),
(32, 9, 1, 9, 'CASH', '2025-05-12 10:42:03', 1, 650000, 'FINISHED', 567000.00),
(33, 13, 1, 9, 'CASH', '2025-05-13 10:42:40', NULL, 1000000, 'FINISHED', 1000000.00),
(34, 6, 1, 9, 'CASH', '2025-05-14 10:44:46', NULL, 350000, 'FINISHED', 339000.00),
(35, 11, 1, 9, 'CASH', '2025-05-14 10:45:28', NULL, 460000, 'FINISHED', 460000.00);

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
(1, 'Giảm giá tháng 1', '2025-01-31', '2025-01-01', 'ACTIVE'),
(2, 'Giảm giá tháng 2', '2024-02-28', '2025-02-01', 'ACTIVE'),
(3, 'Giảm giá tháng 3', '2025-03-31', '2025-03-01', 'ACTIVE'),
(4, 'Giảm giá tháng 4', '2025-04-30', '2025-04-01', 'ACTIVE'),
(5, 'Giảm giá tháng 5', '2025-05-31', '2025-01-01', 'ACTIVE');

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
(2, 'Các loại gia vị', 'ACTIVE'),
(3, 'Đồ uống', 'ACTIVE'),
(4, 'Bánh kẹo', 'ACTIVE'),
(5, 'Vật dụng gia đình', 'ACTIVE');

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
(1, 'Công ty A', '0988881111', 'TP Hồ Chí Minh', 'ACTIVE'),
(2, 'Công ty B', '0988882222', 'Nghệ An', 'ACTIVE'),
(3, 'Công ty C', '0988883333', 'Hà Nội', 'ACTIVE'),
(4, 'Công ty D', '0988884444', 'Bình Dương', 'ACTIVE'),
(5, 'Công ty E', '0988885555', 'Đồng Nai', 'ACTIVE');

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
(1, 'VÕ TRỊNH KHANG', 'Nam', '2005-04-27', '123456789012', 'QUẢNG NGÃI', '0762779663', 10000000.00, b'1', 'nv1.jpg'),
(2, 'Phong Vũ', 'Nam', '2005-04-27', '123456789013', 'Đồng Nai', '0762779664', 5000000.00, b'1', 'nv2.jpeg'),
(3, 'KHANG', 'Nam', '2005-04-27', '123456789014', 'QUẢNG NGÃI KKK', '0762779665', 10000000.00, b'1', 'itachi__1.gif'),
(4, 'Nguyễn Văn An', 'Nam', '1990-05-10', '123456789012', 'Hà Nội', '0912345671', 10000000.00, b'1', 'nv4.jpeg'),
(5, 'Trần Thị Bích', 'Nữ', '1992-07-15', '234567890123', 'Hải Phòng', '0912345672', 12000000.00, b'1', 'nv5.jpg'),
(6, 'Lê Văn Cường', 'Nam', '1993-09-20', '345678901234', 'Đà Nẵng', '0912345673', 15000000.00, b'1', 'nv6.jpg'),
(7, 'Phạm Thị Dung', 'Nữ', '1994-11-25', '456789012345', 'TP Hồ Chí Minh', '0912345674', 11000000.00, b'1', 'nv7.jpg'),
(8, 'Võ Minh Tuấn', 'Nam', '1995-03-30', '567890123456', 'Cần Thơ', '0912345675', 9000000.00, b'1', 'nv8.pngg'),
(9, 'Lê Quang Kiệt', 'Nam', '2005-02-23', '079079079079', '123 Nguyễn Trãi, Tp.Hồ Chí Minh', '0909009090', 15000000.00, b'1', 'hanh.JPG');

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
(1, 1, 1, 'camoi.png', 10000.00, 'Cá mòi', 'Cá mòi ngon, đậm đà', 'ACTIVE', 12),
(2, 1, 1, 'thithop.png', 20000.00, 'Thịt hộp', 'Thịt hộp siêu ngon', 'ACTIVE', 81),
(3, 1, 1, 'xucxich.png', 15000.00, 'Xúc xích', 'Xúc xích hihi', 'ACTIVE', 124),
(4, 1, 1, 'trungga.png', 25000.00, 'Trứng gà ', 'Trứng gà công nghiệp', 'ACTIVE', 59),
(5, 1, 1, 'mitom.png', 5000.00, 'Mì tôm', 'Mì ăn liền Hảo Hảo', 'ACTIVE', 500),
(6, 1, 2, 'dauan.png', 12000.00, 'Dầu ăn Neptune', 'Dầu ăn cao cấp', 'ACTIVE', 109),
(7, 1, 2, 'duong.png', 8000.00, 'Đường trắng', 'Đường tinh luyện', 'ACTIVE', 150),
(8, 1, 2, 'muoi.png', 18000.00, 'Muối', 'Muối sạch', 'ACTIVE', 97),
(9, 1, 2, 'hatnem.png', 3000.00, 'Hạt nêm', 'Hạt nêm siêu ngon', 'ACTIVE', 500),
(10, 1, 2, 'nuocmam.png', 4000.00, 'Nước mắm', 'Nước mắm cá cơm', 'ACTIVE', 90),
(11, 2, 3, 'cocacola.png', 10000.00, 'Coca Cola', 'Nước ngọt có ga', 'ACTIVE', 175),
(12, 2, 3, 'pepsi.png', 9000.00, 'Pepsi', 'Nước ngọt vị cola', 'ACTIVE', 180),
(13, 2, 3, 'c2.png', 12000.00, 'Trà xanh C2', 'Trà xanh giải nhiệt', 'ACTIVE', 138),
(14, 2, 3, 'revive.png', 15000.00, 'Revive', 'Nước bù khoáng', 'ACTIVE', 133),
(15, 2, 3, 'nuoccam.png', 20000.00, 'Nước cam Twister', 'Nước ép cam', 'ACTIVE', 71),
(16, 2, 4, 'banhoshi.png', 11000.00, 'Bánh oshi', 'Bánh ngon', 'ACTIVE', 58),
(17, 2, 4, 'banhmi.png', 25000.00, 'Bánh mỳ Kinh Đô', 'Bánh ngon', 'ACTIVE', 47),
(18, 2, 4, 'banhxep.png', 13000.00, 'Bánh xếp', 'Bánh ngon', 'ACTIVE', 107),
(19, 2, 5, 'banchai.png', 8000.00, 'Bàn chải PS', 'Bàn chải xịn', 'ACTIVE', 300),
(20, 2, 5, 'thamchuichan.png', 7000.00, 'Thảm chùi chân', 'Thảm chùi chân xịn ', 'ACTIVE', 220);

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
(1, 'VOTRINHKHANG', 'VOTRINHKHANG', 'ADMIN', 'VOTRINHKHANG@gmail.com', 'ACTIVE'),
(2, 'TRINHKHANG', 'TRINHKHANG', 'QUẢN LÝ KHO', 'TRINHKHANG@gmail.com', 'ACTIVE'),
(3, 'KHANG', 'KHANG', 'NHÂN VIÊN', 'KHANG@gmail.com', 'ACTIVE'),
(4, 'admin1', '123456', 'ADMIN', 'admin1@example.com', 'ACTIVE'),
(5, 'kho01', 'kho123', 'QUẢN LÝ KHO', 'kho01@example.com', 'ACTIVE'),
(6, 'nhanvien01', 'nv123', 'NHÂN VIÊN', 'nv01@example.com', 'INACTIVE'),
(9, 'poromvp', '123456', 'NHÂN VIÊN', 'KHANG@gmail.com', 'ACTIVE');

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
(1, 'Nguyễn Hữu Hạnh', '1985-01-14', 'Hà Nội', 200, '0987456123', '2023-04-01', '2025-04-30', 'hanh.JPG', 'INACTIVE'),
(2, 'Trần Ngọc Minh', '1988-06-22', 'Hải Dương', 150, '0987456124', '2023-02-01', '2025-02-01', 'nv1.jpg', 'INACTIVE'),
(3, 'Lê Thành Đạt', '1990-09-05', 'Đà Nẵng', 300, '0987456125', '2023-03-01', '2025-03-01', 'nv1.jpg', 'INACTIVE'),
(4, 'Phạm Quang Dũng', '1992-12-18', 'Bắc Ninh', 180, '0987456126', '2023-04-01', '2025-04-01', 'nv2.jpeg', 'INACTIVE'),
(5, 'Võ Hoài Nam', '1995-08-30', 'Cần Thơ', 250, '0987456127', '2023-05-01', '2025-05-01', 'nv1.jpg', 'INACTIVE'),
(6, 'Ô Ngọc Mi', '2005-04-11', 'Tp.HCM Q8', 603, '1234567899', '2025-04-25', '2027-04-25', 'nv3.jpg', 'ACTIVE'),
(8, 'Nguyễn Ngọc Yến Nhi', '2005-02-23', 'Mạc Vân, p12, q.8', 1228, '1234567898', '2025-04-27', '2027-04-27', 'nv2.jpeg', 'ACTIVE'),
(9, 'Lê Quang Kiệt', '2005-02-23', 'Bãi Sau, TP.HCM', 1063, '0977723621', '2025-04-27', '2027-04-27', 'nv4.jpeg', 'ACTIVE'),
(10, 'jkl;', '2005-02-23', 'sdfgsgdf', 0, '0977723625', '2025-04-28', '2027-04-28', 'nv3.jpg', 'INACTIVE'),
(11, 'Hào Hùng Cường', '2005-02-23', 'Bắc Từ Lim, HN', 747, '1234567897', '2025-05-07', '2027-05-07', 'nv7.jpg', 'ACTIVE'),
(12, 'Dư Thị Quỳnh Nga', '2005-02-23', 'CMT8, 3/2', 938, '1234567896', '2025-05-07', '2027-05-07', 'nv8.png', 'ACTIVE'),
(13, 'Hồ Bích Thùy', '2005-02-23', 'Nguyẽn Duy, phường 12, quận 8', 1228, '0123456789', '2025-05-07', '2027-05-07', 'nv4.jpg', 'ACTIVE');

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
  MODIFY `MaDH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

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
  MODIFY `MaNV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  MODIFY `MaPNH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `MaSP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `thethanhvien`
--
ALTER TABLE `thethanhvien`
  MODIFY `MaTV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

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

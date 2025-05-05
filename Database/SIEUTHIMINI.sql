-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2025 at 08:04 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

DROP DATABASE IF EXISTS SIEUTHIMINI;
CREATE DATABASE IF NOT EXISTS SIEUTHIMINI
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;
USE SIEUTHIMINI;
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
-- Table structure for table `chitietkm`
--
CREATE TABLE `chitietkm` (
  `MaKM` int(11) NOT NULL,
  `MaSP` int(11) NOT NULL,
  `TileGiam` decimal(5,2) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
-- Table structure for table `loaisp`
--

CREATE TABLE `loaisp` (
  `MaLSP` int(11) NOT NULL,
  `TenLoaiSP` varchar(100) DEFAULT NULL,
  `TrangThai` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


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

DROP DATABASE IF EXISTS SIEUTHIMINI;
CREATE DATABASE IF NOT EXISTS SIEUTHIMINI 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;
USE SIEUTHIMINI;
START TRANSACTION;
SET time_zone = "+00:00";

-- Tạo bảng, bỏ NVARCHAR, dùng VARCHAR với UTF-8
CREATE TABLE NhanVien (
    MaNV INT AUTO_INCREMENT PRIMARY KEY,
    TenNV VARCHAR(100), 
    GioiTinh ENUM('Nam','Nữ'),
    NgaySinh DATE,
    CCCD VARCHAR(12),  
    DiaChi VARCHAR(255),
    SDT VARCHAR(10) UNIQUE,
    Luong DECIMAL(10,2),
    TrangThai BIT(1)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE TaiKhoan (
    MaNV INT PRIMARY KEY,
    TenTK VARCHAR(100) ,
    MatKhau VARCHAR(255) ,
    Quyen ENUM('ADMIN','QUẢN LÝ KHO','NHÂN VIÊN'),
    Gmail VARCHAR(100),
    TrangThai ENUM('ACTIVE', 'INACTIVE') 
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE TheThanhVien (
    MaTV INT AUTO_INCREMENT PRIMARY KEY,
    TenTV VARCHAR(100) ,
    NgaySinh DATE,
    DiaChi VARCHAR(255) ,
    DiemTL INT DEFAULT 0,
    SDT VARCHAR(20) UNIQUE,
    NgayBD DATE,
    NgayKT DATE,
    TenAnh VARCHAR(255) ,
    TrangThai ENUM('ACTIVE', 'INACTIVE')
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE KhuyenMai (
    MaKM INT AUTO_INCREMENT PRIMARY KEY,
    TenKM VARCHAR(100) ,
    NgayKT DATE,
    NgayBD DATE,
	TrangThai ENUM('ACTIVE', 'INACTIVE', 'DELETEED')
);

CREATE TABLE DonHang (
	MaDH INT AUTO_INCREMENT PRIMARY KEY,
    MaKH INT ,
    MaKM INT ,
    MaNV INT,
    PTTToan ENUM('CASH', 'BANK') NOT NULL DEFAULT 'CASH',
    NgayTT DATETIME,  
    maDTL int ,
    tienKD int , 
    TrangThai ENUM('FINISHED') NOT NULL DEFAULT 'FINISHED'
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE LoaiSP (
    MaLSP INT AUTO_INCREMENT PRIMARY KEY,
    TenLoaiSP VARCHAR(100) ,
	TrangThai ENUM('ACTIVE', 'INACTIVE')
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE NhaNCC (
    MaNCC INT AUTO_INCREMENT PRIMARY KEY,
    TenNCC VARCHAR(255) ,
    SDT VARCHAR(20) UNIQUE,
    DiaChi VARCHAR(255) ,
   	TrangThai ENUM('ACTIVE', 'INACTIVE')
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE SanPham (
    MaSP INT AUTO_INCREMENT PRIMARY KEY,
    MaNCC INT,
    MaLSP INT,
    TenAnh VARCHAR(255) ,
    Gia DECIMAL(10,2),
    TenSP VARCHAR(255) ,
    SoLuongTon INT DEFAULT 0,
    MoTa VARCHAR(555) ,
   	TrangThai ENUM('ACTIVE', 'INACTIVE'),
    soLuongTon int
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE PhieuNhapHang (
    MaPNH INT AUTO_INCREMENT PRIMARY KEY,
    TenPNH VARCHAR(255) ,
    MaNCC INT,
    MaNV INT,
    NgayNhap DATE,
    TrangThai ENUM('FINISHED') NOT NULL DEFAULT 'FINISHED'
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE ChiTietPNH (
    MaCTPNH INT AUTO_INCREMENT PRIMARY KEY,
    MaPNH INT,
    MaSP INT,
    MaLH INT,
    NgayHH date,
    NgaySX date,
    SoLuong INT,
    GiaNhap DECIMAL(10,2),
	TrangThai ENUM('ACTIVE') NOT NULL DEFAULT 'ACTIVE'
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE ChiTietKM (
    MaKM INT,
    MaSP INT,
    TileGiam DECIMAL(5,2),
    PRIMARY KEY (MaKM, MaSP),
	TrangThai ENUM('ACTIVE', 'INACTIVE')
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE ChiTietDH (
    MaDH INT,
    MaSP INT,
    SoLuong INT,
	TrangThai ENUM('ACTIVE', 'INACTIVE')
  --  PRIMARY KEY (MaDH, MaSP)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE DiemTichLuy (
    MaDTL  INT AUTO_INCREMENT PRIMARY KEY,
    DiemTL int,
    TiLeGiam DECIMAL(5,2),
    GiamMax INT,
	TrangThai ENUM('ACTIVE', 'INACTIVE')
    )CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;




ALTER TABLE TaiKhoan ADD CONSTRAINT FK_TaiKhoan_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON DELETE CASCADE;

ALTER TABLE DonHang 
	ADD CONSTRAINT FK_DonHang_TheThanhVien FOREIGN KEY (MaKH) REFERENCES TheThanhVien(MaTV),
	ADD CONSTRAINT FK_DonHang_KhuyenMai FOREIGN KEY (MaKM) REFERENCES KhuyenMai(MaKM),
	ADD CONSTRAINT FK_DonHang_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
	ADD CONSTRAINT FK_DonHang_DiemTL FOREIGN KEY (maDTL) REFERENCES DiemTichLuy(maDTL);

ALTER TABLE SanPham
	ADD CONSTRAINT FK_SanPham_NhaNCC FOREIGN KEY (MaNCC) REFERENCES NhaNCC(MaNCC),
	ADD CONSTRAINT FK_SanPham_LoaiSP FOREIGN KEY (MaLSP) REFERENCES LoaiSP(MaLSP);

ALTER TABLE PhieuNhapHang ADD CONSTRAINT FK_PhieuNhapHang_NhaNCC FOREIGN KEY (MaNCC) REFERENCES NhaNCC(MaNCC),
	ADD CONSTRAINT FK_PhieuNhapHang_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV);

ALTER TABLE ChiTietPNH ADD CONSTRAINT FK_ChiTietPNH_PhieuNhapHang FOREIGN KEY (MaPNH) REFERENCES PhieuNhapHang(MaPNH),
	ADD CONSTRAINT FK_ChiTietPNH_SanPham FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP);

ALTER TABLE ChiTietKM ADD CONSTRAINT FK_ChiTietKM_KhuyenMai FOREIGN KEY (MaKM) REFERENCES KhuyenMai(MaKM),
	ADD CONSTRAINT FK_ChiTietKM_SanPham FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP);

ALTER TABLE ChiTietDH ADD CONSTRAINT FK_ChiTietDH_DonHang FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH),
 ADD CONSTRAINT FK_ChiTietDH_SanPham FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP);




-- SELECT donhang.MaDH, donhang.MaNV, nhanvien.TenNV, 
-- 		donhang.NgayTT, donhang.TrangThai, thethanhvien.TenTV , 
-- 		sum(chitietdh.SoLuong * sanpham.Gia) AS Tongtien,
--         khuyenmai.TileGiam
-- FROM sieuthimini.donhang
-- join nhanvien on nhanvien.MaNV = donhang.MaNV
-- left join thethanhvien on thethanhvien.MaTV = donhang.MaKH
-- left join chitietdh on donhang.MaDH = chitietdh.MaDH
-- left join sanpham on sanpham.MaSP = chitietdh.MaSP
-- left join khuyenmai on donhang.MaKM = khuyenmai.MaKM
-- group by MaDH
-- having Tongtien > 70000
-- order by Tongtien desc;
 -- select * from donhang

COMMIT;
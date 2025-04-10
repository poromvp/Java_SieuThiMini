DROP DATABASE IF EXISTS SIEUTHIMINI;
CREATE DATABASE IF NOT EXISTS SIEUTHIMINI 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;
USE SIEUTHIMINI;

-- Tạo bảng, bỏ NVARCHAR, dùng VARCHAR với UTF-8
CREATE TABLE NhanVien (
    MaNV INT AUTO_INCREMENT PRIMARY KEY,
    TenNV VARCHAR(100), 
    GioiThieu VARCHAR(255), 
    NgaySinh DATE,
    CCCD VARCHAR(20),  
    DiaChi VARCHAR(255),
    ChucVu VARCHAR(50),
    SDT VARCHAR(20) UNIQUE,
    Luong DECIMAL(10,2),
    TrangThai ENUM('ACTIVE', 'INACTIVE')
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE TaiKhoan (
    MaNV INT PRIMARY KEY,
    TenTK VARCHAR(50) ,
    MatKhau VARCHAR(255) ,
    SDT VARCHAR(20) UNIQUE,
    Quyen VARCHAR(50) ,
    Gmail VARCHAR(100),
    TenAnh VARCHAR(255) ,
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
    TileGiam DECIMAL(5,2),
	TrangThai ENUM('ACTIVE', 'INACTIVE', 'DELETEED')
);

CREATE TABLE DonHang (
	MaDH INT AUTO_INCREMENT PRIMARY KEY,
    MaKH INT,
    MaKM INT NULL,
    MaNV INT,
    PTTToan ENUM('CASH', 'BANK') NOT NULL DEFAULT 'CASH',
    NgayTT DATETIME,  
    maDTL int ,
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
    MoTa VARCHAR(555) ,
   	TrangThai ENUM('ACTIVE', 'INACTIVE')
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE QuayHang (
    MaQH INT AUTO_INCREMENT PRIMARY KEY,
    MaLSP INT,
    TenQH VARCHAR(100) ,
   	TrangThai ENUM('ACTIVE', 'INACTIVE')
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE PhieuNhapHang (
    MaPNH INT AUTO_INCREMENT PRIMARY KEY,
    TenPNH VARCHAR(255) ,
    MaNCC INT,
    MaNV INT,
    NgayNhap DATE,
    TrangThai ENUM('FINISHED', 'UNFINISHED', 'DELETED') NOT NULL DEFAULT 'UNFINISHED'
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
	TrangThai ENUM('ACTIVE', 'INACTIVE')
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
	TrangThai ENUM('ACTIVE', 'INACTIVE'),
    PRIMARY KEY (MaDH, MaSP)
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

ALTER TABLE QuayHang ADD CONSTRAINT FK_QuayHang_LoaiSP FOREIGN KEY (MaLSP) REFERENCES LoaiSP(MaLSP);

ALTER TABLE PhieuNhapHang ADD CONSTRAINT FK_PhieuNhapHang_NhaNCC FOREIGN KEY (MaNCC) REFERENCES NhaNCC(MaNCC),
	ADD CONSTRAINT FK_PhieuNhapHang_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV);

ALTER TABLE ChiTietPNH ADD CONSTRAINT FK_ChiTietPNH_PhieuNhapHang FOREIGN KEY (MaPNH) REFERENCES PhieuNhapHang(MaPNH),
	ADD CONSTRAINT FK_ChiTietPNH_SanPham FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP);

ALTER TABLE ChiTietKM ADD CONSTRAINT FK_ChiTietKM_KhuyenMai FOREIGN KEY (MaKM) REFERENCES KhuyenMai(MaKM),
	ADD CONSTRAINT FK_ChiTietKM_SanPham FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP);

ALTER TABLE ChiTietDH ADD CONSTRAINT FK_ChiTietDH_DonHang FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH),
 ADD CONSTRAINT FK_ChiTietDH_SanPham FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP);

INSERT INTO DiemTichLuy (MaDTL, DiemTL, TileGiam, GiamMax, TrangThai) VALUES
(1, 1000, 10.00,100000, 'ACTIVE'),
(2, 2000, 15.50, 200000, 'ACTIVE'),
(3, 3000, 20.00, 300000, 'ACTIVE'),
(4, 4000, 5.00, 400000, 'ACTIVE'),
(5, 5000, 12.75, 50000,  'ACTIVE');


INSERT INTO NhanVien (TenNV, GioiThieu, NgaySinh, DiaChi, ChucVu, SDT, Luong, TrangThai) VALUES
('Nguyễn Văn An', 'Nhân viên kinh doanh', '1990-05-10', 'Hà Nội', 'Nhân viên', '0912345671', 10000000, 'ACTIVE'),
('Trần Thị Bích', 'Nhân viên kế toán', '1992-07-15', 'Hải Phòng', 'Kế toán', '0912345672', 12000000, 'ACTIVE'),
('Lê Văn Cường', 'Nhân viên IT', '1993-09-20', 'Đà Nẵng', 'IT', '0912345673', 15000000, 'ACTIVE'),
('Phạm Thị Dung', 'Nhân viên HR', '1994-11-25', 'TP Hồ Chí Minh', 'HR', '0912345674', 11000000, 'ACTIVE'),
('Võ Minh Tuấn', 'Nhân viên bán hàng', '1995-03-30', 'Cần Thơ', 'Bán hàng', '0912345675', 9000000, 'ACTIVE');


INSERT INTO TaiKhoan (MaNV, TenTK, MatKhau, SDT, Quyen, Gmail, TenAnh, TrangThai) VALUES
(1, 'an_nv', '123456', '0912345671', 'Admin', 'an_nv@gmail.com', 'an.jpg', 'ACTIVE'),
(2, 'bich_tt', 'abcdef', '0912345672', 'Kế toán', 'bich_tt@gmail.com', 'bich.jpg', 'ACTIVE'),
(3, 'cuong_lv', 'pass123', '0912345673', 'IT', 'cuong_lv@gmail.com', 'cuong.jpg', 'ACTIVE'),
(4, 'dung_pt', 'dung456', '0912345674', 'HR', 'dung_pt@gmail.com', 'dung.jpg', 'ACTIVE'),
(5, 'tuan_vm', 'tuan789', '0912345675', 'Bán hàng', 'tuan_vm@gmail.com', 'tuan.jpg', 'ACTIVE');






-- 3. Chèn dữ liệu vào bảng TheThanhVien
INSERT INTO TheThanhVien (TenTV, NgaySinh, DiaChi, DiemTL, SDT, NgayBD, NgayKT, TenAnh, TrangThai) VALUES
('Nguyễn Hữu Hạnh', '1985-01-15', 'Hà Nội', 200, '0987456123', '2023-01-01', '2025-01-01', 'hanh.jpg', 'ACTIVE'),
('Trần Ngọc Minh', '1988-06-22', 'Hải Dương', 150, '0987456124', '2023-02-01', '2025-02-01', 'minh.jpg', 'ACTIVE'),
('Lê Thành Đạt', '1990-09-05', 'Đà Nẵng', 300, '0987456125', '2023-03-01', '2025-03-01', 'dat.jpg', 'ACTIVE'),
('Phạm Quang Dũng', '1992-12-18', 'Bắc Ninh', 180, '0987456126', '2023-04-01', '2025-04-01', 'dung.jpg', 'ACTIVE'),
('Võ Hoài Nam', '1995-08-30', 'Cần Thơ', 250, '0987456127', '2023-05-01', '2025-05-01', 'nam.jpg', 'ACTIVE');



-- 4. Chèn dữ liệu vào bảng LoaiSP
INSERT INTO LoaiSP (TenLoaiSP, TrangThai) VALUES
('Thực phẩm', 'ACTIVE'),
('Đồ uống', 'ACTIVE'),
('Gia dụng', 'ACTIVE'),
('Thời trang', 'ACTIVE'),
('Mỹ phẩm', 'ACTIVE');

INSERT INTO QuayHang (MaLSP, TenQH, TrangThai) VALUES
(1, 'Khu thực phẩm tươi sống', 'ACTIVE'),
(2, 'Khu nước giải khát', 'ACTIVE'),
(3, 'Khu gia dụng và bếp', 'ACTIVE'),
(4, 'Khu thiết bị điện tử', 'ACTIVE'),
(5, 'Khu thời trang', 'ACTIVE');


-- 5. Chèn dữ liệu vào bảng NhaNCC
INSERT INTO NhaNCC (TenNCC, SDT, DiaChi, TrangThai) VALUES
('Công ty Vinamilk', '0988881111', 'TP Hồ Chí Minh', 'ACTIVE'),
('Công ty TH True Milk', '0988882222', 'Nghệ An', 'ACTIVE'),
('Công ty Nestlé', '0988883333', 'Hà Nội', 'ACTIVE'),
('Công ty Orion', '0988884444', 'Bình Dương', 'ACTIVE'),
('Công ty Ajinomoto', '0988885555', 'Đồng Nai', 'ACTIVE');


INSERT INTO KhuyenMai (MaKM, TenKM, NgayKT, NgayBD, TileGiam, TrangThai) VALUES
(1, 'Giảm giá 10%', '2025-12-31', '2025-01-01', 10.00, 'ACTIVE'),
(2, 'Giảm giá 15%', '2024-12-31', '2024-01-01', 15.00, 'ACTIVE'),
(3, 'Giảm giá 20%', '2025-06-30', '2025-01-01', 20.00, 'ACTIVE'),
(4, 'Giảm giá 5%', '2025-03-31', '2025-01-01', 5.00, 'ACTIVE'),
(5, 'Giảm giá 12.75%', '2025-09-30', '2025-06-01', 12.75, 'ACTIVE');


INSERT INTO SanPham ( MaNCC, MaLSP, TenAnh, Gia, TenSP, MoTa, TrangThai) VALUES
(1, 1, 'sp1.jpg', 15000.00, 'Bánh Oreo', 'Bánh quy nhân kem', 'ACTIVE'),
(2, 2, 'sp2.jpg', 12000.50, 'Sữa Vinamilk', 'Sữa tươi nguyên chất', 'ACTIVE'),
(3, 3, 'sp3.jpg', 25000.00, 'Mì Hảo Hảo', 'Mì gói chua cay', 'ACTIVE'),
(4, 4, 'sp4.jpg', 8000.75, 'Nước suối Lavie', 'Nước khoáng thiên nhiên', 'ACTIVE'),
(5, 5, 'sp5.jpg', 35000.00, 'Cà phê G7', 'Cà phê hòa tan đậm đà', 'ACTIVE');


INSERT INTO ChiTietKM (MaKM, MaSP, TileGiam, TrangThai) VALUES
(1, 1, 10.00, 'ACTIVE'),
(2, 2, 15.50, 'ACTIVE'),
(3, 3, 20.00, 'ACTIVE'),
(4, 4, 5.00, 'ACTIVE'),
(5, 5, 12.75, 'ACTIVE');


-- 7. Chèn dữ liệu vào bảng DonHang
INSERT INTO DonHang (MaKH, MaKM, MaNV, PTTToan, NgayTT, maDTL, TrangThai) VALUES
(1, 1, 1, 'BANK', '2024-03-10',1, 'FINISHED'),
(2, 2, 2, 'CASH', '2024-03-11', 2,  'FINISHED'),
(3, 2, 3, 'BANK', '2024-03-12', 3,  'FINISHED'),
(4, null, 4, 'CASH', '2024-03-13',4,  'FINISHED'),
(5, 3, 5, 'CASH', '2024-03-14', 5, 'FINISHED');

-- 8. Chèn dữ liệu vào bảng ChiTietDH
INSERT INTO ChiTietDH (MaDH, MaSP, SoLuong, TrangThai) VALUES
(1, 1, 2, 'ACTIVE'),
(2, 2, 1, 'ACTIVE'),
(3, 3, 3, 'ACTIVE'),
(4, 4, 1, 'ACTIVE'),
(5, 5, 2, 'ACTIVE');



INSERT INTO PhieuNhapHang (TenPNH, MaNCC, MaNV, NgayNhap, TrangThai) VALUES
('Nhập hàng tháng 3', 1, 1, '2024-03-10', 'FINISHED'),
('Nhập hàng thực phẩm', 2, 2, '2024-03-12', 'FINISHED'),
('Nhập hàng gia dụng', 3, 3, '2024-03-15', 'FINISHED'),
('Nhập hàng điện máy', 4, 4, '2024-03-18', 'FINISHED'),
('Nhập hàng thời trang', 5, 5, '2024-03-20', 'FINISHED');

INSERT INTO ChiTietPNH (MaPNH, MaSP, MaLH, SoLuong, GiaNhap, TrangThai) VALUES
(1, 1, 1, 100, 45000, 'ACTIVE'),
(2, 2, 2, 200, 70000, 'ACTIVE'),
(3, 3, 3, 150, 95000, 'ACTIVE'),
(4, 4, 4, 180, 120000, 'ACTIVE'),
(5, 5, 5, 220, 140000, 'ACTIVE');





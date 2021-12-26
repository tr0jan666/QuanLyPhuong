-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: new_schema
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cach_ly`
--

DROP TABLE IF EXISTS `cach_ly`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cach_ly` (
  `idCachLy` int NOT NULL,
  `idNhankhau` int NOT NULL,
  `diaDiemCachLy` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `thoiGianBatDau` datetime NOT NULL,
  `mucDoCachLy` int NOT NULL,
  `hetBenh` int NOT NULL,
  PRIMARY KEY (`idCachLy`),
  KEY `id_NhanKhau_idx` (`idNhankhau`),
  CONSTRAINT `id_NhanKhau` FOREIGN KEY (`idNhankhau`) REFERENCES `test` (`idNhanKhau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cach_ly`
--

LOCK TABLES `cach_ly` WRITE;
/*!40000 ALTER TABLE `cach_ly` DISABLE KEYS */;
/*!40000 ALTER TABLE `cach_ly` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cmt/cccd`
--

DROP TABLE IF EXISTS `cmt/cccd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cmt/cccd` (
  `ID` int NOT NULL,
  `idNhanKhau` int DEFAULT NULL,
  `soCMT` varchar(12) NOT NULL,
  `ngayCap` date DEFAULT NULL,
  `noiCap` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `idNhankhau_fk_idx` (`idNhanKhau`),
  CONSTRAINT `idNhankhau_fk` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmt/cccd`
--

LOCK TABLES `cmt/cccd` WRITE;
/*!40000 ALTER TABLE `cmt/cccd` DISABLE KEYS */;
/*!40000 ALTER TABLE `cmt/cccd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dinh_chinh`
--

DROP TABLE IF EXISTS `dinh_chinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dinh_chinh` (
  `ID` int NOT NULL,
  `idHoKhau` int DEFAULT NULL,
  `thongTinThayDoi` varchar(100) DEFAULT NULL,
  `thayDoiTu` varchar(100) DEFAULT NULL,
  `thayDoiThanh` varchar(100) DEFAULT NULL,
  `ngayThayDoi` date DEFAULT NULL,
  `idNguoiThayDoi` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `idHoKhau` (`idHoKhau`) /*!80000 INVISIBLE */,
  KEY `idNguoiThayDoi` (`idNguoiThayDoi`),
  CONSTRAINT `idHoKhau` FOREIGN KEY (`idHoKhau`) REFERENCES `ho_khau` (`ID`),
  CONSTRAINT `idNguoiThayDoi` FOREIGN KEY (`idNguoiThayDoi`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dinh_chinh`
--

LOCK TABLES `dinh_chinh` WRITE;
/*!40000 ALTER TABLE `dinh_chinh` DISABLE KEYS */;
/*!40000 ALTER TABLE `dinh_chinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ho_khau`
--

DROP TABLE IF EXISTS `ho_khau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ho_khau` (
  `ID` int NOT NULL,
  `maHoKhau` varchar(100) DEFAULT NULL,
  `idChuHo` int DEFAULT NULL,
  `maKhuVuc` varchar(100) DEFAULT NULL,
  `diaChi` varchar(100) DEFAULT NULL,
  `ngayLap` date DEFAULT NULL,
  `ngayDiChuyen` date DEFAULT NULL,
  `lyDoChuyen` text,
  `nguoiThucHien` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `sd_idx` (`maHoKhau`),
  CONSTRAINT `maNhanKhau` FOREIGN KEY (`maHoKhau`) REFERENCES `nhan_khau` (`maNhanKhau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ho_khau`
--

LOCK TABLES `ho_khau` WRITE;
/*!40000 ALTER TABLE `ho_khau` DISABLE KEYS */;
/*!40000 ALTER TABLE `ho_khau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khai_bao`
--

DROP TABLE IF EXISTS `khai_bao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khai_bao` (
  `idKhaiBao` int NOT NULL,
  `idNhanKhau` int NOT NULL,
  `vungDich` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `bieuHien` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `ngayKhaiBao` datetime NOT NULL,
  PRIMARY KEY (`idKhaiBao`),
  KEY `id_nhankhau_kb_fk_idx` (`idNhanKhau`),
  CONSTRAINT `id_nhankhau_kb_fk` FOREIGN KEY (`idNhanKhau`) REFERENCES `quan_ly_nhan_khau`.`nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khai_bao`
--

LOCK TABLES `khai_bao` WRITE;
/*!40000 ALTER TABLE `khai_bao` DISABLE KEYS */;
/*!40000 ALTER TABLE `khai_bao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khai_tu`
--

DROP TABLE IF EXISTS `khai_tu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khai_tu` (
  `ID` int NOT NULL,
  `soGiayKhaiTu` varchar(100) DEFAULT NULL,
  `idNguoiKhai` int DEFAULT NULL,
  `idNguoiChet` int DEFAULT NULL,
  `ngayKhai` date DEFAULT NULL,
  `ngayChet` date DEFAULT NULL,
  `lyDoChet` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `idNguoiKhai` (`idNguoiKhai`) /*!80000 INVISIBLE */,
  KEY `idNguoiChet` (`idNguoiChet`),
  CONSTRAINT `idNguoiChet` FOREIGN KEY (`idNguoiChet`) REFERENCES `nhan_khau` (`ID`),
  CONSTRAINT `idNguoiKhai` FOREIGN KEY (`idNguoiKhai`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khai_tu`
--

LOCK TABLES `khai_tu` WRITE;
/*!40000 ALTER TABLE `khai_tu` DISABLE KEYS */;
/*!40000 ALTER TABLE `khai_tu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhan_khau`
--

DROP TABLE IF EXISTS `nhan_khau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_khau` (
  `ID` int NOT NULL,
  `maNhanKhau` varchar(100) DEFAULT 'null',
  `hoTen` varchar(45) DEFAULT 'null',
  `namSinh` date DEFAULT NULL,
  `gioiTinh` int DEFAULT NULL,
  `noiSinh` varchar(100) DEFAULT 'null',
  `nguyenQuan` varchar(100) DEFAULT 'null',
  `danToc` varchar(100) DEFAULT 'null',
  `tonGiao` varchar(100) DEFAULT 'null',
  `quocTich` varchar(100) DEFAULT 'null',
  `noiThuongTru` varchar(100) DEFAULT 'null',
  `diaChiHienNay` varchar(100) DEFAULT 'null',
  `tinhDoHocVan` varchar(100) DEFAULT 'null',
  `ngheNghiep` varchar(100) DEFAULT 'null',
  `noiLamViec` varchar(100) DEFAULT 'null',
  `tienAn` varchar(100) DEFAULT 'null',
  `ngayChuyenDen` date DEFAULT NULL,
  `lyDoChuyenDen` varchar(100) DEFAULT 'null',
  `ngayChuyenDi` date DEFAULT NULL,
  `lyDoChuyenDi` varchar(100) DEFAULT 'null',
  `diaChiMoi` varchar(100) DEFAULT 'null',
  `ngayTao` date DEFAULT NULL,
  `idNguoiTao` int DEFAULT NULL,
  `ngayXoa` date DEFAULT NULL,
  `idNguoiXoa` int DEFAULT NULL,
  `lyDoXoa` varchar(100) DEFAULT 'null',
  `ghiChu` varchar(100) DEFAULT 'null',
  `status` int DEFAULT NULL,
  `lastUpdate` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `maNhanKhau` (`maNhanKhau`) /*!80000 INVISIBLE */,
  KEY `idNguoiTao_idx` (`idNguoiTao`),
  KEY `idNguoiXoa_idx` (`idNguoiXoa`),
  CONSTRAINT `idNguoiTao` FOREIGN KEY (`idNguoiTao`) REFERENCES `user` (`ID`),
  CONSTRAINT `idNguoiXoa` FOREIGN KEY (`idNguoiXoa`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_khau`
--

LOCK TABLES `nhan_khau` WRITE;
/*!40000 ALTER TABLE `nhan_khau` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhan_khau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tam_tru`
--

DROP TABLE IF EXISTS `tam_tru`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tam_tru` (
  `ID` int NOT NULL,
  `idNhanKhau` int DEFAULT NULL,
  `maGiayTamTru` varchar(100) DEFAULT NULL,
  `tuNgay` date DEFAULT NULL,
  `denNgay` date DEFAULT NULL,
  `lyDo` text,
  PRIMARY KEY (`ID`),
  KEY `idNhanKhau_fk` (`idNhanKhau`),
  CONSTRAINT `idNhanKhau_tt_fk` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tam_tru`
--

LOCK TABLES `tam_tru` WRITE;
/*!40000 ALTER TABLE `tam_tru` DISABLE KEYS */;
/*!40000 ALTER TABLE `tam_tru` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tam_vang`
--

DROP TABLE IF EXISTS `tam_vang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tam_vang` (
  `ID` int NOT NULL,
  `idNhanKhau` int DEFAULT NULL,
  `maGiayTamVang` varchar(100) DEFAULT NULL,
  `tuNgay` date DEFAULT NULL,
  `denNgay` date DEFAULT NULL,
  `lyDo` text,
  PRIMARY KEY (`ID`),
  KEY `idNhanKhau` (`idNhanKhau`),
  CONSTRAINT `idNhanKhau_tv_fk` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tam_vang`
--

LOCK TABLES `tam_vang` WRITE;
/*!40000 ALTER TABLE `tam_vang` DISABLE KEYS */;
/*!40000 ALTER TABLE `tam_vang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `idTest` int NOT NULL,
  `idNhanKhau` int NOT NULL,
  `thoiDiemTest` datetime NOT NULL,
  `hinhThucTest` varchar(50) NOT NULL,
  `ketQua` int NOT NULL,
  PRIMARY KEY (`idTest`),
  KEY `id_nhankhau_fk` (`idNhanKhau`),
  CONSTRAINT `id_nhankhau_fk` FOREIGN KEY (`idNhanKhau`) REFERENCES `quan_ly_nhan_khau`.`nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thanh_vien_cua_ho`
--

DROP TABLE IF EXISTS `thanh_vien_cua_ho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thanh_vien_cua_ho` (
  `idNhanKhau` int NOT NULL,
  `idHoKhau` int NOT NULL,
  `quanHeVoiChuHo` varchar(100) DEFAULT NULL,
  KEY `idHoKhau_fk_idx` (`idHoKhau`),
  KEY `id_NhanKhau_thch_fk_idx` (`idNhanKhau`),
  CONSTRAINT `id_NhanKhau_thch_fk` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`),
  CONSTRAINT `idHoKhau_fk` FOREIGN KEY (`idHoKhau`) REFERENCES `ho_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thanh_vien_cua_ho`
--

LOCK TABLES `thanh_vien_cua_ho` WRITE;
/*!40000 ALTER TABLE `thanh_vien_cua_ho` DISABLE KEYS */;
/*!40000 ALTER TABLE `thanh_vien_cua_ho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiem_chung`
--

DROP TABLE IF EXISTS `tiem_chung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiem_chung` (
  `idTiemChung` int NOT NULL,
  `idNhanKhau` int NOT NULL,
  `soLanTiem` int NOT NULL,
  `ngayTiem` datetime NOT NULL,
  `vacxin` varchar(50) NOT NULL,
  PRIMARY KEY (`idTiemChung`),
  KEY `id_nhankhau_fk` (`idNhanKhau`),
  CONSTRAINT `id_nhankhau_tc_fk` FOREIGN KEY (`idNhanKhau`) REFERENCES `quan_ly_nhan_khau`.`nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiem_chung`
--

LOCK TABLES `tiem_chung` WRITE;
/*!40000 ALTER TABLE `tiem_chung` DISABLE KEYS */;
/*!40000 ALTER TABLE `tiem_chung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` int NOT NULL,
  `userName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-25  9:25:45

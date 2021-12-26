-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quan_ly_nhan_khau_new
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
  `ID` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `noiCachLy` varchar(100) DEFAULT NULL,
  `thoiGianBatDau` datetime DEFAULT NULL,
  `mucDoCachLy` int DEFAULT NULL,
  `hetBenh` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_cach_ly_nhan_khau1_idx` (`idNhanKhau`),
  CONSTRAINT `fk_cach_ly_nhan_khau1` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cach_ly`
--

LOCK TABLES `cach_ly` WRITE;
/*!40000 ALTER TABLE `cach_ly` DISABLE KEYS */;
/*!40000 ALTER TABLE `cach_ly` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chung_minh_thu`
--

DROP TABLE IF EXISTS `chung_minh_thu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chung_minh_thu` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `soCMT` varchar(12) DEFAULT NULL,
  `ngayCap` date DEFAULT NULL,
  `noiCap` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_chung_minh_thu_nhan_khau1_idx` (`idNhanKhau`),
  CONSTRAINT `fk_chung_minh_thu_nhan_khau1` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chung_minh_thu`
--

LOCK TABLES `chung_minh_thu` WRITE;
/*!40000 ALTER TABLE `chung_minh_thu` DISABLE KEYS */;
/*!40000 ALTER TABLE `chung_minh_thu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dinh_chinh`
--

DROP TABLE IF EXISTS `dinh_chinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dinh_chinh` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `idHoKhau` int NOT NULL,
  `thongTinThayDoi` varchar(100) DEFAULT NULL,
  `thayDoiTu` varchar(100) DEFAULT NULL,
  `thayDoiThanh` varchar(100) DEFAULT NULL,
  `ngayThayDoi` date DEFAULT NULL,
  `idNguoiThayDoi` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_dinh_chinh_ho_khau1_idx` (`idHoKhau`),
  KEY `fk_dinh_chinh_users1_idx` (`idNguoiThayDoi`),
  CONSTRAINT `fk_dinh_chinh_ho_khau1` FOREIGN KEY (`idHoKhau`) REFERENCES `ho_khau` (`ID`),
  CONSTRAINT `fk_dinh_chinh_users1` FOREIGN KEY (`idNguoiThayDoi`) REFERENCES `users` (`ID`)
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
  `ID` int NOT NULL AUTO_INCREMENT,
  `maHoKhau` varchar(100) NOT NULL,
  `idChuHo` int NOT NULL,
  `maKhuVuc` varchar(100) DEFAULT NULL,
  `diaChi` varchar(100) DEFAULT NULL,
  `ngayLap` date DEFAULT NULL,
  `ngayChuyenDi` date DEFAULT NULL,
  `lyDoChuyen` text,
  `nguoiThucHien` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_HK_idx` (`idChuHo`),
  CONSTRAINT `ID_HK` FOREIGN KEY (`idChuHo`) REFERENCES `nhan_khau` (`ID`)
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
  `ID` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `vungDich` varchar(100) DEFAULT NULL,
  `bieuHien` varchar(100) DEFAULT NULL,
  `ngayKhaiBao` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_khai_bao_nhan_khau1_idx` (`idNhanKhau`),
  CONSTRAINT `fk_khai_bao_nhan_khau1` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `ID` int NOT NULL AUTO_INCREMENT,
  `soGiayKhaiTu` varchar(100) DEFAULT NULL,
  `idNguoiKhai` int NOT NULL,
  `idNguoiChet` int NOT NULL,
  `ngayKhai` date DEFAULT NULL,
  `ngayChet` date DEFAULT NULL,
  `lyDoChet` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_khai_tu_nhan_khau1_idx` (`idNguoiKhai`),
  KEY `fk_khai_tu_nhan_khau2_idx` (`idNguoiChet`),
  CONSTRAINT `fk_khai_tu_nhan_khau1` FOREIGN KEY (`idNguoiKhai`) REFERENCES `nhan_khau` (`ID`),
  CONSTRAINT `fk_khai_tu_nhan_khau2` FOREIGN KEY (`idNguoiChet`) REFERENCES `nhan_khau` (`ID`)
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
  `ID` int NOT NULL AUTO_INCREMENT,
  `maNhanKhau` varchar(100) NOT NULL,
  `hoTen` varchar(100) NOT NULL,
  `namSinh` date NOT NULL,
  `gioiTinh` int DEFAULT NULL,
  `noiSinh` varchar(100) DEFAULT NULL,
  `nguyenQuan` varchar(100) DEFAULT NULL,
  `danToc` varchar(100) DEFAULT NULL,
  `tonGiao` varchar(100) DEFAULT NULL,
  `quocTich` varchar(100) DEFAULT NULL,
  `soHoChieu` varchar(100) DEFAULT NULL,
  `noiThuongTru` varchar(100) DEFAULT NULL,
  `diaChiHienNay` varchar(100) DEFAULT NULL,
  `trinhDoHocVan` varchar(100) DEFAULT NULL,
  `ngheNghiep` varchar(100) DEFAULT NULL,
  `noiLamViec` varchar(100) DEFAULT NULL,
  `tienAn` varchar(100) DEFAULT NULL,
  `ngayChuyenDen` date DEFAULT NULL,
  `lyDoChuyenDen` varchar(100) DEFAULT NULL,
  `ngayChuyenDi` date DEFAULT NULL,
  `lyDoChuyenDi` varchar(100) DEFAULT NULL,
  `diaChiMoi` varchar(100) DEFAULT NULL,
  `ngayTao` date DEFAULT NULL,
  `idNguoiTao` int NOT NULL,
  `ngayXoa` date DEFAULT NULL,
  `idNguoiXoa` int NOT NULL,
  `lyDoXoa` varchar(100) DEFAULT NULL,
  `ghiChu` varchar(100) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `lastUpdate` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_nhan_khau_users1_idx` (`idNguoiTao`),
  KEY `fk_nhan_khau_users2_idx` (`idNguoiXoa`),
  CONSTRAINT `fk_nhan_khau_users1` FOREIGN KEY (`idNguoiTao`) REFERENCES `users` (`ID`),
  CONSTRAINT `fk_nhan_khau_users2` FOREIGN KEY (`idNguoiXoa`) REFERENCES `users` (`ID`)
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
  `ID` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `maGiayTamTru` varchar(100) DEFAULT NULL,
  `soDienThoaiNguoiDangKy` varchar(100) DEFAULT NULL,
  `tuNgay` date DEFAULT NULL,
  `denNgay` date DEFAULT NULL,
  `lyDo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_tam_tru_nhan_khau1_idx` (`idNhanKhau`),
  CONSTRAINT `fk_tam_tru_nhan_khau1` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
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
  `ID` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `maGiayTamVang` varchar(100) DEFAULT NULL,
  `noiTamTru` varchar(100) DEFAULT NULL,
  `tuNgay` date DEFAULT NULL,
  `denNgay` date DEFAULT NULL,
  `lyDo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_tam_vang_nhan_khau1_idx` (`idNhanKhau`),
  CONSTRAINT `fk_tam_vang_nhan_khau1` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
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
  `ID` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `thoiDiemTest` datetime DEFAULT NULL,
  `ketQua` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_test_nhan_khau1_idx` (`idNhanKhau`),
  CONSTRAINT `fk_test_nhan_khau1` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
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
  PRIMARY KEY (`idHoKhau`,`idNhanKhau`),
  KEY `fk_thanh_vien_cua_ho_nhan_khau1_idx` (`idNhanKhau`),
  KEY `fk_thanh_vien_cua_ho_ho_khau1_idx` (`idHoKhau`),
  CONSTRAINT `fk_thanh_vien_cua_ho_ho_khau1` FOREIGN KEY (`idHoKhau`) REFERENCES `ho_khau` (`ID`),
  CONSTRAINT `fk_thanh_vien_cua_ho_nhan_khau1` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
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
  `idTiemChung` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `soLanTiem` int DEFAULT NULL,
  `ngayTiem` date DEFAULT NULL,
  `vacxin` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idTiemChung`),
  KEY `fk_tiem_chung_nhan_khau1_idx` (`idNhanKhau`),
  CONSTRAINT `fk_tiem_chung_nhan_khau1` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) NOT NULL,
  `passwd` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-26 22:32:53

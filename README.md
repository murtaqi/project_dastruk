# 📊 Aplikasi Pengelolaan Keuangan Sederhana

Aplikasi desktop Java dengan GUI modern untuk mengelola akun dan transaksi keuangan dengan fitur save/load data otomatis.

---

## 🎯 Daftar Isi
- [Overview](#overview)
- [Fitur Utama](#fitur-utama)
- [Struktur Folder](#struktur-folder)
- [Cara Compile & Run](#cara-compile--run)
- [Struktur File Data](#struktur-file-data)
- [Color Theme](#color-theme)
- [Status](#status)

---

## 📱 Overview

Aplikasi ini menyediakan antarmuka GUI yang user-friendly untuk:
- 🔐 Membuat dan mengelola akun pengguna
- 💰 Mencatat transaksi pendapatan dan pengeluaran
- 💾 Menyimpan data secara otomatis
- 📊 Melihat riwayat transaksi dalam format tabel

---

## ✨ Fitur Utama

### 🔐 Autentikasi
- [x] **Daftar Akun Baru**: Membuat akun dengan nama, password, dan saldo awal
- [x] **Login**: Masuk dengan nomor akun dan password
- [x] **Validasi Input**: Pesan error yang jelas untuk input yang salah

### 💰 Manajemen Keuangan
- [x] **Tampilkan Saldo**: Balance display yang bisa disembunyikan
- [x] **Tambah Pendapatan**: Fungsi untuk menambah saldo
- [x] **Tambah Pengeluaran**: Mengurangi saldo dengan kategori:
  - Makanan
  - Belanja
  - Liburan
  - Lainnya
- [x] **Riwayat Transaksi**: Lihat semua transaksi dalam tabel
- [x] **Info Akun**: Detail akun dan statistik transaksi

### 💾 Data Persistence
- [x] **Auto-Save**: Semua perubahan otomatis disimpan ke file
- [x] **Auto-Load**: Data otomatis dimuat saat aplikasi dibuka
- [x] **Format Text**: File txt dengan format terstruktur untuk backup manual

---

## 🗂️ Struktur Folder

```
project_dastruk/
│
├── src/                                 # Source Code
│   ├── AppGUI.java                     # 🚀 Main Entry Point (Launcher)
│   │
│   ├── model/                           # 📦 Core Logic & Data Classes
│   │   ├── Account.java                # Representasi akun pengguna
│   │   ├── Transaction.java            # Representasi transaksi
│   │   ├── LinkedTransaction.java      # Linked list untuk transaksi
│   │   ├── LinkedAccount.java          # Linked list untuk akun
│   │   ├── HashAccount.java            # Hash table untuk storage akun
│   │   └── DataStorage.java            # Load/Save data ke file
│   │
│   ├── gui/                             # 🎨 User Interface Classes
│   │   ├── LoginGUI.java               # Frame login
│   │   ├── RegisterGUI.java            # Frame registrasi akun
│   │   └── MainGUI.java                # Frame dashboard utama
│   │
│   ├── model/                           # Compiled classes (.class)
│   └── gui/                             # Compiled classes (.class)
│
├── bin/                                 # Compiled files (alternative)
├── akun_data.txt                        # Database file (auto-created)
├── STRUKTUR_PROJECT.md                  # Dokumentasi struktur (deprecated)
├── README.md                            # File ini
├── compile.sh                           # Script compile
└── run.sh                               # Script run
```

### 📦 Package Organization

#### **model/** - Data & Core Logic
| File | Deskripsi |
|------|-----------|
| `Account.java` | Class untuk menyimpan info akun (id, nama, password, saldo, transaksi) |
| `Transaction.java` | Class untuk menyimpan info transaksi (nomor, type, nominal, date, category) |
| `LinkedTransaction.java` | Linked list untuk menyimpan riwayat transaksi |
| `LinkedAccount.java` | Linked list untuk menyimpan daftar akun |
| `HashAccount.java` | Hash table untuk pencarian akun yang cepat |
| `DataStorage.java` | Fungsi load/save data dari/ke file txt |

#### **gui/** - User Interface
| File | Deskripsi |
|------|-----------|
| `LoginGUI.java` | Tampilan login dengan username & password |
| `RegisterGUI.java` | Tampilan registrasi akun baru |
| `MainGUI.java` | Dashboard utama dengan menu (Pendapatan, Pengeluaran, Riwayat, Info) |

#### **root/** - Launcher
| File | Deskripsi |
|------|-----------|
| `AppGUI.java` | Entry point aplikasi, membuka LoginGUI |

---

## 🔧 Cara Compile & Run

### 1. Compile Project
```bash
cd /home/kyura/Documents/Project/project_dastruk
javac -d src src/model/*.java src/gui/*.java src/AppGUI.java
```

Atau gunakan script:
```bash
./compile.sh
```

### 2. Menjalankan Aplikasi
```bash
java -cp src AppGUI
```

Atau gunakan script:
```bash
./run.sh
```

---

## 📋 Struktur File Data

File `akun_data.txt` berisi:
```
========== DATA AKUN ==========
ACCOUNT:id|nama|password|saldo
TRANS:accountId|transId|tipe|nominal|tanggal|kategori
==============================
```

### Contoh Isi File:
```
========== DATA AKUN ==========
ACCOUNT:101|Budi Santoso|password123|500000
TRANS:101|20251207145548935|Pemasukan|100000|2025-12-07|
TRANS:101|20251207145549000|Pengeluaran|25000|2025-12-07|Makanan
TRANS:101|20251207145549100|Pengeluaran|50000|2025-12-07|Belanja
ACCOUNT:102|Siti Nurhaliza|pass456|750000
TRANS:102|20251207145550000|Pemasukan|200000|2025-12-07|
==============================
```

### Format Penjelasan:
- `ACCOUNT:id|nama|password|saldo` - Data akun
- `TRANS:accountId|nomor|type|nominal|date|category` - Data transaksi
- Nomor transaksi: timestamp format `yyyyMMddHHmmssSSS`
- Type: `Pemasukan` atau `Pengeluaran`
- Category: Hanya untuk transaksi pengeluaran

---

## 🎨 Design & Color Theme

### Color Palette
| Warna | Hex | RGB | Penggunaan |
|-------|-----|-----|-----------|
| Primary Purple | `#410099` | 65, 0, 155 | Header, buttons |
| Secondary Purple | `#993399` | 153, 51, 255 | Secondary buttons |
| Dark Purple | `#260060` | 38, 0, 96 | Panel background |
| White | `#FFFFFF` | 255, 255, 255 | Text, background |
| Red | `#DC143C` | 220, 20, 60 | Logout button |

### Design Principles
- ✅ Modern flat design
- ✅ Purple theme (inspired by ManajemenKeuangan project)
- ✅ User-friendly interface
- ✅ Responsive layout dengan GridBagLayout
- ✅ Clear button states dengan hover effects

---

## 📝 Catatan Teknis

1. **Package Structure**: Menggunakan `model` dan `gui` packages untuk memisahkan logic dari UI
2. **Database**: File `akun_data.txt` disimpan otomatis di root directory
3. **Look & Feel**: Menggunakan Nimbus theme bawaan Java untuk UI yang modern
4. **Reflection**: Menggunakan Java reflection untuk akses field private `first` di LinkedTransaction
5. **Compilation**: Output `.class` files disimpan di folder `src/` menggunakan flag `-d`

---

## ✅ Status Aplikasi

| Aspek | Status |
|-------|--------|
| Struktur folder | ✅ Rapi & terorganisir |
| Compilation | ✅ 0 errors |
| Aplikasi | ✅ Running lancar |
| GUI | ✅ Responsif & intuitif |
| Data persistence | ✅ Working |
| Login & Register | ✅ Functional |
| Transaksi | ✅ Functional |
| Dokumentasi | ✅ Lengkap |

---

## 🚀 Next Steps (Optional)

Fitur yang bisa ditambahkan di masa depan:
- [ ] Export data ke CSV/Excel
- [ ] Grafik statistik transaksi
- [ ] Budget planning & forecasting
- [ ] Multiple currency support
- [ ] Dark mode theme
- [ ] Report generation
- [ ] User profile management
- [ ] Transaction categorization improvements

---

**Dibuat oleh:** AI Assistant  
**Tanggal Update:** December 7, 2025  
**Java Version:** 11+  
**Status:** ✅ Ready for Use

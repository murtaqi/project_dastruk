# Aplikasi Pengelolaan Keuangan Sederhana

Aplikasi Java untuk mengelola akun dan transaksi keuangan dengan fitur save/load data.

## Folder Structure

- `src/`: Source code Java
  - `App.java` - Main application
  - `Account.java` - Class untuk data akun
  - `Transaction.java` - Class untuk data transaksi
  - `HashAccount.java` - Hash table untuk menyimpan akun
  - `LinkedAccount.java` - Linked list untuk akun
  - `LinkedTransaction.java` - Linked list untuk transaksi
  - `DataStorage.java` - Class untuk save/load data ke txt
  
- `bin/`: Compiled `.class` files (generated automatically)

- `akun_data.txt`: File penyimpanan data akun dan transaksi (auto-generated saat program berjalan)

## Cara Menggunakan

### 1. Compile Project
```bash
./compile.sh
# atau
javac -d bin src/*.java
```

### 2. Menjalankan Aplikasi
```bash
./run.sh
# atau
java -cp bin App
```

### 3. Fitur Utama
- **Daftar**: Membuat akun baru dengan nama, password, dan saldo awal
- **Masuk**: Login dengan nomor akun dan password
- **Informasi Akun**: Melihat detail akun dan saldo
- **Pemasukan**: Menambah saldo
- **Pengeluaran**: Mengurangi saldo dengan kategori (Makanan, Belanja, Liburan, Lainnya)
- **Riwayat Transaksi**: Melihat semua transaksi atau filter berdasarkan periode

### 4. Fitur Save/Load
- Semua data akun dan transaksi otomatis disimpan ke `akun_data.txt` saat program exit
- Saat program dijalankan kembali, data akan otomatis di-load
- Format file: Text dengan separator `|` untuk kemudahan backup manual

## Struktur File Data

File `akun_data.txt` berisi:
```
========== DATA AKUN ==========
ACCOUNT:id|nama|password|saldo
TRANS:accountId|transId|tipe|nominal|tanggal|kategori
==============================
```

Contoh:
```
========== DATA AKUN ==========
ACCOUNT:101|Test User|password123|125
TRANS:101|20251207145548935|Pemasukan|50|2025-12-07|
TRANS:101|20251207145548935|Pengeluaran|25|2025-12-07|Makanan
==============================
```

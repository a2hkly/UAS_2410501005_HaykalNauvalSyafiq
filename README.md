# Tugas UAS DataBase Endemik🐾🌿

EndemikDB adalah aplikasi Android berbasis Java yang dirancang untuk mengenalkan keanekaragaman hayati endemik di Indonesia. Aplikasi ini mengintegrasikan pemanggilan data dari REST API dan menyimpannya ke dalam basis data lokal (Offline-First) untuk memisahkan kategori flora dan fauna berdasarkan regionnya.

## ✨ Fitur Utama

* **Sinkronisasi API & Database Lokal:** Mengambil data JSON dari API dan menyimpannya secara otomatis ke dalam **Room Database** pada saat aplikasi pertama kali dibuka (Splash Screen).
* **Kategorisasi Dinamis:** Memisahkan data menjadi dua kategori utama (Hewan dan Tumbuhan) menggunakan arsitektur Fragment.
* **Fitur Pencarian:** Mencari data hewan atau tumbuhan secara real-time langsung dari database lokal.
* **Sistem Favorit:** Pengguna dapat menyimpan spesies yang disukai ke dalam daftar Favorit (menggunakan relasi tabel pada Room Database).
* **Dukungan Tema:** Mendukung penuh **Light Mode** dan **Dark Mode** menyesuaikan preferensi sistem perangkat.

## 🛠️ Teknologi yang Digunakan

* **Bahasa Pemrograman:** Java
* **Lingkungan Pengembangan:** Android Studio
* **Arsitektur & Komponen:**
    * [Room Database](https://developer.android.com/training/data-storage/room) (SQLite Abstraction untuk penyimpanan lokal)
    * [Retrofit2](https://square.github.io/retrofit/) & Gson (Untuk *HTTP Requests* & parsing JSON)
    * [Glide](https://github.com/bumptech/glide) (Untuk memuat dan melakukan *caching* gambar)
    * Material Design Components (Bottom App Bar, CardView, Tema Dinamis)

## 📸 *Screenshots* Aplikasi

> **Catatan:** Tambahkan *link* atau *path* gambar kamu di sini.

| Home (Hewan) - Light Mode | Home (Tumbuhan) - Dark Mode | Halaman Detail | Halaman Favorit |
| :---: | :---: | :---: | :---: |
| <img src="link_gambar_hewan_light.png" width="200"/> | <img src="link_gambar_tumbuhan_dark.png" width="200"/> | <img src="link_gambar_detail.png" width="200"/> | <img src="link_gambar_favorit.png" width="200"/> |

## 👨‍💻 Profil Pengembang

<div align="center">
  <img src="link_atau_path_foto_kamu.jpg" width="150" style="border-radius: 50%; alt="Foto Haikal"/>
  
  **Haykal Nauval Syafiq**
  
  **NIM:** 2410501005
  **Program Studi:** D3 Sistem Informasi  
  **Universitas:** Universitas Pembangunan Nasional "Veteran" Jakarta
</div>

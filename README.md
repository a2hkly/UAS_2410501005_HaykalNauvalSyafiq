# Tugas UAS DataBase Endemik🐾🌿

EndemikDB adalah aplikasi Android berbasis Java yang dirancang untuk mengenalkan keanekaragaman hayati endemik di Indonesia. Aplikasi ini mengintegrasikan pemanggilan data dari REST API dan menyimpannya ke dalam basis data lokal (Offline-First).

## ✨ Fitur

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


| Home (Hewan) - Dark Mode | Home (Tumbuhan) - Light Mode | Halaman Detail |
| :---: | :---: | :---: |
| <img src="https://github.com/a2hkly/UAS_2410501005_HaykalNauvalSyafiq/blob/b074fc8e3c1b06258cd1df734606842d14bd2c77/foto/image.png" width="200"/> | <img src="https://github.com/a2hkly/UAS_2410501005_HaykalNauvalSyafiq/blob/7193010608eb99578ee95dbf93f08f1a1961b442/foto/Screenshot%202026-06-22%20145944.png" width="200"/> | <img src="https://github.com/a2hkly/UAS_2410501005_HaykalNauvalSyafiq/blob/b074fc8e3c1b06258cd1df734606842d14bd2c77/foto/foto2.jpeg" width="200"/> | 

| Halaman Favorit | Halaman Pencarian | Splash Screen | 
| :---: | :---: | :---: | 
| <img src="https://github.com/a2hkly/UAS_2410501005_HaykalNauvalSyafiq/blob/7193010608eb99578ee95dbf93f08f1a1961b442/foto/foto4.jpeg" width="200"/> | <img src="https://github.com/a2hkly/UAS_2410501005_HaykalNauvalSyafiq/blob/7193010608eb99578ee95dbf93f08f1a1961b442/foto/foto3.jpeg" width="200"/> | <img src="https://github.com/a2hkly/UAS_2410501005_HaykalNauvalSyafiq/blob/f3bee29d8438c29c21a599c7c01204303ed04dd3/foto/Screenshot%202026-06-22%20150710.png" width="200"/> | 

## 👨‍💻 Profil Pengembang

<div align="center">
  <img src=https://github.com/a2hkly/UAS_2410501005_HaykalNauvalSyafiq/blob/b4a878ea40c6cd9000f62ba453cb399da34fd67c/foto/foto.jpeg
" width="150" style="border-radius: 50%; alt="Foto Haikal"/>
  
  **Haykal Nauval Syafiq**
  
  **NIM:** 2410501005
  **Program Studi:** D3 Sistem Informasi  
  **Universitas:** Universitas Pembangunan Nasional "Veteran" Jakarta
</div>

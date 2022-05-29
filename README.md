# IAGORA-WINGMAN

Platform : Android

Arsitektur pada kode ini menggunakan referensi dari [Android Architecture](https://developer.android.com/jetpack/guide#recommended-app-arch).

Secara detail gambaran akan seperti ini,
![clean-arch-s](https://github.com/android10/Sample-Data/raw/master/Android-CleanArchitecture-Kotlin/architecture/clean_architecture_reloaded_main.png)

Secara sederhana gambaran akan seperti ini,
![clean-arch](https://developer.android.com/topic/libraries/architecture/images/mad-arch-overview.png)

**UI Layer**
Peran lapisan UI (atau lapisan presentasi) adalah menampilkan data aplikasi di layar. Setiap kali data berubah, baik karena interaksi pengguna (seperti menekan tombol) atau input eksternal (seperti respons jaringan), UI harus diperbarui untuk mencerminkan perubahan tersebut.

Lapisan UI terdiri dari dua hal:

Elemen UI yang merender data di layar. Anda membuat elemen ini menggunakan fungsi View atau Jetpack Compose.
Pemegang status (seperti class ViewModel ) yang menyimpan data, mengeksposnya ke UI, dan menangani logika.

![ui-layers](https://github.com/android10/Sample-Data/raw/master/Android-CleanArchitecture-Kotlin/architecture/clean_architecture_reloaded_mvvm_app.png)

![ui-layer](https://developer.android.com/topic/libraries/architecture/images/mad-arch-overview-ui.png)

**Data Layer** Aplikasi berisi logika bisnis. Logika bisnis adalah yang memberikan nilai bagi aplikasi Anda— aturan ini terdiri dari aturan yang menentukan cara aplikasi Anda membuat, menyimpan, dan mengubah data.

![data-layer](https://developer.android.com/topic/libraries/architecture/images/mad-arch-overview-data.png)

Lapisan data terdiri dari repositori yang masing-masing dapat berisi nol hingga banyak sumber data (Repository Pattern). Anda harus membuat class repositori untuk setiap jenis data yang ditangani di aplikasi Anda. Misalnya, Anda mungkin membuat class MoviesRepository untuk data yang berhubungan dengan film, atau class PaymentsRepository untuk data yang terkait dengan pembayaran.

![repository-pattern](https://github.com/android10/Sample-Data/raw/master/Android-CleanArchitecture-Kotlin/architecture/clean_archictecture_reloaded_repository.png)

Class repositori bertanggung jawab atas tugas-tugas berikut:

- Mengekspos data ke seluruh aplikasi.
- Memusatkan perubahan pada data.
- Menyelesaikan konflik antara beberapa sumber data.
- Mengabstraksi sumber data dari bagian aplikasi lainnya.
- Berisi logika bisnis.

Setiap class sumber data harus memiliki tanggung jawab untuk menangani hanya satu sumber data, yang dapat berupa file, sumber jaringan, atau database lokal. Class sumber data adalah jembatan antara aplikasi dan sistem untuk operasi data.

**Domain Layer** adalah lapisan opsional yang berada di antara lapisan UI dan data.

Lapisan domain bertanggung jawab untuk mengenkapsulasi logika bisnis yang kompleks, atau logika bisnis sederhana yang digunakan kembali oleh beberapa ViewModels. Lapisan ini bersifat opsional karena tidak semua aplikasi memiliki persyaratan ini. Sebaiknya hanya gunakan jika diperlukan—misalnya, untuk menangani kompleksitas atau memilih penggunaan kembali.

![domain-layer](https://developer.android.com/topic/libraries/architecture/images/mad-arch-overview-domain.png)

Class di lapisan ini biasanya disebut kasus penggunaan atau pemicu interaksi. Setiap kasus penggunaan harus memiliki tanggung jawab atas fungsi tunggal. Misalnya, aplikasi Anda dapat memiliki class GetTimeZoneUseCase jika beberapa ViewModel bergantung pada zona waktu untuk menampilkan pesan yang tepat di layar.

Untuk lebih detail tentang arsitektur yang digunakan pada kode ini silahkan kunjungi [Android Architecture](https://developer.android.com/jetpack/guide#recommended-app-arch) dan [Playlist MAD : Architecture](https://youtube.com/playlist?list=PLWz5rJ2EKKc8GZWCbUm3tBXKeqIi3rcVX).

## Teknologi yang digunakan

- **Dependency Injection : Dagger - Hilt**
- **Jetpack Compose**
- **Kotlin**
- **Retrofit**
- **GSON**
- **Timber**
- **DataStore**
- **Coroutine**
- *selanjutnya jika ada tambahan terbaru akan ditambahkan*

## Pengaturan Awal Sebelum Memulai

buka terminal / cmd dan pastikan anda sudah menginstall git

`git clone https://github.com/IAGORA-Project/IAGORA-WINGMAN.git`

sebelum anda memulai membuka android studio pastikan anda membuat branch lokal untuk version control anda.

`git checkout -b <your branch name>`

buka android studio anda, jika mengalami error saat build gradle, buka file `local.properties` dan tambahkan ini

`baseURL="alamat url iagora wingman api"`

lakukan kembali gradle sync.

## Alur aplikasi yang sudah diterapkan

- Alur authentikasi wingman

![wingman authentication](https://github.com/MashudiSudonym/IAGORA-WINGMAN/blob/mashudi-dev/documentation/flow_user_authentication_iagora_wingman.png)

- Alur wingman login atau registrasi di aplikasi

![wingman login or sign up](https://github.com/MashudiSudonym/IAGORA-WINGMAN/blob/mashudi-dev/documentation/user_login_iagora_wingman.png)

### Informasi Update

*dokumen ini akan ditambahkan jika ada perubahan kode yang perlu dibuat catatannya*

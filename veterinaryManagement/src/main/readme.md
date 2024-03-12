# VETERİNER API PROJESİ
- Bu projede veteriner kliniği için bir API oluşturulmuştur.
- Bu API'da veteriner, müşteri, pet, pet sahibi ve randevu işlemleri yapılabilmektedir.
- Bu projede Spring Boot, Spring Data JPA, PostgreSQL, Lombok, Mapper gibi teknolojiler kullanılmıştır.
- 6 tane Entity sınıfı oluşturulmuştur. Bunlar Animal, Appointment, AvailableDate, Customer, Doctor ve Vaccine sınıflarıdır.

## Projenin UML Diyagramı
![Ekran_Goruntusu_126.png](screenshots%2FEkran_Goruntusu_126.png)

## Entity Sınıfları
- **Animal** : Müşterilere ait hayvanların bilgilerini tutan bir veritabanı varlığı tanımlar. Her Animal nesnesi benzersiz bir kimlik (id), ad (name), tür (species), ırk (breed), renk (color), doğum tarihi (dateOfBirth) ve cinsiyet (gender) içerir. Animal sınıfı ayrıca, her hayvanın sahibi olan müşteriye (Customer) ve o hayvana uygulanan aşı listesine (Vaccine) ilişkileri tanımlar. Her hayvanın birden fazla aşısı (OneToMany ilişkisi) ve randevusu (Appointment) olabilir.
- **Appointment** : Hayvanların veteriner randevularını tutan bir veritabanı varlığı tanımlar. Her Appointment nesnesi benzersiz bir kimlik (id), randevu tarihi (appointmentDate), ilgili hayvan (Animal) ve ilgili doktor (Doctor) içerir. Appointment sınıfı, bir hayvanın (ManyToOne) ve bir doktorun (ManyToOne) birden fazla randevusu olabileceği şekilde ilişkileri tanımlar. Randevu tarih ve saat bilgisi LocalDateTime türünde saklanır.
- **AvailableDate** :  Veteriner yönetim uygulaması için bir veritabanı varlığı tanımlar. Bu sınıf, veterinerlerin müsait olduğu tarihleri temsil eder. Her bir AvailableDate nesnesi, bir kimlik (id) ve müsait tarih (availableDate) bilgisini içerir. Ayrıca, her bir müsait tarih bir doktora (Doctor) atıfta bulunur, bu ilişki ManyToOne ilişkisi ile kurulmuştur, yani bir doktorun birden fazla müsait tarihi olabilir. Sınıf, Lombok kütüphanesini kullanarak getter ve setter metodlarını, bir parametresiz yapıcı metodu (NoArgsConstructor) ve tüm alanları parametre olarak alan bir yapıcı metodu (AllArgsConstructor) otomatik olarak oluşturur.
- **Customer** : Müşterilerin bilgilerini tutan bir veritabanı varlığı tanımlar. Her Customer nesnesi benzersiz bir kimlik (id), müşterinin adı (name), telefon numarası (phone), e-posta adresi (email), adres (address) ve şehir (city) bilgilerini içerir. Telefon ve e-posta adresi alanları benzersizdir, yani her müşteri için farklı olmalıdır. Müşteriler ve hayvanlar (Animal) arasında bir OneToMany ilişkisi vardır, bu da bir müşterinin birden fazla hayvana sahip olabileceği anlamına gelir. Bu ilişki mappedBy özelliği ile hayvan sınıfına bağlanır.
- **Doctor** : Her Doctor nesnesi benzersiz bir kimlik (id), ad (name), telefon numarası (phone), e-posta adresi (email), adres (address) ve şehir (city) bilgilerini içerir. Telefon ve e-posta alanları benzersizdir. Doctor sınıfı ayrıca, doktorun randevuları (Appointment) ve müsait tarihleri (AvailableDate) ile ilişkilerini tanımlar. Her iki ilişki de OneToMany türündedir, yani bir doktorun birden fazla randevusu ve müsait tarihi olabilir.
- **Vaccine** : Hayvanlara uygulanan aşıların bilgilerini içeren bir veritabanı varlığı tanımlar. Her Vaccine nesnesi benzersiz bir kimlik (id), aşı adı (name), aşı kodu (code), koruma başlangıç tarihi (protectionStartDate) ve koruma bitiş tarihi (protectionFinishDate) içerir. Aşı adı ve kodu benzersizdir. Vaccine sınıfı, her bir aşının hangi hayvana uygulandığı bilgisini tutar. Bu ilişki ManyToOne türündedir, yani bir hayvana birden fazla aşı uygulanabilir.

## Mapper Kullanımı
Projede mapper kullanımının amacı, etki alanı modelleri ile veri transfer objeleri (DTO) arasındaki dönüşümü kolaylaştırmak ve böylece kodun okunabilirliğini ve bakımını iyileştirmektir. Bu yaklaşım, nesneler arası dönüşümlerde tutarlılık sağlar ve katmanlar arası geçişleri daha anlaşılır hale getirir, böylece uygulamanın genel mimarisinin sürdürülebilirliğini ve kalitesini arttırmak amaçlanmıştır.

## API EndPoint'leri
- Veteriner yönetim uygulaması için aşağıdaki API endpoint'leri tanımlanmıştır.

1) ### **_AnimalController_**
- **POST /v1/animals**: Yeni bir hayvan kaydı oluştur. 
- **GET /v1/animals**: Tüm hayvan kayıtlarını listele. 
- **GET /v1/animals/find-by-name/{name}**: İsme göre hayvan bul. 
- **GET /v1/animals/{id}**: Belirli bir ID'ye sahip hayvanın detaylarını getir. 
- **PUT /v1/animals/update/{id}**: Belirli bir ID'ye sahip hayvan kaydını güncelle. 
- **DELETE /v1/animals/delete/{id}**: Belirli bir ID'ye sahip hayvan kaydını sil.

2) ### **_AppointmentController_**

- **POST /v1/appointments:** Yeni bir randevu oluştur. 
- **GET /v1/appointments**: Tüm randevuları listele. 
- **PUT /v1/appointments/update/{id}**: Belirli bir ID'ye sahip randevuyu güncelle. 
- **DELETE /v1/appointments/delete/{id}:** Belirli bir ID'ye sahip randevuyu sil. 
- **GET /v1/appointments/{id}**: Belirli bir ID'ye sahip randevunun detaylarını getir. 
- **GET /v1/appointments/filter/by-doctor-id**: Belirli bir doktor ve tarih aralığına göre randevuları filtrele. 
- **GET /v1/appointments/filter/by-animal-id**: Belirli bir hayvan ve tarih aralığına göre randevuları filtrele.

3) ### **_AvailableDateController_**

- **POST /v1/available-dates**: Yeni bir uygun tarih oluştur. 
- **PUT /v1/available-dates/update/{id}**: Belirli bir ID'ye sahip uygun tarihi güncelle. 
- **DELETE /v1/available-dates/delete/{id}**: Belirli bir ID'ye sahip uygun tarihi sil. 
- **GET /v1/available-dates**: Tüm uygun tarihleri listele. 
- **GET /v1/available-dates/{id}**: Belirli bir ID'ye sahip uygun tarihin detaylarını getir. 
- **GET /v1/available-dates/doctors/{doctorId}/available-dates**: Belirli bir doktorun uygun tarihlerini listele.

4) ### **_CustomerController_**

- **POST /v1/customers**: Yeni bir müşteri oluştur.
- **GET /v1/customers**: Tüm müşterileri listele.
- P**UT /v1/customers/update/{id}**: Belirli bir ID'ye sahip müşteriyi güncelle.
- **GET /v1/customers/find-by-name/{name}**: İsme göre müşteri bul.
- **GET /v1/customers/{customerId}/animals**: Belirli bir müşteriye ait hayvanları listele.
- **GET /v1/customers/{id}**: Belirli bir ID'ye sahip müşterinin detaylarını getir.
- **DELETE /v1/customers/delete/{id}**: Belirli bir ID'ye sahip müşteriyi sil.
5) ### **_DoctorController_**

- **POST /v1/doctors**: Yeni bir doktor oluştur. 
- **GET /v1/doctors**: Tüm doktorları listele. 
- **GET /v1/doctors/{id}**: Belirli bir ID'ye sahip doktorun detaylarını getir. 
- **PUT /v1/doctors/update/{id}**: Belirli bir ID'ye sahip doktoru güncelle. 
- **DELETE /v1/doctors/delete/{id}**: Belirli bir ID'ye sahip doktoru sil.
6) ### **_VaccineController_**
- **POST /v1/vaccines**: Yeni bir aşı kaydı oluştur. 
- **PUT /v1/vaccines/update/{id}**: Belirli bir ID'ye sahip aşı kaydını güncelle. 
- **DELETE /v1/vaccines/delete/{id}**: Belirli bir ID'ye sahip aşı kaydını sil. 
- **GET /v1/vaccines**: Tüm aşı kayıtlarını listele. 
- **GET /v1/vaccines/vaccines-upcoming**: Belirli tarihler arasında yaklaşan aşıları olan hayvanların listesini getir. 
- **GET /v1/vaccines/{id**}: Belirli bir ID'ye sahip aşı kaydını getir. 
- **GET /v1/vaccines/{animalId}/vaccines**: Belirli bir ID'ye sahip hayvanın tüm aşı kayıtlarını listele.



## Projeye Ait Örnek Ekran Görüntüleri
![genel.png](screenshots%2Fgenel.png)
![2.png](screenshots%2F2.png)
![3.png](screenshots%2F3.png)
![4.png](screenshots%2F4.png)
![5.png](screenshots%2F5.png)
![6.png](screenshots%2F6.png)
![7.png](screenshots%2F7.png)

## Uygulama Kurulumu
- Bu projeyi klonlayın.
- PostgreSQL veritabanı oluşturun.
- application.properties dosyasında veritabanı bağlantı bilgilerini güncelleyin.
- Projeyi çalıştırın.
- API endpoint'lerini test edin.
- Uygulama başarılı bir şekilde çalışıyorsa, API endpoint'lerini kullanarak veteriner yönetim uygulamasını geliştirmeye devam edebilirsiniz.


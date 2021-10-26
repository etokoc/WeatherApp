# **Weather App** uygulaması Kotlin dili kullanılarak geliştirilmiştir.<img src="https://user-images.githubusercontent.com/49747450/138936812-83485a84-f80c-438e-a2f4-ef3210329038.png" width:100 height = 100 align=right />
 **Kullanılan Api:** [https://www.metaweather.com/api/](url)
## Açıklama


- **Retrofit kullanılmıştır;** [https://square.github.io/retrofit/](url) 
- **View Binding kullanılmıştır.**
- **View Model kullanılmıştır.**
- **Scope Function kullanılmıştır.**

## Api'nin Özellikleri
- **Api, Türkiye'den sadece İzmir, Ankara, İstanbul illerini barındırmaktadır.**
-  Şehirlerin 5 günlük değerini ve günlük verisi alınabilir.

## Api Çalışma Prensibi
1. Cihazdan latt ve long değerleri alınarak lokasyon belirlenir.
2. Lokasyonun belirlenmesi ile woeid denilen lokasyon değeri alınır.
3. Woeid ile lokasyonun hava durumu tahminleri çekilir.


## Uygulama Çalışma Prensibi

1. Android cihazın internet erişimi ve konum izni kontrol edilir.
2. Retrofit kullanılarak api'den veriler alınır.
3. Cihazın yakın olduğu lokasyonları listeler.
4. Her lokasyonun 5 günlük hava tahmini gösterilir.

##Uygulamadan Görüntüler

<table>
<tr><td>
<div align=”left”><img src="https://user-images.githubusercontent.com/49747450/138939609-a6b0838e-e615-4a39-97fa-c09f4dd384d5.jpeg"/></div>
</td><td>
<div align=”right”><img src="https://user-images.githubusercontent.com/49747450/138939674-a8679303-b722-4de6-a98d-3978c7c8cf22.jpeg"/>
</div>
</td></tr>
</table>




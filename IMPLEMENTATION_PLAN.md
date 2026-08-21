# DiceRollerGame - Portfolio & Public Release Implementation Plan

## Proje Ozeti
Play Store'da yayinlanmis bir zar atma oyunu. Hedef: Tamamen Jetpack Compose'a gecis, portfolyo kalitesinde kod, GitHub'da public yayina hazir hale getirme.

---

## FAZE 1: Jetpack Compose Full Migration ✅ TAMAMLANDI

### 1.1 Proje Altyapisini Hazirla ✅
- [x] `build.gradle` dosyalarini guncelle (Compose BOM versiyonunu yukselt, gereksiz XML bagimlilikları kaldir)
- [x] ViewBinding'i devre disi birak
- [x] ConstraintLayout dependency'sini kaldir
- [x] Compose Navigation dependency ekle (`androidx.navigation:navigation-compose`)
- [x] Compose Animation dependency ekle

### 1.2 Theme & Design System Olustur ✅
- [x] `ui/theme/Color.kt` - Renk paleti (Material3 dynamic colors)
- [x] `ui/theme/Type.kt` - Tipografi tanimlari
- [x] `ui/theme/Theme.kt` - Light/Dark tema (DayNight destegi)
- [x] Dice gorselleri icin `painterResource` kullanimina gec

### 1.3 Ana Ekranlari Compose'a Tasi ✅
- [x] **HomeScreen.kt** - Ana menu (Card tabanlı, animasyonlu giris)
- [x] **OneDiceScreen.kt** - Tek zar ekrani (Compose Animation API, ses efekti)
- [x] **TwoDiceScreen.kt** - Cift zar ekrani (senkronize animasyonlar)

### 1.4 Navigation Yapisini Kur ✅
- [x] `NavHost` ve `NavController` ile tek Activity mimarisi
- [x] Sealed class ile route tanimlari (`Routes.kt`)
- [x] Geri navigasyon destegi (popBackStack)
- [x] XML layout dosyalari silindi

### 1.5 Eski Kodlari Temizle ✅
- [x] `OneDiceActivity.kt`, `TwoDiceActivity.kt`, `ComposeActivity.kt` silindi
- [x] `MainActivity.kt` sadece Compose entry point (setContent)
- [x] AndroidManifest'ten gereksiz Activity tanimlari kaldirildi
- [x] Eski `compose/` klasoru silindi

### 2.1 MVVM Mimarisi ✅ (Faz 1 ile birlikte tamamlandi)
- [x] `DiceViewModel.kt` - StateFlow ile UI state yonetimi
- [x] `DiceUiState` data class olusturuldu

### 2.2 Modullestirme & Paket Yapisi ✅ (Faz 1 ile birlikte tamamlandi)
```
com.vedatbasboga.dicerollergame/
├── ui/
│   ├── theme/        ✅ (Color, Type, Theme)
│   ├── screen/       ✅ (HomeScreen, OneDiceScreen, TwoDiceScreen)
│   ├── component/    ✅ (DiceImage, RollButton)
│   └── navigation/   ✅ (NavGraph, Routes)
├── viewmodel/        ✅ (DiceViewModel)
├── model/            ✅ (Dice data class)
└── MainActivity.kt   ✅
```

### 2.3 Reusable Components ✅ (Faz 1 ile birlikte tamamlandi)
- [x] `AnimatedDiceImage` composable - Zar gorseli, shake/rotate/scale animasyonlu
- [x] `RollButton` composable - Bounce efektli ozel buton
- [x] Lifecycle-aware MediaPlayer yonetimi (DisposableEffect)

---

## FAZE 2: Kod Kalitesi & Mimari ✅ TAMAMLANDI
> Faz 1 ile birlikte tamamlandi (MVVM, paket yapisi, reusable components)

---

## FAZE 3: UI/UX Iyilestirmeleri

### 3.1 Modern Tasarim
- [x] Material3 Dynamic Colors destegi
- [x] Smooth animasyonlar (spring, tween)
- [ ] Haptic feedback (titresim) zar atisinda
- [ ] Zar atma gesture destegi (swipe/shake)

### 3.2 Dark Mode
- [x] Compose `isSystemInDarkTheme()` ile otomatik tema
- [x] Tum ekranlarda dark mode uyumu

### 3.3 Responsive Layout
- [ ] Farkli ekran boyutlarina uyum (WindowSizeClass)
- [ ] Landscape modu destegi

---

## FAZE 4: GitHub Public Hazirlik

### 4.1 Hassas Verileri Temizle
- [x] Google Ads ID'lerini `BuildConfig`'e tasi
- [ ] `.gitignore` guncelle (API key'ler, local.properties)
- [ ] `.idea/` klasorunu `.gitignore`'a ekle
- [ ] Commit gecmisinde hassas veri kontrolu

### 4.2 README.md Olustur
- [ ] Proje aciklamasi ve ozellikleri
- [ ] Ekran goruntuleri / GIF demo
- [ ] Teknoloji stack listesi
- [ ] Kurulum / Build talimatlari
- [ ] Play Store linki
- [ ] Lisans bilgisi

### 4.3 Proje Dosyalari
- [ ] `LICENSE` dosyasi ekle (MIT veya Apache 2.0)
- [ ] `.gitignore` kapsamli hale getir
- [ ] Gereksiz `.idea/` dosyalarini repo'dan cikar

### 4.4 Kod Dokumantasyonu
- [ ] Public fonksiyonlara KDoc ekle
- [ ] Paket yapisi aciklamalari

---

## FAZE 5: Ekstra Ozellikler (Opsiyonel)

- [ ] Zar atma gecmisi (son 10 atis)
- [ ] Istatistikler ekrani (en cok gelen sayi vs.)
- [ ] Animasyon ayarlari (hiz secimi)
- [ ] Farkli zar tipleri (D4, D8, D10, D12, D20)
- [ ] Unit testler (ViewModel testleri)
- [ ] UI testler (Compose testing)

---

## Uygulama Sirasi

| Sira | Faz | Oncelik | Durum |
|------|-----|---------|-------|
| 1 | Faz 1 - Compose Migration | Yuksek | ✅ Tamamlandi |
| 2 | Faz 2 - Mimari (MVVM) | Orta | ✅ Tamamlandi |
| 3 | Faz 3 - UI/UX | Orta | ⏳ Siradaki |
| 4 | Faz 4 - GitHub | Yuksek | Bekliyor |
| 5 | Faz 5 - Ekstra | Dusuk | Bekliyor |

---

## Teknik Notlar

- **Min SDK:** 28 (degismeyecek, Play Store uyumlulugu icin)
- **Target SDK:** 36
- **Kotlin:** 2.1.0
- **Compose BOM:** 2024.04.01
- **Tek Activity Mimarisi:** Tum navigation Compose uzerinden
- **Reklam:** Public repo'da ad ID'ler BuildConfig uzerinden gizlenecek

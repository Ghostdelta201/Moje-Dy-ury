# Grafik pracy – aplikacja na Androida

Natywna aplikacja (Kotlin + WebView) z prawdziwymi alarmami ustawianymi automatycznie
na konkretną datę (AlarmManager.setAlarmClock) oraz przypomnieniami o bramie.
Interfejs i logika (norma, nadgodziny, święta, niedziele handlowe) są w `app/src/main/assets/index.html`.

## Budowanie APK na GitHub (bez Android Studio)
1. Utwórz nowe publiczne repozytorium i wgraj wszystkie pliki z tego folderu (razem z folderem `.github`).
2. Wejdź w zakładkę **Actions** i poczekaj kilka minut na zielony znaczek przy „Build APK”.
3. Wejdź w **Releases** → „Grafik pracy (APK)” → pobierz `app-debug.apk` na telefon i zainstaluj.
   (Android poprosi o zgodę na instalację z nieznanych źródeł.)

Przy pierwszym uruchomieniu zezwól na powiadomienia. Alarmy działają też po restarcie telefonu.

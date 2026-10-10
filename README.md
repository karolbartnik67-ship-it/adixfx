# AdixFX (Fabric 1.21.4, klient)

Osobny mod: **kolorowe hitboxy** + **losowa playlista phonk po zabiciu gracza**. Wszystko włączane w GUI (domyślnie **prawy Shift**, zmienisz w Sterowaniu).

## GUI
**Hitboxy:** włącz/wyłącz • tryb: *jeden kolor (losowy, stały)* / *jeden kolor (zmienia się)* / *wiele kolorów naraz (zmieniają się)* • przycisk "Wylosuj nowy kolor" (tryb stały) • szybkość zmian • zasięg • filtry: gracze / wrogie / przyjazne / przedmioty / inne.

**Muzyka:** włącz/wyłącz • "po zabiciu gracza" • **głośność ogólna 0–200%** • przyciski **Losowy utwór / Skip ⏭ / Stop** • lista utworów: `[WŁ/WYŁ] [nazwa] [głośność utworu 0–200%] [✎]`.
- Klik w nazwę = ten utwór gra od razu, a po jego końcu startuje **losowy** (nie po kolei) spośród włączonych.
- **Skip** pomija obecny utwór i losuje następny spośród włączonych.
- **✎** = zmiana nazwy utworu (przycisk "Domyślna" przywraca oryginalną).
- Głośność = ogólna × utworu. W Minecraftcie końcowa głośność nie może przekroczyć 100% mocy, więc **100% w GUI = połowa mocy, 200% = pełna** (żeby ustawienia powyżej 100% naprawdę coś zmieniały). Zmiana suwaka działa od następnego utworu.
- Playlista jest na razie **pusta** — czekam na nowe pliki.

## Dodawanie kolejnych utworów
1. Wrzuć `.ogg` do `src/main/resources/assets/adixfx/sounds/`.
2. Dopisz go w `sounds.json` (wzór: `phonk_adix`).
3. Dopisz wpis w `Playlist.java`.

## Build
Wrzuć na GitHuba → Actions → artefakt **AdixFX-mod** (jar). Lokalnie: `gradle build`.

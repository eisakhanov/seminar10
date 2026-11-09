# Задание 3 · Gap и overlap в Europe/Berlin

## Постановка

Дано: зона `Europe/Berlin` и три локальных времени:

- `2024-03-31T02:30` — не существует (час пропущен при переходе на лето);
- `2024-10-27T02:30` — существует дважды (час повторен при переходе на зиму);
- `2024-03-15T12:00` — обычное время, без переходов.

Реализуйте:

1. `isGap(LocalDateTime, ZoneId)` — не существует ли такое локальное время
   в зоне (DST gap);
2. `validOffsets(LocalDateTime, ZoneId)` — список валидных смещений;
3. `atEarlier(...)` — значение в overlap с более ранним смещением;
4. `atLater(...)` — значение в overlap с более поздним смещением;
5. `overlapShift(LocalDateTime, ZoneId)` — на сколько моменты `earlier` и
   `later` различаются.

## Указания

- Правила зоны: `zone.getRules().getValidOffsets(time)` — пустой список
  означает gap, из двух элементов — overlap, из одного — обычное время.
- Смещения в overlap выбирает `withEarlierOffsetAtOverlap()` и
  `withLaterOffsetAtOverlap()` у `ZonedDateTime`.
- Разность моментов — `Duration.between(...)` по `toInstant()`.

## Ожидаемый вывод

```
зона: Europe/Berlin; gap 2024-03-31T02:30, overlap 2024-10-27T02:30
OK   gap-это-gap
OK   gap-смещения-пустые
OK   overlap-не-gap
OK   overlap-смещения
OK   earlier-смещение
OK   later-смещение
OK   overlap-разница-час
OK   обычное-время-одно-смещение
дополнительно: getValidOffsets ничего не выбрасывает — в отличие от ofStrict
```

## Дополнительно

Объясните, чем `ZonedDateTime.of` (автовыбор смещения) отличается от
`ZonedDateTime.ofStrict` (проверка смещения) для времени из gap и overlap:
какой вызов бросает исключение и какое. Почему интервал
`2024-03-31T01:00` — `2024-03-31T04:00` в Берлине длится 2 часа, а
`2024-10-27T01:00` — `2024-10-27T04:00` — 4 часа?
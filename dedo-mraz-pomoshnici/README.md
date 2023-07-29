# Помошниците на Дедо Мраз

## Задача
Помошниците на Дедо Мраз направиле компјутерски систем во кој се чуваа список на сите добри деца и нивната желба за подарок за Нова Година,
само што за македонските деца употребиле стара транскрипција на специфичните македонски букви, па така буквата ч ја чуваат како c,
буквата ж како z и ш како s. Но, кога треба да проверат дали некое дете било добро, неговото име го добиваат според новата транскрипција
каде буквата ч се преставува како ch, буквата ж како zh и ш како sh. Помогнете им на помошниците на Дедо Мраз да проверат дали детете било добро ,
и доколку било, кој подарок треба да го добие.

### Влез
Во првата линија е даден број N на деца кои биле добри. Во наредните N линии се дадени името на детете и поклонот кој го сака.
Во последниот ред е дадено името на детете кое треба да се провери.

### Излез
Ако даденото дете не било добро (т.е. го нема во списокот на добри деца) да се испечати Nema poklon, а ако било добро да се испечати кој подарок го сакало.

#### Име на класа
DedoMrazPomoshnici

#### Делумно решение
Задачата се смета за делумно решена доколку се поминати 7 тест примери.

#### Забелешка
При реализација на задачите МОРА да се користат дадените структури, а не да користат помошни структури како низи или сл.

## Решение

Оваа задача може да се реши на неколку различни начини. Но едно од барањата е да се користи
`HashMap` и не некои други податочни структури.

Знаеме дека треба да ги содржиме во меморија имињата на децата и нивните подароци на таков начин
што лесно и ефикасно ќе можеме да ги наоѓаме подароците по име на дете.
За ова може да користиме HashMap како именик каде што `key` ќе биде име на дете а `value` подарокот.

Ако го претставиме овој именик т.е. список, да го наречеме `zhelbiZaPodarok`, во JSON формат би изгледало вака:

```json
{
  "Marko": "trotinet",
  "Snezana": "barbika",
  "Neso": "robot",
  "Sasko": "kuce",
  "Zivce": "nintendo"
}
```

Од влезот ќе ги вчитуваме имињата и подароците за децата и ќе ги внесуваме во `zhelbiZaPodarok`:

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

HashMap<String, String> zhelbiZaPodarok = new HashMap<>();

int n = Integer.parseInt(br.readLine());

for (int i = 0; i < n; i += 1) {
    String[] imeIPodarok = br.readLine().split(" ");
    zhelbiZaPodarok.put(imeIPodarok[0], imeIPodarok[1]);
}
```

Проблемот што треба да го решиме е тоа што имињата во списокот `zhelbiZaPodarok` се по стара
транскрипција. Како на пример името **Sashko** е запишан по старата транскрипција како **Sasko**.
Кога ќе побараме име во списокот и не најдеме некаков подарок, тоа значи дека името не постои во списокот
или е напишан со старата транскрипција. Тоа значи дека ќе ни треба некаков начин да преведеме име
од нова во стара транскрипција:

```java
"Marko"     -> null         // бидејки ова име не содржи букви како sh, zh i ch.
"Snezhana"  -> "Snezhana"   // (Zh -> Z)
"Nesho"     -> "Neso"       // (sh -> s)
"Sashko"    -> "Sasko"      // (sh -> s)
"Zhivche"   -> "Zivce"      // (Zh -> Z, ch -> c)
```

Следниот чекор е да го решиме тоа како од едно име да се заменат 
новата во старата транскрипција. Тоа може да се направи со `replaceAll` методата на следниот начин:

```java
String prevedenoIme = ime;

prevedenoIme = prevedenoIme.replaceAll("Sh", "S"); // Zhivche -> Zhivche (nema promena)
prevedenoIme = prevedenoIme.replaceAll("sh", "s"); // Zhivche -> Zhivche (nema promena)
prevedenoIme = prevedenoIme.replaceAll("ch", "c"); // Zhivche -> Zhiche  (ch -> c)
prevedenoIme = prevedenoIme.replaceAll("Ch", "C"); // Zhivche -> Zhivce  (nema promena)
prevedenoIme = prevedenoIme.replaceAll("zh", "z"); // Zhivche -> Zhivce  (nema promena)
prevedenoIme = prevedenoIme.replaceAll("Zh", "Z"); // Zhivche -> Zivce   (Zh -> Z)
```

Преведеното име можеме сега да го провериме во списокот дали тоа има подарок и да
го прикажеме на корисникот:

```java
String podarok = zhelbiZaPodarok.get(prevedenoIme);
```

Една интересна примена на `HashMap` која што ќе можеме да ја употребиме при преведување
на името е за складирање на новата до старата транскрипција.
Тоа би изгледало вака ако го претставиме во JSON:

```json
{
  "zh": "z",
  "sh": "s",
  "ch": "c",
  "Zh": "Z",
  "Sh": "S",
  "Ch": "C"
}
```

Во Java тоа бе изгледало вака:
```java
HashMap<String, String> novaDoStaraTranskripcija;

novaDoStaraTranskripcija = new HashMap<>();
novaDoStaraTranskripcija.put("zh", "z");
novaDoStaraTranskripcija.put("sh", "s");
novaDoStaraTranskripcija.put("ch", "c");
novaDoStaraTranskripcija.put("Zh", "Z");
novaDoStaraTranskripcija.put("Sh", "S");
novaDoStaraTranskripcija.put("Ch", "C");

String prevedenoIme = ime;

for (String novaTranscripcija : novaDoStaraTranskripcija.keySet()) {
    prevedenoIme = prevedenoIme.replaceAll(novaTranscripcija, novaDoStaraTranskripcija.get(novaTranscripcija));;
}
```
Како што можете да забележите ова не го скрати кодот, ниту пак го направи по ефикасен
Но ако понатака сакаме да го смениме кодот да го прими преводот на транскрипцијата од влез,
тогаш ќе треба некако да ги ставиме во меморија со помош на некаква податочна структра со која што
ќе можеме лесно да ги наоѓаме преводите како на пример "Ch" -> "C" итн.

Целосното решение на оваа задача ќе може да се види во `DedoMrazPomoshnici.java` и `Main.java`.
Во класата `DedoMrazPomoshnici` имаме метода `najdiZhelba` која можеме да ја повикаме од надвор со параметар
име на дете. Оваа метода го враќа подарокот на детето чие име сме го специфицирале во параметарот.
Ако детето нема подарок тогаш методата ќе возврати `null`.

Пред да можеме да ја користиме методата `najdiZhelba` ќе треба да креираме инстанца на `DedoMrazPomoshnici`
со параметар список на деца и подароци од типот `HashMap`:

```java
// Main.java

HashMap<String, String> zhelbiZaPodarok = new HashMap<>();

DedoMrazPomoshnici dedoMrazPomoshnici = new DedoMrazPomoshnici(zhelbiZaPodarok);
```

Кога би го тестирале котод со 5 имиња и подароци тоа би изгледало вака:

Влез:
```shell
5
Marko trotinet
Snezana barbika
Neso robot
Sasko kuce
Zivce nintendo
Zhivche
```

Излез:
```shell
Zhivche saka podarok nintendo
```

Со име кое што не постои во списокот:

Влез:
```shell
5
Marko trotinet
Snezana barbika
Neso robot
Sasko kuce
Zivce nintendo
Leonid
```

Излез:
```shell
Leonid nema podarok
```
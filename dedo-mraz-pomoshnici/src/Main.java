import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> zhelbiZaPodarok = new HashMap<>();

        zhelbiZaPodarok.put("Marija", "Barkika");
        zhelbiZaPodarok.put("Marko", "Velosiped");
        zhelbiZaPodarok.put("Dimitar", "Nintendo Switch");
        zhelbiZaPodarok.put("Snezana", "Elka za Nova Godina");
        zhelbiZaPodarok.put("Mirce", "Kuche");
        zhelbiZaPodarok.put("Neso", "Bumerang");
        zhelbiZaPodarok.put("Zarko Kezarovski", "Sijamska machka");

        DedoMrazPomoshnici dedoMrazPomoshnici = new DedoMrazPomoshnici(zhelbiZaPodarok);

        String[] iminjaZaProverka = {
                "Marija",
                "Marko",
                "Snezhana",
                "Zharko Kezharovski"
        };

        for (String ime : iminjaZaProverka) {
            String podarok = dedoMrazPomoshnici.najdiZhelba(ime);

            if (podarok != null) {
                System.out.println(ime + " saka podarok " + podarok);
            } else {
                System.out.println(ime + " nema podarok");
            }
        }
    }
}

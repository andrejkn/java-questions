import java.util.HashMap;

public class DedoMrazPomoshnici {
    private HashMap<String, String> novaDoStaraTranskripcija;
    private HashMap<String, String> zhelbiZaPodarok;

    public DedoMrazPomoshnici(HashMap<String, String> zhelbiZaPodarok) {
        this.zhelbiZaPodarok = zhelbiZaPodarok;

        novaDoStaraTranskripcija = new HashMap<>();
        novaDoStaraTranskripcija.put("zh", "z");
        novaDoStaraTranskripcija.put("sh", "s");
        novaDoStaraTranskripcija.put("ch", "c");
        novaDoStaraTranskripcija.put("Zh", "Z");
        novaDoStaraTranskripcija.put("Sh", "S");
        novaDoStaraTranskripcija.put("Ch", "C");
    }

    public String najdiZhelba(String ime) {
        String podarokZhelba = zhelbiZaPodarok.get(ime);

        if (podarokZhelba == null) {
            String imePoNovaTranskripcija = prevediIme(ime);
            return zhelbiZaPodarok.get(imePoNovaTranskripcija);
        }

        return podarokZhelba;
    }

    private String prevediIme(String ime) {
        for (String novaTranscripcija : novaDoStaraTranskripcija.keySet()) {
            ime = ime.replaceAll(novaTranscripcija, novaDoStaraTranskripcija.get(novaTranscripcija));;
        }

        return ime;
    }
}

import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, String> zhelbiZaPodarok = new HashMap<>();

        DedoMrazPomoshnici dedoMrazPomoshnici = new DedoMrazPomoshnici(zhelbiZaPodarok);

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i += 1) {
            String[] imeIPodarok = br.readLine().split(" ");
            zhelbiZaPodarok.put(imeIPodarok[0], imeIPodarok[1]);
        }

        String ime = br.readLine();

        String podarok = dedoMrazPomoshnici.najdiZhelba(ime);

        if (podarok != null) {
            System.out.println(ime + " saka podarok " + podarok);
        } else {
            System.out.println(ime + " nema podarok");
        }
    }
}

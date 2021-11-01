package telenorprojectone;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;

public class BasicBillDataRead {
    public static void szamlaszintudijak(File szamlaszintudijak){

        try {
            Scanner scan = new Scanner(szamlaszintudijak, "ISO-8859-2");
            scan.nextLine();
            while(scan.hasNextLine()){
                String sor = scan.nextLine();
                String[] adatok = sor.split(";");
                outputchck e = new outputchck();
                e.ctn = (adatok[0]);
                e.bruttos = adatok[1];
                e.bruttos = e.bruttos.replaceAll(",",".");
                e.brutto = Double.parseDouble(e.bruttos);
                e.nettos = adatok[2]; //nettó 3. oszlop
                e.nettos = e.nettos.replaceAll(",",".");
                e.netto = Double.parseDouble(e.nettos);
                e.afas = adatok[3];
                e.afas = e.afas.replaceAll(",",".");
                e.afa = Double.parseDouble(e.afas);
                e.vat_p = (adatok[4]);
                TelenorProjectOne.szamlasz.add(e);
            }
            scan.close();
        } catch (Exception ex) {
            TelenorProjectOne.szamlachk = true; //ha ide bejön a program valami nem volt rendben a beolvasással, ezért a szamlachk true lesz, logba és terminálba hibát ír ki később, ha true.
            System.out.println("Hiba szamlaszintu voidban: " + ex.getMessage()); //hibaüzenet, ha nem jó valami
        }
        DecimalFormat formatter = new DecimalFormat("##########.######");
        System.out.println("\nTelenor e-számla összesítő program v.1.0.3 // Coded by Korek György\nContact me: gyorgy.korek@astrazeneca.com\nLOG_README.txt-ben találod a program (ön)ellenőrzésének eredményét, kérlek olvasd el!");
        if (TelenorProjectOne.szamlachk == false) {
            System.out.println("---------------------------------------------------------------\nSzámlaszintű díjakat ellenőrizd!\nAZ_szamlaszintudijak.csv fájlban találhatóak az értékei, amennyiben változtatás szükséges!\nJelenleg bruttó: " + formatter.format(TelenorProjectOne.szamlasz.get(0).brutto) + " forint");
        } else {
            System.out.println("HIBA SZAMLASZINTUDIJ.CSV FÁJLBAN!");
        }
        System.out.println("---------------------------------------------------------------");
    }
}

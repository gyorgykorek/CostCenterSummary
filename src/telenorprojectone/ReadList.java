package telenorprojectone;

import java.io.File;
import java.util.Collections;
import java.util.Scanner;

public class ReadList {

    public static void tombFeltolt(File f){
        try {
            f = new File(Gui.path);
            Scanner scan = new Scanner(f, "ISO-8859-2");
            scan.nextLine();
            while(scan.hasNextLine()){
                String sor = scan.nextLine();
                String[] adatok = sor.split(";");
                szamla e = new szamla();
                e.ctn = (adatok[2]);
                e.nettos = adatok[18];
                e.nettos = e.nettos.replaceAll(",",".");
                e.netto = Double.parseDouble(e.nettos);
                e.bruttos = adatok[19];
                e.bruttos = e.bruttos.replaceAll(",",".");
                e.brutto = Double.parseDouble(e.bruttos);
                e.afas = adatok[20];
                e.afas = e.afas.replaceAll(",",".");
                e.afa = Double.parseDouble(e.afas);
                e.vat_percent = Integer.parseInt(adatok[21]);
                TelenorProjectOne.telenorszamla.add(e);
                TelenorProjectOne.inputchk += e.brutto + e.netto + e.afa;

            }
            scan.close();

            for (int i = 0; i < 3; i++) {
                szamla sizefix1 = new szamla();
                sizefix1.ctn = "999999999";
                sizefix1.brutto = 0;
                sizefix1.netto = 0;
                sizefix1.afa = 0;
                if (i==1) {
                    sizefix1.vat_percent = 27;
                }
                else if (i==0){
                    sizefix1.vat_percent = 5;
                }
                else if (i == 2){
                    sizefix1.vat_percent = 0;
                }
                TelenorProjectOne.telenorszamla.add(sizefix1);
            }

        } catch (Exception ex) {
            System.out.println("Hiba: tombfeltolt voidban " + ex.getMessage());
        }

    }
}

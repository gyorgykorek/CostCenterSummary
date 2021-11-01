package telenorprojectone;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LastOutput {

    public static void vegsokiiras(ArrayList<outputchck> outputlist, ArrayList<ctnlist> ctncheck, ArrayList<outputchck> szamlasz) {
        int szamol = 0;
        try {
            File f = new File("AZ_Telenor_szamla_"+Gui.month+".csv");
            FileOutputStream os = new FileOutputStream(f, false);
            OutputStreamWriter osw = new OutputStreamWriter(os, "ISO-8859-2");
            osw.write("Telefonszám;Bruttó;Nettó;Áfa;Áfakulcs;Tulajdonos; Cost Center;\n"); //fejléc
            DecimalFormat fro = new DecimalFormat("##########.######"); //formázó
            if (TelenorProjectOne.szamlachk == false) { //ha  nem futott hibára a beolvasásánál
                osw.write(szamlasz.get(0).ctn + ";" + fro.format(szamlasz.get(0).brutto) + ";" + fro.format(szamlasz.get(0).netto) + ";" + fro.format(szamlasz.get(0).afa) + ";" + szamlasz.get(0).vat_p + ";" + "Számlaszintű díjak;" + "140100000;\n"); // számlaszintű díjak kiiratása AZ_szamlaszintudijak.csv fájlból
            } else if (TelenorProjectOne.szamlachk == true) { //ha hibára futott a beolvasásnál
                osw.write("HIBA;AZ_szamlaszintudijak.CSV FÁJL HIBÁS;\n");
            }
            for (int i = 0; i < outputlist.size(); i=i) { //végigmegy az outputlist listán
                if (outputlist.get(i).ctn.equals(ctncheck.get(szamol).ctn)) { //ha outputlistben megyegezik a hívószám a ctnlist-ben szereplővel, akkor kiírja fájlba az indexedik elemet
                    DecimalFormat formatter = new DecimalFormat("##########.######"); //formázó csak
                    //System.out.println(outputlist.get(i).ctn + ", " +outputlist.get(i).br + ", " +outputlist.get(i).nt +", " +outputlist.get(i).vat  +", " +outputlist.get(i).vat_percent +", "  + ctncheck.get(szamol).nameofctn + "," + ctncheck.get(szamol).costcenter);
                    osw.write(outputlist.get(i).ctn + "; " + formatter.format(outputlist.get(i).brutto) + "; " + formatter.format(outputlist.get(i).netto) + "; " + formatter.format(outputlist.get(i).afa) + "; " + outputlist.get(i).vat_p + "; " + ctncheck.get(szamol).nameofctn + ";" + ctncheck.get(szamol).costcenter + ";\n");
                    TelenorProjectOne.outputchk = TelenorProjectOne.outputchk + outputlist.get(i).brutto + outputlist.get(i).netto + outputlist.get(i).afa; //outpuchk ellenörzőszám számolása a LOG_README.txt fájlba
                    i++; //i emelése
                    szamol = 0; //ha talált ilyet a szamol index nullazasa
                } else if (szamol == ctncheck.size() - 1) {    //ha a számol index elérte a AZ_ctnlist.csv hosszát, tehát nem talált costcentert és tulajdonost, akkor csak az alap adatokat írja ki amiket a program számolt ki és olvasott be
                    DecimalFormat formatter = new DecimalFormat("##########.######"); //formázó
                    //System.out.println(outputlist.get(i).ctn + ", " +outputlist.get(i).br + ", " +outputlist.get(i).nt +", " +outputlist.get(i).vat  +", " +outputlist.get(i).vat_percent);
                    osw.write(outputlist.get(i).ctn + "; " + formatter.format(outputlist.get(i).brutto) + "; " + formatter.format(outputlist.get(i).netto) + "; " + formatter.format(outputlist.get(i).afa) + "; " + outputlist.get(i).vat_p + "; NINCS ADAT CTNLIST FÁJLBAN;NINCS ADAT CTNLIST FÁJLBAN\n");
                    TelenorProjectOne.outputchk = TelenorProjectOne.outputchk + outputlist.get(i).brutto + outputlist.get(i).netto + outputlist.get(i).afa;
                    i++; //i emelése, hogy a következő elemet vizsgálhassuk, hiszen nincs további értelme az összevetésnek
                    szamol = 0; //számol nullázása
                    TelenorProjectOne.ctnchecker = true; //ha ide bejön a program akkor a ctnlist nem teljes tehát hiba van, ekkor a log_radme.txt-be kiírja a hibát

                } else {

                    szamol++;
                }

            }
            //SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            //Date date = new Date(System.currentTimeMillis());
            //osw.write("\nFájl készítésének időpontja:;" + form.format(date) + "\n");
            //osw.write("Futtatta:;");
            //osw.write(System.getProperty("user.name"));
            //osw.write("\nv1.0.3 build;Coded: gyorgy.korek@astrazeneca.com");
            osw.flush();
            osw.close();
        } catch (Exception ex) {
            System.out.println("Hiba oka vegsokiiras:" + ex.getMessage());
        }

    }

}

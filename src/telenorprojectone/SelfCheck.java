package telenorprojectone;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SelfCheck {

    public static void ellenorzes(ArrayList<outputchck> szamlasz) {

        try {
            File f = new File("LOG_README.txt"); //log fájl
            FileOutputStream os = new FileOutputStream(f, false);
            OutputStreamWriter osw = new OutputStreamWriter(os, "ISO-8859-2");
            DecimalFormat formatter = new DecimalFormat("##########.######");
            osw.write("Automatikusan generált hibaellenörző txt olvasd át figyelmesen.\n\nFOLYAMAT ELLENŐRZÉS\n----------------------------\nBeolvasott fájl bruttó, nettó és áfa összegének ellenőrzőszáma:  " + formatter.format(TelenorProjectOne.inputchk) + "\nÖsszesített fájl bruttó, nettó és áfa összegének ellenőrzőszáma: " + formatter.format(TelenorProjectOne.outputchk) + "\n\nAmennyiben a fenti kettő szám megegyezik a program feltehetően hiba nélkül elkészítette az összesítést.\nAmennyiben nem egyezik meg, a beolvasott fájl feldolgozásakor hiba történt!\nKiemelten kérlek, hogy a létrejött összesítő fájl adatainak helyességét ellenőrizd szúrópróba szerűen!");
            if (TelenorProjectOne.szamlachk == false) {
                osw.write("\n\nSZÁMLASZINTŰDÍJAK ELLENŐRZÉS\n----------------------------\nA program a AZ_szamlaszintudijak.csv fájlt beolvasta, nem észlelt hibát: Br.");
                osw.write(formatter.format(szamlasz.get(0).brutto) + " forint jelenleg a számlaszintű díj!\n");
            } else if (TelenorProjectOne.szamlachk == true) {
                osw.write("\n\nSZÁMLASZINTŰDÍJAK ELLENŐRZÉS\n----------------------------\nAZ_szamlaszintudijak.csv fájl HIBÁS VAGY HIÁNYOS!\n");
            }
            if (TelenorProjectOne.ctnchecker == false) { //false, tehát nem ment be a hibás ctnlist ágba
                osw.write("\n\nCTNLIST ELLENŐRZÉS!\n----------------------------\nA ctnlist.csv fájlban nem talált a program hiányosságot!");
                SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                osw.write("\n\nFájl készítésének időpontja:" + form.format(date) + "\n"); //futtatás időpontja
                osw.write("Futtatta:");
                osw.write(System.getProperty("user.name")); //username aki futtatta a programot, prid
            } else if (TelenorProjectOne.ctnchecker == true) { //true tehát belement a hibás ctnlist ágba
                osw.write("\n\nCTNLLIST ELLENŐRZÉS!\n----------------------------\nHIBA:AZ_ctnlist.csv hiányos vagy hibás!\nKérlek nézd át a AZ_Telenor_szamla_ELKESZULT.csv fájlt, hogy melyik hívószámnál nincs Tulajdonos és Cost Center!\nMajd javítsd ki a AZ_ctnlist.csv fájlban!");
                SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                osw.write("\nFájl készítésének időpontja:" + form.format(date) + "\nFuttatta:" + System.getProperty("user.name"));
            }
            osw.write("\n\n----------------------------\nTelenorProjectOne számlaösszesítő progrogram v1.0.4. - alpha build 2020.02.03.\nv1.0.4 improvements:\n-Basic Java Swing UI implemented\n-More NullPointerException fixed\n-0% vat group added\nv1.0.3 improvements:\n-input structure fixed\n-class simplified (source)\nv1.0.2 improvements:\n-AZ_szamlaszintudijak.csv file added, Számlaszintű díjak based on this file\nv1.0.1 improvements:\n-Resource leak fixed\n-Not used functions removed\n-Source commented\n-'Számlaszintű díjak' added\n-The date the file was created added both files\n-Username query added both files\n" +
                    "-Sizefix method developed (sizefix void replaced with sizefixplus void - no file required to fix index problem)\n\nÍrta: Korek György\ngyorgy.korek@astrazeneca.com");

            osw.flush();
            osw.close();

        } catch (Exception ex) {
            System.out.println("Hiba ellenrozes voidban:" + ex.getMessage());
        }

        System.out.print("Programot futtatta:");
        System.out.print(System.getProperty("user.name"));
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("\nFájl készítésének időpontja:" + form.format(date));

    }

}

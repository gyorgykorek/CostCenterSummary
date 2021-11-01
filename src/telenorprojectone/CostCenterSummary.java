package telenorprojectone;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import static telenorprojectone.TelenorProjectOne.costcenter;

public class CostCenterSummary {
    static File cc = new File("AZ_Telenor_szamla_"+Gui.month+".csv");

    public static void costcenter(){

        try {
            Scanner scan = new Scanner(cc, "ISO-8859-2");
            scan.nextLine();
            while(scan.hasNextLine()){
                String sor = scan.nextLine();
                String[] adatok = sor.split(";");
                outputchck e = new outputchck();
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
                e.ccenter = (adatok[6]);
                costcenter.add(e);
            }
            scan.close();
        } catch (Exception ex) {
            System.out.println("CostCenter Voidban hiba: " + ex.getMessage());
        }
        for (int i = 0; i < 3; i++) {
            outputchck sizefix1 = new outputchck();
            sizefix1.ccenter = "9999999999999";
            sizefix1.ctn = "9999999999999";
            sizefix1.brutto = 0;
            sizefix1.netto = 0;
            sizefix1.afa = 0;
            if (i==1) {
                sizefix1.vat_p = "27% áfa";
            }
            else if (i==0){
                sizefix1.vat_p = "5% áfa";
            }
            else if (i == 2){
                sizefix1.vat_p = "0% áfa";
            }
            costcenter.add(sizefix1);
        }
      //  for (outputchck e: costcenter) {
        //     System.out.println(e.ccenter + "," + e.ctn + "," +e.brutto);
       // }


    }
    public static void szetvalogatasCC(ArrayList<outputchck> costcenter, ArrayList<outputchck> vat5c, ArrayList<outputchck> vat27c, ArrayList<outputchck> vat0c, ArrayList<outputchck> ccenteroutput ){


        //áfakulcs alapján szétválogatás
        for (int i = 0; i < costcenter.size(); i++) {
            if (costcenter.get(i).vat_p.contains("27")) {
                vat27c.add(costcenter.get(i));
            } else if (costcenter.get(i).vat_p.contains("5")) {
                vat5c.add(costcenter.get(i));
            } else {
                vat0c.add(costcenter.get(i));
            }
        }

        try {
            double osszegb = 0;
            double osszegn = 0;
            double osszvat = 0;

          
            System.out.println("5% áfakulccsal rendelkeznek a következő tételek: " + (vat5c.size() - 1) + "db\n27% áfakulccsal rendelkeznek a következő tételek: " + (vat27c.size() - 1) + "db\n0% áfakulccsal rendelkeznek a következő tételek:" + (vat0c.size() - 1) + "\n---------------------------------------------------------------");

        } catch (Exception ex) {
            System.out.println("Hiba fajlba27 voidban:" + ex.getMessage()); //szokásos hibaüzenet rész, ha valami nem lenne okés
        }


    }
    public static void kiiratascc(ArrayList<outputchck> vat5c, ArrayList<outputchck> vat27c, ArrayList<outputchck> vat0c, ArrayList<outputchck> ccenteroutput){
        try {
            File f = new File("AZ_Telenor_szamla_cc.csv");
            FileOutputStream os = new FileOutputStream(f, false);
            OutputStreamWriter osw = new OutputStreamWriter(os, "ISO-8859-2");
            osw.write("Cost Center;Bruttó;Nettó;Áfa\n"); //fejléc
            for (int i = 0; i < vat0c.size()-1; i++) {
                osw.write(vat0c.get(i).ccenter + ";" +vat0c.get(i).bruttos+ ";" +vat0c.get(i).nettos+ ";" +vat0c.get(i).afas+"\n");
            }
            for (int i = 0; i < vat5c.size()-1; i++) {
                osw.write(vat5c.get(i).ccenter + ";" +vat5c.get(i).bruttos+ ";" +vat5c.get(i).nettos+ ";" +vat5c.get(i).afas+"\n");
            }
            for (int i = 0; i < vat27c.size()-1; i++) {
                osw.write(vat27c.get(i).ccenter + ";" +vat27c.get(i).bruttos+ ";" +vat27c.get(i).nettos+ ";" +vat27c.get(i).afas+"\n");
            }


            osw.flush();
            osw.close();
        } catch (Exception ex) {
            System.out.println("Hiba oka vegsokiiras:" + ex.getMessage());
        }


    }

}

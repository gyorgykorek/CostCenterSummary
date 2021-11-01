package telenorprojectone;

import java.io.File;
import java.util.Scanner;


public class ReadCtn {

    public static void ctnlistfeltolt(File ctnlist){ //AZ_ctnlist.csv fájl feltöltése, hogy legyen mivel megalkotni a tulajdonos-costcenter  táblát
         try {
            Scanner scan = new Scanner(ctnlist, "ISO-8859-2"); //beolvasó scanner
            scan.nextLine(); //fejlécet kilvassa majd eldobja!!
            while(scan.hasNextLine()){ //ameddig van adat
                String sor = scan.nextLine(); //kövi sor
                String[] adatok = sor.split(";"); //splitelés ; szerint
                ctnlist e = new ctnlist(); //hozzádás
                e.ctn = (adatok[0]); //nulladik index, első oszlop hívószám
                e.nameofctn = (adatok[1]); //első index, második oszlop tulajdonos
                e.costcenter = (adatok[2]);//masodik index harmadik oszlop costcenter
                TelenorProjectOne.ctncheck.add(e); //arraylisthez adni
            }
            scan.close(); //scan lezárása, resource leak
        } catch (Exception ex) {
            System.out.println("Hiba ctnlistfeltolt voidban: " + ex.getMessage()); //hibaüzenet ha valami nem jó
        }

    }

}

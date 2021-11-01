package telenorprojectone;
import java.io.*;
import java.util.*;

//Számla class, alap struktúrája programnak
class szamla {

    String ctn;String nettos;String bruttos;String afas;double netto;double brutto;double afa;int vat_percent;

    //be nem fejezett listarendezés, nem szükséges amennyiben excelben rendezve kapja az ctn szerinti adatokat
    public int compareTo(szamla other) { return ctn.compareTo(other.ctn);
    }

    @Override
    public String toString() {
        return "szamla{" + "ctn=" + ctn + ", nettos=" + nettos + ", bruttos=" + bruttos + ", afas=" + afas + ", vat_percent=" + vat_percent + '}';
    }
}

//AZ_ctnlist struktúrája, állandó
class ctnlist {
    String ctn;String nameofctn;String costcenter;

    @Override
    public String toString() { return "ctnlist{" + "ctn=" + ctn + ", nameofctn=" + nameofctn + ", costcenter=" + costcenter + '}';
    }
}

class outputchck extends szamla {

    String vat_p;
    String ccenter;

    @Override
    public String toString() { return "outputchck{" + "vat_p='" + vat_p + '\'' + '}';
    }
}

public class TelenorProjectOne {

    //Listák
    static ArrayList<outputchck> szamlasz = new ArrayList<>(); //szamlaszintudij.csv listája
    static ArrayList<szamla> telenorszamla = new ArrayList<>(); //alap lista, beerkezo_rendezett_havi.csv-ből beolvasva
    static ArrayList<ctnlist> ctncheck = new ArrayList<>(); //AZ_ctnlist.csv beolvasása
    static ArrayList<outputchck> outputlist = new ArrayList<>(); //vat5 és vat27 kiírását követően ide olvassuk vissza az adatokat
    static ArrayList<outputchck> costcenter = new ArrayList<>(); //vat5 és vat27 kiírását követően ide olvassuk vissza az adatokat cc specific list
    static ArrayList<outputchck> ccenteroutput = new ArrayList<>(); //vat5 és vat27 kiírását követően ide olvassuk vissza az adatokat



    //szétválogatott listák áfakulcs szerint
    static ArrayList<szamla> vat5 = new ArrayList<>();
    static ArrayList<szamla> vat27 = new ArrayList<>();
    static ArrayList<szamla> vat0 = new ArrayList<>();

    //collate cc vat
    static ArrayList<outputchck> vat5c = new ArrayList<>();
    static ArrayList<outputchck> vat27c = new ArrayList<>();
    static ArrayList<outputchck> vat0c= new ArrayList<>();

    //checkerek
    static boolean ctnchecker;
    static boolean szamlachk = false;
    static double inputchk = 0;
    static double outputchk = 0;

    //File elérések
    static File ctnlist = new File("AZ_ctnlist.csv");
    static File szamlaszintudijak = new File("AZ_szamlaszintudijak.csv");



    public static void main(String[] args) {
        Gui module = new Gui();
        module.gui();
        //costcenter szerinti összesítés
        /*
        5% és 27% áfa szerint összesítve.
         */

    }

}
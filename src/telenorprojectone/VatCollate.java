package telenorprojectone;

import java.util.ArrayList;

public class VatCollate {

    public static void szetvalogat(ArrayList<szamla> telenorszamla, ArrayList<szamla> vat5, ArrayList<szamla> vat27, ArrayList<szamla> vat0, ArrayList<outputchck> outputlist) {

        //áfakulcs alapján szétválogatás
        for (int i = 0; i < telenorszamla.size(); i++) {
            if (telenorszamla.get(i).vat_percent == 5) {
                vat5.add(telenorszamla.get(i));
            } else if (telenorszamla.get(i).vat_percent == 27) {
                vat27.add(telenorszamla.get(i));
            } else if (telenorszamla.get(i).vat_percent == 0) {
                vat0.add(telenorszamla.get(i));
            }
        }

        try {
            double osszegb = 0;
            double osszegn = 0;
            double osszvat = 0;

            //27% áfa szétválogatása
            for (int i = 0; i < vat27.size() - 1; i++) {
                if (vat27.get(i).ctn.equals(vat27.get(i + 1).ctn)) {
                    osszegb = osszegb + vat27.get(i).brutto;
                    osszegn = osszegn + vat27.get(i).netto;
                    osszvat = osszvat + vat27.get(i).afa;
                } else {

                    osszegb = vat27.get(i).brutto + osszegb;
                    osszegn = vat27.get(i).netto + osszegn;
                    osszvat = vat27.get(i).afa + osszvat;
                    outputchck afa27 = new outputchck();
                    afa27.ctn = vat27.get(i).ctn;
                    afa27.netto = osszegn;
                    afa27.brutto = osszegb;
                    afa27.afa = osszvat;
                    afa27.vat_p = "27% áfa";
                    outputlist.add(afa27);
                    osszegb = 0;
                    osszegn = 0;
                    osszvat = 0;
                }
            }

            //5% áfa szétválogatása
            for (int i = 0; i < vat5.size() - 1; i++)//same
            {
                if (vat5.get(i).ctn.equals(vat5.get(i + 1).ctn)) {//same
                    osszegb = osszegb + vat5.get(i).brutto;
                    osszegn = osszegn + vat5.get(i).netto;
                    osszvat = osszvat + vat5.get(i).afa;
                } else {
                    osszegb = vat5.get(i).brutto + osszegb;
                    osszegn = vat5.get(i).netto + osszegn;
                    osszvat = vat5.get(i).afa + osszvat;
                    outputchck afa5 = new outputchck();
                    afa5.ctn = vat5.get(i).ctn;
                    afa5.netto = osszegn;
                    afa5.brutto = osszegb;
                    afa5.afa = osszvat;
                    afa5.vat_p = "5% áfa";
                    outputlist.add(afa5);

                    osszegb = 0;
                    osszegn = 0;
                    osszvat = 0;
                }
            }

            // 0% áfa szétválogatás
            for (int i = 0; i < vat0.size() - 1; i++)//same
            {
                if (vat0.get(i).ctn.equals(vat0.get(i + 1).ctn)) {//same
                    osszegb = osszegb + vat0.get(i).brutto;
                    osszegn = osszegn + vat0.get(i).netto;
                    osszvat = osszvat + vat0.get(i).afa;
                } else {

                    osszegb = vat0.get(i).brutto + osszegb;
                    osszegn = vat0.get(i).netto + osszegn;
                    osszvat = vat0.get(i).afa + osszvat;
                    outputchck afa0 = new outputchck();
                    afa0.ctn = vat0.get(i).ctn;
                    afa0.netto = osszegn;
                    afa0.brutto = osszegb;
                    afa0.afa = osszvat;
                    afa0.vat_p = "0% áfa";
                    outputlist.add(afa0);
                    osszegb = 0;
                    osszegn = 0;
                    osszvat = 0;
                }
            }

            System.out.println("5% áfakulccsal rendelkeznek a következő tételek: " + (vat5.size() - 1) + "db\n27% áfakulccsal rendelkeznek a következő tételek: " + (vat27.size() - 1) + "db\n0% áfakulccsal rendelkeznek a következő tételek:" + (vat0.size() - 1) + "\n---------------------------------------------------------------");

        } catch (Exception ex) {
            System.out.println("Hiba fajlba27 voidban:" + ex.getMessage()); //szokásos hibaüzenet rész, ha valami nem lenne okés
        }

    }

}

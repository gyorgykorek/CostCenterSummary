package telenorprojectone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;

public class Gui {
    public static Window frame;
    public static String path;
    public static String month;
    public static JTextArea ta;
    public void gui(){

        JFrame frame = new JFrame("Telenor számlaösszesítő v1.0.4 - gyorgy.korek@astrazeneca.com");
        ImageIcon img = new ImageIcon("telenor.png");
        frame.setResizable(false);
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(690, 300);
        frame.setLocationRelativeTo(null);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Fájl");
        JMenu m2 = new JMenu("Segítség");
        mb.add(m1);
        mb.add(m2);
        JMenuItem megnyit = new JMenuItem("Megnyitás");
        JMenuItem m22 = new JMenuItem("Elkészült fájl mappájának megnyitása");
        JMenuItem readme = new JMenuItem("Használati útmutató megnyitása");
        JMenuItem logger = new JMenuItem("LOG fájl megnyitása");
        JMenuItem about = new JMenuItem("A programról..");
        m1.add(megnyit);
        m1.add(m22);
        m2.add(readme);
        m2.add(logger);
        m2.add(about);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Nevezd el a fájlt:");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Kontírozás");
        JButton reset = new JButton("Bezár");
        JButton ccbutton = new JButton("CostCenter");
        ccbutton.setVisible(false);
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);
        panel.add(ccbutton);

        // Text Area at the Center
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
        //proper method to put text in jtextfield
        ta.setText("Telenor számlaösszesítő v1.0.4. gyorgy.korek@astrazeneca.com\nCoded: Korek György");
        ta.append("\n"+"");

        //ta.get(path);
        // add the listener to the jbutton to handle the "pressed" event
        megnyit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                FileBrowser browser1= new FileBrowser();
                browser1.jbrowser();
            }
        });

        reset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                //JDialog d = new JDialog((Dialog) Gui.frame, "Megszakítottad a folyamatot!", true);
                //d.setIconImage(img.getImage());
                //d.setSize(250, 30);
                //d.setLocationRelativeTo(Gui.frame);
                //d.setVisible(true);
                System.exit(0);
            }
        });

        readme.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    Desktop.getDesktop().open(new java.io.File("_README_TelenorApps.pdf"));
                } catch (Exception ex) {
                    System.out.println("README File nem található a mappában");
                }

            }
        });
        logger.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    Desktop.getDesktop().open(new java.io.File("LOG_README.txt"));
                } catch (Exception ex) {
                    System.out.println("README File nem található a mappában");
                }

            }
        });
        about.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               // JOptionPane.showMessageDialog(frame, "A programot Korek György készítette az AstraZeneca Kft. részére\nHiba esetén kérlek olvasd el a program használati útmutatóját:\nSegítség -> Használati útmutató megnyitása\nAmennyiben nem segített ennek az olvasása kérlek keress:\ngyorgy.korek@astrazeneca.com");
                JOptionPane.showMessageDialog(frame,"A programot Korek György készítette az AstraZeneca Kft. részére\nHiba esetén kérlek olvasd el a program használati útmutatóját:\nSegítség -> Használati útmutató megnyitása\nAmennyiben nem segített ennek az olvasása kérlek keress:\ngyorgy.korek@astrazeneca.com\n\n----------------------------\nTelenorProjectOne számlaösszesítő progrogram v1.0.4. - alpha build 2020.02.03.\nv1.0.4 improvements:\n-Basic Java Swing UI implemented\n-More NullPointerException fixed\n-0% vat group added\nv1.0.3 improvements:\n-input structure fixed\n-class simplified (source)\nv1.0.2 improvements:\n-AZ_szamlaszintudijak.csv file added, Számlaszintű díjak based on this file\nv1.0.1 improvements:\n-Resource leak fixed\n-Not used functions removed\n-Source commented\n-'Számlaszintű díjak' added\n-The date the file was created added both files\n-Username query added both files\n" +
                        "-Sizefix method developed (sizefix void replaced with sizefixplus void - no file required to fix index problem)\n\nÍrta: Korek György\ngyorgy.korek@astrazeneca.com");


            }
        });
        m22.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String workingDir = System.getProperty("user.dir");
                System.out.println(workingDir);
                try {
                    Desktop.getDesktop().open(new File(workingDir));
                } catch (Exception ex) {
                    System.out.println("Mappa nem található.");
                }

            }
        });
        send.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {   File f = new File(Gui.path);
                month = tf.getText();
                String workingDir = System.getProperty("user.dir");
                System.out.println(workingDir);


                //Számlaszintűdíjak beolvasása
                BasicBillDataRead.szamlaszintudijak(TelenorProjectOne.szamlaszintudijak);

                //Fájl beolvasás, eltárolás
                ReadList.tombFeltolt(f);
                System.out.println(TelenorProjectOne.telenorszamla);


                //Áfa szerinti szétválogatás a számoláshoz
                VatCollate.szetvalogat(TelenorProjectOne.telenorszamla, TelenorProjectOne.vat5, TelenorProjectOne.vat27, TelenorProjectOne.vat0, TelenorProjectOne.outputlist);

                //CTN lista beolvasása
                ReadCtn.ctnlistfeltolt(TelenorProjectOne.ctnlist);

                //Végső kiírás fájlba
                LastOutput.vegsokiiras(TelenorProjectOne.outputlist, TelenorProjectOne.ctncheck, TelenorProjectOne.szamlasz);

                //Self-Check
                SelfCheck.ellenorzes(TelenorProjectOne.szamlasz);
                ta.append("\nFeldolgozott file:\n"+path+"\nElkészült file:\n"+workingDir+"/AZ_Telenor_szamla_ELKESZULT_"+Gui.month+".csv") ;
                if (TelenorProjectOne.szamlachk == false) {
                    ta.append("\n\nSzámlaszintűdíjak: \tok");
                }
                else{
                    ta.append("\n\nSzámlaszintűdíjak: \tHIBA" );

                }
                if (TelenorProjectOne.ctnchecker == false) {
                    ta.append("\nHívószámlista: \tok" );
                }
                else{
                    ta.append("\n\nHívószámlista: \tHIBA" );

                }
                DecimalFormat formatter = new DecimalFormat("##########.######"); //formázó
                ta.append("\nBeovasott számok összege:\t"+formatter.format(TelenorProjectOne.inputchk) + "\nKiiratott számok összege:\t"+formatter.format(TelenorProjectOne.outputchk));
                JOptionPane.showMessageDialog(frame, "A kontírozás elkészült.\nKérlek ellenőrizd a LOG fájlt!\nAZ_Telenor_szamla_ELKESZULT_"+Gui.month+".csv");
                //System.out.println(month);
                JButton secondButton = new JButton("Elkészült fájl megnyitása");
                panel.remove(send);
                panel.add(secondButton);
                panel.revalidate();
                panel.repaint();

                secondButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try {
                            Desktop.getDesktop().open(new java.io.File("AZ_Telenor_szamla_ELKESZULT_"+Gui.month+".csv"));
                        } catch (Exception ex) {
                            System.out.println("README File nem található a mappában");
                        }

                    }
                });
                ccbutton.setVisible(true);
                ccbutton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        try {
                           CostCenterSummary.costcenter();
                           CostCenterSummary.szetvalogatasCC(TelenorProjectOne.costcenter, TelenorProjectOne.vat5c, TelenorProjectOne.vat27c, TelenorProjectOne.vat0c, TelenorProjectOne.ccenteroutput);
                            CostCenterSummary.kiiratascc(TelenorProjectOne.ccenteroutput,TelenorProjectOne.vat5c, TelenorProjectOne.vat27c, TelenorProjectOne.vat0c);
                        } catch (Exception ex) {
                            System.out.println("CC hiba");
                        }

                    }
                });

            }
        });




    }
}

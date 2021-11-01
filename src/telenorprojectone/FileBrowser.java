package telenorprojectone;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;


public class FileBrowser {



    public void jbrowser(){
        try {
            JFileChooser chooser = new JFileChooser();
        String workingDir = System.getProperty("user.dir");

        chooser.setCurrentDirectory(new File(workingDir));
        chooser.setPreferredSize(new Dimension(450, 600));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Comma Separated Values (.csv files works only)", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            Gui.path = selectedFile.getAbsolutePath();
            System.out.println(Gui.path);
            JOptionPane.showMessageDialog(chooser, "Kiválasztottad az alábbi fájlt:\n" + Gui.path + "\nFájl neve: "+chooser.getSelectedFile().getName());
        }
        
        System.out.println("You chose to open this file: " +
                chooser.getSelectedFile().getName());
        } catch (Exception e) {
            System.out.println("Kezelt Exception FileChooser: "+e  );
        }
        


    }
}

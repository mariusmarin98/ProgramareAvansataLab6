package lab6;

import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        setLayout(new GridLayout(1, 4));

        this.add(saveBtn);
        this.add(loadBtn);
        this.add(resetBtn);
        this.add(exitBtn);

        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        saveBtn.addActionListener(this::reset);
        saveBtn.addActionListener(this::exit);

    }
    private void save(ActionEvent e) {
        try {
            JFrame parentFrame = new JFrame();
            File fileToSave;

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify the directory and file to be saved");

            int userSelection = fileChooser.showSaveDialog(parentFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                fileToSave = fileChooser.getSelectedFile();
                String path = fileToSave.getAbsolutePath();
                String[] path_splitted = path.split("\\.");
                ImageIO.write(frame.canvas.image, path_splitted[path_splitted.length - 1], new File(fileToSave.getAbsolutePath()));
            }
        } catch (IOException ex) { System.err.println(ex.toString()); }
    }

    private void load(ActionEvent e) {
    try {
            JFrame parentFrame = new JFrame();
            File loadFile;

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose file to be loaded");

            int userSelection = fileChooser.showOpenDialog(parentFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                loadFile = fileChooser.getSelectedFile();
                String path = loadFile.getAbsolutePath();
                frame.canvas.image = ImageIO.read(new File(path));
                frame.canvas.repaint();
            }
        } catch (IOException ex) {
            System.out.println("Error appeared: " + ex.toString());
        }

    }

    private void reset(ActionEvent e) {
    }

    private void exit(ActionEvent e) {
    }
}

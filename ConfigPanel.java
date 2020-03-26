package lab6;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JComboBox<String> colorCombo; // the color of the shape
    JComboBox<String> shapeCombo;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create the label and the spinner
        label = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(0); //default number of sides
        //create the colorCombo, containing the values: Random and Black
        String[] colors = new String[] {"Random", "Black", "White", "Red", "Blue", "Green"};
        String[] shapes = new String[] {"Regular Polygon", "Node Shape"};
        colorCombo = new JComboBox<>(colors);
        shapeCombo = new JComboBox<>(shapes);

        add(label); //JPanel uses FlowLayout by default
        add(sidesField);
        add(colorCombo);
        add(shapeCombo);
    }
}

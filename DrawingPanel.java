package lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    public DrawingPanel(MainFrame frame) {
        this.frame = frame; createOffscreenImage(); init();
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY()); repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }

    private ArrayList<Integer> getList(int r, int g, int b)
    {
        return new ArrayList<Integer>(){{
            add(r);
            add(g);
            add(b);
        }};
    }

    private void drawShape(int x, int y) {
        Map<String, ArrayList<Integer>> colors = new HashMap<String, ArrayList<Integer>>();
        colors.put("Black", getList(0, 0, 0));
        colors.put("White", getList(255,255,255));
        colors.put("Red", getList(255, 0, 0));
        colors.put("Green", getList(0, 255, 0));
        colors.put("Blue", getList(0, 0, 255));

        Random rand = new Random();

        int radius = rand.nextInt(360);
        int sides = (Integer)this.frame.configPanel.sidesField.getValue();

        Color color = new Color(255, 255, 255);

        String colorValue = (String)this.frame.configPanel.colorCombo.getSelectedItem();
        if ( colorValue != null) {
            if (!colorValue.equals("Random")) {
                ArrayList<Integer> rgbColor = colors.get(colorValue);
                color = new Color(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2));
            } else {
                color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            }
        }

        graphics.setColor(color);

        String shapeSelected = (String)this.frame.configPanel.shapeCombo.getSelectedItem();

        if (shapeSelected != null ) {
            if (shapeSelected.equals("Regular Polygon"))
                graphics.fill(new RegularPolygon(x, y, radius, sides));
            else if ( shapeSelected.equals("Node Shape"))
                graphics.fill(new NodeShape(x, y, radius));
        }
    }
    @Override
    public void update(Graphics g) { } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}

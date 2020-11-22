import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Application {
    public static void main(String[] args) throws IOException {
        try {
            final String filename = "normal.png";

			File image = new File("mazes/" + filename);
			BufferedImage mazeimg = ImageIO.read(image);
			System.out.println("Successfully read maze!");
          
            Maze maze = new Maze(mazeimg);
            System.out.println((maze.findGoal(maze.getStart()))? "solved":"failed");
            //maze.printMaze();
            //maze.printSolution();
            maze.exportSolution(new File("solutions/" + filename));
            
            JFrame frame = new JFrame("Maze");
        	JPanel p = new JPanel(new BorderLayout());
			MazeUI ui = new MazeUI(maze.getMaze());
			p.add(ui);
			
//			ui.openWindow("mazes/" + filename);
//            ui.setSolImg("solutions/" + filename);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public class BasePanel extends JPanel {

    	ImageIcon backImage;
    	public BasePanel() {
    	    backImage = new ImageIcon("src/images/welcome.png");
    	}

    	@Override
    	protected void paintComponent(Graphics g) {
    	    BufferedImage scaledImage = getScaledImage();
    	    super.paintComponent(g);
    	    g.drawImage(scaledImage, 0, 0, null);
    	}

    	private BufferedImage getScaledImage(){
    	    BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
    	    Graphics2D g2d = (Graphics2D) image.createGraphics();
    	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
    	    g2d.drawImage(backImage.getImage(), 0, 0,getWidth(),getHeight(), null);

    	    return image;
    	}

    	}
    
    
    
    
    
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeUI  extends JPanel {
	static JFrame frame = new JFrame("Maze");
	JPanel p = new JPanel(new BorderLayout());
	
	public MazeUI(int[][] level){		
		
		final Board test = new Board(level);
		final Player player = new Player(test);
		
		p.add(test, BorderLayout.CENTER);
		p.setFocusable(true);
		frame.add(p, BorderLayout.CENTER);
		
//		JButton south = new JButton("South");
//		south.setFocusable(false);
//		p2.add(south, BorderLayout.SOUTH);			
//		south.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent ae)
//			{	
//				player.moveDown(test);
//			}
//		});
//		
//		JButton north = new JButton("North");
//		north.setFocusable(false);
//		p2.add(north, BorderLayout.NORTH);
//		north.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent ae)
//			{	
//				player.moveUp(test);
//			}
//		});
//		
//		JButton east = new JButton("East");
//		east.setFocusable(false);
//		p2.add(east, BorderLayout.EAST);
//		east.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent ae)
//			{	
//				player.moveRight(test);
//			}
//		});		
//		
//		
//		JButton west = new JButton("West");
//		west.setFocusable(false);
//		p2.add(west, BorderLayout.WEST);
//		west.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent ae)
//			{	
//				player.moveLeft(test);
//			}
//		});	
//		
//		JButton menu = new JButton("New Game");
//		menu.setFocusable(false);
//		p2.add(menu, BorderLayout.CENTER);
//		menu.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent ae)
//			{	
//				frame.dispose();
//				new MainMenu();
//			}
//		});	
				
		p.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e){				
				int keyCode = e.getKeyCode();
				if(e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT) player.moveLeft(test);
				if(e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT) player.moveRight(test);
				if(e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP) player.moveUp(test);
				if(e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN) player.moveDown(test);
			}
		});	
		
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.green);
		frame.setVisible(true);
	}
	
	
	
}

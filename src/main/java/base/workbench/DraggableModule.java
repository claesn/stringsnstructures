package base.workbench;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

public class DraggableModule extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3876638050736495281L;
	private volatile int screenX = 0;
	private volatile int screenY = 0;
	private volatile int myX = 0;
	private volatile int myY = 0;

	public DraggableModule() {
		setBorder(new LineBorder(Color.BLUE, 3));
		setBackground(Color.WHITE);
		setBounds(0, 0, 100, 100);
		setOpaque(false);

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				screenX = e.getXOnScreen();
				screenY = e.getYOnScreen();

				myX = getX();
				myY = getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				int deltaX = e.getXOnScreen() - screenX;
				int deltaY = e.getYOnScreen() - screenY;

				setLocation(myX + deltaX, myY + deltaY);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}

		});
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Swing Hello World");

		// by doing this, we prevent Swing from resizing
		// our nice component
		f.setLayout(null);

		DraggableModule mc = new DraggableModule();
		f.add(mc);

		f.setSize(500, 500);

		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}

}
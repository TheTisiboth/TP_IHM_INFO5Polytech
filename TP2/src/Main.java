import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new BorderLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 600);

		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}

package _00_Intro_To_ArrayLists;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_GuestBook implements ActionListener {
    /*
     * Create a GUI with two buttons. One button reads "Add Name" and the other
     * button reads "View Names". When the add name button is clicked, display
     * an input dialog that asks the user to enter a name. Add that name to an
     * ArrayList. When the "View Names" button is clicked, display a message
     * dialog that displays all the names added to the list. Format the list as
     * follows:
     * Guest #1: Bob Banders
     * Guest #2: Sandy Summers
     * Guest #3: Greg Ganders
     * Guest #4: Donny Doners
     */
	JFrame frame;
	JPanel panel;
	JButton addNames;
	JButton viewNames;
	ArrayList<String> names;
	public static void main(String[] args) {
		new _02_GuestBook().run();
	}
	void run() {
		frame = new JFrame("Guest Book");
		names = new ArrayList<String>();
		panel = new JPanel();
		addNames = new JButton("Add Name");
		viewNames = new JButton("View Names");
		panel.add(addNames);
		panel.add(viewNames);
		addNames.addActionListener(this);
		viewNames.addActionListener(this);
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addNames) {
			names.add(JOptionPane.showInputDialog(frame, "Add a name to the list"));
		}
		else if (e.getSource() == viewNames) {
			String nameMessage="";
			for(int i = 0; i < names.size(); i++) {
				nameMessage+=String.format("Guest #%d: %s\n", i, names.get(i));
			}
			JOptionPane.showMessageDialog(frame, nameMessage);
		}
	}

}

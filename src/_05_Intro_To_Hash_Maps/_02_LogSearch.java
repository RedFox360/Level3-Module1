package _05_Intro_To_Hash_Maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_LogSearch implements ActionListener {
	/*
	 * Crate a HashMap of Integers for the keys and Strings for the values. Create a
	 * GUI with three buttons. Button 1: Add Entry When this button is clicked, use
	 * an input dialog to ask the user to enter an ID number. After an ID is
	 * entered, use another input dialog to ask the user to enter a name. Add this
	 * information as a new entry to your HashMap.
	 * 
	 * Button 2: Search by ID When this button is clicked, use an input dialog to
	 * ask the user to enter an ID number. If that ID exists, display that name to
	 * the user. Otherwise, tell the user that that entry does not exist.
	 * 
	 * Button 3: View List When this button is clicked, display the entire list in a
	 * message dialog in the following format: ID: 123 Name: Harry Howard ID: 245
	 * Name: Polly Powers ID: 433 Name: Oliver Ortega etc...
	 * 
	 * When this is complete, add a fourth button to your window. Button 4: Remove
	 * Entry When this button is clicked, prompt the user to enter an ID using an
	 * input dialog. If this ID exists in the HashMap, remove it. Otherwise, notify
	 * the user that the ID is not in the list.
	 */
	static HashMap<Integer, String> values = new HashMap<Integer, String>();
	private JFrame frame;
	private JPanel panel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;

	public static void main(String[] args) {
		new _02_LogSearch().run();
	}

	private void run() {
		frame = new JFrame();
		panel = new JPanel();
		button1 = new JButton("Add Entry");
		button4 = new JButton("Remove Entry");
		button2 = new JButton("Search by ID");
		button3 = new JButton("View List");
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);

		panel.add(button1);
		panel.add(button4);
		panel.add(button2);
		panel.add(button3);
		frame.add(panel);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button1) {
			int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter an ID Number"));
			String name = JOptionPane.showInputDialog(frame, "Enter a name");
			values.put(id, name);
			JOptionPane.showMessageDialog(frame, "Name " + name + " succesfully added.");
		}
		if (e.getSource() == button2) {
			int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter an ID Number"));
			if (values.containsKey(id)) {
				JOptionPane.showMessageDialog(frame, values.get(id));
			} else {
				JOptionPane.showMessageDialog(frame, "No name is mapped to this ID");
			}
		}
		if (e.getSource() == button3) {
			String message = "";
			for (int id : values.keySet()) {
				message += String.format("ID %d: Name: %s\n", id, values.get(id));
			}
			JOptionPane.showMessageDialog(frame, message);
		}
		if (e.getSource() == button4) {
			int idToRemove = Integer.parseInt(JOptionPane.showInputDialog("Which ID would you like to remove?"));
			if (values.containsKey(idToRemove)) {
				values.remove(idToRemove);
				JOptionPane.showMessageDialog(frame, "ID " + idToRemove + " succesfully removed from the database.");
			} else {
				JOptionPane.showMessageDialog(frame, "ID not found");
			}
		}
	}
}

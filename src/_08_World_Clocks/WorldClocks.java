package _08_World_Clocks;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

/*
 * You task is to create a java program that:
 * 1. Displays the time for multiple cities around the world on one display.
 * 2. Gives the user the ability to add a city to the display. One possible
 *    way to do this is to create a HashMap of city names and their
 *    corresponding time zones, e.g. HashMap<String, TimeZone>, then use each
 *    city's TimeZone to get the current date/time every second using a
 *    Timer object (see example code below).
 * 
 * The code below is an example of how to print out a clock for San Diego.
 * Use the ClockUtilities class to find the time zone of each city, then use
 * Calendar.getInstance to return a Calendar object to get the current time for
 * that city. Example:
 *   TimeZone timeZone = clockUtil.getTimeZoneFromCityName("San Diego, US");
 *   Calendar c = Calendar.getInstance(timeZone);
 *   System.out.println("Full date and time: " + calendar.getTime());
 * 
 * NOTE: The program may take a second or two to execute
 * 
 * Calendar class:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 */

public class WorldClocks implements ActionListener {
	ClockUtilities clockUtil;
	Timer timer;
	String dateStr, timeStr;
	TimeZone timeZone;
	ArrayList<JTextArea> addedTextAreas;
	JFrame frame;
	JPanel panel;
	ArrayList<JTextArea> textAreas;
	JButton check;
	JTextField cityField, countryField;
	String city;
	ArrayList<Calendar> times;
	ArrayList<String> names;
	public WorldClocks() {
		times = new ArrayList<>();
		clockUtil = new ClockUtilities();
		textAreas = new ArrayList<>();
		names = new ArrayList<>();
		addedTextAreas = new ArrayList<>();
		timer = new Timer(1000, this);
		// The format for the city must be: city, country (all caps)
		city = "San Diego, US";
		TimeZone timeZone = clockUtil.getTimeZoneFromCityName(city);
		Calendar c = Calendar.getInstance(timeZone);
		times.add(c);
		names.add(city);
		JTextArea textArea1 = new JTextArea();
		textAreas.add(textArea1);

		// Sample starter program
		frame = new JFrame();
		panel = new JPanel();
		cityField = new JTextField("City");
		countryField = new JTextField("Country");
		cityField.setPreferredSize(new Dimension(150, 30));
		countryField.setPreferredSize(new Dimension(75, 30));
		check = new JButton("Enter City");
		check.addActionListener(this);
		countryField.addActionListener(this);
		cityField.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(100, 100);
		frame.add(panel);
		panel.add(cityField);
		panel.add(countryField);
		panel.add(check);
		for (JTextArea textArea : textAreas) {
			textArea = new JTextArea();
			panel.add(textArea);
		}

		// This Timer object is set to call the actionPerformed() method every
		// 1000 milliseconds
		timer = new Timer(1000, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == check) {
			String city = cityField.getText() + ", " + countryField.getText();
			TimeZone timeZone = clockUtil.getTimeZoneFromCityName(city);
			Calendar c = Calendar.getInstance(timeZone);
			String amOrPM;
			if (c.AM_PM == c.AM)
				amOrPM = "AM";
			else
				amOrPM = "PM";
			String time = c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + " "
					+ amOrPM;
			times.add(c);
			names.add(city);
			textAreas.add(new JTextArea());
			System.out.println("Time in " + city + ": " + time);
		}
		if (e.getSource() == timer) {
			for (int i = 0; i < textAreas.size(); i++) {
				System.out.println("TIMER");
				Calendar c = times.get(i);
				String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":"
						+ c.get(Calendar.SECOND);
				String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":"
						+ c.get(Calendar.SECOND) + "]";
				dateStr = "";
				timeStr = militaryTime + twelveHourTime;
				JTextArea textArea = textAreas.get(i);
				textAreas.get(i).setText(names.get(i) + '\n' + dateStr + '\n' + timeStr);
				if (!addedTextAreas.contains(textArea)) {
					panel.add(textArea);
					addedTextAreas.add(textArea);
				}
				panel.add(textArea);
			}
			frame.pack();
		}
	}
}

package pworldwideearthquake;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.title.Title;
import org.jfree.data.xy.*;
import org.jfree.ui.about.ProjectInfo;

import sun.text.normalizer.VersionInfo;

import com.oracle.jrockit.jfr.ValueDefinition;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * @author Chris_Saxon
 * @version 1.0
 */
public class EarthquakeGui extends JFrame {

	// Data fields
	private int ID = 0;
	private int num = 0;

	// Objects
	private ArrayList<Earthquake> earthquakes;
	private Utilities worker;

	// Frame
	private JFrame mainWindow;

	// Combo Box
	private JComboBox<String> filter;
	private JComboBox<String> comboFilter;

	// Scroll Pane
	private JScrollPane scrollPane;

	// Tabbed Pane
	private JTabbedPane tabbedPane;

	// Panels
	private JPanel panelOne;
	private JPanel panelTwo;
	private JPanel panelThree;
	private JPanel panelFour;

	// Text Area
	private JTextArea textArea;

	// Text Fields
	private JTextField txtID;
	private JTextField textMagnitude;
	private JTextField textMagType;
	private JTextField textDate;
	private JTextField textDepth;
	private JTextField textID;
	private JTextField textLatitude;
	private JTextField textLongitude;
	private JTextField textDescription;
	private JTextField textField;

	// Labels
	private JLabel lblLatitude;
	private JLabel lblLongitude;
	private JLabel lblDescription;
	private JLabel lblLogo2;
	private JLabel lblQuickSearch;
	private JLabel lblId;
	private JLabel lblBackgroundThree;
	private JLabel lblIDNumber;
	private JLabel lblMagnitude;
	private JLabel lblDate;
	private JLabel lblMagType;
	private JLabel lblDepth;
	private JLabel lblLogo;
	private JLabel lblBackground;
	private JLabel background;

	// Buttons
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnStart;
	private JButton btnSort;
	private JButton btnClear;
	private JButton btnSearch;
	private JButton btnAddNew;
	private JButton btnListAll;
	private JButton btnReset;

	// Menu bar
	private JMenuBar menuBar;

	// Menu
	private JMenu mnAbout;
	private JMenuItem mntmNewMenuItem;

	// Progress Bar
	JProgressBar loadingBar;

	/**
	 * This is the constructor for the EarthquakeGui class it takes the
	 * Earthquake ArrayList and calls the <b>initialize</b> method.
	 * 
	 * @param earthquake
	 */
	// EarthquakeGui constructor accepts a ArrayList of earthquake and calls the
	// initialize method that initializes the swing components.
	public EarthquakeGui(ArrayList<Earthquake> earthquakes) {
		initialize();
		worker = new Utilities();
		this.earthquakes = earthquakes;

		mainWindow.setIconImage(new ImageIcon("images/frame-icon.png")
				.getImage());
		lblBackgroundThree
				.setIcon(new ImageIcon("images/manual-background.png"));
		createLineChart();

	}

	/**
	 * This method is called by the <b>EarthquakeGui</b> constructor. It is used
	 * to initialize all of the swing components.
	 */
	// This method is called by the constructor and initializes the swing
	// components.
	private void initialize() {

		// Instance of JFrame
		mainWindow = new JFrame();
		// Modifies the visibility of the frame.
		mainWindow.setVisible(true);
		// By setting resizable to false it prevents the frame from being
		// resized.
		mainWindow.setResizable(false);
		// Sets the location and size of the frame.
		mainWindow.setBounds(100, 100, 607, 427);
		// Assigns the EXIT_ON_CLOSE action to the frames corner close button.
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Sets the title of the frame.
		mainWindow.setTitle("Worldwide Earthquakes");
		// Selects the layout of the frame.
		mainWindow.getContentPane().setLayout(new BorderLayout(0, 0));
		// An instance of JMenuBar called menuBar.s
		menuBar = new JMenuBar();
		// Adds the menuBar to the frame.
		mainWindow.getContentPane().add(menuBar, BorderLayout.NORTH);
		// An instance of JMenu.
		mnAbout = new JMenu("Help");
		// Adds the "About" menu option to the Menu Bar.
		menuBar.add(mnAbout);
		// An instance of JMenuItem.
		mntmNewMenuItem = new JMenuItem("About Worldwide Earthquakes");
		// An ActionListner that responds to the click of the menu item
		// "About Worldwide Earthquakes".
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								rootPane,
								"Worldwide Earthquakes Version: 1.0\n"+"The data for this program was retrieved from http://earthquake.usgs.gov. The information"
										+"\n has been filtered to show earthquakes with a magnitude of 6 or higher.") ;
			}
		});
		// Adds the menu item "About Worldwide Earthquakes to the "About" menu.
		mnAbout.add(mntmNewMenuItem);
		// Creates an instance of JTabbedPane.
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// Sets the location of the TabbedPane.
		tabbedPane.setLocation(1, 21);
		// Adds the TabbedPane to the Frame.
		mainWindow.getContentPane().add(tabbedPane);
		// A new instance of JPanel.
		panelOne = new JPanel();
		// Sets the layout of the panel to absolute.
		panelOne.setLayout(null);
		// Creates an instance of the JComboBox called filter.
		filter = new JComboBox<String>();
		// Sets the location and size of the ComboBox.
		filter.setBounds(396, 76, 160, 31);
		// Adds "Highest Magnitude" to the ComboBox.
		filter.addItem("Highest Magnitude");
		// Adds "Lowest Magnitude" to the ComboBox.
		filter.addItem("Lowest Magnitude");
		// Adds "Deepest Depth" to the ComboBox.
		filter.addItem("Deepest Depth");
		// Adds the "Deepest Depth" to the ComboBox.
		filter.addItem("Shortest Depth");
		// Adds the ComboBox called filter to panelOne.
		panelOne.add(filter);
		// Creates a new instance of JButton called btnSearch.
		btnSearch = new JButton("Search");
		// Sets the text color of the button to "Gray".
		btnSearch.setForeground(Color.GRAY);
		// Sets the font-family, weight and size to the text on btnSearch.
		btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 12));
		// An ActionListener that uses if statements to determine what option
		// has been selected on the action of the button click of btnSearch.
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// If "Highest Magnitude" is selected the worker class will call
				// it's highestMagnitude method returning the highest Magnitude.
				// It will then be set to the text field
				if (filter.getSelectedItem() == "Highest Magnitude") {
					textField.setText("The Highest Magnitude on record is:  "
							+ Double.toString(worker
									.highestMagnitude(earthquakes)));
				}
				// If "Lowest Magnitude" is selected the worker class will call
				// it's lowestMagnitude method returning the highest Magnitude.
				// It will then be set to the text field
				if (filter.getSelectedItem() == "Lowest Magnitude") {
					textField.setText("The Lowest Magnitude on record is: "
							+ Double.toString(worker
									.lowestMagnitude(earthquakes)));
				}
				// If "Deepest Depth" is selected the worker class will call
				// it's deepestDepth method returning the highest Magnitude.
				// It will then be set to the text field
				if (filter.getSelectedItem() == "Deepest Depth") {
					textField.setText("The Deepest Depth on record is:  "
							+ Double.toString(worker.DeepestDepth(earthquakes))
							+ "km");
				}
				// If "Shortest Depth" is selected the worker class will call
				// it's shortestDepth method returning the highest Magnitude.
				// It will then be set to the text field
				if (filter.getSelectedItem() == "Shortest Depth") {
					textField.setText("The Shortest Depth on record is:  "
							+ Double.toString(worker.ShortestDepth(earthquakes))
							+ "km");

				}
			}
		});
		// Sets the location and size of the button "btnSearch".
		btnSearch.setBounds(427, 173, 129, 43);
		// Adds button search to "panelOne".
		panelOne.add(btnSearch);
		// Creates a new instance of JTextField called "textField".
		textField = new JTextField();
		// Sets the location and size of the text field.
		textField.setBounds(234, 121, 322, 41);
		// Sets the number of columns for the text field.
		textField.setColumns(10);
		// Adds "textField" to "panelOne".
		panelOne.add(textField);
		// Instance of JPanel to create panelTwo
		panelTwo = new JPanel();
		// Sets the layout of "panelTwo" to absolute.
		panelTwo.setLayout(null);
		// Creates an instance of JButton called "btnListAll".
		btnListAll = new JButton("List All");
		// Sets text color of "btnListAll" to "Gray".
		btnListAll.setForeground(Color.GRAY);
		// An ActionListener for "btnListAll" that is called on button click.
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// for the size of the Earthquake ArrayList each row will be
				// appended to the text Area.
				for (int i = 0; i < earthquakes.size(); i++) {
					textArea.append(earthquakes.get(i).toString());
				}

			}
		});
		// Instance of JTextField to create txtID.
		txtID = new JTextField();
		// An ActionListener for "txtID" that is called when a new id number is
		// entered.
		txtID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If the entered id number is less than 0 or greater than the
				// size of the Earthquake ArrayList a message dialog box will
				// open. The dialog box will display no results found. If it is
				// within the size of the ArrayList it will be listed within the
				// text area.
				if ((Integer.parseInt(txtID.getText()) > earthquakes.size())
						|| (Integer.parseInt(txtID.getText()) < 0)) {
					JOptionPane.showMessageDialog(panelTwo, "No results found");
				} else {
					textArea.setText("");
					textArea.append(earthquakes.get(

					Integer.parseInt(txtID.getText())).toString());
				}
			}
		});
		// Sets the location and size of the text field called "txtID".
		txtID.setBounds(401, 18, 49, 28);
		// Adds "txtID" to "panelTwo".
		panelTwo.add(txtID);
		// Sets the number of columns for "txtID".
		txtID.setColumns(10);
		// Sets the font-family, size and weight for the button called
		// "btnListAll".
		btnListAll.setFont(new Font("Segoe UI", Font.BOLD, 12));
		// Sets the location and size for the button called "btnListAll".
		btnListAll.setBounds(31, 18, 89, 28);
		// Adds "btnListAll" to "panelTwo".
		panelTwo.add(btnListAll);
		// Adds "panelOne" to "tabbedPane".
		tabbedPane.addTab("Search", null, panelOne, null);
		// Adds "panelTwo" to "tabbedPane".
		tabbedPane.addTab("New tab", null, panelTwo, null);
		// Creates a new instance of JTextArea called "textArea".
		textArea = new JTextArea(16, 50);
		// Creates a new instance of JScrollPane called "scrollPane" and adds
		// "textArea".
		scrollPane = new JScrollPane(textArea);
		// Sets the location and size of "scrollPane".
		scrollPane.setBounds(31, 66, 537, 226);
		// Adds "scrollPane" to "panelTwo".
		panelTwo.add(scrollPane);
		// Sets the scroll bar settings for the scroll pane.
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// Creates an instance of JLabel called "lblLogo".
		lblLogo = new JLabel("");
		// Loads an image into "lblLogo".
		lblLogo.setIcon(new ImageIcon("images/Logo.png"));
		// Sets the location and size of "lblLogo".
		lblLogo.setBounds(27, 25, 186, 168);
		// Adds "lblLogo" to "panelOne".
		panelOne.add(lblLogo);
		// Creates a new instance of JLabel called "lblQuickSearch".
		lblQuickSearch = new JLabel("Quick Search");
		// Sets the font-family, size and weight of the "lblQuickSearch".
		lblQuickSearch.setFont(new Font("Segoe UI", Font.BOLD, 19));
		// Sets the text color of "lblQuickSearch to "White".
		lblQuickSearch.setForeground(Color.WHITE);
		// Sets the location and size of "lblQuickSearch".
		lblQuickSearch.setBounds(237, 70, 215, 38);
		// Adds "lblQuickSearch" to "panelOne".
		panelOne.add(lblQuickSearch);
		// Creates a new instance of JLabel called "background".
		background = new JLabel();
		// Sets the location and size of "background".
		background.setBounds(0, 0, 601, 353);
		// Assigns the image the "background".
		background.setIcon(new ImageIcon("images/main-background-2.png"));
		// Adds "background" to "panelOne".
		panelOne.add(background);
		tabbedPane.addTab("Data", null, panelTwo, null);
		// Instance of JButton to create the Add New Button.
		btnAddNew = new JButton("Add New");
		// Sets the text color of "btnAddNew" to "Gray".
		btnAddNew.setForeground(Color.GRAY);

		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append(earthquakes.get(getNum()).toString());
				txtID.setText(Integer.toString(earthquakes.get(num)
						.getIdNumber()));
				num++;
			}
		});
		// Sets the font-family, size and weight of "btnAddNew".
		btnAddNew.setFont(new Font("Segoe UI", Font.BOLD, 12));
		// Sets the location and size of "btnAddNew".
		btnAddNew.setBounds(130, 18, 89, 28);
		// Adds "btnAddNew" to "panelTwo".
		panelTwo.add(btnAddNew);
		// Creates a new instance of JComboBox called "comboFilter".
		comboFilter = new JComboBox<String>();
		// Sets the font-family, size and weight for "comboFilter".
		comboFilter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		// Sets the text color for "comboFilter" to "Gray".
		comboFilter.setForeground(Color.GRAY);
		comboFilter.addItem("Filter");
		// Adds the item "Magnitude" to "comboFilter".
		comboFilter.addItem("Magnitude");
		comboFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// This Collections.sort uses the MagnitudeComparator to sort
				// the Earthquake ArrayList in descending order. By comparing
				// the
				// magnitude size.
				Collections.sort(earthquakes, new MagnitudeComparator());
			}
		});
		// Adds the item "Depth" to "comboFilter".
		comboFilter.addItem("Depth");
		// An ActionListener that responds when the item "Depth" is selected
		// from the "comboFilter".
		comboFilter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// This Collections.sort uses the DepthComparator to sort the
				// Earthquake ArrayList in descending order. By comparing the
				// depth.
				Collections.sort(earthquakes, new DepthComparator());

			}
		});
		// Creates an instance of JLabel called "lblId".
		lblId = new JLabel("ID No:");
		// Sets the font-family, size, and weight of "lblId".
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		// Sets the text color of "lblId" to "White".
		lblId.setForeground(Color.WHITE);
		// Sets the location and size of "lblId".
		lblId.setBounds(352, 22, 48, 21);
		// Adds "lblId" to "panelTwo.
		panelTwo.add(lblId);
		// Set location and size of "comboFilter".
		comboFilter.setBounds(468, 19, 102, 26);
		// Add "comboFilter" to "panelTwo".
		panelTwo.add(comboFilter);
		// Creates a new instance of JButton called "btnClear".
		btnClear = new JButton("Clear");
		// Sets the text color of "btnClear" to "Gray".
		btnClear.setForeground(Color.GRAY);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				num = 0;
			}
		});
		// Sets the font-family, size and weight of "btnClear".
		btnClear.setFont(new Font("Segoe UI", Font.BOLD, 12));
		// Sets the location and size of "btnClear".
		btnClear.setBounds(235, 18, 89, 28);
		// Adds "btnClear" to "panelTwo".
		panelTwo.add(btnClear);
		// Creates an instance of JButton called "btnSort".
		btnSort = new JButton("Reverse");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Collections.reverse(earthquakes);

			}
		});
		// Sets the text color of "btnSort" to "Gray".
		btnSort.setForeground(Color.GRAY);
		// Sets the font-family, size and weight for "btnSort".
		btnSort.setFont(new Font("Segoe UI", Font.BOLD, 12));
		// Sets the location and size for "btnSort".
		btnSort.setBounds(317, 303, 116, 28);
		// Adds "btnSort" to "panelTwo".
		panelTwo.add(btnSort);
		// Creates an instance of JButton called "btnReset".
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(earthquakes, new IdNumberComparator());
			}
		});
		// Sets the text color of "btnReset" to "Gray".
		btnReset.setForeground(Color.GRAY);
		// Set the font-family, size and weight for "btnReset".
		btnReset.setFont(new Font("Segoe UI", Font.BOLD, 12));
		// Sets the location and size of "btnReset".
		btnReset.setBounds(185, 303, 116, 28);
		// Adds "btnReset" to "panelTwo".
		panelTwo.add(btnReset);
		// Creates a new instance of JLabel called "lblBackground".
		lblBackground = new JLabel("");
		// Assigns an image to "lblBackground".
		lblBackground.setIcon(new ImageIcon("images/data-background.png"));
		// Sets the location and size of "lblBackground".
		lblBackground.setBounds(1, 1, 596, 349);
		// Adds "lblBackground" to "panelTwo".
		panelTwo.add(lblBackground);
		// Creates an instance of JPanel called "panelThree".
		panelThree = new JPanel();
		// Creates an instance of JPanel called "panelFour".
		panelFour = new JPanel();
		// Adds "panelThree" to "tabbedPane".
		tabbedPane.addTab("Manual Search", null, panelThree, null);
		// Sets the layout of "panelThree" to absolute.
		panelThree.setLayout(null);
		// Creates a new instance of JLabel called "lblLogo2".
		lblLogo2 = new JLabel("");
		// Assigns an image to "lblLogo2".
		lblLogo2.setIcon(new ImageIcon("images/Logo.png"));
		// Sets the location and size of "lblLogo2".
		lblLogo2.setBounds(27, 25, 186, 168);
		// Adds "lblLogo2" to "panelThree".
		panelThree.add(lblLogo2);
		// Creates a new instance of "lblDescription".
		lblDescription = new JLabel("Description:");
		// Sets location and size of "lblDescription".
		lblDescription.setBounds(193, 213, 75, 14);
		// Adds "lblDescription" to "panelThree".
		panelThree.add(lblDescription);
		// Sets the text color of "lblDescription" to "White".
		lblDescription.setForeground(Color.WHITE);
		// Sets the font-family, size and weight of "lblDescription".
		lblDescription.setFont(new Font("Segoe UI", Font.BOLD, 13));
		// Creates a new instance of JTexField called "textDescription".
		textDescription = new JTextField();
		// Sets the location and size of "textDescription".
		textDescription.setBounds(285, 210, 279, 20);
		// Adds "textDescription" to "panelThree".
		panelThree.add(textDescription);
		// Sets the number of columns for "textDescription".
		textDescription.setColumns(10);
		// Creates an instance of JButton called "btnPrevious".
		btnPrevious = new JButton("Previous");
		// Sets the location and size of "btnPrevious".
		btnPrevious.setBounds(276, 256, 129, 43);
		// Sets the visibility of "btnPrevious" to "false".
		btnPrevious.setVisible(false);
		// Creates a new instance of JButton called "btnStart".
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ID <= earthquakes.size()) {
					// Sets the value of the textID field based on the ID.
					textID.setText(Integer.toString(ID) + "/"
							+ earthquakes.size());
					// Sets the value of the textMagnitude field based on the
					// ID.
					textMagnitude.setText(Double.toString(earthquakes.get(ID)
							.getMagnitude()));
					// Sets the value of the textMagType field based on the ID.
					textMagType.setText(earthquakes.get(ID).getMagType());
					// Sets the value of the textDate field based on the ID.
					textDate.setText(earthquakes.get(ID).getDate());
					// Sets the value of the textDepth field based on the ID.
					textDepth.setText(Double.toString(earthquakes.get(ID)
							.getDepth()));
					// Sets the value of the textLatitude field based on the ID.
					textLatitude.setText(Float.toString(earthquakes.get(ID)
							.getLatitude()));
					// Sets the value of the textLongitude field based on the
					// ID.
					textLongitude.setText(Float.toString(earthquakes.get(ID)
							.getLongitude()));
					// Sets the value o the textDescription field based on the
					// ID.
					textDescription.setText(earthquakes.get(ID)
							.getDescription());
					// Sets the visibility of the start button to false making
					// it's opacity 0.
					btnStart.setVisible(false);
					// Sets the visibility of the next button to visible.
					btnNext.setVisible(true);
					// Sets the visibility of the previous button to visible.
					btnPrevious.setVisible(true);
				}
			}
		});
		// Sets the text color of "btnStart" to "Gray".
		btnStart.setForeground(Color.GRAY);
		// Sets the font-family, size and weight of the text in "btnStart".
		btnStart.setFont(new Font("Segoe UI", Font.BOLD, 12));
		// Sets the location and size of "btnStart".
		btnStart.setBounds(357, 256, 129, 43);
		// Adds "btnStart" to "panelThree".
		panelThree.add(btnStart);
		// Adds "btnPrevious" to "panelThree".
		panelThree.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (ID >= 1) {
					--ID;
					textID.setText(Integer.toString(ID) + "/"
							+ earthquakes.size());
					textMagnitude.setText(Double.toString(earthquakes.get(ID)
							.getMagnitude()));
					textMagType.setText(earthquakes.get(ID).getMagType());
					textDate.setText(earthquakes.get(ID).getDate());
					textDepth.setText(Double.toString(earthquakes.get(ID)
							.getDepth()));
					textDepth.setText(Double.toString(earthquakes.get(ID)
							.getDepth()));
					textLatitude.setText(Float.toString(earthquakes.get(ID)
							.getLatitude()));
					textLongitude.setText(Float.toString(earthquakes.get(ID)
							.getLongitude()));
					textDescription.setText(earthquakes.get(ID)
							.getDescription());
				}
			}
		});
		// Sets the text color of "btnPrevious" to "Gray".
		btnPrevious.setForeground(Color.GRAY);
		// Sets the font-family, size and weight of "btnPrevious".
		btnPrevious.setFont(new Font("Segoe UI", Font.BOLD, 12));
		// Creates a new instance of JButton called "btnNext".
		btnNext = new JButton("Next");
		// Sets the location and size of "btnNext".
		btnNext.setBounds(435, 256, 129, 43);
		// Sets the visibility of "btnNext" to false.
		btnNext.setVisible(false);
		// Adds "btnNext" to "panelThree".
		panelThree.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ID++;

				// Sets the value of the textID field to the value of ID
				textID.setText(Integer.toString(ID) + "/" + earthquakes.size());
				// Sets the value of the textMagnitude field.
				textMagnitude.setText(Double.toString(earthquakes.get(ID)
						.getMagnitude()));
				// Sets the value of the textMagType field.
				textMagType.setText(earthquakes.get(ID).getMagType());
				// Sets the value of the textDate field.
				textDate.setText(earthquakes.get(ID).getDate());
				// Sets the value of the textDepth field.
				textDepth.setText(Double.toString(earthquakes.get(ID)
						.getDepth()));
				// Sets the value of the textLatitude field.
				textLatitude.setText(Float.toString(earthquakes.get(ID)
						.getLatitude()));
				// Sets the value of the textLongitude field.
				textLongitude.setText(Float.toString(earthquakes.get(ID)
						.getLongitude()));
				// Sets the value of the textDescription field.
				textDescription.setText(earthquakes.get(ID).getDescription());

			}
		});
		// Sets the text of "btnNext" to "Gray".
		btnNext.setForeground(Color.GRAY);
		// Sets the font-family, size and weight for "btnNext".
		btnNext.setFont(new Font("Segoe UI", Font.BOLD, 12));
		// Creates a new instance of JLabel called "lblLatitude".
		lblLatitude = new JLabel("Latitude:");
		// Sets the location and size of "lblLatitude".
		lblLatitude.setBounds(225, 176, 67, 14);
		// Sets the text color of "lblLatitude" to "White".
		lblLatitude.setForeground(Color.WHITE);
		// Sets the font-family, size and weight of "lblLatitude".
		lblLatitude.setFont(new Font("Segoe UI", Font.BOLD, 13));
		// Adds "lblLatitude" to "panelThree".
		panelThree.add(lblLatitude);
		// Creates a new instance of JTextField called "textLatitude".
		textLatitude = new JTextField();
		// Sets the location and size of "textLatitude".
		textLatitude.setBounds(293, 173, 86, 20);
		// Sets the number of columns for "textLatitude".
		textLatitude.setColumns(10);
		// Adds "textLatitude" to "panelThree".
		panelThree.add(textLatitude);
		// Creates a new instance of JLabel called "lblLongitude".
		lblLongitude = new JLabel("Longitude:");
		// Sets the location and size of "lblLongitude".
		lblLongitude.setBounds(396, 176, 69, 14);
		// Adds "lblLongitude" to "panelThree".
		panelThree.add(lblLongitude);
		// Sets the text color of "lblLongitude".
		lblLongitude.setForeground(Color.WHITE);
		// Sets the font-family, size and weight of "lblLongitude".
		lblLongitude.setFont(new Font("Segoe UI", Font.BOLD, 13));
		// Creates a new instance of JTextField called "textLongitude".
		textLongitude = new JTextField();
		// Sets the location and size of "textLongitude".
		textLongitude.setBounds(478, 173, 86, 20);
		// Adds "textLongitude" to "panelThree".
		panelThree.add(textLongitude);
		// Sets the number of columns for "textLongitude".
		textLongitude.setColumns(10);
		// Creates a new instance of JTextField called "textDate".
		textDate = new JTextField();
		// Sets the location and size of "textDate".
		textDate.setBounds(357, 133, 207, 20);
		// Adds "textDate" to "panelThree".
		panelThree.add(textDate);
		// Sets the number of columns for "textDate".
		textDate.setColumns(10);
		// Creates an instance of JLabel called "lblDate".
		lblDate = new JLabel("Date / Time:");
		// Sets the location and size of "lblDate".
		lblDate.setBounds(263, 136, 87, 14);
		// Adds "lblDate" to "panelThree".
		panelThree.add(lblDate);
		// Sets the text color of "lblDate" to "White".
		lblDate.setForeground(Color.WHITE);
		// Set font-family, size and weight of "lblDate".
		lblDate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		// Creates a new instance of JLabel called "lblMagnitude".
		lblMagnitude = new JLabel("Magnitude:");
		// Sets the location and size of "lblMagnitude".
		lblMagnitude.setBounds(394, 59, 80, 14);
		// Adds "lblMagnitude" to "panelThree".
		panelThree.add(lblMagnitude);
		// Sets the text color of "lblMagnitude" to "White".
		lblMagnitude.setForeground(Color.WHITE);
		// Sets the font-family, size and weight of "lblMagnitude".
		lblMagnitude.setFont(new Font("Segoe UI", Font.BOLD, 13));
		// Creates a new instance of JTextField called "textMagnitude".
		textMagnitude = new JTextField();
		// Sets the location and size of "textMagnitude".
		textMagnitude.setBounds(478, 56, 86, 20);
		// Adds "textMagnitude" to "panelThree".
		panelThree.add(textMagnitude);
		// Sets the number of columns for "textMagnitude".
		textMagnitude.setColumns(10);
		// Creates a new instance of JLabel called "lblDepth".
		lblDepth = new JLabel("Depth:");
		// Sets the location and size of "lblDepth".
		lblDepth.setBounds(424, 97, 46, 14);
		// Adds "lblDepth" to "panelThree".
		panelThree.add(lblDepth);
		// Sets the text color of "lblDepth" to "White".
		lblDepth.setForeground(Color.WHITE);
		// Sets the font-family, size and weight of "lblDepth".
		lblDepth.setFont(new Font("Segoe UI", Font.BOLD, 13));
		// Creates an instance of JTextField called "textDepth".
		textDepth = new JTextField();
		// Sets the location and size of "textDepth".
		textDepth.setBounds(480, 94, 86, 20);
		// Adds "textDepth" to "panelThree".
		panelThree.add(textDepth);
		// Sets the number of columns for "textDepth".
		textDepth.setColumns(10);
		// Creates a new instance of JTextField called "textMagType".
		textMagType = new JTextField();
		// Sets the location and size of "textMagType".
		textMagType.setBounds(319, 94, 86, 20);
		// Adds "textMagType" to "panelThree".
		panelThree.add(textMagType);
		// Sets the number of columns for "textMagType".
		textMagType.setColumns(10);
		// Creates an instance of JLabel called "lblMagType".
		lblMagType = new JLabel("Mag Type:");
		// Sets the location and size of "lblMagType".
		lblMagType.setBounds(246, 97, 69, 14);
		// Adds "lblMagType" to "panelThree".
		panelThree.add(lblMagType);
		// Sets the text color of "lblMagType".
		lblMagType.setForeground(Color.WHITE);
		// Sets the font-family, size and weight of "lblMagType".
		lblMagType.setFont(new Font("Segoe UI", Font.BOLD, 13));
		// Creates a new instance of JTextField called "textID".
		textID = new JTextField();
		// Sets the location and size of "textID".
		textID.setBounds(307, 56, 74, 20);
		// Adds "textID" to "panelThree".
		panelThree.add(textID);
		// Sets the number of columns for "textID".
		textID.setColumns(10);
		// Creates a new instance of JLabel called "lblIDNumber".
		lblIDNumber = new JLabel("Id No:");
		// Sets the location and size of "lblIDNumber".
		lblIDNumber.setBounds(246, 59, 46, 14);
		// Adds "lblIDNumber" to "panelThree".
		panelThree.add(lblIDNumber);
		// Sets the text color
		lblIDNumber.setForeground(Color.WHITE);
		// Sets the font-family, size and weight of "lblIDNumber".
		lblIDNumber.setFont(new Font("Segoe UI", Font.BOLD, 13));
		// Creates a new instance of JLabel called "lblBackgroundThree".
		lblBackgroundThree = new JLabel("");
		// Sets the location and size of "lblBackgroundThree".
		lblBackgroundThree.setBounds(0, 0, 596, 351);
		// Adds "lblBackgroundThree" to "panelThree".
		panelThree.add(lblBackgroundThree);
		// Adds "panelFour to "tabbedPane".
		tabbedPane.addTab("Graph", null, panelFour, null);

	}

	/**
	 * This method creates a line graph using the depth and the height of all
	 * the earthquakes found in the earthquake.csv file.
	 */
	// This method uses the magnitude and depth from the Earthquake ArrayList to
	// output the data in a line graph.
	public void createLineChart() {
		// Dataset creation
		XYSeries series1 = new XYSeries("Earthquake");

		for (int i = 0; i < earthquakes.size(); i++) {
			series1.add(earthquakes.get(i).getMagnitude(), earthquakes.get(i)
					.getDepth());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);

		// Create the chart...
		JFreeChart chart = ChartFactory.createXYLineChart("Earthquake",
				"Magnitude", "Depth", dataset, PlotOrientation.VERTICAL, true,
				true, true);
		// Get a reference to the plot for further customization...
		XYPlot plot = chart.getXYPlot();

		// the y axis is the domain axis
		NumberAxis range = (NumberAxis) plot.getRangeAxis();
		range.setRange(45, 80);
		range.setTickUnit(new NumberTickUnit(50));
		panelFour.setLayout(null);
		// Creates an instance of Graph Panel
		ChartPanel graphPanel = new ChartPanel(chart);
		graphPanel.setBounds(1, 0, 596, 351);
		panelFour.add(graphPanel);
	}

	/**
	 * This method accessor method returns the primitive num.
	 * 
	 * @return num
	 */
	public int getNum() {
		return num;
	}
}

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author Matt Storms
 * February 2014
 */
public class MediaManagerDAI implements ActionListener {
	
	private ArrayList mediaList;
	private MediaDAI mDAI;
	
	private JFrame frame;
	
	private JLabel jlbTitle, jlbYear, jlbGenre, jlbMediaType, jlbComments, jlbCurrentVal;
	private JTextField jtfTitle, jtfYear, jtfGenre, jtfMediaType, jtfComments, jtfCurrentVal;
	private JButton jbnSearch, jbnAdd, jbnExit, jbnPrev, jbnNext, jbnClear;
	
	private String title, genre, mediaType, comments;
	private int year;
	private double currentVal;
	
	private Container cPane;
	private int recordNum;

	/**
	 * Main method
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MediaManagerDAI();
			}
		});

	}
	
	/**
	 * Constructor
	 */
	public MediaManagerDAI() {
		title = "";
		genre = "";
		mediaType = "";
		comments = "";
		year = -1;
		currentVal = -1;
		
		createGUI();
		
		mediaList = new ArrayList();
		
		mDAI = new MediaDAI();
		
	}
	
	/**
	 * Create user interface
	 */
	private void createGUI() {
		frame = new JFrame("Media Library");
		
		cPane = frame.getContentPane();
		cPane.setLayout(new GridBagLayout());
		
		setUpComponents();
		
		frame.setSize(300, 325);;
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Set up components of form
	 */
	private void setUpComponents() {
		// Set labels
		jlbTitle = new JLabel("Title");
		jlbYear = new JLabel("Year");
		jlbGenre = new JLabel("Genre");
		jlbMediaType = new JLabel("Media Type");
		jlbComments = new JLabel("Comments");
		jlbCurrentVal = new JLabel("Current Value");
		
		// Set titles
		jtfTitle = new JTextField(50);
		jtfYear = new JTextField(4);
		jtfGenre = new JTextField(20);
		jtfMediaType = new JTextField(20);
		jtfComments = new JTextField(100);
		jtfCurrentVal = new JTextField(10);
		
		// Set buttons
		jbnSearch = new JButton("Search");
		jbnAdd = new JButton("Add");
		jbnExit = new JButton("Exit");
		jbnPrev = new JButton();
		jbnPrev.setIcon(new ImageIcon("images/back.png"));
		jbnNext = new JButton();
		jbnNext.setIcon(new ImageIcon("images/next.png"));
		jbnClear = new JButton("Clear");
		
		// Set and add to GridBag
		GridBagConstraints gridBagConstraintsx01 = new GridBagConstraints();
		gridBagConstraintsx01.gridx = 0;
		gridBagConstraintsx01.gridy = 0;
		gridBagConstraintsx01.insets = new Insets(5,5,5,5);
		cPane.add(jlbTitle, gridBagConstraintsx01);
		
		GridBagConstraints gridBagConstraintsx02 = new GridBagConstraints();
		gridBagConstraintsx02.gridx = 1;
		gridBagConstraintsx02.insets = new Insets(5,5,5,5);
		gridBagConstraintsx02.gridy = 0;
		gridBagConstraintsx02.gridwidth = 2;
		gridBagConstraintsx02.fill = GridBagConstraints.BOTH;
		cPane.add(jtfTitle, gridBagConstraintsx02);
		
		GridBagConstraints gridBagConstraintsx03 = new GridBagConstraints();
		gridBagConstraintsx03.gridx = 0;
		gridBagConstraintsx03.gridy = 1;
		gridBagConstraintsx03.insets = new Insets(5,5,5,5);
		cPane.add(jlbYear, gridBagConstraintsx03);
		
		GridBagConstraints gridBagConstraintsx04 = new GridBagConstraints();
		gridBagConstraintsx04.gridx = 1;
		gridBagConstraintsx04.insets = new Insets(5,5,5,5);
		gridBagConstraintsx04.gridy = 1;
		gridBagConstraintsx04.gridwidth = 2;
		gridBagConstraintsx04.fill = GridBagConstraints.BOTH;
		cPane.add(jtfYear, gridBagConstraintsx04);
		
		GridBagConstraints gridBagConstraintsx05 = new GridBagConstraints();
		gridBagConstraintsx05.gridx = 0;
		gridBagConstraintsx05.gridy = 2;
		gridBagConstraintsx05.insets = new Insets(5,5,5,5);
		cPane.add(jlbGenre, gridBagConstraintsx05);
		
		GridBagConstraints gridBagConstraintsx06 = new GridBagConstraints();
		gridBagConstraintsx06.gridx = 1;
		gridBagConstraintsx06.insets = new Insets(5,5,5,5);
		gridBagConstraintsx06.gridy = 2;
		gridBagConstraintsx06.gridwidth = 2;
		gridBagConstraintsx06.fill = GridBagConstraints.BOTH;
		cPane.add(jtfGenre, gridBagConstraintsx06);
		
		GridBagConstraints gridBagConstraintsx07 = new GridBagConstraints();
		gridBagConstraintsx07.gridx = 0;
		gridBagConstraintsx07.gridy = 3;
		gridBagConstraintsx07.insets = new Insets(5,5,5,5);
		cPane.add(jlbMediaType, gridBagConstraintsx07);
		
		GridBagConstraints gridBagConstraintsx08 = new GridBagConstraints();
		gridBagConstraintsx08.gridx = 1;
		gridBagConstraintsx08.insets = new Insets(5,5,5,5);
		gridBagConstraintsx08.gridy = 3;
		gridBagConstraintsx08.gridwidth = 2;
		gridBagConstraintsx08.fill = GridBagConstraints.BOTH;
		cPane.add(jtfMediaType, gridBagConstraintsx08);
		
		GridBagConstraints gridBagConstraintsx09 = new GridBagConstraints();
		gridBagConstraintsx09.gridx = 0;
		gridBagConstraintsx09.gridy = 4;
		gridBagConstraintsx09.insets = new Insets(5,5,5,5);
		cPane.add(jlbComments, gridBagConstraintsx09);
		
		GridBagConstraints gridBagConstraintsx10 = new GridBagConstraints();
		gridBagConstraintsx10.gridx = 1;
		gridBagConstraintsx10.insets = new Insets(5,5,5,5);
		gridBagConstraintsx10.gridy = 4;
		gridBagConstraintsx10.gridwidth = 2;
		gridBagConstraintsx10.fill = GridBagConstraints.BOTH;
		cPane.add(jtfComments, gridBagConstraintsx10);
		
		GridBagConstraints gridBagConstraintsx11 = new GridBagConstraints();
		gridBagConstraintsx11.gridx = 0;
		gridBagConstraintsx11.gridy = 5;
		gridBagConstraintsx11.insets = new Insets(5,5,5,5);
		cPane.add(jlbCurrentVal, gridBagConstraintsx11);
		
		GridBagConstraints gridBagConstraintsx12 = new GridBagConstraints();
		gridBagConstraintsx12.gridx = 1;
		gridBagConstraintsx12.insets = new Insets(5,5,5,5);
		gridBagConstraintsx12.gridy = 5;
		gridBagConstraintsx12.gridwidth = 2;
		gridBagConstraintsx12.fill = GridBagConstraints.BOTH;
		cPane.add(jtfCurrentVal, gridBagConstraintsx12);
		
		GridBagConstraints gridBagConstraintsx13 = new GridBagConstraints();
		gridBagConstraintsx13.gridx = 0;
		gridBagConstraintsx13.gridy = 6;
		gridBagConstraintsx13.insets = new Insets(5,5,5,5);
		gridBagConstraintsx13.fill = GridBagConstraints.BOTH;
		cPane.add(jbnSearch, gridBagConstraintsx13);
		
		GridBagConstraints gridBagConstraintsx14 = new GridBagConstraints();
		gridBagConstraintsx14.gridx = 1;
		gridBagConstraintsx14.gridy = 6;
		gridBagConstraintsx14.insets = new Insets(5,5,5,5);
		gridBagConstraintsx14.fill = GridBagConstraints.BOTH;
		cPane.add(jbnAdd, gridBagConstraintsx14);
		
		GridBagConstraints gridBagConstraintsx15 = new GridBagConstraints();
		gridBagConstraintsx15.gridx = 2;
		gridBagConstraintsx15.gridy = 6;
		gridBagConstraintsx15.insets = new Insets(5,5,5,5);
		gridBagConstraintsx15.fill = GridBagConstraints.BOTH;
		cPane.add(jbnExit, gridBagConstraintsx15);
		
		GridBagConstraints gridBagConstraintsx16 = new GridBagConstraints();
		gridBagConstraintsx16.gridx = 0;
		gridBagConstraintsx16.gridy = 7;
		gridBagConstraintsx16.insets = new Insets(5,5,5,5);
		gridBagConstraintsx16.fill = GridBagConstraints.BOTH;
		cPane.add(jbnPrev, gridBagConstraintsx16);
		
		GridBagConstraints gridBagConstraintsx17 = new GridBagConstraints();
		gridBagConstraintsx17.gridx = 1;
		gridBagConstraintsx17.gridy = 7;
		gridBagConstraintsx17.insets = new Insets(5,5,5,5);
		gridBagConstraintsx17.fill = GridBagConstraints.HORIZONTAL;
		cPane.add(jbnClear, gridBagConstraintsx17);
		
		GridBagConstraints gridBagConstraintsx18 = new GridBagConstraints();
		gridBagConstraintsx18.gridx = 2;
		gridBagConstraintsx18.gridy = 7;
		gridBagConstraintsx18.insets = new Insets(5,5,5,5);
		gridBagConstraintsx18.fill = GridBagConstraints.BOTH;
		cPane.add(jbnNext, gridBagConstraintsx18);
		
		// Add actionlisteners
		jbnSearch.addActionListener(this);
		jbnAdd.addActionListener(this);
		jbnExit.addActionListener(this);
		jbnPrev.addActionListener(this);
		jbnNext.addActionListener(this);
		jbnClear.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == jbnSearch) {
			searchMedia();
		}
		else if (e.getSource() == jbnAdd) {
			int output2 = JOptionPane.showConfirmDialog(null, "Add " + jtfTitle.getText() + " to Media Library?", "Add Item?", 
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (output2 == JOptionPane.YES_OPTION) {
				addMedia();
			}
			else {
				clear();
			}
		}
		else if (e.getSource() == jbnExit) {
			int output2 = JOptionPane.showConfirmDialog(null, "Are you sure you would like to exit?", 
					"End application?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (output2 == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		else if (e.getSource() == jbnPrev) {
			prevRecord();
		}
		else if (e.getSource() == jbnNext) {
			nextRecord();
		}
		else if (e.getSource() == jbnClear) {
			clear();
		}
	}
	
	/**
	 * Search stored media by title
	 */
	private void searchMedia() {

		title = jtfTitle.getText();
		title = title.toUpperCase();
		mediaList.clear();

		recordNum = 0;

		if(title.equals("")) {
			JOptionPane.showMessageDialog(null,"Please enter a Title to search.");
		}
		else {
			mediaList = mDAI.searchMedia(title);

			if(mediaList.size() == 0) {
				JOptionPane.showMessageDialog(null, "No records found.");
				clear();
			}
			else {
				MediaInfo media = (MediaInfo) mediaList.get(recordNum);

				jtfTitle.setText(media.getTitle());
				jtfYear.setText(String.valueOf(media.getYear()));
				jtfGenre.setText(media.getGenre());
				jtfMediaType.setText(media.getMediaType());
				jtfComments.setText(media.getComments());
				jtfCurrentVal.setText(String.valueOf(media.getCurrentValue()));
				
				JOptionPane.showMessageDialog(null, "Your search has found " + mediaList.size() + " result(s).");
			}
		}
	}
	
	
	/**
	 * Add media to database
	 */
	private void addMedia() {
		title = jtfTitle.getText();
		genre = jtfGenre.getText();
		mediaType = jtfMediaType.getText();
		comments = jtfComments.getText();
		
		try {
			year = Integer.parseInt(jtfYear.getText());
			currentVal = Double.parseDouble(jtfCurrentVal.getText());
		}
		catch(Exception e) {
			System.out.println(e);
		}

		if (title.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a Title.");
		}
		else if (genre.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a Genre.");
		}
		else if (mediaType.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a Media Type.");
		}
		else {
			MediaInfo media = new MediaInfo(title, year, genre, mediaType, comments, currentVal);
			mDAI.addMedia(media);
			JOptionPane.showMessageDialog(null, "Media Saved");
		}
	}
	
	/**
	 * Display next record
	 */
	private void nextRecord() {
		recordNum++;
		jbnPrev.setEnabled(true);

		if(recordNum >= mediaList.size()) {
			jbnNext.setEnabled(false);
			JOptionPane.showMessageDialog(null, "You have reached the end of " +
					"the search results.");

			recordNum--;
		}
		else {
			MediaInfo media = (MediaInfo) mediaList.get(recordNum);

			jtfTitle.setText(media.getTitle());
			jtfYear.setText(String.valueOf(media.getYear()));
			jtfGenre.setText(media.getGenre());
			jtfMediaType.setText(media.getMediaType());
			jtfComments.setText(media.getComments());
			jtfCurrentVal.setText(String.valueOf(media.getCurrentValue()));
		}
	}
	
	/**
	 * Display previous record
	 */
	private void prevRecord() {
		recordNum--;
		jbnNext.setEnabled(true);

		if(recordNum < 0 ) {
			jbnPrev.setEnabled(false);
			JOptionPane.showMessageDialog(null, "You have reached the begining " +
					"of the search results.");

			recordNum++;
		}
		else {
			MediaInfo media = (MediaInfo) mediaList.get(recordNum);

			jtfTitle.setText(media.getTitle());
			jtfYear.setText(String.valueOf(media.getYear()));
			jtfGenre.setText(media.getGenre());
			jtfMediaType.setText(media.getMediaType());
			jtfComments.setText(media.getComments());
			jtfCurrentVal.setText(String.valueOf(media.getCurrentValue()));
		}
	}
	
	/**
	 * Clear fields and array
	 */
	private void clear() {

		jtfTitle.setText("");
		jtfYear.setText("");
		jtfGenre.setText("");
		jtfMediaType.setText("");
		jtfComments.setText("");
		jtfCurrentVal.setText("");

		recordNum = -1;
		mediaList.clear();
		jbnNext.setEnabled(true);
		jbnPrev.setEnabled(true);
	}
}

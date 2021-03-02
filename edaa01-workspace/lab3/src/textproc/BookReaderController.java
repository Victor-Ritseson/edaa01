package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class BookReaderController {

	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}
	
	private void createWindow(GeneralWordCounter counter, String title, int width, int heigt) {
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//pane är en behållarkomponent till vilken de övriga komponentern 
		// (listvy, knappar etc.) ska läggas till.
		Container pane = frame.getContentPane();
		
		//Lägger till lista
		
		SortedListModel<Map.Entry<String, Integer>> sorted = new SortedListModel<Map.Entry<String, Integer>>(counter.getWordList());
		JList<Map.Entry<String, Integer>> mylist = new JList<Map.Entry<String, Integer>>(sorted);
		JScrollPane scrollPane = new JScrollPane(mylist);
		pane.add(scrollPane);
		
		
		//knappar
		
		JRadioButton alphabetic = new JRadioButton("Alphabetic",false);
		alphabetic.addActionListener(e -> {
			sorted.sort((p1,p2) -> p1.getKey().compareTo(p2.getKey()));
		
		});
		
		JRadioButton frequency = new JRadioButton("Frequency");
		frequency.addActionListener(e -> {
			sorted.sort((p1,p2) -> p2.getValue() - p1.getValue());
		
		});
		
		//nytt
		ButtonGroup bg = new ButtonGroup();
		bg.add(alphabetic);
		bg.add(frequency);
		
		JPanel panel = new JPanel();
		panel.add(alphabetic);
		panel.add(frequency);
		pane.add(panel, BorderLayout.SOUTH);
		
		
		JPanel panel2 = new JPanel();
		
		JTextField text = new JTextField(20);
		panel2.add(text);
		
		JButton searchbutton = new JButton("Search");
		searchbutton.addActionListener(e -> {
		String input = text.getText().toLowerCase().trim();	 
		int x = 0;
		Boolean found = false;
		for(int i = 0; i < sorted.getSize(); i++) {
			if(sorted.getElementAt(i).getKey().equals(input)) {
				x = i;
				found = true;
			}
		}
		if(found) {
			mylist.setSelectedIndex(x);
			mylist.ensureIndexIsVisible(x);
		} else {
			JOptionPane.showMessageDialog(pane, input + " finns inte");
		}
		
		});
		
		
		
		panel2.add(searchbutton);
	
		pane.add(panel2, BorderLayout.EAST);
		
		
		

			
		frame.pack();
		frame.setVisible(true);
	}
	
}

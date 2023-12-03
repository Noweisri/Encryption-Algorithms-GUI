

package Project;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.ScrollPaneLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.ViewportLayout;

import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.Boolean;
import java.awt.event.ActionEvent;

import javax.print.attribute.HashDocAttributeSet;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Window.Type;
import javax.swing.border.EtchedBorder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// private JTextField inputkey;
	private JLabel lblB;
	private File file;
	private int MAX_KEY = 0;
	private int keyFieldCount = 0;
	private int inputTextLength = 0;
	public int columnIndex = 0;
	
	// For easy encryption and decryption, we decided, the Option 7 group instead of the GUI group, to add these variables.
	private StringBuilder plaintext;
	private ArrayList<String> capitalizedBeforeEncryption;
	private ArrayList<Boolean> digitBeforeEncryption;
	private StringBuilder ciphertext;
	private boolean encrypted = false;
	
	// This is the homophone cipher object
	private Homophone cipher;
	
	// Create GUI components

	JTextField inputtext = new JTextField();
	JTextField resultsText = new JTextField("Here is where the result is shown.");
	JButton openbtn = new JButton("Open file");
	JButton savebtn = new JButton("Save results into file");
	JPanel keyspanel = new JPanel();
	JTextArea brief = new JTextArea();
	JLabel lblMaximumKeys = new JLabel();

	// Get Keys values from user
	public String[] getKeyValue() {
		java.util.List<String> ListOfKeys = new java.util.ArrayList<>();
		for (Component comp : keyspanel.getComponents()) {
			if (comp instanceof JTextField) {
				ListOfKeys.add(((JTextField) comp).getText());
			}
		}
		String[] ArrayOfKeys = ListOfKeys.toArray(new String[0]);
		return ArrayOfKeys;
	}

	// Show error messege
	public void Error(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	Object[][] ArrayOfAlgorithms = {
			{ "None", "Homophone", "DES", "AES" }, // Algorithm name , String
			{ "", "The Homophonic Substitution Cipher is a simple encryption algorithm where each plaintext English letter " +
				  "is converted into one of multiple possible cipher-letters specified to obscure the plaintext's frequency." + 
			      " (i.e. A frequent letter such as 'e' has four possible mappings: 'z', '7', '2' and '1'," + 
			      " while the letter 'z' has only one mapping: 'n' due to its low occurrence in English text.\n\n",
			      
			      "An acronym  of Data Encryption Standard, this algorithm creates a 56-bit key to encrypt a 64-bit data block by methods such as " +
			      "bitwise XORing, permutation and substitution. This algorithm is implemented as a Feistel architecture so that encryption and " + 
			      "decryption are very similar processes. This algorithm is not implemented in this simple GUI program.",
			      
			      "AES, also known as Advanced Encryption Standard, was published in 1998 following a selection process of alternative " + 
			      "algorithms to replace DES. A key consists of either 128, 192 or 256 bits by which a 128-bit data block can be encrypted and decrypted." + 
			      " In contrast with DES, AES uses a substitution and permutation network. " + 
			      "This algorithm is not implemented in this simple GUI program.", }, // Algorithm brief , String
			{ 0, 1, 1, 3 } // Maximum keys of an algorithm , Integer
	};

	// Lunch the program
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Initialize the MAin Frame
	public MainFrame() {
		setType(Type.NORMAL);
		setBackground(SystemColor.activeCaptionBorder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(110, 110, 609, 1007);
		setPreferredSize(new Dimension(609, 1007));
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setLayout(null);
		
		/* This is the main layout for the JFrame. */
		JScrollPane mainScrollPane = new JScrollPane(contentPane);
		mainScrollPane.setLayout(new ScrollPaneLayout());
		mainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setContentPane(mainScrollPane);
		
		plaintext = new StringBuilder(10000);
		ciphertext = new StringBuilder(10000);
		capitalizedBeforeEncryption = new ArrayList<String>(10000);
		digitBeforeEncryption = new ArrayList<Boolean>(10000);
		
		JLabel lblkey = new JLabel("Key");
		lblkey.setBounds(404, 354, 85, 22);
		lblkey.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblkey);
		lblkey.setVisible(false);

		// Fill the drop menu of algorithms && Each time the user clicks an algorithm
		// the brief of it changes
		JComboBox ChooseAlgorithm = new JComboBox();
		
		ChooseAlgorithm.setBounds(39, 69, 512, 35);
		contentPane.add(ChooseAlgorithm);
		for (Object element : ArrayOfAlgorithms[0]) {
			ChooseAlgorithm.addItem(element);
		}
		
		
		resultsText = new JTextField();
		resultsText.setBounds(39, 599, 512, 48);
		contentPane.add(resultsText);
		resultsText.setColumns(10);
		resultsText.setEditable(false);

		lblB = new JLabel("A Concise Description");
		lblB.setBounds(195, 115, 280, 22);
		lblB.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblB);
		brief.setWrapStyleWord(true);
		brief.setRows(4);
		brief.setColumns(5);
		brief.setBounds(0, 0, 512, 69);
		brief.setEditable(false);
		brief.setLineWrap(true);
		brief.setWrapStyleWord(true);

		JScrollPane briefScrollPane = new JScrollPane(brief);
		briefScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		lblB.setLabelFor(briefScrollPane);
		briefScrollPane.setBounds(39, 148, 512, 57);
		briefScrollPane.setBackground(new Color(240, 248, 255));
		briefScrollPane.setLayout(new ScrollPaneLayout());
		contentPane.add(briefScrollPane);
		
		JButton addkey = new JButton("Add key");
		addkey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (keyFieldCount < MAX_KEY) {
					JTextField inputkey = new JTextField();
					inputkey.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputkey.getPreferredSize().height));
					inputkey.setAlignmentX(Component.CENTER_ALIGNMENT);
					// inputkey = new JTextField();
					// inputkey.setBounds(38, 389, 197, 35);
					// contentPane.add(inputkey);
					// keyspanel.setColumns(2);
					keyspanel.add(inputkey);
					keyspanel.revalidate();
					keyspanel.repaint();
					keyFieldCount++;
				} else {
					Error("Maximum number of key fields reached.");
				}
			}
		});
		
		
		JLabel lblB_1 = new JLabel("Choose your Encryption Algorithm");
		lblB_1.setBounds(129, 36, 317, 22);
		lblB_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblB_1);

		inputtext.setBounds(39, 295, 513, 35);
		contentPane.add(inputtext);
		inputtext.setColumns(10);
		inputtext.setEditable(false);
		

		JLabel lblNewLabel_1 = new JLabel("Enter Plaintext or Ciphertext");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(129, 262, 302, 22);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel_1);

		openbtn.setBounds(72, 216, 108, 35);
		openbtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(openbtn);

		// Decrypt button
		JButton DecBtn = new JButton("Decrypt");
		DecBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(encrypted == false)
					return;
				ciphertext.delete(0, plaintext.capacity());
				ciphertext.append(resultsText.getText());
				plaintext.delete(0, plaintext.capacity());
				switch (ChooseAlgorithm.getSelectedItem().toString()) {
					case "Homophone": // write here the same name that you wrote in line 74
						// Call here you algorithm function
						Homophone.decrypt(ciphertext, plaintext, capitalizedBeforeEncryption, digitBeforeEncryption);
						break;
					default:
						break;
				}
				resultsText.setText("");
				if(file == null)
					inputtext.setEditable(true);
				resultsText.setText(plaintext.toString());
				encrypted = false;
				ciphertext.delete(0, ciphertext.capacity());
			}
		});
		DecBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		DecBtn.setBounds(341, 410, 134, 57);
		contentPane.add(DecBtn);

		// Encrypt button
		JButton EncBtn = new JButton("Encrypt");
		EncBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(encrypted == true)
					return;
				plaintext.delete(0, plaintext.capacity()); // make sure plaintext is only from the text field.
				plaintext.append(inputtext.getText());
				ciphertext.delete(0, ciphertext.capacity());
				capitalizedBeforeEncryption.clear();
				digitBeforeEncryption.clear();
				for(int i = 0; i < plaintext.length(); i++) {
					Character c = plaintext.charAt(i);
					// Check if a plaintext letter is capitalized
					if(Character.isUpperCase(c))
						capitalizedBeforeEncryption.add("true");
					else if(Character.isLowerCase(c))
						capitalizedBeforeEncryption.add("false");
					else
						capitalizedBeforeEncryption.add("null");
					// After that, check if it is a number
					if(Character.isDigit(c))
						digitBeforeEncryption.add(true);
					else
						digitBeforeEncryption.add(false);
				}
				switch (ChooseAlgorithm.getSelectedItem().toString()) {
					case "Homophone": // write here the same name that you wrote in line 74
						// Call here your algorithm function
						Homophone.encrypt(plaintext, ciphertext);
						break;
					default:
						break;
				}
				resultsText.setText(ciphertext.toString());
				inputtext.setEditable(false);
				encrypted = true;
				plaintext.delete(0, plaintext.capacity());
			}
		});
		EncBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		EncBtn.setBounds(105, 410, 134, 57);
		contentPane.add(EncBtn);

		// HMAC Button
		JButton HmacBtn = new JButton("HMAC");
		HmacBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Here implement HMAC Algorithm
			}
		});
		HmacBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		HmacBtn.setBounds(430, 489, 108, 40);
		contentPane.add(HmacBtn);

		// Hash Button
		JButton HashBtn = new JButton("HASH");
		HashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Here Implement Hash algorithm
			}
		});
		HashBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		HashBtn.setBounds(50, 489, 108, 40);
		contentPane.add(HashBtn);

		// Digital Signiture Button
		JButton DSBtn = new JButton("Digital Signiture");
		DSBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Here implement Digital Signutre Algorithm
			}
		});
		DSBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DSBtn.setBounds(224, 489, 134, 40);
		contentPane.add(DSBtn);

		
		

		JLabel lblResults = new JLabel("Results");
		lblResults.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblResults.setBounds(257, 556, 90, 22);
		contentPane.add(lblResults);
		savebtn.setForeground(SystemColor.activeCaptionText);
		savebtn.setBackground(new Color(240, 240, 240));

		savebtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		savebtn.setBounds(341, 216, 169, 35);
		contentPane.add(savebtn);
		
		JLabel lblWelcome = new JLabel("Welcome to the Simple CSC 429 Project");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, ١٦));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(105, 11, 360, 22);
		contentPane.add(lblWelcome);
		
		keyspanel.setBackground(SystemColor.activeCaption);
		keyspanel.setBounds(39, 354, 259, 35);
		contentPane.add(keyspanel);
		keyspanel.setLayout(new BoxLayout(keyspanel, BoxLayout.Y_AXIS));
		keyspanel.setVisible(true);
		
		JLabel lblMaximumKeys = new JLabel("New label");
		lblMaximumKeys.setFont(new Font("Tahoma", Font.BOLD, ١٣));
		lblMaximumKeys.setBounds(308, 387, 256, 14);
		contentPane.add(lblMaximumKeys);
		lblMaximumKeys.setVisible(false);
		
		

		/*
		 * _________________________________________________________________________________________________________________________
		 */

		// Open file function
		openbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choose a file to open");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fileChooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					try {
						Path filepath = Paths.get(file.getPath());
						String content = Files.readString(filepath);
						inputtext.setText(content);
						inputTextLength = content.length(); // line 54
						inputtext.setEditable(false);
					} catch (Exception ex) {
						System.out.println("Error reading file: " + ex.getMessage());
					}
				}
			}
		});

		// Save file function
		savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choose a file to save");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int userSelection = fileChooser.showSaveDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					if (!fileToSave.getName().contains(".")) {
						fileToSave = new File(fileToSave.toString() + ".txt");
					}
					try (FileWriter fileWriter = new FileWriter(fileToSave)) {
						fileWriter.write(resultsText.getText());
					} catch (IOException ex) {
						System.out.println("Error occured, " + ex.getMessage());
					}
				}
			}
		});
		
		ChooseAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < ArrayOfAlgorithms[0].length; j++) {
					if (ArrayOfAlgorithms[0][j].equals(ChooseAlgorithm.getSelectedItem())) {
						columnIndex = j;
						break;
					}
				}
				Object valueUnderneath = ArrayOfAlgorithms[1][columnIndex];
				brief.setText((String) valueUnderneath);
				MAX_KEY = (int) ArrayOfAlgorithms[2][columnIndex];
				if(ChooseAlgorithm.getSelectedItem().equals("None")) {
					inputtext.setEditable(false);
					DecBtn.setEnabled(false);
					EncBtn.setEnabled(false);
					lblB.setVisible(false);
					briefScrollPane.setVisible(false);
					lblMaximumKeys.setVisible(false);
				} else {
					if(file == null)
						inputtext.setEditable(true);
					
					DecBtn.setEnabled(true);
					EncBtn.setEnabled(true);
					lblB.setVisible(true);
					briefScrollPane.setVisible(true);
					lblMaximumKeys.setVisible(true);
					lblMaximumKeys.setText(Integer.toString(MAX_KEY) + " keys maximum are allowed.");
					switch((String) ChooseAlgorithm.getSelectedItem()) {
						case "Homophone":
							cipher = new Homophone();
							break;
					}
				}

			}
		});
	}
}
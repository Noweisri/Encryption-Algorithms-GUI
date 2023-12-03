import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BoxLayout;

public class MainFrame extends JFrame {

	public static String SHA1_encryption(String input) {
		try {
			// getInstance() method is called with algorithm SHA-1
			MessageDigest md = MessageDigest.getInstance("SHA-1");

			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			// return the HashText
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// private JTextField inputkey;
	private JLabel lblB;
	private int MAX_KEY = 0;
	private int keyFieldCount = 0;
	public int columnIndex = 0;
	private PlayfairCipher playfairCipher = new PlayfairCipher();

	// Create GUI components panel_1
	JPanel panel_1 = new JPanel();

	JTextField inputtext = new JTextField();
	JLabel results = new JLabel();
	JButton openbtn = new JButton("Open file");
	JButton savebtn = new JButton("Save results into file");
	JPanel keyspanel = new JPanel();
	JLabel brief = new JLabel("Enter Here Brief about your algorithm");

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
			{ "Example Algorithm", "SHA-1", "HMAC", "Playfair Cipher", }, // Algorithm name , String
			{ "Example Brief",
					"<html>SHA-1 takes an input message of any length and produces a fixed-size output of 160-bit, known as hash value. The hash function is designed to be computationally infeasible to reverse, meaning that it is extremely difficult to find two different messages that produce the same hash value, This property makes SHA-1 useful for a variety of applications. For example it can be used to verify the integrity of the data.</html>",
					"<html> HMAC is a security technique that ensures the integrity and authenticity of a message. It uses a combination of a cryptographic hash function and a secret key to generate a hash value.</html> \r\n"
							+ //
							"\n A key is required </html>",
					"<html>A symmetric encryption technique using a 5x5 matrix of letters to encrypt a pair of letters.</html>"
			}, // Algorithm brief // , // String
			{ 1, 1, 1, 1 } // Maximum keys of an algorithm , Integer

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
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(110, 110, 600, 960);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(555, 900));
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(contentPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setContentPane(scrollPane);

		JLabel lblkey = new JLabel("Key");
		lblkey.setBounds(50, 325, 85, 22);
		lblkey.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblkey);

		// Fill the drop menu of algorithms && Each time the user clicks an algorithm
		// the brief of it changes
		JComboBox ChooseAlgorithm = new JComboBox();
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
				System.out.print(MAX_KEY);

			}
		});
		ChooseAlgorithm.setBounds(39, 103, 512, 48);
		contentPane.add(ChooseAlgorithm);
		for (Object element : ArrayOfAlgorithms[0]) {
			ChooseAlgorithm.addItem(element);
		}

		lblB = new JLabel("brief about the algorithem");
		lblB.setBounds(50, 174, 280, 22);
		lblB.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblB);

		JPanel panel = new JPanel();
		panel.setBounds(39, 200, 512, 69);
		panel.setBackground(new Color(240, 248, 255));
		contentPane.add(panel);
		panel.setLayout(null);

		brief.setBounds(10, 11, 502, 47);
		panel.add(brief);
		brief.setVerticalAlignment(SwingConstants.TOP);
		brief.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblB_1 = new JLabel("Choose your Encryption Algorithm");
		lblB_1.setBounds(52, 70, 499, 22);
		lblB_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblB_1);

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
		addkey.setBounds(439, 363, 95, 40);
		addkey.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		contentPane.add(addkey);

		inputtext.setBounds(38, 518, 513, 35);
		contentPane.add(inputtext);
		inputtext.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Enter Plaintext or Ciphertext");
		lblNewLabel_1.setBounds(52, 484, 302, 22);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel_1);

		openbtn.setBounds(241, 294, 108, 35);
		openbtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(openbtn);

		// Decrypt button
		JButton DecBtn = new JButton("Decrypt");
		DecBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (ChooseAlgorithm.getSelectedItem().toString()) {
					case "SHA-1": // write here the same name that you wrote in line 74
						Error("SHA-1 cannot be used to decrypt");
					case "Playfair Cipher":

						Component comp = keyspanel.getComponent(0);
						if (comp instanceof JTextField) {
							String key = ((JTextField) comp).getText();
							playfairCipher.setKey(key);
							String ciphertext = inputtext.getText();
							String decryptedText = playfairCipher.decrypt(ciphertext);
							results.setText(decryptedText);
						}
						// Call here you algorithm function
						break;
				}
			}
		});
		DecBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		DecBtn.setBounds(298, 612, 134, 57);
		contentPane.add(DecBtn);

		// Encrypt button
		JButton EncBtn = new JButton("Encrypt");
		EncBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (ChooseAlgorithm.getSelectedItem().toString()) {

					case "SHA-1": // write here the same name that you wrote in line 74
						String plain_text = SHA1_encryption(inputtext.getText());
						results.setText(plain_text);
					case "Playfair Cipher":
						Component comp = keyspanel.getComponent(0);
						if (comp instanceof JTextField) {
							String key = ((JTextField) comp).getText();
							playfairCipher.setKey(key);
							String plaintext = inputtext.getText();
							String encryptedText = playfairCipher.encrypt(plaintext);
							results.setText(encryptedText);
						}
						// Call here your algorithm function
						break;
				}
			}
		});
		EncBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		EncBtn.setBounds(139, 612, 134, 57);
		contentPane.add(EncBtn);

		// HMAC Button
		JButton HmacBtn = new JButton("HMAC");
		HmacBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Here implement HMAC Algorithm
				try {
					String algorithmName = "HMAC"; // Replace with your algorithm name

					// Check if the selected algorithm is HMAC
					if (ChooseAlgorithm.getSelectedItem().toString().equals(algorithmName)) {

						String hmacResult = Hmac();

						// Display results
						results.setText(hmacResult);
					} else {

						Error("Please select the HMAC algorithm");
					}
				} catch (Exception ex) {
					// Display an error for any unexpected exception
					Error("An error occurred: " + ex.getMessage());
				}
			}
		});
		HmacBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		HmacBtn.setBounds(426, 680, 108, 40);
		contentPane.add(HmacBtn);

		// Hash Button
		JButton HashBtn = new JButton("HASH");
		HashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Here Implement Hash algorithm
			}
		});
		HashBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		HashBtn.setBounds(50, 680, 108, 40);
		contentPane.add(HashBtn);

		// Digital Signiture Button
		JButton DSBtn = new JButton("Digital Signiture");
		DSBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Here implement Digital Signutre Algorithm
			}
		});
		DSBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DSBtn.setBounds(215, 680, 134, 40);
		contentPane.add(DSBtn);

		panel_1.setLayout(null);
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(38, 781, 512, 48);
		contentPane.add(panel_1);

		results.setFont(new Font("Tahoma", Font.PLAIN, 14));
		results.setHorizontalAlignment(SwingConstants.LEFT);
		results.setBounds(10, 0, 502, 47);
		panel_1.add(results);

		JLabel lblResults = new JLabel("Results");
		lblResults.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblResults.setBounds(50, 748, 280, 22);
		contentPane.add(lblResults);
		savebtn.setForeground(SystemColor.activeCaptionText);
		savebtn.setBackground(new Color(240, 240, 240));

		savebtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		savebtn.setBounds(201, 855, 169, 35);
		contentPane.add(savebtn);

		keyspanel.setBackground(SystemColor.activeCaption);
		keyspanel.setBounds(39, 363, 261, 110);
		contentPane.add(keyspanel);
		keyspanel.setLayout(new BoxLayout(keyspanel, BoxLayout.Y_AXIS));

		/*
		 * _________________________________________________________________________________________________________________________
		 */

		// Open file function
		openbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
						inputtext.setText(content);
					} catch (Exception ex) {
						// showError("Error reading file: " + ex.getMessage());
					}
				}
			}
		});

		// Save file function
		savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");
				int userSelection = fileChooser.showSaveDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					if (!fileToSave.getName().contains(".")) {
						fileToSave = new File(fileToSave.toString() + ".txt");
					}
					try (FileWriter fileWriter = new FileWriter(fileToSave)) {
						fileWriter.write(results.getText());
					} catch (IOException ex) {
						Error("Error occured, " + ex.getMessage());
					}
				}
			}
		});

	}

	public String Hmac() throws NoSuchAlgorithmException, InvalidKeyException {
		String key = getKeyValue()[0]; // Get the key value

		Mac mac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
		mac.init(secretKey);

		String plainText = inputtext.getText(); // Get user input

		// Calculate HMAC
		byte[] hmacInBytes = mac.doFinal(plainText.getBytes());

		// Convert bytes to string
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : hmacInBytes) {
			stringBuilder.append(String.format("%02x", b));
		}

		// Return result and key
		return stringBuilder.toString();
	}

	public class PlayfairCipher {
		private String[][] table;

		public PlayfairCipher() {

		}

		public void setKey(String key) {
			key = parseString(key);
			table = this.cipherTable(key);
		}

		private String parseString(String text) {
			text = text.toUpperCase();
			text = text.replaceAll("[^A-Z]", "");
			text = text.replace("J", "I");
			return text;
		}

		private String[][] cipherTable(String key) {
			String[][] playfairTable = new String[5][5];
			String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";

			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 5; j++)
					playfairTable[i][j] = "";

			for (int k = 0; k < keyString.length(); k++) {
				boolean repeat = false;
				boolean used = false;
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (playfairTable[i][j].equals("" + keyString.charAt(k))) {
							repeat = true;
						} else if (playfairTable[i][j].equals("") && !repeat && !used) {
							playfairTable[i][j] = "" + keyString.charAt(k);
							used = true;
						}
					}
				}
			}
			return playfairTable;
		}

		public String encrypt(String text) {
			text = parseString(text);
			return cipher(text);
		}

		public String decrypt(String text) {
			text = parseString(text);
			return decode(text);
		}

		private String cipher(String in) {
			String[] digraph = createDigraphs(in);
			String out = "";
			for (String d : digraph) {
				char a = d.charAt(0);
				char b = d.charAt(1);
				int r1 = (int) getPoint(a).getX();
				int r2 = (int) getPoint(b).getX();
				int c1 = (int) getPoint(a).getY();
				int c2 = (int) getPoint(b).getY();

				if (r1 == r2) {
					c1 = (c1 + 1) % 5;
					c2 = (c2 + 1) % 5;
				} else if (c1 == c2) {
					r1 = (r1 + 1) % 5;
					r2 = (r2 + 1) % 5;
				} else {
					int temp = c1;
					c1 = c2;
					c2 = temp;
				}
				out += table[r1][c1] + "" + table[r2][c2];
			}
			return out;
		}

		private String decode(String in) {
			String[] digraph = createDigraphs(in);
			String out = "";
			for (String d : digraph) {
				char a = d.charAt(0);
				char b = d.charAt(1);
				int r1 = (int) getPoint(a).getX();
				int r2 = (int) getPoint(b).getX();
				int c1 = (int) getPoint(a).getY();
				int c2 = (int) getPoint(b).getY();

				if (r1 == r2) {
					c1 = (c1 - 1 + 5) % 5;
					c2 = (c2 - 1 + 5) % 5;
				} else if (c1 == c2) {
					r1 = (r1 - 1 + 5) % 5;
					r2 = (r2 - 1 + 5) % 5;
				} else {
					int temp = c1;
					c1 = c2;
					c2 = temp;
				}
				out += table[r1][c1] + "" + table[r2][c2];
			}
			return out;
		}

		private String[] createDigraphs(String in) {
			int length = in.length() / 2 + in.length() % 2;
			for (int i = 0; i < (length - 1); i++) {
				if (in.charAt(2 * i) == in.charAt(2 * i + 1)) {
					in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
					length = in.length() / 2 + in.length() % 2;
				}
			}
			if (in.length() / 2 == (length - 1))
				in += "X";

			String[] digraph = new String[length];
			for (int j = 0; j < length; j++) {
				digraph[j] = in.charAt(2 * j) + "" + in.charAt(2 * j + 1);
			}
			return digraph;
		}

		private Point getPoint(char c) {
			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 5; j++)
					if (c == table[i][j].charAt(0))
						return new Point(i, j);
			return null;
		}
	}
}

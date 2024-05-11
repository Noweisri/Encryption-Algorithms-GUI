package Project;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.lang.StringBuilder;
import java.lang.Boolean;
import java.lang.Character;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JButton;
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
import java.awt.event.ActionEvent;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BoxLayout;

public class MainFrame2 extends JFrame {

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
	
	
	public  String RSA_encryption(String input,int k) {
		
		
		if(getKeyValue().length>k)
		{
		String key = getKeyValue()[k];
		
		String[] numbers = key.split(",");
     
		    
		BigInteger e = new BigInteger(numbers[0].trim());
		BigInteger n = new BigInteger(numbers[1].trim());
		
		String M = input;// one line for now 
		    

		    
		//System.out.println("M   = " + M);

		// First he turns M into a number m smaller than n
		BigInteger m = new BigInteger(M.getBytes());// m in bytes
		    
		   
		if (m.compareTo(n) == 1) {
			Error("message too long for the given key ");
			return "";
		}
		    
		byte[] c = m.modPow(e, n).toByteArray(); //byte array
		   
		    
		String cipher = new String(c); // this the cipher text not in bytes
		
		
		return cipher;
		
}
		else {
			Error("please enter you key!");
			return "";
		}
		
		
		
	}
	
	
	
	public static String SHA512_encryption(String input) {
		try {
			// getInstance() method is called with algorithm SHA-1
			MessageDigest md = MessageDigest.getInstance("SHA-512");

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
	private Homophone homophoneCipher = new Homophone(false);

	// Create GUI components panel_1
	JPanel panel_1 = new JPanel();

	JTextField inputtext = new JTextField();
	JLabel results = new JLabel();
	JButton openbtn = new JButton("Open file");
	JButton savebtn = new JButton("Save results into file");
	JPanel keyspanel = new JPanel();
	JLabel brief = new JLabel("Enter Here Brief about your algorithm");

	// Get Keys values from user
	public  String[] getKeyValue() {
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
			{ "Example Algorithm", "SHA-1", "HMAC", "Playfair Cipher", "Homophone","RSA", }, // Algorithm name , String
			{ "Example Brief",
					"<html>SHA-1 takes an input message of any length and produces a fixed-size output of 160-bit, known as hash value. The hash function is designed to be computationally infeasible to reverse, meaning that it is extremely difficult to find two different messages that produce the same hash value, This property makes SHA-1 useful for a variety of applications. For example it can be used to verify the integrity of the data.</html>",
					"<html> HMAC is a security technique that ensures the integrity and authenticity of a message. It uses a combination of a cryptographic hash function and a secret key to generate a hash value.</html> \r\n"
							+ //
							"\n A key is required </html>",
					"<html>A symmetric encryption technique using a 5x5 matrix of letters to encrypt a pair of letters.</html>",
					"<html>" + "The Homophonic Substitution Cipher is a simple encryption algorithm where each plaintext English letter " + "is converted into one of multiple possible cipher-letters specified to obscure the plaintext's frequency." + " (i.e. A frequent letter such as 'e' has four possible mappings: 'z', '7', '2' and '1'," + " while the letter 'z' has only one mapping: 'n' due to its low occurrence in English text.\n\n", 
			      "An acronym  of Data Encryption Standard, this algorithm creates a 56-bit key to encrypt a 64-bit data block by methods such as " +
			      "bitwise XORing, permutation and substitution. This algorithm is implemented as a Feistel architecture so that encryption and " + 
			      "decryption are very similar processes. This algorithm is not implemented in this simple GUI program.",
			      
			      "AES, also known as Advanced Encryption Standard, was published in 1998 following a selection process of alternative " + 
			      "algorithms to replace DES. A key consists of either 128, 192 or 256 bits by which a 128-bit data block can be encrypted and decrypted." + 
			      " In contrast with DES, AES uses a substitution and permutation network. " + 
			      "This algorithm is not implemented in this simple GUI program."+ "</html>",
        "<html> RSA takes a key that consist of two part and apply the algorithm the same algorithm is applied on encrypt and decrypt. \n key format: 7,943 first key will be used for encryption and second key for decyption.<html>"
			}, // Algorithm brief // , // String
			{ 1, 1, 1, 1, 1 , 2} // Maximum keys of an algorithm , Integer
	};

	// Lunch the program
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame2 frame = new MainFrame2();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Initialize the MAin Frame
	public MainFrame2() {
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(110, 110, 600, 960);
		setTitle("Encryption Algorithms GUI");
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
		 final JComboBox ChooseAlgorithm = new JComboBox();
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
					case "Homophone":
						String ciphertext = Homophone.getCiphertext();
						Homophone.decrypt(ciphertext);
						results.setText(Homophone.getPlaintext());

						// Call here you algorithm function
					case "RSA":
						String plain_text2 = RSA_encryption(inputtext.getText(),1);
						
						results.setText(plain_text2);
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
					case "Homophone":
						Homophone.encrypt(inputtext.getText());
						results.setText(Homophone.getCiphertext());
						// Call here your algorithm function

						break;
						
					case "RSA":
						String plain_text2 = RSA_encryption(inputtext.getText(),0);
						
						results.setText(plain_text2);
						break;
					case "Homophone":
						Homophone.encrypt(inputtext.getText());
						results.setText(Homophone.getCiphertext());
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
				String hashed = SHA512_encryption(results.getText());
				results.setText(hashed);
				
			
				
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
	public class Homophone {
		private static StringBuilder plaintext;
		private static StringBuilder ciphertext;
		private static boolean encrypted;
		private static ArrayList<String> capitalizedBeforeEncryption;
		private static ArrayList<Boolean> isDigitBeforeEncryption;
		private static Hashtable<String, ArrayList<String>> table; // the Homophonic table, which the actual encryption
																	// sets on, is allocated to this hash table.

		public Homophone(boolean e) {

			if (plaintext == null || plaintext.capacity() == 0)
				plaintext = new StringBuilder(10000);
			if (ciphertext == null || ciphertext.capacity() == 0)
				ciphertext = new StringBuilder(10000);

			if (capitalizedBeforeEncryption == null)
				capitalizedBeforeEncryption = new ArrayList<String>();
			if (isDigitBeforeEncryption == null)
				isDigitBeforeEncryption = new ArrayList<Boolean>();

			encrypted = e;

			if (table == null || table.isEmpty()) {
				table = new Hashtable<String, ArrayList<String>>(52);

				/*
				 * Because converting between cases is weirdly difficult in Java, we decided to
				 * store
				 * both lower-case and upper-case English letters in this hash table
				 */
				ArrayList<String> LetterMap = new ArrayList<String>();

				// These are the upper-case mappings
				LetterMap.add("D");
				LetterMap.add("9");
				table.put("A", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("X");
				table.put("B", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("S");
				table.put("C", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("F");
				table.put("D", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("Z");
				LetterMap.add("7");
				LetterMap.add("2");
				LetterMap.add("1");
				table.put("E", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("E");
				table.put("F", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("H");
				table.put("G", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("C");
				table.put("H", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("V");
				LetterMap.add("3");
				table.put("I", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("I");
				table.put("J", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("T");
				table.put("K", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("P");
				table.put("L", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("G");
				table.put("M", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("A");
				LetterMap.add("5");
				table.put("N", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("Q");
				LetterMap.add("0");
				table.put("O", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("L");
				table.put("P", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("K");
				table.put("Q", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("J");
				table.put("R", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("R");
				LetterMap.add("4");
				table.put("S", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("U");
				LetterMap.add("6");
				table.put("T", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("O");
				table.put("U", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("W");
				table.put("V", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("M");
				table.put("W", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("Y");
				table.put("X", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("B");
				table.put("Y", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("N");
				table.put("Z", LetterMap);

				// And these are the lower-case mappings.
				LetterMap.add("d");
				LetterMap.add("9");
				table.put("a", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("x");
				table.put("b", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("s");
				table.put("c", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("f");
				table.put("d", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("z");
				LetterMap.add("7");
				LetterMap.add("2");
				LetterMap.add("1");
				table.put("e", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("e");
				table.put("f", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("h");
				table.put("g", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("c");
				table.put("h", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("v");
				LetterMap.add("3");
				table.put("i", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("i");
				table.put("j", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("t");
				table.put("k", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("p");
				table.put("l", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("g");
				table.put("m", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("a");
				LetterMap.add("5");
				table.put("n", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("q");
				LetterMap.add("0");
				table.put("o", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("l");
				table.put("p", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("k");
				table.put("q", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("j");
				table.put("r", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("r");
				LetterMap.add("4");
				table.put("s", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("u");
				LetterMap.add("6");
				table.put("t", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("o");
				table.put("u", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("w");
				table.put("v", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("m");
				table.put("w", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("y");
				table.put("x", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("b");
				table.put("y", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("n");
				table.put("z", LetterMap);

				// These are some punctuation marks and whitespaces.
				LetterMap = new ArrayList<String>();
				LetterMap.add(" ");
				table.put(" ", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("?");
				table.put("?", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("!");
				table.put("!", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add(",");
				table.put(",", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add(".");
				table.put(".", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("\t");
				table.put("\t", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("%");
				table.put("%", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("&");
				table.put("&", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("~");
				table.put("~", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("`");
				table.put("`", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("-");
				table.put("-", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("+");
				table.put("+", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("_");
				table.put("_", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add(")");
				table.put(")", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("(");
				table.put("(", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("@");
				table.put("@", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("'");
				table.put("'", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("\"");
				table.put("\"", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("[");
				table.put("[", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("]");
				table.put("]", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("{");
				table.put("{", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("}");
				table.put("}", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add(";");
				table.put(";", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("<");
				table.put("<", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add(">");
				table.put(">", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("=");
				table.put("=", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add(":");
				table.put(":", LetterMap);

				// And here are the decimal numbers
				LetterMap = new ArrayList<String>();
				LetterMap.add("0");
				table.put("0", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("1");
				table.put("1", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("2");
				table.put("2", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("3");
				table.put("3", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("4");
				table.put("4", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("5");
				table.put("5", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("6");
				table.put("6", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("7");
				table.put("7", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("8");
				table.put("8", LetterMap);

				LetterMap = new ArrayList<String>();
				LetterMap.add("9");
				table.put("9", LetterMap);

			}

		}

		public static void encrypt(String p) {
			if (encrypted)
				return;
			plaintext.delete(0, plaintext.capacity());
			plaintext.append(p);
			capitalizedBeforeEncryption.clear();
			isDigitBeforeEncryption.clear();
			for (int i = 0; i < plaintext.length(); i++) {
				Character c = plaintext.charAt(i);
				if (Character.isUpperCase(c))
					capitalizedBeforeEncryption.add("true");
				else if (Character.isLowerCase(c))
					capitalizedBeforeEncryption.add("false");
				else
					capitalizedBeforeEncryption.add("null");
				if (Character.isDigit(c))
					isDigitBeforeEncryption.add(true);
				else
					isDigitBeforeEncryption.add(false);

			}

			ciphertext.delete(0, ciphertext.capacity());

			for (int i = 0; i < plaintext.length(); i++) {
				int codePoint = plaintext.codePointAt(i);
				String letter = Character.toString(codePoint);
				int possibleMappings = table.get(letter).size();
				Random mappedLetter = new Random(); // we constructed this pseudorandom generator to encrypt each
													// plain-letter into a random cipher-letter
				if (possibleMappings > 1) {
					int chosen = mappedLetter.nextInt(possibleMappings);
					letter = table.get(letter).get(chosen);
				} else
					letter = table.get(letter).get(0);
				ciphertext.append(letter);
			}
			encrypted = true;
		}

		public static void decrypt(String c) {
			if (!encrypted)
				return;

			ciphertext.delete(0, ciphertext.capacity());
			ciphertext.append(c);
			plaintext.delete(0, plaintext.capacity());

			for (int i = 0; i < ciphertext.length(); i++) {
				String cipherLetter = Character.toString(ciphertext.codePointAt(i));
				String wasCapitalized = capitalizedBeforeEncryption.get(i);
				boolean wasDigit = isDigitBeforeEncryption.get(i);
				Enumeration<String> originalLetters = table.keys();
				while (originalLetters.hasMoreElements()) { // this loop represents each plaintext letter
					String plainLetter = originalLetters.nextElement();
					ArrayList<String> mappedLetters = table.get((Object) plainLetter);
					String discoveredLetter = null; // we want to break out of the inner for loop here so that letters
													// with multiple possibilities are not duplicated.
					for (String lc : mappedLetters) // and this represents the ArrayList<String> of the possible
													// ciphertext letters for each plaintext letter
						if (cipherLetter.contentEquals(lc)) {
							if (wasCapitalized.contentEquals("true")) {
								if (wasDigit) {
									plaintext.append(cipherLetter);
									discoveredLetter = cipherLetter;
								} else {
									plaintext.append(plainLetter.toUpperCase());
									discoveredLetter = plainLetter.toUpperCase();
								}
								break;
							}
							else if (wasCapitalized.contentEquals("false")) {
								if (wasDigit) {
									plaintext.append(cipherLetter);
									discoveredLetter = cipherLetter;
								} else {
									plaintext.append(plainLetter.toLowerCase());
									discoveredLetter = plainLetter.toLowerCase();
								}
								break;
							}
							else if (wasCapitalized.contentEquals("null")) {
								if (wasDigit) {
									plaintext.append(cipherLetter);
									discoveredLetter = cipherLetter;
								} else {
									plaintext.append(plainLetter);
									discoveredLetter = plainLetter;
								}
								break;
							}
						}
					if (discoveredLetter != null)
						break;
				}
			}
			capitalizedBeforeEncryption.clear();
			isDigitBeforeEncryption.clear();
			encrypted = false;
		}
		public static String getPlaintext() {
			return plaintext.toString();
		}

		public static String getCiphertext() {
			return ciphertext.toString();
		}

	}
}
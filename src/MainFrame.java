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
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSpinner;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputkey;
	private JLabel lblB;
	private JTextField inputtext;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(110, 110, 600, 960);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		inputkey = new JTextField();
		inputkey.setBounds(38, 389, 197, 35);
		contentPane.add(inputkey);
		inputkey.setColumns(10);

		JLabel lblNewLabel = new JLabel("Key");
		lblNewLabel.setBounds(52, 363, 85, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, ١٨));
		contentPane.add(lblNewLabel);

		JComboBox chooseAthm = new JComboBox();
		chooseAthm.setBounds(39, 103, 512, 48);
		contentPane.add(chooseAthm);

		lblB = new JLabel("brief about the algorithem");
		lblB.setBounds(50, 174, 280, 22);
		lblB.setFont(new Font("Tahoma", Font.BOLD, ١٨));
		contentPane.add(lblB);

		JPanel panel = new JPanel();
		panel.setBounds(39, 200, 512, 69);
		panel.setBackground(new Color(240, 248, 255));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel brief = new JLabel("Enter Here Brief about your algorithm");
		brief.setBounds(10, 11, 502, 47);
		panel.add(brief);
		brief.setVerticalAlignment(SwingConstants.TOP);
		brief.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblB_1 = new JLabel("Choose your Encryption Algorithm");
		lblB_1.setBounds(52, 70, 499, 22);
		lblB_1.setFont(new Font("Tahoma", Font.BOLD, ١٨));
		contentPane.add(lblB_1);

		JButton openbtn = new JButton("Open file");
		openbtn.setBounds(241, 294, 108, 35);
		openbtn.setFont(new Font("Tahoma", Font.PLAIN, ١٣));
		contentPane.add(openbtn);

		JButton addkey = new JButton("+");
		addkey.setBounds(332, 374, 62, 57);
		addkey.setFont(new Font("Tahoma", Font.PLAIN, ١٩));
		contentPane.add(addkey);

		inputtext = new JTextField();
		inputtext.setBounds(38, 518, 513, 35);
		contentPane.add(inputtext);
		inputtext.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Enter Plaintext or Ciphertext");
		lblNewLabel_1.setBounds(52, 484, 302, 22);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, ١٨));
		contentPane.add(lblNewLabel_1);

		JButton DecBtn = new JButton("Decrypt");
		DecBtn.setBounds(298, 612, 134, 57);
		contentPane.add(DecBtn);

		JButton EncBtn = new JButton("Encrypt");
		EncBtn.setBounds(139, 612, 134, 57);
		contentPane.add(EncBtn);

		JButton HmacBtn = new JButton("HMAC");
		HmacBtn.setBounds(426, 680, 108, 40);
		contentPane.add(HmacBtn);

		JButton HashBtn = new JButton("HASH");
		HashBtn.setBounds(50, 680, 108, 40);
		contentPane.add(HashBtn);

		JButton DSBtn = new JButton("Digital Signiture");
		DSBtn.setBounds(215, 680, 134, 40);
		contentPane.add(DSBtn);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(38, 781, 512, 48);
		contentPane.add(panel_1);

		JLabel results = new JLabel("Write the results here");
		results.setFont(new Font("Tahoma", Font.PLAIN, ١٤));
		results.setHorizontalAlignment(SwingConstants.LEFT);
		results.setBounds(10, 0, 502, 47);
		panel_1.add(results);

		JLabel lblResults = new JLabel("Results");
		lblResults.setFont(new Font("Tahoma", Font.BOLD, ١٨));
		lblResults.setBounds(50, 748, 280, 22);
		contentPane.add(lblResults);

		JButton savebtn = new JButton("Save results into file");
		savebtn.setFont(new Font("Tahoma", Font.PLAIN, ١٣));
		savebtn.setBounds(201, 855, 169, 35);
		contentPane.add(savebtn);
	}

	// Adds a new key input field to the keyPanel
	private void addKeyField() {
		if (keyFieldCount < MAX_KEY_FIELDS) {
			JTextField newKeyField = new JTextField();
			newKeyField.setMaximumSize(new Dimension(Integer.MAX_VALUE, newKeyField.getPreferredSize().height));
			newKeyField.setAlignmentX(Component.CENTER_ALIGNMENT);
			keyPanel.add(newKeyField);
			keyPanel.revalidate();
			keyPanel.repaint();
			keyFieldCount++;
		} else {
			showError("Maximum number of key fields reached.");
		}
	}

}
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame implements Action {

	JTextArea textArea;
	JScrollPane scrollPane;
	JLabel fontLabel;
	JSpinner fontSizeSpinner;
	JButton colorButton;
	JComboBox fontBox;

	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem exitItem;

	TextEditor() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Text Editor");
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setSize(500, 500);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Ink Free", Font.BOLD, 25));

		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(450, 450));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		fontLabel = new JLabel("Font: ");

		fontSizeSpinner = new JSpinner();
		fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
		fontSizeSpinner.setValue(25);
		fontSizeSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.BOLD, (int) fontSizeSpinner.getValue()));
			}

		});

		colorButton = new JButton("Color");
		colorButton.addActionListener(this);

		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontBox = new JComboBox<>(fonts);
		fontBox.setSelectedItem("Ink Free");
		fontBox.setAction(this);

		// menuBar Section

		menuBar = new JMenuBar();

		fileMenu = new JMenu("File");

		openItem = new JMenuItem("open");
		saveItem = new JMenuItem("save");
		exitItem = new JMenuItem("exit");

		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);

		fileMenu.setMnemonic(KeyEvent.VK_F);
		openItem.setMnemonic(KeyEvent.VK_O);
		saveItem.setMnemonic(KeyEvent.VK_S);
		exitItem.setMnemonic(KeyEvent.VK_E);

		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);

		menuBar.add(fileMenu);

		// /menuBar Section
		this.setJMenuBar(menuBar);
		this.add(fontLabel);
		this.add(fontSizeSpinner);
		this.add(colorButton);
		this.add(fontBox);
		this.add(scrollPane);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == colorButton) {
			JColorChooser colorChooser = new JColorChooser();
			Color color = colorChooser.showDialog(null, "Choose a color", Color.black);
			textArea.setForeground(color);
		}
		if (e.getSource() == fontBox) {
			textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.BOLD, textArea.getFont().getSize()));
		}
		if (e.getSource() == openItem) {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt"); 
			fileChooser.setFileFilter(filter);
			
			int response = fileChooser.showOpenDialog(null);
			if(response == JFileChooser.APPROVE_OPTION){
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				
				Scanner scanner = null;
				try {
					scanner = new Scanner(file);
					if(file.isFile()) {
						while(scanner.hasNextLine()) {
							String line = scanner.nextLine() + "\n";
							textArea.append(line);
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					scanner.close();
				}
				
			}
			
			
		}
		if (e.getSource() == saveItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			
			int response = fileChooser.showSaveDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file;
				PrintWriter writer = null;
				
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				try {
					writer = new PrintWriter(file);
					writer.println(textArea.getText());
					System.out.println("fayl ochildi");
				} catch (FileNotFoundException e1) {
					System.out.println("fayl xxxx");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					writer.close();
				}
				
				
			}
		}
		if (e.getSource() == exitItem) {
			System.exit(0);
		}

	}

	private void If(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub

	}

}

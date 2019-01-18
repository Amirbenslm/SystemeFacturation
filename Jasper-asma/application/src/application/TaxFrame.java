package application;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class TaxFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfFodec;
	private JTextField tfTVA;
	private JTextField tfTimbre;
	protected static double fodec;
	protected static  double tva;
	protected static double timbre;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaxFrame frame = new TaxFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public TaxFrame() {
		setTitle("Tax");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 438, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFodec = new JLabel("Fodec : %");
		lblFodec.setBounds(39, 28, 75, 29);
		contentPane.add(lblFodec);
		
		JLabel lblTva = new JLabel("TVA  : %");
		lblTva.setBounds(39, 68, 46, 14);
		contentPane.add(lblTva);
		
		tfFodec = new JTextField();
		tfFodec.setBounds(135, 31, 103, 23);
		contentPane.add(tfFodec);
		tfFodec.setColumns(10);
		
		tfTVA = new JTextField();
		tfTVA.setBounds(135, 64, 103, 23);
		contentPane.add(tfTVA);
		tfTVA.setColumns(10);
		
		JLabel lblTimbre = new JLabel("Timbre : DT");
		lblTimbre.setBounds(39, 103, 75, 14);
		contentPane.add(lblTimbre);
		
		tfTimbre = new JTextField();
		tfTimbre.setBounds(135, 99, 103, 23);
		contentPane.add(tfTimbre);
		tfTimbre.setColumns(10);
		/******Lire dapres Fichier*****************************/
		try{
			InputStream ips=new FileInputStream("SystemeFacturation/configuration/Tax.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			fodec=Double.parseDouble(br.readLine());
			tva=Double.parseDouble(br.readLine());
			timbre=Double.parseDouble(br.readLine());
			tfFodec.setText(String.valueOf(fodec));
			tfTVA.setText(String.valueOf(tva));
			tfTimbre.setText(String.valueOf(timbre));
				br.close(); 
		}		
		
		catch (Exception e){
			JOptionPane.showMessageDialog(null,"Probléme dans le fichier Tax \n"+e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBackground(SystemColor.controlHighlight);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fodec=Double.parseDouble(tfFodec.getText());
					tva=Double.parseDouble(tfTVA.getText());
					timbre=Double.parseDouble(tfTimbre.getText());
					String dirName = "C:\\SystemeFacturation\\configuration\\";
							 File dir = new File(dirName);
							 boolean isCreated = dir.mkdirs();
					FileWriter fw = new FileWriter (dirName+"Tax.txt");
					BufferedWriter bw = new BufferedWriter (fw);
					PrintWriter fichierSortie = new PrintWriter (bw); 
						fichierSortie.println(fodec);
						fichierSortie.println(tva);
						fichierSortie.println(timbre);
						fichierSortie.close();
					//System.out.println("Le fichier " + "infos_entreprise.txt" + " a été créé!"); 
				}
			
			catch (NumberFormatException ee) {
				JOptionPane.showMessageDialog(null,"les champs doivent étre numérique !","Erreur",JOptionPane.ERROR_MESSAGE);
			}
				catch (Exception e1){
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null,"Probléme dans le fichier Tax \n"+e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				}		
			}
		});
		btnEnregistrer.setBounds(123, 155, 103, 23);
		contentPane.add(btnEnregistrer);
		
		JButton btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFermer.setBounds(263, 155, 95, 23);
		btnFermer.setBackground(SystemColor.controlHighlight);
		contentPane.add(btnFermer);
		this.setVisible(true);
	}
}

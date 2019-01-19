package application;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.UIManager;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class A {

	private JFrame frmSystmeDeFacturation;
	private JTextField tfClient;
	private JTextField tfDesignation;
	private JTextField tflonguer;
	private JTextField tflargeur;
	private JTextField tfQuantite;
	private JTextField tfprix1metre;
	private JTable table;
	private ArticleModel mytablemodel;
	protected double longueur;
	protected double largeur;
	protected int quantite=1;
	protected double prix1metre;
	protected double totalHorsTaxe;
	double surfaceTotal=0;
	protected  int numarticle=0;
	private JTextField tfHauteur;
	protected double hauteur;
	private JTextField tfIdentifiantFisc;
	private JTextField tfadresse;
	protected double montantTVA;
			int	quaniteTotal=0;
	protected double totalTTc;
	protected double fodec;
	protected double tva;
	protected double timbre;
	protected double montantFodec;
	private JTextField tfMoyenTS;
	private JTextField jtFodec;
	private JTextField jtTVA;
	private JTextField jtTotalNetHT;
	private JTextField jtNetaPayer;
	protected double valuOfJTFodec;
	protected double valueOfTVA;
	protected double valuOfJTNETHT;
	protected double valuOfJTNETaPayer;
	private JTextField jt_NumFact;
	private final Action action = new SwingAction();
	protected boolean etatFactureNouveau=true;
	 int numthread=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A window = new A();
					window.frmSystmeDeFacturation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public A() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSystmeDeFacturation = new JFrame();
		frmSystmeDeFacturation.setIconImage(Toolkit.getDefaultToolkit().getImage("SystemeFacturation/configuration/a.jpg"));
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ");
		Date date = new Date();
		numthread=1;
		frmSystmeDeFacturation.setTitle("Syst\u00E9me de Facturation ");
		frmSystmeDeFacturation.setBounds(100, 10, 1112, 714);
		frmSystmeDeFacturation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystmeDeFacturation.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1106, 21);
		frmSystmeDeFacturation.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Fichier");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmTax = new JMenuItem("Tax");
		mntmTax.setHorizontalAlignment(SwingConstants.LEFT);
		mntmTax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TaxFrame();
			}
		});
		mnNewMenu.add(mntmTax);
		
		JPanel panel = new JPanel();
		panel.setBounds(16, 41, 187, 304);
		frmSystmeDeFacturation.getContentPane().add(panel);
		panel.setLayout(new GridLayout(7,1,0,0));
		
		JLabel lblDsignationArticle = new JLabel("D\u00E9signation Article :");
		panel.add(lblDsignationArticle);
		
		JLabel lblLongueur = new JLabel("Longueur :");
		panel.add(lblLongueur);
		
		JLabel lblLargeur = new JLabel("Largeur :");
		panel.add(lblLargeur);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E9 :");
		panel.add(lblQuantit);
		
		JLabel lblPrixMtre = new JLabel("Prix 1 m\u00E9tre/ Pi\u00E8ce :");
		panel.add(lblPrixMtre);
		
		JLabel lblNewLabel = new JLabel("Formule :");
		panel.add(lblNewLabel);
		
		JLabel lblHauteur = new JLabel("Hauteur :");
		panel.add(lblHauteur);
		
		tfDesignation = new JTextField();
		tfDesignation.setBounds(207, 47, 304, 33);
		frmSystmeDeFacturation.getContentPane().add(tfDesignation);
		tfDesignation.setColumns(10);
		
		tflonguer = new JTextField();
		tflonguer.setBounds(207, 91, 304, 33);
		frmSystmeDeFacturation.getContentPane().add(tflonguer);
		tflonguer.setColumns(10);
		
		tflargeur = new JTextField();
		tflargeur.setBounds(207, 135, 304, 33);
		frmSystmeDeFacturation.getContentPane().add(tflargeur);
		tflargeur.setColumns(10);
		
		tfQuantite = new JTextField();
		tfQuantite.setText("1");
		tfQuantite.setBounds(207, 179, 304, 33);
		frmSystmeDeFacturation.getContentPane().add(tfQuantite);
		tfQuantite.setColumns(10);
		
		tfprix1metre = new JTextField();
		tfprix1metre.setBounds(207, 223, 304, 33);
		frmSystmeDeFacturation.getContentPane().add(tfprix1metre);
		tfprix1metre.setColumns(10);
		
		JComboBox cb_formule = new JComboBox();
		cb_formule.setBackground(Color.LIGHT_GRAY);
		cb_formule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cb_formule.getSelectedItem().equals("Métre cube"))
				{tfHauteur.setEditable(true);}
				else
				{tfHauteur.setEditable(false);}
			}
		});
		cb_formule.setBounds(207, 267, 304, 33);
		frmSystmeDeFacturation.getContentPane().add(cb_formule);
		cb_formule.setModel(new DefaultComboBoxModel(new String[] {"Lin\u00E9aire", "M\u00E9tre carr\u00E9", "M\u00E9tre cube", "Pi\u00E8ce"}));
		mytablemodel=new ArticleModel();
		table = new JTable();
		table.setModel(mytablemodel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 415, 1049, 208);
		frmSystmeDeFacturation.getContentPane().add(scrollPane);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBackground(SystemColor.controlHighlight);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mytablemodel.getRowCount()<11)
				{
					if(!tfDesignation.getText().equals("")&&!tflonguer.getText().equals("")&&!tflargeur.getText().equals("")&&!tfQuantite.getText().equals("")&&!tfprix1metre.getText().equals(""))
					{
						try{
						 longueur= Double.parseDouble(tflonguer.getText());
						 largeur= Double.parseDouble(tflargeur.getText());
						 quantite= Integer.parseInt(tfQuantite.getText());
						 prix1metre= Double.parseDouble(tfprix1metre.getText());
						 quaniteTotal=quantite+quaniteTotal;
						 InputStream ips=new FileInputStream("SystemeFacturation/configuration/Tax.txt"); 
							InputStreamReader ipsr=new InputStreamReader(ips);
							BufferedReader br=new BufferedReader(ipsr);
							fodec=Double.parseDouble(br.readLine());
							tva=Double.parseDouble(br.readLine());
							timbre=Double.parseDouble(br.readLine());
						 
						 if(cb_formule.getSelectedItem().toString().equals("Linéaire"))
							{totalHorsTaxe=calculLineaire(longueur,largeur,quantite,prix1metre);
							
							}
						 else if(cb_formule.getSelectedItem().toString().equals("Métre carré"))
						 {
							 totalHorsTaxe=calculMetreCarre(longueur,largeur,quantite,prix1metre);
							
						 }
						 else if(cb_formule.getSelectedItem().toString().equals("Métre cube"))
						 {
							 hauteur= Double.parseDouble(tfHauteur.getText());
							 totalHorsTaxe=calculMetreCube(longueur,largeur,hauteur,quantite,prix1metre);
							
						 }
						 else if(cb_formule.getSelectedItem().toString().equals("Pièce"))
						 {
							 
							 totalHorsTaxe=calculPièce(quantite,prix1metre);
							
						 }
						 numarticle=mytablemodel.getRowCount()+1;
						 
						 montantFodec=totalHorsTaxe*fodec/100;
						montantTVA=((totalHorsTaxe+montantFodec)*tva)/100;
						 totalTTc=totalHorsTaxe+montantFodec+montantTVA;
						 totalTTc=(double)((int)(totalTTc*1000))/1000;
						 //calcule du valeur de fodec et TVA sur 3 chiifre aprés la virgule
						 valuOfJTFodec=Double.parseDouble(jtFodec.getText())+montantFodec;
						 jtFodec.setText(String.valueOf((double)((int)(valuOfJTFodec*1000))/1000));
						 valueOfTVA=Double.parseDouble(jtTVA.getText())+montantTVA;
						 jtTVA.setText(String.valueOf((double)((int)(valueOfTVA*1000))/1000));
						 //calcule du valeur de Total NET HT et Total net a payer sur 3 chiifre aprés la virgule
						 valuOfJTNETHT=Double.parseDouble(jtTotalNetHT.getText())+montantFodec+totalHorsTaxe;
						 jtTotalNetHT.setText(String.valueOf((double)((int)(valuOfJTNETHT*1000))/1000));
						 valuOfJTNETaPayer=Double.parseDouble(jtNetaPayer.getText())+totalTTc;
						 jtNetaPayer.setText(String.valueOf((double)((int)(valuOfJTNETaPayer*1000))/1000));
						 Article a=new Article(numarticle,tfDesignation.getText(), longueur, largeur,quantite, prix1metre,cb_formule.getSelectedItem().toString(),totalHorsTaxe,totalTTc);
						mytablemodel.AjouterLigne(a);
					}
					catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(null,"les champs doivent être numèrique Sauf Désignation!","Erreur",JOptionPane.ERROR_MESSAGE);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
					}
					else
					{JOptionPane.showMessageDialog(null,"Il faut remplir les champs ! et les champs doiventt étre numérique Sauf Désignation!","Erreur",JOptionPane.ERROR_MESSAGE);}
					
				}else
					{JOptionPane.showMessageDialog(null,"La Facture est saturée vous avez déja 11 article ","Erreur",JOptionPane.ERROR_MESSAGE);}
			
		}});
		btnAjouter.setBounds(74, 358, 95, 27);
		frmSystmeDeFacturation.getContentPane().add(btnAjouter);
		
		tfHauteur = new JTextField();
		tfHauteur.setEditable(false);
		tfHauteur.setBounds(208, 311, 303, 31);
		frmSystmeDeFacturation.getContentPane().add(tfHauteur);
		tfHauteur.setColumns(10);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.isRowSelected(table.getSelectedRow()))
				{int i=JOptionPane.showConfirmDialog(null, "La suppression est irréversible. Êtes-vous sûr de vouloir continuer?",
		                "Veuillez confirmer votre choix",
		                JOptionPane.YES_NO_OPTION);
				 	if(i==0){Article article=mytablemodel.getArticle((int)table.getModel().getValueAt(table.getSelectedRow(),0));
				 		totalHorsTaxe=article.getTotalHorsTaxe();
				 		montantFodec=totalHorsTaxe*fodec/100;
						montantTVA=((totalHorsTaxe+montantFodec)*tva)/100;
						 //calcule du valeur de fodec et TVA sur 3 chiifre aprés la virgule
						 valuOfJTFodec=Double.parseDouble(jtFodec.getText())-montantFodec;
						 jtFodec.setText(String.valueOf((double)((int)(valuOfJTFodec*1000))/1000));
						 valueOfTVA=Double.parseDouble(jtTVA.getText())-montantTVA;
						 jtTVA.setText(String.valueOf((double)((int)(valueOfTVA*1000))/1000));
						 //calcule du valeur de Total NET HT et Total net a payer sur 3 chiifre aprés la virgule
						 valuOfJTNETHT=Double.parseDouble(jtTotalNetHT.getText())-montantFodec-totalHorsTaxe;
						 jtTotalNetHT.setText(String.valueOf((double)((int)(valuOfJTNETHT*1000))/1000));
						 valuOfJTNETaPayer=Double.parseDouble(jtNetaPayer.getText())-article.getTotalTTC();
						 jtNetaPayer.setText(String.valueOf((double)((int)(valuOfJTNETaPayer*1000))/1000));
						 quaniteTotal=quaniteTotal-article.getQuantite();
					mytablemodel.supprimerLigne((int)table.getModel().getValueAt(table.getSelectedRow(),0));
				}}
				else
				{JOptionPane.showMessageDialog(null,"Il faut selectionner une ligne!","Erreur",JOptionPane.ERROR_MESSAGE);}
			}
		});
		btnSupprimer.setBounds(232, 358, 95, 27);
		btnSupprimer.setBackground(SystemColor.controlHighlight);
		frmSystmeDeFacturation.getContentPane().add(btnSupprimer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(550, 25, 509, 275);
		frmSystmeDeFacturation.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNomDuClient = new JLabel("Nom Du Client :");
		lblNomDuClient.setBounds(10, 19, 95, 33);
		panel_1.add(lblNomDuClient);
		
		tfClient = new JTextField();
		tfClient.setBounds(137, 19, 324, 32);
		panel_1.add(tfClient);
		tfClient.setColumns(10);
		
		JLabel lblIdentifiantFiscale = new JLabel("Identifiant Fiscale:");
		lblIdentifiantFiscale.setBounds(10, 61, 115, 33);
		panel_1.add(lblIdentifiantFiscale);
		
		tfIdentifiantFisc = new JTextField();
		tfIdentifiantFisc.setBounds(137, 61, 324, 32);
		panel_1.add(tfIdentifiantFisc);
		tfIdentifiantFisc.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setBounds(10, 104, 115, 33);
		panel_1.add(lblAdresse);
		
		tfadresse = new JTextField();
		tfadresse.setBounds(137, 104, 324, 32);
		panel_1.add(tfadresse);
		tfadresse.setColumns(10);
		
		JLabel lblDocument = new JLabel("Document :");
		lblDocument.setBounds(10, 147, 95, 33);
		panel_1.add(lblDocument);
		
		JComboBox cb_Document = new JComboBox();
		cb_Document.setBackground(Color.LIGHT_GRAY);
		cb_Document.setModel(new DefaultComboBoxModel(new String[] {"Facture", "Bon De Livraison", "Devis"}));
		cb_Document.setBounds(137, 147, 170, 32);
		panel_1.add(cb_Document);
		
		JLabel lblMoyenDeTransport = new JLabel("Moyen de transport :");
		lblMoyenDeTransport.setBounds(10, 187, 126, 33);
		panel_1.add(lblMoyenDeTransport);
		
		tfMoyenTS = new JTextField();
		tfMoyenTS.setBounds(137, 190, 324, 32);
		panel_1.add(tfMoyenTS);
		tfMoyenTS.setColumns(10);
		
		JRadioButton radioButton = new JRadioButton("");
		radioButton.setAction(action);
		radioButton.setBounds(314, 147, 19, 33);
		panel_1.add(radioButton);
		radioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(radioButton.isSelected())
				{
					jt_NumFact.setEditable(true);
					etatFactureNouveau=false;
				}
				else{jt_NumFact.setEditable(false);
				etatFactureNouveau=true;}				
			}
		});
		
		JLabel lblN = new JLabel("N\u00B0:");
		lblN.setBounds(346, 147, 35, 33);
		panel_1.add(lblN);
		
		jt_NumFact = new JTextField();
		jt_NumFact.setEditable(false);
		jt_NumFact.setBounds(374, 147, 86, 32);
		panel_1.add(jt_NumFact);
		jt_NumFact.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(137, 232, 324, 32);
		panel_1.add(comboBox);
		
		JLabel lblClient = new JLabel("Client :");
		lblClient.setBounds(10, 237, 95, 23);
		panel_1.add(lblClient);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Article", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 25, 514, 379);
		frmSystmeDeFacturation.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(550, 306, 509, 98);
		frmSystmeDeFacturation.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("FODEC :");
		lblNewLabel_1.setBounds(10, 11, 80, 26);
		panel_3.add(lblNewLabel_1);
		
		jtFodec = new JTextField("0");
		jtFodec.setEditable(false);
		jtFodec.setBounds(77, 11, 139, 26);
		panel_3.add(jtFodec);
		jtFodec.setColumns(10);
		
		JLabel lblTva = new JLabel("TVA :");
		lblTva.setBounds(10, 61, 62, 26);
		panel_3.add(lblTva);
		
		jtTVA = new JTextField("0");
		jtTVA.setEditable(false);
		jtTVA.setBounds(75, 61, 141, 26);
		panel_3.add(jtTVA);
		jtTVA.setColumns(10);
		
		JLabel lblTotalNetHt = new JLabel("Total NET HT :");
		lblTotalNetHt.setBounds(236, 11, 80, 26);
		panel_3.add(lblTotalNetHt);
		
		jtTotalNetHT = new JTextField("0");
		jtTotalNetHT.setEditable(false);
		jtTotalNetHT.setBounds(326, 11, 139, 26);
		panel_3.add(jtTotalNetHT);
		jtTotalNetHT.setColumns(10);
		
		JLabel lblNetAPayer = new JLabel("NET a Payer :");
		lblNetAPayer.setBounds(236, 61, 80, 26);
		panel_3.add(lblNetAPayer);
		
		jtNetaPayer = new JTextField("0");
		jtNetaPayer.setEditable(false);
		jtNetaPayer.setBounds(326, 61, 139, 26);
		panel_3.add(jtNetaPayer);
		jtNetaPayer.setColumns(10);
		
		JButton btnFacturer = new JButton("Facturer");
		btnFacturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					FileReader schemaReader;
					try {Long numfact;
						schemaReader = new FileReader("SystemeFacturation/configuration/factures.json");
						JSONParser schemajsonParser = new JSONParser();
						JSONObject jsonObject = (JSONObject) schemajsonParser.parse(schemaReader);
						if(etatFactureNouveau==true){numfact=(Long)jsonObject.get(cb_Document.getSelectedItem().toString());
						jsonObject.put(cb_Document.getSelectedItem().toString(),(Long)jsonObject.get(cb_Document.getSelectedItem().toString())+1);}
						else{numfact=Long.parseLong(jt_NumFact.getText());}
						
						ConvertierMontantEnLettre c = new ConvertierMontantEnLettre();
						 double aux1=(Double.parseDouble(jtNetaPayer.getText()))+0.6;
						 aux1=(double)((int)(aux1*1000))/1000;
				           System.out.println(jtNetaPayer.getText()+"###"+aux1);
				            c.setMontant(String.valueOf(aux1));
				            c.calculer_glob();
				            Map<String, Object> parameters = new HashMap<String, Object>();
				            parameters.put("num_fac",numfact);
				            parameters.put("nom_cl",tfClient.getText());
				            parameters.put("adresse",tfadresse.getText());
				            parameters.put("ident_fiscale_cl",tfIdentifiantFisc.getText());
				            parameters.put("moyen_transport",tfMoyenTS.getText());
				            parameters.put("type_facture",cb_Document.getSelectedItem().toString());
				            parameters.put("total_lettre",c.getMontantLettre().toUpperCase());
				            parameters.put("total_net_ht",Double.parseDouble(jtTotalNetHT.getText()));
				            parameters.put("total_qte",quaniteTotal);
				            parameters.put("total_tva",jtTVA.getText());
				            parameters.put("total_surface",surfaceTotal);
				            parameters.put("total_chiffre",aux1);
				            /********Tax****************/
				            List<TableauTaxe> listeTaxes=new ArrayList<TableauTaxe>();
				            TableauTaxe taxe1=new TableauTaxe();
				            taxe1.setLibele_taxe("FODEC ");
				            taxe1.setBase(Double.parseDouble(jtTotalNetHT.getText()));
				            taxe1.setTaux("1%");
				            taxe1.setMontant(Double.parseDouble(jtFodec.getText()));
				            listeTaxes.add(taxe1);
				            TableauTaxe taxe2=new TableauTaxe();
				            taxe2.setLibele_taxe("TVA ");
				            double aux=taxe1.getBase()+taxe1.getMontant();
				            taxe2.setBase(((double)((int)(aux*1000))/1000));
				            taxe2.setTaux("19%");
				            taxe2.setMontant(Double.parseDouble(jtTVA.getText()));
				            listeTaxes.add(taxe2);
				            
				            new JasperClass(mytablemodel.data,cb_Document.getSelectedItem().toString(),parameters,listeTaxes,etatFactureNouveau);
						FileWriter file = new FileWriter("SystemeFacturation/configuration/factures.json");
				            file.write(jsonObject.toJSONString());
				            file.flush();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
		});
		btnFacturer.setBackground(SystemColor.controlHighlight);
		btnFacturer.setBounds(721, 641, 127, 33);
		frmSystmeDeFacturation.getContentPane().add(btnFacturer);
		
		JButton btnNouveau = new JButton("Nouvelle Facture");
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			tfDesignation.setText("");
			tfHauteur.setText("");
			tflargeur.setText("");
			tflonguer.setText("");
			tfQuantite.setText("1");
			tfprix1metre.setText("");
			tfClient.setText("");
			tfIdentifiantFisc.setText("");
			tfadresse.setText("");
			tfMoyenTS.setText("");
			jtFodec.setText("0");
			jtNetaPayer.setText("0");
			jtTotalNetHT.setText("0");
			jtTVA.setText("0");
			surfaceTotal=0;
			quaniteTotal=0;
			mytablemodel=new ArticleModel();
			table.setModel(mytablemodel);
			}
		});
		btnNouveau.setBackground(SystemColor.controlHighlight);
		btnNouveau.setBounds(898, 641, 138, 33);
		frmSystmeDeFacturation.getContentPane().add(btnNouveau);
		frmSystmeDeFacturation.setResizable(false);
		
	}

	protected double calculPièce(int quantite2, double prix1metre2) {
		// TODO Auto-generated method stub
		return quantite2*prix1metre2;
	}

	protected double calculMetreCube(double longueur2, double largeur2, double hauteur2, int quantite2,
			double prix1metre2) {
		surfaceTotal=surfaceTotal+longueur2*largeur2*hauteur2;
		double nombre=((longueur2*largeur2*hauteur2)*prix1metre2)*quantite2;
		return (double)((int)(nombre*1000))/1000;
	}

	protected double calculMetreCarre(double longueur2, double largeur2, int quantite2, double prix1metre2) {
		surfaceTotal=surfaceTotal+longueur2*largeur2;
		double nombre=((longueur2*largeur2)*prix1metre2)*quantite2;
		return (double)((int)(nombre*1000))/1000;
	}

	protected double calculLineaire(double longueur2, double largeur2, int quantite2, double prix1metre2) {
		surfaceTotal=surfaceTotal+2*longueur2+2*largeur2;
		double nombre=((2*longueur2+2*largeur2)*prix1metre2)*quantite2;
		return (double)((int)(nombre*1000))/1000;
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
}

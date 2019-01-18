package application;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class ArticleModel extends AbstractTableModel{
	int nblig;
	private int id;
	 private String designation;
	 private double longueur;
	 private double largeur;
	 private int quantite;
	 private double prix1metre;
	 private String formule;
	 private double totalHorsTaxe;
	 private double totalTTC;
	Article c ;
	ArrayList <Article> data=new ArrayList<Article>();

	public  ArticleModel (){
		}
		@Override
		public int getRowCount() {
			return nblig;
		}
		@Override
		public int getColumnCount() {
			
				return 8;
			}
		@Override
		public Object getValueAt(int l, int c) {
						
			 Article cl=data.get(l);
			if(c==0)
				{return cl.getId();
			  }
				if(c==1)
				{return cl.getDesignation();	}
			
			if(c==2)
				{return cl.getLongueur();
			}
			if(c==3)
				{return cl.getLargeur();}
			if(c==4)
			{
				return cl.getQuantite();
			}
			if(c==5)
			{
				return cl.getPrix1metre();
			}
			if(c==6)
			{
				return cl.getTotalHorsTaxe();
			}
			if(c==7)
			{
				return cl.getTotalTTC();
			}
			
			return("erreur");
			
		}
		public String getColumnName(int c) {
			if(c==0)
			{return "N°";
		  }
			if(c==1)
			{return "Désignation";	}
		
			if(c==2)
			{return "Longueur";
		}
		if(c==3)
			{return "Largeur";}
		if(c==4)
		{
			return "Quantite";
		}
		if(c==5)
		{
			return "Prix 1 métre";
		}
		if(c==6)
		{
			return "TotalHorsTax";
		}
		if(c==7)
		{
			return "TotalTTC";
		}
		
		return("erreur");
			
		}
		public void supprimerLigne(int l){
			int ligne=RechercheBYID(l);
		data.remove(ligne);
		diminuerIdTable(ligne);
		nblig --;
	    fireTableDataChanged();
			
		}
		public void AjouterLigne(Article f){
			data.add(f);
			nblig ++;
		    fireTableDataChanged();
				
			}
		public void ModifierLigne(Article f)
		{int ligne=RechercheBYID(f.getId());
			data.set(ligne,f);
		fireTableDataChanged();
			
		}
		public int RechercheBYID(int id)
		{int i=0;
		Boolean b=false;
		while(i<data.size()&&(b==false))
		{
		if(data.get(i).getId()==id)
		{
		b=true;
		}	
		i++;
		}
				return i-1;
		}
		public Article getArticle(int selectedRow) {
			int ligne=RechercheBYID(selectedRow);
			return data.get(ligne);
		}
		
		public void diminuerIdTable(int ligne)
		{
			for(int i=ligne;i<data.size();i++)
			{
				data.get(i).setId(data.get(i).getId()-1);
			}
		}
		public void deleteAll()
		{
			data.clear();
			//fireTableDataChanged();
			
		}
}




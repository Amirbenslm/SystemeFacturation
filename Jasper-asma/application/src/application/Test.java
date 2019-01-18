package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		Desktop d = Desktop.getDesktop();
		
			d.open(new File("C:/SystemeFacturation/Facture/05-12-2018 #31.pdf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package backimg;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JP_IMG extends JPanel {
	private static final long serialVersionUID = 1L;
	
    @Override
	public void paintComponent(Graphics g){
       //Creamos Variable para el Tamaño del JFrame
       Dimension tamaño=getSize();
       //Creamos Variable de la Imagen a Poner dentro del Area del JPanel
       ImageIcon imagen=new ImageIcon(new  ImageIcon(getClass().getResource("/formatos/brown2.jpg")).getImage());
       //Pintamos la Imagen deacuerdo al tamaño del JFrame
       g.drawImage(imagen.getImage(),0,0,tamaño.width,tamaño.height,null);
	}
}

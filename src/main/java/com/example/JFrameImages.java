package com.example;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JFrameImages extends JFrame{

    private Image imagenOriginal;
    private JLabel etiqueta;

    public JFrameImages(final Image aux){                                    
        this.imagenOriginal = aux;
        this.etiqueta = new JLabel();
        this.etiqueta.setIcon(new ImageIcon(this.imagenOriginal));
        add(this.etiqueta);
        setSize(this.imagenOriginal.getWidth(null),this.imagenOriginal.getHeight(null));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Image getImagenOriginal(){
        return this.imagenOriginal;
    }
    public void setImage(Image nueva){
        this.imagenOriginal = nueva;
        this.etiqueta.setIcon(new ImageIcon(nueva));
        this.etiqueta.repaint();
    }
}  


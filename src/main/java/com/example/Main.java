package com.example;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;


public class Main {
    public static void main(String[] args) {

        Image imagen = AbrirImagen.openImage();
        JFrameImages frame = new JFrameImages(imagen);
        BufferedImage copy = AbrirImagen.toBufferedImage(imagen);
    
        int ancho = copy.getWidth();
        int alto = copy.getHeight();
        
        double rojo[] = new double[256];
        double verde[] = new double[256];
        double azul[] = new double[256];
        
        int t= 0;

        for (int j = 0; j < ancho; j++) {
            for (int m = 0; m < alto; m++) {
                
                int emp = copy.getRGB(j, m);
                Color aux = new Color(emp);
                int r = aux.getRed() + t;
                int g = aux.getGreen();
                int b = aux.getBlue() - t;

                if (r > 255) {
                    r = 255;
                }
                if(r < 0){
                    r = 0;
                }
                
                if (b > 255) {
                    b = 255;
                }
                if(b < 0){
                    b = 0;
                }
                Color nuevoColor = new Color(r, g, b);
                copy.setRGB(j, m, nuevoColor.getRGB());
            }
        }

        for(int x=0; x < copy.getWidth(null);x++){
            for(int y=0; y<copy.getHeight(null);y++){
                Color col = new Color(copy.getRGB(x, y));
                rojo[col.getRed()]=rojo[col.getRed()]+1;
                verde[col.getGreen()]=verde[col.getGreen()]+1;
                azul[col.getBlue()]=azul[col.getBlue()]+1;
            }
        }

        HistogramPanel.mostrar(rojo, verde, azul);

        Image nueva = AbrirImagen.toImage(copy);
        frame.setImage(nueva); // o el método que uses para refrescar el JFrame
    }
}

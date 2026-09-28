package com.example;

import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AbrirImagen{

    public static Image openImage (){
    
        try {
            // definir los filtros para lectura
            FileNameExtensionFilter filtro =
                    new FileNameExtensionFilter("Imagenes","jpg","jpeg","png","bmp");
            // crear un selector de archivos
            JFileChooser selector = new JFileChooser();
            // agregar el filtro al selector
            selector.addChoosableFileFilter(filtro);
            // especificar que solo se puedan abrir archivos
            selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            //ejecutar el selector de imagenes
            
            int res = selector.showOpenDialog(null);
            
            if (res == 1 ){
                
                return null;
                
            }
            
            File archivo = selector.getSelectedFile();
            
            BufferedImage  bi = ImageIO.read(archivo);
            
            return toImage(bi);
        } catch (IOException ex) {
            //Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Image toImage (BufferedImage bi){
        return bi.getScaledInstance(bi.getWidth(),bi.getHeight(), BufferedImage.TYPE_INT_RGB);
    }
    
    public static BufferedImage toBufferedImage (Image imagen){
         // imagen es un objeto de tipo BufferedImage
        if (imagen instanceof BufferedImage){
          return (BufferedImage)imagen;
        }
        int width = imagen.getWidth(null);
        int height = imagen.getHeight(null);


        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("No se pudieron obtener las dimensiones de la imagen");
        }

        BufferedImage bi = new BufferedImage(
            width,
            height,
            BufferedImage.TYPE_INT_RGB
        );
        Graphics2D nueva = bi.createGraphics();
        System.err.println(width);
        System.err.println(height);

        nueva.drawImage(imagen, 0, 0,null);
        nueva.dispose();
        
        return bi ;
    }
    

}

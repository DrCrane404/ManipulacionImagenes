package com.example;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {

        Image imagen = AbrirImagen.openImage();
        if (imagen == null) {
            return;
        }

        JFrameImages frame = new JFrameImages(imagen);
        BufferedImage copy = AbrirImagen.toBufferedImage(imagen);

        int ancho = copy.getWidth();
        int alto = copy.getHeight();

        // 1. Encontrar los valores mínimos y máximos de R, G y B
        int minR = 255, maxR = 0;
        int minG = 255, maxG = 0;
        int minB = 255, maxB = 0;

        for (int x = 0; x < ancho; x++) {
            for (int y = 0; y < alto; y++) {
                Color color = new Color(copy.getRGB(x, y));
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                if (r < minR) minR = r;
                if (r > maxR) maxR = r;

                if (g < minG) minG = g;
                if (g > maxG) maxG = g;

                if (b < minB) minB = b;
                if (b > maxB) maxB = b;
            }
        }

        // 2. Expandir el histograma píxel por píxel
        for (int x = 0; x < ancho; x++) {
            for (int y = 0; y < alto; y++) {
                Color color = new Color(copy.getRGB(x, y));

                // Fórmula: (valor - min) * 255 / (max - min)
                int newR = (maxR != minR) ? (color.getRed() - minR) * 255 / (maxR - minR) : color.getRed();
                int newG = (maxG != minG) ? (color.getGreen() - minG) * 255 / (maxG - minG) : color.getGreen();
                int newB = (maxB != minB) ? (color.getBlue() - minB) * 255 / (maxB - minB) : color.getBlue();

                Color nuevoColor = new Color(newR, newG, newB);
                copy.setRGB(x, y, nuevoColor.getRGB());
            }
        }

        // 3. Recalcular las frecuencias para mostrar el histograma expandido
        double[] rojo = new double[256];
        double[] verde = new double[256];
        double[] azul = new double[256];

        for (int x = 0; x < ancho; x++) {
            for (int y = 0; y < alto; y++) {
                Color col = new Color(copy.getRGB(x, y));
                rojo[col.getRed()]++;
                verde[col.getGreen()]++;
                azul[col.getBlue()]++;
            }
        }

        // 4. Mostrar el histograma actualizado y refrescar la ventana de la imagen
        HistogramPanel.mostrar(rojo, verde, azul);
        Image nueva = AbrirImagen.toImage(copy);
        frame.setImage(nueva);
    }
}
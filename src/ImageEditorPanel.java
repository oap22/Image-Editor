import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class ImageEditorPanel extends JPanel implements KeyListener {

    Color[][] pixels;

    public ImageEditorPanel() {
        BufferedImage imageIn = null;
        try {
            // the image should be in the main project folder, not in \src or \bin
            imageIn = ImageIO.read(new File("Messi.jpg"));
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        pixels = makeColorArray(imageIn);
        setPreferredSize(new Dimension(pixels[0].length, pixels.length));
        setBackground(Color.BLACK);
        addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        // paints the array pixels onto the screen
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                g.setColor(pixels[row][col]);
                g.fillRect(col, row, 1, 1);
            }
        }
    }

    public void run() {

        repaint();
    }

    public Color[][] SixtysPoster(Color[][] oldArr) {
        int height = pixels.length;
        int width = pixels[0].length;
        Color[][] newArr = new Color[height][width];

        Color burntOrng = new Color(253, 156, 0);
        Color orng = new Color(250, 103, 35);
        Color blue = new Color(15, 103, 121);
        Color tan = new Color(211, 198, 17);
        Color white = new Color(255, 255, 255);
        Color brown = new Color(117, 63, 8);

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {

                int redPix = oldArr[r][c].getRed();
                int greenPix = oldArr[r][c].getGreen();
                int bluePix = oldArr[r][c].getBlue();

                double burntOrngDist = Math.sqrt(Math.pow((redPix - burntOrng.getRed()), 2)
                        + Math.pow((greenPix - burntOrng.getGreen()), 2)
                        + Math.pow((bluePix - burntOrng.getBlue()), 2));

                double blueDist = Math.sqrt(Math.pow((redPix - blue.getRed()), 2)
                        + Math.pow((greenPix - blue.getGreen()), 2)
                        + Math.pow((bluePix - blue.getBlue()), 2));

                double orngDist = Math.sqrt(Math.pow((redPix - orng.getRed()), 2)
                        + Math.pow((greenPix - orng.getGreen()), 2)
                        + Math.pow((bluePix - orng.getBlue()), 2));

                double whiteDist = Math.sqrt(Math.pow((redPix - white.getRed()), 2)
                        + Math.pow((greenPix - white.getGreen()), 2)
                        + Math.pow((bluePix - white.getBlue()), 2));

                double tanDist = Math.sqrt(Math.pow((redPix - tan.getRed()), 2)
                        + Math.pow((greenPix - tan.getGreen()), 2)
                        + Math.pow((bluePix - tan.getBlue()), 2));

                double brownDist = Math.sqrt(Math.pow((redPix - brown.getRed()), 2)
                        + Math.pow((greenPix - brown.getGreen()), 2)
                        + Math.pow((bluePix - brown.getBlue()), 2));

                if (burntOrngDist < blueDist && burntOrngDist < orngDist && burntOrngDist < whiteDist
                        && burntOrngDist < tanDist && burntOrngDist < brownDist) {

                    newArr[r][c] = burntOrng;

                } else if (blueDist < burntOrngDist && blueDist < orngDist && blueDist < whiteDist
                        && blueDist < tanDist && blueDist < brownDist) {
                    newArr[r][c] = blue;
                } else if (orngDist < burntOrngDist && orngDist < blueDist && orngDist < whiteDist
                        && orngDist < tanDist && orngDist < brownDist) {
                    newArr[r][c] = orng;
                } else if (whiteDist < burntOrngDist && whiteDist < blueDist && whiteDist < orngDist
                        && whiteDist < tanDist && whiteDist < brownDist) {
                    newArr[r][c] = white;
                } else if (tanDist < burntOrngDist && tanDist < whiteDist && tanDist < blueDist && tanDist < orngDist
                        && tanDist < brownDist) {
                    newArr[r][c] = tan;
                } else {
                    newArr[r][c] = brown;
                }

            }
        }

        return newArr;
    }

    public Color[][] bluescale(Color[][] oldArr) {
        int height = pixels.length;
        int width = pixels[0].length;
        final int ADD_ON = 10;
        final int MAX = 255;

        Color[][] newArr = new Color[height][width];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {

                Color temp = oldArr[r][c];
                int blueTemp = temp.getBlue();

                if (blueTemp + ADD_ON > MAX) {
                    newArr[r][c] = new Color(temp.getRed(), temp.getGreen(), MAX);
                } else {
                    newArr[r][c] = new Color(temp.getRed(), temp.getGreen(), temp.getBlue() + ADD_ON);
                }

            }
        }
        return newArr;
    }

    public Color[][] posterize(Color[][] oldArr) {
        int height = pixels.length;
        int width = pixels[0].length;

        // for color palette
        Color blue = new Color(36, 209, 212);
        Color red = new Color(209, 50, 79);
        Color purple = new Color(129, 21, 191);
        Color white = new Color(255, 255, 255);

        Color[][] newArr = new Color[height][width];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {

                int redPix = oldArr[r][c].getRed();
                int greenPix = oldArr[r][c].getGreen();
                int bluePix = oldArr[r][c].getBlue();

                double redDist = Math.sqrt(Math.pow((redPix - red.getRed()), 2)
                        + Math.pow((greenPix - red.getGreen()), 2)
                        + Math.pow((bluePix - red.getBlue()), 2));

                double blueDist = Math.sqrt(Math.pow((redPix - blue.getRed()), 2)
                        + Math.pow((greenPix - blue.getGreen()), 2)
                        + Math.pow((bluePix - blue.getBlue()), 2));

                double purpleDist = Math.sqrt(Math.pow((redPix - purple.getRed()), 2)
                        + Math.pow((greenPix - purple.getGreen()), 2)
                        + Math.pow((bluePix - purple.getBlue()), 2));

                double whiteDist = Math.sqrt(Math.pow((redPix - white.getRed()), 2)
                        + Math.pow((greenPix - white.getGreen()), 2)
                        + Math.pow((bluePix - white.getBlue()), 2));

                if (purpleDist < blueDist && purpleDist < redDist && purpleDist < whiteDist) {
                    newArr[r][c] = purple;
                } else if (blueDist < purpleDist && blueDist < redDist && blueDist < whiteDist) {
                    newArr[r][c] = blue;
                } else if (redDist < purpleDist && redDist < blueDist && redDist < whiteDist) {
                    newArr[r][c] = red;
                } else {
                    newArr[r][c] = white;
                }
            }
        }

        return newArr;
    }

    public Color[][] blur(Color[][] oldArr) {

        final int BLUR_NUM = 2;
        int height = pixels.length;
        int width = pixels[0].length;
        Color[][] newArr = new Color[height][width];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {

                int redTot = 0;
                int greenTot = 0;
                int blueTot = 0;
                int divNum = 0;

                for (int j = r - BLUR_NUM; j <= r + BLUR_NUM; j++) {
                    for (int k = c - BLUR_NUM; k <= c + BLUR_NUM; k++) {

                        if ((j >= 0 && j < height) && (k >= 0 && k < width)) {

                            Color col = oldArr[j][k];

                            int red = col.getRed();
                            int green = col.getGreen();
                            int blue = col.getBlue();

                            redTot += red;
                            greenTot += green;
                            blueTot += blue;

                            divNum++;

                        }
                    }
                }
                int blurR = (redTot / divNum);
                int blurB = (blueTot / divNum);
                int blurG = (greenTot / divNum);
                newArr[r][c] = new Color(blurR, blurG, blurB);
            }
        }

        return newArr;
    }

    public Color[][] grayscale(Color[][] oldArr) {
        final int COLOR_NUM = 3;
        int height = pixels.length;
        int width = pixels[0].length;
        Color[][] newArr = new Color[height][width];

        for (int r = 0; r < height; r++) {

            for (int c = 0; c < width; c++) {

                Color temp = oldArr[r][c];
                int gray = (temp.getBlue() + temp.getRed() + temp.getGreen()) / COLOR_NUM;
                newArr[r][c] = new Color(gray, gray, gray);

            }
        }
        return newArr;
    }

    public Color[][] flipHoriz(Color[][] oldArr) {

        int height = pixels.length;
        int width = pixels[0].length;
        Color[][] newArr = new Color[height][width];

        for (int r = 0; r < height; r++) {

            for (int c = 0; c < width; c++) {

                newArr[r][c] = oldArr[r][(width - 1) - c];

            }
        }
        return newArr;
    }

    public Color[][] flipVert(Color[][] oldArr) {

        int height = pixels.length;
        int width = pixels[0].length;
        Color[][] newArr = new Color[height][width];

        for (int r = 0; r < height; r++) {

            for (int c = 0; c < width; c++) {

                newArr[r][c] = oldArr[(height - 1) - r][c];

            }
        }
        return newArr;
    }

    public Color[][] makeColorArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Color[][] result = new Color[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(image.getRGB(col, row), true);
                result[row][col] = c;
            }
        }
        // System.out.println("Loaded image: width: " +width + " height: " + height);
        return result;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getKeyChar() == 'h') {
            System.out.println("FLIP HORIZ");
            pixels = flipHoriz(pixels);
        }

        if (e.getKeyChar() == 'v') {
            System.out.println("FLIP VERT");
            pixels = flipVert(pixels);
        }

        if (e.getKeyChar() == 'b') {
            System.out.println("BLUR");
            pixels = blur(pixels);
        }

        if (e.getKeyChar() == 'g') {
            System.out.println("GRAY");
            pixels = grayscale(pixels);
        }

        if (e.getKeyChar() == ' ') {
            System.exit(0);
        }

        if (e.getKeyChar() == 'p') {
            System.out.println("POSTERIZE");
            pixels = posterize(pixels);
        }

        if (e.getKeyChar() == 'n') {
            System.out.println("BLUESCALE");
            pixels = bluescale(pixels);
        }

        if (e.getKeyChar() == 'm') {
            System.out.println("60s POSTER");
            pixels = SixtysPoster(pixels);
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

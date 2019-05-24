package com.TP;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ResourceUtility {

	public static ImageIcon loadImage(String imagePath) throws IOException {
		return new ImageIcon(loadBufferedImage(imagePath));
	}

	public static BufferedImage loadBufferedImage(String imagePath) throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = loader.getResourceAsStream(imagePath);
		try {
			return ImageIO.read(inputStream);
		}
		catch (IllegalArgumentException e) {
			throw new IOException("The image " + imagePath + " could not be loaded", e);
		}
	}

	public static BufferedImage resize(BufferedImage img, double scale) {
		int newWidth = (int) Math.round(img.getWidth() * scale);
		int newHeight = (int) Math.round(img.getHeight() * scale);
		return resize(img, newWidth, newHeight);
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	public static ImageIcon[][] splitImageIcon(BufferedImage bufferedImage, int rows, int cols) throws IOException {
		BufferedImage[][] images = splitImage(bufferedImage, rows, cols);
		ImageIcon[][] imageIcon = new ImageIcon[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				imageIcon[i][j] = new ImageIcon(images[i][j]);
			}
		}
		return imageIcon;
	}

	public static BufferedImage[][] splitImage(BufferedImage img, int rows, int cols) {
		int w = img.getWidth() / cols;
		int h = img.getHeight() / rows;
		BufferedImage imgs[][] = new BufferedImage[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				imgs[i][j] = new BufferedImage(w, h, img.getType());
				// Tell the graphics to draw only one block of the image
				Graphics2D g = imgs[i][j].createGraphics();
				g.drawImage(img, 0, 0, w, h, w * j, h * i, w * j + w, h * i + h, null);
				g.dispose();
			}
		}
		return imgs;
	}
}

package com.TP;

import java.io.IOException;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Jeu {
	private ImageIcon[][] figures = loadImages();

	public Jeu() throws IOException {

		int index = 1;
		for (int row = 0; row <= 3; row++) {
			for (int col = 0; col <= 2; col++) {
				figures[row][col].setDescription("" + index++);
			}
		}

	}

	public Deque<ImageIcon> creerPioche() {
		LinkedList<ImageIcon> list = new LinkedList<ImageIcon>();
		for (int i = 0; i < 2; i++) {
			for (int row = 0; row <= 3; row++) {
				for (int col = 0; col <= 2; col++) {
					list.push(figures[row][col]);
				}
			}
		}
		Collections.shuffle(list);
		return list;

	}

	public ImageIcon[][] loadImages() throws IOException {
		int rows = 4;
		int cols = 3;
		ImageIcon[][] images = ResourceUtility
				.splitImageIcon(ResourceUtility.loadBufferedImage("images/butterfly2.png"), rows, cols);

		return images;
	}

}
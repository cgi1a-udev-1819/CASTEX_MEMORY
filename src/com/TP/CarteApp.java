package com.TP;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Deque;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public class CarteApp extends FrameForDemoMaker {
	private static final int ROW_COUNT = 4;
	private static final int COLUMN_COUNT = 6;
	private ImageIcon dosCarte = ResourceUtility.loadImage("images/dos.png");
	private EtatMemoire state = new EtatMemoire();
	private Jeu jeu = new Jeu();

	public CarteApp() throws IOException {
		super("Mémoire");
		setDefaultBounds(100, 100, 900, 600);
	}

	@Override
	public void init(JFrame frame) {
		Container cp = frame.getContentPane();
		cp.setLayout(new GridLayout(ROW_COUNT, COLUMN_COUNT));

		Deque<ImageIcon> pioche = jeu.creerPioche();
		while (!pioche.isEmpty()) {
			cp.add(createButton(pioche));
		}
	}

	public JComponent createButton(Deque<ImageIcon> pioche) {
		ImageIcon imgP = pioche.pop();
		JToggleButton button = new JToggleButton(dosCarte);
		button.setSelectedIcon(imgP);
		button.setDisabledIcon(imgP);
		button.setDisabledSelectedIcon(imgP);
		button.putClientProperty("carte", imgP.getDescription());
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state.nouveauBoutonSelectionne(button);
			}
		});
		return button;
	}

	public static void main(String[] args) throws IOException {
		CarteApp example = new CarteApp();
		SwingUtilities.invokeLater(example);
	}
}
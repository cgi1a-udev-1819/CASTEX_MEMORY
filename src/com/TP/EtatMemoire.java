package com.TP;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JToggleButton;

public class EtatMemoire {

	private List<JToggleButton> listeBoutonsSelectionnes = new ArrayList<>();

	private void verifierNombreCartesRetournees() {
		if (listeBoutonsSelectionnes.size() >= 2) {
			for (JToggleButton jToggleButton : listeBoutonsSelectionnes) {
				jToggleButton.setSelected(false);
			}

			listeBoutonsSelectionnes.clear();
		}
	}

	private void verifierCartesIdentiques() {
		if (listeBoutonsSelectionnes.size() == 2) {
			if (listeBoutonsSelectionnes.get(0).getClientProperty("carte")
					.equals(listeBoutonsSelectionnes.get(1).getClientProperty("carte"))) {

				for (JToggleButton jToggleButton : listeBoutonsSelectionnes) {
					jToggleButton.setEnabled(false);
				}

				listeBoutonsSelectionnes.clear();
			}
		}
	}

	public void nouveauBoutonSelectionne(JToggleButton button) {

		if (!listeBoutonsSelectionnes.contains(button)) {

			verifierNombreCartesRetournees();

			listeBoutonsSelectionnes.add(button);

			verifierCartesIdentiques();
		}
	}
}
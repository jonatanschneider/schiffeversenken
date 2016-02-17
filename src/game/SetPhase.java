package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import game.Player;

public class SetPhase extends Buttons {
	private int clickCounter = 0;

	private int[] clickCoordinates = new int[4];

	public SetPhase(Player player) {
		super();
		this.player = player;
	}

	protected void buttons(Container pane) {
		this.al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String source = e.getSource().toString().substring(21);
				int[] coordinates = getCoordinatesOfClick(source);
				getCoordinatesCounter = 0;
				colorButton(coordinates, Color.red, false);

				if (clickCounter < 2) {
					if (clickCounter == 0) {
						clickCoordinates[0] = coordinates[0];
						clickCoordinates[1] = coordinates[1];
						clickCounter++;
					}
					else {
						clickCoordinates[2] = coordinates[0];
						clickCoordinates[3] = coordinates[1];
						clickCounter++;
						setShipByClickCoordinates();
					}
				}
				if(player.allShipsSet()){
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Alle Schiffe erstellt!");
					main.initiateShootPhase(player);
				}
			}
		};
		this.setButtons();
		pane.add(this.panel, BorderLayout.NORTH);
	}

	private void setShipByClickCoordinates() {
		this.player.setShip(this.clickCoordinates);
		this.clickCounter = 0;
		this.player.showShips(this.button);
	}
}
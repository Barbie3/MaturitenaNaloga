package sudoku;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI implements ActionListener {
	JFrame okvir;
	JButton gumb, gumb2;
	JPanel plosca, plosca2;
	JTextField tf1, tf2, tf3;
	JLabel napis, n1, n2, n3, n4, n5;
	public int tezavnost = 0;

	public GUI() {
		this.okvir = new JFrame();// prostor za shranjevanje teksta, gumbov
		this.gumb = new JButton("Lahka");// ustvari gumb
		this.gumb2 = new JButton("Težka");// ustvari gumb
		this.plosca = new JPanel();// ustvari plošèo za dodajanje gumbov
		this.plosca2 = new JPanel();//ustvari plošèo za besedilo
		this.napis = new JLabel(//uvodno besedilo
				"<html>Pozdravljen :)<br/>Ko boš pripravljen izberi težavnost in tukaj se bo prikazala izbrana uganka ;)</html>",
				SwingConstants.CENTER);//<br/> = gre v novo vrstico

		plosca.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50));
		plosca.setLayout(new GridLayout(1, 0));// gumba postavi enega poleg drugega v horizontalno in ne vertikalno (0,1) lego
		plosca.add(gumb, BorderLayout.WEST);// gumb postavi na desno
		plosca.add(gumb2, BorderLayout.EAST);// gumb postavi na levo

		plosca2.add(napis);

		okvir.add(plosca, BorderLayout.NORTH);// doda plošèo z gumboma na vrh
		okvir.add(plosca2, BorderLayout.SOUTH);// doda plošèo z mrežo na dno
		okvir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		okvir.setTitle("Sudoku");//naslov v zgornji vrstici nastavi na "Sudoku"
		okvir.pack();//okno postavi grafiène elemente, v skladu z nastavljenimi velikostmi
		okvir.setLayout(null);//ni upravitelja razporeditve
		okvir.setVisible(true);//okno nastavi vidno
		okvir.setResizable(false);// uporabnik ne more poveèati okna
		okvir.setLocationRelativeTo(null);// okno se postavi na sredino zaslona

		gumb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tezavnost = 1;//ob kliku na gumb (lahka) se tezavnost nastavi na 1
				okvir.dispose();
			}
		});
		gumb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tezavnost = 2;//ob kliku na gumb2 (težka) se težavnost nastavi na 2
				okvir.dispose();
			}
		});
	}
	public static void main(String[] args) {
		GUI gui = new GUI();
		GUImreza mreza = new GUImreza();
		while (true) {//neskonèna zanka
			if (gui.tezavnost == 1) {//èe je izbrana lažja stopnja
				mreza.okvir2.setVisible(true);//odpre se novo okno
				mreza.tezavnost = 40;
				mreza.tab=mreza.Generiraj.zapolni1(mreza.tezavnost);//generira se nakljuèna mreža iz katere se izbriše 40 polj
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						mreza.izbrani[i][j] = mreza.tab[i][j];// enaèi tabeli (zapolne tabelo izbrani)
					}
				}
				gui.tezavnost=0;
			}
			else {
				if (gui.tezavnost == 2) {//èe je izbrana težja stopnja
					mreza.okvir2.setVisible(true);//odpre se novo okno
					mreza.tezavnost = 50;
					mreza.tab=mreza.Generiraj.zapolni1(mreza.tezavnost);//generira se nakljuèna mreža iz katere se izbriše 50 polj
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							mreza.izbrani[i][j] = mreza.tab[i][j];// enaèi tabeli (zapolne tabelo izbrani)
						}
					}
					gui.tezavnost=0;
				}
			}
			gui.okvir.repaint();// posodablja okno
			mreza.okvir2.repaint();//posodablja okno
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
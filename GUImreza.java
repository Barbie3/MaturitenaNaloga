package sudoku;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class GUImreza implements ActionListener {
	public int tezavnost;
	public int mx = -100;
	public int my = -100;
	JFrame okvir2 = new JFrame();
	generiraj Generiraj = new generiraj();
	//v tabeli bo mreža
	int tab[][] = new int[9][9];
	//dodatna tabela, v kateri bo vidno katerio polje je izbrano ob kliku
	public int izbrani[][] = new int[9][9];
	public boolean oznacen = false;
	public int vpis = 0;
	public int k = -1;
	public int z = -1;

	GUImreza() {
		okvir2.setTitle("Sudoku");//naslov okna je Sudoku
		okvir2.setSize(465, 590);//velikost okna
		okvir2.setVisible(false);//okno ni vidno
		okvir2.setResizable(false);//uporabnik ne more poveèati okna
		okvir2.setLocationRelativeTo(null);//okno se postavi na sredino zaslona
		Board board = new Board();//doda objekt razreda Board
		okvir2.setContentPane(board);
		Move move = new Move();//doda objekt razreda Move
		okvir2.addMouseMotionListener(move);
		okvir2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Click klik = new Click();//doda nov objekt razreda Click
		okvir2.addMouseListener(klik);
	}

	public class Board extends JPanel {

		public void paintComponent(Graphics g) {
			if (zmaga() == true) {
				g.setColor(Color.green);//èe je rešitev igralca enaka originl-u se ozadje obarva zeleno
			} else {
				g.setColor(Color.GRAY);//ostali èas igre je ozadje sivo
			}
			g.fillRect(0, 0, 465, 590);//doloèi velikost 

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					g.setColor(Color.WHITE);//prazna polja so obarvana belo

					if ((mx >= 5 + (i * 50) && mx < 5 + (i * 50) + 50 - (2 * 5) && my >= 5 + (j * 50) + 26
							&& my < 5 + (j * 50) + 50 + 26 - (2 * 5))) {
						g.setColor(Color.LIGHT_GRAY);//polja že vnaprej podanih vrednosti so svetlo sive barve
					}

					if (izbrani[i][j] > 19) {
						g.setColor(Color.PINK);//ko kliknemo na izbrano polje se obarva rozasto
					}
					if (izbrani[i][j] > 0 && izbrani[i][j] < 10) {
						g.setColor(Color.LIGHT_GRAY);//ko se miška "sprehodi" preko polja, vendar nanj ne klikne se le-to obarva svetlo sivo
					}

					g.fillRect(5 + (i * 50), 5 + (j * 50), 50 - (2 * 5), 50 - (2 * 5));
					// 5 je razmak med kvadratki,i in j koordinati, 50 velikost kvadratka (5 px je
					// za èrto) (kordinata1,koordinata2,velikost,velikost)
					g.setColor(Color.BLACK);//barva številk
					g.setFont(new Font("Tahoma", Font.BOLD, 20));//nastavljanje pisave in velikosti številk
					if (tab[i][j] == 0) {
					} else {
						g.drawString(Integer.toString(tab[i][j]), i * 50 + 14 + 5, j * 50 + 27 + 5);
					}
				}
			}
			for (int i = 0; i < 9; i++) {
				g.setColor(Color.BLACK);//kvadrati s številkami pod mrežo => èrno ozadje
				if ((mx >= 5 + (i * 50) && mx < 5 + (i * 50) + 50 - 5) && my >= 496 && my < 546) {
					g.setColor(Color.DARK_GRAY); //ko se miška "sprehodi" èez, se kvadrati obarvajo temno sivo
				}

				g.fillRect(5 + (i * 50), 20 + 9 * 50, 50 - (2 * 5), 50 - (2 * 5));
				g.setColor(Color.WHITE);//številke so napisane z belo barvo
				g.drawString(Integer.toString(i + 1), i * 50 + 14 + 5, 9 * 50 + 27 + 20);
			}
			// nariše èrte
			for (int i = 1; i < 3; i++) {
				for (int j = 1; j < 3; j++) {
					g.setColor(Color.BLACK);
					g.fillRect((i * 150) - 5, 5, 10, 440);
					g.fillRect(5, (j * 150) - 5, 440, 10);
				}
			}
		}
	}

	public class Move implements MouseMotionListener {

		@Override
		public void mouseMoved(MouseEvent e) {
			mx = e.getX();//vraèa x
			my = e.getY();//vraèa y
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public class Click implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			mx = e.getX();//vraèa x
			my = e.getY();//vraèa y
			//èe je x in y v mreži in èe je vrednost znotraj polja 0 ter nobeno polje še ni oznaèeno
			if (znotrajX() != -1 && znotrajY() != -1 && izbrani[znotrajX()][znotrajY()] == 0 && oznacen == false) {
				oznacen = true;//polje znotraj mreže se oznaèi, zato je true
				izbrani[znotrajX()][znotrajY()] = 20;//izbrano polje dobi vrednost 20;
				z = znotrajX();//z je koordinata mesta kjer je oznaèeno polje (roza)
				k = znotrajY();//k je koordinata mesta kjer je oznaèeno polje (roza)
			} else {
				//èe je x in y v mreži in èe je vrednost znotraj polja 0 ter nobeno polje še ni oznaèeno
				if (znotrajX() != -1 && znotrajY() != -1 && izbrani[znotrajX()][znotrajY()] == 20 && oznacen == true) {
					oznacen = false;//polje znotraj mreže se "odznaèi", zato je false
					izbrani[znotrajX()][znotrajY()] = 0;//izbrano polje ima zopet vrednost 0;
					z = -1;
					k = -1;
				}
			}
			if (vStevilkah() != -1&&k>-1&&z>-1) {
				tab[z][k] = vStevilkah() + 1;//v tabelo se zapiše število, ki se je kliknilo v spodnji skupini kvadratov 
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}
	}
	
	// vraèa vrednost/koordinate x v mreži
	public int znotrajX() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if ((mx >= 5 + (i * 50) && mx < 5 + (i * 50) + 50 - (2 * 5) && my >= 5 + (j * 50) + 26
						&& my < 5 + (j * 50) + 50 + 26 - (2 * 5))) {

					return i;
				}
			}
		}
		return -1;
	}

	// vraèa vredsnost/koordinate y v mreži
	public int znotrajY() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if ((mx >= 5 + (i * 50) && mx < 5 + (i * 50) + 50 - (2 * 5) && my >= 5 + (j * 50) + 26
						&& my < 5 + (j * 50) + 50 + 26 - (2 * 5))) {
					return j;
				}
			}
		}
		return -1;
	}

	// vrne v kateri številki se nahaja (spodnja vrstica "gumbi")
	public int vStevilkah() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if ((mx >= 5 + (i * 50) && mx < 5 + (i * 50) + 50 - 5) && my >= 496 && my < 546) {
					return i;
				}
			}
		}
		return -1;
	}

	//preverja ali je tabela, ki jo rešuje igralec enaka original-u (rešitvi)
	public boolean zmaga() {
		boolean bo=true;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(tab[i][j]!=Generiraj.original[i][j]) {
					bo=false;break;
				}
			}
		}
		return bo;
	}
}

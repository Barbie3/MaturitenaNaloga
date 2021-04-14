package sudoku;

import java.util.*;

public class generiraj {

	public int tab[][] = new int[9][9];
	public int original[][] = new int[9][9];

	public generiraj() {
	}

	// poišèe nakljuèno število na intervalu [1,9]
	public static int nakljucno(int n) {
		int a = (int) (Math.random() * n + 1); // ker Math.random poda na intervalu [0,1) in ker ne more biti 0 je
												// random*9+1
		return a;
	}

	// metoda s katero dobimo posamezno vrednost vrstice latiskega kvadrata
	public static int get_vrstica(LinkedList<Integer> sez, int n) {
		int a = sez.get(n);
		return a;
	}

	// izraèun latinskega kvadrata po modelu 3
	public static int[][] latinski_kvadrat() {
		int tab[][] = new int[3][3];
		LinkedList<Integer> sez1 = new LinkedList<Integer>();
		for (int i = 1; i < 4; i++) {// polnjenje seznama
			sez1.add(i);
		}
		LinkedList<Integer> sez2 = new LinkedList<Integer>();
		sez2 = sez1;
		Collections.shuffle(sez2);// nakljuèno premeša sezanm
		for (int i = 0; i < 3; i++) {// v enem krogu zanke = ena enaka številka v vsako vrstico
			tab[0][i] = get_vrstica(sez1, i);// prva vrstica
			if (i + 1 < 3) {// druga vrstica
				tab[1][i] = get_vrstica(sez1, i + 1);
			} else {
				tab[1][i] = get_vrstica(sez1, i - 2);
			}
			if (i + 2 < 3) {// tretja vrstica
				tab[2][i] = get_vrstica(sez1, i + 2);
			} else {
				tab[2][i] = get_vrstica(sez1, i - 1);
			}
		}
		return tab;
	}

	// menjava vrstice v tabeli splošno
	// v podani tabeli zamenja zahtevani dve vrstici (tabela mora biti kvadratna)
	public static int[][] zamenja(int[][] tab, int iz, int na) {
		int premik[] = new int[tab.length];// podvojena vrstica, brez nje se podatek povozi
		for (int i = 0; i < premik.length; i++) {
			premik[i] = tab[na][i];
		}
		for (int j = 0; j < premik.length; j++) {
			tab[na][j] = tab[iz][j];
		}
		for (int j = 0; j < premik.length; j++) {
			tab[iz][j] = premik[j];
		}
		return tab;
	}

	// nakljuèno zapolni mrežo (1,2,3) v velikosti 9x9 (logika je zapisana v zvezku)
	public int[][] zapolni1(int stevilo) {
		// int tab[][] = new int[9][9]; // velikost mreže
		int tab1[][] = new int[3][3];
		tab1 = latinski_kvadrat();
		for (int i = 0; i < 3; i++) {// vnašanje prve 1./9 mreže
			for (int j = 0; j < 3; j++) {
				tab[i][j] = tab1[i][j];
			}
		}
		tab1 = zamenja(tab1, 1, 2);
		for (int i = 0; i < 3; i++) {// vnašanje prve 2./9 mreže
			for (int j = 3; j < 6; j++) {
				tab[i][j] = tab1[i][(j - 3)];
			}
		}
		tab1 = zamenja(tab1, 0, 1);
		for (int i = 0; i < 3; i++) {// vnašanje prve 3./9 mreže
			for (int j = 6; j < 9; j++) {
				tab[i][j] = tab1[i][(j - 6)];
			}
		}
		tab1 = zamenja(tab1, 1, 2);
		for (int i = 3; i < 6; i++) {// vnašanje prve 4./9 mreže
			for (int j = 0; j < 3; j++) {
				tab[i][j] = tab1[(i - 3)][j];
			}
		}
		tab1 = zamenja(tab1, 0, 1);
		for (int i = 3; i < 6; i++) {// vnašanje prve 5./9 mreže
			for (int j = 3; j < 6; j++) {
				tab[i][j] = tab1[(i - 3)][(j - 3)];
			}
		}
		tab1 = zamenja(tab1, 2, 1);
		for (int i = 3; i < 6; i++) {// vnašanje prve 6./9 mreže
			for (int j = 6; j < 9; j++) {
				tab[i][j] = tab1[(i - 3)][(j - 6)];
			}
		}
		tab1 = latinski_kvadrat();
		for (int i = 6; i < 9; i++) {// vnašanje prve 7./9 mreže
			for (int j = 0; j < 3; j++) {
				tab[i][j] = tab1[(i - 6)][j];
			}
		}
		tab1 = zamenja(tab1, 2, 1);
		for (int i = 6; i < 9; i++) {// vnašanje prve 8./9 mreže
			for (int j = 3; j < 6; j++) {
				tab[i][j] = tab1[(i - 6)][(j - 3)];
			}
		}
		tab1 = zamenja(tab1, 0, 1);
		for (int i = 6; i < 9; i++) {// vnašanje prve 9./9 mreže
			for (int j = 6; j < 9; j++) {
				tab[i][j] = tab1[(i - 6)][(j - 6)];
			}
		}

		tab1 = latinski_kvadrat();

		// zamenja številke 123 za številke 123456789 po zgledu nakljuènega latinskega
		// kvadrata
		int stevec1 = 0;
		int stevec2 = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				switch (tab1[i][j]) {
				case 1:
					for (int k = 0 + (stevec2 * 3); k < 3 + (stevec2 * 3); k++) {// pomnožiš s tri zato da dobiš enako
																					// vrednost kot pri zgornjem
																					// vstavljanju (glej zvezek)
						for (int z = 0 + (stevec1 * 3); z < 3 + (stevec1 * 3); z++) {
							switch (tab[k][z]) {
							case 1:
								tab[k][z] = 4;
								break;
							case 2:
								tab[k][z] = 5;
								break;
							case 3:
								tab[k][z] = 6;
								break;
							}
						}
					}
					break;
				case 2:
					for (int k = 0 + (stevec2 * 3); k < 3 + (stevec2 * 3); k++) {
						for (int z = 0 + (stevec1 * 3); z < 3 + (stevec1 * 3); z++) {
							switch (tab[k][z]) {
							case 1:
								tab[k][z] = 7;
								break;
							case 2:
								tab[k][z] = 8;
								break;
							case 3:
								tab[k][z] = 9;
								break;
							}
						}
					}
					break;
				}
				stevec1++;// premika se po vrstici
			}
			if (stevec1 > 2) {// premika se po stolpcu
				stevec2++;
				stevec1 = stevec1 - 3;
			}
		}

		// zamenjava vrstic, da so posamezna veèja polja (3x3) med seboj razlièna
		tab = zamenja(tab, 1, 3);
		tab = zamenja(tab, 2, 6);
		tab = zamenja(tab, 5, 7);

		// shranjevanje originalne mreže
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				original[i][j] = tab[i][j];
			}
		}

		// izbiši nakljuèna polja iz generirane nakljuène mreže
		int i = 0;
		int j = 0;
		while (stevilo > 0) {// zanka se vrti tako dolgo, dokler ni izbrisano zastavljeno število polj (npr. 40)
			if (nakljucno(9) > 3) {
				tab[i][j] = 0;
				stevilo--;
			}
			i = i + 1;
			if (i > 8) {
				i = i - 9;
				j = j + 5;
				if (j > 8) {
					j = j - 8;
				}
			}
		}
		return tab; // vrne nakljuèno generirano mrežo
	}
}

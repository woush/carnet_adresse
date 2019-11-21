package carnet_adresse;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Classe de travail. Contient un ArrayList de {@link Contact}.
 * 
 * @author Julien
 * @version 1.0
 */
public class Model extends Observable {
	/**
	 * Contient les {@link Contact}
	 */
	private ArrayList<Contact> _colec = new ArrayList<Contact>();
	private static final String nbC = "a";
	private static final String rf = "z";
	private static final String aide = "e";
	private static final String save = "r";
	private static final String savefail = "t";
	private static final String rech = "y";
	private static final String rechfail = "u";
	private static final String rechnull = "i";
	private static final String rechnop = "o";
	private static final String supp = "p";
	private static final String suppfail = "q";
	private static final String maj = "s";
	private static final String multimaj = "l";
	private static final String saveDoublon = "g";
	private static int sap = -5;

	private class Deuxint {
		private int _a;
		private int _b;

		private Deuxint(int a, int b) {
			_a = a;
			_b = b;
		}

		private int get_a() {
			return _a;
		}

		private int get_b() {
			return _b;
		}
	}

	/**
	 * Stop le programme.
	 */
	public void quitter() {
		System.exit(0);
	}

	/**
	 * Notifie un rafraichissement de la vue.
	 */
	public void refresh() {
		setChanged();
		notifyObservers(rf);
		sap = -5;
	}

	/**
	 * Est susceptible de vous aider... Dessine également des sapins.
	 */
	public void aide() {
		setChanged();
		notifyObservers(aide);
		if (sap == 0 || sap == 4 || sap == 8)
			System.out.println("    *");
		if (sap == 1 || sap == 5 || sap == 9)
			System.out.println("   ***");
		if (sap == 2 || sap == 6 || sap == 10)
			System.out.println("  *****");
		if (sap == 3 || sap == 7 || sap == 11)
			System.out.println(" *******");
		if (sap > 11 && sap < 15)
			System.out.println("   |||");
		sap++;
	}

	/**
	 * @param c
	 * Sauvegarde un Contact c si il n'est pas un doublon.
	 */
	public void save(Contact c) {
		int cpt = 0;

		if (_colec.size() < 1) {
			_colec.add(c);
			setChanged();
			notifyObservers(save);
		} else {
			for (int i = 0; i < _colec.size(); i++) {
				if (c.equals(_colec.get(i))) {
					cpt++;
				}
			}
			if (cpt > 0) {
				setChanged();
				notifyObservers(saveDoublon);
			} else {
				_colec.add(c);
				setChanged();
				notifyObservers(save);
			}
		}
	}

	/**
	 * Notifie la vue de l'echect de la sauvegarde.
	 */
	public void saveFail() {
		setChanged();
		notifyObservers(savefail);
	}

	/**
	 * @param s
	 * Recherche un contact s et le supprime.
	 */
	public void suppr(Contact s) {
		int cpt = 0;

		rechercheFail(s);
		for (int i = 0; i < _colec.size(); i++) {
			if (s.equals(_colec.get(i))) {
				_colec.remove(_colec.get(i));
				cpt++;
			}
		}
		if (cpt > 0) {
			setChanged();
			notifyObservers(supp);
		} else {
			setChanged();
			notifyObservers(suppfail);
		}
	}

	/**
	 * @param r
	 * Met a jour un contact r, si il existe et si il n'est pas similaire a un autre contact.
	 */
	public void maj(Contact r) {
		rechercheFail(r);
		if (rechercheFail(r) == false) {
			int cpt = 0;
			int majC = 0;
			Deuxint di = majLoop(r);

			cpt = di.get_a();
			majC = di.get_b();
			if (cpt < 1) {
				setChanged();
				notifyObservers(multimaj);
			} else if (cpt == 1) {
				majDone(r, majC);
			} else if (cpt > 1) {
				setChanged();
				notifyObservers(multimaj);
			}
		}
	}

	private Deuxint majLoop(Contact r) {
		int cpt = 0;
		int majC = 0;

		for (int i = 0; i < _colec.size(); i++) {
			if (_colec.get(i) == null)
				cpt--;
			else if (r.equalsTwo(_colec.get(i))) {
				cpt++;
				if (cpt == 1)
					majC = i;
			} else if (r.equals(_colec.get(i)))
				cpt++;

		}
		return new Deuxint(cpt, majC);
	}

	private void majDone(Contact r, int majC) {
		if (_colec.get(majC).equals(r)) {
			setChanged();
			notifyObservers(saveDoublon);
		} else {
			_colec.get(majC).set_nom(r.get_nom());
			_colec.get(majC).set_prenom(r.get_prenom());
			_colec.get(majC).set_tel(r.get_tel());
			_colec.get(majC).set_mail(r.get_mail());
			setChanged();
			notifyObservers(maj);
		}
	}

	/**
	 * @param r
	 * Recherche un Contact r et l'affiche si il comprend des similitudes
	 */
	public void recherche(Contact r) {
		int cpt = 0;

		if (rechercheFail(r) == false) {
			cpt = rechercheLoop(r);
			if (cpt == 0) {
				setChanged();
				notifyObservers(rechnop);
			}
		}
	}

	private int rechercheLoop(Contact r) {
		int cpt = 0;

		for (int i = 0; i < _colec.size(); i++) {
			if (_colec.get(i) == null)
				cpt--;
			else if (_colec.get(i).get_prenom().contains(r.get_prenom())
					&& _colec.get(i).get_nom().contains(r.get_nom()) && _colec.get(i).get_tel().contains(r.get_tel())
					&& _colec.get(i).get_mail().contains(r.get_mail())) {
				System.out.println("Contact trouvé : " + _colec.get(i).toString());
				cpt++;
				setChanged();
				notifyObservers(nbC);
			}
		}
		setChanged();
		notifyObservers(rech);
		return cpt;
	}

	private boolean rechercheFail(Contact r) {
		if (_colec.isEmpty()) {
			setChanged();
			notifyObservers(rechfail);
			return true;
		} else if (r.get_nom().equals("") && r.get_prenom().equals("") && r.get_tel().equals("")
				&& r.get_mail().equals("")) {
			System.out.println("contact dans le répertoire : " + _colec.toString());
			setChanged();
			notifyObservers(rechnull);
			return true;
		} else
			return false;
	}
}
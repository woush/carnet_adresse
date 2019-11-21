package carnet_adresse;

/**
 * Un Contact est représenté par son nom, prénom, téléphone et mail
 * 
 * @author Julien
 * @version 1.0
 */

public class Contact {
	private String _nom;
	private String _prenom;
	private String _tel;
	private String _mail;

	public Contact(String nom, String prenom, String tel, String mail) {
		this._nom = nom;
		this._prenom = prenom;
		this._tel = tel;
		this._mail = mail;
	}

	public String get_nom() {
		return _nom;
	}

	public void set_nom(String nom) {
		this._nom = nom;
	}

	public String get_prenom() {
		return _prenom;
	}

	public void set_prenom(String prenom) {
		this._prenom = prenom;
	}

	public String get_tel() {
		return _tel;
	}

	public void set_tel(String tel) {
		this._tel = tel;
	}

	public String get_mail() {
		return _mail;
	}

	public void set_mail(String mail) {
		this._mail = mail;
	}

	public String toString() {
		return (_nom + " " + _prenom + " " + _tel + " " + _mail);
	}

	public String toCode() {
		return (_nom + _prenom + _tel + _mail);
	}

	public String toCode_nom() {
		return (_prenom + _tel + _mail);
	}

	public String toCode_tel() {
		return (_nom + _prenom + _mail);
	}

	public String toCode_prenom() {
		return (_nom + _tel + _mail);
	}

	public String toCode_mail() {
		return (_nom + _prenom + _tel);
	}

	public String toCode_prenom_nom() {
		return (_tel + _mail);
	}

	public String toCode_prenom_nom_tel() {
		return (_mail);
	}

	public String toCode_prenom_nom_mail() {
		return (_tel);
	}

	public String toCode_prenom_tel() {
		return (_nom + _mail);
	}

	public String toCode_prenom_mail() {
		return (_nom + _tel);
	}

	public String toCode_nom_tel() {
		return (_prenom + _mail);
	}

	public String toCode_nom_tel_mail() {
		return (_prenom);
	}

	public String toCode_nom_mail() {
		return (_prenom + _tel);
	}

	public String toCode_tel_mail() {
		return (_nom + _prenom);
	}

	public boolean equalsTwo(Contact r) {
		boolean b = false;

		if (r.toCode().contains(_prenom) == false) {
			if (r.toCode_prenom().contains(_nom) && r.toCode_prenom().contains(_tel)
					&& r.toCode_prenom().contains(_mail))
				b = true;
		} else if (r.toCode().contains(_prenom) == false && r.toCode().contains(_nom) == false) {
			if (r.toCode_prenom_nom().contains(_tel) && r.toCode_prenom_nom().contains(_mail))
				b = true;
		} else if (r.toCode().contains(_prenom) == false && r.toCode().contains(_nom) == false
				&& r.toCode().contains(_tel) == false) {
			if (r.get_mail().contains(_mail))
				b = true;
		} else if (r.toCode().contains(_prenom) == false && r.toCode().contains(_nom) == false
				&& r.toCode().contains(_mail) == false) {
			if (r.get_tel().contains(_tel))
				b = true;
		} else if (r.toCode().contains(_prenom) == false && r.toCode().contains(_tel) == false) {
			if (r.toCode_prenom_tel().contains(_nom) && r.toCode_prenom_tel().contains(_mail))
				b = true;
		} else if (r.toCode().contains(_prenom) == false && r.toCode().contains(_mail) == false) {
			if (r.toCode_prenom_mail().contains(_nom) && r.toCode_prenom_mail().contains(_tel))
				b = true;
		} else if (r.toCode().contains(_nom) == false && r.toCode().contains(_tel) == false) {
			if (r.toCode_nom_tel().contains(_prenom) && r.toCode_nom_tel().contains(_mail))
				b = true;
		} else if (r.toCode().contains(_nom) == false && r.toCode().contains(_mail) == false
				&& r.toCode().contains(_tel) == false) {
			if (r.get_prenom().contains(_prenom))
				b = true;
		} else if (r.toCode().contains(_nom) == false && r.toCode().contains(_mail) == false) {
			if (r.toCode_nom_mail().contains(_prenom) && r.toCode_nom_mail().contains(_tel))
				b = true;
		} else if (r.toCode().contains(_tel) == false && r.toCode().contains(_mail) == false) {
			if (r.toCode_tel_mail().contains(_nom) && r.toCode_tel_mail().contains(_prenom))
				b = true;
		} else if (r.toCode().contains(_nom) == false) {
			if (r.toCode_nom().contains(_prenom) && r.toCode_nom().contains(_tel) && r.toCode_nom().contains(_mail))
				b = true;
		} else if (r.toCode().contains(_tel) == false) {
			if (r.toCode_tel().contains(_prenom) && r.toCode_tel().contains(_nom) && r.toCode_tel().contains(_mail))
				b = true;
		} else if (r.toCode().contains(_mail) == false) {
			if (r.toCode_mail().contains(_prenom) && r.toCode_mail().contains(_nom) && r.toCode_mail().contains(_tel))
				b = true;
		} else {
			b = false;
		}
		return b;
	}

	public boolean equals(Contact c) {
		if (this._prenom.equals(c.get_prenom()) && this._nom.equals(c.get_nom()) && this._tel.equals(c.get_tel())
				&& this._mail.equals(c.get_mail()))
			return true;
		else
			return false;
	}
}

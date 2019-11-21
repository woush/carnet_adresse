package carnet_adresse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe de contrôle des actions entre {@link Vue} et {@link Model}
 * 
 * @author Julien
 * @version 1.0
 */

public class Controller implements ActionListener {
	private Vue _v;
	private Model _m;
	private static final String rf = "z";
	private static final String aide = "e";
	private static final String save = "r";
	private static final String rech = "y";
	private static final String supp = "p";
	private static final String maj = "s";
	private static final String quit = "h";

	public Controller(Vue v, Model m) {
		this._v = v;
		this._m = m;
		_v.get_bQuit().addActionListener(this);
		_v.get_bAide().addActionListener(this);
		_v.get_bMaj().addActionListener(this);
		_v.get_bRecherche().addActionListener(this);
		_v.get_bRefresh().addActionListener(this);
		_v.get_bSave().addActionListener(this);
		_v.get_bSuppr().addActionListener(this);
	}

	public Vue get_v() {
		return _v;
	}

	public Model get_m() {
		return _m;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String e = event.getActionCommand();
		Contact c = new Contact(_v.get_zNom(), _v.get_zPrenom(), _v.get_zTel(), _v.get_zMail());

		switch (e) {
		case save:
			if (_v.get_zNom().equals("") && _v.get_zPrenom().equals("") && _v.get_zTel().equals("")
					&& _v.get_zMail().equals(""))
				_m.saveFail();
			else if (_v.get_zNom().equals("") || _v.get_zPrenom().equals(""))
				_m.saveFail();
			else
				_m.save(c);
			break;
		case supp:
			_m.suppr(c);
			break;
		case maj:
			_m.maj(c);
			break;
		case rech:
			_m.recherche(c);
			break;
		case aide:
			_m.aide();
			break;
		case rf:
			_m.refresh();
			break;
		case quit:
			_m.quitter();
		}
	}
}

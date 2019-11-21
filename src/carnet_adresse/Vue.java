package carnet_adresse;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Classe de gestion de l'interface utilisateur
 * @author Julien
 * @version 1.0
 */
public class Vue implements Observer {
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
	private static final String quit = "h";
	private static final String multimaj = "l";
	private static final String saveDoublon = "g";
	//
	private StringBuffer _nbC = new StringBuffer();
	private JFrame _frame = new JFrame("Carnet d'adresses");
	private Container _conteneur;
	private GridLayout _grid = new GridLayout(3, 1, 10, 15);
	private GridLayout _gridBoutton = new GridLayout(3, 3, 10, 10);
	private GridLayout _gridText = new GridLayout(4, 1, 10, 15);
	private JLabel _lbNom = new JLabel("Nom");
	private JLabel _lbPrenom = new JLabel("Prenom");
	private JLabel _lbTel = new JLabel("Telephone");
	private JLabel _lbMail = new JLabel("Email");
	private JLabel _lbG = new JLabel("<<", SwingConstants.CENTER);
	private JLabel _lbD = new JLabel(">>", SwingConstants.CENTER);
	private JLabel _lbFin = new JLabel("Bonjour, pour de l'aide cliquez sur Aide.", SwingConstants.CENTER);
	private JTextField _zNom = new JTextField(50);
	private JTextField _zPrenom = new JTextField(50);
	private JTextField _zTel = new JTextField(50);
	private JTextField _zMail = new JTextField(50);
	private JButton _bSave = new JButton("Sauvegarder");
	private JButton _bSuppr = new JButton("Supprimer");
	private JButton _bMaj = new JButton("Mise à jour");
	private JButton _bRecherche = new JButton("Rechercher");
	private JButton _bAide = new JButton("Aide");
	private JButton _bRefresh = new JButton("Rafraichir");
	private JButton _bQuit = new JButton("Quitter");
	private JPanel _panTop = new JPanel(new GridLayout(1, 2));
	private JPanel _panLabel = new JPanel(_gridText);
	private JPanel _panTxt = new JPanel(_gridText);
	private JPanel _panB = new JPanel(_gridBoutton);

	public Vue() {

		_conteneur = _frame.getContentPane();
		_conteneur.setLayout(_grid);
		_conteneur.add(_panTop);
		_panTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		_panTop.add(_panLabel);
		_panLabel.add(_lbNom);
		_panLabel.add(_lbPrenom);
		_panLabel.add(_lbTel);
		_panLabel.add(_lbMail);
		_panTop.add(_panTxt);
		_panTxt.add(_zNom);
		_panTxt.add(_zPrenom);
		_panTxt.add(_zTel);
		_panTxt.add(_zMail);
		_conteneur.add(_panB);
		_panB.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		_panB.add(_bSave);
		_bSave.setActionCommand(save);
		_panB.add(_bSuppr);
		_bSuppr.setActionCommand(supp);
		_panB.add(_bMaj);
		_bMaj.setActionCommand(maj);
		_panB.add(_lbG);
		_panB.add(_bRecherche);
		_bRecherche.setActionCommand(rech);
		_panB.add(_lbD);
		_panB.add(_bAide);
		_bAide.setActionCommand(aide);
		_panB.add(_bRefresh);
		_bRefresh.setActionCommand(rf);
		_panB.add(_bQuit);
		_bQuit.setActionCommand(quit);
		_conteneur.add(_lbFin);
		_frame.setSize(550, 650);
		_frame.setVisible(true);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setLocationRelativeTo(null);
	}

	public JButton get_bSave() {
		return _bSave;
	}

	public JButton get_bSuppr() {
		return _bSuppr;
	}

	public JButton get_bMaj() {
		return _bMaj;
	}

	public JButton get_bRecherche() {
		return _bRecherche;
	}

	public JButton get_bAide() {
		return _bAide;
	}

	public JButton get_bRefresh() {
		return _bRefresh;
	}

	public JButton get_bQuit() {
		return _bQuit;
	}

	public String get_zNom() {
		return _zNom.getText();
	}

	public void set_zNom(JTextField zNom) {
		this._zNom = zNom;
	}

	public String get_zPrenom() {
		return _zPrenom.getText();
	}

	public void set_zPrenom(JTextField zPrenom) {
		this._zPrenom = zPrenom;
	}

	public String get_zTel() {
		return _zTel.getText();
	}

	public void set_zTel(JTextField zTel) {
		this._zTel = zTel;
	}

	public String get_zMail() {
		return _zMail.getText();
	}

	public void set_zMail(JTextField zMail) {
		this._zMail = zMail;
	}

	public void set_lbFin(JLabel lbFin) {
		this._lbFin = lbFin;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		String notif = arg.toString();

		if (notif.equals(nbC))
			_nbC.append('x');
		switch (notif) {
		case saveDoublon:
			_lbFin.setText("Le contact " + get_zNom() + " " + get_zPrenom() + " existe déjà.");
			break;
		case multimaj:
			_lbFin.setText("Quel \"" + get_zNom() + " " + get_zPrenom()
					+ "\" je dois modifier? Précisez peut être d'autre champs...");
			break;
		case rf:
			refreshAll();
			break;
		case aide:
			_lbFin.setText("Vraiment ?");
			break;
		case save:
			_lbFin.setText("Nouveau contact ajouté " + get_zNom() + " " + get_zPrenom());
			break;
		case savefail:
			_lbFin.setText("Contact non valide, entrez au moins un nom et un prenom.");
			break;
		case rech:
			_lbFin.setText(_nbC.length() + " contact trouvé");
			this._nbC = new StringBuffer();
			break;
		case rechfail:
			_lbFin.setText("Commencez par créer un contact.");
			break;
		case rechnull:
			_lbFin.setText("Affichage du répertoire.");
			break;
		case rechnop:
			_lbFin.setText("Pas de contact correspondant avec votre recherche, sauvegardez le.");
			break;
		case supp:
			_lbFin.setText("contact(s) supprimé");
			break;
		case suppfail:
			_lbFin.setText("Précisez votre contact");
			break;
		case maj:
			_lbFin.setText(get_zPrenom() + " " + get_zNom() + " mis a jour !");
			break;
		}

	}

	/**
	 * Rafraichi tous les champs de la vue.
	 */
	private void refreshAll() {
		_zNom.setText("");
		_zPrenom.setText("");
		_zTel.setText("");
		_zMail.setText("");
		_lbFin.setText("");
	}
}

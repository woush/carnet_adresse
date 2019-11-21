package main;

import carnet_adresse.Controller;
import carnet_adresse.Model;
import carnet_adresse.Vue;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Vue v = new Vue();
		Model m = new Model();
		Controller c = new Controller(v, m);
		m.addObserver(v);
	}
}
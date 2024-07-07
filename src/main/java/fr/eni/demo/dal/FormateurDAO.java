package fr.eni.demo.dal;

import java.util.List;

import fr.eni.demo.bo.Formateur;

public interface FormateurDAO {
	void create(Formateur formateur);

	Formateur read(String emailFormateur);

	void update(Formateur formateur);

	List<Formateur> findAll();
}

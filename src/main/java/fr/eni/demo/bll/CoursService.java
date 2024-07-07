package fr.eni.demo.bll;
import java.util.List;

import fr.eni.demo.bo.Cours;

public interface CoursService {
	List<Cours> getCours();

	Cours findById(long id);
}

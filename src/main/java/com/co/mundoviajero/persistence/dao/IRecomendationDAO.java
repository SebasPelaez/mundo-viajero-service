package com.co.mundoviajero.persistence.dao;

import java.util.List;
import com.co.mundoviajero.persistence.entity.Recomendation;

public interface IRecomendationDAO {

	List<Recomendation> getAllRecomendations();
	Recomendation getRecomendation(Long id);
	boolean createRecomendation(Recomendation recomendation);
	
}

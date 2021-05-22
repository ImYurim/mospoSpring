package net.developia.mospo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.developia.mospo.models.MovieDAO;
import net.developia.mospo.models.MovieDTO;

@Service
public class MovieServiceImpl implements MovieService {
	

	@Autowired
	@Qualifier(value = "movieDAO")
	private MovieDAO movieDAO; 
	

	@Override
	public MovieDTO getMovie(String title) throws Exception {
		return movieDAO.getMovie(title);
	}
}

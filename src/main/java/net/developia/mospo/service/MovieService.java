package net.developia.mospo.service;

import net.developia.mospo.models.MovieDTO;

public interface MovieService {
	MovieDTO getMovie(String title) throws Exception;
}

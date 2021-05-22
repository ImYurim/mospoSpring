package net.developia.mospo.service;

import java.util.List;

import net.developia.mospo.models.CommentDTO;

public interface CommentService {
	int selectComment(CommentDTO commentDTO) throws Exception;
	void insertComment(CommentDTO commentDTO) throws Exception;
	void editComment(CommentDTO commentDTO) throws Exception;
	void deleteComment(CommentDTO commentDTO) throws Exception;
	List<CommentDTO> getCommentlist(long movieid) throws Exception;
}

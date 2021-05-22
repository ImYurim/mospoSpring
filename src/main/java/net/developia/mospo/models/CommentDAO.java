package net.developia.mospo.models;

import java.sql.SQLException;
import java.util.List;

public interface CommentDAO {
	void insertComment(CommentDTO commentDTO) throws SQLException;
	
	void editComment(CommentDTO commentDTO) throws SQLException;
	
	void deleteComment(CommentDTO commentDTO) throws SQLException;
	
	int selectComment(CommentDTO commentDTO) throws SQLException;

	List<CommentDTO> getCommentlist(long movieid) throws SQLException;
}

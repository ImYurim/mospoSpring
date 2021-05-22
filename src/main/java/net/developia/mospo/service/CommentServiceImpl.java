package net.developia.mospo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.developia.mospo.models.CommentDAO;
import net.developia.mospo.models.CommentDTO;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDAO commentDAO; 
	
	@Override
	public int selectComment(CommentDTO commentDTO) throws Exception {
		System.out.println("selectcomment service");
		return commentDAO.selectComment(commentDTO);
	}
	@Override
	public void insertComment(CommentDTO commentDTO) throws Exception {
		System.out.println("insertcomment service");
		commentDAO.insertComment(commentDTO);
	}
	@Override
	public List<CommentDTO> getCommentlist(long movieid) throws Exception {
		System.out.println("comment serviceimpl");
		List<CommentDTO> comment = commentDAO.getCommentlist(movieid);
		return comment;
	}
	@Override
	public void editComment(CommentDTO commentDTO) throws Exception {
		commentDAO.editComment(commentDTO);
	}
	@Override
	public void deleteComment(CommentDTO commentDTO) throws Exception {
		commentDAO.deleteComment(commentDTO);
		System.out.println("delete service");
		
	}

	
}

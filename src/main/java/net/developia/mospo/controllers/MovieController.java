package net.developia.mospo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.developia.mospo.models.CommentDTO;
import net.developia.mospo.models.MovieDTO;
import net.developia.mospo.models.UserDTO;
import net.developia.mospo.service.CommentService;
import net.developia.mospo.service.MovieService;
import net.developia.mospo.service.UserService;


@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;

	
	@GetMapping(value="/main")
	public ModelAndView main() {
		
		return new ModelAndView("main");
	}
	
	@GetMapping(value="/signIn")
	public ModelAndView signin() {
		
		return new ModelAndView("signin");
	}
	
	@PostMapping(value="/signIn")
	public ModelAndView signinaction(@ModelAttribute UserDTO userDTO ,HttpSession session) {

		try {
			userService.userLogin(userDTO);
			session.setAttribute("userid", userDTO.getId());
			System.out.println("session:"+session.getAttribute("userid"));
			return new ModelAndView("main");
		}catch(Exception e){
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("result");
			mav.addObject("msg","로그인에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
			return mav;
		}
	}
	
	@GetMapping(value="/signUp")
	public ModelAndView signup() {
		
		return new ModelAndView("signup");
	}
	
	@PostMapping(value="/signUp")
	public ModelAndView singupaction(@ModelAttribute UserDTO userDTO ,HttpSession session) {
			
		try {
			userService.userSignup(userDTO);
			return new ModelAndView("redirect:signIn");
		}catch(Exception e){
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("result");
			mav.addObject("msg","회원가입에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
			return mav;
		}
	}
	
	@GetMapping(value="/logOut")
	public ModelAndView logoutaction(HttpSession session) {
		session.invalidate();
		return new ModelAndView("main");
	}
	
	@RequestMapping(value="/searchAction")
	public ModelAndView searchaction(@RequestParam(required=false) String title ,HttpSession session) {
		if((String)session.getAttribute("title")==null) {
			session.setAttribute("title", title);
		}else {
			title = (String) session.getAttribute("title");
		}
		
		try {
			MovieDTO movie = movieService.getMovie(title);
			session.setAttribute("title", movie.getTitle());
			session.setAttribute("movieid", movie.getId());
			System.out.println("movieservice끝");
			
			List<CommentDTO> comment = commentService.getCommentlist(movie.getId());
			System.out.println("commentService");
			ModelAndView mav = new ModelAndView("detail");
			mav.addObject("movie", movie);
			mav.addObject("comment", comment);
			
			return mav;
		}catch(Exception e){
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("main");
			return mav;
		}

	}
	
	@GetMapping(value="/detail")
	public ModelAndView detail() {
		return new ModelAndView("detail");
	}
	
	@PostMapping(value="/CommentInsert")
	public ModelAndView insertComment(@ModelAttribute CommentDTO commentDTO ,HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		long movieid = (Long)session.getAttribute("movieid");
		System.out.println(movieid);

		commentDTO.setMember_id(userid);
		commentDTO.setMovie_id(movieid);
		
		ModelAndView mav = new ModelAndView();
		
		try {
			if(commentService.selectComment(commentDTO)==0) {
				commentService.insertComment(commentDTO);
				
				mav.setViewName("redirect:searchAction");
				
			}else {
				mav.setViewName("result");
				mav.addObject("msg","이미 글이 등록되어있습니다.");
				mav.addObject("url", "javascript:history.back();");
			}



		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("result");
			mav.addObject("msg","글 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
		}
		
		
		return mav;
	
	}
	
	@PostMapping(value="/Commentedit")
	public ModelAndView commentedit(@ModelAttribute CommentDTO commentDTO ,HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		long movieid = (Long)session.getAttribute("movieid");
		System.out.println(movieid);

		commentDTO.setMember_id(userid);
		commentDTO.setMovie_id(movieid);
		
		ModelAndView mav = new ModelAndView();
		
		try {

			commentService.editComment(commentDTO);
			mav.setViewName("redirect:searchAction");



		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("result");
			mav.addObject("msg","글 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
		}
		
		
		return mav;
	
	}
	
	@PostMapping(value="/Commentdelete")
	public ModelAndView commentdelete(@ModelAttribute CommentDTO commentDTO ,HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		long movieid = (Long)session.getAttribute("movieid");
		System.out.println(movieid);

		commentDTO.setMember_id(userid);
		commentDTO.setMovie_id(movieid);
		
		ModelAndView mav = new ModelAndView();
		
		try {

			commentService.deleteComment(commentDTO);
			mav.setViewName("redirect:searchAction");
			System.out.println("delete controller");




		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("result");
			mav.addObject("msg","글 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
		}
		
		
		return mav;
	
	}

}

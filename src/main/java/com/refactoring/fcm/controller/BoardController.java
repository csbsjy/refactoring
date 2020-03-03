package com.refactoring.fcm.controller;

import com.refactoring.fcm.DTO.ArticleDTO;
import com.refactoring.fcm.DTO.CommentDTO;
import com.refactoring.fcm.DTO.user.Account;
import com.refactoring.fcm.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class BoardController {


	private BoardService boardService;

	@Autowired
	public void setBoardService(BoardService boardService){
		this.boardService=boardService;
	}

	@GetMapping("/board.do")
	public String showArticleList(@AuthenticationPrincipal Account user, Model model){
		List<ArticleDTO> articles=boardService.showAllArticles();
		model.addAttribute("articles",articles);
		model.addAttribute("type",user.getType());
		model.addAttribute("board","active");
		return "/board/articleList";
	}

	@GetMapping("/write.do")
	public String showArticle(@AuthenticationPrincipal Account user, Model model){
		model.addAttribute("board","active");
		model.addAttribute("type",user.getType());
		return "/board/write";
	}

	@PostMapping("/write.do")
	public String addArticle(@AuthenticationPrincipal Account user, ArticleDTO article,Model model){
		try{
			article.setWriter(user.getId());
			article.setView(0);
			article.setCreated(new Date());
			boardService.write(article);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("board","active");
		model.addAttribute("type",user.getType());
		return "redirect:/article?no="+article.getIdx();
	}
	@GetMapping("/article")
	public String showArticle(@AuthenticationPrincipal Account user, String no, HttpServletRequest req, Model model){
		int idx=Integer.parseInt(no);
		if(req.getParameter("delete")!=null){
			model.addAttribute("message","정말 삭제하시겠습니까?");
		}
		ArticleDTO article=new ArticleDTO();
		article=boardService.showArticleByIdx(idx);
		model.addAttribute("type",user.getType());
		model.addAttribute("article",article);
		model.addAttribute("user",user);
		model.addAttribute("board","active");

		List<CommentDTO> comments=boardService.showAllComments(article.getIdx());
		if(comments.size()==0){
			CommentDTO comment=new CommentDTO();
			comment.setContent("아직 작성된 댓글이 없습니다");
			comments.add(comment);
		}
		model.addAttribute("comments",comments);
		return "/board/article";

	}

	@GetMapping("/revise.do")
	public String reviseArticle(@AuthenticationPrincipal Account user, String no, Model model, HttpServletRequest req) {
		int idx = Integer.parseInt(no);
		ArticleDTO article = new ArticleDTO();
		article = boardService.showArticleByIdx(idx);
		model.addAttribute("article", article);
		model.addAttribute("type", user.getType());
		return "/board/revise";
	}

	@PostMapping("/revise.do")
	public String reviseArticleByPost(ArticleDTO article, Model model, String no, HttpServletRequest req) {
		article.setCreated(new Date());
		article.setIdx(Integer.parseInt(no));
		boardService.reviseArticle(article);
		return "redirect:/article?no="+article.getIdx();
	}
	@GetMapping("/delete.do")
	public String deleteArticle(Model model, String no, HttpServletRequest req){
		int idx=Integer.parseInt(no);
		try{
			boardService.deleteArticle(idx);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/board.do";
	}

	//댓글작성
	@PostMapping("/article")
	public String addComment(@AuthenticationPrincipal Account user, CommentDTO comment, String no, HttpServletRequest req){
		comment.setCenter_id(user.getCenter_id());
		comment.setCreated(new Date());
		comment.setIdx(Integer.parseInt(no));
		boardService.addComment(comment);
		return "redirect:/article?no="+no;

	}

}

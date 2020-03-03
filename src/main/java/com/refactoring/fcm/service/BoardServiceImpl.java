package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.ArticleDTO;
import com.refactoring.fcm.DTO.CommentDTO;
import com.refactoring.fcm.dao.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {


	private BoardDAO boardDao;


	@Autowired
	public void setBoardDAO(BoardDAO boardDao){
		this.boardDao= boardDao;
	}

	@Override
	public List<ArticleDTO> showAllArticles() {
		return boardDao.findAllArticles();
	}

	@Override
	public ArticleDTO showArticleByIdx(int idx) {
		return boardDao.findArticleByIndex(idx);
	}

	@Override
	@Transactional
	public Integer write(ArticleDTO article) {
		boardDao.refreshIdx();
		return boardDao.saveArticle(article);
	}

	@Override
	public void reviseArticle(ArticleDTO article) {
		boardDao.reviseArticle(article);
	}

	@Override
	@Transactional
	public void deleteArticle(int idx) {
		boardDao.deleteArticle(idx);
		boardDao.refreshIdx();
		boardDao.deleteComment(idx);
	}

	@Override
	public void addComment(CommentDTO comment) {
		boardDao.saveComment(comment);
	}

	@Override
	public List<CommentDTO> showAllComments(int idx) {
		return boardDao.findAllCommentsByIdx(idx);
	}


}

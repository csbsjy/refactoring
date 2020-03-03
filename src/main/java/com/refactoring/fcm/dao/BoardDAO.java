package com.refactoring.fcm.dao;

import com.refactoring.fcm.DTO.ArticleDTO;
import com.refactoring.fcm.DTO.CommentDTO;
import com.refactoring.fcm.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *  create Article - saveArticle
 *  read Article - findAllArticles, findArticleByIndex
 *  revise Article
 *  delete Article
 *
 *  create Comment
 *  read Comments
 */
@Repository
public class BoardDAO {

	private BoardMapper boardMapper;

	@Autowired
	public void setBoardMapper(BoardMapper boardMapper){
		this.boardMapper=boardMapper;
	}

	public Integer saveArticle(ArticleDTO article){
		return boardMapper.addArticle(article);
	}

	public List<ArticleDTO> findAllArticles(){
		return boardMapper.selectAllArticles();
	}

	public ArticleDTO findArticleByIndex(int idx){
		return boardMapper.selectArticleByIdx(idx);
	}

	public void refreshIdx(){
		boardMapper.indexingBoard();
	}

	public void reviseArticle(ArticleDTO article){
		boardMapper.reviseArticle(article);
	}

	public int deleteArticle(int idx){
		boardMapper.deleteArticle(idx);
		return idx;
	}

	public CommentDTO saveComment(CommentDTO comment){
		boardMapper.addComment(comment);
		return comment;
	}

	public List<CommentDTO> findAllCommentsByIdx(int idx){
		List<CommentDTO> comments=boardMapper.showAllComments(idx);
		return comments;
	}

	public int deleteComment(int idx){
		boardMapper.deleteCommentByIdx(idx);
		return idx;
	}
}

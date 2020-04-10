package com.fcm.refactoring.api;

import com.fcm.refactoring.dto.board.ArticleListResponseDto;
import com.fcm.refactoring.service.board.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

    private final ArticleService articleService;

    //TODO: 페이징처리는 어떻게하는지!
    @GetMapping
    public ResponseEntity<List<ArticleListResponseDto>> showArticleList() {
        return new ResponseEntity<>(articleService.findAllList(), HttpStatus.OK);
    }


}

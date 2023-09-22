package m22t.ansdlsrb.m22tProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import m22t.ansdlsrb.m22tProject.data.dto.PostDto;
import m22t.ansdlsrb.m22tProject.data.repository.PostRepository;
import m22t.ansdlsrb.m22tProject.service.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final PostService postService;
    @GetMapping
    public String board(Model model){
        //postList 만들어서 addAttribute
        List<PostDto> postDtoList = postService.findAllPost();
        model.addAttribute("postDtoList",postDtoList);
        return "/board/board";
    }

    @GetMapping("/new")
    public String newBoardForm(@ModelAttribute PostDto postDto){
        return "board/newPostForm";
    }

    @PostMapping("/new")
    public String newBoard(@Validated @ModelAttribute PostDto postDto, BindingResult result, HttpServletRequest request){
        // 입력폼 오류 확인
        if (result.hasErrors()) {
            return "board/newPostForm";
        }
        //현재 세션에서 로그인된 사용자의 nickname을 postDto에 저장
        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/";
        }
        Object nickname = session.getAttribute("nickname");
        postDto.setNickname((String)nickname);

        // 게시글 저장
        postService.savePost(postDto);

        return "redirect:/board";
    }

    @GetMapping("/{postId}")
    public String boardInfo(@PathVariable Long postId ){
        return "/";
    }


}

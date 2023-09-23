package m22t.ansdlsrb.m22tProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m22t.ansdlsrb.m22tProject.data.dto.PostDto;
import m22t.ansdlsrb.m22tProject.data.repository.PostRepository;
import m22t.ansdlsrb.m22tProject.service.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final PostService postService;
    @GetMapping
    public String board(Model model,HttpServletRequest request){
        //postList 만들어서 addAttribute
        List<PostDto> postDtoList = postService.findAllPost();
        model.addAttribute("postDtoList",postDtoList);

        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/";
        }
        Object sessionNickname = session.getAttribute("nickname");
        model.addAttribute("sessionNickname",(String)sessionNickname);

        return "/board/board";
    }

    @GetMapping("/new")
    public String newPostForm(@ModelAttribute PostDto postDto){
        return "board/newPostForm";
    }

    @PostMapping("/new")
    public String newPost(@Validated @ModelAttribute PostDto postDto, BindingResult result, HttpServletRequest request){
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
    public String boardInfo(@PathVariable Long postId, Model model, HttpServletRequest request ){

        //현재 세션에서 로그인된 사용자의 nickname을 postDto에 저장
        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/";
        }
        Object nickname = session.getAttribute("nickname");

        PostDto postDto = postService.findPostById(postId);

        String author = postDto.getNickname();

        model.addAttribute("postDto",postDto);

        if(isCurrentUserAuthor((String)nickname,author)){
            model.addAttribute("isCurrentUserAuthor",true);
        }
        else{
            model.addAttribute("isCurrentUserAuthor",false);
        }


        return "/board/post";
    }
    @GetMapping("/edit/{postId}")
    public String upadatePostForm(@PathVariable Long postId, Model model ){
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("postDto",postDto);

        return "/board/editPostForm";
    }

    @PostMapping("/edit/{postId}")
    public String upadatePost(@PathVariable Long postId, Model model, @Validated @ModelAttribute PostDto postDto, BindingResult result ){
        // 입력폼 오류 확인
        if (result.hasErrors()) {
            return "board/newPostForm";
        }
        postDto.setPostId(postId);
        postService.updatePost(postDto);

        return "redirect:/board";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId ){
        postService.deletePost(postId);
        return "redirect:/board";
    }

    public boolean isCurrentUserAuthor(String loginUser, String author) {
        return loginUser.equals(author);
    }

}

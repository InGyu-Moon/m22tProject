package m22t.ansdlsrb.m22tProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "testHome";
        }
        // memberId, nickname session에서 꺼내기
        Object memberId = session.getAttribute("memberId");
        Object nickname = session.getAttribute("nickname");

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("memberId",memberId);
        model.addAttribute("helloNickname",nickname + " 님 안녕하세요");
        return "testHome";

    }
}

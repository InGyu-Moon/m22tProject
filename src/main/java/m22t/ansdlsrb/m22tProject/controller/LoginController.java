package m22t.ansdlsrb.m22tProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import m22t.ansdlsrb.m22tProject.data.dto.LoginInputDto;
import m22t.ansdlsrb.m22tProject.data.dto.MemberDto;
import m22t.ansdlsrb.m22tProject.data.entity.MemberEntity;
import m22t.ansdlsrb.m22tProject.service.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginInputDto loginInputDto){
        return "login/loginFormV2";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginInputDto loginInputDto, BindingResult result,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request){

        if(result.hasErrors()){
            return "login/loginFormV2";
        }

        //로그인 확인
        MemberDto loginMember = loginService.login(loginInputDto.getMemberEmail(), loginInputDto.getPassword());

        //로그인 실패
        if(loginMember == null){
            // 전체 폼, 객체 수준의 오류 -> reject 사용
            // 특정 필드 수준의 오류 -> rejectValue
            //result.rejectValue("", "loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            result.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login/loginFormV2";
        }

        //로그인 성공 로직
        // default : true -> 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        // false -> 세션이 없으면 null 반환
        HttpSession session = request.getSession(true);

        // session에 memberId, nickname 추가
        session.setAttribute("memberId",loginMember.getMemberId());
        session.setAttribute("nickname",loginMember.getNickname());

        return "redirect:" + redirectURL;

    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

}

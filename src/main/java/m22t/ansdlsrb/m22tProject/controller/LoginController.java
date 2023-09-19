package m22t.ansdlsrb.m22tProject.controller;

import lombok.RequiredArgsConstructor;
import m22t.ansdlsrb.m22tProject.data.dto.LoginInputDto;
import m22t.ansdlsrb.m22tProject.data.dto.MemberDto;
import m22t.ansdlsrb.m22tProject.data.entity.MemberEntity;
import m22t.ansdlsrb.m22tProject.service.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginInputDto loginInputDto){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginInputDto loginInputDto, BindingResult result ){

        if(result.hasErrors()){
            return "login/loginForm";
        }

        MemberDto loginMember = loginService.login(loginInputDto.getMemberEmail(), loginInputDto.getPassword());

        //로그인 실패
        if(loginMember == null){
            // 전체 폼, 객체 수준의 오류 -> reject 사용
            // 특정 필드 수준의 오류 -> rejectValue
            //result.rejectValue("", "loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            result.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공


        return "redirect:/";

    }

}

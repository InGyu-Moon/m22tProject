package m22t.ansdlsrb.m22tProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m22t.ansdlsrb.m22tProject.data.dto.MemberInputDto;
import m22t.ansdlsrb.m22tProject.service.user.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/new")
    public String addUser(@ModelAttribute MemberInputDto memberInputDto){
        return "members/newMemberForm";
    }

    @PostMapping("/new")
    public String save(@Validated @ModelAttribute MemberInputDto memberInputDto, BindingResult result){

        // MemberInputDto의 @NotEmpty 체크
        if (result.hasErrors()) {
            return "members/newMemberForm";
        }
        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (!memberInputDto.getPassword().equals(memberInputDto.getConfirmPassword())) {
            result.rejectValue("password", "error.userInputDto", "비밀번호가 일치하지 않습니다.");
            return "members/newMemberForm";
        }
        // email 중복 체크
        if (!memberService.isEmailUnique(memberInputDto.getMemberEmail())) {
            result.rejectValue("userEmail", "error.userInputDto", "중복된 이메일입니다.");
            return "members/newMemberForm";
        }
        // nickname 중복 체크
        if (!memberService.isNicknameUnique(memberInputDto.getNickname())) {
            result.rejectValue("nickname", "error.userInputDto", "중복된 닉네임입니다.");
            return "members/newMemberForm";
        }

        // 성공 로직 (회원가입 성공)
        memberService.saveUser(memberInputDto);
        return "redirect:/";
    }

}

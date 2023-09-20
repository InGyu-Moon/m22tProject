package m22t.ansdlsrb.m22tProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m22t.ansdlsrb.m22tProject.data.dto.MemberDto;
import m22t.ansdlsrb.m22tProject.data.dto.MemberInputDto;
import m22t.ansdlsrb.m22tProject.service.member.MemberService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    // 회원가입 form -> newMemberForm ->newMemberFormV3
    @GetMapping("/new")
    public String addUserV2(@ModelAttribute MemberInputDto memberInputDto){
        return "members/newMemberFormV3";
    }
    @PostMapping("/new")
    public String save(@Validated @ModelAttribute MemberInputDto memberInputDto, BindingResult result){
        // MemberInputDto의 @NotEmpty 체크
        if (result.hasErrors()) {
            return "members/newMemberFormV3";
        }
        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (!memberInputDto.getPassword().equals(memberInputDto.getConfirmPassword())) {
            result.rejectValue("password", "error.userInputDto", "비밀번호가 일치하지 않습니다.");
            return "members/newMemberFormV3";
        }
        // email 중복 체크
        if (!memberService.isEmailUnique(memberInputDto.getMemberEmail())) {
            log.info("3");
            result.rejectValue("memberEmail", "error.userInputDto", "중복된 이메일입니다.");
            return "members/newMemberFormV3";
        }
        // nickname 중복 체크
        if (!memberService.isNicknameUnique(memberInputDto.getNickname())) {
            result.rejectValue("nickname", "error.userInputDto", "중복된 닉네임입니다.");
            return "members/newMemberFormV3";
        }

        // 성공 로직 (회원가입 성공)
        memberService.saveMember(memberInputDto);
        return "redirect:/";
    }

    @GetMapping("/{memberId}")
    public String getMemberInfo(@PathVariable Long memberId, Model model){
        MemberDto memberByMemberId = memberService.getMemberByMemberId(memberId);
        if(memberByMemberId == null){
            return "redirect:/";
        }
        model.addAttribute("member",memberByMemberId);
        return "members/mypage";
    }

}

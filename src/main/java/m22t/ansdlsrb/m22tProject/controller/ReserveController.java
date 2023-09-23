package m22t.ansdlsrb.m22tProject.controller;

import lombok.RequiredArgsConstructor;
import m22t.ansdlsrb.m22tProject.data.dto.MemberDto;
import m22t.ansdlsrb.m22tProject.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {

    private final MemberService memberService;
    @GetMapping("/{memberId}")
    public String reserve(@PathVariable("memberId") Long memberId,Model model){

        MemberDto memberByMemberId =memberService.getMemberByMemberId(memberId);

        if(memberByMemberId == null){
            return "redirect:/";
        }


        String memberNickname=memberByMemberId.getNickname();
        model.addAttribute("memberNickname",memberNickname);
        return "reserve/reservePage";
    }
}

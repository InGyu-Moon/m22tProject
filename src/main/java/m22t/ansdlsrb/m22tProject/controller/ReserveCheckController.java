package m22t.ansdlsrb.m22tProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import m22t.ansdlsrb.m22tProject.data.dto.MemberDto;
import m22t.ansdlsrb.m22tProject.data.dto.ReserveDto;
import m22t.ansdlsrb.m22tProject.service.member.MemberService;
import m22t.ansdlsrb.m22tProject.service.reserve.ReserveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/reserve-check")
public class ReserveCheckController {

    private final MemberService memberService;

    private final ReserveService reserveService;
    @GetMapping("/{memberId}")
    public String reserve(@PathVariable("memberId") Long memberId, Model model, HttpServletRequest request ){

        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/";
        }
        Object nickname = session.getAttribute("nickname");

        List<ReserveDto> reserveDtoList = reserveService.getReserveByNickname((String) nickname);
        model.addAttribute("reserveDtoList",reserveDtoList);
        return "check/checkPage";
    }
}

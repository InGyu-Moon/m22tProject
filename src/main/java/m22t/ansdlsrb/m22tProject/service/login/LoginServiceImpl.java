package m22t.ansdlsrb.m22tProject.service.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m22t.ansdlsrb.m22tProject.data.dto.MemberDto;
import m22t.ansdlsrb.m22tProject.data.entity.MemberEntity;
import m22t.ansdlsrb.m22tProject.data.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public MemberDto login(String memberEmail, String password) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail)
                .filter(m->bCryptPasswordEncoder.matches(password,m.getPassword()))
                .orElse(null);

        if (memberEntity == null) {
            return null;
        }

        MemberDto memberDto = new MemberDto();
        memberDto.setMemberEmail(memberEntity.getMemberEmail());
        memberDto.setPassword(memberEntity.getPassword());
        memberDto.setNickname(memberEntity.getNickname());

        return memberDto;

    }
}

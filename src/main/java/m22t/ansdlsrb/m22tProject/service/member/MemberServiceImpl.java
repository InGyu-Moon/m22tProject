package m22t.ansdlsrb.m22tProject.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m22t.ansdlsrb.m22tProject.data.dto.MemberInputDto;
import m22t.ansdlsrb.m22tProject.data.entity.MemberEntity;
import m22t.ansdlsrb.m22tProject.data.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveMember(MemberInputDto userInputDto) {

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setMemberEmail(userInputDto.getMemberEmail());
        memberEntity.setPassword(bCryptPasswordEncoder.encode(userInputDto.getPassword()));
        memberEntity.setNickname(userInputDto.getNickname());

        memberRepository.save(memberEntity);
    }

    @Override
    // userEmail 중복 검사
    public boolean isEmailUnique(String memberEmail) {
        return memberRepository.findByMemberEmail(memberEmail)
                .isEmpty();
        //return memberRepository.findByMemberEmail(memberEmail) == null;
    }
    @Override
    // nickname 중복 검사
    public boolean isNicknameUnique(String nickname) {
        return memberRepository.findByNickname(nickname)
                .isEmpty();
        //return memberRepository.findByNickname(nickname) == null;
    }
}

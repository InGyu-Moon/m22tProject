package m22t.ansdlsrb.m22tProject.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m22t.ansdlsrb.m22tProject.data.dto.MemberDto;
import m22t.ansdlsrb.m22tProject.data.dto.MemberInputDto;
import m22t.ansdlsrb.m22tProject.data.entity.MemberEntity;
import m22t.ansdlsrb.m22tProject.data.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public MemberDto getMemberByMemberId(Long memberId) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberId);

        //memberEntityOptional가 null이 아니라면
        if (memberEntityOptional.isPresent()) {
            MemberEntity memberEntity = memberEntityOptional.get();

            // MemberEntity를 MemberDto로 변환
            MemberDto memberDto = new MemberDto();
            memberDto.setMemberId(memberEntity.getMemberId());
            memberDto.setMemberEmail(memberEntity.getMemberEmail());
            memberDto.setNickname(memberEntity.getNickname());
            /**
             * password는 변환안함
             * pw가 없는 dto를 만들어야하나? 아니면 그냥 null로 반환해도되나?
             */
            return memberDto;
        } else {
            // memberId에 해당하는 회원이 없을 경우 처리 방법을 결정하십시오.
            // 예를 들어, 예외를 던지거나 기본값을 반환할 수 있습니다.
            return null; // 또는 다른 처리 방법을 선택
        }

    }
}

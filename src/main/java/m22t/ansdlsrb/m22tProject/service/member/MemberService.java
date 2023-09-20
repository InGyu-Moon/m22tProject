package m22t.ansdlsrb.m22tProject.service.member;

import m22t.ansdlsrb.m22tProject.data.dto.MemberDto;
import m22t.ansdlsrb.m22tProject.data.dto.MemberInputDto;

public interface MemberService {
    public void saveMember(MemberInputDto userInputDto);
    // userEmail 중복 검사
    public boolean isEmailUnique(String userEmail);

    // nickname 중복 검사
    public boolean isNicknameUnique(String nickname);
    public MemberDto getMemberByMemberId(Long memberId);

}

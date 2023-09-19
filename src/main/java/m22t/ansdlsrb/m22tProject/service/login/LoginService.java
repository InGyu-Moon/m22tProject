package m22t.ansdlsrb.m22tProject.service.login;

import m22t.ansdlsrb.m22tProject.data.dto.MemberDto;
import m22t.ansdlsrb.m22tProject.data.entity.MemberEntity;

public interface LoginService {
    public MemberDto login(String loginId, String password);
}

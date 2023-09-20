package m22t.ansdlsrb.m22tProject.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class MemberDto {
    private Long memberId;
    private String memberEmail;
    private String password;
    private String nickname;
}


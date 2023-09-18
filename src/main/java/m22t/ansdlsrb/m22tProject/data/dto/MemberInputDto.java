package m22t.ansdlsrb.m22tProject.data.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInputDto {
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email
    private String memberEmail;
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
    @NotBlank(message = "비밀번호를 다시 한 번 작성해주세요!!!")
    private String confirmPassword; // 비밀번호 확인 필드
    @NotBlank(message = "사용자명은 필수 입력값입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "사용자명은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickname;
}

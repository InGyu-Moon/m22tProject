package m22t.ansdlsrb.m22tProject.data.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInputDto {
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email
    private String memberEmail;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

}

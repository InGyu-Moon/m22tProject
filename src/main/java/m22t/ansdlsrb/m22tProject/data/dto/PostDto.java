package m22t.ansdlsrb.m22tProject.data.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostDto {
    private Long postId;
    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;
    @Min(value = 1, message = "가격은 1 이상이어야 합니다.")
    private int cost;
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;
    private String nickname;
}

package m22t.ansdlsrb.m22tProject.data.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDisplayDto {

    private Long id;
    private int type;

    private String name;

    private String roadAddress;
}

package m22t.ansdlsrb.m22tProject.data.dto;


import lombok.Getter;
import lombok.Setter;
import m22t.ansdlsrb.m22tProject.data.entity.PlaceEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReserveDto { // 예약자로 부터 받아올 데이터

    private String user_name;

    private String user_nickname;

    private String user_phone_number;
    private Long place_id;
    private LocalDateTime start_time; // 시작 시간
    private LocalDateTime end_time; // 끝 시간

    private Integer num_of_people;

    private PlaceEntity placeEntity;



    public PlaceEntity getPlaceEntity() {
        return placeEntity;
    }

    public void setPlaceEntity(PlaceEntity placeEntity) {
        this.placeEntity = placeEntity;
    }
}

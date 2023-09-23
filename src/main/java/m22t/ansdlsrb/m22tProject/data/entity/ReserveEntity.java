package m22t.ansdlsrb.m22tProject.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Entity
@Table(name="Reserve_table")
@Getter
@Setter
public class ReserveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_name; // 예약자 이름
    @Column(name="user_nickname")
    private String nickname;//예약자 닉네임
    private String user_phone_number; // 예약자의 전화번호
    private Long place_id;  // 예약 장소의 고유키 (id)

    private LocalDateTime start_time; // 시작 시간
    private LocalDateTime end_time;// 끝 시간

    private Integer num_of_people;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "placeCode")
    private PlaceEntity placeEntity;

    public PlaceEntity getPlaceEntity() {
        return placeEntity;
    }

    public void setPlaceEntity(PlaceEntity placeEntity) {
        this.placeEntity = placeEntity;
    }

    public ReserveEntity() {
    }

    public ReserveEntity(Long id, String user_name, String user_nickname, String user_phone_number, Long place_id, LocalDateTime start_time, LocalDateTime end_time, Integer num_of_people,PlaceEntity placeEntity) {
        this.id = id;
        this.user_name = user_name;
        this.nickname=user_nickname;
        this.user_phone_number = user_phone_number;
        this.place_id = place_id;
        this.start_time=start_time;
        this.end_time=end_time;
        this.num_of_people=num_of_people;
        this.placeEntity = placeEntity;
    }
}

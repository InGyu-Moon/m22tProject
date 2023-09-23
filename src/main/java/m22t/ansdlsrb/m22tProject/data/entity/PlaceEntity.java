package m22t.ansdlsrb.m22tProject.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "m22t")
@Getter
@Setter
public class PlaceEntity {
    @Id
    private Long id;
    private int type;
    private String location;
    private String name;
    private String address;
    @Column(name="road_address")
    private String roadAddress;
    private String phone;
    private String url;
    private double x;
    private double y;
    @Column(name="imgurl")
    private String imgUrl;

}

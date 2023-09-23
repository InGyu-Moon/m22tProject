package m22t.ansdlsrb.m22tProject.service.reserve;

import m22t.ansdlsrb.m22tProject.data.dto.PlaceDisplayDto;
import m22t.ansdlsrb.m22tProject.data.dto.ReserveDto;
import m22t.ansdlsrb.m22tProject.data.entity.ReserveEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReserveService {

    public boolean is_valid(ReserveDto reserveDto);

    public List<PlaceDisplayDto> getPlaceSelectDtos(); // 장소 리스트를 사이트 유저에게 보여줄 예정이다.


    public List<ReserveEntity> findMatchingUsers(String nickname);

    public List<ReserveDto> getReserveByNickname(String nickname);

}

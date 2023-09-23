package m22t.ansdlsrb.m22tProject.service.reserve;

import m22t.ansdlsrb.m22tProject.data.dto.PlaceDisplayDto;
import m22t.ansdlsrb.m22tProject.data.dto.ReserveDto;
import m22t.ansdlsrb.m22tProject.data.entity.PlaceEntity;
import m22t.ansdlsrb.m22tProject.data.entity.ReserveEntity;
import m22t.ansdlsrb.m22tProject.data.repository.PlaceRepository;
import m22t.ansdlsrb.m22tProject.data.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public boolean is_valid(ReserveDto reserveDto) {
        List<ReserveEntity> entityList = new ArrayList<>();
        reserveRepository.findAll().forEach(entityList::add);

        if(isTimeWrong(reserveDto)) return false;

        else {
            for (ReserveEntity entity : entityList) {
                if (isMatching(entity, reserveDto)) {
                    if (isTimeDuplicated(entity, reserveDto))
                        return false; // 일치하는 순간 false 반환
                }
            }
        }


        return true; // 일치하는 것이 없을 때 true 반환

    }

    private boolean isMatching(ReserveEntity entity, ReserveDto reserveDto) {
        return entity.getPlace_id().equals(reserveDto.getPlace_id());

    }
    
    // 아래는 시간이 겹치는지 안겹치는지

    private boolean isTimeDuplicated(ReserveEntity entity,ReserveDto reserveDto){

        LocalDateTime start_time_entity=entity.getStart_time();
        LocalDateTime end_time_entity=entity.getEnd_time();

        LocalDateTime start_time_reserveDto=reserveDto.getStart_time();
        LocalDateTime end_time_reserveDto=reserveDto.getEnd_time();

        if(start_time_reserveDto.isAfter(end_time_entity) || end_time_reserveDto.isBefore(start_time_entity))
            return false;
        else return true;


    }


    private boolean isTimeWrong(ReserveDto reserveDto){
        LocalDateTime start_time_reserveDto=reserveDto.getStart_time();
        LocalDateTime end_time_reserveDto=reserveDto.getEnd_time();

        if(start_time_reserveDto.isBefore(end_time_reserveDto)){
            return false;

        }

        return true;}


    // 서버에서 display 할 장소 정보
    public List<PlaceDisplayDto> getPlaceSelectDtos() {
        List<PlaceEntity> places = placeRepository.findAll();
        return convertToPlaceSelectDtos(places);
    }


    public List<PlaceDisplayDto> convertToPlaceSelectDtos(List<PlaceEntity> places) {
        List<PlaceDisplayDto> placeSelectDtos = new ArrayList<>();
        for (PlaceEntity place : places) {
            PlaceDisplayDto dto = new PlaceDisplayDto();
            dto.setId(place.getId());
            dto.setType(place.getType());
            dto.setName(place.getName());
            dto.setRoadAddress(place.getRoadAddress());
            placeSelectDtos.add(dto);
        }
        return placeSelectDtos;
    }

    @Override
    public List<ReserveEntity> findMatchingUsers(String nickname) {

        List<ReserveEntity> matchingUsers = new ArrayList<>();

        List<ReserveEntity> entityList = new ArrayList<>();
        reserveRepository.findAll().forEach(entityList::add);



        for (ReserveEntity entity : entityList) {
            if (isMatching(entity, nickname)) {
                matchingUsers.add(entity);
            }
        }

        return matchingUsers;
    }

    private boolean isMatching(ReserveEntity entity,String nickname) {
        return entity.getUser_nickname().equals(nickname);

    }

}

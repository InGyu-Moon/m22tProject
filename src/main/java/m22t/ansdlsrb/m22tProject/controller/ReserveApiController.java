package m22t.ansdlsrb.m22tProject.controller;


import lombok.RequiredArgsConstructor;
import m22t.ansdlsrb.m22tProject.data.dto.PlaceDisplayDto;
import m22t.ansdlsrb.m22tProject.data.dto.PlaceDto;
import m22t.ansdlsrb.m22tProject.data.dto.ReserveDto;
import m22t.ansdlsrb.m22tProject.data.entity.ReserveEntity;
import m22t.ansdlsrb.m22tProject.data.repository.ReserveRepository;
import m22t.ansdlsrb.m22tProject.service.reserve.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReserveApiController {

    @Autowired
    private final ReserveRepository reserveRepository;
    @Autowired
    private final ReserveService reserveService;

    @PostMapping("/input-reserve") // 이 주소로 post 요청을 하면 예약 정보 db 저장
    public ResponseEntity<?> checkReservation(@RequestBody ReserveDto reserveDto) {
        try {
            if (reserveService.is_valid(reserveDto)) {
                // DTO 객체로부터 예약 정보를 추출하여 DB에 저장
                ReserveEntity user = new ReserveEntity();
                user.setUser_name(reserveDto.getUser_name());
                user.setUser_nickname(reserveDto.getUser_nickname());
                user.setUser_phone_number(reserveDto.getUser_phone_number());
                user.setPlace_id(reserveDto.getPlace_id());
                user.setStart_time(reserveDto.getStart_time());
                user.setEnd_time(reserveDto.getEnd_time());
                user.setNum_of_people(reserveDto.getNum_of_people());
                user.setPlaceEntity(reserveDto.getPlaceEntity());

                reserveRepository.save(user);

                return ResponseEntity.ok().body("{\"success\": true}");
            } else {
                return ResponseEntity.badRequest().body("{\"error\": \"Duplicate reservation.\"}");
            }
        } catch (Exception e) {
            // 에러 응답을 JSON 형식으로 생성하여 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"An error occurred while processing the request.\"}");
        }
    }

    @GetMapping("/find-places") // 서버로 부터 데이터를 가져오는 과정
    public List<PlaceDisplayDto> getPlacesApi() {
        return reserveService.getPlaceSelectDtos();
    }


    @GetMapping("/check-reserve/{nickname}")
    public ResponseEntity<?> checkReservation(@PathVariable String nickname) {
        try {
            List<ReserveEntity> matchingUser = reserveService.findMatchingUsers(nickname);

            if (matchingUser != null) {
                return ResponseEntity.ok().body(matchingUser);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No matching reservation found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request.");
        }
    }






}

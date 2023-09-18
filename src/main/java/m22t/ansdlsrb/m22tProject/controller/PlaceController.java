package m22t.ansdlsrb.m22tProject.controller;

import m22t.ansdlsrb.m22tProject.data.dto.PlaceLocationInputDto;
import m22t.ansdlsrb.m22tProject.data.dto.PlaceDto;
import m22t.ansdlsrb.m22tProject.service.place.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class PlaceController {
    private final PlaceService placeService;
    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }
    @PostMapping("/search-places")
    public ResponseEntity<List<PlaceDto>> searchPlacesByCoordinatesAndType(@RequestBody PlaceLocationInputDto placeLocationInputDto) {
        List<PlaceDto> places = placeService.getPlacesByCoordinates(placeLocationInputDto);
        return ResponseEntity.ok(places);
    }
}

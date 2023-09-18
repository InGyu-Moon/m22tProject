package m22t.ansdlsrb.m22tProject.service.place;

import m22t.ansdlsrb.m22tProject.data.dto.PlaceLocationInputDto;
import m22t.ansdlsrb.m22tProject.data.dto.PlaceDto;

import java.util.List;

public interface PlaceService {
    List<PlaceDto> getPlacesByCoordinates(PlaceLocationInputDto placeLocationInputDto);

}
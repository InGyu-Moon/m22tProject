package m22t.ansdlsrb.m22tProject.service.place;

import m22t.ansdlsrb.m22tProject.data.dao.PlaceDao;
import m22t.ansdlsrb.m22tProject.data.dto.PlaceLocationInputDto;
import m22t.ansdlsrb.m22tProject.data.dto.PlaceDto;
import m22t.ansdlsrb.m22tProject.data.entity.PlaceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService{

    PlaceDao placeDao;
    @Autowired
    public PlaceServiceImpl(PlaceDao placeDao){
        this.placeDao = placeDao;
    }
    @Override
    public List<PlaceDto> getPlacesByCoordinates(PlaceLocationInputDto placeLocationInputDto) {

        double minX = placeLocationInputDto.getX() - 0.005;
        double maxX = placeLocationInputDto.getX() + 0.005;
        double minY = placeLocationInputDto.getY() - 0.005;
        double maxY = placeLocationInputDto.getY() + 0.005;

        List<PlaceEntity> places = placeDao.getPlacesByCoordinates(minX, maxX, minY, maxY, placeLocationInputDto.getType());

        List<PlaceDto> placeDtos = new ArrayList<>();
        for (PlaceEntity placeEntity : places) {
            PlaceDto placeDto = new PlaceDto();
            placeDto.setId(placeEntity.getId());
            placeDto.setType(placeEntity.getType());
            placeDto.setLocation(placeEntity.getLocation());
            placeDto.setName(placeEntity.getName());
            placeDto.setAddress(placeEntity.getAddress());
            placeDto.setRoadAddress(placeEntity.getRoadAddress());
            placeDto.setPhone(placeEntity.getPhone());
            placeDto.setUrl(placeEntity.getUrl());
            placeDto.setX(placeEntity.getX());
            placeDto.setY(placeEntity.getY());
            placeDto.setImgUrl(placeEntity.getImgUrl());

            placeDtos.add(placeDto);
        }
        return placeDtos;
    }
}

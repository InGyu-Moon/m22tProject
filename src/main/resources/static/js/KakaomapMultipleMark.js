import { Map, MapMarker } from 'react-kakao-maps-sdk';

function KakaomapMultipleMark(){
    const positions = [
      {
        title: "카페1",
        latlng: { lat: 36.60117, lng: 127.2961 },
      },
      {
        title: "워크플레이스",
        latlng: { lat: 36.60117, lng: 127.2959 },
      },
      {
        title: "민간회의실2",
        latlng: { lat: 36.60116, lng: 127.2960 },
      },
      {
        title: "공공회의실",
        latlng: { lat: 36.60116, lng: 127.2961 },
      },
    ]

    return (
      <Map // 지도를 표시할 Container
        center={{
          // 지도의 중심좌표
          lat: 36.60117,
          lng: 127.2960,
        }}
        style={{
          // 지도의 크기
          width: "100%",
          height: "450px",
        }}
        level={3} // 지도의 확대 레벨
      >
        {positions.map((position, index) => (
          <MapMarker
            key={`${position.title}-${position.latlng}`}
            position={position.latlng} // 마커를 표시할 위치
            image={{
              src: "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png", // 마커이미지의 주소입니다
              size: {
                width: 24,
                height: 35
              }, // 마커이미지의 크기입니다
            }}
            title={position.title} // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          />
        ))}
      </Map>

    )

  }
  // KakaomapMultipleMark.js
// ...

export default KakaomapMultipleMark; // default export 추가
KakaomapMultipleMark.js
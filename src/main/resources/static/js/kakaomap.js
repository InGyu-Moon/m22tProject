import React, { useState } from 'react';
import { Map, MapMarker } from 'react-kakao-maps-sdk';


function Kakaomap(){
  const Main = () => {
    const [position, setPosition] = useState()
    return (
      <>
        <Map // 지도를 표시할 Container
          center={{
            // 지도의 중심좌표
            lat: 36.60117,
            lng: 127.2960,
          }}
          style={{
            width: "100%",
            height: "450px",
          }}
          level={3} // 지도의 확대 레벨
          onClick={(_t, mouseEvent) => setPosition({
            lat: mouseEvent.latLng.getLat(),
            lng: mouseEvent.latLng.getLng(),
          })}
        >
          {position && <MapMarker position={position} />}
        </Map>
        {position && <p>{'클릭한 위치의 위도는 ' + position.lat + ' 이고, 경도는 ' + position.lng + ' 입니다'}</p>}
      </>
    )
  }
  return (<Main/>)
}
export default Kakaomap
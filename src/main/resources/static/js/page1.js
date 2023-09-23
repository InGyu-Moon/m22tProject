// Page1.js
import React from 'react';
import Kakaomap from './Kakaomap';
import Button from './Button';

function Page1() {
  return (
    <div>
      <h1>Page 1</h1>
      <Kakaomap />
      <Button /> {/* Button 컴포넌트 렌더링 */}
    </div>
  );
}

export default Page1;
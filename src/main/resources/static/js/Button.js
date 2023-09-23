// Button.js
import React from 'react';
import { Link } from 'react-router-dom';

function Button() {
  return (
    <div>
      <Link to="/Page2">Go to Page 2</Link>
    </div>
  );
}

export default Button;
import React from 'react';
import { Helmet } from 'react-helmet'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Page1 from './Page1';
import Page2 from './Page2';
import Page3 from './Page3';

function App() {
  return (
    <>
      <Helmet>
        <script
          type="text/javascript"
          src="//dapi.kakao.com/v2/maps/sdk.js?appkey=525feabd973387121f74e3e0ece481bd&libraries=services,clusterer"
        ></script>
      </Helmet>
      <Router>
        <div>
          <Switch>
            <Route path="/" exact component={Page1} />
            <Route path="/Page2" component={Page2} />
            <Route path="/Page3" component={Page3} />
          </Switch>
        </div>
      </Router>
    </>
  );
}

export default App;
import React from 'react';
import {Link} from "react-router-dom";

function MainPage(props) {
  return (
      <div className={'text-3xl'}>
        <div>
          <Link to={'/about'}>about</Link>
        </div>
        <div>Main page</div>
      </div>
  );
}

export default MainPage;
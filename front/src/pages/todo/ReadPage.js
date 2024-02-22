import React from 'react';
import {createSearchParams, useNavigate, useParams, useSearchParams} from "react-router-dom";

function ReadPage(props) {
  const navigate = useNavigate();
  const {tno} = useParams();
  const [queryParams] = useSearchParams();
  const page = queryParams.get('page') ? parseInt(queryParams.get('page')) : 1;
  const size = queryParams.get('size') ? parseInt(queryParams.get('size')) : 10;
  const queryString = createSearchParams({page: page, size: size}).toString();

  console.log(tno)


  const moveToModify = (tno) => {
    navigate({
      pathname: `/todo/modify/${tno}`,
      search: queryString
    })
  }

  const moveToList = () => {
    navigate({
      pathname: `/list`,
      search: queryString
    })
  }

  return (
      <div className={'text-3xl'}>
        Todo Read Page {tno}

        <div>
          <button onClick={() => moveToModify(tno)}>test modify</button>
          <button onClick={moveToList}>목록으로 돌아가기</button>
        </div>
      </div>
  );
}

export default ReadPage;
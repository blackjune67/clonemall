import React from 'react';
import {createSearchParams, useNavigate, useParams, useSearchParams} from "react-router-dom";
import ReadComponent from "../../components/todo/ReadComponent";

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
      <div className={'font-extrabold w-full bg-white mt-6'}>
        {/*Todo Read Page {tno}*/}

        <div className="text-2xl">
          Todo Read Page Component
        </div>

        <ReadComponent tno={tno}/>
      </div>
  );
}

export default ReadPage;
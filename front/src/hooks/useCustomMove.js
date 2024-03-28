import {createSearchParams, useNavigate, useSearchParams} from "react-router-dom";
import {useState} from "react";

const getNum = (param, defaultValue) => {
  if (!param) {
    return defaultValue;
  }
  return parseInt(param);
}

const useCustomMove = () => {
  const navigate = useNavigate();

  const [refresh, setRefresh] = useState(false);

  const [queryParams] = useSearchParams();

  const page = getNum(queryParams.get('page'), 1);
  const size = getNum(queryParams.get('size'), 10);

  const queryDefault = createSearchParams({page, size}).toString();
  /*const moveToList = () => {
    navigate({
      pathname: '/list',
      search: queryDefault
    });
  }*/

  const moveToList = (pageParam) => {
    let queryString = ""
    if (pageParam) {
      const pageNum = getNum(pageParam.page, 1);
      const sizeNum = getNum(pageParam.size, 10);

      queryString = createSearchParams({page: pageNum, size: sizeNum}).toString();
    } else {
      queryString = queryDefault;
    }

    // ! 실시간으로 데이터를 받을 때 사용할 수 있게끔 만듦.
    // ex) 1페이지를 계속 눌렀을 때 데이터를 계속 받을 수 있다.
    setRefresh(!refresh);

    navigate({
      pathname: '../list',
      search: queryString
    });
  }

  const moveToModify = (num) => {
    navigate({
      pathname: `../modify/${num}`,
      search: queryDefault
    });
  }

  const moveToRead = (num) => {
    navigate({
      pathname: `../read/${num}`,
      search: queryDefault
    });
  }

  return {moveToList, moveToModify, moveToRead, page, size, refresh}
}


export default useCustomMove;
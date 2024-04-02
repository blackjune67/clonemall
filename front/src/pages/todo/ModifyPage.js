import React from 'react';
import ModifyComponent from "../../components/todo/ModifyComponent";
import {useNavigate, useParams} from "react-router-dom";

function ModifyPage() {
  const {tno} = useParams();
  const navigate = useNavigate();
  const moveToRead = () => {
    navigate({
      pathname: `/todo/read/${tno}`
    })
  }
  const moveToList = () => {
    navigate({
      pathname: `/todo/list`
    })
  }

  return (
      <div className={'p-4 w-full bg-white'}>
        <div className={'text-3xl font-extrabold'}>수정페이지</div>

        <ModifyComponent tno={tno}/>
      </div>
  );
}

export default ModifyPage;
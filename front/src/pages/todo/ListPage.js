import React from 'react';
import ListComponent from "../../components/todo/ListComponent";

function ListPage(props) {
/*
  const [queryParams] = useSearchParams();
  console.log(`==> queryParams : ${queryParams}`)
  const page = queryParams.get('page') ? parseInt(queryParams.get('page')) : 1;
  const size = queryParams.get('size') ? parseInt(queryParams.get('size')) : 10;*/

  return (
      <div className={'p-4 w-full bg-white'}>
        <div className={'text-3xl font-extrabold'}>
        </div>

        <ListComponent/>
      </div>
  );
}

export default ListPage;
import {lazy, Suspense} from "react";
import {Navigate} from "react-router-dom";

const Loading = <div className={'bg-red-700'}>로딩중...</div>
const TodoList = lazy(() => import('../pages/todo/ListPage'));
const TodoRead = lazy(() => import('../pages/todo/ReadPage'));
const TodoAdd = lazy(() => import('../pages/todo/AddPage'));
export const todoRouter = () => {
  return [
    {
      path: 'list',
      element: <Suspense fallback={Loading}><TodoList/></Suspense>
    },
    {
      path: '',
      element: <Navigate replace={true} to={'list'}/>
    },
    {
      path: 'read/:tno',
      element: <Suspense fallback={Loading}><TodoRead/></Suspense>
    },
    {
      path: 'add',
      element: <Suspense fallback={Loading}><TodoAdd/></Suspense>
    }
  ]
}
/* router 설정 */
import {createBrowserRouter} from "react-router-dom";
import {lazy, Suspense} from "react";
import {todoRouter} from "./todoRouter";

const Loading = <div className={'bg-red-700'}>로딩중...</div>
const Main = lazy(() => import('../pages/MainPage'));
const About = lazy(() => import('../pages/AboutPage'));
const TodoIndex = lazy(() => import('../pages/todo/IndexPage'));
// const TodoList = lazy(() => import('../pages/todo/ListPage'));
export const root = createBrowserRouter([
  {
    path: '',
    element: <Suspense fallback={Loading}><Main/></Suspense>,
    /*children: [
      {
        path: 'about',
        element: <AboutPage/>
      },
    ]*/
  },
  {
    path: 'about',
    element: <Suspense fallback={Loading}><About/></Suspense>,
  },
  {
    path: 'todo',
    element: <Suspense fallback={Loading}><TodoIndex/></Suspense>,
    children: todoRouter()
  }
]);
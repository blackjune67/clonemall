/* router 설정 */
import {createBrowserRouter} from "react-router-dom";
import {lazy, Suspense} from "react";


const Main = lazy(() => import('../pages/MainPage.js'));
const About = lazy(() => import('../pages/AboutPage.js'));
export const root = createBrowserRouter([
  {
    path: '',
    element: <Suspense><Main/></Suspense>,
    /*children: [
      {
        path: 'about',
        element: <AboutPage/>
      },
    ]*/
  },
  {
    path: 'about',
    element: <Suspense><About/></Suspense>,
  }
]);
import {useNavigate} from "react-router-dom";
import {useCallback} from "react";
import BasicLayout from "../../layout/BasicLayout";

const IndexPage = () => {
  const navigate = useNavigate()
  const handleClickList = useCallback(
      () => {
        navigate({pathname: 'list'});
      }, []);

  const handleClickAdd = useCallback(
      () => {
        navigate({pathname: 'add'});
      }, []);

  return (
      <BasicLayout>
        <div className={"text-black font-extrabold -mt-10"}>
          Products Menus
        </div>

        <div>
          <div
              className={"text-x1 m-1 p-2 w-20 font-extrabold text-center underline"}
              onClick={handleClickList}
          >List
          </div>

          <div
              className={"text-x1 m-1 p-2 w-20 font-extrabold text-center underline"}
              onClick={}
          >

          </div>
        </div>
      </BasicLayout>
  )
}

export default IndexPage;
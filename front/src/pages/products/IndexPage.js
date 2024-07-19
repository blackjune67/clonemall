import {Outlet, useNavigate} from "react-router-dom";
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

            <div className={'w-full flex m-2 p-2'}>
                <div
                    className={"text-x1 m-1 p-2 w-20 font-extrabold text-center underline"}
                    onClick={handleClickList}
                >List
                </div>

                <div
                    className={"text-x1 m-1 p-2 w-20 font-extrabold text-center underline"}
                    onClick={() => navigate('add')}
                > ADD
                </div>
            </div>
            <div className={"flex flex-wrap w-full"}>
                <Outlet/>
            </div>
        </BasicLayout>
    )
}

export default IndexPage;
import React from 'react';
import BasicLayout from "../../layout/BasicLayout";
import {Outlet, useNavigate} from "react-router-dom";

function IndexPage(props) {
    const navigate = useNavigate();
    // const handleClickList = useCallback(() => {
    //   navigate({pathname: 'list'});
    // },[navigate])
    //
    // const handleClickAdd = useCallback(() => {
    //   navigate({pathname: 'add'});
    // },[navigate])

    return (
        <BasicLayout>
            <div className={'w-full flex m-2 p-2'}>
                <div className={'text-xl m-1 p-2 w-20 font-extrabold text-center underline'}
                     onClick={() => navigate('list')}
                >LIST
                </div>
                <div className={'text-xl m-1 p-2 w-20 font-extrabold text-center underline'}
                     onClick={() => navigate('add')}>ADD
                </div>
            </div>
            <div className={'flex flex-wrap w-full'}>
                <Outlet/>
            </div>
        </BasicLayout>
    );
}

export default IndexPage;
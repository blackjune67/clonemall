import React, {useEffect, useState} from 'react';
import useCustomMove from "../../hooks/useCustomMove";
import {getList} from "../../api/productsApi";
import FetchingModal from "../common/FetchingModal";

function ListComponent(props) {
    const initState = {
        dtoList: [],
        pageNumberList: [],
        pageRequestDto: null,
        prev: false,
        next: false,
        totalCount: 0,
        prevPage: 0,
        nextPage: 0,
        totalPage: 0,
        current: 0,
    }

    const [serverData, setServerData] = useState(initState);
    const [fetching, setFetching] = useState(false);
    const {moveToList, moveToRead, page, size, refresh} = useCustomMove();

    useEffect(() => {
        setFetching(true);

        getList({}).then(data => {
            setFetching(false);
            setServerData(data);
        });
    }, [page, size, refresh]);
    return (
        <div>
            {fetching ? <FetchingModal/> : <></>}
        </div>
    );
}

export default ListComponent;
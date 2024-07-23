import React, {useEffect, useState} from 'react';
import {API_SERVER_HOST, getOne} from "../../api/productsApi";
import FetchingModal from "../common/FetchingModal";
import useCustomMove from "../../hooks/useCustomMove";

const initState = {
    pno: 0,
    pname: '',
    pdesc: '',
    price: 0,
    uploadFileNames: [],
}

const host = API_SERVER_HOST

function ReadComponent({pno}) {
    const [product, setProduct] = useState(initState);
    const [fetching, setFetching] = useState(false);

    const {moveToList, moveToModify, page, size} = useCustomMove();

    useEffect(() => {
        getOne(pno).then((data) => {
            console.log('==> getONE', data);
            setFetching(false);
            setProduct(data)
        });
    }, [pno]);

    return (
        <div className="border-2 border-sky-200 mt-10 m-2 p-4">

            {fetching ? <FetchingModal/> : <></>}

            <div className="flex justify-center mt-10">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">PNO</div>
                    <div className="w-4/5 p-6 rounded-r border border-solid shadow-md">
                        {product.pno}
                    </div>
                </div>
            </div>

            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">PNAME</div>
                    <div className="w-4/5 p-6 rounded-r border border-solid shadow-md">
                        {product.pname}
                    </div>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">PRICE</div>
                    <div className="w-4/5 p-6 rounded-r border border-solid shadow-md">
                        {product.price}
                    </div>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">PDESC</div>
                    <div className="w-4/5 p-6 rounded-r border border-solid shadow-md">
                        {product.pdesc}
                    </div>
                </div>
            </div>
            <div className="w-full justify-center flex  flex-col m-auto items-center">
                {product.uploadFileNames.map((imgFile, i) =>
                    <img
                        alt="product"
                        key={i}
                        className="p-4 w-1/2"
                        src={`${host}/api/products/view/${imgFile}`}/>
                )}
            </div>

            <div className="flex justify-end p-4">
                <button type="button"
                        className="inline-block rounded p-4 m-2 text-xl w-32  text-white bg-red-500"
                        onClick={() => moveToModify(pno)}
                >
                    Modify
                </button>
                <button type="button"
                        className="rounded p-4 m-2 text-xl w-32 text-white bg-blue-500"
                        onClick={() => moveToList({page, size})}
                >
                    List
                </button>
            </div>
        </div>
    );
}

export default ReadComponent;
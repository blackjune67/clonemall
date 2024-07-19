import React from 'react';

function FetchingModal(props) {
    return (
        <div className={`fixed top-0 left-0 z-[1055] flex h-full w-full  justify-center bg-black bg-opacity-20`}>
            <div
                className="absolute bg-white shadow dark:bg-gray-700 opacity-100 w-1/4 rounded  mt-10 mb-10 px-6 min-w-[600px]">
                <div className="justify-end flex ">
                    처리 중...
                </div>
            </div>
        </div>
    );
}

export default FetchingModal;
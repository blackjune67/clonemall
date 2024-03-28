// rsf

import React from 'react';

function PageComponent({serverData, movePage}) {
  // serverData.prev, pageNumList, next
  return (
      <div className="m-6 flex justify-center">
        {
          serverData.prev ?
              <div
                  className="m-2 p-2 w-16 text-center front-bold text-blue-400"
                  onClick={() => movePage({page: serverData.prevPage})}
              > Prev </div> : <></>
        }

        {
          serverData.pageNumberList.map(pageNumber =>
              console.log(`pageNumber :  ${pageNumber}`, `serverData : ${serverData.current}`) ||
              <div
                  key={pageNumber}
                  className={`m-2 p-2 w-16 text-center rounded shadow-md text-white
                      ${serverData.current === pageNumber ? 'bg-gray-500' : 'bg-blue-400'}`}
                  onClick={() => movePage({page: pageNumber})}>
                {pageNumber}
              </div>)
        }
        {
          serverData.next ?
              <div
                  className="m-2 p-2 w-16 text-center front-bold text-blue-400"
                  onClick={() => movePage({page: serverData.nextPage})}
              >
                Next
              </div> : <></>
        }

      </div>
  );
}

export default PageComponent;
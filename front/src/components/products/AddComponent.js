import React, {useRef, useState} from 'react';
import {postAdd} from "../../api/productsApi";

const initState = {
  pname: '',
  pdesc: '',
  price: 0,
  files: []
}

function AddComponent() {
  const [product, setProduct,] = useState({...initState});
  const uploadRef = useRef();
  const handleChangeProduct = (e) => {
    product[e.target.name] = e.target.value;
    setProduct({...product})
    const files = uploadRef.current.files;
    const formData = new FormData();
    // for (let i = 0; i < files.length; i++) {
    //   formData.append('files', files[i]);
    // }
    for (const file of files) {
      formData.append('files', file);
    }

    formData.append('pname', product.pname);
    formData.append('pdesc', product.pdesc);
    formData.append('price', product.price);

    postAdd(formData)
  }

  return (
      <div className="border-2 border-sky-200 mt-10 m-2 p-4">
        <div className="flex justify-center">
          <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-1/5 p-6 text-right font-bold">Name</div>
            <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md"
                   name="pname"
                   type={'text'}
                   value={product.pname}
                   onChange={handleChangeProduct}
            >
            </input>
          </div>
        </div>
        <div className="flex justify-center">
          <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-1/5 p-6 text-right font-bold">Desc</div>
            <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md"
                   name="pdesc"
                   rows={"4"}
                   type={'text'}
                   value={product.pdesc}
                   onChange={handleChangeProduct}
            >
            </input>
          </div>
        </div>
        <div className="flex justify-center">
          <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-1/5 p-6 text-right font-bold">price</div>
            <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md"
                   name="price"
                   type={'number'}
                   value={product.price}
                   onChange={handleChangeProduct}
            >
            </input>
          </div>
        </div>
        <div className="flex justify-center">
          <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-1/5 p-6 text-right font-bold">Files</div>
            <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md"
                   ref={uploadRef}
                   name="files"
                   type={'file'}
                   multiple={true}

            >
            </input>
          </div>
        </div>
        <div className="flex justify-end">
          <div className="relative mb-4 flex p-4 flex-wrap items-stretch">
            <button type="button"
                    className="rounded p-4 w-36 bg-blue-500 text-xl  text-white "
                    onClick={() => {
                      console.log('test')
                    }}
            >
              ADD
            </button>
          </div>
        </div>

        {/* Modal */}
        {/*{result ?
            <ResultModal
                title={'Add Result'}
                content={`New ${result} Added`}
                callbackFn={closeModal}
            />
            : <></>}*/}
      </div>
  );
}

export default AddComponent;
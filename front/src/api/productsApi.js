import axios from "axios";

export const API_SERVER_HOST = 'http://localhost:8080'

const host = `${API_SERVER_HOST}/api/products`

export const postAdd = async (product) => {
    const header = {
        headers: {'Content-Type': 'multipart/form-data'}
    }
    const response = await axios.post(`${host}/`, product, header)
    return response.data;
}

export const getList = async (pageParam) => {
    const {page, size} = pageParam;
    const response = await axios.get(`${host}/list`, {
        params: {page: page, size: size}
    });
    return response.data;
}
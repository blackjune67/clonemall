import axios from "axios";

export const API_SERVER_HOST = 'http://localhost:8080'

const prefix = `${API_SERVER_HOST}/api/todo`

export const getOne = async (tno) => {
  const response = await axios.get(`${prefix}/${tno}`)
  return response.data
}

export const getList = async (pageParam) => {
  const {page, size} = pageParam
  const response = await axios.get(`${prefix}/list`, {params: {page, size}})
  // const response = await axios.get(`${prefix}/list`, {params: {...pageParam}})
  return response.data
}

export const postAdd = async (todoObj) => {
  const response = await axios.post(`${prefix}/`, todoObj)
  console.log(`response.data : ${response.data}`, ' : ', response)
  return response.data
}
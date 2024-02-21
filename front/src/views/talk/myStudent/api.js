import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getMyStudentOne = (params) => {
    return getRequest('/myStudent/getOne', params)
}
export const getMyStudentList = (params) => {
    return getRequest('/myStudent/getByPage', params)
}
export const getMyStudentCount = (params) => {
    return getRequest('/myStudent/count', params)
}
export const addMyStudent = (params) => {
    return postRequest('/myStudent/insert', params)
}
export const editMyStudent = (params) => {
    return postRequest('/myStudent/update', params)
}
export const addOrEditMyStudent = (params) => {
    return postRequest('/myStudent/insertOrUpdate', params)
}
export const deleteMyStudent = (params) => {
    return postRequest('/myStudent/delByIds', params)
}
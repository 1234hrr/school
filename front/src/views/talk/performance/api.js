import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getPerformanceOne = (params) => {
    return getRequest('/performance/getOne', params)
}
export const getPerformanceList = (params) => {
    return getRequest('/performance/getByPage', params)
}
export const getPerformanceCount = (params) => {
    return getRequest('/performance/count', params)
}
export const addPerformance = (params) => {
    return postRequest('/performance/insert', params)
}
export const editPerformance = (params) => {
    return postRequest('/performance/update', params)
}
export const addOrEditPerformance = (params) => {
    return postRequest('/performance/insertOrUpdate', params)
}
export const deletePerformance = (params) => {
    return postRequest('/performance/delByIds', params)
}
export const getMyStudentList = (params) => {
    return getRequest('/myStudent/getAll', params)
}
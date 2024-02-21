import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getExamScoresOne = (params) => {
    return getRequest('/examScores/getOne', params)
}
export const getExamScoresList = (params) => {
    return getRequest('/examScores/getByPage', params)
}
export const getExamScoresCount = (params) => {
    return getRequest('/examScores/count', params)
}
export const addExamScores = (params) => {
    return postRequest('/examScores/insert', params)
}
export const editExamScores = (params) => {
    return postRequest('/examScores/update', params)
}
export const addOrEditExamScores = (params) => {
    return postRequest('/examScores/insertOrUpdate', params)
}
export const deleteExamScores = (params) => {
    return postRequest('/examScores/delByIds', params)
}
export const getMyStudentList = (params) => {
    return getRequest('/myStudent/getAll', params)
}
import request from '@/router/axios'

/**
 * 分页查询
 * @param query 分页查询条件
 */
export function fetchList(query) {
  return request({
    url: '/mangosound/qrtzJobDetails',
    method: 'get',
    params: query
  })
}

/**
 * 新增
 * @param obj 
 */
export function addObj(obj) {
  return request({
    url: '/mangosound/qrtzJobDetails',
    method: 'post',
    data: obj
  })
}

/**
 * 通过id查询
 * @param id 主键
 */
export function getObj(id) {
  return request({
    url: '/mangosound/qrtzJobDetails/' + id,
    method: 'get'
  })
}

/**
 * 通过id删除
 * @param id 主键
 */
export function delObj(id) {
  return request({
    url: '/mangosound/qrtzJobDetails/' + id,
    method: 'delete'
  })
}

/**
 * 修改
 * @param id 主键
 * @param obj 
 */
export function putObj(id, obj) {
  return request({
    url: '/mangosound/qrtzJobDetails/' + id,
    method: 'put',
    data: obj
  })
}

//存储公共数据
export const state = ()=>({
    count:0
})
//完成数据的同步修改
export const mutations = {
    increment (state, payload) {
       state.count+=payload;
    }
}
//存储公共数据
export const state = ()=>({
    username:'',
    userId:11,
    role:'',
    token:''
})
//完成数据的同步修改
export const mutations = {
    //修改用户信息
    updateUserId(state, payload) {
       state.userId=payload;
    },
    updateRole(state, payload) {
        state.role=payload;
     },
     updateToken(state, payload) {
        state.token=payload;
     }
}

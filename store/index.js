//存储公共数据
export const state = ()=>({
    username:'',
    userId:11,
    role:'',
    token:'eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoi5pmu6YCa55So5oi3IiwidXNlcklkIjoxMSwidXNlcm5hbWUiOiLpmYjpmYjpmYgiLCJpYXQiOjE2NzY2NDI3MDIsImV4cCI6MTY3NjY0MzMwMn0.-uBx9MEvjJM1BGOTu1eB2OfqHv3OSZh4wqcAB0xpPL8'
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

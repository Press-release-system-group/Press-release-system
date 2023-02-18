// const cookieParser = require('cookieparser');
//存储公共数据
export const state = ()=>({
    username:'',
    userId:11,
    role:'',
    token:'',
    category_id:''
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
     },
     updatecategory_id(state, payload) {
      state.category_id=payload;
   }
}


// export const actions = {
//    //  只能使用在store/index.js中的actoins中
//    nuxtServerInit({commit}, {req}){
//        let token = '';
//        if (req.headers.cookie) {
//            let parserd = cookieParser.parse(req.headers.cookie);
//            token = parserd.token;
//            console.log(token,'token');
//        }
//        commit('updateToken', token);
//    }
// }
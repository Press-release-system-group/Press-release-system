const cookieparser =require('cookieparser')
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


export const actions = {
   //  只能使用在store/index.js中的actoins中
   nuxtServerInit({commit}, {req}){
      let token='';
      let role='';
      let userId='';
      console.log("cookie——"+req.headers.cookie);
      //判断用户是否已登录
      if(req.headers.cookie){
         let parser=cookieparser.parse(req.headers.cookie);
         //修改token
         role=parser.role;
         userId=parser.userId;
         token=parser.token;
      }
      commit('updateToken',token);
      commit('updateRole',role);
      commit('updateUserId',userId);
   }
}
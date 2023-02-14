package com.example.javaee.interceptor;

import com.example.javaee.utils.JsonUtils;
import com.example.javaee.utils.JwtUtil;
import com.example.javaee.utils.Result;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 拦截请求检查token
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader("token");
        if (token != null && token.isEmpty()==false&&token.equals("null")!=true) {
            //检验是否是token签名

            if (JwtUtil.isSigned(token) != false) {

                //检验签名是否正确
                if (JwtUtil.verify(token) != false) {
                    System.out.println(3);
                    //检验签名是否过期
                    if (JwtUtil.isExpired(token) == false) {

                        Claims claims=JwtUtil.getClaim(token);

                        String username=(String)claims.get("username");
                        int userId=(int)claims.get("userId");
                        Map<String,Object> map=new HashMap<>();

                        map.put("username",username);
                        map.put("userId",userId);
                        String newToken=JwtUtil.generate(map);
                        response.setHeader("token",newToken);
                        return true;
                    }
                }
            }
        }


        response.setStatus(404);
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        String details="登录已过期或者没登陆";
      Result result=new Result(404,"登录已过期或者没登陆");
       String json= JsonUtils.toJson(result);
        response.getWriter().write(json);
        response.getWriter().flush();
        return false;
    }
}

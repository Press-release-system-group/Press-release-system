package com.example.javaee.interceptor;

import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.utils.JsonUtils;
import com.example.javaee.utils.JwtUtil;
import com.example.javaee.utils.Result;
import com.example.javaee.utils.Role;
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

                        //role权限校验
                        String uri = request.getRequestURI();
                        System.out.println(uri);
                        Role role = Role.valueOf((String)claims.get("role"));

                        //放行的条件取反
                        if(!(uri.startsWith("/common") ||
                                uri.startsWith("/publisher") && (role == Role.新闻发布者 || role == Role.管理员) ||
                                uri.startsWith("/admin") && role == Role.管理员))
                        {
                            throw new BusinessException(ExceptionEnum.USER_无权限);
                        }

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

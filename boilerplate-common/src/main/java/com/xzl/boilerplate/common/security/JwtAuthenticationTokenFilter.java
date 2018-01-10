package com.xzl.boilerplate.common.security;

import com.xzl.boilerplate.common.dto.exception.BizCode;
import com.xzl.boilerplate.common.dto.exception.BizException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
@Log
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    public static final String FILTER_EXCEPTION = "filter_exception";
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private UsernamePasswordAuthenticationToken authentication = null;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException, BizException {
        //eureka批量查询请求
        String authHeader = request.getHeader(this.tokenHeader);
        if (!request.getServletPath().equals("/auth")) {
            if (authHeader != null && authHeader.startsWith(tokenHead)) {
                final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
                JwtUser jwtUser = null;
                if (jwtTokenUtil.isTokenExpired(authToken)) {
                    request.setAttribute(FILTER_EXCEPTION, new BizException(BizCode.TOKEN_EXPIRED));
                    throw new BizException(BizCode.TOKEN_EXPIRED);
                }
                try {
                    //token的校验与解析
                    jwtUser = jwtTokenUtil.getJwtUserFromToken(authToken);
                } catch (Exception e) {
                    //token不合法
                    logger.info("###############【token not validated】 ###########");
                    SecurityContextHolder.getContext().setAuthentication(getUnauthorizedAuthentication());
                    request.setAttribute(FILTER_EXCEPTION, new BizException(BizCode.TOKEN_ILLEGAL));
                    throw new BizException(BizCode.TOKEN_ILLEGAL);
                }
                logger.info("###############【checking authentication】 ###########" + jwtUser.getUsername());
                if (jwtUser.getUsername() != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 签名token采用RSA非对称算法加密
                    // 这种情况下安全性较高，我们可以不用再查询数据库，而直接采用token中的数据
                    // 可以采用直接验证token是否合法来避免昂贵的数据查询
                    if (!jwtTokenUtil.isTokenExpired(authToken)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                jwtUser, authToken, jwtUser.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                request));
                        logger.info("#############authenticated user " + jwtUser.getUsername() + ", setting security context");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } else {
                //请求头缺少token
                logger.info("###############【token not present】 ###########");
                SecurityContextHolder.getContext().setAuthentication(getUnauthorizedAuthentication());
                request.setAttribute(FILTER_EXCEPTION, new BizException(BizCode.TOKEN_UNPRESENT));
                throw new BizException(BizCode.TOKEN_UNPRESENT);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 构建无权限的authentication,方便全局异常处理
     *
     * @return
     */
    public UsernamePasswordAuthenticationToken getUnauthorizedAuthentication() {
        if (this.authentication != null)
            return this.authentication;
        List list = new ArrayList();
        SimpleGrantedAuthority sga = new SimpleGrantedAuthority("Unauthorized");
        list.add(sga);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                null, null, list);
        this.authentication = authentication;
        return authentication;
    }

}

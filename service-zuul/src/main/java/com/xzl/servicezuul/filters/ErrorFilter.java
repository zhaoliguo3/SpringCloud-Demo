//package com.xzl.servicezuul.filters;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class ErrorFilter extends ZuulFilter {
//
//    private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);
//
//    @Override
//    public String filterType() {
//        //异常过滤器
//        return "error";
//    }
//
//    @Override
//    public int filterOrder() {
//        //优先级，数字越大，优先级越低
//        return 10;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        //是否执行该过滤器，true代表需要过滤
//        return true;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        log.info("进入异常过滤器");
//
//        System.out.println(ctx.getResponseBody());
//
////        ctx.setResponseBody("出现异常");
//        Throwable throwable = ctx.getThrowable();
//        log.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
//        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        ctx.set("error.exception", throwable.getCause());
//        return null;
//
//
//
//    }
//
//}

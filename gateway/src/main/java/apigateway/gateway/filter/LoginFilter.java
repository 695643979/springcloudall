package apigateway.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by Administrator on 2018/12/20.
 * @author: wenhuaping
 */
@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 过滤器类型，前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 过滤器顺序，越小越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 4;
    }

    /**
     * 过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //System.out.println(request.getRequestURI()); ///apigateway/product/api/v1/product/list
        //System.out.println(request.getRequestURL()); //http://localhost:9000/apigateway/product/api/v1/product/list

        //ACL
        //访问路径：9000为服务网购的路由，/apigateway/order/** 是配置的规则，http://localhost:9000/apigateway/order/api/v1/order/save?userId=1&productId=1
        if ("/apigateway/order/api/v1/order/save".equalsIgnoreCase(request.getRequestURI())){
            System.out.println("进入了save方法！");
            return true;
        }else if ("/apigateway/order/api/v1/order/list".equalsIgnoreCase(request.getRequestURI())){
            System.out.println("进入了list方法！");
            return true;
        }else if ("/apigateway/order/api/v1/order/find".equalsIgnoreCase(request.getRequestURI())){
            System.out.println("进入了find方法！");
            return true;
        }
        return false;
    }

    /**
     * 业务逻辑
     * shouldFilter() 为true 的话，就直接跳转到run（）
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        System.out.println("订单接口，进入了LoginFilter中run方法！");
        //JWT
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        // token 对象,从请求头中获取，请求头中没有的话，就从请求参数里面获取。
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

        //登陆校验逻辑，根据公司情况自定义 JWT
        if (StringUtils.isBlank(token)){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}

package xsm520.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class optionInterceptor  extends AbstractInterceptor{
	@Override
	public String intercept(ActionInvocation invocation) throws Exception{
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		System.out.println("自定义拦截器-http请求发生 类型为："+request.getMethod());
		if (request.getMethod().equals("OPTIONS")) {
			System.out.println("Options请求 返回空");
			return Action.NONE;
		}else{
			return invocation.invoke();
		}
	}
}

package xsm520.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class isloginInterceptor  extends AbstractInterceptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		boolean isLogin = (boolean)session.getAttribute("isLogin");
		System.out.println("�Զ���������-�Ƿ��¼isLogin��"+isLogin);
		if (!isLogin) {
			System.out.println("δ��¼");
			return Action.NONE;
		}else{
			return invocation.invoke();
		}
	}
}
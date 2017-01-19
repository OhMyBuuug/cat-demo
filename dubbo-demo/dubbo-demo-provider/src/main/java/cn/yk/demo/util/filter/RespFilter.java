package cn.yk.demo.util.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.apache.log4j.Logger;

/**
 * 2016年11月9日下午1:29:54
 * 增加了响应头,允许跨域调用
 * @author zhangx
 * TODO
 */
public class RespFilter implements ContainerResponseFilter{
	
	private Logger log = Logger.getLogger(RespFilter.class);

	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		log.info("增加Access-Control-Allow-Origin:*");
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
	}

}

package corp.asbp.platform.commonutils.cu.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;

import corp.asbp.platform.commonutils.cu.constants.CommonUtilConstants;
import corp.asbp.platform.commonutils.cu.model.SsHeader;
import corp.asbp.platform.commonutils.cu.model.UserResponseDto;

@Component
public class ServletUtil {
	
	@Autowired
	private ObjectMapper mapper;
	
	public UserResponseDto getUserDto(HttpServletRequest request) {
		Object obj = request.getAttribute(CommonUtilConstants.USER_HEADER);
		if (obj == null) {
			throw new IllegalArgumentException("User attribute can't be empty");
		}
		return (UserResponseDto) obj;
	}
	
	public SsHeader getSsHeader(HttpServletRequest request) throws IOException {
		String ssHeaderStr = request.getHeader(CommonUtilConstants.SS_HEADER);
		if (StringUtils.isNotBlank(ssHeaderStr)) {
			return mapper.readValue(ssHeaderStr, SsHeader.class);
		} 
		throw new IllegalArgumentException("Headers can't be empty!");
	}
	
	public boolean hasIgnoreCriteria(String uri, String method) {
		return uri.contains(CommonUtilConstants.SWAGGER_URI) 
			|| uri.contains(CommonUtilConstants.SWAGGER_DOCS_URI) 
			|| uri.contains(CommonUtilConstants.SWAGGER_CONFIGURATION) 
			|| uri.contains(CommonUtilConstants.JAVA_MELODY_URI) 
			|| method.equals(CommonUtilConstants.OPTIONS_HEADER)
			|| uri.contains(CommonUtilConstants.STATIC_CONTENTS)
			|| uri.contains(CommonUtilConstants.HEALTH_CHECK_API)
			|| CommonUtilConstants.STATIC_CONTENTS_REGEX.matcher(uri).matches();
			
	}
	
	public void setServletRequestObject(UserResponseDto userResponseDto, HttpServletRequest request) {
		request.setAttribute(CommonUtilConstants.USER_HEADER, userResponseDto);
	}

	public String getRequestUriFromAttribute(HttpServletRequest request) {
		return (String) request.getAttribute(CommonUtilConstants.REQUEST_URI); 
	}
	
	public String getRequestUri(HttpServletRequest request) {
		return request.getHeader(CommonUtilConstants.REQUEST_URI); 
	}
	
	public HttpMethod getRequestMethod(HttpServletRequest request) {
		return HttpMethod.valueOf(request.getHeader(CommonUtilConstants.REQUEST_METHOD)); 
	}
	
	public HttpServletRequest getHttpServletRequest() {
	    RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
	    if (attribs instanceof NativeWebRequest) {
	        return (HttpServletRequest) ((NativeWebRequest) attribs).getNativeRequest();
	    }
	    return null;
	}
}

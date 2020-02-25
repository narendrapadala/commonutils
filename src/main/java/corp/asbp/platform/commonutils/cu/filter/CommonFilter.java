package corp.asbp.platform.commonutils.cu.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import corp.asbp.platform.commonutils.cu.config.RestTemplateConfiguration;
import corp.asbp.platform.commonutils.cu.constants.CommonUtilConstants;
import corp.asbp.platform.commonutils.cu.exception.model.InternalRestApiException;
import corp.asbp.platform.commonutils.cu.filter.condition.CommonFilterCondition;
import corp.asbp.platform.commonutils.cu.model.GenericResponseDto;
import corp.asbp.platform.commonutils.cu.model.RestMethod;
import corp.asbp.platform.commonutils.cu.model.UserResponseDto;
import corp.asbp.platform.commonutils.cu.util.CommonUtilProperties;
import corp.asbp.platform.commonutils.cu.util.RestApiUtilInternal;
import corp.asbp.platform.commonutils.cu.util.ServletUtil;

@Component
@Import(RestTemplateConfiguration.class)
@Conditional(CommonFilterCondition.class)
@Order(1)
public class CommonFilter implements Filter {

	private static Logger LOGGER = LoggerFactory.getLogger(CommonFilter.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private CommonUtilProperties properties;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ServletUtil servletUtil;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Initiating common filter..");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			
			
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			String headerToken = httpServletRequest.getHeader(CommonUtilConstants.AUTH_TOKEN);
			String uri = httpServletRequest.getRequestURI();
			String method = httpServletRequest.getMethod();
			
			
			try {
				if (servletUtil.hasIgnoreCriteria(uri, method)) {
					LOGGER.info("Sending request as it passed ignore criteria...");
					chain.doFilter(request, response);
				} else if (StringUtils.isNotEmpty(headerToken)) {
					if (headerToken.equals(properties.getAuthToken())) {
						LOGGER.info("Sending request via auth token...");
						if(StringUtils.isNotEmpty(httpServletRequest.getHeader(CommonUtilConstants.SS_HEADER))) {
							setUserAttributes(httpServletRequest, httpServletResponse, uri, method);
						}
						chain.doFilter(request, response);
					} else {
						httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			            writeErrorResponse(httpServletResponse, HttpStatus.UNAUTHORIZED, uri, 
			            		"Invalid token. You are not authorized to access this resource");
					}
				} else {
					setUserAttributes(httpServletRequest, httpServletResponse, uri, method);
					chain.doFilter(request, response);
				}
				
			} catch (InternalRestApiException e) {
				LOGGER.error("Error occured while calling validateSession! ", e);
				e.printStackTrace();
				httpServletResponse.setStatus(e.getStatusCode());
				writeErrorResponse(httpServletResponse, HttpStatus.valueOf(e.getStatusCode()), uri, e.getMessage());
			}
			
			return;
		}
		
	}
	
	private void writeErrorResponse(HttpServletResponse httpServletResponse, HttpStatus httpStatus, String uri, String msg)
			throws IOException, JsonProcessingException {
		httpServletResponse.getWriter().write(mapper.writeValueAsString(new GenericResponseDto.GenericResponseDtoBuilder<UserResponseDto>(msg, 
																																	     httpStatus.value(), 
																																	     uri, 
																																	     null).build()));
	}
	private void setUserAttributes(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String uri, String method) throws JsonProcessingException, IOException, InternalRestApiException {
		httpServletRequest.setAttribute(CommonUtilConstants.REQUEST_URI, uri);
		httpServletRequest.setAttribute(CommonUtilConstants.REQUEST_METHOD, method);
	
		LOGGER.info("Sending request to asbp for getting user object via session and doing other validations...");
		GenericResponseDto<UserResponseDto> genericResponseDto = getGenericResponseDto(httpServletRequest);
		UserResponseDto userResponseDto = genericResponseDto.getResponse();
		servletUtil.setServletRequestObject(userResponseDto, httpServletRequest);
	}

	private GenericResponseDto<UserResponseDto> getGenericResponseDto(HttpServletRequest httpServletRequest) throws InternalRestApiException {
		RestApiUtilInternal<RequestEntity<Object>, UserResponseDto> util = new RestApiUtilInternal<>(properties.getAuthToken());
		return util.requestAndGetGenericResponseObject(restTemplate, properties.getAsbpSessionValidationUrl(), RestMethod.POST, UserResponseDto.class, httpServletRequest);
	}
	
	@Override
	public void destroy() {
		LOGGER.info("Destroying common filter..");
		
	}

}

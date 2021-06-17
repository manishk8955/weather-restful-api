package com.weather.api.config;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Component
public class WeatherThrottlingApiKeyFilter implements Filter {


	@Autowired
	private Environment env;

	private LoadingCache<String, Integer> requestsPerApiKey;

	public WeatherThrottlingApiKeyFilter() {
		super();
		requestsPerApiKey = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		String inputApiKey = ((HttpServletRequest) servletRequest).getHeader("x-api-key");

		if (StringUtils.isNotBlank(inputApiKey) && isLimitCrossed(inputApiKey)) {
			httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
			int maxRequests = Integer.parseInt(env.getProperty("requests.per.hour")) + 1;
			System.out.println("Too many requests: Only " + maxRequests + " requests per hour allowed.");
			httpServletResponse.getWriter().write("Too many requests: Only " + maxRequests + " requests per hour allowed.");
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	private boolean isLimitCrossed(String apiKey) {
		int requests = 0;
		try {
			int maxRequests = Integer.parseInt(env.getProperty("requests.per.hour"));
			requests = requestsPerApiKey.get(apiKey);
			if (requests > maxRequests) {
				requestsPerApiKey.put(apiKey, requests);
				return true;
			}
		} catch (ExecutionException e) {
			requests = 0;
		}
		requests++;
		requestsPerApiKey.put(apiKey, requests);
		return false;
	}
}
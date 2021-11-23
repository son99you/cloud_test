/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.pel.mybatis.interceptor; 

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mybatis SQL 로그를 가독성있게 재구성해주는 Interceptor 클래스
 * 
 * @author : 
 * @date : 2014. 11. 13.
 * @version : 1.0
 */
@Intercepts({
    
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
       ,@Signature(type = Executor.class, method = "query",  args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
       ,@Signature(type = Executor.class, method = "query",  args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
    

})
public class FwkSqlLogInterceptor implements Interceptor {
	
	private static final Logger LOG = LoggerFactory.getLogger("SQL_LOG");

	public Object intercept(Invocation invocation) throws Throwable {
	    
	    Object[]        args     = invocation.getArgs();
	    MappedStatement ms       = (MappedStatement)args[0];		
		Object          param    = (Object)args[1];
		BoundSql        boundSql = ms.getBoundSql(param);
		
		String sql = replaceSql(boundSql, param);
		
		
		LOG.debug("=====================================================================");		
		LOG.debug("SQL Id : " + ms.getId());
		LOG.debug("orgiSql : " + boundSql.getSql());
		LOG.debug("params : " + param);
		LOG.debug("bindSql : " + sql);
		LOG.debug("=====================================================================");
		
		return invocation.proceed(); // 쿼리 실행
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties arg0) {
	}
	
private static String replaceSql(BoundSql boundSql, Object param) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		String sql = boundSql.getSql();
		
		
		
		if(param == null) {
			sql = sql.replaceFirst("\\?",  "''");
		} else {
			if(param instanceof Integer || param instanceof Long || param instanceof Float || param instanceof Double) {
				sql = sql.replaceFirst("\\?",  Matcher.quoteReplacement(param.toString()));
			} else if(param instanceof String ) {
				sql = sql.replaceFirst("\\?",  "'" + Matcher.quoteReplacement((String)param) + "''");
			} else if(param instanceof Map ) {				
				sql = replaceSqlByMap(boundSql, param);				
			} else {
				
				List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
				
				Class<? extends Object> paramClass = param.getClass();
				
				for(ParameterMapping mapping : paramMapping) {
					String propValue = mapping.getProperty();
					
					Field field = paramClass.getDeclaredField(propValue);
					field.setAccessible(true);
					
					Class<?> javaType = mapping.getJavaType();
					
					if(String.class == javaType) {
						sql = sql.replaceFirst("\\?",  "" + field.get(param) + "'");
					} else {
						sql = sql.replaceFirst("\\?",  "" + Matcher.quoteReplacement(field.get(param).toString()) + "");
					}
				}
				
				
			}
		}
		return sql;
		
	}
	
	private static String replaceSqlByMap(BoundSql boundSql, Object param) {
		
		String sql = boundSql.getSql();
		
		List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
		
		for(ParameterMapping mapping : paramMapping) {
			
			String propValue = mapping.getProperty();
			@SuppressWarnings("rawtypes")
			Object value = ((Map) param).get(propValue);
			
			if(value == null ){
				sql = sql.replaceFirst("\\?",  "" + value + "");
			} else {
			
				if(value instanceof String) {
					sql = sql.replaceFirst("\\?",  "'" + Matcher.quoteReplacement(value.toString()) + "'");
				} else {						
					sql = sql.replaceFirst("\\?",  "'" + Matcher.quoteReplacement(value.toString()) + "'");
				}
			}
		}
		
		return sql;
	}

}

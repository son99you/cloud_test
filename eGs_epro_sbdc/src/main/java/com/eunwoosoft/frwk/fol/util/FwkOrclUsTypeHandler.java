/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.fol.util;


import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * com.eunwoosoft.frwk.fol.util 
 *    |_ FwkCertUtil.java
 * 
 * </pre>
 * @date : 2015. 3. 12. 오후 12:07:08
 * @version : 1.0
 * @author : 
 */
public class FwkOrclUsTypeHandler extends BaseTypeHandler<String> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {

		String str = null;
		System.out.println("dddddddd====");
		try {

			str = new String(rs.getString(columnName).getBytes("ISO8859_1"), "EUC_KR");

		} catch (UnsupportedEncodingException e) {

			this.logger.debug("UnsupportedEncodingException : "

					+ e.getMessage());
			
			str = rs.getString(columnName);

		} catch (Exception localException) {

		}

		return str;

	}
	
	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

		String str = null;
		System.out.println("cccccccc====");
		try {

			str = new String(rs.getString(columnIndex).getBytes("ISO8859_1"), "EUC_KR");

		} catch (UnsupportedEncodingException e) {

			this.logger.debug("UnsupportedEncodingException : "

					+ e.getMessage());
			
			str = rs.getString(columnIndex);

		} catch (Exception localException) {

		}

		return str;

	}
	
	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

		String str = null;
		
		System.out.println("bbbbbbbb====");

		try {

			str = new String(cs.getString(columnIndex).getBytes("ISO8859_1"), "EUC_KR");

		} catch (UnsupportedEncodingException e) {

			this.logger.debug("UnsupportedEncodingException : "

					+ e.getMessage());
			
			str = cs.getString(columnIndex);

		} catch (Exception localException) {

		}

		return str;

	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbctype)

			throws SQLException {

		String str = null;
		
		System.out.println("aaaaaaaaaa====");

		try {

			str = new String(parameter.getBytes("ISO8859_1"), "EUC_KR");

		} catch (UnsupportedEncodingException e) {

			this.logger.debug("UnsupportedEncodingException : "

					+ e.getMessage());

			str = parameter;

		} catch (Exception localException) {

		}

		ps.setString(i, str);

	}



	public Object valueOf(String s) {

		return s;

	}
}

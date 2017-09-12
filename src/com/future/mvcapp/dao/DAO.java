package com.future.mvcapp.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.future.mvcapp.db.JdbcUtils;
import com.mysql.jdbc.Connection;

/**
 * 
 * @author admin
 * ��ǰdaoֱ���ڷ����л�ȡ���ݿ�����
 * @param<T>:��ǰDAO�����ʵ��������ʲô
 */
public class DAO<T> {
	
	private QueryRunner queryRunner = new QueryRunner();
	
	private Class<T> clazz;
	
	public DAO() {
		Type superClass = getClass().getGenericSuperclass();
		//���䣡����
		if(superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			
			Type [] typeArgs = parameterizedType.getActualTypeArguments();
			if(typeArgs !=null && typeArgs.length>0) {
				if(typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0];
				}
			}
		}
		
	}
	
	/**
	 * ����ĳһ���ֶε�ֵ�����緵��ĳһ����¼��customerName,���߷������ݱ����ж�������¼
	 * @param sql
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> E getForValue(String sql, Object ...args) {
		
		Connection connection = null;
		
		try {
			connection = (Connection) JdbcUtils.getConnection();
			return (E) queryRunner.query(connection,sql,new ScalarHandler(),args);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JdbcUtils.releaseConnection(connection);
		}

		return null;
	}
	
	
	/**
	 * ����T��Ӧ��List
	 * @param sql
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getForList(String sql, Object ...args){
		
		Connection connection = null;
		
		try {
			connection = (Connection) JdbcUtils.getConnection();
			return (List<T>) queryRunner.query(connection,sql,new BeanListHandler<>(clazz),args);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JdbcUtils.releaseConnection(connection);
		}

		return null;

	}
	
	/**
	 * ���ض�Ӧ��T��һ��ʵ����Ķ���
	 * @param sql
	 * @param args
	 * @return
	 */
	
	public T get(String sql, Object ...args) {
		
		Connection connection = null;
		
		try {
			connection = (Connection) JdbcUtils.getConnection();
			return queryRunner.query(connection,sql,new BeanHandler<>(clazz),args);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
		
		System.out.println(clazz);
		return null;
	}
	
	/**
	 *�÷�����װ��Insert Delete Update����
	 * @param sql��SQL���
	 * @param args�����SQL����ռλ��
	 */
	public void update(String sql, Object ... args) {
		Connection connection = null;
		try {
			connection = (Connection) JdbcUtils.getConnection();
			queryRunner.update(connection,sql,args);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
	}
	

}

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
 * 当前dao直接在方法中获取数据库连接
 * @param<T>:当前DAO处理的实体类型是什么
 */
public class DAO<T> {
	
	private QueryRunner queryRunner = new QueryRunner();
	
	private Class<T> clazz;
	
	public DAO() {
		Type superClass = getClass().getGenericSuperclass();
		//反射！！！
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
	 * 返回某一个字段的值，例如返回某一条记录的customerName,或者返回数据表中有多少条记录
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
	 * 返回T对应的List
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
	 * 返回对应的T的一个实例类的对象
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
	 *该方法封装了Insert Delete Update操作
	 * @param sql：SQL语句
	 * @param args：填充SQL语句的占位符
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

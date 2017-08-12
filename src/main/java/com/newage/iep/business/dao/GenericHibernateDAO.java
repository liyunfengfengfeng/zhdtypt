package com.newage.iep.business.dao;

import com.newage.iep.util.PaginationSupport;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class GenericHibernateDAO<PK extends Serializable>  {

	@Autowired
	private SessionFactory sessionFactory;
    public Session getSession() {
        //事务必须是开启的(Required)，否则获取不�?
        return sessionFactory.getCurrentSession();
    }
    /**
     * 存储数据
     * @param model
     * @return
     */
    public  Serializable save(Object model) {
        return getSession().save(model);
    }

    /**
     * 存储或修改
     * @param model
     */
    public void saveOrUpdate(Object model) {
        getSession().saveOrUpdate(model);
    }

    /**
     * 修改
     * @param model
     */
    public void update(Object model) {
        getSession().update(model);
    }

    /**
     * 删除
     * @param model
     */
    public void delete(Object model) {
        getSession().delete(model);
    }

    public void deleteByIds(Class model,PK...id){
    	for(int i=0;i<id.length;i++){
    		getSession().delete(getObjById(model, id[i]));
    	}
    }

    /**
     * 通过ID获取对象
     * @param model
     * @param id
     */
    public Object getObjById(Class model,PK id){
    	return getSession().get( model, id);
    }
    /**
	 * 创建Query对象.
	 * 对于�?��first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设�?
	 * 留意可以连续设置,如下�?
	 * 
	 * <pre>
	 * dao.createQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下�?
	 * 
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 * 
	 * @param values
	 *            可变参数.
	 * @return org.hibernate.Query
	 */
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	public Query createQueryByLimit(String hql, Integer startNo, Integer num, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.setFirstResult(startNo).setMaxResults(num);
	}

	@SuppressWarnings("unchecked")
	public List find(String hql){
		return getSession().createQuery(hql).list();
	}
	
	/**
	 * 创建Query对象(SQL).
	 * 对于�?��first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设�?
	 * 留意可以连续设置,如下�?
	 * 
	 * <pre>
	 * dao.createQuery(sql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下�?
	 * 
	 * <pre>
	 *        dao.createQuery(sql)
	 *        dao.createQuery(sql,arg0);
	 *        dao.createQuery(sql,arg0,arg1);
	 *        dao.createQuery(sql,new Object[arg0,arg1,arg2])
	 * </pre>
	 * 
	 * @param values
	 *            可变参数.
	 * @return org.hibernate.Query
	 */
	public Query createSQLQuery(String sql, Object... values) {
		Assert.hasText(sql);
		Query query = getSession().createSQLQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	public List<Map<String,Object>> createSQLQueryReturnMap(String sql, Object... values) {
		Assert.hasText(sql);
		Query query = getSession().createSQLQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = query.list();
		return list;
	}
	/**
	 * 查询二级缓存
	 */
	public Query queryCache(String sql, Object... values){
		Assert.hasText(sql);
		Query query = getSession().createQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		query.setCacheable(true);
		return query;
	}
	public PaginationSupport findPageByQuery(String hql, int pageNo,
											 int pageSize, Object... values) {
		
		String countQueryString = hql;
		countQueryString = " select count(*) " + removeSelect(removeOrders(countQueryString));
		int totalCount = 0;
		if (createQuery(countQueryString, values).uniqueResult() != null)
			totalCount = ((Long) createQuery(countQueryString, values).uniqueResult()).intValue();

		if (totalCount < 1)
			return new PaginationSupport(new ArrayList(), 0, 0, pageSize);
		
		int start = PaginationSupport.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);
		List records = query.setFirstResult(start).setMaxResults(pageSize).list();
		return new PaginationSupport(records, totalCount, pageNo, pageSize);
	}

	/**sql分页查询*/
	public PaginationSupport findPageBySqlQuery(String sql, int pageNo,
												int pageSize, Object... values) {

		String countQueryString = sql;
		countQueryString = " select count(*) " + removeSelect(removeOrders(countQueryString));
		int totalCount = 0;
		if (createSQLQuery(countQueryString, values).uniqueResult() != null)
			totalCount = ((BigInteger) createSQLQuery(countQueryString, values).uniqueResult()).intValue();

		if (totalCount < 1)
			return new PaginationSupport(new ArrayList(), 0, 0, pageSize);

		int start = PaginationSupport.getStartOfPage(pageNo, pageSize);
		Query query = createSQLQuery(sql, values);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List records = query.setFirstResult(start).setMaxResults(pageSize).list();
		return new PaginationSupport(records, totalCount, pageNo, pageSize);
	}

	public PaginationSupport findPageByQuery2(String hql, int pageNo,
			int pageSize,String table, Object... values) {
		
		String countQueryString = hql;
		countQueryString = " select count(*) " + removeSelect(removeOrders(countQueryString));
		int totalCount = 0;
		if (createQuery(countQueryString, values).uniqueResult() != null)
			totalCount = ((Long) createQuery(countQueryString, values).uniqueResult()).intValue();

		if (totalCount < 1)
			return new PaginationSupport(new ArrayList(), 0, 0, pageSize);
		
		int start = PaginationSupport.getStartOfPage(pageNo, pageSize);
		Query query = createQuery("select "+table+" "+hql, values);
		List records = query.setFirstResult(start).setMaxResults(pageSize).list();
		return new PaginationSupport(records, totalCount, pageNo, pageSize);
	}
	public PaginationSupport findPageByQuery3(String hql, int pageNo,
			int pageSize, Object... values) {
		
		String countQueryString = hql;
		countQueryString = " select count(*) " + removeSelect(removeOrders(countQueryString));
		int totalCount = 0;
		if (createQuery(countQueryString, values).uniqueResult() != null){
			totalCount = ((Long) createQuery(countQueryString, values).uniqueResult()).intValue();
		}
		if (totalCount < 1)
			return new PaginationSupport(new ArrayList(), 0, 0, pageSize);
		
		int start = PaginationSupport.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);
		List records = query.setFirstResult(start).setMaxResults(pageSize).list();
		return new PaginationSupport(records, totalCount, pageNo, pageSize);
	}

	public List<?> findListBySQL(String sql, Map<String, Object> agrs) {
		return findListBySQL(sql, agrs, -1, -1);
	}

	private List<?> findListBySQL(String sql, Map<String, Object> agrs, int start, int size) {
		Assert.hasText(sql);
		Query query = getSession().createSQLQuery(sql);
		query = setQueryParameter(query, agrs);
		if (size != -1) {
			query.setFirstResult(start);
			query.setMaxResults(size);
		}
		return query.list();
	}

	//HQL查询语句IN
	//@param hql
	//@param agrs 查询条件
	//@return
	public List<?> findListByHQL(String hql, Map<String, Object> agrs) {
		return findListByHQL(hql, agrs, -1, -1);
	}

	private List<?> findListByHQL(String hql, Map<String, Object> agrs, int start, int size) {
		Query query = getSession().createQuery(hql);
		query = setQueryParameter(query, agrs);
		if (size != -1) {
			query.setFirstResult(start);
			query.setMaxResults(size);
		}
		return query.list();
	}

	//设置Query 的参数（Map类型的）
	//@param query
	//@param agrs
	//@return
	//
	private Query setQueryParameter(Query query, Map<String, Object> agrs) {
		if (agrs != null) {
			Iterator<String> keys = agrs.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = agrs.get(key);
				if (value instanceof Collection)
					query.setParameterList(key, (Collection<?>) value);
				else if (value instanceof Object[])
					query.setParameterList(key, (Object[]) value);
				else
					query.setParameter(key, value);
			}
		}
		return query;
	}


	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	protected static String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	protected static String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}




}
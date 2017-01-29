package com.jy.dao.impl;

import com.jy.dao.MoodDao;
import com.jy.entity.Mood;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MoodDaoImpl extends BaseDaoImpl<Mood> implements MoodDao {


    @Override
    public int countMoodByMoodParam(MoodParam param) {
        StringBuilder hqlBuilder = new StringBuilder("select count(mood) from Mood mood where mood.deleted = :deleted");
        Map<String, Object> sqlParam = new HashMap<>();
        sqlParam.put("deleted", false);
        if (param != null) {
            //ID
            if (param.getId() != null) {
                hqlBuilder.append(" and mood.id = :id");
                sqlParam.put("id", param.getId());
            }
            //Username
            if (param.getUsername() != null) {
                hqlBuilder.append(" and mood.owner.username = :username");
                sqlParam.put("username", param.getUsername());
            }
        }
        Query query = getCurrentSession().createQuery(hqlBuilder.toString());
        for (Map.Entry<String, Object> entry : sqlParam.entrySet()) {
            setHqlParam(query, entry.getKey(), entry.getValue());
        }
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public List<Mood> selectMoodByMoodParam(MoodParam param) {

        StringBuilder hqlBuilder = new StringBuilder("select mood from Mood mood where mood.deleted = :deleted");
        Map<String, Object> sqlParam = new HashMap<>();
        sqlParam.put("deleted", false);
        if (param != null) {
            //ID
            if (param.getId() != null) {
                hqlBuilder.append(" and mood.id = :id");
                sqlParam.put("id", param.getId());
            }
            //Username
            if (param.getUsername() != null) {
                hqlBuilder.append(" and mood.owner.username = :username");
                sqlParam.put("username", param.getUsername());
            }
            if (param.getOrderBy() != null && param.getOrderBy().size() > 0) {
                hqlBuilder.append(" order by ");
                param.getOrderBy().forEach(column -> {
                    hqlBuilder.append(column.getKey());
                    hqlBuilder.append(" ");
                    hqlBuilder.append(column.getValue());
                    hqlBuilder.append(",");
                });
                hqlBuilder.deleteCharAt(hqlBuilder.length() - 1);
            }
        }
        Query query = getCurrentSession().createQuery(hqlBuilder.toString());
        for (Map.Entry<String, Object> entry : sqlParam.entrySet()) {
            setHqlParam(query, entry.getKey(), entry.getValue());
        }
        if (param != null) {
            query.setFirstResult((param.getCurrentPage() - 1) * param.getPageSize());
            query.setMaxResults(param.getPageSize());
        }
        return query.list();
    }
}

package com.ylzinfo.model.his.mz.bgddy.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class HibernateQueryResultDataSource
    implements JRDataSource
{
    private ServletContext context;
    private List columnList;
    private String fields[];
    private Iterator iterator;
    private Object currentValue;

    public HibernateQueryResultDataSource(List list, String fields[])
    {
        this(list, fields, null, null);
    }

    public HibernateQueryResultDataSource(List list, String fields[], ServletContext context)
    {
        this(list, fields, context, null);
    }

    public HibernateQueryResultDataSource(List list, String fields[], ServletContext context, List columnList)
    {
        this.columnList = new ArrayList();
        this.context = context;
        this.fields = fields;
        iterator = list.iterator();
        this.columnList = columnList;
    }

    public Object getFieldValue(JRField field)
        throws JRException
    {
        Object value = null;
        int index = getFieldIndex(field.getName());
        if(index > -1)
        {
            Object values[] = (Object[])currentValue;
            value = values[index];
        }
//        return getCodeName(field.getName(), value);
        return value;
    }

    public boolean next()
        throws JRException
    {
        currentValue = iterator.hasNext() ? iterator.next() : null;
        return currentValue != null;
    }

    private int getFieldIndex(String field)
    {
        int index = -1;
        for(int i = 0; i < fields.length; i++)
        {
            if(!fields[i].equals(field))
                continue;
            index = i;
            break;
        }

        return index;
    }

    private Object getCodeName(String fieldName, Object value)
    {
        if(value == null)
            return "";
        if(isInEx(fieldName))
            return value;
        if(context == null)
            return value;
        if(!(value instanceof String))
            return value;
        TreeMap map = (TreeMap)context.getAttribute(fieldName.toUpperCase());
        if(map == null)
            return value;
        if(map.get(value) == null)
            return value;
        else
            return map.get(value);
    }

    private boolean isInEx(String fieldName)
    {
        if(columnList == null)
            return false;
        return columnList.contains(fieldName);
    }
}
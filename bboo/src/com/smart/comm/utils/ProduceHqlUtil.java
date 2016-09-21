package com.smart.comm.utils;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Id;

import com.smart.comm.entity.GjSaleOrderEntity;

public class ProduceHqlUtil {
	public static void main(String[] args) throws Exception {
		GjSaleOrderEntity gjSaleOrderEntity=new GjSaleOrderEntity();
		gjSaleOrderEntity.setId("id123456");
		gjSaleOrderEntity.setAccountid(123);
		gjSaleOrderEntity.setAccountInfo("");
		gjSaleOrderEntity.setIsSplit(false);
		System.out.println(getQueryByParamHql(gjSaleOrderEntity));
	}
	
	/**
	 * 传入一个对象实例，然后通过反射，
	 * 将对象中不为空值的属性作为要更新的字段，id作为条件
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getUpdateByIdHql(Object object) throws Exception{
		StringBuffer buffer=new StringBuffer();
		String conditionString="";
		Class<? extends Object> clazz=object.getClass();
		String clazzName=clazz.getName();
		buffer.append("update "+clazzName+" set ");
		Field[] fields=clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			Field field=fields[i];
			field.setAccessible(true);
			//System.out.println("将字段打印出来："+field);
			String fieldName=field.getName();
			Column column=field.getAnnotation(Column.class);
			Object fieldValue=field.get(object);
			if(column!=null&&fieldValue!=null){
				
				Id id=field.getAnnotation(Id.class);
				if(id!=null){
					conditionString=" where "+fieldName+"='"+fieldValue+"'";
				}else{
					if(column.columnDefinition()!=null&&column.columnDefinition().equalsIgnoreCase("timestamp")){
						int intValue=Integer.parseInt(((String) fieldValue).replaceAll("^0[x|X]", ""), 16);
						conditionString+=" and "+fieldName+"="+intValue;
					}else{
						Class<?> classType=field.getType();
						if(classType==String.class){
							buffer.append(fieldName+"='"+fieldValue+"',");
						}else{
							buffer.append(fieldName+"="+fieldValue+",");
						}
					}
				}
				
			}else{
				//不做任何处理。
			}
		}
		String hql=buffer.toString();
		return hql.substring(0, hql.length()-1)+conditionString;
	}
	
	public static String getQueryByParamHql(Object object) throws Exception{
		StringBuffer buffer=new StringBuffer();
		Class<? extends Object> clazz=object.getClass();
		String clazzName=clazz.getName();
		buffer.append("from "+clazzName+" where 1=1");
		Field[] fields=clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			Field field=fields[i];
			field.setAccessible(true);
			//System.out.println("将字段打印出来："+field);
			String fieldName=field.getName();
			Column column=field.getAnnotation(Column.class);
			Object fieldValue=field.get(object);
			if(column!=null&&fieldValue!=null){
				Class<?> classType=field.getType();
				if(classType==String.class){
					buffer.append(" and "+fieldName+"='"+fieldValue+"'");
				}else{
					buffer.append(" and "+fieldName+"="+fieldValue+"");
				}
			}else{
				//不做任何处理。
			}
		}
		String hql=buffer.toString();
		return hql;
	}
}

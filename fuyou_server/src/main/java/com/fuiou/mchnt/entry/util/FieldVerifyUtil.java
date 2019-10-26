package com.fuiou.mchnt.entry.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fuiou.mchnt.entry.annotation.Check;
import com.fuiou.mchnt.entry.dto.BaseReqDto;
import com.fuiou.mchnt.entry.exception.FieldException;

/**
 * 
 * @project fzgWx
 * @description 
 * @author abel.li
 * @creation 2016年12月9日
 * @email 
 * @version
 */
public class FieldVerifyUtil {

	/**
	 * 检查非空及数据格式
	 * @param dto
	 * @attention 该方法 仅用于 dto中数据类型全为string且getter方法名为get{Field}或get{field}的情况
	 * @throws FieldException
	 */
	public static void checkField(BaseReqDto dto) throws FieldException, Exception{
		Field[] fields = dto.getClass().getDeclaredFields(); 
		for(Field field : fields){
			Check annotation = field.getAnnotation(Check.class);
			if(annotation != null){
				Method getMethod = checkGetMethod(dto, field);
				String value = (String)getMethod.invoke(dto);
				String zh = annotation.zh();
				String retName = StringUtils.isNotEmpty(zh) ? zh : field.getName();
				if(annotation.require()){
					if(StringUtils.isEmpty(value)){
						throw new FieldException("请求数据验证失败:" + retName + "不能为空！");
					}
				}
				if(StringUtils.isNotEmpty(annotation.regex())){
					Pattern pat = Pattern.compile(annotation.regex());
					Matcher mat = pat.matcher(value);
					if(!mat.matches()){
						throw new FieldException("请求数据验证失败:" + retName + "格式不符合！");
					}
				}
				if(annotation.minlength() > 0){
					if(value.length() < annotation.minlength() ){
						throw new FieldException("请求数据验证失败:" + retName + "长度不能小于" +annotation.minlength()+ "位！");
					}
				}
				if(annotation.maxlength() > 0){
					if(value.length() > annotation.maxlength()){
						throw new FieldException("请求数据验证失败:" + retName + "长度不能大于"+annotation.maxlength()+"位！");
					}
				}
			}
		}
	}

	/**
	 * 获取get方法
	 * @param dto
	 * @param field
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static Method checkGetMethod(BaseReqDto dto, Field field) throws NoSuchMethodException, SecurityException {
		Method method = dto.getClass().getDeclaredMethod("get" + upperStart(field.getName()));
		if(method != null){
			return method;
		} else {
			return dto.getClass().getDeclaredMethod("get" + field.getName());
		}
	}
	
	/**
	 * 首字母大写
	 * @param name
	 * @return
	 */
	public static String upperStart(String name){
		char firstChar = name.charAt(0);
		if ((firstChar >= 'a') && (firstChar <= 'z')) {
			char[] arr = name.toCharArray();
			int tmp25_24 = 0;
			char[] tmp25_23 = arr; tmp25_23[tmp25_24] = (char)(tmp25_23[tmp25_24] - ' ');
			return new String(arr);
		}
		return name;
	}
	
}

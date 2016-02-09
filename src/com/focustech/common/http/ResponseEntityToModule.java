package com.focustech.common.http;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**********************************************************
 * @�ļ����ƣ�ResponseEntityToModule.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2013-10-24 ����03:16:56
 * @�ļ�������ת��JSON��ʽ����Ϊ�Զ�������ģ��
 * @�޸���ʷ��2013-10-24������ʼ�汾
 **********************************************************/
public class ResponseEntityToModule
{
	/**
	 * ����JSON��ʽ���ַ�����ͨ�������ֵ���õ�����ģ���ж�Ӧ������
	 * @param jsonContent
	 * @param clazz
	 * @return ����Class���ʵ��������
	 */
	public static Object parseJsonToModule(String jsonContent, Class<?> clazz)
	{
		Object moduleObj = null;
		try
		{
			JSONObject jsonObj = new JSONObject(jsonContent);
			moduleObj = parseJsonObjectToModule(jsonObj, clazz);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return moduleObj;
	}

	/**
	 * ����JSON����ͨ�������ֵ���õ�����ģ���ж�Ӧ������
	 * @param jsonObj
	 * @param clazz
	 * @return ����Class���ʵ��������
	 */
	public static Object parseJsonObjectToModule(JSONObject jsonObj, Class<?> clazz)
	{
		Object moduleObj = null;
		try
		{
			moduleObj = (Object) clazz.newInstance();
			setFieldValue(moduleObj, jsonObj, clazz);
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return moduleObj;
	}

	/**
	 * ��������ֵ(�ݹ鸸��������е����ж���)
	 * @param moduleObj
	 * @param jsonObj
	 * @param clazz
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws JSONException
	 * @throws InstantiationException
	 */
	private static void setFieldValue(Object moduleObj, JSONObject jsonObj, Class<?> clazz)
			throws IllegalArgumentException, IllegalAccessException, JSONException, InstantiationException
	{
		if (clazz.getSuperclass() != null)
		{
			setFieldValue(moduleObj, jsonObj, clazz.getSuperclass());
		}
		Field[] fields = clazz.getDeclaredFields();
		Class<?> cls;
		String name;
		for (Field f : fields)
		{
			f.setAccessible(true);
			cls = f.getType();
			name = f.getName();
			if (!jsonObj.has(name) || jsonObj.isNull(name))
			{
				continue;
			}
			if (cls.isPrimitive() || isWrappedPrimitive(cls))// ����ǻ�������boolean,byte,char,short,int,long,float,double,void
			{
				setPrimitiveFieldValue(f, moduleObj, jsonObj.get(name));
			}
			else
			{
				if (cls.isAssignableFrom(String.class))
				{
					f.set(moduleObj, jsonObj.getString(name));
				}
				else if (cls.isAssignableFrom(ArrayList.class))
				{
					parseJsonArrayToList(f, name, moduleObj, jsonObj);
				}
				else if (cls.getName().equals(Map.class.getName()))
				{
					parseJsonToMap(f, name, moduleObj, jsonObj);
				}
				else
				{
					Object obj = parseJsonObjectToModule(jsonObj.getJSONObject(name), cls.newInstance().getClass());
					f.set(moduleObj, obj);
				}
			}
		}
	}

	private static ArrayList<Object> parseJsonArrayToList(Field field, String fieldName, Object moduleObj,
			JSONObject jsonObj) throws JSONException, IllegalArgumentException, IllegalAccessException
	{
		ArrayList<Object> objList = new ArrayList<Object>();
		Type fc = field.getGenericType();
		if (fc instanceof ParameterizedType)// �Ƿ���
		{
			ParameterizedType pt = (ParameterizedType) fc;
			if (pt.getActualTypeArguments()[0] instanceof Class)// ��ָ������,����"?"
			{
				Class<?> clss = (Class<?>) pt.getActualTypeArguments()[0];

				if (jsonObj.get(fieldName) instanceof JSONArray)
				{
					JSONArray array = jsonObj.getJSONArray(fieldName);
					for (int i = 0; i < array.length(); i++)
					{
						if (array.get(i) instanceof JSONObject)
						{
							objList.add(parseJsonObjectToModule(array.getJSONObject(i), clss));
						}
						else
						{
							if (clss.isAssignableFrom(array.get(i).getClass()))
							{
								objList.add(array.get(i));
							}
						}
					}
				}
				field.set(moduleObj, objList);
			}
		}
		return objList;
	}

	/**
	 * ����ģ����ԭʼ���͵�ֵ
	 * @param field
	 * @param moduleObj
	 * @param jsonObj
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws NumberFormatException
	 */
	private static void setPrimitiveFieldValue(Field field, Object moduleObj, Object jsonObj)
			throws IllegalArgumentException, IllegalAccessException
	{
		// ģ�������Ե�����������Դ�е�����������ͬ
		if (field.getType().isAssignableFrom(jsonObj.getClass()))
		{
			field.set(moduleObj, jsonObj);
		}
		else
		{
			field.set(moduleObj, makeTypeSafeValue(field.getType(), jsonObj.toString()));

		}
	}

	/**
	 * �������ͷ��ض�Ӧ���͵�ֵ
	 * @param type
	 * @param value
	 * @return
	 * @throws NumberFormatException
	 * @throws IllegalArgumentException
	 */
	private static final Object makeTypeSafeValue(Class<?> type, String value) throws NumberFormatException,
			IllegalArgumentException
	{
		if (int.class == type || Integer.class == type)
		{
			return Integer.parseInt(value);
		}
		else if (long.class == type || Long.class == type)
		{
			return Long.parseLong(value);
		}
		else if (short.class == type || Short.class == type)
		{
			return Short.parseShort(value);
		}
		else if (char.class == type || Character.class == type)
		{
			return value.charAt(0);
		}
		else if (byte.class == type || Byte.class == type)
		{
			return Byte.valueOf(value);
		}
		else if (float.class == type || Float.class == type)
		{
			return Float.parseFloat(value);
		}
		else if (double.class == type || Double.class == type)
		{
			return Double.parseDouble(value);
		}
		else if (boolean.class == type || Boolean.class == type)
		{
			return Boolean.valueOf(value);
		}
		else
		{
			return value;
		}
	}

	/**
	 * �Ƿ�Ϊ��װ����ԭʼ����
	 * @param type
	 * @return
	 */
	private static boolean isWrappedPrimitive(Class<?> type)
	{
		if (type.getName().equals(Boolean.class.getName()) || type.getName().equals(Byte.class.getName())
				|| type.getName().equals(Character.class.getName()) || type.getName().equals(Short.class.getName())
				|| type.getName().equals(Integer.class.getName()) || type.getName().equals(Long.class.getName())
				|| type.getName().equals(Float.class.getName()) || type.getName().equals(Double.class.getName()))
		{
			return true;
		}
		return false;
	}

	public static Map<String, Object> parseJsonToMap(Field field, String fieldName, Object moduleObj, JSONObject jsonObj)
			throws JSONException, IllegalAccessException, IllegalArgumentException
	{
		Map<String, Object> map = new HashMap<String, Object>();

		if (jsonObj != JSONObject.NULL)
		{
			String strJson = null;
			JSONObject object = null;
			if (jsonObj.get(fieldName) instanceof String)
			{
				strJson = jsonObj.optString(fieldName, null);
				if (strJson != null)
				{
					object = new JSONObject(strJson);
				}
			}
			else if (jsonObj.get(fieldName) instanceof JSONObject)
			{
				object = jsonObj.getJSONObject(fieldName);
			}

			Iterator<String> keysItr = object.keys();
			while (keysItr.hasNext())
			{
				String key = keysItr.next();
				Object value = object.get(key);

				if (value instanceof String)
				{
					value = String.valueOf(value);
				}
				map.put(key, value);
			}

			field.set(moduleObj, map);

		}
		return map;
	}

}

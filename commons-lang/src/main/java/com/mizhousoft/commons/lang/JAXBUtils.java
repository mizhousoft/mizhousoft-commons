package com.mizhousoft.commons.lang;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mizhousoft.commons.data.NestedRuntimeException;

/**
 * JAXB工具类
 *
 * @version
 */
public class JAXBUtils
{
	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String xml, Class<T> clazz)
	{
		T t = null;

		try
		{
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		}
		catch (Exception e)
		{
			throw new NestedRuntimeException("XML to bean failed.", e);
		}

		return t;
	}

	public static String beanToXml(Object object, Class<?> load)
	{
		String xmlString = "";

		try
		{
			JAXBContext context = JAXBContext.newInstance(load);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			StringWriter sw = new StringWriter();
			marshaller.marshal(object, sw);
			xmlString = sw.toString();
		}
		catch (JAXBException e)
		{
			throw new NestedRuntimeException("Bean to xml failed.", e);
		}

		return xmlString;
	}
}

package com.fuiou.mchnt.entry.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * @project entry
 * @description 
 * @author abel.li
 * @creation 2017年3月14日
 * @email 
 * @version
 */
public class JaxbUtil {

    public static <T> String toXml(T xmlBean,Class<T> xmlClass, String encode) throws JAXBException{
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JAXBContext jc = JAXBContext.newInstance(xmlClass);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encode);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        marshaller.marshal(xmlBean, output);
        String xml = output.toString();
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            return xml;
        }
        return xml;
    }

    public static <T> T readString(Class<T> clazz, String context) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(new File(context));
        } catch (JAXBException e) {
            // logger.trace(e);
            throw e;
        }
    }
}

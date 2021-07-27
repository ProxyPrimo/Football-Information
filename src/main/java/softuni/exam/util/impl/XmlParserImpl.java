package softuni.exam.util.impl;

import softuni.exam.util.XmlParser;

import javax.xml.bind.*;
import java.io.File;

public class XmlParserImpl implements XmlParser {
    @Override
    @SuppressWarnings("unchecked")
    public <T> T parseXml(Class<T> objectClass, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new File(filePath));
    }

    @Override
    public <T> void exportXml(T object, Class<T> objectClass, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, new File(filePath));
    }
}

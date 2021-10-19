package gr.nikolis.parsers.strategy;

import gr.nikolis.parsers.enums.ParserName;
import gr.nikolis.parsers.factory.Parser;
import gr.nikolis.parsers.xml.Students;
import gr.nikolis.parsers.xml.Xml;
import gr.nikolis.parsers.xml.XmlModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyXmlParser extends Parser {
    @Autowired
    private Xml xml;

    @Override
    public void run() {
        XmlModel xmlModel = xml.toObjectFromFile(XmlModel.class, "files/username.xml");
        System.out.println(xmlModel);
        Students student = xml.toObjectFromFile(Students.class, "files/students.xml");
        String studentStr = xml.toXmlString(student);
        System.out.println(studentStr);
    }

    @Override
    protected ParserName getParserName() {
        return ParserName.Xml;
    }
}
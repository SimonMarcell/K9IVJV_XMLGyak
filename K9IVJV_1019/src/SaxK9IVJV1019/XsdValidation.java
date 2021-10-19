package SaxK9IVJV1019;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XsdValidation {

    public static void main(String[] args) throws Exception {

        if(validateAgainstXSD(new File("src/szemelyekK9IVJV.xml")))
        {
            System.out.println("Successful validation!");
        }
        else
        {
            System.out.println("Unsuccessful validation!!");
        }
    }
    
    static boolean validateAgainstXSD(File xml){
        try{

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource("src/szemelyekK9IVJV.xsd"));

            Validator validator  = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        }catch(Exception exe){
            exe.printStackTrace();
        return false;
        }
    }

}
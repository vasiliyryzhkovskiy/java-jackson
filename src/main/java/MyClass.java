
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MyClass {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            // JSON string to Java object
            String jsonInString = "{\"name\":\"someTest\",\"localDate\":\"2021-03-13\"}";
            TipaJson tipaJson = mapper.readValue(jsonInString, TipaJson.class);
            String tipaJsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tipaJson);
            System.out.println("tipaJson = " + tipaJson);
            System.out.println("tipaJsonPretty = " + tipaJsonPretty);
            System.out.println("");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

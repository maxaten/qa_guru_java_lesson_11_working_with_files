package tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.models.GlossaryModel;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileParsingTest {
    private final ClassLoader cl = FileParsingTest.class.getClassLoader();
    private final Gson gson = new Gson();





    @Test
    void csvTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("test.csv")) {
            Reader reader = new InputStreamReader(stream);
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> content = csvReader.readAll();

            Assertions.assertEquals(3, content.size());

            final String[] firstString = content.get(0);
            final String[] secondString = content.get(1);
            final String[] thirdString = content.get(2);

            Assertions.assertArrayEquals(new String[]{"Pushkin", "Aleksandr"}, firstString);
            Assertions.assertArrayEquals(new String[]{"Tolstoy", "Lev"}, secondString);
            Assertions.assertArrayEquals(new String[]{"Chehov", "Anton"}, thirdString);
        }
    }

    @Test
    void zipTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("file.zip");
            ZipInputStream zip = new ZipInputStream(stream)) {

            ZipEntry entry;
                while((entry = zip.getNextEntry()) !=null){
                    final String name = entry.getName();
                    Assertions.assertTrue(name.contains("test.csv"));
                }
        }
    }

    @Test
    void jsonTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("spiderman.json");
             Reader reader = new InputStreamReader(stream)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            Assertions.assertEquals("Человек-Паук",
                    jsonObject.get("superhero")
                            .getAsJsonObject().get("name")
                            .getAsString());
        }
    }

    @Test
    void json2Test() throws Exception {

        try (InputStream stream = cl.getResourceAsStream("glossary.json");
             Reader reader = new InputStreamReader(stream)) {
             GlossaryModel glossary = gson.fromJson(reader, GlossaryModel.class);

            Assertions.assertEquals("example glossary", glossary.getTitle());
            Assertions.assertEquals("S", glossary.getGlossDiv().getTitle());
            Assertions.assertTrue(glossary.getGlossDiv().isFlag());

        }
    }
}
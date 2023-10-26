package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



public class FileTest extends TestBase{


    @Test
    void downloadTxtFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");

        File downloaded = $("[data-testid='raw-button']").download();
        try (InputStream is = new FileInputStream(downloaded)){
            byte[] bytes = is.readAllBytes();
            String content = new String(bytes, StandardCharsets.UTF_8);
            Assertions.assertTrue(content.contains("This repository is the home of _JUnit 5_."));
        }
    }

    @Disabled
    @Test
    void uploadFileTest() throws Exception {
        open("https://demoqa.com/automation-practice-form");

        File file = new File("src/test/resources/files/avatar_cat.jpg");

        $("input[type='file']").uploadFromClasspath("files/avatar_cat.jpg");
        $(".practice-form-wrapper").shouldHave(text("avatar_cat.jpg"));

        System.out.println();
    }

    @Test
    void downloadPdfFileTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded = $("a[href*='junit-user-guide-5.10.0.pdf']").download();

        PDF pdf = new PDF(downloaded);
        Assertions.assertEquals("JUnit 5 User Guide", pdf.title);

    }

    @Test
    void downloadXlsFileTest() throws Exception  {
        open("https://ruexcel.ru/goodfiles-1/");

        File downloaded = $("a[href*='Excel.xlsx']").download();
        XLS xls = new XLS(downloaded);
        Assertions.assertEquals("Наименование товара (работ, услуг)",
                xls.excel.getSheetAt(0)
                        .getRow(13).
                        getCell(1).
                        getStringCellValue());

        System.out.println();
    }
}

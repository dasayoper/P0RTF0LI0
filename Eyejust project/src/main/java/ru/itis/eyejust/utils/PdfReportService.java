package ru.itis.eyejust.utils;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.eyejust.dto.ReportFormDto;
import ru.itis.eyejust.models.FileInfo;
import ru.itis.eyejust.models.UserEntity;
import ru.itis.eyejust.repositories.mongo.FileInfoRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PdfReportService {

    private final FileInfoRepository fileInfoRepository;

    public byte[] createPdf(ReportFormDto reportFormDto, UserEntity user) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        String regularArialFontPath = "static/fonts/arial/arial.ttf";
        String boldArialFontPath = "static/fonts/arial/arialbd.ttf";

        PdfFont regularArialFont = PdfFontFactory.createFont(regularArialFontPath, PdfEncodings.IDENTITY_H);
        PdfFont boldArialFont = PdfFontFactory.createFont(boldArialFontPath, PdfEncodings.IDENTITY_H);

        document.setFont(regularArialFont);

        Style styleBold = new Style()
                .setFont(boldArialFont);

        Style styleRegular = new Style()
                .setFont(regularArialFont);

        //Врач-офтальмолог, медицинское учреждение, профиль, дата осмотра
        String date = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new java.util.Date());

        Paragraph firstParagraph = new Paragraph()
                .add(new Text(reportFormDto.getMedicalInstitution() + "\n").addStyle(styleBold))
                .add(new Text("Врач-офтальмолог: ").addStyle(styleBold))
                .add(new Text(reportFormDto.getTherapist()  + "\n").addStyle(styleRegular))
                .add(new Text("Профиль: ").addStyle(styleBold))
                .add(new Text("офтальмология\n").addStyle(styleRegular))
                .add(new Text("Дата и время осмотра: ").addStyle(styleBold))
                .add(new Text(date).addStyle(styleRegular));

        document.add(firstParagraph);
        document.add(new Paragraph().setHeight(20f));

        //Данные пациента
        Paragraph patientInfoParagraph = new Paragraph()
                .add(new Text("Данные пациента\n").addStyle(styleBold))
                .add(new Text("Ф.И.О.: " + user.getLastName() + " " + user.getFirstName() + " " + user.getPatronymic() + "\n"))
                .add(new Text(
                        "Дата рождения: " + (LocalDate.parse(user.getBirthdate(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd"))).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        + "(" + calculateUserAge(user.getBirthdate()) + " лет)\n"))
                .add(new Text("Пол: " + user.getGender() + "\n"))
                .add(new Text("Адрес проживания: " + user.getAddress()));

        document.add(patientInfoParagraph);
        document.add(new Paragraph().setHeight(20f));

        //Жалобы
        Paragraph complaintsParagraph = new Paragraph()
                .add(new Text("Жалобы\n").addStyle(styleBold))
                .add(new Text(reportFormDto.getComplaints()).addStyle(styleRegular));

        document.add(complaintsParagraph);
        document.add(new Paragraph().setHeight(20f));

        //Анамнез жизни
        Paragraph lifeAnamnesisParagraph = new Paragraph()
                .add(new Text("Анамнез жизни\n").addStyle(styleBold))
                .add(new Text("Перенесенные заболевания: " + user.getPastIllnesses() + "\n"))
                .add(new Text("Перенесенные операции: " + user.getSurgeries() + "\n"))
                .add(new Text("Хронические заболевания: " + user.getChronicDiseases() + "\n"))
                .add(new Text("Лекарственная непереносимость: " + user.getDrugIntolerance() + "\n"))
                .add(new Text("Вредные привычки: " + user.getBadHabits() + "\n"));

        document.add(lifeAnamnesisParagraph);
        document.add(new Paragraph().setHeight(20f));

        // Анамнез заболевания
        Paragraph diseaseAnamnesisParagraph = new Paragraph()
                .add(new Text("Анамнез заболевания\n").addStyle(styleBold))
                .add(new Text(reportFormDto.getDiseaseAnamnesis()));

        document.add(diseaseAnamnesisParagraph);
        document.add(new Paragraph().setHeight(20f));

        // Объективный статус
        Paragraph objectiveStatusParagraph = new Paragraph()
                .add(new Text("Объективный статус\n").addStyle(styleBold))
                .add(new Text(reportFormDto.getObjectiveStatus()));

        document.add(objectiveStatusParagraph);
        document.add(new Paragraph().setHeight(20f));

        // Локальный статус
        Paragraph localStatusParagraph = new Paragraph()
                .add(new Text("Локальный статус\n").addStyle(styleBold))
                .add(new Text(reportFormDto.getLocalStatus()));

        document.add(localStatusParagraph);
        document.add(new Paragraph().setHeight(20f));

        // Диагноз
        Paragraph diagnosisParagraph = new Paragraph()
                .add(new Text("Диагноз\n").addStyle(styleBold))
                .add(new Text(reportFormDto.getDiagnosis()));

        document.add(diagnosisParagraph);
        document.add(new Paragraph().setHeight(20f));

        // Заключение
        Paragraph conclusionParagraph = new Paragraph()
                .add(new Text("Заключение\n").addStyle(styleBold))
                .add(new Text(reportFormDto.getConclusion()));

        document.add(conclusionParagraph);
        document.add(new Paragraph().setHeight(20f));

        // Рекомендации, назначения
        Paragraph recommendationsParagraph = new Paragraph()
                .add(new Text("Рекомендации, назначения\n").addStyle(styleBold))
                .add(new Text(reportFormDto.getRecommendations()));

        document.add(recommendationsParagraph);
        document.add(new Paragraph().setHeight(20f));

        Table imageTable = new Table(2);
        imageTable.setWidth(pdfDocument.getDefaultPageSize().getWidth() * 0.9f);
        imageTable.setHorizontalAlignment(HorizontalAlignment.LEFT);

        imageTable.addCell("Левый глаз");
        imageTable.addCell("Правый глаз");

        // Фундус-снимки и вероятность глаукомы
        FileInfo leftEyeOrigImage = fileInfoRepository.findById(reportFormDto.getLeftEyeImageId()).orElseThrow();
        FileInfo rightEyeOrigImage = fileInfoRepository.findById(reportFormDto.getRightEyeImageId()).orElseThrow();

        imageTable.addCell("Исходное изображение");
        imageTable.addCell("Исходное изображение");

        imageTable.addCell(
                new Image(ImageDataFactory.create(leftEyeOrigImage.getData().getData())).scaleToFit(240, 240));
        imageTable.addCell(
                new Image(ImageDataFactory.create(rightEyeOrigImage.getData().getData())).scaleToFit(240, 240));

        imageTable.addCell("Вероятность глаукомы:\n" + reportFormDto.getLeftEyeDiagnosticValue());
        imageTable.addCell("Вероятность глаукомы:\n" + reportFormDto.getRightEyeDiagnosticValue());

        // Обрезанные изображения
        FileInfo leftEyeCroppedImage = fileInfoRepository.findById(reportFormDto.getLeftEyeCroppedImageId()).orElseThrow();
        FileInfo rightEyeCroppedImage = fileInfoRepository.findById(reportFormDto.getRightEyeCroppedImageId()).orElseThrow();

        imageTable.addCell("Область оптического диска");
        imageTable.addCell("Область оптического диска");

        imageTable.addCell(
                new Image(ImageDataFactory.create(leftEyeCroppedImage.getData().getData())).scaleToFit(180,  180));
        imageTable.addCell(
                new Image(ImageDataFactory.create(rightEyeCroppedImage.getData().getData())).scaleToFit(180, 180));

        // Маски OD, OC и CDR
        FileInfo leftEyeMaskImage = fileInfoRepository.findById(reportFormDto.getLeftEyeMaskImageId()).orElseThrow();
        FileInfo rightEyeMaskImage = fileInfoRepository.findById(reportFormDto.getRightEyeMaskImageId()).orElseThrow();

        imageTable.addCell("Маска Optic Cup, Optic Disk");
        imageTable.addCell("Маска Optic Cup, Optic Disk");

        imageTable.addCell(
                new Image(ImageDataFactory.create(leftEyeMaskImage.getData().getData())).scaleToFit(180, 180));
        imageTable.addCell(
                new Image(ImageDataFactory.create(rightEyeMaskImage.getData().getData())).scaleToFit(180, 180));

        imageTable.addCell("CDR:\n" + reportFormDto.getLeftEyeCDR());
        imageTable.addCell("CDR:\n" + reportFormDto.getRightEyeCDR());

        document.add(imageTable);

        document.close();
        return outputStream.toByteArray();
    }

    public String calculateUserAge(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(birthdate, formatter);

        LocalDate currentDate = LocalDate.now();

        return String.valueOf(Period.between(birthday, currentDate).getYears());
    }
}

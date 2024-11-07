<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница с формой для добавления нового отчета">
    <meta name="author" content="Сабитов Тимур">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Панель создания отчета</title>

    <#include "components/links.ftl">
</head>
<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1>Добавить отчет</h1>
        <form action="/reports" method="post" class="base-form">
            <input type="hidden" name="leftEyeImageId" value="${leftEyeImageId}">
            <input type="hidden" name="rightEyeImageId" value="${rightEyeImageId}">
            <input type="hidden" name="leftEyeDiagnosticValue" value="${leftEyeDiagnosticValue}">
            <input type="hidden" name="rightEyeDiagnosticValue" value="${rightEyeDiagnosticValue}">
            <input type="hidden" name="leftEyeCroppedImageId" value="${leftEyeCroppedImageId}">
            <input type="hidden" name="rightEyeCroppedImageId" value="${rightEyeCroppedImageId}">
            <input type="hidden" name="leftEyeMaskImageId" value="${leftEyeMaskImageId}">
            <input type="hidden" name="rightEyeMaskImageId" value="${rightEyeMaskImageId}">
            <input type="hidden" name="leftEyeCDR" value="${leftEyeCDR}">
            <input type="hidden" name="rightEyeCDR" value="${rightEyeCDR}">
            <div>
                <h3>Врач-офтальмолог</h3>
                <input type="text" class="input-form-wide" id="therapist" name="therapist" placeholder="Укажите врача-офтальмолога" maxlength="100" required>
            </div>
            <div>
                <h3>Медицинское учреждение</h3>
                <input type="text" class="input-form-wide" id="medicalInstitution" name="medicalInstitution" placeholder="Укажите медицинское учреждение" maxlength="100" required>
            </div>
            <div>
                <h3>Жалобы</h3>
                <textarea class="input-form-wide-textarea" id="complaints" name="complaints" rows="2" placeholder="Укажите жалобы пациента" maxlength="1500" required></textarea>
            </div>
            <div>
                <h3>Анамнез заболевания</h3>
                <textarea class="input-form-wide-textarea" id="diseaseAnamnesis" name="diseaseAnamnesis" rows="2" placeholder="Укажите анамнез заболевания пациента" maxlength="1500" required></textarea>
            </div>
            <div>
                <h3>Объективный статус</h3>
                <textarea class="input-form-wide-textarea" id="objectiveStatus" name="objectiveStatus" rows="2" placeholder="Укажите объективный статус" maxlength="1500" required></textarea>
            </div>
            <div>
                <h3>Локальный статус</h3>
                <textarea class="input-form-wide-textarea" id="localStatus" name="localStatus" rows="2" placeholder="Укажите локальный статус" maxlength="1500" required></textarea>
            </div>
            <div>
                <h3>Диагноз</h3>
                <textarea class="input-form-wide-textarea" id="diagnosis" name="diagnosis" rows="2" placeholder="Укажите поставленный диагноз" maxlength="1500" required></textarea>
            </div>
            <div>
                <h3>Заключение</h3>
                <textarea class="input-form-wide-textarea" id="conclusion" name="conclusion" rows="2" placeholder="Укажите медицинское заключение" maxlength="1500" required></textarea>
            </div>
            <div>
                <h3>Рекомендации, назначения</h3>
                <textarea class="input-form-wide-textarea" id="recommendations" name="recommendations" rows="2" placeholder="Укажите рекомендации и назначения" maxlength="1500" required></textarea>
            </div>
            <input class="base-form-btn" type="submit" value="Сохранить">
        </form>
        <br>
    </div>
    <br>
</main>
</body>
<br>
</html>
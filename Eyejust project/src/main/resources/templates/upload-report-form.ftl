<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница с формой для загрузки отчета с устройства">
    <meta name="author" content="Сабитов Тимур">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Панель загрузки отчета</title>
    <#include "components/links.ftl">
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var inputFile = document.getElementById('input__file');
            var fileButtonText = document.querySelector('.input__file-button-text');

            inputFile.addEventListener('change', function () {
                if (inputFile.files.length > 0) {
                    fileButtonText.innerText = inputFile.files[0].name;
                } else {
                    fileButtonText.innerText = 'Загрузить изображение';
                }
            });
        });
    </script>
</head>
<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1>Загрузить отчет</h1>
        <form action="/reports/upload" method="post" enctype="multipart/form-data" class="base-form">
            <input type="hidden" name="leftEyeImageDBId" value="no-image">
            <input type="hidden" name="rightEyeImageDBId" value="no-image">
            <input type="hidden" name="leftEyeDiagnosticValue" value="0.0">
            <input type="hidden" name="rightEyeDiagnosticValue" value="0.0">
            <input type="hidden" name="leftEyeCDR" value="0.0">
            <input type="hidden" name="rightEyeCDR" value="0.0">
            <div>
                <h3>Дата осмотра</h3>
                <input type="date" class="input-form" id="creationDate" name="creationDate"
                       min="2000-01-01" required>
            </div>
            <div class="input__wrapper input-align">
                <input name="reportFile" type="file" id="input__file" class="input input__file" accept=".pdf"
                       required>
                <label for="input__file" class="input__file-button">
                    <span class="input__file-button-text">Выберите файл</span>
                </label>
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
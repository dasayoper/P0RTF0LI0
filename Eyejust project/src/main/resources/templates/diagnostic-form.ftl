<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница диагностики">
    <meta name="author" content="Сабитов Тимур">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Отправка на диагностику</title>
    <#include "components/links.ftl">
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var inputFiles = document.querySelectorAll('.input__file');

            inputFiles.forEach(function(inputFile) {
                var fileButtonText = inputFile.closest('.input__wrapper').querySelector('.input__file-button-text');

                inputFile.addEventListener('change', function () {
                    if (inputFile.files.length > 0) {
                        fileButtonText.innerText = inputFile.files[0].name;
                    } else {
                        fileButtonText.innerText = 'Загрузить изображение';
                    }
                });
            });
        });
    </script>
</head>
<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1>Загрузить изображения</h1>
        <form method="post" action="/diagnostic" enctype="multipart/form-data" class="base-form">
            <div class="input__wrapper input-align">
                <input name="file1" type="file" id="input__file1" class="input input__file" accept=".jpg, .jpeg" required>
                <label for="input__file1" class="input__file-button">
                    <span class="input__file-button-text">Загрузить изображение левого глаза</span>
                </label>
            </div>
            <div class="input__wrapper input-align">
                <input name="file2" type="file" id="input__file2" class="input input__file" accept=".jpg, .jpeg" required>
                <label for="input__file2" class="input__file-button">
                    <span class="input__file-button-text">Загрузить изображение правого глаза</span>
                </label>
            </div>
            <input class="base-form-btn" type="submit" value="Отправить на диагностику">
        </form>
    </div>
</main>
</body>
</html>
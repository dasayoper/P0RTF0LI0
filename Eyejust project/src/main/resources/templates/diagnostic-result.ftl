<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница с результами диагностики">
    <meta name="author" content="Сабитов Тимур">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Результаты диагностики</title>

    <#include "components/links.ftl">
</head>
<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1>Результат диагностики</h1>
        <div class="profile-info">
            <p>Вероятность глаукомы для левого глаза:&nbsp;</p>
            <p class="profile-text">${leftEyeDiagnosticValue}</p>
        </div>
        <div class="profile-info">
            <p>Вероятность глаукомы для правого глаза:&nbsp;</p>
            <p class="profile-text">${rightEyeDiagnosticValue}</p>
        </div>
        <div class="profile-info">
            <p>Отношение OC к OD (CDR) для левого глаза:&nbsp;</p>
            <p class="profile-text">${leftEyeCDR}</p>
        </div>
        <div class="profile-info">
            <p>Отношение OC к OD (CDR) для правого глаза:&nbsp;</p>
            <p class="profile-text">${rightEyeCDR}</p>
        </div>
        <form action="/reports" method="get">
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
            <button type="submit" class="btn">Создать отчет</button>
        </form>
        <br>
    </div>
</main>
</body>
</html>
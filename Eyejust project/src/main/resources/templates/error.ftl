<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница с отображением ошибок">
    <meta name="author" content="Сабитов Тимур">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Ошибка</title>
    <#include "components/links.ftl">
</head>

<body>
<#include "components/header.ftl">
<main class="container">
    <div class="error-wrapper">
        <div class="error-content">
            <#if errorMessage??>
                <h1 class="error-title">Ошибка</h1>
                <p class="error-message">${errorMessage}</p>
            </#if>
        </div>
    </div>
</main>
</body>
</html>
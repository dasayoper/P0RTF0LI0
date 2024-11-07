<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница профиля">
    <meta name="author" content="Сабитов Тимур">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Профиль</title>

    <#include "components/links.ftl">
</head>
<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="profile-wrapper">
        <div class="profile-header">
            <h1>Профиль</h1>
            <a href="/profile/update" class="edit-profile-button">Редактировать</a>
        </div>
        <div class="profile-content">
            <div class="profile-info">
                <p>ФИО:&nbsp;</p>
                <p class="profile-text">${user.lastName} ${user.firstName} ${user.patronymic}</p>
            </div>
            <div class="profile-info">
                <p>Пол:&nbsp;</p>
                <p class="profile-text">${user.gender}</p>
            </div>
            <div class="profile-info">
                <p>Дата рождения:&nbsp;</p>
                <p class="profile-text">${user.birthdate}</p>
            </div>
            <div class="profile-info">
                <p>Адрес проживания:&nbsp;</p>
                <p class="profile-text">${user.address}</p>
            </div>
            <div class="profile-info">
                <p>Перенесенные заболевания:&nbsp;</p>
                <p class="profile-text">${user.pastIllnesses}</p>
            </div>
            <div class="profile-info">
                <p>Перенесенные операции:&nbsp;</p>
                <p class="profile-text">${user.surgeries}</p>
            </div>
            <div class="profile-info">
                <p>Хронические заболевания:&nbsp;</p>
                <p class="profile-text">${user.chronicDiseases}</p>
            </div>
            <div class="profile-info">
                <p>Лекарственная непереносимость:&nbsp;</p>
                <p class="profile-text">${user.drugIntolerance}</p>
            </div>
            <div class="profile-info">
                <p>Вредные привычки:&nbsp;</p>
                <p class="profile-text">${user.badHabits}</p>
            </div>
        </div>

        <#if user.state == "COMPLETED">
            <div class="btn-group">
                <a href="/diagnostic" class="base-form-btn">Диагностика</a>
                <a href="/reports/my-reports" class="base-form-btn">Мои отчеты</a>
            </div>
        <#else>
            <p class="edit-profile-text">Чтобы воспользоваться функционалом<a href="/profile/update" class="go-edit-profile">Заполните профиль</a></p>
        </#if>

        <br>
        <br>
    </div>

</main>
</body>
</html>

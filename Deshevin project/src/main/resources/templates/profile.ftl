<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Панель добавления лекарств">
    <meta name="author" content="М. Ильдар">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Профиль</title>

    <#include "components/links.ftl">

<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="profile-wrapper">
        <h1>Профиль</h1>
        <div class="profile-content">
            <div class="profile-info">
                <p>Имя пользователя:&nbsp;</p>
                <p class="profile-text">${user.username}</p>
            </div>
            <div class="profile-info">
                <p>Имя:&nbsp;</p>
                <p class="profile-text">${user.firstName}</p>
            </div>
            <div class="profile-info">
                <p>Фамилия:&nbsp;</p>
                <p class="profile-text">${user.lastName}</p>
            </div>
            <div class="profile-info">
                <p>Город:&nbsp;</p>
                <p class="profile-text">${user.city}</p>
            </div>
            <div class="profile-info">
                <p>Электронная почта:&nbsp;</p>
                <p class="profile-text">${user.email}</p>
            </div>
        </div>

        <#if user.role == 'COMMON_USER'>
            <div>
                <button onclick="location.href='/favourites';" class="btn" href="/search">Избранное</button>
            </div>
        </#if>

        <br>
        <br>
    </div>

</main>
</body>
</html>
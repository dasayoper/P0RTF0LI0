<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница с формой авторизации">
    <meta name="author" content="Сабитов Тимур">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Форма авторизации</title>

    <#include "components/links.ftl">

</head>
<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1>Вход</h1>
        <div class="base-form">
            <#if errorMessage??>
                <div class="error-message" role="alert">
                    ${errorMessage}
                </div>
            </#if>
            <form method="post" action="/sign-in" class="form-wrapper" enctype="multipart/form-data">
                <div>
                    <h3>Электронная почта</h3>
                    <input type="email" class="input-form" id="email" name="email" placeholder="Введите email" required>
                </div>
                <div>
                    <h3>Пароль</h3>
                    <input type="password" class="input-form" id="password" name="password" placeholder="Введите пароль" required>
                </div>
                <br>
                <br>
                <input class="btn" type="submit" value="Войти">
            </form>
            <p class="go-sign-up">Ещё нет аккаунта? <a href="/sign-up" class="go-edit-profile">Зарегистрироваться</a>
            </p>
        </div>
    </div>
</main>
</body>
</html>

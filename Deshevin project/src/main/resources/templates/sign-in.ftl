<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Панель добавления лекарств">
    <meta name="author" content="М. Ильдар">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Форма регистрации</title>

    <style>
        .sign-in-form {
            margin-left: auto;
            margin-right: auto;
            width: 50%;
        }

        .brd {
            border: 4px double black; /* Параметры границы */
            padding: 10px; /* Поля вокруг текста */
        }

    </style>


    <#include "components/links.ftl">

<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1>Вход</h1>
        <form method="post" action="/sign-in" class="form-wrapper" enctype="multipart/form-data">
            <div>
                <h3>Электронная почта</h3>
                <input type="email" class="input-form" id="email" name="email" required>
            </div>
            <div>
                <h3>Пароль</h3>
                <input type="password" class="input-form" id="password" name="password" required>
            </div>
            <br>
            <br>
            <input class="btn" type="submit" value="Войти">

        </form>

        <p class="go-sign-up">Ещё нет аккаунта? <a href="/sign-up" class="go-sign-up">Зарегистрироваться</a></p>
    </div>

</main>
</body>
</html>
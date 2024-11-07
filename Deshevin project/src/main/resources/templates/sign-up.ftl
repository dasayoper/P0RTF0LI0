<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Панель добавления лекарств">
    <meta name="author" content="М. Ильдар">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Форма регистрации</title>

<#--    <style>-->
<#--        .sign-up-form {-->
<#--            margin-left: auto;-->
<#--            margin-right: auto;-->
<#--            width: 50%;-->
<#--        }-->

<#--        .brd {-->
<#--            border: 4px double black; /* Параметры границы */-->
<#--            padding: 10px; /* Поля вокруг текста */-->
<#--        }-->

<#--    </style>-->


    <#include "components/links.ftl">

<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1>Регистрация</h1>
        <form method="post" action="/sign-up" class="form-wrapper" enctype="multipart/form-data">
            <div>
                <h3>Имя пользователя</h3>
                <input type="text" class="input-form" id="username" name="username" required>
            </div>
            <div>
                <h3>Имя</h3>
                <input type="text" class="input-form" id="firstName" name="firstName" required>
            </div>
            <div>
                <h3>Фамилия</h3>
                <input type="text" class="input-form" id="lastName" name="lastName" required>
            </div>
            <div>
                <h3>Город</h3>
                <input type="text" class="input-form" id="city" name="city" required>
            </div>
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
            <input class="btn btn-info" type="submit" value="Зарегистрироваться">

        </form>

        <#if errors??>
            <#list errors as error>
                <div class="text">Ошибка: ${error.name}</div>
                <br>
            </#list>
        </#if>

        <p class="go-sign-up">Есть аккаунт? <a href="/sign-in" class="go-sign-up">Войти</a></p>
    </div>


<#--    <#include "components/footer.ftl" >-->

</main>
</body>
</html>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница с формой регистрации">
    <meta name="author" content="Сабитов Тимур">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Форма регистрации</title>

    <#include "components/links.ftl">

<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1>Регистрация</h1>
        <div class="base-form">
            <#if errorMessage??>
                <div class="error-message" role="alert">
                    ${errorMessage}
                </div>
            </#if>
            <form method="post" action="/sign-up" class="form-wrapper" enctype="multipart/form-data">
                <div>
                    <h3>Имя</h3>
                    <#if signUpDto.firstName??>
                        <input type="text" class="input-form" id="firstName" name="firstName"
                               value="${signUpDto.firstName}" placeholder="Введите ваше имя"
                               maxlength="30" required>
                    <#else>
                        <input type="text" class="input-form" id="firstName" name="firstName"
                               placeholder="Введите ваше имя" maxlength="30" required>
                    </#if>
                </div>
                <div>
                    <h3>Фамилия</h3>
                    <#if signUpDto.lastName??>
                        <input type="text" class="input-form" id="lastName" name="lastName"
                               value="${signUpDto.lastName}" placeholder="Введите вашу фамилию"
                               maxlength="30" required>
                    <#else>
                        <input type="text" class="input-form" id="lastName" name="lastName"
                               placeholder="Введите вашу фамилию" maxlength="30" required>
                    </#if>
                </div>
                <div>
                    <h3>Отчество</h3>
                    <#if signUpDto.patronymic??>
                        <input type="text" class="input-form" id="patronymic" name="patronymic"
                               value="${signUpDto.patronymic}" placeholder="Введите ваше отчество"
                               maxlength="30" required>
                    <#else>
                        <input type="text" class="input-form" id="patronymic" name="patronymic"
                               placeholder="Введите ваше отчество" maxlength="30" required>
                    </#if>
                </div>
                <div>
                    <h3>Пол</h3>
                    <select class="input-form" id="gender" name="gender" style="width: 425px" required>
                        <#if signUpDto.gender?? && signUpDto.gender == "Мужской">
                            <option value="Мужской" selected>Мужской</option>
                            <option value="Женский">Женский</option>
                        <#else>
                            <option value="Мужской">Мужской</option>
                            <option value="Женский" selected>Женский</option>
                        </#if>
                    </select>
                </div>
                <div>
                    <h3>Дата рождения</h3>
                    <#if signUpDto.birthdate??>
                        <input type="date" class="input-form" id="birthdate" name="birthdate"
                               min="1910-01-01" max="2024-01-01" value="${signUpDto.birthdate}"
                               placeholder="Укажите дату рождения" required>
                    <#else>
                        <input type="date" class="input-form" id="birthdate" name="birthdate"
                               min="1910-01-01" max="2024-01-01"
                               placeholder="Укажите дату рождения" required>
                    </#if>
                </div>
                <div>
                    <h3>Электронная почта</h3>
                    <#if signUpDto.email??>
                        <input type="email" class="input-form" id="email" name="email" value="${signUpDto.email}"
                               placeholder="Введите ваш email" maxlength="50" required>
                    <#else>
                        <input type="email" class="input-form" id="email" name="email"
                               placeholder="Введите ваш email" maxlength="50" required>
                    </#if>
                </div>
                <div>
                    <h3>Пароль</h3>
                    <input type="password" class="input-form" id="password" name="password" placeholder="Введите пароль" maxlength="100" required>
                </div>
                <br>
                <br>
                <input class="btn btn-info" type="submit" value="Зарегистрироваться">
            </form>
            <p class="go-sign-up">Есть аккаунт? <a href="/sign-in" class="go-edit-profile">Войти</a></p>
        </div>
    </div>
</main>
</body>
</html>
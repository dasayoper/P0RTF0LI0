<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница с формой личной информации">
    <meta name="author" content="Сабитов Тимур">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Панель обновления данных пользователе</title>

    <#include "components/links.ftl">

<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1>Информация о пользователе</h1>
        <form method="post" action="/profile/update" class="base-form" enctype="multipart/form-data">
            <div>
                <h3>Имя</h3>
                <input type="text" class="input-form-wide" id="firstName" name="firstName" value="${user.firstName}"
                       placeholder="Введите ваше имя" maxlength="30" required>
            </div>
            <div>
                <h3>Фамилия</h3>
                <input type="text" class="input-form-wide" id="lastName" name="lastName" value="${user.lastName}"
                       placeholder="Введите вашу фамилию" maxlength="30" required>
            </div>
            <div>
                <h3>Отчество</h3>
                <input type="text" class="input-form-wide" id="patronymic" name="patronymic" value="${user.patronymic}"
                       placeholder="Введите ваше отчество" maxlength="30" required>
            </div>
            <div>
                <h3>Пол</h3>
                <select class="input-form-wide" id="gender" name="gender" style="width: 625px" required>
                    <#if user.gender == "Мужской">
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
                <input type="date" class="input-form-wide" id="birthdate" name="birthdate" value="${user.birthdate}"
                        min="1910-01-01" max="2024-01-01" placeholder="Укажите дату рождения" required>
            </div>
            <div>
                <h3>Адрес проживания</h3>
                <#if user.address == "не указано">
                    <input type="text" class="input-form-wide-textarea" id="address" name="address"
                           placeholder="Укажите адрес проживания" maxlength="255" required>
                <#else>
                    <input type="text" class="input-form-wide-textarea" id="address" name="address"
                           value="${user.address}" placeholder="Укажите адрес проживания" maxlength="255" required>
                </#if>
            </div>
            <div>
                <h3>Перенесенные заболевания:</h3>
                <#if user.pastIllnesses == "не указано">
                    <textarea class="input-form-wide-textarea" id="pastIllnesses" name="pastIllnesses" rows="2"
                              placeholder="Укажите информацию о перенесенных заболеваниях" maxlength="1000" required></textarea>
                <#else>
                    <textarea class="input-form-wide-textarea" id="pastIllnesses" name="pastIllnesses" rows="2"
                              placeholder="Укажите информацию о перенесенных заболеваниях" maxlength="1000"
                              required>${user.pastIllnesses}</textarea>
                </#if>
            </div>
            <div>
                <h3>Перенесенные операции:</h3>
                <#if user.surgeries == "не указано">
                    <textarea class="input-form-wide-textarea" id="surgeries" name="surgeries" rows="2"
                              placeholder="Укажите информацию о перенесенных операциях" maxlength="1000" required></textarea>
                <#else>
                    <textarea class="input-form-wide-textarea" id="surgeries" name="surgeries" rows="2"
                              placeholder="Укажите информацию о перенесенных операциях" maxlength="1000"
                              required>${user.surgeries}</textarea>
                </#if>
            </div>
            <div>
                <h3>Хронические заболевания:</h3>
                <#if user.chronicDiseases == "не указано">
                    <textarea class="input-form-wide-textarea" id="chronicDiseases" name="chronicDiseases" rows="2"
                              placeholder="Укажите информацию о хронических заболеваниях" maxlength="1000" required></textarea>
                <#else>
                    <textarea class="input-form-wide-textarea" id="chronicDiseases" name="chronicDiseases" rows="2"
                              placeholder="Укажите информацию о хронических заболеваниях" maxlength="1000"
                              required>${user.chronicDiseases}</textarea>
                </#if>
            </div>
            <div>
                <h3>Лекарственная непереносимость:</h3>
                <#if user.drugIntolerance == "не указано">
                    <textarea class="input-form-wide-textarea" id="drugIntolerance" name="drugIntolerance" rows="2"
                              placeholder="Укажите информацию о лекарственной непереносимости" maxlength="1000" required></textarea>
                <#else>
                    <textarea class="input-form-wide-textarea" id="drugIntolerance" name="drugIntolerance" rows="2"
                              placeholder="Укажите информацию о лекарственной непереносимости" maxlength="1000"
                              required>${user.drugIntolerance}</textarea>
                </#if>
            </div>
            <div>
                <h3>Вредные привычки</h3>
                <#if user.badHabits == "не указано">
                    <input type="text" class="input-form-wide-textarea" id="badHabits" name="badHabits"
                           placeholder="Укажите информацию о вредных привычках" maxlength="100" required>
                <#else>
                    <input type="text" class="input-form-wide-textarea" id="badHabits" name="badHabits" value="${user.badHabits}"
                           placeholder="Укажите информацию о вредных привычках" maxlength="100" required>
                </#if>
            </div>
            <br>
            <input class="btn btn-info" type="submit" value="Сохранить">

        </form>

        <#if errors??>
            <#list errors as error>
                <div class="text">Ошибка: ${error.name}</div>
                <br>
            </#list>
        </#if>
    </div>

</main>
</body>
</html>
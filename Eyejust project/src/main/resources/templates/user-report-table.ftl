<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница с таблицей отчетов пользователя">
    <meta name="author" content="Сабитов Тимур">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Мои отчеты</title>

    <#include "components/links.ftl">
    <script>
        function deleteReport(id) {
            jQuery.ajax({
                url: '/reports/' + id,
                type: 'post'
            });
            document.getElementById("my-tr-" + id).hidden = true;
        }
    </script>
</head>
<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1 class="text-center">Мои отчеты</h1>
        <table class="table table-align">
            <thead class="thead-light bg-info">
            <tr>
                <th scope="col" class="table-title">ID </th>
                <th scope="col" class="table-title">Название документа </th>
                <th scope="col" class="table-title">Дата осмотра </th>
                <th scope="col" class="table-title">ID фотографии левого глаза </th>
                <th scope="col" class="table-title">ID фотографии правого глаза </th>
                <th scope="col" class="table-title">Действия</th>
            </tr>
            </thead>
            <#list reports as report>
                <#if report.status == "NORMAL">
                    <tr id="my-tr-${report.id}">
                        <td>
                            <div class="table-item">
                                ${report.id}
                            </div>
                        </td>
                        <td>
                            <div class="table-link-item">
                                <a href="/files/${report.reportFileDBId}"
                                   class="table-link-item">Отчет ${report.reportFileDBId}</a>
                            </div>
                        </td>
                        <td>
                            <div class="table-item">
                                ${report.creationDate}
                            </div>
                        </td>
                        <td>
                            <div class="table-link-item">
                                <#if report.leftEyeImageDBId == 'no-image'>
                                    ${report.leftEyeImageDBId}
                                <#else>
                                    <a href="/files/${report.leftEyeImageDBId}"
                                       class="table-link-item">${report.leftEyeImageDBId}</a>
                                </#if>
                            </div>
                        </td>
                        <td>
                            <div class="table-link-item">
                                <#if report.rightEyeImageDBId == 'no-image'>
                                    ${report.rightEyeImageDBId}
                                <#else>
                                    <a href="/files/${report.rightEyeImageDBId}"
                                       class="table-link-item">${report.rightEyeImageDBId}</a>
                                </#if>
                            </div>
                        </td>
                        <td>
                            <div class="table-item">
                                <button type="button" onclick="deleteReport('${report.id}')" class="btn-delete">Удалить
                                </button>
                            </div>
                        </td>
                    </tr>
                </#if>
            </#list>
        </table>
        <div class="btn-group">
            <a href="/reports/upload" class="base-form-btn">Загрузить отчет</a>
        </div>
    </div>
</main>
</body>
</html>
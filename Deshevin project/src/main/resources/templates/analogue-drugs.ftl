<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница поиска препаратов">
    <meta name="author" content="Г. Лейсан">
    <title>Просмотр аналогов</title>
    <#include "components/links.ftl">
    <style>
        .avatar {
            width: 200px;
            height: 200px;
            display: inline;
            justify-content: center;
        }
    </style>
</head>
<body class="text-center">
    <#include "components/header.ftl">
    <#function inFavorites(user, analogue)>
        <#list user.favorites as userFavDrugs>
            <#if userFavDrugs.title == analogue.title>
                <#return true>
            </#if>
        </#list>
        <#return false>
    </#function>
    <div class="container">
        <h1>Просмотр аналогов препаратов для: ${drug.title}</h1>

        <table class="table table-align">
            <thead class="thead-light bg-info">
            <tr>
                <th scope="col" class="table-title">Название</th>
                <th scope="col" class="table-title">Описание</th>
                <th scope="col" class="table-title">Категория</th>
                <th scope="col" class="table-title">Класс аналогов</th>
<#--                <th scope="col" class="table-title">Действие</th>-->
            </tr>
            </thead>
            <#list analogues as analogue>
                <tr>
                    <td>
                        <div class="table-item">
                            <div class="title-photo">
                                <a href="/drug/${analogue.id}" class="drug-name">${analogue.title}</a>
                                <br>
                                <#if (analogue.drugImageFileDBID)??>
                                    <img src="/files/${analogue.drugImageFileDBID}" alt="avatar" class="avatar">
                                <#else>
                                    <img src="/img/no-image.png" alt="avatar" class="avatar"/>
                                </#if>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="table-item">
                            ${analogue.description}...
                        </div>
                    </td>
                    <td>
                        <div class="table-item">
                            <#if (analogue.category)??>
                                <#list (analogue.category) as cat>
                                    ${cat.name}<br>
                                </#list>
                            </#if>
                        </div>
                    </td>
                    <td>
                        <div class="table-item">
                            <#if analogue.analogueClass??>${analogue.analogueClass}<#else>нет</#if>
                        </div>
                    </td>
                </tr>
            </#list>

        </table>
    </div>

<#--    <#include "components/footer.ftl" >-->
</body>
</html>
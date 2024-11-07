<!doctype html>
<html lang="ru" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница поиска препаратов">
    <meta name="author" content="Г. Лейсан">
    <title>Поиск препаратов</title>
    <#include "components/links.ftl">
    <script>
        function delet(id, button) {
            jQuery.ajax({
                url: '/favourites/delete-from-favourites/' + id,
                type: 'delete'
            });
            button.innerText = 'Добавить в избранное';
            button.onclick = function () {
                addToFav(id, button);
            };
        }
    </script>

    <script>
        function addToFav(id, button) {
            jQuery.ajax({
                url: '/favourites/add-to-favourites/' + id,
                type: 'post'
            });
            button.innerText = 'Удалить из избранного';
            button.onclick = function () {
                delet(id, button);
            };
        }
    </script>

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
<#function inFavorites(user, drug)>
    <#list user.favorites as userFavDrugs>
        <#if userFavDrugs.title == drug.title>
            <#return true>
        </#if>
    </#list>
    <#return false>
</#function>

<div class="container">
    <div class="content">
        <h1>Поиск препаратов</h1>
        <form action="/search" method="get">
            <p>
                <input type="text" class="input-form" name="prefixParam" id="prefixParam"
                       placeholder="Введите название лекарства">
                <input type="submit" class="btn" value="Поиск">
            </p>
        </form>
    </div>

    <table class="table table-align" id="content-table">
        <thead class="thead-light bg-info">
        <tr>
            <th scope="col" class="table-title">Название</th>
            <th scope="col" class="table-title">Описание</th>
            <th scope="col" class="table-title">Категория</th>
            <th scope="col" class="table-title">Действие</th>
        </tr>
        </thead>
        <tbody id="content-tbody">
        <#list drugs as drug>
            <tr>
                <td>
                    <div class="table-item">
                        <div class="title-photo">
                            <a href="/drug/${drug.id}" class="drug-name">${drug.title}</a>
                            <br>
                            <#if (drug.drugImageFileDBID)??>
                                <img src="/files/${drug.drugImageFileDBID}" alt="avatar" class="avatar">
                            <#else>
                                <img src="/img/no-image.png" alt="avatar" class="avatar"/>
                            </#if>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="table-item">
                        ${drug.description}...
                    </div>
                </td>
                <td>
                    <div class="table-item">
                        <#if (drug.category)??>
                            <#list (drug.category) as cat>
                                ${cat.name}<br>
                            </#list>
                        </#if>
                    </div>
                </td>
                <td>
                    <div class="table-item">
                        <form action="/search/analogue/${drug.id}">
                            <button type="submit" class="btn-delete btn-align">Просмотреть аналоги</button>
                        </form>
                        <#if user??>
                            <#if user.role == 'COMMON_USER'>
                                <#if inFavorites(user, drug)>
                                    <button type="submit" onclick="delet('${drug.id}', this)" class="btn-delete">Удалить из
                                        избранного
                                    </button>
                                <#else>
                                    <button type="submit" onclick="addToFav('${drug.id}', this)" class="btn-delete">Добавить
                                        в
                                        избранное
                                    </button>
                                </#if>
                            </#if>
                        </#if>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>
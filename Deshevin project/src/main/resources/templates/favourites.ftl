<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Панель добавления лекарств">
    <meta name="author" content="Г. Лейсан">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Избранное</title>

    <#include "components/links.ftl">

    <script>
        function delFromFav(id) {
            jQuery.ajax({
                url: '/favourites/delete-from-favourites/' + id,
                type: 'delete'
            });
            document.getElementById("my-tr-" + id).hidden = true
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


    <#include "components/links.ftl">

<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <h1 class="text-center">Избранное</h1>

    <table class="table table-align">
        <thead class="thead-light bg-info">
        <tr>
<#--            <th scope="col">#</th>-->
            <th scope="col" class="table-title">Название</th>
            <th scope="col" class="table-title">Описание</th>
            <th scope="col" class="table-title">Категория</th>
            <th scope="col" class="table-title">Действие</th>
        </tr>
        </thead>
        <#if favourites??>
            <#list favourites as drug>
                <tr id="my-tr-${drug.id}">
<#--                    <th scope="row">${drug?index + 1}</th>-->
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
                            ${drug.description}
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
                            <button type="submit" onclick="delFromFav('${drug.id}')"
                                    class="btn-delete">Удалить из избранного
                            </button>
                        </div>
                    </td>
                </tr>
            </#list>
        </#if>
    </table>
</main>

<#--<#include "components/footer.ftl" >-->

</body>
</html>
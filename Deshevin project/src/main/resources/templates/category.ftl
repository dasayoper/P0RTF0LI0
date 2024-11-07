<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Панель для добавления категорий">
    <meta name="author" content="Г. Лейсан">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Панель для добавления категорий</title>

    <#include "components/links.ftl">
    <script>
        function delet(id) {
            jQuery.ajax({
                url: '/category?id=' + id,
                type: 'delete'
            });
            $('#' + id).closest('.item-row').remove();
        }
    </script>

</head>
<body class="text-center">
<#include "components/header.ftl">
<div class="container" style="place-items: center;">
    <h1>Категории</h1>
    <section class="py-5">
        <form method="post" action="/category" enctype="multipart/form-data">
            <p>
                <input class="input-form" name="name" id="inputCategory" type="text" placeholder="Введите категорию">
                <button type="submit" class="btn">Добавить</button>
            </p>
        </form>
    </section>
    <br>
    <#list categories as category>
        <div class="item-row">
            <div class="form-check-label" style="font-weight: bold" id=${category.id}>${category.name}</div>
            <button type="submit" onclick="delet('${category.id}')" class="btn-delete">Удалить</button>
        </div>
    </#list>
</div>


<#--<#include "components/footer.ftl" >-->


</body>
</html>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Панель для добавления классов аналогов">
    <meta name="author" content="М. Ильдар">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Панель для добавления классов аналогов</title>


    <#include "components/links.ftl">
    <script>
        function delet(id) {
            jQuery.ajax({
                url: '/analogue?id=' + id,
                type: 'delete'
            });
            $('#' + id).closest('.item-row').remove();
        }
    </script>

</head>
<body class="text-center">
<#include "components/header.ftl">
<div class="container" style="place-items: center;">
    <h1>Классы аналогов</h1>
    <section class="py-5">
        <form method="post" action="/analogue" enctype="multipart/form-data">
            <p>
                <input class="input-form" name="title" id="inputAnalogue" type="text"
                       placeholder="Введите класс аналогов">
                <button type="submit" class="btn btn-info mb-2">Добавить</button>
            </p>
        </form>
    </section>
    <br>
    <#list analogues as analogue>
        <div class="item-row">
            <div class="form-check-label" style="font-weight: bold" id=${analogue.id}>${analogue.title}</div>
            <button type="submit" onclick="delet('${analogue.id}')" class="btn-delete">Удалить</button>
        </div>
    </#list>
</div>


<#--<#include "components/footer.ftl" >-->


</body>
</html>
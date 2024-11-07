<!doctype html>
<html lang="ru">
<head>
    <meta charlist="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Домашняя страница">
    <meta name="author" content="М. Ильдар">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Домашняя страница</title>

    <#include "components/links.ftl">

</head>
<body>
<#include "components/header.ftl">
<img src="/img/pill1.png" class="img-pill1">
<img src="/img/pill2.png" class="img-pill2">
<img src="/img/pill3.png" class="img-pill3">
<img src="/img/pill4.png" class="img-pill4">
<div class="container">
    <div class="home-content">
        <h1 class="home-text-main">Найди аналог<br>нужного лекарства<br>за секунду!</h1>
        <p class="home-text">Наш сервис позволяет найти информацию о<br>препарате и его аналогах, узнать наличие и<br>стоимость
            в ближайших магазинах.</p>
        <div>
            <button onclick="location.href='/search';" class="btn" href="/search">Начать поиск</button>
        </div>
    </div>

</div>

<#--<#include "components/footer.ftl" >-->

</body>
</html>
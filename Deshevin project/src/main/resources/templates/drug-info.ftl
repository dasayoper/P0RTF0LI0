<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Страница информации о лекарстве">
    <meta name="author" content="Г. Лейсан">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Информация о лекарстве</title>

    <#include "components/links.ftl">

    <script>
        function isCategorySelected(name, drug) {
            console.log(drug);
            if (drug.category.size !== 0) {
                console.log(drug.category);
                return true;
            }
            return false;
        }
    </script>
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
            width: 250px;
            height: 250px;
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
<main class="container">
    <div class="content">
        <div class="drug-title">
            <h2 class="drug-name">${drug.title}</h2>
        </div>
        <div class="row justify-content-md-center">
            <#if user?? && user.role == 'ADMIN'>
                <form method="post" action="" class="content" enctype="multipart/form-data">
                    <#if (drug.drugImageFileDBID)??>
                        <img src="/files/${drug.drugImageFileDBID}" alt="avatar" class="avatar">
                    <#else>
                        <img src="/img/no-image.png" alt="avatar" class="avatar"/>
                    </#if>
                    <div class="col">
                        <p>
                        <h3>Название лекарства</h3>
                        <input class="input-form" name="title" value="${drug.title}" type="text">
                        </p>
                        <p>
                        <h3>Категории лекарства</h3>
                        <select multiple name="categoryIdSet" class="input-form"
                                id="inlineFormCustomSelect">
                            <option value="" multiple="multiple" selected> <#if drug.category??>
                                    <#list drug.category as category>
                                        <${category.name}
                                    </#list>
                                <#else>нет</#if>
                            </option>
                            <#list categories as category>
                                <option name="categoryIdSet" value="${category.id}">${category.name}</option>
                            </#list>
                        </select>
                        </p>
                        <p>
                        <h3>Класс аналога</h3>
                        <select name="analogueId" class="input-form" id="inlineFormCustomSelect">
                            <option name="analogueId" value=""
                                    selected> <#if drug.analogueClass??>${drug.analogueClass}<#else>нет</#if></option>
                            <#list analogues as analogue>
                                <option value="${analogue.id}">${analogue.title}</option>
                            </#list>
                        </select>
                        </p>
                    </div>
                    <div class="row">
                        <div class="col align-self-center">
                            <p>
                            <h3>Описание</h3></p>
                            <textarea class="input-form" id="drugDescription" rows="3"
                                      name="description">${drug.description}</textarea>
                            <p>
                            <h3>Состав</h3></p>
                            <textarea class="input-form" id="drugComposition" rows="3"
                                      name="composition">${drug.composition}</textarea>
                            <p>
                            <h3>Побочные эффекты</h3></p>
                            <textarea class="input-form" id="drugSideEffects" rows="3"
                                      name="sideEffects">${drug.sideEffects}</textarea>
                            <p>
                            <h3>Действие</h3></p>
                            <textarea class="input-form" id="drugEffect" rows="3"
                                      name="effect">${drug.effect}</textarea>
                            <p>
                            <h3>Применение</h3></p>
                            <textarea class="input-form" id="drugInstruction" rows="3"
                                      name="instruction">${drug.instruction}</textarea>
                            <p>
                            <h3>Противопоказания</h3></p>
                            <textarea class="input-form" id="drugContraindications" rows="3"
                                      name="contraindications">${drug.contraindications}</textarea>
                            <p>
                            <h3>Форма выпуска</h3></p>
                            <textarea class="input-form" id="drugReleaseForm" rows="3"
                                      name="releaseForm">${drug.releaseForm}</textarea>
                            <p>
                            <h3>Производитель</h3></p>
                            <textarea class="input-form" id="drugManufacturer" rows="3"
                                      name="manufacturer">${drug.manufacturer}</textarea>
                            <p>
                            <h3>Условия хранения</h3></p>
                            <textarea class="input-form" id="drugStorageConditions" rows="3"
                                      name="storageConditions">${drug.storageConditions}</textarea>
                        </div>
                    </div>
                    <div class="d-flex justify-content-around">
                        <br>
                        <button type="submit" class="btn btn-info mb-2">Изменить</button>
                        <br>
                        <br>
                    </div>
                </form>
            <#else>
                <main>
                    <div class="photo-desc">
                        <#if (drug.drugImageFileDBID)??>
                            <img src="/files/${drug.drugImageFileDBID}" alt="avatar" class="avatar">
                        <#else>
                            <img src="/img/no-image.png" alt="avatar" class="avatar"/>
                        </#if>
                        <h2 class="drug-desc">Описание: <p
                                    style="font-weight: normal; font-size: 24px">${drug.description}</p></h2>
                    </div>
                    <br>
                    <form action="/search/analogue/${drug.id}" class="inst-title">
                        <button type="submit" onclick="" class="btn">Просмотреть аналоги</button>
                        <br>
                        <div>
                            <#if user??>
                                <#if user.role == 'COMMON_USER'>
                                    <#if inFavorites(user, drug)>
                                        <button type="button" onclick="delet('${drug.id}', this)" class="btn-delete">
                                            Удалить из избранного
                                        </button>
                                    <#else>
                                        <button type="button" onclick="addToFav('${drug.id}', this)" class="btn-delete">
                                            Добавить в избранное
                                        </button>
                                    </#if>
                                </#if>
                            </#if>
                        </div>
                        <br>
                        <h2>Инструкция:</h2>
                    </form>

                    <div class="drug-info">
                        <p class="info-name">
                            <strong>Состав: </strong>${drug.composition}
                        </p>
                        <p class="info-name">
                            <strong>Побочные эффекты: </strong>${drug.sideEffects}
                        </p>
                        <p class="info-name">
                            <strong>Действие: </strong>${drug.effect}
                        </p>
                        <p class="info-name">
                            <strong>Применение: </strong>${drug.instruction}
                        </p>
                        <p class="info-name">
                            <strong>Противопоказания: </strong>${drug.contraindications}
                        </p>
                        <p class="info-name">
                            <strong>Форма выпуска: </strong>${drug.releaseForm}
                        </p>
                        <p class="info-name">
                            <strong>Производитель: </strong>${drug.manufacturer}
                        </p>
                        <p class="info-name">
                            <strong>Условия хранения: </strong>${drug.storageConditions}
                        </p>
                        <p class="info-name">
                            <strong>Категория: </strong>
                            <#if (drug.category)??>
                                <#list (drug.category) as category>
                                    ${category.name}<#sep>, </#sep>
                                </#list>
                            <#else>Нет категории</#if>
                        </p>
                        <p class="info-name">
                            <strong>Класс
                                аналогов: </strong><#if drug.analogueClass??>${drug.analogueClass}<#else>нет</#if>
                        </p>
                    </div>
                </main>
            </#if>
        </div>
    </div>


</main>

</body>
</html>
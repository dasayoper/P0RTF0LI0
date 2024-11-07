<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Панель добавления лекарств">
    <meta name="author" content="М. Ильдар">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Панель добавления лекарств</title>

    <#--    <style>-->
    <#--        .drug-form {-->
    <#--            margin-left: auto;-->
    <#--            margin-right: auto;-->
    <#--            width: 50%;-->
    <#--        }-->

    <#--        .brd {-->
    <#--            border: 4px double black; /* Параметры границы */-->
    <#--            padding: 10px; /* Поля вокруг текста */-->
    <#--        }-->

    <#--    </style>-->
    <#include "components/links.ftl">
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var inputFile = document.getElementById('input__file');
            var fileButtonText = document.querySelector('.input__file-button-text');

            inputFile.addEventListener('change', function () {
                if (inputFile.files.length > 0) {
                    fileButtonText.innerText = inputFile.files[0].name;
                } else {
                    fileButtonText.innerText = 'Загрузить изображение';
                }
            });
        });
    </script>
</head>
<body class="text-center">
<#include "components/header.ftl">
<main class="container">
    <div class="home-content">
        <h1>Добавить лекарство</h1>

        <#if success??>
            <div class="alert alert-success" role="alert">
                Лекарство успешно сохранено!
            </div>
        </#if>
        <form method="post" action="/drug" enctype="multipart/form-data" class="content">
            <div>
                <div class="mb-3">
                    <h3>Название лекарства</h3>
                    <input type="text" class="input-form" id="drugTitle" name="title">
                </div>
                <div class="mb-3">
                    <h3>Описание</h3>
                    <textarea class="input-form" id="drugDescription" rows="3" name="description"></textarea>
                </div>
                <div class="mb-3">
                    <h3>Состав</h3>
                    <textarea class="input-form" id="drugComposition" rows="3" name="composition"></textarea>
                </div>
                <div class="mb-3">
                    <h3>Производитель</h3>
                    <textarea class="input-form" id="drugManufacturer" rows="3" name="manufacturer"></textarea>
                </div>
                <div class="mb-3">
                    <h3>Противопоказания</h3>
                    <textarea class="input-form" id="drugContraindications" rows="3"
                              name="contraindications"></textarea>
                </div>
                <div class="mb-3">
                    <h3>Побочные эффекты</h3>
                    <textarea class="input-form" id="drugSideEffects" rows="3" name="sideEffects"></textarea>
                </div>
                <div class="mb-3">
                    <h3>Форма выпуска</h3>
                    <textarea class="input-form" id="drugReleaseForm" rows="3" name="releaseForm"></textarea>
                </div>
                <div class="mb-3">
                    <h3>Действие</h3>
                    <textarea class="input-form" id="drugEffect" rows="3" name="effect"></textarea>
                </div>
                <div class="mb-3">
                    <h3>Применение</h3>
                    <textarea class="input-form" id="drugInstruction" rows="3" name="instruction"></textarea>
                </div>
                <div class="mb-3">
                    <h3>Условия хранения</h3>
                    <textarea class="input-form" id="drugStorageConditions" rows="3"
                              name="storageConditions"></textarea>
                </div>


                <div class="input__wrapper input-align">
                    <input name="file" type="file" id="input__file" class="input input__file" accept=".jpg, .png, .jpeg"
                           required>
                    <label for="input__file" class="input__file-button">
                        <span class="input__file-button-text">Загрузить изображение</span>
                    </label>
                </div>

                <h3>Категории лекарства</h3>
                <div class="brd">
                    <#list categories as category>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" name="categoryIdSet" type="checkbox" id="inlineCheckbox"
                                   value="${category.id}">
                            <label class="form-check-label" for="inlineCheckbox">${category.name}</label>
                        </div>
                    </#list>
                </div>

                <h3>Класс аналогов</h3>
                <div class="brd">
                    <#list analogues as analogue>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="analogueId" id="inlineRadio"
                                   value="${analogue.id}">
                            <label class="form-check-label" for="inlineRadio">${analogue.title}</label>
                        </div>
                    </#list>
                </div>
                <br>
                <br>
            </div>

            <input class="btn" type="submit" value="Сохранить">
            <br>
            <br>

        </form>
    </div>


    <#--    <#include "components/footer.ftl" >-->
</main>
</body>
</html>
<header class="navbar">
    <div class="navbar-content">
        <div>
            <#--    <div class="d-flex align-items-center">-->
            <#--        <img src="/img/logo.png" width="80px" height="80px">-->
            <#--        <a class="navbar-brand" href="/homepage" style="position:relative; left:10px;"/>Дешевин</a>-->
            <a class="navbar-brand" href="/homepage" style="position:relative; left:10px;"/>ДЕШЕВИН.РУ</a>
        </div>

        <div class="navbar-text-content">

            <div class="navbar-left-content">
                <a class="navbar-text" href="/search"/>Поиск лекарств</a>
            </div>

            <div class="navbar-right-content">
                <#if user??>

                    <#if user.role == 'ADMIN'>
                        <a class="navbar-text" href="/drug/all"/>База лекарств</a>

                        <a class="navbar-text" href="/drug"/>Добавить лекарство</a>

                        <a class="navbar-text" href="/analogue"/>Классы аналогов</a>

                        <a class="navbar-text" href="/category"/>Категории</a>
                    </#if>

                    <a class="navbar-text" href="/profile"/>Профиль (${user.username})</a>

                    <a class="navbar-text" href="/logout"/>Выход</a>
                <#else>
                    <a class="navbar-text" href="/sign-in"/>Войти</a>

                    <a class="navbar-text" href="/sign-up"/>Зарегистрироваться</a>
                </#if>
            </div>

        </div>
    </div>



</header>
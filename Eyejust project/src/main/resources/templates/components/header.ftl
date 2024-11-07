<header class="navbar">
    <div class="navbar-content">
        <div>
            <span class="navbar-brand" style="position:relative; left:10px;">EyeJust</span>
        </div>

        <div class="navbar-text-content">
            <div class="navbar-right-content">
                <#if user??>
                    <a class="navbar-text" href="/profile">Профиль</a>

                    <a class="navbar-text" href="/logout">Выход</a>
                <#else>
                    <a class="navbar-text" href="/sign-in">Войти</a>

                    <a class="navbar-text" href="/sign-up">Зарегистрироваться</a>
                </#if>
            </div>
        </div>
    </div>

</header>
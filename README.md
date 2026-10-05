# AQA Project

Автотестовый фреймворк для UI- и API-тестирования на Java: **Selenide** для UI (демо-сайт [saucedemo.com](https://www.saucedemo.com/)) и **REST Assured** для API, с отчётностью через **Allure** и запуском через **TestNG**. Пайплайн собран на **Jenkins**, тесты в UI-режиме гоняются против **Selenium Grid (Chrome)** в Docker.

## Стек технологий

- **Java 17**, **Maven**
- **TestNG 7.10** — тест-раннер, сьюты, параллельный запуск, DataProvider'ы
- **Selenide 7.16** — UI-тесты
- **REST Assured 5.5** — API-тесты
- **Allure 2.35** — отчётность (`allure-testng`, `allure-selenide`), скриншоты крепятся к упавшим тестам
- **Lombok**, **Jackson**, **SLF4J + Logback** — вспомогательные библиотеки
- **Docker / Docker Compose** — Jenkins + Selenium Standalone Chrome
- **Jenkins** (`Jenkinsfile`) — CI-пайплайн с выбором сьюта и публикацией Allure/JUnit отчётов

## Структура проекта

```
src/
├── main/java/
│   ├── api/
│   │   ├── actions/       # методы работы с API (UserApi и т.д.)
│   │   ├── base/          # базовые классы и методы для API-тестов
│   │   └── models/        # POJO-модели запросов/ответов
│   ├── config/            # чтение конфигурации (ConfigReader)
│   ├── infostructure/     # слушатели TestNG, ретраеры, кастомный репортер
│   ├── ui/
│   │   ├── base/          # базовый класс UI-теста, кастомные Selenide-условия
│   │   └── pages/         # Page Object классы (LoginPage, InventoryPage...)
│   └── utils/             # генерация тестовых данных, ассерты, доп. слушатели
├── test/java/tests/
│   ├── api/                # API-тесты
│   └── ui/                 # UI-тесты
└── test/resources/
    ├── config-{at,dev,stage}.properties  # конфиги окружений
    └── global.properties                 # общие настройки
```

Проект построен по паттерну **Page Object** (UI) и слоем **Actions/Models** (API).

## Быстрый старт

### Требования

- JDK 17+
- Maven 3.8+
- Docker и Docker Compose (для запуска UI-тестов через Selenium Grid)

### Запуск через Docker Compose (Jenkins + Selenium)

```bash
docker-compose up -d
```

Поднимет:
- Jenkins на `http://localhost:8080`
- Selenium Standalone Chrome на `http://localhost:4444` (VNC-просмотр — `http://localhost:7900`)

### Запуск тестов локально

Все тесты (API + UI):
```bash
mvn clean test
```

Только API-тесты:
```bash
mvn clean test -Papi
```

Только UI-тесты:
```bash
mvn clean test -Pui
```

По умолчанию UI-тесты идут через удалённый Selenium (`remote.connection.use=true` в `global.properties`, адрес `http://selenium-chrome:4444/wd/hub`). Для локального запуска в браузере на своей машине измените это значение или соответствующий системный property.

### Выбор окружения

Окружение задаётся параметром `-Denv` (значения соответствуют файлам `config-<env>.properties`: `at`, `dev`, `stage`). По умолчанию используется `at`.

```bash
mvn clean test -Denv=dev
```

Любой параметр из `.properties`-файлов можно переопределить через `-D`, например:
```bash
mvn clean test -Dbrowser=firefox -Dtimeout=15000
```

## Отчётность

Тесты используют Allure listener (`io.qameta.allure.testng.AllureTestNg`), результаты пишутся в `target/allure-results`.

Просмотр отчёта локально:
```bash
mvn allure:serve
```

## CI/CD

`Jenkinsfile` описывает пайплайн:
1. Триггер по push в GitHub (`githubPush()`)
2. Параметр `TEST_SUITE` — выбор сьюта (`all` / `api` / `ui`)
3. Запуск `mvn clean test` с соответствующим TestNG-сьютом
4. Публикация JUnit- и Allure-отчётов

## Версионирование (GitVersion)

Версия считается автоматически из истории git по правилам `GitVersion.yml` (workflow `GitHubFlow/v1`):

| Ветка | Пример версии |
|---|---|
| `main`, коммит с тегом `v1.0.0` | `1.0.0` |
| `main`, 1 коммит после тега | `1.0.1-1` (число растёт с каждым коммитом) |
| `feature/login` | `1.0.1-login.1` |
| любая другая дочерняя ветка (`bugfix/x`) | `1.0.1-bugfix-x.1` |

Номер в ветках не растёт с каждым коммитом (остаётся `.1`), число коммитов видно в `FullSemVer` (`1.0.1-login.1+3`).

- В Jenkins отдельная стадия `Version` выполняет `gitversion /showvariable SemVer`, результат попадает в имя билда и передаётся в Maven через `-Drevision` (в `pom.xml` версия — `${revision}`, локально по умолчанию `1.0.0-SNAPSHOT`).
- Чтобы зафиксировать релиз и сдвинуть номер, поставьте тег на коммит в `main`: `git tag v1.1.0 && git push origin v1.1.0`. После первого тега строку `next-version` в `GitVersion.yml` можно удалить.
- Повысить мажорную/минорную версию можно и без тега: добавьте в сообщение коммита `+semver: major` или `+semver: minor`.
- GitVersion требует полной истории git (не shallow clone) и тегов — в Jenkinsfile это учтено в стадии Checkout.
- Проверка локально (через Docker, из корня репозитория): `docker run --rm -v "%cd%:/repo" gittools/gitversion:latest-debian.12 /repo`.

## TestNG-сьюты

- `testng-master.xml` — запускает `testng-api.xml` + `testng-ui.xml`
- `testng-api.xml` — пакет `tests.api`
- `testng-ui.xml` — пакет `tests.ui`, параллельный запуск (`parallel="methods"`, `thread-count=3`)

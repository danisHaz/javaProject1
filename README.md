# Четвертый (практически ультимативный) проект из семестровой по Жабе

Тут исходники, которые можно скачать для того, чтобы пользоваться благами Великой java.

## Что сделать, чтобы поставить себе:
1. Необходимо поставить себе **gradle plugin (7.4.2)**
2. Посмотреть настройку запуска через конкретные команды для свое ОС

## Запуск проекта
1. Проверяем версию gradle wrapper через команду **gradle wrapper --version**
2. Если версия стоит не **7.4.2**, то обновляем до этой версии
3. **В коде есть строка подключения (да, глупо, что прям там, но допустим), которая содержит логин/пароль, поэтому перед пользованием базой данных в монго не забудьте поменять на свои!!!**
4. Также нужно настроить VPN для вашей ОС, чтобы запросики доходили до mongodb...
3. При первой сборке выполнить **gradle build**
4. Для запуска проекта выполняем команду **gradle -q**


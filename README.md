Тестовое задание:
На основе ответа REST API(https://restcountries.com/v3/all) надо скачать 
все флаги стран с ресурса (https://www.geonames.org/countries/).
Скачанные флаги положить в директорию:
base.dir(указана в application.properties) + /geonames/flags/x/.

Инструкция по сборке и запуску:

mvn package && java -jar .\target\FlagDownloader-0.0.1.jar

Список загруженных флагов стран будет доступен по ссылке: 

http://localhost:8080/download-flags

Флаги будут сохранены в папку: 

./download/flag_countries/geonames/flags/x

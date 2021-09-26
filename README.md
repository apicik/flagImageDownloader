Тестовое задание:
На основе ответа REST API(https://restcountries.com/v3/all) необходимо скачать 
все флаги стран с ресурса (https://www.geonames.org/countries/).
Скачанные флаги положить в директорию:

base.dir(указана в application.properties) + /geonames/flags/x/.

Инструкция по сборке и запуску:

git clone https://github.com/apicik/flagImageDownloader.git

mvn package && java -jar .\target\FlagDownloader-0.0.1.jar

Для загрузки флагов стран необходимо перейти по ссылке: 

http://localhost:8080/download-flags

Флаги будут сохранены в папку: 

./download/flag_countries/geonames/flags/x

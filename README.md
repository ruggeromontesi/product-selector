# How to run product-selector (Intellij)
1) Open command prompt from <YOUR FOLDER>
2) Clone from repote repository typing : git clone  https://github.com/ruggeromontesi/product-selector.git
3) Open project with intellij : File/open/ select folder <YOUR FOLDER>/product-selector
4) Build
5) Run the application using configuration located in ./run : ProductSelectorApplication.run.xml



# How to run product-selector (Maven)
1) Open command prompt
2) Clone from repote repository typing : git clone  https://github.com/ruggeromontesi/product-selector.git
3) Move into project folder : cd  product-selector
4) Build project mvn clean install -f pom.xml
5) Run the project mvn spring-boot:run

# Start page is available at localhost:8080/form


# How to load  a new product:

USE METHOD POST.
endpoint http://localhost:8080/product/create

json body:
{
    "name":"test product 2",
    "minAge": 8,
    "maxAge":64,
    "minIncome": 4000
}

cURL: 
curl --location 'http://localhost:8080/product/create' \
--header 'Content-Type: application/json' \
--data '{
    "name":"test product 8",
    "minAge": 18,
    "maxAge":64,
    "minIncome": 4000,
    "maxIncome" : 7800,
    "mustBeStudent": true
}'

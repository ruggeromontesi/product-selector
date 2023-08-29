# How to run product-selector (Intellij)
1) Open command prompt from <YOUR FOLDER>
2) Clone from remote repository typing : git clone  https://github.com/ruggeromontesi/product-selector.git
3) Open project with intellij : File/open/ select folder <YOUR FOLDER>/product-selector
4) Build
5) Run the application using configuration located in ./run : ProductSelectorApplication.run.xml


# Start page
 is available at localhost:8080/form
 
 Credentials:
 
 user:ruggero
 password:montesi


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

# cURL: 
curl -u ruggero:montesi --location 'http://localhost:8080/product/create' \
--header 'Content-Type: application/json' \
--data '{
    "name":"test product 8",
    "minAge": 18,
    "maxAge":64,
    "minIncome": 14000,
    "maxIncome" : 37800,
    "mustBeStudent": true
}'

# postman
a collection of calls is available in 
 /product-selector/postman-collection folder

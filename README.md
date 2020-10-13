#Recipe App (Server side implementation) 
This is a personal project created for learning purposes. Here I 
build a Spring Boot Recipe application using Kotlin and Gradle. The 
database used is MariaDB. Other notable technologies: 
- [x] Hibernate
- [ ] MS Azure deployment
- [ ] Liquibase 
- [x] Trello Board for tracking progress (https://trello.com/b/z2fm1Jey/recip3-app)
- [x] GitHub Actions
- [x] SonarCloud (https://sonarcloud.io/dashboard?id=maxr96_Recipy-App-Server)
- [ ] and more soon...

#Main features of the API
   - [x] Upload and store recipes
   - [x] Get and extend possible measurement units
   - [x] Retrieve list of available ingredients
   - [ ] Search for recipes by ingredients (not yet done)
   - [ ] Login the user (not yet done)
   - [ ] and more...
## Database Model
The main entities used for the database model:
- MeasurementUnit - stores the name of different units, e.g. gram, spoon.
- Ingredient - stores the name of different ingredients, e.g. black pepper, tomato.
- RecipeIngredient - stores the ingredient with its amount and measurement unit used in a recipe.
- Recipe - stores the actual recipe with title, description, ingredients, time it was posted and author.
- Author - stores the user of the website with their username and email.
![Cookbook database schema](cookbook_db_schema.png)

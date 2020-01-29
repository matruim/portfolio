# Campsite Project

* Add Landing Page
* Add Campgrounds Page that lists all campgrounds

Each Campground has:
* Name
* Image

## Layout and Basic Styling
* Create our header and footer partials
* Add in Bootstrap

## Creating New Campgrounds
* Setup new campgound POST route
* Add in body-parser
* Setup route to show form
* Add basic unstyled form

## Style the campgrounds page
* Add a better header/title
* Make campgrounds display in a grid

## Style the Navbar and Form
* Add a navbar to all templates
* Style the new campground form

## Add Mongoose
* Install and configure mongoose
* Setup campground model
* Use campground model inside of our routes!

## Show Page
* Review the RESTful routes we've seen so far
* Add description to our campground model
* Show db.collection.drop()
* Add a show route/template


RESTFUL ROUTES
Name    URL                 Verb    Description
==================================================
INDEX   /campgrounds        GET     Display a list of all campgrounds
NEW     /campgrounds/new    GET     Display Form to make a new campgrounds
CREATE  /campgrounds        POST    Add new campgrounds to Database
SHOW    /campgrounds/:id    GET     Shows info about one campground

## Refactor Mongoose Code
* Create a models directory
* Use module.exports
* Require everyting correctly!

## Add Seeds File
* Add a seeds.js file
* Run the seeds file every time the server starts

## Add the comment model
* Make our errors go away
* Display comments on campground show page

## Comment New/Create
* Discuss nested routes
* Add the comment new and create routes
* Add the new comment form

RESTFUL ROUTES Nesting
Name    URL                 Verb    Description
==================================================
INDEX   /campgrounds        GET     Display a list of all campgrounds
NEW     /campgrounds/new    GET     Display Form to make a new campgrounds
CREATE  /campgrounds        POST    Add new campgrounds to Database
SHOW    /campgrounds/:id    GET     Shows info about one campground

NEW     /campgrounds/:id/comments/new    GET     Display Form to make a new campground comment
CREATE  /campgrounds/:id/comments        POST    Add new campground comment to Database

## Style Show Page
* Add sidebar to show page
* Display comments nicely

## Finish Styling Show Page
* Add public directory
* Add custom stylesheet

## Auth Part 1 - Add User Model
* Install all packages needed for auth
* Define User model

## Auth Part 2 - Register
* Configure Passport
* Add register routes
* Add register template

## Auth Part 3 - Login
* Add login routes
* Add login template

## Auth Part 4 - Logout/Navbar
* Add logout route
* Prevent user from adding a comment if not signed in
* Add Links to navbar
* Show/hide auth links correctly

## Auth Part 5 - Show/Hide Links
* Show/Hide auth links in navbar correctly

## Refactor The Routes
* use Express router to reorganize all routes

## Users + Comments
* Associate users and comments
* Save Author's name to a comment automattically

## Users + Campgrounds
* Prevent an unathenticated user from creating a campground
* Save username + id to newly created campground

## Editing Campgrounds
* Add Mehtod-Override
* Add Edit Route for Campgrounds
* Add Link to Edit Page
* Add Update Route
* Fix $set problem

## Deleting Campgrounds
* Add Destroy Route
* Add Delete Button

## Authorization: Campgrounds
* User can only edit his/her campgrounds
* User can only delete his/her campgrounds
* Hide/Show edit and delete buttons

## Editing Comments
* Add Edit Route for Comments
* Add Edit Button
* Add Update Route

## Deleting Comments
* Add Destroy Route
* Add Delete Button

## Authorization: Comments
* User can only edit his/her comments
* User can only delete his/her comments
* Hide/Show edit and delete buttons
* Refactor Middleware

## Adding in Flash!
* Demo working version
* Install and configure connect-flash
* Add bootstrap alerts to header

## Edit Landing Page
* Add background slider and fader to landing page.

## Add in Dynamic Price
* add Price to campground model as a String datatype
* add Price to views/campgrounds/new.ejs and views/campgrounds.edit.ejs
* add Price to views/campgrounds/showejs


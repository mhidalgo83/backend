### Expat Journal API

This is the backend of a full-stack application project. The backend is built in Java utilizing the Spring Boot framework, with a PostrgreSQL database. All controllers and services have been tested using JUnit and built-in testing tools inside Spring.  

Base URL for the application is:
https://sj-mh-expat-journal.herokuapp.com/

| Request                 | Endpoint                          | Request                                 | Res                  |
|-------------------------|-----------------------------------|-----------------------------------------|----------------------|
| Create User (POST)      | /createnewuser                    | { username:, password:, primaryemail: } | Token                |
| Login (POST)            | /login                            | { username:, password: }                | Token                |
| Get user info (GET)     | /users/userinfo                   | NA                                      | Logged in  user info |
| Get user by ID (GET)    | /users/user/{userid}              | NA                                      | Specific  user info  |
| Get list of users (GET) | /users/users                      | NA                                      | List of all users    |
| Update user (PUT)       | /users/user/{userid}              | { username:, password:, primaryemail: } | NA                   |
| Get photos (GET)        | /photos/photos                    | NA                                      | List of photos       |
| Get photo by id (GET)   | /photos/photo/{photoid}           | NA                                      | Get specific  photo  |
| Create new photo (POST) | /photos/photo/story/{storyid}     | {imageurl: ,  description: }            | NA                   |
| Delete photo (DELETE)   | /photos/photo/{photoid}           | photoid                                 | NA                   |
| Get stories (GET)       | /stories/stories                  | NA                                      | All stories          |
| Get story by id (GET)   | /stories/story/{storyid}          | NA                                      | Story by id          |
| Create story (POST)     | /stories/story                    | { title:, location:, description: }     | NA                   |
| Delete story (DELETE)   | /stories/story/{storyid}          | storyid                                 | NA                   |
| Update story (PUT)      | /stories/story/{storyid}          | { title:, location:, description: }     | NA                   |
| Stories by userid (GET) | /stories/stories/user/{userid}    | NA                                      | Stories by userid    |

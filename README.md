### Expat Journal API

Base URL for the application is:
http://sj-mh-expat-journal.herokuapp.com/

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
| Delete photo (DELETE)   | /photos/photo/{photoid}           | NA                                      | NA                   |
| Get stories (GET)       | /stories/stories                  | NA                                      | All stories          |
| Get story by id (GET)   | /stories/story/{storyid}          | NA                                      | Story by id          |
| Create story (POST)     | /stories/story                    | { title:, location:, description: }     | NA                   |
| Delete story (DELETE)   | /stories/story/{storyid}          | NA                                      | NA                   |
| Update story (PUT)      | /stories/story/{storyid}          | { title:, location:, description: }     | NA                   |
| Stories by userid (GET) | /stories/stories/user/{userid}    | NA                                      | Stories by userid    |

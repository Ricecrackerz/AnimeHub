Original App Design Project
===

# AnimeHub 

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
The goal for this app is to create a place where users can easily browse through a wide selection of anime shows, create their personal list of prefered animes, and discuss with other users of this app about the presented animes

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Entertainment
- **Mobile:** Android
- **Story:** See list of animes and discuss about it
- **Market:** Anime watchers
- **Habit:** Consistent updates on new animes
- **Scope:** A viable project that is presentable on demo day.

## Fifth Sprint Progress Gif
<img src = "https://github.com/Ricecrackerz/AnimeHub/blob/master/AnimeHubGIF5.gif" width = 500>

## Fourth Sprint Progress Gif
<img src = "https://github.com/Ricecrackerz/AnimeHub/blob/master/AnimeHubGIF4.gif" width = 500>

## Third Sprint Progress Gif
<img src = "https://github.com/Ricecrackerz/AnimeHub/blob/master/AnimeHubGIF3.gif" width = 500>

## Second Sprint Progress Gif
<img src = "https://github.com/Ricecrackerz/AnimeHub/blob/master/AnimeHubGIF2.gif" width = 500>

## First Sprint Progress Gif

<img src="https://github.com/Ricecrackerz/AnimeHub/blob/master/AnimeHubGIF1.gif" width=500>

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

- [x] User can login/sign up/sign out of the app
- [x] User can navigate through the app using bottom tab navigation
- [x] User can see a list of anime
- [x] User can make posts about anime
- [x] User can see their own profile which contains thier contributions to posts
- [ ] ...

**Optional Nice-to-have Stories**

- [ ] User can select anime to add to their watchlist
- [ ] User can sort anime by category(trending, highly rated, etc.)
- [ ] Users can make comments on anime
- [x] User can watch a trailer of the anime
- [x] Users can have profile pictures
- [ ] User can see anime that they are interested in
- [ ] User can make comments on posts
- [ ] ...

### 2. Screen Archetypes

* Home Page (Anime list Page)
   * User can view a list of anime
   * User can tap on an anime to go to its **Anime Detail Screen**
   * User can see the navigation bar which contains buttons to go to the **Discussion Page** and **Profile Page**
* Anime Detail Screen
   * A detailed description of each anime is displayed
   * A button can be tapped to add the anime to the user's list of anime
   * *Optional* : User can see comments about the anime 
   * *Optional* : User can watch a trailer of the anime
* Discussion Page
    * User can view the list of posts made about certain animes
    * User can see the name of the original poster,name of anime being discusses, post title and timestamp of the pos
    * User can click on a certain post and be redirected to the **Discussion Detail Page**
* Discussion Detail Page
    * User can see comments made on this post
    * User can respond to the post and make their own comment
* Profile Page
    * User can see all the discussions they have contributed to
    * User can tap on a discussion in the list to view its **Discussion detail Page**
    * *Optional*: User can sort their personalized anime list by rating, trending, etc.
    * *Optional*: User can add a profile picture
    * *Optional:* User can see their own personalized anime list
    * *Optional:* User can tap on the anime in the list to go to its **Anime Detail Screen**
    

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* [Home/Anime List] -> Home Page
* [Discussion] -> Discussion Page
* [Profile] -> Profile Page

**Flow Navigation** (Screen to Screen)

* Home/Anime List
   * Anime Detail Page
* Discussion page
   * Detailed discussion page
* Profile Page
    * Anime Detail Page
    * Discussion Detail Page

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="wireframe.png" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype
<img src="https://github.com/Ricecrackerz/AnimeHub/blob/master/AnimeHub_Figma.gif" width=500>

## Schema 

### Models

#### Discussion Post
| Property    | Type | Description |
| ----------- | ----------- | ---------- |
| objectId    | String      | unique id for the user post (default field) |
| username    | Pointer to User  | name of the author|
| animeTitle  | String      | title of the anime being discussed |
| postTitle   | String      | title of the post|
| createdAt   | DateTime    | date when post is created (default field)|
| updatedAt   | DateTime    | date when post is last updated (default field)| 

#### Anime
|Property    | Type | Description |
| ----------- | ----------- | ---------- |
| objectId   | int       | unique numericl id from JSON |
| animeTitle | String    | title of the anime being discussed |
| animeRating| double    | rating of the viewed anime |
| animeOverview | String | summary of the anime being viewed |
| animePosterPath | String | visual image for the anime |

### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]

* Signup Page
    * (Create/POST) Can make a new Username and Password - to Parse
* Login Page
    * (Read/GET) Authenticate the username and password - from Parse
* Home Page (Anime List Page)
    *  (Read/GET) Query all anime to show in the list - from Kitsu
    *  (Read/GET) Query ratings for each anime - from Kitsu
    *  *Optional:(If long press for favorite is implemented)* (Create/POST) A link to the anime favorited - to Parse
* Anime Detail Screen
    * (Read/GET) Query the details of the anime (description/rating) - from Kitsu
    * *Optional: (Read/GET) Query the YouTube trailer URL to show the trailer* - from Kitsu
    * ***Optional: (Create/POST) Add the interested anime to a user's anime list???*** - to Parse
* Discussion Page
    * (Read/GET) Query the list of discussions - from Parse
    * (Read/GET) Query some user information for each discussion - from Parse
    * (Create/POST) Create a new discussion - to Parse
* Discussion Detail Page
    * (Read/GET) Query the details of the discussion post - from Parse
    * (Read/GET) Show comments on the discussion post - from Parse
    * (Create/POST) Add a comment to the discussion post - to Parse
* Profile Page
    * *Optional:(Read/GET) Query the user's watchlist of anime - from Parse*
    * *Optional:(Read/GET) Use the user's watchlist from Parse to show the anime - from Kitsu*
    * *Optional:(Read/GET) Query the user's discussion posts - from Parse*


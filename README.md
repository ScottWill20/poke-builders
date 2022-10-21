# poke-builders

## Overview
Pokemon builder web application

## User Stories

### The unauthenticated user:
As a unauthenticated user, I want to view Pokemon in the public forum and create a Pokemon without saving to an account in order to experiment with creating Pokemon. 
* The unauthenticated user lands on the homepage, which is a public forum of pokemon that have been created by other authenticated site users.
### Unauthenticated User Creates an Account
* The unauthenticated user can click 'Create an Account' in the navigation bar, be directed to a account creation page, and fill out form details including email, username, password, birthday, and favorite official pokemon to create an account.
    * emails must be a valid email (required HTML input).
        * compare email against list of all emails to determine its uniqueness.
    * username must be less than or equal to 25 characters and only contain letters and numbers with no spaces required HTML input).
    * password must be minimum 10 characters and maximum 30 characters with no spaces. Must contain at least 1 of each:
        * capital letter
        * lowercase letter
        * number
        * symbol
    * *the combination of email and password makes a user unique*
    * birthday is not required 
    * favorite official pokemon (select from list with search bar) or if user selects [RANDOM] from dropdown list, randomly choose a pokemon to represent their profile icon from PokeAPI (required HTML input).
* The unauthenticated user will submit the form and upon verification of form input requirements will be automatically logged in. If the user is not verified/form inputs do not meet domain layer requirements they will be prompted by error messages on the form to attempt to create an account again. 
### The Unauthenticated User Attempts to Create a Pokemon
* The unauthenticated user can navigate to the 'Create a Pokemon' page via a link in the navigation bar, directing them to a form and drawing tool to experiment with the pokemon creation tools and form inputs. 
    * Form inputs for information about their pokemon include: 
        * name (text, required)
        * height (meters) (number, required)
        * weight (kg) (number, required)
        * vibes (nature) (text, required)
        * types (maximum 2 types) (checkboxes/multi-select, required)
            * *type must be determined before moves can be selected*
        * ability (max 1) (select, not required)
        * birthday (default: current date)
        * moves (max 4) (multi-select, required)
    * drawing tool (HTML canvas element):
        * pencil icon
            * line width
        * eraser icon
            * line width
        * color wheel
        * (stretch goal) palette presets of previously used colors
        * undo/re-do buttons
        * clear entire canvas button


* The unauthenticated user can attempt to submit their pokemon, but will be prompted by popup to create an account. Otherwise, the user will be notified that their pokemon will not be saved. 
* If the unauthenticated user chooses to not make an account, they will click the 'Cancel' button and be able to continue drawing to their heart's content.
* The unauthenticated user can then eventually choose to return to the homepage or leave the site by closing the browser tab.

### The authenticated user:
As an authenticated user, I want to view Pokemon in the public forum, post Pokemon to the public forum, and create Pokemon that will be saved to my account.
* Dependent upon: As a new user, I want to create an account.
* Dependent upon: As an unauthenticated user, I want to log in, so I can use features that require authentication.
* The authenticated user will arrive at the homepage with the option to 'Login' in their navigation bar. 
* The authenticated user will click 'Login', and via popup will be prompted to enter their username and password. 
* If the user is verified, their navigation bar will now include an icon that gives them options to go to their profile. If the user is not verified, they will receive a message in the popup window informing them their account was not found/bad credentials. 
* The authenticated user has all the rights and permissions of unauthenticated users, with the addition of creating pokemon in the 'Create a Pokemon' form and being able to save their creations to their profile.
* The authenticated user can navigate to their profile, to view their saved pokemon. 
* Each pokemon in the list will include a checkbox toggle to be visible in the public forum or to remain private (default).
* Each pokemon in list will include an 'Edit' button and 'Delete' button. 
* The authenticated user, upon selecting 'Edit' will be directed to the form and drawing tool creation set up, where the form and canvas will be pre-populated with the previous values of their saved pokemon. The authenticated user can change these values and re-save their pokemon. 
* The authenticated user, upon selecting 'Delete' will be directed to a page that includes their pokemon details and prompts the user to confirm that they would like to delete this pokemon.
* Upon confirmation, the pokemon will be deleted and the authenticated user will be redirected back to their profile list of pokemon. If the authenticated user cancels the deletion, they will be redirected back to their profile list of pokemon without deleting the pokemon. If the deleted pokemon was on the public forum, it will be taken out as well.
* When the authenticated user is done using the app, they can select 'Logout' from the navigation bar and exit the application, being redirected to the homepage as an unauthenticated user. 



### (Stretch Goal) The moderator:
As an app moderator, I want to view Pokemon in the public forum, and hide Pokemon from the public forum should they not meet public Pokemon criteria. I also am an authenticated user.
* Dependent upon: As a new user, I want to create an account.
* Dependent upon: As an unauthenticated user, I want to log in, so I can use features that require authentication.
* Dependent upon: I have been approved by an administrator to become a moderator and use features that require moderator authorization.
* The moderator has all the rights and permissions of authenticated users, with the addition of hiding public forum pokemon that are deemed inappropriate.
* A moderator's icon has a moderator badge.
* As a moderator, I am able to hide pokemon from the public forum by clicking the toggle if the pokemon does not meet community standards
* Community standards:
  * Only sfw pokemon are allowed
  * No imagery with racist, homophobic or otherwise discriminatory undertones are allowed
* When a moderator hides a pokemon from the public forum, the hidden pokemon is added to a list that only the administrator can view.

### (Stretch Goal) The administrator:
As an administrator, I want to view Pokemon in the public forum, and hide Pokemon from the public forum should they not meet public Pokemon criteria. I also want to add moderators and remove moderators. 
* Dependent upon: As an unauthenticated user, I want to log in, so I can use features that require authentication.
* The administrator has all the rights and permissions of a moderator in addition to suspending and deleting accounts, as well as adding and removing moderators.
* The administrator can see a list of pokemon that moderators have flagged and decide if a pokemon in the list meets community standards. 
* If a user has more than 3 strikes (3 of their public pokemon have been removed from the public forum and all deemed by the administrator to not meet community standards), the administrator can suspend the user's account by clicking a suspend user button.
* The administrator can track a list of accounts that have been suspended such that they can delete any of the suspended accounts if the owner gets suspended twice.
* The administrator can make a user a moderator by clicking a make mod button on the user's profile
* The administrator can remove moderator privileges from a current moderator if the moderator decides not to moderate anymore or the moderator abused moderator privileges by hiding pokemon needlessly by clicking a demote mod button on the moderator's profile.



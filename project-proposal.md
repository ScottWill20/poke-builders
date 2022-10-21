# PokeBuilders Project Proposal

## Problem Statement
Pokemon is one of the most successful video game franchises in the world, and has expanded into anime, television, movies, and merchandise. It is widely beloved by children and adults who share in the nostalgia of one of their favorite games.

In the game, Pokemon exist out in the wild to be caught and trained by Pokemon trainers, with whom they will eventually battle and win fights against other Pokemon and Pokemon trainers. However, there is one thing that players are not able to do -- make their own Pokemon!! While there are a myriad of Pokemon that exist as part of the game (982 to be exact), there isn't the ability to design and completely customize your own Pokemon, with their own characteristics decided by YOU.

## Technical Overview
Create an application for drawing and defining the characteristics of your very own Pokemon. Share your Pokemon with other creators.

### Scenario 1
Jane really likes the design of Eevee (her favorite Pokemon), but wishes that Eevee had more fun colors (brown is too natural) and a more exciting Pokemon type, like "Ghost" or "Fairy." She uses the PokeBuilder application to design her dream Eevee and is able to share her ideal Eevee with her friends. 

### Scenario 2
Delanie has been playing Pokemon for years, and is tired of the same old cute Pokemon designs. Why can't a skyscraper be a Pokemon? Why not a bike? She thinks there is so much potential for what Pokemon could be, and uses the PokeBuilder application to experiment and create the wildest Pokemon she can think of. 

## Glossary

### Pokemon
A creature created by users that can be saved and shared. Each Pokemon has a name, height, weight, birthday, type, ability, nature, and moves it can perform. 

### User
Anyone who enters the application and can view, create, and share Pokemon. 

## High Level Requirements
* Create a Pokemon (USER, authenticated)
* Edit a Pokemon (authenticated)
* Delete a Pokemon (authenticated)
* View all public Pokemon (USER)
* View all private Pokemon that belong to you (authenticated)

## User Stories/Scenarios
### Create a Pokemon
Suggested data:
* Pokemon Characteristics Form Inputs:
    * Name (text)
    * Birthday (date cannot be in the future)
    * Height
    * Weight
    * Type (choose from dropdown)
    * Ability (choose from dropdown)
    * Nature (choose from dropdown)
    * Moves (select four from list)
* Pokemon Drawing Tool 
    * Draw anything! (that falls within community guidelines).
    * All colors
    * One pencil tool
    * One eraser tool
    * Undo button
    * Redo button
    * Clear all button

**No Precondition (USER)**
<br>**Precondition (authenticated):** User must be logged in with the authenticated user role in order to save their work.
<br>**Post-condition (authenticated):** If the user is authenticated, they can see their Pokemon in their profile. If they choose to make their Pokemon public, they can see it posted to the public forum. 

### Edit a Pokemon
* Users can only edit their saved Pokemon. 
* They can edit all characteristics and the drawing of their Pokemon except the Pokemon's birthday.

**Precondition:** User must be logged in with the authenticated user role in order to edit their work.
**Post-condition:** If the user is authenticated, they can see their edited Pokemon in their profile. If they choose to make their Pokemon public, they can see it posted/edited in the public forum. 

### Delete a Pokemon 
* Users can only delete their saved Pokemon. 

**Precondition:** User must be logged in with the authenticated user role in order to delete their work.
**Post-condition:** If the user is authenticated, they will no longer see their Pokemon in their profile. If they chose to make their Pokemon public, they will no longer see it posted in the public forum. 

### View All Public Pokemon
* Present all public Pokemon as grid of Pokemon cards with an image of the drawing of the Pokemon, the Pokemon's name, and important characteristics. 

**Precondition:** None
<br>**Post-condition:** None

### View All Private Pokemon That Belong to You
* Present all Pokemon that belong to a specific user in their profile page as grid of Pokemon cards with an image of the drawing of the Pokemon, the Pokemon's name, and important characteristics. 

**Precondition:** User must be logged in with the authenticated user role in order to view their Pokemon.
**Post-condition:** None

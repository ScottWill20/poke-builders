# Tasks
## Tables
* pokemon
* move
* poke_move (bridge: many-to-many pokemon and move)
* type
* poke_type
(bridge: many-to-many pokemon type)
* ability
* vibe
* app_user
* app_role
* app_user_role (bridge: app_user and app_role)

## Database Tasks
- [ ] Create database schema diagram and DDL (1 hr)
- [ ] Database DML (30 mins)
- [ ] Create test database (30 mins)

## Model tasks
- [ ] Create pokemon model (2 hr)
- [ ] Create type model (with a list of moves) (1 hr)
- [ ] Create vibe model (enum) (1 hr)
- [ ] Create ability model (1 hr)
- [ ] Create move model (1 hr)
- [ ] Create user model (1 hr)

## Repository tasks
- [ ] Create pokemon repository interface (30 mins)
- [ ] Create pokemon jdbcrepository (30 mins)
- [ ] Create findAllPublicPokemon method in pokemon jdbcrepository (45 mins)
- [ ] Create findByUserId method in pokemon jdbcrepository (45 mins)
- [ ] Create findByPokemonId method in pokemon jdbcrepository (45 mins)
- [ ] Create createPokemon(pokemon) method in pokemon jdbcrepository (45 mins)
- [ ] Create updatePokemon(pokemonId) method in pokemon jdbcrepository (45 mins)
- [ ] Create deletePokemon(pokemonId) method in pokemon jdbcrepository (45 mins)
- [ ] Create type repository interface (30 mins)
- [ ] Create type jdbcrepository (30 mins)
- [ ] Create findByName(String) method in type jdbc repository (20 minutes)
- [ ] Create vibe repository interface (1 hr)
- [ ] Create vibe jdbcrepository (1 hr)
- [ ] Create findByName(String) method in vibe jdbc repository (20 minutes)
- [ ] Create move repository interface (1 hr)
- [ ] Create move jdbcrepository (1 hr)
- [ ] Create findByType(String) method in move jdbc repository (20 minutes)
- [ ] Create ability repository interface (1 hr)
- [ ] Create ability jdbcrepository (1 hr)
- [ ] Create findByName(String) method in ability jdbc repository (20 minutes)
- [ ] Create user repository interface (1 hr)
- [ ] Create user jdbcrepository (1 hr)
- [ ] Create findByEmail(String) method in user jdbc repository (20 minutes)
- [ ] Create createUser(user) method in user jdbcrepository (45 mins)
- [ ] Create updateUser(userId) method in user jdbcrepository (45 mins)
- [ ] Create deleteUser(userId) method in user jdbcrepository (45 mins)

## Repository Test Tasks
- [ ] shouldFind (1 hr)
- [ ] shouldNotFind (1 hr)
- [ ] shouldCreate (1 hr)
- [ ] shouldNotCreate (1 hr)
- [ ] shouldUpdate (1 hr)
- [ ] shouldNotUpdate (1 hr)
- [ ] shouldCDelete (1 hr)
- [ ] shouldNotDelete (1 hr)

## Service Tasks
- [ ] Create pokemon service (2 hr)
- [ ] Create findAllPublicPokemon method in pokemon service (45 mins)
- [ ] Create findByUserId method in pokemon service (45 mins)
- [ ] Create findByPokemonId method in pokemon service (45 mins)
- [ ] Create createPokemon(pokemon) method in pokemon service (45 mins)
- [ ] Create updatePokemon(pokemonId) method in pokemon service (45 mins)
- [ ] Create deletePokemon(pokemonId) method in pokemon service (45 mins)
- [ ] Create move service (with a list of moves) (1 hr)
- [ ] Create findByType(String) method in move service (20 minutes)
- [ ] Create user service (1 hr)
- [ ] Create loadUserByUsername(String) method in user service (20 minutes)
- [ ] Create createUser(user) method in user service (45 mins)
- [ ] Create updateUser(userId) method in user service (45 mins)
- [ ] Create deleteUser(userId) method in user service (45 mins)

## Service Test Tasks
- [ ] shouldFind (1 hr)
- [ ] shouldNotFind (1 hr)
- [ ] shouldCreate (1 hr)
- [ ] shouldNotCreate (1 hr)
- [ ] shouldUpdate (1 hr)
- [ ] shouldNotUpdate (1 hr)
- [ ] shouldCDelete (1 hr)
- [ ] shouldNotDelete (1 hr)

## Controller Tasks
- [ ] Create pokemon controller (2hrs)
- [ ] Create getMapping for public pokemon (30 minutes)
- [ ] Create postMapping for creating pokemon (30 minutes)
- [ ] Create putMapping for editing pokemon (30 minutes)
- [ ] Create deleteMapping for deleting pokemon (30 minutes)
- [ ] Create user controller (2hrs)
- [ ] Create getMapping for private pokemon using userId (30 minutes)
- [ ] Create postMapping for creating user accounts (30 minutes)
- [ ] Create deleteMapping for deleting user accounts (30 minutes)
- [ ] Create auth controller (2hrs)
- [ ] Create postMapping for authenticating users (1hr)

## Front-End tasks
- [ ] Create Navigation Component (3 hrs)
- [ ] Create Pokemon Creation component (3 hrs)
- [ ] Create Homepage component (3 hrs)
- [ ] Create User profile component (3 hrs)
- [ ] Create Login component (3 hrs)
- [ ] Create Account Creation component (3 hrs)


# Tasks
## Tables
* pokemon
* moves
* poke_move (bridge: many-to-many pokemon and moves)
* type
* poke_type
(bridge: many-to-many pokemon type)
* ability
* vibes
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
- [ ] Create vibes model (enum) (1 hr)
- [ ] Create ability model (1 hr)
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
- [ ] Create vibes repository interface (1 hr)
- [ ] Create vibes jdbcrepository (1 hr)
- [ ] Create findByName(String) method in vibes jdbc repository (20 minutes)
- [ ] Create moves repository interface (1 hr)
- [ ] Create moves jdbcrepository (1 hr)
- [ ] Create findByType(String) method in moves jdbc repository (20 minutes)
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
- [ ] shouldFind 
- [ ] shouldNotFind
- [ ] shouldCreate
- [ ] shouldNotCreate
- [ ] shouldUpdate
- [ ] shouldNotUpdate
- [ ] shouldCDelete
- [ ] shouldNotDelete



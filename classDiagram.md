```
src
├───main
│   └───java
│       └───capstone
│           └───pokebuilder
│               │   App.java                                -- app entry point
│               │
│               ├───data
│               │       UserRepository.java                 -- repository interface
│               │       UserJdbcRepository.java             -- concrete repository
│               │       AbilityRepository.java              -- repository interface
│               │       AbilityJdbcRepository.java          -- concrete repository
│               │       VibeRepository.java                 -- repository interface
│               │       VibeJdbcRepository.java             -- concrete repository
│               │       TypeRepository.java                 -- repository interface
│               │       TypeJdbcRepository.java             -- concrete repository
│               │       PokemonRepository.java              -- repository interface
│               │       PokemonJdbcRepository.java          -- concrete repository
│               │
│               ├───domain
│               │       Result.java                         -- domain result for handling success/failure
│               │       ResultType.java                     -- result enum for success, invalid or not found
│               │       UserService.java                    -- user validation/rules
│               │       AbilityService.java                 -- ability validation/rules
│               │       VibeService.java                    -- vibe validation/rules
│               │       TypeService.java                    -- type validation/rules
│               │       PokemonService.java                 -- pokemon validation/rules 
│               │
│               ├───models
│               │       User.java                           -- user model
│               │       Ability.java                        -- ability model
│               │       Vibe.java                           -- vibe model
│               │       Type.java                           -- type model
│               │       Pokemon.java                        -- pokemon model 
│               │
│               ├───controllers
│               │       AuthController.java                 -- UI controller
│               │       GlobalExceptionHandler.java         -- exception handler
│               │       UserController.java                 -- user endpoint and functionality
│               │       PokemonController.java              -- pokemon endpoint and functionality
│               │
│               ├───security
│               │       AppConfig.java                      -- app configuration
│               │       SecurityConfig.java                 -- app security configuration
│               │       JwtConverter.java                   -- jwt token converter
│               │       JwtRequestFilter.java               -- jwt request filter 
│
│
└───test
    └───java
        └───capstone
            └───pokebuilder
                ├───data
                │       UserRepositoryTest.java            -- user repo tests
                │       AbilityRepositoryTest.java         -- ability repo tests
                │       VibeRepositoryTest.java            -- vibe repo tests
                │       TypeRepositoryTest.java            -- type repo tests
                │       PokemonRepositoryTest.java         -- pokemon repo tests 
                └───domain
                │       UserService.java                   -- user service tests
                │       AbilityService.java                -- ability service tests
                │       VibeService.java                   -- vibe service tests
                │       TypeService.java                   -- type service tests
                │       PokemonService.java                -- pokemon service tests 

```
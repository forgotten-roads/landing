# Development Plan

## Anticipated Dataflow

### Login

1. User attempts to access protected / admin site
2. User is redirected to login form
   a. login form is generated with Clojure Enlive
   b. templates from Authenty Bootstrap project
3. User enters correct credentials and is redirected to admin site

This flow assumes the following is in place:
 * roles have been defined
 * specific users have been assigned to roles
 * resources are guarded (Clojure Friend is installed and /admin is protected)
 * login forms have been selected
   - sign up
   - sign in
   - forgot password
   - incorrect credentials
 * templatized forms have been developed by converting bootstrap HTML to Clojure Enlive
 * a login resource hass been defined
 * assoicated login functions have been defined
 * support has been enabled for OAuth2
   - Google
   - Twitter
   - Github
 * a callback URL resource has been defined
 * an event system has been put in place
 * notifications are sent for logins (both successful and otherwise)
 * user profile data is extracted from OAuth2 provider
 * a non-reversible hash/id is generated from the user data available from the
   third-party authentication
 * user data is not stored, only referenced
 * support has been enabled for creating user sessions
 * support has been enabled for deleting user sessions
 * a session schema has been set up in Redis
 * Redis has been setup to support persistent / disk storage
 * a means of migrating schemas in the database has been devised
 * appropriate data/links/associations get stored in the user session

### Creating Article Categories

TBD

### Creating Article

TBD

### Displaying Categories

TBD

### Listing Articles

TBD

### Viewing Article

TBD

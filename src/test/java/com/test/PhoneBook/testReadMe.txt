To use embeded database for testing purpose
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")

MySql
@AutoConfigureTestDatabase(replace = NONE)
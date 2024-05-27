package boulder.be.integration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import boulder.be.model.Subscription;
import boulder.be.model.TenTimesPass;
import boulder.be.model.User;
import boulder.be.repository.DbInitializer;
import boulder.be.repository.SubscriptionRepository;
import boulder.be.repository.TenTimesPassRepository;
import boulder.be.unit.repository.UserRepositoryTestImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
@Sql("classpath:schema.sql")
public class UserintegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepositoryTestImpl userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private TenTimesPassRepository tenTimesPassRepository;

    @Autowired
    private DbInitializer dbInitializer;

    @BeforeEach
    public void setup() {
        dbInitializer.initialize();
    }

    @Test
    public void givenUsers_whenGetAllUsersWithLastNameFilter_thenFilteredUsersReturned() {
        // Test retrieving all users filtered by name
        webTestClient.get()
                .uri("/users/search?last_name=Delvaux")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\n" + //
                        "  \"subscription\": [\n" + //
                        "    {\n" + //
                        "      \"type\": \"1MONTH\",\n" + //
                        "      \"startDate\": \"2024-05-23\",\n" + //
                        "      \"endDate\": \"2024-06-23\",\n" + //
                        "      \"isActive\": \"TRUE\"\n" + //
                        "    }\n" + //
                        "  ],\n" + //
                        "  \"id\": 1,\n" + //
                        "  \"firstName\": \"Wiebe\",\n" + //
                        "  \"name\": \"Delvaux\",\n" + //
                        "  \"email\": \"wiebe.delvaux@gmail.com\",\n" + //
                        "  \"birthDate\": \"2004-11-24\",\n" + //
                        "  \"isStudent\": true,\n" + //
                        "  \"age\": 19\n" + //
                        "}");
    }

    @Test
    public void givenUsers_whenGetAllStudents_thenAllStudentsReturned() {
        // Test retrieving all students
        webTestClient.get()
                .uri("/users/students")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("[\r\n" + //
                        "  {\r\n" + //
                        "    \"subscription\": [\r\n" + //
                        "      {\r\n" + //
                        "        \"type\": \"1MONTH\",\r\n" + //
                        "        \"startDate\": \"2024-05-23\",\r\n" + //
                        "        \"endDate\": \"2024-06-23\",\r\n" + //
                        "        \"isActive\": \"TRUE\"\r\n" + //
                        "      }\r\n" + //
                        "    ],\r\n" + //
                        "    \"id\": 1,\r\n" + //
                        "    \"firstName\": \"Wiebe\",\r\n" + //
                        "    \"name\": \"Delvaux\",\r\n" + //
                        "    \"email\": \"wiebe.delvaux@gmail.com\",\r\n" + //
                        "    \"birthDate\": \"2004-11-24\",\r\n" + //
                        "    \"isStudent\": true,\r\n" + //
                        "    \"age\": 19\r\n" + //
                        "  },\r\n" + //
                        "  {\r\n" + //
                        "    \"subscription\": [\r\n" + //
                        "      {\r\n" + //
                        "        \"type\": \"3MONTH\",\r\n" + //
                        "        \"startDate\": \"2024-02-05\",\r\n" + //
                        "        \"endDate\": \"2024-05-05\",\r\n" + //
                        "        \"isActive\": \"EXPIRED\"\r\n" + //
                        "      }\r\n" + //
                        "    ],\r\n" + //
                        "    \"id\": 5,\r\n" + //
                        "    \"firstName\": \"Bob\",\r\n" + //
                        "    \"name\": \"Brown\",\r\n" + //
                        "    \"email\": \"bob.brown@example.com\",\r\n" + //
                        "    \"birthDate\": \"2000-07-30\",\r\n" + //
                        "    \"isStudent\": true,\r\n" + //
                        "    \"age\": 23\r\n" + //
                        "  },\r\n" + //
                        "  {\r\n" + //
                        "    \"id\": 7,\r\n" + //
                        "    \"firstName\": \"Eve\",\r\n" + //
                        "    \"name\": \"Williams\",\r\n" + //
                        "    \"email\": \"eve.williams@example.com\",\r\n" + //
                        "    \"birthDate\": \"2001-04-12\",\r\n" + //
                        "    \"isStudent\": true,\r\n" + //
                        "    \"age\": 23\r\n" + //
                        "  },\r\n" + //
                        "  {\r\n" + //
                        "    \"tenTimesPass\": [\r\n" + //
                        "      {\r\n" + //
                        "        \"startDate\": \"2024-03-13\",\r\n" + //
                        "        \"endDate\": \"2025-03-13\",\r\n" + //
                        "        \"isActive\": \"TRUE\",\r\n" + //
                        "        \"entries\": 10\r\n" + //
                        "      }\r\n" + //
                        "    ],\r\n" + //
                        "    \"id\": 10,\r\n" + //
                        "    \"firstName\": \"Henry\",\r\n" + //
                        "    \"name\": \"Martinez\",\r\n" + //
                        "    \"email\": \"henry.martinez@example.com\",\r\n" + //
                        "    \"birthDate\": \"2003-09-02\",\r\n" + //
                        "    \"isStudent\": true,\r\n" + //
                        "    \"age\": 20\r\n" + //
                        "  },\r\n" + //
                        "  {\r\n" + //
                        "    \"tenTimesPass\": [\r\n" + //
                        "      {\r\n" + //
                        "        \"startDate\": \"2024-06-07\",\r\n" + //
                        "        \"endDate\": \"2025-06-07\",\r\n" + //
                        "        \"isActive\": \"NOT ACTIVE: will be active from: 2024-06-07\",\r\n" + //
                        "        \"entries\": 10\r\n" + //
                        "      }\r\n" + //
                        "    ],\r\n" + //
                        "    \"id\": 12,\r\n" + //
                        "    \"firstName\": \"Jack\",\r\n" + //
                        "    \"name\": \"Lewis\",\r\n" + //
                        "    \"email\": \"jack.lewis@example.com\",\r\n" + //
                        "    \"birthDate\": \"2002-01-29\",\r\n" + //
                        "    \"isStudent\": true,\r\n" + //
                        "    \"age\": 22\r\n" + //
                        "  }\r\n" + //
                        "]");
    }

    @Test
    public void givenUsers_whenGetUserByEmail_thenUserReturned() {
        // Test retrieving user by email
        webTestClient.get()
                .uri("/users/jack.lewis@example.com")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\r\n" + //
                        "  \"tenTimesPass\": [\r\n" + //
                        "    {\r\n" + //
                        "      \"startDate\": \"2024-06-07\",\r\n" + //
                        "      \"endDate\": \"2025-06-07\",\r\n" + //
                        "      \"isActive\": \"NOT ACTIVE: will be active from: 2024-06-07\",\r\n" + //
                        "      \"entries\": 10\r\n" + //
                        "    }\r\n" + //
                        "  ],\r\n" + //
                        "  \"id\": 12,\r\n" + //
                        "  \"firstName\": \"Jack\",\r\n" + //
                        "  \"name\": \"Lewis\",\r\n" + //
                        "  \"email\": \"jack.lewis@example.com\",\r\n" + //
                        "  \"birthDate\": \"2002-01-29\",\r\n" + //
                        "  \"isStudent\": true,\r\n" + //
                        "  \"age\": 22\r\n" + //
                        "}");
    }

    @Test
    public void givenUsers_whenGetUsersByFirstName_thenFilteredUsersReturned() {
        // Test retrieving users by first or last name
        webTestClient.get()
                .uri("/users/search?first_name=Wiebe")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\r\n" + //
                        "  \"subscription\": [\r\n" + //
                        "    {\r\n" + //
                        "      \"type\": \"1MONTH\",\r\n" + //
                        "      \"startDate\": \"2024-05-23\",\r\n" + //
                        "      \"endDate\": \"2024-06-23\",\r\n" + //
                        "      \"isActive\": \"TRUE\"\r\n" + //
                        "    }\r\n" + //
                        "  ],\r\n" + //
                        "  \"id\": 1,\r\n" + //
                        "  \"firstName\": \"Wiebe\",\r\n" + //
                        "  \"name\": \"Delvaux\",\r\n" + //
                        "  \"email\": \"wiebe.delvaux@gmail.com\",\r\n" + //
                        "  \"birthDate\": \"2004-11-24\",\r\n" + //
                        "  \"isStudent\": true,\r\n" + //
                        "  \"age\": 19\r\n" + //
                        "}");
    }

    @Test
    public void givenUsers_whenScanUserByEmailWithSubscription_thenUserSubscriptionReturned() {
        // Test scanning user by email
        webTestClient.get()
                .uri("/users/wiebe.delvaux@gmail.com/scan")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\r\n" + //
                        "  \"subscription\": [\r\n" + //
                        "    {\r\n" + //
                        "      \"type\": \"1MONTH\",\r\n" + //
                        "      \"startDate\": \"2024-05-23\",\r\n" + //
                        "      \"endDate\": \"2024-06-23\",\r\n" + //
                        "      \"isActive\": \"TRUE\"\r\n" + //
                        "    }\r\n" + //
                        "  ],\r\n" + //
                        "  \"id\": 1,\r\n" + //
                        "  \"firstName\": \"Wiebe\",\r\n" + //
                        "  \"name\": \"Delvaux\",\r\n" + //
                        "  \"email\": \"wiebe.delvaux@gmail.com\",\r\n" + //
                        "  \"birthDate\": \"2004-11-24\",\r\n" + //
                        "  \"isStudent\": true,\r\n" + //
                        "  \"age\": 19\r\n" + //
                        "}");
    }

    @Test
    public void givenUsers_whenScanUserByEmailWithTenTimes_thenUserTenTimesReturned() {
        // Test scanning user by email
        webTestClient.get()
                .uri("/users/jack.lewis@example.com/scan")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\r\n" + //
                        "  \"tenTimesPass\": [\r\n" + //
                        "    {\r\n" + //
                        "      \"startDate\": \"2024-06-07\",\r\n" + //
                        "      \"endDate\": \"2025-06-07\",\r\n" + //
                        "      \"isActive\": \"NOT ACTIVE: will be active from: 2024-06-07\",\r\n" + //
                        "      \"entries\": 9\r\n" + //
                        "    }\r\n" + //
                        "  ],\r\n" + //
                        "  \"id\": 12,\r\n" + //
                        "  \"firstName\": \"Jack\",\r\n" + //
                        "  \"name\": \"Lewis\",\r\n" + //
                        "  \"email\": \"jack.lewis@example.com\",\r\n" + //
                        "  \"birthDate\": \"2002-01-29\",\r\n" + //
                        "  \"isStudent\": true,\r\n" + //
                        "  \"age\": 22\r\n" + //
                        "}");
    }

    @Test
    public void givenUser_whenAddUser_thenUserAdded() {
        // Test adding a user
        webTestClient.post()
                .uri("/users")
                .header("Content-Type", "application/json")
                .bodyValue("{\n" + //
                        "  \"firstName\": \"Jan\",\n" + //
                        "  \"name\": \"Bollard\",\n" + //
                        "  \"birthDate\": \"2002-05-20\",\n" + //
                        "  \"email\": \"jan.bollard@gmail.com\",\n" + //
                        "  \"isStudent\": true\n" + //
                        "}")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\n" + //
                        "  \"id\": 16,\n" + //
                        "  \"firstName\": \"Jan\",\n" + //
                        "  \"name\": \"Bollard\",\n" + //
                        "  \"email\": \"jan.bollard@gmail.com\",\n" + //
                        "  \"birthDate\": \"2002-05-20\",\n" + //
                        "  \"isStudent\": true,\n" + //
                        "  \"age\": 22\n" + //
                        "}");

        assertTrue(userRepository.existsByEmail("jan.bollard@gmail.com"));
    }

    @Test
    public void givenUser_whenAddSubscription_thenSubscriptionAdded() {
        // Test adding a subscription to user
        webTestClient.post()
                .uri("/users/jack.sparrow@example.com/subscription")
                .header("Content-Type", "application/json")
                .bodyValue("[\n" + //
                        "  {\n" + //
                        "    \"type\" : \"6MONTH\" ,\n" + //
                        "    \"startDate\" : \"2024-05-24\"\n" + //
                        "  }\n" + //
                        "]")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\r\n" + //
                        "  \"subscription\": [\r\n" + //
                        "    {\r\n" + //
                        "      \"type\": \"6MONTH\",\r\n" + //
                        "      \"startDate\": \"2024-05-24\",\r\n" + //
                        "      \"endDate\": \"2024-11-24\",\r\n" + //
                        "      \"isActive\": \"TRUE\"\r\n" + //
                        "    }\r\n" + //
                        "  ],\r\n" + //
                        "  \"id\": 15,\r\n" + //
                        "  \"firstName\": \"Jack\",\r\n" + //
                        "  \"name\": \"Sparrow\",\r\n" + //
                        "  \"email\": \"jack.sparrow@example.com\",\r\n" + //
                        "  \"birthDate\": \"1974-01-29\",\r\n" + //
                        "  \"isStudent\": false,\r\n" + //
                        "  \"age\": 50\r\n" + //
                        "}");
    }

    @Test
    public void givenUser_whenAddTenTimesPass_thenTenTimesPassAdded() {
        // Test adding a ten times pass to user
        webTestClient.post()
                .uri("/users/john.wick@example.com/tentimes")
                .header("Content-Type", "application/json")
                .bodyValue("""
                        [
                            {
                              "startDate" : "2024-05-24"
                            }
                          ]
                            """)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("""
                        {
                            "tenTimesPass": [
                              {
                                "startDate": "2024-05-24",
                                "endDate": "2025-05-24",
                                "isActive": "TRUE",
                                "entries": 10
                              }
                            ],
                            "id": 13,
                            "firstName": "John",
                            "name": "Wick",
                            "email": "john.wick@example.com",
                            "birthDate": "1965-04-02",
                            "isStudent": false,
                            "age": 59
                          }
                            """);
    }

    @Test
    public void givenUser_whenUpdateUser_thenUserUpdated() {
        // Test updating user information
        webTestClient.put()
                .uri("/users/john.doe@example.com")
                .header("Content-Type", "application/json")
                .bodyValue("{\n" + //
                        "  \"firstName\": \"Jon\",\n" + //
                        "  \"name\": \"Doodle\",\n" + //
                        "  \"birthDate\": \"1990-05-16\",\n" + //
                        "  \"email\": \"jon.doodle@example.com\",\n" + //
                        "  \"isStudent\": false\n" + //
                        "}")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\r\n" + //
                        "  \"subscription\": [\r\n" + //
                        "    {\r\n" + //
                        "      \"type\": \"3MONTH\",\r\n" + //
                        "      \"startDate\": \"2024-01-20\",\r\n" + //
                        "      \"endDate\": \"2024-04-20\",\r\n" + //
                        "      \"isActive\": \"EXPIRED\"\r\n" + //
                        "    }\r\n" + //
                        "  ],\r\n" + //
                        "  \"id\": 2,\r\n" + //
                        "  \"firstName\": \"Jon\",\r\n" + //
                        "  \"name\": \"Doodle\",\r\n" + //
                        "  \"email\": \"jon.doodle@example.com\",\r\n" + //
                        "  \"birthDate\": \"1990-05-16\",\r\n" + //
                        "  \"isStudent\": false,\r\n" + //
                        "  \"age\": 34\r\n" + //
                        "}");
        assertTrue(userRepository.existsByEmail("jon.doodle@example.com"));
    }

    @Test
    public void givenUsers_whenDeleteUser_thenUserDeleted() {
        // Test deleting a user
        webTestClient.delete()
                .uri("/users/jane.smith@example.com")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\r\n" + //
                        "  \"message\": \"User with email: jane.smith@example.com has been deleted.\"\r\n" + //
                        "}");
        assertFalse(userRepository.existsByEmail("jane.smith@example.com"));
    }

    @Test
    public void givenUser_whenDeleteSubscription_thenSubscriptionDeleted() {
        // Test deleting a subscription from user
        User user = userRepository.findByEmail("charlie.davis@example.com");
        List<Subscription> subscriptions = subscriptionRepository.findByUser(user);
        webTestClient.delete()
                .uri("/users/charlie.davis@example.com/subscription")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\r\n" + //
                        "  \"message\": \"Subscription from user with email: charlie.davis@example.com has been deleted.\"\r\n"
                        + //
                        "}");
        subscriptions = subscriptionRepository.findByUser(user);
        assertTrue(subscriptions.isEmpty());
    }

    @Test
    public void givenUser_whenDeleteTenTimesPass_thenTenTimesPassDeleted() {
        // Test deleting a ten times pass from user
        User user = userRepository.findByEmail("ivy.clark@example.com");
        List<TenTimesPass> pass = tenTimesPassRepository.findByUser(user);
        webTestClient.delete()
                .uri("/users/ivy.clark@example.com/tentimespass")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\r\n" + //
                        "  \"message\": \"Ten times pass from user with email: ivy.clark@example.com has been deleted.\"\r\n"
                        + //
                        "}");
        pass = tenTimesPassRepository.findByUser(user);
        assertTrue(pass.isEmpty());
    }

}

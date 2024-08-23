# Reactive APIs with Spring

This repository provides a reference implementation of reactive APIs using Spring WebFlux. It demonstrates the use of `Mono` and `Flux`, which are the two main types in Project Reactor, the reactive library used by Spring WebFlux.

## Table of Contents
- [Introduction to Reactive Programming](#introduction-to-reactive-programming)
- [Project Reactor Overview](#project-reactor-overview)
    - [Mono](#mono)
    - [Flux](#flux)
- [Getting Started](#getting-started)
- [Example Usage](#example-usage)
    - [Handling Mono](#handling-mono)
    - [Handling Flux](#handling-flux)
- [Reactive Repositories](#reactive-repositories)
- [Testing Reactive APIs](#testing-reactive-apis)
- [Contributing](#contributing)
- [License](#license)

## Introduction to Reactive Programming

Reactive programming is a programming paradigm that deals with asynchronous data streams and the propagation of change. It is particularly useful for handling a large number of requests efficiently, making it ideal for modern web applications.

Spring WebFlux is a reactive web framework that provides support for building non-blocking, asynchronous applications. It uses Project Reactor as its core reactive library.

## Project Reactor Overview

Project Reactor provides two main types: `Mono` and `Flux`.

### Mono
A `Mono` represents a single or empty asynchronous value. It is used when you expect 0 or 1 result. You can think of it as a reactive equivalent of `Optional` or `CompletableFuture`.

#### Common Use Cases
- Fetching a single item from a database (For instance, returning a DTO after a save operation)
- Returning a single value as a response to an HTTP request

#### Example:
```java
Mono<String> mono = Mono.just("Hello, Reactive World!");
mono.subscribe(System.out::println);
```

### Flux
A `Flux` represents a stream of 0 to N items. It is used when you expect multiple results. A `Flux` can emit any number of items, including none.

#### Common Use Cases
- Streaming multiple items from a database (For instance, returning a list of objects after a get operation)
- Returning a list of items as a response to an HTTP request

#### Example:
```java
Flux<String> flux = Flux.just("Hello", "Reactive", "World");
flux.subscribe(System.out::println);
```

## Getting Started

To start using reactive programming with Spring WebFlux, you'll need to add the following dependency to your `pom.xml` (for Maven):

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

Or for Gradle:
```groovy
implementation 'org.springframework.boot:spring-boot-starter-webflux'
```

## Example Usage

### Handling Mono

```java
@GetMapping("/mono")
public Mono<String> getMono() {
    return Mono.just("Hello from Mono");
}
```

### Handling Flux

```java
@GetMapping("/flux")
public Flux<String> getFlux() {
    return Flux.just("Hello", "from", "Flux");
}
```

## Reactive Repositories

Spring Data provides reactive support for MongoDB, Cassandra, and more. Below is a simple example of a reactive repository for MongoDB:

```java
import dev.thesarfo.springflux.entity.Employee;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
    Flux<Employee> findByFirstName(String firstName);
}
```

Even though the above uses `ReactiveMongoRepository`, there are other implementations of Reactive Repositories you can adopt according to your use case. For instance, the  `ReactiveCrudRepository`

## Testing Reactive APIs

Testing reactive APIs requires a different approach. Spring provides `WebTestClient` for testing WebFlux applications. This offers a powerful way to quickly test WebFlux controllers without needing to start a full HTTP server. 

Spring also provides `@WebFluxTest` to test Spring WebFlux controllers. This annotation creates an application context that contains all the beans necessary to test a WebFlux controller. This annotation is also used in combination with `@MockBean` to provide mock implementations for required collaborators. 

```java
@WebFluxTest
class ExampleControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testMonoEndpoint() {
        webTestClient.get().uri("/mono")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class).isEqualTo("Hello from Mono");
    }
}
```
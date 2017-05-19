# Spring Java Config Project Sample

The purpose of this project is show how to using spring java config to build up a web application.

### Include Spring Features:

- **Web initialize without web.xml**
  - **Initializer Servlet**
  - **Initializer ApplicationContext, ServletContext**
  - **Register Filter**
- **Spring Core**
  - **DI Basic:**
  
    @Service, @Repository, @Configuration, @ComponentScan, @Autowired, @Bean, @Scope, FactoryBean, @Profile, @Qualifier, @PropertySource, @Value
  
  - **AOP:** 
  
    @EnableAspectJAutoProxy, @Aspect, @After, @annotation
  
- **Spring web mvc & Rest:** 

    @EnableWebMvc, @Controller, @RestController, @GetMapping, @PutMapping, @PostMapping, @PathVariable, @RequestBody, HttpEntity, @RestControllerAdvice, DelegatingFilterProxy

- **RestTemplate:** 

    customize http request head
    
- **Data Access**
  - **JDBC:** 
  
      @Repository, JdbcTemplate, ResourceDatabasePopulator
      
  - **Transaction:** 
  
      PlatformTransactionManager, @Transactional
- **Test Framework:** 

    SpringRunner, @ContextConfiguration, @ActiveProfiles, @TestPropertySource, @WebAppConfiguration,  MockMvc, MockRestServiceServer, @Sql, EmbeddedDatabaseBuilder
  

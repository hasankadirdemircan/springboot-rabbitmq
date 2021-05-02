# Spring Boot Messaging with RabbitMQ

### How can i run RabbitMQ ?

```
$  docker-compose -f docker-compose.yml up -d
```

### How is it work?
- There are 3 examples within the project,
Exchange Types
1) Direct, 
2) Topic
3) Fanout

> Therefore, I've created 2 projects that consumer and rabbitmq,
You can see that how can we handle rabbitmq produce/consume process,
Consumer Example:  only to see that how does fanout work,
